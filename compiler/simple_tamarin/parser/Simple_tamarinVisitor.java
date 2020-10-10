// Generated from Simple_tamarin.g4 by ANTLR 4.8
package simple_tamarin.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Simple_tamarinParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Simple_tamarinVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(Simple_tamarinParser.ModelContext ctx);
}