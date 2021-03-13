package simpleT;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.Token;

import simpleT.Constants.*;
import simpleT.dataStructures.*;
import simpleT.dataStructures.command.*;
import simpleT.dataStructures.query.Confidentiality;
import simpleT.dataStructures.query.ForwardSecrecy;
import simpleT.dataStructures.term.*;
import simpleT.errors.Errors;
import simpleT.errors.STException;
import simpleT.stParser.SimpleTParser.*;

/**
 * This class implements all visitor methods of the SimpleT compiler
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

		// TODO: check that each principal has at least one block
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
			if (model.findPublic(principalName) != null) {
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
			if (model.findPublic(principalName) != null) {
				Errors.ErrorPrincipalNameCollision(ctx.principal);
			}
			principal = model.addPrincipal(principalName);
		}

		STBlock curBlock = principal.nextBlock;
		principal.nextBlock();
		for (CommandContext command : ctx.command()) {
			visitCommand(command, principal, curBlock);
		}

		if (!curBlock.unaryEqualsPending.isEmpty()) {
			ArrayList<String> varnames = new ArrayList<>();
			for (Variable variable : curBlock.unaryEqualsPending) {
				varnames.add(variable.renderOutput());
			}
			Errors.InfoUnaryEquals(ctx.stop, varnames);
		}
	}

	public void visitCommand(CommandContext ctx, Principal principal, STBlock block) {
		if (ctx.distributed() != null) {
			visitDistributed(ctx.distributed(), principal, block);
			return;
		}
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

	public void visitDistributed(DistributedContext ctx, Principal principal, STBlock block) {
		Variable left = visitVariable(ctx.variable(), principal, block, VariableDefined.DISTRIBUTED_LEFT);
		Term right = visitTerm(ctx.term(), principal, block, VariableDefined.DISTRIBUTED_RIGHT);
		left.distributedAssign(right);
		principal.learnPublic(left);
	}

	public void visitKnows(KnowsContext ctx, Principal principal, STBlock block) {
		String modifier = ctx.modifier.getText();
		boolean pub = modifier.equals("public");

		VariableDefined expectVD = pub ? VariableDefined.KNOWS_PUBLIC : VariableDefined.KNOWS_PRIVATE;
		for (VariableContext vctx : ctx.variable()) {
			Variable variable = visitVariable(vctx, principal, block, expectVD);

			if (principal.getFirstBlock() != block && !variable.isConstructed()) {
				Errors.InfoKnowsInFirstBlock(vctx.start);
			}

			if (pub) {
				principal.learnPublic(variable);
			} else {
				principal.learnLongTermPrivate(variable);
				model.builtins.prefab_private_reveal = true;
			}
		}
	}

	public void visitGenerates(GeneratesContext ctx, Principal principal, STBlock block) {
		for (VariableContext vctx : ctx.variable()) {
			Variable variable = visitVariable(vctx, principal, block, VariableDefined.GENERATES);
			principal.learnEphemeralPrivate(variable);
			block.premiseFresh.add(new CommandFr(variable, block));
			block.state.add(variable);
		}
	}

	public void visitCheck(CheckContext ctx, Principal principal, STBlock block){
		visitCheckedCall(ctx.checkedCall(), principal, block, VariableDefined.CHECK);
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
			Term term = visitTerm(message, sender, null, VariableDefined.MESSAGE);
			Term receivedTerm = term.sentToReceived(model, receiver, message);

			// add all new variables to receiver's knowledge
			for (Variable variable : receivedTerm.extractKnowledge()) {
				// do not learn a variable again if you aleady know it (it is implicitly compared, because it's in the state)
				if (receiver.knowsAnyVariableByName(variable) == null) {
					receiver.learnEphemeralPrivate(variable);
				} else {
					receiver.nextBlock.unaryEqualsPending.add(variable);
				}
			}

			sender.getLastBlock().resultOutputs.add(new CommandOut(term, sender.getLastBlock()));
			receiver.nextBlock.premiseInputs.add(new CommandIn(receivedTerm, receiver.nextBlock));
			if (!receiver.nextBlock.state.contains(receivedTerm)) {
				receiver.nextBlock.state.add(receivedTerm);
			}
		}
	}

	public void visitAssignment(AssignmentContext ctx, Principal principal, STBlock block) {
		Term left = visitTerm(ctx.left, principal, block, VariableDefined.ASSIGNMENT_LEFT);
		Term right = visitTerm(ctx.right, principal, block, VariableDefined.ASSIGNMENT_RIGHT);

		if (!left.assign(right, false, block)) {
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
			if (expectVD == VariableDefined.ASSIGNMENT_LEFT) {
				Errors.ErrorLeftNontransparent(ctx.start);
			}
			if (expectVD == VariableDefined.MESSAGE) {
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

		// principal knows it as ephemeral private
		Variable result = principal.knowsEphemeralPrivate(name);
		if (result != null) {
			switch (expectVD) {
				case DISTRIBUTED_LEFT:
				case KNOWS_PUBLIC:
				case KNOWS_PRIVATE:
				case GENERATES:
					Errors.ErrorVariableAlreadyKnown(principal, ctx.start, false);
					return null;
				case DISTRIBUTED_RIGHT:
					Errors.ErrorVariableNotLongTerm(ctx.start);
					return null;
				default:
					return result;
			}
		}
		
		// principal knows it as long-term private
		result = principal.knowsLongTermPrivate(name);
		if (result != null) {
			switch (expectVD) {
				case DISTRIBUTED_LEFT:
				case KNOWS_PUBLIC:
				case KNOWS_PRIVATE:
				case GENERATES:
					Errors.ErrorVariableAlreadyKnown(principal, ctx.start, false);
					return null;
				default:
					return result;
			}
		}

		// principal knows it as public 
		result = principal.knowsPublic(name);
		if (result != null) {
			switch (expectVD) {
				case KNOWS_PUBLIC:
				case KNOWS_PRIVATE:
				case GENERATES:
					Errors.ErrorVariableAlreadyKnown(principal, ctx.start, true);
					return null;
				case DISTRIBUTED_RIGHT:
					if (result.isConstructed()) {
						Errors.ErrorVariableNotLongTerm(ctx.start);
						return null;
					}
				default:
					return result;
			}
		}

		// it exists for some other principal (and we care)
		if (expectVD == VariableDefined.DISTRIBUTED_LEFT ||
				expectVD == VariableDefined.KNOWS_PUBLIC ||
				expectVD == VariableDefined.KNOWS_PRIVATE ||
				expectVD == VariableDefined.GENERATES) {
			// it exists as public
			result = model.findPublic(name);
			if (result != null) {
				switch (expectVD) {
					case DISTRIBUTED_LEFT:
						Errors.ErrorVariableNameCollisionPublic(ctx.start);
						return null;
					case KNOWS_PUBLIC:
						return result;
					case KNOWS_PRIVATE:
					case GENERATES:
						Errors.WarningShadowedPublic(ctx.start);
					default:
				}
			}
			
			// some other principal knows it as long-term private, there can only be one long-term private with this name
			result = null;
			for (Principal p : model.getPrincipals()){
				if (p != principal) {
					result = p.knowsLongTermPrivate(name);
					if (result != null) {
						break;
					}
				}
			}
			if (result != null) {
				switch (expectVD) {
					case DISTRIBUTED_LEFT:
					case KNOWS_PUBLIC:
						Errors.WarningShadowedLongTermPrivate(ctx.start);
						break;
					case KNOWS_PRIVATE:
						return result;
					case GENERATES:
						Errors.WarningShadowedLongTermPrivate(ctx.start);
						break;
					default:
				}
			}
		}

		// it does not exist (or at least principal doesn't know it and we don't care if someone else does)
		if (!identifierNameValid(name)) {
			Errors.ErrorReservedName(ctx.start);
		}
		switch (expectVD) {
			case DISTRIBUTED_LEFT:
				return Variable.placeholder(model, name);
			case KNOWS_PUBLIC:
			case KNOWS_PRIVATE:
			case GENERATES:
				return new Variable(model, name);
			case ASSIGNMENT_LEFT:
				return Variable.placeholder(model, name);
			default:
				Errors.ErrorVariableUnknown(principal, ctx.start);
				return null;
		}
	}

	public void visitCheckedCall(CheckedCallContext ctx, Principal principal, STBlock block, VariableDefined expectVD) {
		switch (ctx.CHECKED().getText()) {
			case Constants.VPEQUALS: {
				if (ctx.argument.size() == 1) {
					if (ctx.argument.get(0).variable() == null) {
						Errors.ErrorUnaryEqualsNotVariable(ctx.argument.get(0).start, ctx.argument.get(0).getText());
					}
					Variable variable = visitVariable(ctx.argument.get(0).variable(), principal, block, expectVD);
					boolean removed = false;
					for (int i = 0; i < block.unaryEqualsPending.size(); i++) {
						// it is important to compare using '==' here, we want the same object, not just equal terms
						if (variable == block.unaryEqualsPending.get(i)) {
							block.unaryEqualsPending.remove(i);
							removed = true;
							break;
						}
					}
					if (!removed) {
						Errors.ErrorUnaryEqualsNotPending(ctx.argument.get(0).start, ctx.argument.get(0).getText());
					}
					return;
				}
				if (ctx.argument.size() == 2) {	
					model.builtins.restriction_eq = true;
					Term term1 = visitTerm(ctx.argument.get(0), principal, block, expectVD);
					Term term2 = visitTerm(ctx.argument.get(1), principal, block, expectVD);
					if (!(term1.equals(term2))) {
						Errors.ErrorEqualsNeverTrue(ctx.start);
					}
					block.actions.add(Fact.equality(term1, term2));
					return;
				}
				Errors.ErrorArgumentsCount(ctx.CHECKED().getSymbol(), 2, ctx.argument.size());
			}
			case Constants.VPSIGNVERIF: {
				model.builtins.restriction_eq = true;
				if (ctx.argument.size() != 3) {
					Errors.ErrorArgumentsCount(ctx.CHECKED().getSymbol(), 3, ctx.argument.size());
				}
				Term key = visitTerm(ctx.argument.get(0), principal, block, expectVD);
				Term message = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				Term signature = visitTerm(ctx.argument.get(2), principal, block, expectVD);
				signature.getNormalForm().verifySignature(key, message, ctx.argument.get(0), ctx.argument.get(1), ctx.argument.get(2));
				FunctionVerify verify = new FunctionVerify(key, message, signature);
				block.actions.add(Fact.equality(ValueTrue.instance(), verify));
				return;
			}
			default: {
				Errors.DebugUnexpectedTokenType(ctx.CHECKED().getText(), "visitCheckedCall");
			}
		}
	}
	

	public Term visitFunctionCall(FunctionCallContext ctx, Principal principal, STBlock block, VariableDefined expectVD) {
		if (expectVD == VariableDefined.ASSIGNMENT_LEFT) {
			// so far we have no transparent functions
			Errors.ErrorLeftNontransparent(ctx.start);
		}
		if (expectVD == VariableDefined.MESSAGE) {
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
				Term decoded = value.getNormalForm().decode(key, ctx.argument.get(0), ctx.argument.get(1));
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
			case Constants.VPPK: {
				model.builtins.signing = true;
				if (ctx.argument.size() != 1) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 1, ctx.argument.size());
				}
				return new FunctionPk(visitTerm(ctx.argument.get(0), principal, block, expectVD));
			}
			case Constants.VPSIGN: {
				model.builtins.signing = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
				}
				Term key = visitTerm(ctx.argument.get(0), principal, block, expectVD);
				Term message = visitTerm(ctx.argument.get(1), principal, block, expectVD);
				return new FunctionSign(key, message);
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
		} else if (ctx.forwardSecrecy() != null) {
			visitForwardSecrecy(ctx.forwardSecrecy());			
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
		Principal principal = model.findPrincipal(ctx.principal.getText());
		if (principal == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.principal);
		}
		Variable variable = visitVariable(ctx.variable(), principal, null, VariableDefined.QUERY);
		for (Confidentiality query : model.queries.confidentiality) {
			if (query.principal == principal && query.variable.equals(variable)) {
				Errors.WarningQueryConfidentialityDuplicite(ctx.variable().start);
				return;
			}
		}
		model.queries.confidentiality.add(new Confidentiality(principal, variable));
	}

	public void visitForwardSecrecy(ForwardSecrecyContext ctx) {
		Principal principal = model.findPrincipal(ctx.principal.getText());
		if (principal == null) {
			Errors.ErrorPrincipalDoesNotExist(ctx.principal);
		}
		Variable variable = visitVariable(ctx.variable(), principal, null, VariableDefined.QUERY);
		for (ForwardSecrecy query : model.queries.forwardSecrecy) {
			if (query.principal == principal && query.variable.equals(variable)) {
				Errors.WarningQueryConfidentialityDuplicite(ctx.variable().start);
				return;
			}
		}
		model.queries.forwardSecrecy.add(new ForwardSecrecy(principal, variable));
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