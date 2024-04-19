package MyCuteSQLParser;

import DBMain.DBApp;
import DBMain.DBAppException;
import DBMain.SQLTerm;
import gen.CuteSQLGrammarBaseListener;
import gen.CuteSQLGrammarLexer;
import gen.CuteSQLGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;

public class CuteSQLWalker extends CuteSQLGrammarBaseListener {

    DBApp dbApp;

    public CuteSQLWalker(DBApp dbApp) {
        this.dbApp = dbApp;
    }

    @Override
    public void enterCreateTable(CuteSQLGrammarParser.CreateTableContext ctx) {
        String tableName = ctx.tableName().getText();
        String clusteringKeyColumn = ctx.primaryColumn().columnDef().columnName().getText();
        String clusteringKeyDataType = ctx.primaryColumn().columnDef().columnType().getText();
        Hashtable<String,String> htblColNameType = new Hashtable<>();
        htblColNameType.put(clusteringKeyColumn, clusteringKeyDataType);
        for (CuteSQLGrammarParser.ColumnDefContext node : ctx.columnDef()) {
            String columnName = node.columnName().getText();
            String columnType = node.columnType().getText();
            htblColNameType.put(columnName, columnType);
        }
        try {
            this.dbApp.createTable(tableName, clusteringKeyColumn, htblColNameType);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void enterCreateIndex(CuteSQLGrammarParser.CreateIndexContext ctx) {
        String indexName = ctx.indexName().getText();
        String tableName = ctx.tableName().getText();
        String columnName = ctx.columnName().getText();
        try {
            this.dbApp.createIndex(tableName, columnName, indexName);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void enterInsertIntoTable(CuteSQLGrammarParser.InsertIntoTableContext ctx) {
        String tableName = ctx.tableName().getText();
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        Vector<String> columnNames = new Vector<>();
        for (CuteSQLGrammarParser.ColumnNameContext node : ctx.columnName()) {
            columnNames.add(node.getText());
        }
        int counter = 0;
        for (CuteSQLGrammarParser.ColumnValueContext node : ctx.columnValue()) {
            if (node.INTValue() != null){
                htblColNameValue.put(columnNames.get(counter), Integer.parseInt(node.INTValue().getText()));
            } else if (node.DOUBLEValue() != null){
                htblColNameValue.put(columnNames.get(counter), Double.parseDouble(node.DOUBLEValue().getText()));
            } else if (node.StringValue() != null){
                htblColNameValue.put(columnNames.get(counter), node.StringValue().getText().substring(1, node.StringValue().getText().length()-1));
            } else {
                System.out.println("Unsupported data type");
                return;
            }
            counter++;
        }
        try {
            this.dbApp.insertIntoTable(tableName, htblColNameValue);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void enterUpdateTable(CuteSQLGrammarParser.UpdateTableContext ctx) {
        String tableName = ctx.tableName().getText();
        String clusteringKeyColumnValue = ctx.clusteringColumnValue().getText();
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        Vector<String> columnNames = new Vector<>();
        for (CuteSQLGrammarParser.ColumnNameContext node : ctx.columnName()) {
            columnNames.add(node.getText());
        }
        int counter = 0;
        for (CuteSQLGrammarParser.ColumnValueContext node : ctx.columnValue()) {
            if (node.INTValue() != null){
                htblColNameValue.put(columnNames.get(counter), Integer.parseInt(node.INTValue().getText()));
            } else if (node.DOUBLEValue() != null){
                htblColNameValue.put(columnNames.get(counter), Double.parseDouble(node.DOUBLEValue().getText()));
            } else if (node.StringValue() != null){
                htblColNameValue.put(columnNames.get(counter), node.StringValue().getText().substring(1, node.StringValue().getText().length()-1));
            } else {
                System.out.println("Unsupported data type");
                return;
            }
            counter++;
        }
        try {
            this.dbApp.updateTable(tableName, clusteringKeyColumnValue,htblColNameValue);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void enterDeleteFromTable(CuteSQLGrammarParser.DeleteFromTableContext ctx) {
        String tableName = ctx.tableName().getText();
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        Vector<String> columnNames = new Vector<>();
        for (CuteSQLGrammarParser.ColumnNameContext node : ctx.columnName()) {
            columnNames.add(node.getText());
        }
        int counter = 0;
        for (CuteSQLGrammarParser.ColumnValueContext node : ctx.columnValue()) {
            if (node.INTValue() != null){
                htblColNameValue.put(columnNames.get(counter), Integer.parseInt(node.INTValue().getText()));
            } else if (node.DOUBLEValue() != null){
                htblColNameValue.put(columnNames.get(counter), Double.parseDouble(node.DOUBLEValue().getText()));
            } else if (node.StringValue() != null){
                htblColNameValue.put(columnNames.get(counter), node.StringValue().getText().substring(1, node.StringValue().getText().length()-1));
            } else {
                System.out.println("Unsupported data type");
                return;
            }
            counter++;
        }
        try {
            this.dbApp.deleteFromTable(tableName, htblColNameValue);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void enterSelectFromTable(CuteSQLGrammarParser.SelectFromTableContext ctx) {
        String tableName = ctx.tableName().getText();
        Vector<SQLTerm> sqlTerms = new Vector<>();
        Vector<String> strarrOperators = new Vector<>();
        for (CuteSQLGrammarParser.ConditionContext node : ctx.condition()) {
            String colName = node.columnName().getText();
            String operator = node.operator().getText();
            SQLTerm sqlTerm = new SQLTerm();
            sqlTerm._strTableName = tableName;
            sqlTerm._strColumnName = colName;
            sqlTerm._strOperator = operator;
            if (node.columnValue().INTValue() != null){
                sqlTerm._objValue = Integer.parseInt(node.columnValue().INTValue().getText());
            } else if (node.columnValue().DOUBLEValue() != null){
                sqlTerm._objValue = Double.parseDouble(node.columnValue().DOUBLEValue().getText());
            } else if (node.columnValue().StringValue() != null){
                sqlTerm._objValue = node.columnValue().StringValue().getText().substring(1, node.columnValue().StringValue().getText().length()-1);
            } else {
                System.out.println("Unsupported data type");
                return;
            }
            sqlTerms.add(sqlTerm);
        }

        for (CuteSQLGrammarParser.StarrOperatorContext node : ctx.starrOperator()) {
            strarrOperators.add(node.getText());
        }

        SQLTerm[] arrSQLTerms = sqlTerms.toArray(new SQLTerm[0]);
        String[] arrStarrOperators = strarrOperators.toArray(new String[0]);

        try {
            this.dbApp.selectFromTable(arrSQLTerms, arrStarrOperators);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        ANTLRInputStream input = new ANTLRInputStream("SELECT * FROM Users WHERE gpa > 0.5 AND name = 'Alice' OR Age >= 20;");
        // Create a lexer and parser
        CuteSQLGrammarLexer lexer = new CuteSQLGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CuteSQLGrammarParser parser = new CuteSQLGrammarParser(tokens);

        // Walk the parse tree
        ParseTree tree = parser.cuteSQL();
        ParseTreeWalker walker = new ParseTreeWalker();
        CuteSQLWalker listener = new CuteSQLWalker(new DBApp());
        walker.walk(listener, tree);
    }

}
