// Generated from C:/Users/moham/OneDrive/Desktop/cute-tiny-database-engine/src/main/java/gen/CuteSQLGrammar.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CuteSQLGrammarParser}.
 */
public interface CuteSQLGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#cuteSQL}.
	 * @param ctx the parse tree
	 */
	void enterCuteSQL(CuteSQLGrammarParser.CuteSQLContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#cuteSQL}.
	 * @param ctx the parse tree
	 */
	void exitCuteSQL(CuteSQLGrammarParser.CuteSQLContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(CuteSQLGrammarParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(CuteSQLGrammarParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(CuteSQLGrammarParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(CuteSQLGrammarParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#insertIntoTable}.
	 * @param ctx the parse tree
	 */
	void enterInsertIntoTable(CuteSQLGrammarParser.InsertIntoTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#insertIntoTable}.
	 * @param ctx the parse tree
	 */
	void exitInsertIntoTable(CuteSQLGrammarParser.InsertIntoTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#updateTable}.
	 * @param ctx the parse tree
	 */
	void enterUpdateTable(CuteSQLGrammarParser.UpdateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#updateTable}.
	 * @param ctx the parse tree
	 */
	void exitUpdateTable(CuteSQLGrammarParser.UpdateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#deleteFromTable}.
	 * @param ctx the parse tree
	 */
	void enterDeleteFromTable(CuteSQLGrammarParser.DeleteFromTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#deleteFromTable}.
	 * @param ctx the parse tree
	 */
	void exitDeleteFromTable(CuteSQLGrammarParser.DeleteFromTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#selectFromTable}.
	 * @param ctx the parse tree
	 */
	void enterSelectFromTable(CuteSQLGrammarParser.SelectFromTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#selectFromTable}.
	 * @param ctx the parse tree
	 */
	void exitSelectFromTable(CuteSQLGrammarParser.SelectFromTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#primaryColumn}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryColumn(CuteSQLGrammarParser.PrimaryColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#primaryColumn}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryColumn(CuteSQLGrammarParser.PrimaryColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void enterColumnDef(CuteSQLGrammarParser.ColumnDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void exitColumnDef(CuteSQLGrammarParser.ColumnDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(CuteSQLGrammarParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(CuteSQLGrammarParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(CuteSQLGrammarParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(CuteSQLGrammarParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#columnType}.
	 * @param ctx the parse tree
	 */
	void enterColumnType(CuteSQLGrammarParser.ColumnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#columnType}.
	 * @param ctx the parse tree
	 */
	void exitColumnType(CuteSQLGrammarParser.ColumnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#indexName}.
	 * @param ctx the parse tree
	 */
	void enterIndexName(CuteSQLGrammarParser.IndexNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#indexName}.
	 * @param ctx the parse tree
	 */
	void exitIndexName(CuteSQLGrammarParser.IndexNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#columnValue}.
	 * @param ctx the parse tree
	 */
	void enterColumnValue(CuteSQLGrammarParser.ColumnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#columnValue}.
	 * @param ctx the parse tree
	 */
	void exitColumnValue(CuteSQLGrammarParser.ColumnValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#clusteringColumnName}.
	 * @param ctx the parse tree
	 */
	void enterClusteringColumnName(CuteSQLGrammarParser.ClusteringColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#clusteringColumnName}.
	 * @param ctx the parse tree
	 */
	void exitClusteringColumnName(CuteSQLGrammarParser.ClusteringColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#clusteringColumnValue}.
	 * @param ctx the parse tree
	 */
	void enterClusteringColumnValue(CuteSQLGrammarParser.ClusteringColumnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#clusteringColumnValue}.
	 * @param ctx the parse tree
	 */
	void exitClusteringColumnValue(CuteSQLGrammarParser.ClusteringColumnValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(CuteSQLGrammarParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(CuteSQLGrammarParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(CuteSQLGrammarParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(CuteSQLGrammarParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CuteSQLGrammarParser#starrOperator}.
	 * @param ctx the parse tree
	 */
	void enterStarrOperator(CuteSQLGrammarParser.StarrOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CuteSQLGrammarParser#starrOperator}.
	 * @param ctx the parse tree
	 */
	void exitStarrOperator(CuteSQLGrammarParser.StarrOperatorContext ctx);
}