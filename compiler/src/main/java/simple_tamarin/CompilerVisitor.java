package simple_tamarin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple_tamarin.Constants.*;
import simple_tamarin.dataStructures.*;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.parser.Simple_tamarinParser.*;
import simple_tamarin.errors.Errors;
import simple_tamarin.errors.STException;

/**
 * This class implements all visitor methods of the Simple_tamarin compiler
 */
public class CompilerVisitor {	
	private FileWriter writer;
	private StModel model;

	// focused objects - objects that are currently being worked on
	private Principal curPrincipal;
	private StBlock curBlock;
	private Term curTerm;

	// Changes behavior of visitVariable
	private VariableDefined expectDefinedVariables;

	public CompilerVisitor(FileWriter writer, boolean quitOnWarning, boolean showInfo) {
		this.writer = writer;
		Errors.quitOnWarning = quitOnWarning;
		Errors.showInfo = showInfo;
	}

	public void visitModel(ModelContext ctx) {
		this.model = new StModel();
		for (SegmentContext segment : ctx.segment()) {
			visitSegment(segment);
		}

		for (Principal principal : model.principals) {
			principal.nextBlock(); // add last nextBlock, so that incomming messages are received
			principal.squishBlocks();
		}

		Builder builder = new Builder(model);
		try {
			writer.write(builder.output());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void visitSegment(SegmentContext ctx) {
		if (ctx.messageBlock() != null) {
			visitMessageBlock(ctx.messageBlock());
			return;
		}
		if (ctx.principalBlock() != null) {
			visitPrincipalBlock(ctx.principalBlock());
			return;
		}
		if (ctx.queriesBlock() != null) {
			visitQueriesBlock(ctx.queriesBlock());
			return;
		}
	}

	public void visitPrincipalBlock(PrincipalBlockContext ctx) {
		String principalName = ctx.principal.getText();
		if (!identifierNameValid(principalName)) {
			Errors.ErrorReservedName(ctx.principal);
		}
		
		Principal principal = model.findPrincipal(principalName);
		if (principal == null) {
			Errors.InfoDeclarePrincipal(ctx.principal);
			// TODO allow declaring principals
			if (model.findVariable(principalName) != null) {
				Errors.ErrorPrincipalNameCollision(ctx.principal);
			}
			principal = model.addPrincipal(principalName);
		}

		// init focused objects
		curPrincipal = principal;
		curBlock = curPrincipal.nextBlock;
		curPrincipal.nextBlock();

		for (CommandContext command : ctx.command()) {
			visitCommand(command);
		}
	}

	public void visitCommand(CommandContext ctx) {
		if (ctx.knows() != null) {
			visitKnows(ctx.knows());
			return;
		}
		if (ctx.generates() != null) {
			visitGenerates(ctx.generates());
			return;
		}
		if (ctx.assignment() != null) {
			visitAssignment(ctx.assignment());
			return;
		}
		if (ctx.check() != null) {
			visitCheck(ctx.check());
			return;
		}
	}

	public void visitKnows(KnowsContext ctx) {
		if (curPrincipal.blocks.get(0) != curBlock) {
			Errors.InfoKnowsInFirstBlock(ctx.getStart());
		}

		boolean pub = (ctx.modifier.getText().equals("public")) ? true : false;
		expectDefinedVariables = pub ? VariableDefined.ONLY_PUBLIC : VariableDefined.ONLY_SHADOW_PUBLIC;
		visitVariable(ctx.variable());
		Variable variable = (Variable)curTerm;

		if (pub) {
			variable.sort = VariableSort.PUBLIC;
			curPrincipal.initState.add(variable);
		} else {
			variable.sort = VariableSort.FRESH;
			// unify the private variable with one known by another principal
			for (Principal principal : model.principals) {
				Variable existing = principal.knows(variable.name);
				if (existing != null) {
					if (existing.cratedBy == null) {
						// created by null confirms it's a long term variable
						variable = existing;
					} else {
						Errors.WarningVariableEphemeralShadowed(ctx.variable().start);
					}
				}
			}
		}
		curPrincipal.learn(variable);
		curPrincipal.initState.add(variable);
	}

	public void visitGenerates(GeneratesContext ctx) {
		expectDefinedVariables = VariableDefined.ONLY_SHADOW_PUBLIC;
		visitVariable(ctx.variable());
		Variable variable = (Variable)curTerm;
		variable.cratedBy = curPrincipal;
		variable.sort = VariableSort.FRESH;
		curPrincipal.knowledge.add(variable);
		curBlock.premise.add(new Command(CommandType.FRESH, variable));
		curBlock.state.add(variable);
	}

	public void visitCheck(CheckContext ctx){
		visitFunctionCall(ctx.functionCall());
	}

	public void visitMessageBlock(MessageBlockContext ctx) {
		Principal sender = model.findPrincipal(ctx.sender.getText());
		if (sender == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.sender);
		}
		Principal receiver = model.findPrincipal(ctx.receiver.getText());
		if (receiver == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.receiver);
		}

		curPrincipal = sender;
		expectDefinedVariables = VariableDefined.YES;
		for (TermContext message : ctx.term()) {
			visitTerm(message);

			if (!curTerm.canBeLearnt()) {
				Errors.ErrorMessageContainsUnnamed(message);
			}

			// if it's not a public variable
			if (!(curTerm instanceof Variable) || model.findVariable(((Variable)curTerm).name) == null) {
				// add all new variables to receiver's knowledge
				receiver.learn(curTerm);
			}

			if (sender.blocks.isEmpty()) {
				// sender may send a public variable before doing anything else, in that case it needs an initial block
				sender.nextBlock();
			}

			StBlock sendersLastBlock = sender.blocks.get(sender.blocks.size()-1);
			sendersLastBlock.result.add(new Command(CommandType.OUT, curTerm));
			receiver.nextBlock.premise.add(new Command(CommandType.IN, curTerm));
			if (!receiver.nextBlock.state.contains(curTerm)) {
				// TODO: we're adding the entire message to state, some deconstruction or extraction may be in order
				receiver.nextBlock.state.add(curTerm);
			}
		}
	}

	public void visitAssignment(AssignmentContext ctx) {
		expectDefinedVariables = VariableDefined.ONLY_SHADOW_PUBLIC;
		visitTerm(ctx.left);
		Term left = curTerm;

		expectDefinedVariables = VariableDefined.YES;
		visitTerm(ctx.right);
		Term right = curTerm;

		curBlock.aliases.add(new Alias(left, right));
		curBlock.state.add(left);

		List<Variable> learntVariables = left.unify(right);
		for (Variable variable : learntVariables) {
			if (!curPrincipal.knowledge.contains(variable)) {
				curPrincipal.knowledge.add(variable);
			}
		}
	}

	public void visitTerm(TermContext ctx) {
		if (ctx.variable() != null) {
			visitVariable(ctx.variable());
			return;
		}
		if (ctx.functionCall() != null) {
			visitFunctionCall(ctx.functionCall());
			return;
		}
		if (ctx.tuple() != null) {
			visitTuple(ctx.tuple());
			return;
		}
	}

	/**
	 * Finds a variable if it should have existed or creates a new one otherwise.
	 */
	public void visitVariable(VariableContext ctx) {
		String name = ctx.IDENTIFIER().getText();
		
		curTerm = curPrincipal.knows(name);
		if (curTerm != null) {
			switch (expectDefinedVariables) {
				case NO:
				case ONLY_SHADOW_PUBLIC:
				case ONLY_PUBLIC:
					Errors.ErrorVariableCollisionPrivate(curPrincipal, ctx.start);
				default:
					return;
			}
		}

		curTerm = model.findVariable(name);
		if (curTerm != null) {
			switch (expectDefinedVariables) {
				case NO:
					Errors.ErrorVariableCollisionPublic(curTerm, ctx.start);
					return;
				case ONLY_SHADOW_PUBLIC:
					Errors.WarningVariableShadowed(ctx.start);
					break;
				default:
					return;
			}
		}

		if (!identifierNameValid(name)) {
			Errors.ErrorReservedName(ctx.start);
		}

		switch (expectDefinedVariables) {
			case ONLY_PUBLIC:
			case YES:
				Errors.ErrorVariableUnknown(curPrincipal, ctx.start);
			case PLEASE:
				Errors.InfoDeclareLongTermVariable(ctx.start);
			default:
				curTerm = new Variable(name);
				return;
		}
	}

	public void visitFunctionCall(FunctionCallContext ctx) {
		switch (ctx.FUNCTION().getText()) {
			case Constants.VPSENC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				VariableDefined restoreEDV = expectDefinedVariables;
				expectDefinedVariables = VariableDefined.YES;
				visitTerm(ctx.argument.get(0));
				Term key = curTerm;
				expectDefinedVariables = restoreEDV;
				visitTerm(ctx.argument.get(1));
				Term value = curTerm;
				curTerm = new FunctionSenc(key, value);
				return;
			}
			case Constants.VPSDEC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				VariableDefined restoreEDV = expectDefinedVariables;
				expectDefinedVariables = VariableDefined.YES;
				visitTerm(ctx.argument.get(0));
				Term key = curTerm;
				expectDefinedVariables = restoreEDV;
				visitTerm(ctx.argument.get(1));
				// if value is a variable find it's definition
				Term realValue = curTerm;
				while (realValue instanceof Variable) {
					realValue = ((Variable)realValue).subterm;
				}
				if (!(realValue instanceof FunctionSenc)) {
					Errors.ErrorDecodingNotEncoded(ctx.argument.get(1));
				}
				if (!((FunctionSenc)realValue).key.equals(key)) {
					Errors.ErrorWrongKey(ctx.argument.get(0));
				}
				realValue = ((FunctionSenc)realValue).value;
				curTerm = new FunctionSdec(key, curTerm, realValue);
				return;
			}
			case Constants.VPASSERT: {
				expectDefinedVariables = VariableDefined.YES;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				visitTerm(ctx.argument.get(0));
				Term term1 = curTerm;
				visitTerm(ctx.argument.get(1));
				Term term2 = curTerm;
				if (!(term1.equals(term2))) {
					Errors.WarningAssertNeverTrue(ctx.start);
				}
				model.builtins.restriction_eq = true;
				curBlock.actions.add(new ActionFact(Constants.EQUALITY, new ArrayList<Term>(Arrays.asList(term1, term2))));
			}
			default: {
				throw new STException("Debug: Unexpected function type: " + ctx.FUNCTION().getText() + " in visitFunctionCall.");
			}
		}
	}

	public void visitTuple(TupleContext ctx) {
		ArrayList<Term> subterms = new ArrayList<>();
		for (TermContext termctx : ctx.term()) {
			visitTerm(termctx);
			subterms.add(curTerm);
		}
		curTerm = new Tuple(subterms);
		return;
	}

	public void visitQueriesBlock(QueriesBlockContext ctx) {
		for (QueryContext query : ctx.query()) {
			visitQuery(query);
		}
	}

	public void visitQuery(QueryContext ctx) {
		if (ctx.executable() != null) {
			visitExecutable(ctx.executable());
			return;
		}
	}

	public void visitExecutable(ExecutableContext ctx) {
		if (model.queries.executable == true) {
			Errors.WarningQueryExecutableDuplicite(ctx.start);
		} else {
			model.queries.executable = true;
		}
	}

	private boolean identifierNameValid(String id) {
		for (String name : Constants.reservedNames) {
			if (name.equals(id)) {
				return false;
			}
		}
		return true;
	}
}