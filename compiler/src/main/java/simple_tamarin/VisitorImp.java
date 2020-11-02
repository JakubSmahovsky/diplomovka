package simple_tamarin;

import java.io.FileWriter;
import java.io.IOException;

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
	private FileWriter writer;
	private StModel model;

	// focused objects - objects that are currently being worked on
	private Principal curPrincipal;
	private StBlock curBlock;
	private Term curTerm;

	public VisitorImp(FileWriter writer) {
		this.writer = writer;
	}

	@Override
	public Integer visitModel(ModelContext ctx) {
		this.model = new StModel();
		for (SegmentContext segment : ctx.segment()) {
			if (visitSegment(segment) != 0) {
				return 1;
			}
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
			// TODO error message "name reserved..."
			return 1;
		}
		
		Principal principal = model.findPrincipal(principalName);
		if (principal == null) {
			// TODO info message "It is recommended to declare all principals..."
			if (model.findVariable(principalName) != null) {
				// TODO error message "Name of principal is reserved for a variable"
				return 1;
			}
			principal = model.addPrincipal(principalName);
		}

		// init focused objects
		curPrincipal = principal;
		curBlock = curPrincipal.nextBlock;
		curPrincipal.nextBlock = new StBlock(curPrincipal);
		principal.blocks.add(curBlock);

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
		// TODO if (curPrincipal.blocks.get(0) != currBlock) info message: "recommend knows the first block"

		if (pub) {
			if (curPrincipal.findVariable(name) != null) {
				// TODO error message: "principal allready knows variable with that name as private"
				return 1;
			}

			Variable variable = model.findVariable(name);
			if (variable == null) {
				// TODO info message: "recommend declaring variable"
				variable = new Variable(name, VariableSort.PUBLIC);
				model.pubVariables.add(variable);
			}

			curPrincipal.initState.add(variable);
		} else {
			// TODO info message if there is a public variable with the same name
			if (curPrincipal.findVariable(name) != null) {
				// TODO warning message: principal allready knows it
			} else {
				Variable variable = new Variable(name, VariableSort.FRESH);
				for (Principal principal : model.principals) {
					for (Variable existing : principal.variables) {
						if (existing.name.equals(name)) {
							if (existing.cratedBy == null) {
								variable = existing;
							} else {
								/* TODO warning messsage: "ephemeral variable name allready exists for some principal,
									 curPrincipal will know a different, long-term variable name"*/
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
		String name = ctx.variable().IDENTIFIER().getText();
		if (!identifierNameValid(name)) {
			// TODO error message: identifier name is reserverd
		}
		
		if (model.findVariable(name) != null) {
			// TODO info message: shadowed variable
		}
		if (curPrincipal.findVariable(name) != null) {
			// TODO error message: private variable ... allready exists
			return 1;
		}

		Variable variable = new Variable(name, curPrincipal, VariableSort.FRESH);
		curPrincipal.variables.add(variable);
		curBlock.premise.add(new Command(CommandType.FRESH, variable));
		return 0;
	}

	@Override public Integer visitMessageBlock(MessageBlockContext ctx) {
		Principal sender = model.findPrincipal(ctx.sender.getText());
		if (sender == null) {
			// TODO error message "sender principal does not exist at this point ..."
			return 1;
		}
		Principal receiver = model.findPrincipal(ctx.receiver.getText());
		if (receiver == null) {
			System.out.println("error: Receiver \"" + ctx.receiver.getText() + "\" does not exist at this point!"); // TODO
			return 1;
		}

		for (VariableContext message : ctx.variable()) {
			String msg = message.getText();

			Variable variable = model.findVariable(msg);
			if (variable == null) {
				variable = sender.findVariable(msg);
				if (variable == null) {
					System.out.println("error: Principal \"" + sender.name + "\" is trying to send variable \"" + msg + "\" despite not knowing it."); // TODO 
					return 1;
				}

				if (receiver.findVariable(msg) == null) {
					// only add variable to receiver knowledge if it's not public nor allready known
					receiver.variables.add(variable);
				}
			}

			if (sender.blocks.isEmpty()) {
				// sender may send a public variable before doing anything else, in that case it needs an initial block
				sender.blocks.add(new StBlock(sender));
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
			// TODO: error message: "principal allready knows a variable called name"
			return 1;
		}
		if (model.findVariable(name) != null) {
			// TODO: info message: "shadowed variable"
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
				// TODO error message: "principal curPrincipal doesn't know variable called name"
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
					// TODO: error "wrong number of arguments"
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
					// TODO: error "value is not symmetrically encoded"
					return 1;
				}
				if (!((FunctionSenc)realValue).key.equals(key)) {
					// TODO error "wrong key"
					return 1;
				}
				realValue = ((FunctionSenc)realValue).value;
				curTerm = new FunctionSdec(key, curTerm, realValue);
				return 0;
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
			// TODO warning message: duplicite executable query declaration
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