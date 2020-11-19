package simple_tamarin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import simple_tamarin.Constants.CommandType;
import simple_tamarin.Constants.VariableSort;
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

	public VisitorImp(FileWriter writer, boolean quitOnWarning, boolean showInfo) {
		this.writer = writer;
		this.quitOnWarning = quitOnWarning;
		Errors.showInfo = showInfo;
	}

	@Override public Integer visitModel(ModelContext ctx) {
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
		String name = ctx.variable().IDENTIFIER().getText();
		boolean pub = (ctx.modifier.getText().equals("public")) ? true : false;
		if (curPrincipal.blocks.get(0) != curBlock) {
			Errors.InfoKnowsInFirstBlock(ctx.getStart());
		}

		if (pub) {
			if (curPrincipal.findVariable(name) != null) {
				Errors.ErrorVariableCollisionPrivate(curPrincipal, ctx.variable().getStart());
				return 1;
			}

			Variable variable = model.findVariable(name);
			if (variable == null) {
				Errors.InfoDeclareLongTermPubVariable(ctx.variable().start);
				// TODO allow declaring variables
				variable = new Variable(name, VariableSort.PUBLIC);
				model.pubVariables.add(variable);
			}

			curPrincipal.initState.add(variable);
		} else {
			if (model.findVariable(name) != null) {
				Errors.WarningVariableShadowed(ctx.variable().start);
				if (quitOnWarning) {
					return 1;
				}
			}
			if (curPrincipal.findVariable(name) != null) {
				Errors.WarningVariableKnownPrivate(curPrincipal, ctx.variable().start);
				if (quitOnWarning) {
					return 1;
				}
			} else {
				Variable variable = new Variable(name, VariableSort.FRESH);
				for (Principal principal : model.principals) {
					for (Variable existing : principal.variables) {
						if (existing.name.equals(name)) {
							if (existing.cratedBy == null) {
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

				curPrincipal.variables.add(variable);
				curPrincipal.initState.add(variable);
			}
		}
		return 0;
	}

	@Override public Integer visitGenerates(GeneratesContext ctx) {
		String name = ctx.variable().getText();
		if (!identifierNameValid(name)) {
			Errors.ErrorReservedName(ctx.variable().start);
			return 1;
		}
		
		if (model.findVariable(name) != null) {
			Errors.WarningVariableShadowed(ctx.variable().start);
			if (quitOnWarning) {
				return 1;
			};
		}
		if (curPrincipal.findVariable(name) != null) {
			Errors.ErrorVariableCollisionPrivate(curPrincipal, ctx.variable().start);
			return 1;
		}

		Variable variable = new Variable(name, curPrincipal, VariableSort.FRESH);
		curPrincipal.variables.add(variable);
		curBlock.premise.add(new Command(CommandType.FRESH, variable));
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

		for (VariableContext message : ctx.variable()) {
			String msg = message.getText();

			Variable variable = model.findVariable(msg);
			if (variable == null) {
				variable = sender.findVariable(msg);
				if (variable == null) {
					Errors.ErrorVariableUnknown(curPrincipal, message.start);
					return 1;
				}

				if (receiver.findVariable(msg) == null) {
					// only add variable to receiver knowledge if it's not public nor allready known
					receiver.variables.add(variable);
				}
			}

			if (sender.blocks.isEmpty()) {
				// sender may send a public variable before doing anything else, in that case it needs an initial block
				sender.nextBlock();
			}

			StBlock sendersLastBlock = sender.blocks.get(sender.blocks.size()-1);
			sendersLastBlock.result.add(new Command(CommandType.OUT, variable));
			receiver.nextBlock.premise.add(new Command(CommandType.IN, variable));
		}

		return 0;
	}

	@Override public Integer visitAssignment(AssignmentContext ctx) {
		String name = ctx.variable().IDENTIFIER().getText();
		if (curPrincipal.findVariable(name) != null) {
			Errors.ErrorVariableCollisionPrivate(curPrincipal, ctx.variable().start);
			return 1;
		}
		if (model.findVariable(name) != null) {
			Errors.WarningVariableShadowed(ctx.variable().start);
			if (quitOnWarning) {
				return 1;
			}
		}
		if (visitTerm(ctx.term()) != 0) {
			return 1;
		}
		Variable alias = new Variable(name, curTerm, curPrincipal, VariableSort.NOSORT);
		Variable variable = alias;
		if (curTerm instanceof FunctionSdec) {
			variable = new Variable(name, ((FunctionSdec)curTerm).decodedValue, curPrincipal, VariableSort.NOSORT);
			curBlock.premise.add(new Command(CommandType.SDEC, variable));
		}
		curBlock.aliases.add(alias);
		curPrincipal.variables.add(variable);
		return 0;
	}

	@Override public Integer visitTerm(TermContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * Populate curTerm by existing variable accessible by currPrincipal with given name.
	 * @return 1 if no variable with given name is accessible by currPrincipal
	 */
	@Override public Integer visitVariable(VariableContext ctx) {
		String name = ctx.IDENTIFIER().getText();
		Variable variable = curPrincipal.findVariable(name);
		if (variable == null) {
			variable = model.findVariable(name);
			if (variable == null) {
				Errors.ErrorVariableUnknown(curPrincipal, ctx.start);
				return 1;
			}
			// if we're using a public variable, principal should know it from initial state
			if (!curPrincipal.initState.contains(variable)) {
				curPrincipal.initState.add(variable);
			}
		}
		curTerm = variable;
		return 0;
	}

	@Override public Integer visitFunctionCall(FunctionCallContext ctx) {
		switch (ctx.FUNCTION().getText()) {
			case Constants.VPSENC: {
				model.builtins.symmetric_encryption = true;
				if (ctx.argument.size() != 2) {
					Errors.ErrorArgumentsCount(ctx.FUNCTION().getSymbol(), 2, ctx.argument.size());
					return 1;
				}
				if (visitTerm(ctx.argument.get(0)) != 0 ) {
					return 1;
				}
				Term key = curTerm;
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
				if (visitTerm(ctx.argument.get(0)) != 0 ) {
					return 1;
				}
				Term key = curTerm;
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