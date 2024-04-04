// Generated from C:/Users/moham/OneDrive/Desktop/cute-tiny-database-engine/src/main/java/SQLParser/SQLGrammar.g4 by ANTLR 4.13.1
package SQLParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLGrammarParser}.
 */
public interface SQLGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#sqlRule}.
	 * @param ctx the parse tree
	 */
	void enterSqlRule(SQLGrammarParser.SqlRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#sqlRule}.
	 * @param ctx the parse tree
	 */
	void exitSqlRule(SQLGrammarParser.SqlRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(SQLGrammarParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(SQLGrammarParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(SQLGrammarParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(SQLGrammarParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#insertIntoTable}.
	 * @param ctx the parse tree
	 */
	void enterInsertIntoTable(SQLGrammarParser.InsertIntoTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#insertIntoTable}.
	 * @param ctx the parse tree
	 */
	void exitInsertIntoTable(SQLGrammarParser.InsertIntoTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#updateTable}.
	 * @param ctx the parse tree
	 */
	void enterUpdateTable(SQLGrammarParser.UpdateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#updateTable}.
	 * @param ctx the parse tree
	 */
	void exitUpdateTable(SQLGrammarParser.UpdateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#deleteFromTable}.
	 * @param ctx the parse tree
	 */
	void enterDeleteFromTable(SQLGrammarParser.DeleteFromTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#deleteFromTable}.
	 * @param ctx the parse tree
	 */
	void exitDeleteFromTable(SQLGrammarParser.DeleteFromTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#selectFromTable}.
	 * @param ctx the parse tree
	 */
	void enterSelectFromTable(SQLGrammarParser.SelectFromTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#selectFromTable}.
	 * @param ctx the parse tree
	 */
	void exitSelectFromTable(SQLGrammarParser.SelectFromTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#primaryColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryColumnDefinition(SQLGrammarParser.PrimaryColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#primaryColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryColumnDefinition(SQLGrammarParser.PrimaryColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(SQLGrammarParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(SQLGrammarParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(SQLGrammarParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(SQLGrammarParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#columnValue}.
	 * @param ctx the parse tree
	 */
	void enterColumnValue(SQLGrammarParser.ColumnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#columnValue}.
	 * @param ctx the parse tree
	 */
	void exitColumnValue(SQLGrammarParser.ColumnValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(SQLGrammarParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(SQLGrammarParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(SQLGrammarParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(SQLGrammarParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#starrOperators}.
	 * @param ctx the parse tree
	 */
	void enterStarrOperators(SQLGrammarParser.StarrOperatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#starrOperators}.
	 * @param ctx the parse tree
	 */
	void exitStarrOperators(SQLGrammarParser.StarrOperatorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(SQLGrammarParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(SQLGrammarParser.TableNameContext ctx);
}