package simple_tamarin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.antlr.v4.runtime.Token;

import simple_tamarin.Constants.*;
import simple_tamarin.dataStructures.*;
import simple_tamarin.dataStructures.query.Confidentiality;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.stParser.Simple_tamarinParser.*;
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

	public StModel visitModel(ModelContext ctx) {
		this.model = new StModel();
		for (DeclarationContext dctx : ctx.declaration()) {
			visitDeclaration(dctx);
		}
		for (SpecificationSegmentContext sctx : ctx.specificationSegment()) {
			visitSpecificationSegment(sctx);
		}
		if (ctx.queriesBlock() != null) {
			visitQueriesBlock(ctx.queriesBlock());
		}

		for (Principal principal : model.getPrincipals()) {
			principal.nextBlock(); // add last nextBlock to blocks list
			principal.squishBlocks();
		}

		Builder builder = new Builder(model);
		try {
			writer.write(builder.output());
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new STException();
		}
		return model;
	}

	public void visitSpecificationSegment(SpecificationSegmentContext ctx) {
		if (ctx.message() != null) {
			visitMessage(ctx.message());
			return;
		}
		if (ctx.principalBlock() != null) {
			visitPrincipalBlock(ctx.principalBlock());
			return;
		}
	}

	private void visitDeclaration(DeclarationContext ctx) {
		if (ctx.decPrincipals() != null) {
			visitDecPrincipals(ctx.decPrincipals());
			return;
		}
	}

	private void visitDecPrincipals(DecPrincipalsContext decPrincipals) {
		for (Token pctx : decPrincipals.principal) {
			String principalName = pctx.getText();
			if (!identifierNameValid(principalName)) {
				Errors.ErrorReservedName(pctx);
			}
			// check for name collision, allso covers duplicite principals
			if (model.findVariable(principalName) != null) {
				Errors.ErrorPrincipalNameCollision(pctx);
			}

			model.addPrincipal(principalName);
			model.builtins.principalsWereDeclared = true;
		}
	}

	public void visitPrincipalBlock(PrincipalBlockContext ctx) {
		String principalName = ctx.principal.getText();
		if (!identifierNameValid(principalName)) {
			Errors.ErrorReservedName(ctx.principal);
		}
		
		Principal principal = model.findPrincipal(principalName);
		if (principal == null) {
			if (model.builtins.principalsWereDeclared) {
				Errors.WarningUndeclaredPrincipal(ctx.principal);
			} else {
				Errors.InfoDeclarePrincipal(ctx.principal);
			}
			if (model.findVariable(principalName) != null) {
				Errors.ErrorPrincipalNameCollision(ctx.principal);
			}
			principal = model.addPrincipal(principalName);
		}

		StBlock curBlock = principal.nextBlock;
		principal.nextBlock();
		for (CommandContext command : ctx.command()) {
			visitCommand(command, principal, curBlock);
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
		Errors.DebugUnexpectedTokenType(ctx.getText(), "visitCommad");
	}

	public void visitKnows(KnowsContext ctx, Principal principal, StBlock block) {
		if (principal.getFirstBlock() != block) {
			Errors.InfoKnowsInFirstBlock(ctx.getStart());
		}

		String modifier = ctx.modifier.getText();
		boolean pub;
		if (modifier.equals("public")){
			pub = true;
		} else if (modifier.equals("private")){
			pub = false;
		} else {
			Errors.DebugUnexpectedTokenType(modifier, "modifier in knows");
			return;
		}

		VariableDefined expectVD = pub ? VariableDefined.PUBLIC_KNOWS : VariableDefined.PRIVATE_KNOWS;
		for (VariableContext vctx : ctx.variable()) {
			Variable variable = visitVariable(vctx, principal, block, expectVD);

			if (pub) {
				variable.sort = VariableSort.PUBLIC;
				principal.initState.add(variable);
			} else {
				// sort will be set to fresh when init block is rendered
				// possibly unify the private variable with one known by another principal
				for (Principal anyPrincipal : model.getPrincipals()) {
					Variable existing = anyPrincipal.knows(variable.name);
					if (existing != null) {
						if (existing.cratedBy == null) {
							// created by null confirms it's a long term variable
							variable = existing;
						} else {
							Errors.WarningVariableEphemeralShadowed(vctx.start);
						}
					}
				}
			}
			principal.learn(variable);
			principal.initState.add(variable);
			model.builtins.prefab_private_reveal = true;
			principal.initResults.add(new Fact(true, Constants.PRINCIPAL_PRIVATE, Arrays.asList(principal.principalID, variable)));
		}
	}

	public void visitGenerates(GeneratesContext ctx, Principal principal, StBlock block) {
		for (VariableContext vctx : ctx.variable()) {
			Variable variable = visitVariable(vctx, principal, block, VariableDefined.PRIVATE_GENERATES);
			principal.learn(variable);
			block.premise.add(new Command(CommandType.FRESH, variable));
			block.state.add(variable);
		}
	}

	public void visitCheck(CheckContext ctx, Principal principal, StBlock block){
		visitCheckedCall(ctx.checkedCall(), principal, block, VariableDefined.USE_RIGHT);
	}

	public void visitMessage(MessageContext ctx) {
		Principal sender = model.findPrincipal(ctx.sender.getText());
		if (sender == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.sender);
		}
		Principal receiver = model.findPrincipal(ctx.receiver.getText());
		if (receiver == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.receiver);
		}

		for (TermContext message : ctx.term()) {
			// visitTerm verifies that message is transparent (when expectVD is USE_MESSAGE)
			Term term = visitTerm(message, sender, null, VariableDefined.USE_MESSAGE);

			// if it's not a public variable
			if (!(term instanceof Variable) || model.findVariable(((Variable)term).name) == null) {
				// add all new variables to receiver's knowledge
				for (Variable variable : term.extractKnowledge()) {
					receiver.learn(new Variable(variable, receiver));
				}
			}

			if (sender.getBlocks().isEmpty()) {
				// sender may send a public variable before doing anything else, in that case it needs an initial block
				sender.nextBlock();
			}

			sender.getLastBlock().result.add(new Command(CommandType.OUT, term));
			receiver.nextBlock.premise.add(new Command(CommandType.IN, term));
			if (!receiver.nextBlock.state.contains(term)) {
				receiver.nextBlock.state.add(term);
			}
		}
	}

	public void visitAssignment(AssignmentContext ctx, Principal principal, StBlock block) {
		Term left = visitTerm(ctx.left, principal, block, VariableDefined.PRIVATE_LEFT);
		Term right = visitTerm(ctx.right, principal, block, VariableDefined.USE_RIGHT);

		block.state.add(left);

		if (!left.assign(right, block, principal)) {
			Errors.ErrorCannotUnify(ctx.left, ctx.right);
		}
	}

	public Term visitTerm(TermContext ctx, Principal principal, StBlock block, VariableDefined expectVD) {
		if (ctx.variable() != null) {
			return visitVariable(ctx.variable(), principal, block, expectVD);
		}
		if (ctx.functionCall() != null) {
			return visitFunctionCall(ctx.functionCall(), principal, block, expectVD);
		}
		if (ctx.tuple() != null) {
			return visitTuple(ctx.tuple(), principal, block, expectVD);
		}
		Errors.DebugUnexpectedTokenType(ctx.getText(), "visitTerm()");
		return null;
	}

	/**
	 * Finds a variable if it should have existed or creates a new one otherwise.
	 */
	public Variable visitVariable(VariableContext ctx, Principal principal, StBlock block, VariableDefined expectVD) {
		String name = ctx.IDENTIFIER().getText();
		
		Variable result = principal.knows(name);
		if (result != null) {
			switch (expectVD) {
				case PUBLIC_DEFINITION:
				case PRIVATE_KNOWS:
				case PRIVATE_GENERATES:
				case PUBLIC_KNOWS:
					Errors.ErrorVariableCollisionPrivate(principal, ctx.start);
					return null;
				default:
					return result;
			}
		}

		result = model.findVariable(name);
		if (result != null) {
			switch (expectVD) {
				case PUBLIC_DEFINITION:
					Errors.ErrorVariableCollisionPublic(result, ctx.start);
					return result;
				case PRIVATE_KNOWS:
				case PRIVATE_GENERATES:
				case PRIVATE_LEFT:
					Errors.WarningVariableShadowed(ctx.start);
					break; // and go define it as private placeholder
				default:
					return result;
			}
		}

		if (!identifierNameValid(name)) {
			Errors.ErrorReservedName(ctx.start);
		}

		switch (expectVD) {
			case USE_RIGHT:
			case USE_MESSAGE:
				Errors.ErrorVariableUnknown(principal, ctx.start);
				return null;
			case PUBLIC_KNOWS:
				Errors.InfoDeclareLongTermVariable(ctx.start);
			case PRIVATE_KNOWS:
				return new Variable(name);
			case PRIVATE_LEFT:
				return Variable.placeholder(name);
			default:
				return new Variable(name, block);
		}
	}

	public void visitCheckedCall(CheckedCallContext ctx, Principal principal, StBlock block, VariableDefined expectVD) {
		switch (ctx.CHECKED().getText()) {
			case Constants.VPASSERT: {
				model.builtins.restriction_eq = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.CHECKED().getSymbol(), 2, ctx.argument.size());
				}
				Term term1 = visitTerm(ctx.argument.get(0), principal, block, expectVD);
				Term term2 = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				if (!(term1.equals(term2))) {
					Errors.WarningAssertNeverTrue(ctx.start);
				}
				block.actions.add(Fact.equality(term1, term2));
			}
			return;
			default: {	
				Errors.DebugUnexpectedTokenType(ctx.CHECKED().getText(), "visitCheckedCall");
			}
		}
	}
	

	public Term visitFunctionCall(FunctionCallContext ctx, Principal principal, StBlock block, VariableDefined expectVD) {
		if (expectVD == VariableDefined.PRIVATE_LEFT) {
			// so far we have no transparent functions
			Errors.ErrorLeftNontransparent(ctx.start);
		}
		if (expectVD == VariableDefined.USE_MESSAGE) {
			// so far we have no transparent functions
			Errors.ErrorMessageNontransparent(ctx.start);
		}
		switch (ctx.FUNCTION().getText()) {
			case Constants.VPSENC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				Term key = visitTerm(ctx.argument.get(0), principal, block, expectVD);
				Term value = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				return new FunctionSenc(key, value);
			}
			case Constants.VPSDEC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				Term key = visitTerm(ctx.argument.get(0), principal, block, expectVD);
				Term value = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				// use canonical form in case value is not directly ENC function
				Term encodedValue = value.toCanonical();
				if (!(encodedValue instanceof FunctionSenc)) {
					Errors.ErrorDecodingNotEncoded(ctx.argument.get(1));
				}
				if (!((FunctionSenc)encodedValue).key.equals(key)) {
					Errors.ErrorWrongKey(ctx.argument.get(0));
				}
				return new FunctionSdec(key, value, ((FunctionSenc)encodedValue).value);
			}
			case Constants.VPHASH: {
				model.builtins.hashing = true;
				if (ctx.argument.size() < 1) {
					Errors.ErrorArgumentsMinCount(ctx.FUNCTION().getSymbol(), 1, ctx.argument.size());
				}
				// n-ary hash is syntactic sugar for unary hash of a tuple
				if (ctx.argument.size() == 1) {
					return new FunctionHash(visitTerm(ctx.argument.get(0), principal, block, expectVD));
				}
				ArrayList<Term> subterms = new ArrayList<>();
				for (TermContext termctx : ctx.argument) {
					subterms.add(visitTerm(termctx, principal, block, expectVD));
				}
				return new FunctionHash(new Tuple(subterms));
			}
			default: {
				Errors.DebugUnexpectedTokenType(ctx.FUNCTION().getText(), "visitFunctionCall");
				return null;
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
		} else if (ctx.confidentiality() != null) {
			visitConfidentiality(ctx.confidentiality());			
		}
	}

	public void visitExecutable(ExecutableContext ctx) {
		if (model.queries.executable == true) {
			Errors.WarningQueryExecutableDuplicite(ctx.start);
		} else {
			model.queries.executable = true;
		}
	}

	public void visitConfidentiality(ConfidentialityContext ctx) {
		if (ctx.principal == null) {
			Errors.debug("Not yet implemented: confidentiality without specific principal!");
		}
		Principal principal = model.findPrincipal(ctx.principal.getText());
		if (principal == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.principal);
		}
		Variable variable = visitVariable(ctx.variable(), principal, null, VariableDefined.USE_MESSAGE);
		for (Confidentiality query : model.queries.confidentiality) {
			if (query.principal == principal && query.variable.equals(variable)) {
				Errors.WarningQueryConfidentialityDuplicite(ctx.variable().start);
				return;
			}
		}
		model.queries.confidentiality.add(new Confidentiality(principal, variable));
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