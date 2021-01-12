// Generated from Logging.g4 by ANTLR 4.8
package simple_tamarin.loggingParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LoggingParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LoggingVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LoggingParser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(LoggingParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#solved}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSolved(LoggingParser.SolvedContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#by}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBy(LoggingParser.ByContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(LoggingParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFact(LoggingParser.FactContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(LoggingParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(LoggingParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(LoggingParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LoggingParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(LoggingParser.VariableContext ctx);
}