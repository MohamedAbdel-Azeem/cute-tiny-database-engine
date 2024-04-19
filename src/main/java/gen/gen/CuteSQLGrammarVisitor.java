// Generated from C:/Users/moham/OneDrive/Desktop/cute-tiny-database-engine/src/main/java/gen/CuteSQLGrammar.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CuteSQLGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CuteSQLGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#cuteSQL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuteSQL(CuteSQLGrammarParser.CuteSQLContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTable(CuteSQLGrammarParser.CreateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#createIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateIndex(CuteSQLGrammarParser.CreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#insertIntoTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertIntoTable(CuteSQLGrammarParser.InsertIntoTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#updateTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateTable(CuteSQLGrammarParser.UpdateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#deleteFromTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteFromTable(CuteSQLGrammarParser.DeleteFromTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#selectFromTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFromTable(CuteSQLGrammarParser.SelectFromTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#primaryColumn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryColumn(CuteSQLGrammarParser.PrimaryColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#columnDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDef(CuteSQLGrammarParser.ColumnDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(CuteSQLGrammarParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(CuteSQLGrammarParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#columnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnType(CuteSQLGrammarParser.ColumnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#indexName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexName(CuteSQLGrammarParser.IndexNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#columnValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnValue(CuteSQLGrammarParser.ColumnValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#clusteringColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClusteringColumnName(CuteSQLGrammarParser.ClusteringColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#clusteringColumnValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClusteringColumnValue(CuteSQLGrammarParser.ClusteringColumnValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(CuteSQLGrammarParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(CuteSQLGrammarParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CuteSQLGrammarParser#starrOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStarrOperator(CuteSQLGrammarParser.StarrOperatorContext ctx);
}