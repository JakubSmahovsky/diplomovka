package simple_tamarin;

import simple_tamarin.dataStructures.*;
import simple_tamarin.parser.*;

/**
 * This class implements all visitor methods of the Simple_tamarin compiler
 */
public class VisitorImp extends Simple_tamarinBaseVisitor<Integer> {
	private ProtocolModel model;

	public VisitorImp(){
		model = new ProtocolModel();
	};
	
	@Override public Integer visitModel(Simple_tamarinParser.ModelContext ctx) { return visitChildren(ctx); }

	@Override public Integer visitSegment(Simple_tamarinParser.SegmentContext ctx) { return visitChildren(ctx); }

	@Override public Integer visitPrincipalBlock(Simple_tamarinParser.PrincipalBlockContext ctx) { 
		String principalName = ctx.principal.getText();
		
		if (!identifierNameValid(principalName)) {
			// TODO error message "name reserved..."
			return 1; // TODO error recovery
		}
		
		Principal principal = model.findPrincipal(principalName);
		if (principal == null) {
			// TODO info message "It is recommended to declare all principals..."
			principal = model.addPrincipal(principalName);
		}

		for (Simple_tamarinParser.CommandContext command : ctx.command) {
			
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