// Generated from C:/Users/moham/OneDrive/Desktop/cute-tiny-database-engine/src/main/java/SQLParser/SQLGrammar.g4 by ANTLR 4.13.1
package SQLParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SQLGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SQLGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#sqlRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlRule(SQLGrammarParser.SqlRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTable(SQLGrammarParser.CreateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#createIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateIndex(SQLGrammarParser.CreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#insertIntoTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertIntoTable(SQLGrammarParser.InsertIntoTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#updateTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateTable(SQLGrammarParser.UpdateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#deleteFromTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteFromTable(SQLGrammarParser.DeleteFromTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#selectFromTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFromTable(SQLGrammarParser.SelectFromTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#primaryColumnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryColumnDefinition(SQLGrammarParser.PrimaryColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(SQLGrammarParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(SQLGrammarParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#columnValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnValue(SQLGrammarParser.ColumnValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataType(SQLGrammarParser.DataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(SQLGrammarParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#starrOperators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStarrOperators(SQLGrammarParser.StarrOperatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(SQLGrammarParser.TableNameContext ctx);
}