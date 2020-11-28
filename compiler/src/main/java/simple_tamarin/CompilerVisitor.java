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
		StBlock block = principal.nextBlock;
		principal.nextBlock();

		for (CommandContext command : ctx.command()) {
			visitCommand(command, principal, block);
		}
	}

	public void visitCommand(CommandContext ctx, Principal principal, StBlock block) {
		if (ctx.knows() != null) {
			visitKnows(ctx.knows(), principal, block);
			return;
		}
		if (ctx.generates() != null) {
			visitGenerates(ctx.generates(), principal, block);
			return;
		}
		if (ctx.assignment() != null) {
			visitAssignment(ctx.assignment(), principal, block);
			return;
		}
		if (ctx.check() != null) {
			visitCheck(ctx.check(), principal, block);
			return;
		}
	}

	public void visitKnows(KnowsContext ctx, Principal principal, StBlock block) {
		if (principal.blocks.get(0) != block) {
			Errors.InfoKnowsInFirstBlock(ctx.getStart());
		}

		boolean pub = (ctx.modifier.getText().equals("public")) ? true : false;
		VariableDefined expectVD = pub ? VariableDefined.ONLY_PUBLIC : VariableDefined.ONLY_SHADOW_PUBLIC;
		Variable variable = visitVariable(ctx.variable(), principal, expectVD);

		if (pub) {
			variable.sort = VariableSort.PUBLIC;
			principal.initState.add(variable);
		} else {
			variable.sort = VariableSort.FRESH;
			// unify the private variable with one known by another principal
			for (Principal anyPrincipal : model.principals) {
				Variable existing = anyPrincipal.knows(variable.name);
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
		principal.learn(variable);
		principal.initState.add(variable);
	}

	public void visitGenerates(GeneratesContext ctx, Principal principal, StBlock block) {
		VariableDefined expectVD = VariableDefined.ONLY_SHADOW_PUBLIC;
		Variable variable = visitVariable(ctx.variable(), principal, expectVD);
		variable.cratedBy = principal;
		variable.sort = VariableSort.FRESH;
		principal.knowledge.add(variable);
		block.premise.add(new Command(CommandType.FRESH, variable));
		block.state.add(variable);
	}

	public void visitCheck(CheckContext ctx, Principal principal, StBlock block){
		visitFunctionCall(ctx.functionCall(), principal, block, VariableDefined.YES);
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

		VariableDefined expectVD = VariableDefined.YES;
		for (TermContext message : ctx.term()) {
			Term term = visitTerm(message, sender, null, expectVD);

			if (!term.canBeLearnt()) {
				Errors.ErrorMessageContainsUnnamed(message);
			}

			// if it's not a public variable
			if (!(term instanceof Variable) || model.findVariable(((Variable)term).name) == null) {
				// add all new variables to receiver's knowledge
				receiver.learn(term);
			}

			if (sender.blocks.isEmpty()) {
				// sender may send a public variable before doing anything else, in that case it needs an initial block
				sender.nextBlock();
			}

			StBlock sendersLastBlock = sender.blocks.get(sender.blocks.size()-1);
			sendersLastBlock.result.add(new Command(CommandType.OUT, term));
			receiver.nextBlock.premise.add(new Command(CommandType.IN, term));
			if (!receiver.nextBlock.state.contains(term)) {
				// TODO: we're adding the entire message to state, some deconstruction or extraction may be in order
				receiver.nextBlock.state.add(term);
			}
		}
	}

	public void visitAssignment(AssignmentContext ctx, Principal principal, StBlock block) {
		Term left = visitTerm(ctx.left, principal, block, VariableDefined.ONLY_SHADOW_PUBLIC);
		Term right = visitTerm(ctx.right, principal, block, VariableDefined.YES);

		block.aliases.add(new Alias(left, right));
		block.state.add(left);

		List<Variable> learntVariables = left.unify(right);
		for (Variable variable : learntVariables) {
			if (principal.knows(variable) == null) {
				principal.knowledge.add(variable);
			}
		}
	}

	public Term visitTerm(TermContext ctx, Principal principal, StBlock block, VariableDefined expectVD) {
		if (ctx.variable() != null) {
			return visitVariable(ctx.variable(), principal, expectVD);
		}
		if (ctx.functionCall() != null) {
			return visitFunctionCall(ctx.functionCall(), principal, block, expectVD);
		}
		if (ctx.tuple() != null) {
			return visitTuple(ctx.tuple(), principal, block, expectVD);
		}
		return null; // TODO debug message
	}

	/**
	 * Finds a variable if it should have existed or creates a new one otherwise.
	 */
	public Variable visitVariable(VariableContext ctx, Principal principal, VariableDefined expectVD) {
		String name = ctx.IDENTIFIER().getText();
		
		Variable result = principal.knows(name);
		if (result != null) {
			switch (expectVD) {
				case NO:
				case ONLY_SHADOW_PUBLIC:
				case ONLY_PUBLIC:
					Errors.ErrorVariableCollisionPrivate(principal, ctx.start);
				default:
					return result;
			}
		}

		result = model.findVariable(name);
		if (result != null) {
			switch (expectVD) {
				case NO:
					Errors.ErrorVariableCollisionPublic(result, ctx.start);
					return result;
				case ONLY_SHADOW_PUBLIC:
					Errors.WarningVariableShadowed(ctx.start);
					break;
				default:
					return result;
			}
		}

		if (!identifierNameValid(name)) {
			Errors.ErrorReservedName(ctx.start);
		}

		switch (expectVD) {
			case ONLY_PUBLIC:
			case YES:
				Errors.ErrorVariableUnknown(principal, ctx.start);
			case PLEASE:
				Errors.InfoDeclareLongTermVariable(ctx.start);
			default:
				result = new Variable(name);
				return result;
		}
	}

	public Term visitFunctionCall(FunctionCallContext ctx, Principal principal, StBlock block, VariableDefined expectVD) {
		switch (ctx.FUNCTION().getText()) {
			case Constants.VPSENC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				Term key = visitTerm(ctx.argument.get(0), principal, block, VariableDefined.YES);
				Term value = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				return new FunctionSenc(key, value);
			}
			case Constants.VPSDEC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				Term key = visitTerm(ctx.argument.get(0), principal, block, VariableDefined.YES);
				Term value = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				// if value is a variable find it's definition
				Term decodedValue = value.deconstructTerm();
				if (!(decodedValue instanceof FunctionSenc)) {
					Errors.ErrorDecodingNotEncoded(ctx.argument.get(1));
				}
				if (!((FunctionSenc)decodedValue).key.equals(key)) {
					Errors.ErrorWrongKey(ctx.argument.get(0));
				}
				decodedValue = ((FunctionSenc)decodedValue).value;
				return new FunctionSdec(key, value, decodedValue);
			}
			case Constants.VPASSERT: {
				model.builtins.restriction_eq = true;
				expectVD = VariableDefined.YES;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				Term term1 = visitTerm(ctx.argument.get(0), principal, block, expectVD);
				Term term2 = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				if (!(term1.equals(term2))) {
					Errors.WarningAssertNeverTrue(ctx.start);
				}
				block.actions.add(new Fact(Constants.EQUALITY, new ArrayList<Term>(Arrays.asList(term1, term2))));
			}
			case Constants.VPHASH: {
				model.builtins.hashing = true;
				if (ctx.argument.size() < 1) {
					Errors.ErrorArgumentsMinCount(ctx.FUNCTION().getSymbol(), 1, ctx.argument.size());
				}
				ArrayList<Term> subterms = new ArrayList<>();
				for (TermContext termctx : ctx.argument) {
					subterms.add(visitTerm(termctx, principal, block, expectVD));
				}
				return new FunctionHash(subterms);
			}
			default: {
				throw new STException("Debug: Unexpected function type: " + ctx.FUNCTION().getText() + " in visitFunctionCall.");
			}
		}
	}

	public Term visitTuple(TupleContext ctx, Principal principal, StBlock block, VariableDefined expectVD) {
		ArrayList<Term> subterms = new ArrayList<>();
		for (TermContext termctx : ctx.term()) {
			subterms.add(visitTerm(termctx, principal, block, expectVD));
		}
		return new Tuple(subterms);
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