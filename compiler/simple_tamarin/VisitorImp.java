package simple_tamarin;

import simple_tamarin.parser.*;

/**
 * This class implements all visitor methods of the Simple_tamarin compiler
 */
public class VisitorImp<T> extends Simple_tamarinBaseVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitModel(Simple_tamarinParser.ModelContext ctx) { return visitChildren(ctx); }
}