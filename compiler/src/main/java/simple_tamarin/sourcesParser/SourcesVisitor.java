// Generated from Sources.g4 by ANTLR 4.8
package simple_tamarin.sourcesParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SourcesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SourcesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SourcesParser#sources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSources(SourcesParser.SourcesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(SourcesParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(SourcesParser.SourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(SourcesParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFact(SourcesParser.FactContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(SourcesParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(SourcesParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(SourcesParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(SourcesParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#subst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubst(SourcesParser.SubstContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(SourcesParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#lemma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLemma(SourcesParser.LemmaContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#lemmaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLemmaStmt(SourcesParser.LemmaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#jsonchars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonchars(SourcesParser.JsoncharsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#jsonword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonword(SourcesParser.JsonwordContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJson(SourcesParser.JsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#jsonKeyValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonKeyValue(SourcesParser.JsonKeyValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SourcesParser#jsonValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonValue(SourcesParser.JsonValueContext ctx);
}