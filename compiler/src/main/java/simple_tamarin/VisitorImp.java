package simple_tamarin;

import org.antlr.v4.runtime.Token;
import simple_tamarin.dataStructures.*;
import simple_tamarin.parser.*;

/**
 * This class implements all visitor methods of the Simple_tamarin compiler
 */
public class VisitorImp extends Simple_tamarinBaseVisitor<Integer> {
	private StModel model;

	// focused objects - objects that are currently being worked on
	private Principal curPrincipal;
	private StBlock curBlock;

	public VisitorImp(){
		model = new StModel();
	};
	
	@Override public Integer visitModel(Simple_tamarinParser.ModelContext ctx) { return visitChildren(ctx); }

	@Override public Integer visitSegment(Simple_tamarinParser.SegmentContext ctx) { return visitChildren(ctx); }

	@Override public Integer visitPrincipalBlock(Simple_tamarinParser.PrincipalBlockContext ctx) { 
		String principalName = ctx.principal.getText();
		
		if (!identifierNameValid(principalName)) {
			// TODO error message "name reserved..."
			return 1;
		}
		
		Principal principal = model.findPrincipal(principalName);
		if (principal == null) {
			// TODO info message "It is recommended to declare all principals..."
			principal = model.addPrincipal(principalName);
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
		Variable variable = model.findVariable(name);
		boolean pub = (ctx.modifier.getText().equals("public")) ? true : false;

		if (variable == null) {
			// TODO info message "new variable ... it is recommended to declare variables..."
			variable = model.addVariable(name, pub);
		} else if (variable.pub != pub) {
			// TODO error message "new variable ... declared as pub, but already exists as variable.pub ..."
			return 1;
		} // else everything is allready correct

		if (curPrincipal.variables.contains(variable)) {
			// TODO warning message "principal allready knows variable ..."
		} else {
			curPrincipal.variables.add(variable);
		}
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
			// TODO error message "receiver principal does not exist at this point ..."
			return 1;
		}

		for (Token message : ctx.message) {
			String msg = message.getText();
			Variable variable = model.findVariable(msg);
			
			if (variable == null) {
				// TODO error message "variable doesn't exist..."
				return 1;
			}

			if (!(sender.knows(msg) || variable.pub)) {
				// TODO error message "principal is trying to send a variable despite not knowing it..."
				return 1;
			}

			if (sender.blocks.isEmpty()) {
				// sender may send a public variable before doing anything else, in that case it needs an initial block
				sender.blocks.add(new StBlock(sender));
			}
			StBlock sendersLastBlock = sender.blocks.get(sender.blocks.size()-1);
			sendersLastBlock.commands.add(new Command(Command.out, variable));
			receiver.nextBlock.commands.add(new Command(Command.in, variable));

			if (!(receiver.knows(msg) || variable.pub)) {
				receiver.variables.add(variable);
			}
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