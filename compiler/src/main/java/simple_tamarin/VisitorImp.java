package simple_tamarin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple_tamarin.Constants.*;
import simple_tamarin.dataStructures.*;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.parser.*;
import simple_tamarin.parser.Simple_tamarinParser.*;

/**
 * This class implements all visitor methods of the Simple_tamarin compiler
 */
public class VisitorImp extends Simple_tamarinBaseVisitor<Integer> {
	public boolean quitOnWarning;
	
	private FileWriter writer;
	private StModel model;

	// focused objects - objects that are currently being worked on
	private Principal curPrincipal;
	private StBlock curBlock;
	private Term curTerm;

	// Changes behavior of visitVariable
	private VariableDefined expectDefinedVariables;

	public VisitorImp(FileWriter writer, boolean quitOnWarning, boolean showInfo) {
		this.writer = writer;
		this.quitOnWarning = quitOnWarning;
		Errors.showInfo = showInfo;
	}

	@Override	public Integer visitModel(ModelContext ctx) {
		this.model = new StModel();
		for (SegmentContext segment : ctx.segment()) {
			if (visitSegment(segment) != 0) {
				return 1;
			}
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
		return 0;
	}

	@Override public Integer visitSegment(SegmentContext ctx) { 
		return visitChildren(ctx);
	}

	@Override public Integer visitPrincipalBlock(PrincipalBlockContext ctx) {
		String principalName = ctx.principal.getText();
		if (!identifierNameValid(principalName)) {
			Errors.ErrorReservedName(ctx.principal);
			return 1;
		}
		
		Principal principal = model.findPrincipal(principalName);
		if (principal == null) {
			Errors.InfoDeclarePrincipal(ctx.principal);
			// TODO allow declaring principals
			if (model.findVariable(principalName) != null) {
				Errors.ErrorPrincipalNameCollision(ctx.principal);
				return 1;
			}
			principal = model.addPrincipal(principalName);
		}

		// init focused objects
		curPrincipal = principal;
		curBlock = curPrincipal.nextBlock;
		curPrincipal.nextBlock();

		for (CommandContext command : ctx.command()) {
			if (visitCommand(command) != 0)
				return 1;
		}

		return 0;
	}

	@Override public Integer visitCommand(CommandContext ctx) {
		return visitChildren(ctx);
	}

	@Override public Integer visitKnows(KnowsContext ctx) {
		if (curPrincipal.blocks.get(0) != curBlock) {
			Errors.InfoKnowsInFirstBlock(ctx.getStart());
		}

		boolean pub = (ctx.modifier.getText().equals("public")) ? true : false;
		expectDefinedVariables = pub ? VariableDefined.ONLY_PUBLIC : VariableDefined.ONLY_SHADOW_PUBLIC;
		if (visitVariable(ctx.variable()) != 0) {
			return 1;
		} 
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
						if (quitOnWarning) {
							return 1;
						}
					}
				}
			}
		}
		curPrincipal.learn(variable);
		curPrincipal.initState.add(variable);
		return 0;
	}

	@Override public Integer visitGenerates(GeneratesContext ctx) {
		expectDefinedVariables = VariableDefined.ONLY_SHADOW_PUBLIC;
		if (visitVariable(ctx.variable()) != 0) {
			return 1;
		}
		Variable variable = (Variable)curTerm;
		variable.cratedBy = curPrincipal;
		variable.sort = VariableSort.FRESH;
		curPrincipal.knowledge.add(variable);
		curBlock.premise.add(new Command(CommandType.FRESH, variable));
		curBlock.state.add(variable);
		return 0;
	}

	@Override public Integer visitCheck(CheckContext ctx){
		return visitChildren(ctx);
	}

	@Override public Integer visitMessageBlock(MessageBlockContext ctx) {
		Principal sender = model.findPrincipal(ctx.sender.getText());
		if (sender == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.sender);
			return 1;
		}
		Principal receiver = model.findPrincipal(ctx.receiver.getText());
		if (receiver == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.receiver);
			return 1;
		}

		curPrincipal = sender;
		expectDefinedVariables = VariableDefined.YES;
		for (TermContext message : ctx.term()) {
			visitTerm(message);

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
		return 0;
	}

	@Override public Integer visitAssignment(AssignmentContext ctx) {
		expectDefinedVariables = VariableDefined.ONLY_SHADOW_PUBLIC;
		if (visitTerm(ctx.left) != 0) {
			return 1;
		}
		Term left = curTerm;

		expectDefinedVariables = VariableDefined.YES;
		if (visitTerm(ctx.right) != 0) {
			return 1;
		}
		Term right = curTerm;

		curBlock.aliases.add(new Alias(left, right));
		curBlock.state.add(left);

		List<Variable> learntVariables = left.unify(right);
		for (Variable variable : learntVariables) {
			if (!curPrincipal.knowledge.contains(variable)) {
				curPrincipal.knowledge.add(variable);
			}
		}
		return 0;
	}

	@Override public Integer visitTerm(TermContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * Finds a variable if it should have existed or creates a new one otherwise.
	 */
	@Override public Integer visitVariable(VariableContext ctx) {
		String name = ctx.IDENTIFIER().getText();
		
		curTerm = curPrincipal.knows(name);
		if (curTerm != null) {
			switch (expectDefinedVariables) {
				case NO:
				case ONLY_SHADOW_PUBLIC:
				case ONLY_PUBLIC:
					Errors.ErrorVariableCollisionPrivate(curPrincipal, ctx.start);
					return 1;
				default:
					return 0;
			}
		}

		curTerm = model.findVariable(name);
		if (curTerm != null) {
			switch (expectDefinedVariables) {
				case NO:
					Errors.ErrorVariableCollisionPublic(curTerm, ctx.start);
					return 1;
				case ONLY_SHADOW_PUBLIC:
					Errors.WarningVariableShadowed(ctx.start);
					if (quitOnWarning) {
						return 1;
					}
					break;
				default:
					return 0;
			}
		}

		if (!identifierNameValid(name)) {
			Errors.ErrorReservedName(ctx.start);
			return 1;
		}

		switch (expectDefinedVariables) {
			case ONLY_PUBLIC:
			case YES:
				Errors.ErrorVariableUnknown(curPrincipal, ctx.start);
				return 1;
			case PLEASE:
				Errors.InfoDeclareLongTermVariable(ctx.start);
			default:
				curTerm = new Variable(name);
				return 0;
		}
	}

	@Override public Integer visitFunctionCall(FunctionCallContext ctx) {
		switch (ctx.FUNCTION().getText()) {
			case Constants.VPSENC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
					return 1;
				}
				VariableDefined restoreEDV = expectDefinedVariables;
				expectDefinedVariables = VariableDefined.YES;
				if (visitTerm(ctx.argument.get(0)) != 0 ) {
					return 1;
				}
				Term key = curTerm;
				expectDefinedVariables = restoreEDV;
				if (visitTerm(ctx.argument.get(1)) != 0 ) {
					return 1;
				}
				Term value = curTerm;
				curTerm = new FunctionSenc(key, value);
				return 0;
			}
			case Constants.VPSDEC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
					return 1;
				}
				VariableDefined restoreEDV = expectDefinedVariables;
				expectDefinedVariables = VariableDefined.YES;
				if (visitTerm(ctx.argument.get(0)) != 0 ) {
					return 1;
				}
				Term key = curTerm;
				expectDefinedVariables = restoreEDV;
				if (visitTerm(ctx.argument.get(1)) != 0 ) {
					return 1;
				}
				// if value is a variable find it's definition
				Term realValue = curTerm;
				while (realValue instanceof Variable) {
					realValue = ((Variable)realValue).subterm;
				}
				if (!(realValue instanceof FunctionSenc)) {
					Errors.ErrorDecodingNotEncoded(ctx.argument.get(1));
					return 1;
				}
				if (!((FunctionSenc)realValue).key.equals(key)) {
					Errors.ErrorWrongKey(ctx.argument.get(0));
					return 1;
				}
				realValue = ((FunctionSenc)realValue).value;
				curTerm = new FunctionSdec(key, curTerm, realValue);
				return 0;
			}
			case Constants.VPASSERT: {
				expectDefinedVariables = VariableDefined.YES;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
					return 1;
				}
				if (visitTerm(ctx.argument.get(0)) != 0 ) {
					return 1;
				}
				Term term1 = curTerm;
				if (visitTerm(ctx.argument.get(1)) != 0 ) {
					return 1;
				}
				Term term2 = curTerm;
				if (!(term1.equals(term2))) {
					Errors.WarningAssertNeverTrue(ctx.start);
					if (quitOnWarning){
						return 1;
					}
				}
				model.builtins.restriction_eq = true;
				curBlock.actions.add(new ActionFact(Constants.EQUALITY, new ArrayList<Term>(Arrays.asList(term1, term2))));
			}
			default: {
				System.out.println("Debug: Unexpected function type: " + ctx.FUNCTION().getText() + " in visitFunctionCall.");
				return 1;
			}
		}
	}

	@Override public Integer visitTuple(TupleContext ctx) {
		ArrayList<Term> subterms = new ArrayList<>();
		for (TermContext tctx : ctx.term()) {
			if (visitTerm(tctx) != 0 ) {
				return 1;
			}
			subterms.add(curTerm);
		}
		curTerm = new Tuple(subterms);
		return 0;
	}

	@Override public Integer visitQueriesBlock(QueriesBlockContext ctx) {
		for (QueryContext query : ctx.query()) {
			if (visitQuery(query) != 0)
			return 1;
		}
		return 0;
	}

	@Override public Integer visitQuery(QueryContext ctx) {
		if (visitChildren(ctx) != 0) {
			return 1;
		}
		return 0;
	}

	@Override public Integer visitExecutable(ExecutableContext ctx) {
		if (model.queries.executable == true) {
			Errors.WarningQueryExecutableDuplicite(ctx.start);
			if (quitOnWarning) {
				return 1;
			}
		} else {
			model.queries.executable = true;
		}

		return 0;
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