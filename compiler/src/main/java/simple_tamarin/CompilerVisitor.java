package simple_tamarin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.antlr.v4.runtime.Token;

import simple_tamarin.Constants.*;
import simple_tamarin.dataStructures.*;
import simple_tamarin.dataStructures.command.*;
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
	private STModel model;

	public CompilerVisitor(FileWriter writer, boolean quitOnWarning, boolean showInfo) {
		this.writer = writer;
		Errors.quitOnWarning = quitOnWarning;
		Errors.showInfo = showInfo;
	}

	public STModel visitModel(ModelContext ctx) {
		this.model = new STModel();
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

		STBlock curBlock = principal.nextBlock;
		principal.nextBlock();
		for (CommandContext command : ctx.command()) {
			visitCommand(command, principal, curBlock);
		}
	}

	public void visitCommand(CommandContext ctx, Principal principal, STBlock block) {
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

	public void visitKnows(KnowsContext ctx, Principal principal, STBlock block) {
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
				principal.initState.add(variable);
			} else {
				// possibly unify the private variable with one known by another principal
				for (Principal anyPrincipal : model.getPrincipals()) {
					Variable existing = anyPrincipal.knows(variable);
					if (existing != null) {
						if (existing.isLongTerm()) {
							variable = existing;
							break;
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

	public void visitGenerates(GeneratesContext ctx, Principal principal, STBlock block) {
		for (VariableContext vctx : ctx.variable()) {
			Variable variable = visitVariable(vctx, principal, block, VariableDefined.PRIVATE_GENERATES);
			principal.learn(variable);
			block.premiseFresh.add(new CommandFr(variable, block));
			block.state.add(variable);
		}
	}

	public void visitCheck(CheckContext ctx, Principal principal, STBlock block){
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
			if (!term.isPublicInModel(model)) {
				// add all new variables to receiver's knowledge
				for (Variable variable : term.extractKnowledge()) {
					receiver.learn(variable.clone());
				}
			}

			if (sender.getBlocks().isEmpty()) {
				// sender may send a public variable before doing anything else, in that case it needs an initial block
				sender.nextBlock();
			}

			sender.getLastBlock().resultOutputs.add(new CommandOut(term, sender.getLastBlock()));
			receiver.nextBlock.premiseInputs.add(new CommandIn(term, receiver.nextBlock));
			if (!receiver.nextBlock.state.contains(term)) {
				receiver.nextBlock.state.add(term);
			}
		}
	}

	public void visitAssignment(AssignmentContext ctx, Principal principal, STBlock block) {
		Term left = visitTerm(ctx.left, principal, block, VariableDefined.PRIVATE_LEFT);
		Term right = visitTerm(ctx.right, principal, block, VariableDefined.USE_RIGHT);

		if (!left.assign(right, block, principal)) {
			Errors.ErrorCannotUnify(ctx.left, ctx.right);
		}
	}

	/**
	 * Also verifies wheather term is transparent if needed (because of expectVD)
	 */
	public Term visitTerm(TermContext ctx, Principal principal, STBlock block, VariableDefined expectVD) {
		// bracketed term
		if (ctx.term().size() == 1) {
			return visitTerm(ctx.term(0), principal, block, expectVD);
		}

		if (ctx.constant() != null) {
			return visitConstant(ctx.constant());
		}
		if (ctx.variable() != null) {
			return visitVariable(ctx.variable(), principal, block, expectVD);
		}
		if (ctx.functionCall() != null) {
			return visitFunctionCall(ctx.functionCall(), principal, block, expectVD);
		}
		if (ctx.tuple() != null) {
			return visitTuple(ctx.tuple(), principal, block, expectVD);
		}

		if (ctx.POWER_OP() != null && ctx.term().size() == 2) {
			// exponentiation is not transparent
			if (expectVD == VariableDefined.PRIVATE_LEFT) {
				Errors.ErrorLeftNontransparent(ctx.start);
			}
			if (expectVD == VariableDefined.USE_MESSAGE) {
				Errors.ErrorMessageNontransparent(ctx.start);
			}
			model.builtins.diffie_hellman = true;
			ArrayList<Term> exponent = new ArrayList<>();
			exponent.add(visitTerm(ctx.term(1), principal, block, expectVD));
			Term base = visitExponentiationBase(ctx.term(0), exponent, principal, block, expectVD);
			return new Exponentiation(base, exponent);
		}

		Errors.DebugUnexpectedTokenType(ctx.getText(), "visitTerm()");
		return null;
	}

	/**
	 * Since exponentiation is left-associative in Tamarin (and ST) we encounter exponents from the right.
	 * This function gets a list of exponents to the right.
	 * If ctx is an exponentiation (even if it's in brackets),
	 * it adds another exponent to the list and recursively calls to the left.
	 * Otherwise it returns the base.
	 */
	public Term visitExponentiationBase(TermContext ctx, ArrayList<Term> exponent, Principal principal, STBlock block, VariableDefined expectVD) {
		if (ctx.POWER_OP() != null && ctx.term().size() == 2) {
			exponent.add(visitTerm(ctx.term(1), principal, block, expectVD));
			return visitExponentiationBase(ctx.term(0), exponent, principal, block, expectVD);
		}
		//bracketed term
		if (ctx.term().size() == 1) {
			return visitExponentiationBase(ctx.term(0), exponent, principal, block, expectVD);
		}

		return visitTerm(ctx, principal, block, expectVD);
	}

	public Constant visitConstant(ConstantContext ctx) {
		return new Constant(ctx.word.getText());
	}

	/**
	 * Finds a variable if it should have existed or creates a new one otherwise.
	 */
	public Variable visitVariable(VariableContext ctx, Principal principal, STBlock block, VariableDefined expectVD) {
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
					Errors.InfoComparedPublicVariable(ctx.start);
					// and go define it as private placeholder
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
				result = new Variable(name, VariableSort.PUBLIC);
				model.pubVariables.add(result);
				return result;
			case PRIVATE_KNOWS:
				return new Variable(name);
			case PRIVATE_LEFT:
				return Variable.placeholder(name);
			default:
				return new Variable(name, block);
		}
	}

	public void visitCheckedCall(CheckedCallContext ctx, Principal principal, STBlock block, VariableDefined expectVD) {
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
	

	public Term visitFunctionCall(FunctionCallContext ctx, Principal principal, STBlock block, VariableDefined expectVD) {
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
				Term decoded = value.toCanonical().decode(key, ctx.argument.get(0), ctx.argument.get(1));
				return new FunctionSdec(key, value, decoded);
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

	public Term visitTuple(TupleContext ctx, Principal principal, STBlock block, VariableDefined expectVD) {
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