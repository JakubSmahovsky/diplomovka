// Generated from Simple_tamarin.g4 by ANTLR 4.8
package simple_tamarin.stParser;
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
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment(Simple_tamarinParser.SegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#principalBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrincipalBlock(Simple_tamarinParser.PrincipalBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(Simple_tamarinParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#knows}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnows(Simple_tamarinParser.KnowsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#generates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerates(Simple_tamarinParser.GeneratesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(Simple_tamarinParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#check}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheck(Simple_tamarinParser.CheckContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#messageBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageBlock(Simple_tamarinParser.MessageBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#queriesBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueriesBlock(Simple_tamarinParser.QueriesBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(Simple_tamarinParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(Simple_tamarinParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(Simple_tamarinParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#checkedCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckedCall(Simple_tamarinParser.CheckedCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(Simple_tamarinParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(Simple_tamarinParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#confidentiality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfidentiality(Simple_tamarinParser.ConfidentialityContext ctx);
	/**
	 * Visit a parse tree produced by {@link Simple_tamarinParser#executable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutable(Simple_tamarinParser.ExecutableContext ctx);
}