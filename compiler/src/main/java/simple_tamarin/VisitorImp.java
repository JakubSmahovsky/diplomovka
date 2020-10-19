package simple_tamarin;

import java.io.FileWriter;
import java.io.IOException;

import org.antlr.v4.runtime.Token;

import simple_tamarin.Constants.CommandType;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.*;
import simple_tamarin.parser.*;
import simple_tamarin.parser.Simple_tamarinParser.QueryContext;

/**
 * This class implements all visitor methods of the Simple_tamarin compiler
 */
public class VisitorImp extends Simple_tamarinBaseVisitor<Integer> {
	private FileWriter writer;
	private StModel model;

	// focused objects - objects that are currently being worked on
	private Principal curPrincipal;
	private StBlock curBlock;

	public VisitorImp(FileWriter writer) {
		this.writer = writer;
		this.model = new StModel();
	};

	@Override
	public Integer visitModel(Simple_tamarinParser.ModelContext ctx) {
		for (Simple_tamarinParser.SegmentContext segment : ctx.segment()) {
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

	@Override public Integer visitSegment(Simple_tamarinParser.SegmentContext ctx) { 
		if (visitChildren(ctx) == null) {
			System.out.println(ctx.getText());
		}
		/*
		if (visitChildren(ctx) != 0) {
			return 1;
		}
		*/
		return 0;
	}

	@Override public Integer visitPrincipalBlock(Simple_tamarinParser.PrincipalBlockContext ctx) { 
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
			model.pubVariables.add(new Variable(principalName, VariableSort.PUBLIC));
		}

		// init focused objects
		curPrincipal = principal;
		curBlock = curPrincipal.nextBlock;
		curPrincipal.nextBlock = new StBlock(curPrincipal);
		principal.blocks.add(curBlock);

		for (Simple_tamarinParser.CommandContext command : ctx.command()) {
			if (visitCommand(command) != 0)
				return 1;
		}

		return 0;
	}

	@Override public Integer visitCommand(Simple_tamarinParser.CommandContext ctx) {
		return visitChildren(ctx);
	}

	@Override public Integer visitKnows(Simple_tamarinParser.KnowsContext ctx) {
		String name = ctx.IDENTIFIER().getText();
		boolean pub = (ctx.modifier.getText().equals("public")) ? true : false;
		// TODO if (!curPrincipal.blocks.isEmpty) info message: "recommend knows the first block"

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
						if (existing.name.equals(name) && existing.cratedBy == null) {
							variable = existing;
						}
					}
				}

				curPrincipal.variables.add(variable);
				curPrincipal.initState.add(variable);
			}
		}
		return 0;
	}

	@Override public Integer visitGenerates(Simple_tamarinParser.GeneratesContext ctx) {
		String name = ctx.IDENTIFIER().getText();
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

	@Override public Integer visitMessageBlock(Simple_tamarinParser.MessageBlockContext ctx) {
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

		for (Token message : ctx.message) {
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

	@Override public Integer visitQueriesBlock(Simple_tamarinParser.QueriesBlockContext ctx) {
		for (QueryContext query : ctx.query()) {
			if (visitQuery(query) != 0)
			return 1;
		}
		return 0;
	}

	@Override public Integer visitQuery(Simple_tamarinParser.QueryContext ctx) {
		if (visitChildren(ctx) != 0) {
			return 1;
		}
		return 0;
	}

	@Override public Integer visitExecutable(Simple_tamarinParser.ExecutableContext ctx) {
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