package SQLParser;

import DBMain.DBAppException;

import java.util.Hashtable;
import java.util.Vector;

import static Utils.SQLParserHelper.AdjustingOperations.getDataTypes;
import DBMain.DBApp;
import SQLParser.SQLGrammarParser;


public class SQLWalker extends SQLParser.SQLGrammarBaseListener {

    private static SQLWalker instance = null;
    private DBApp dbApp;

    private SQLWalker(){
        dbApp = new DBApp();
    }

    public static SQLWalker getSQLWalkerInstance(){
        if (instance == null){
            instance = new SQLWalker();
        }
        return instance;
    }


    public void enterCreateTable(SQLGrammarParser.CreateTableContext ctx) {
        try {
            String strTableName = ctx.tableName().getText();
            String strClusteringKeyColumn = ctx.primaryColumnDefinition().columnDefinition().columnName().getText();
            Hashtable<String,String> htblColNameValue = new Hashtable<>();
            htblColNameValue.put(strClusteringKeyColumn, getDataTypes(ctx.primaryColumnDefinition().columnDefinition().dataType().getText()));
            for (SQLGrammarParser.ColumnDefinitionContext node : ctx.columnDefinition()) {
                htblColNameValue.put(node.columnName().getText(), getDataTypes(node.dataType().getText()));
            }
            try {
                dbApp.createTable(strTableName, strClusteringKeyColumn, htblColNameValue);
                System.out.println(strTableName+" Table Created Successfully");
            } catch (DBAppException e) {
                System.out.println(e.getMessage());
            }
        } catch (DBAppException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enterInsertIntoTable(SQLGrammarParser.InsertIntoTableContext ctx) {
        String strTableName = ctx.tableName().getText();
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        Vector<String> colNames = new Vector<>();
        for (SQLGrammarParser.ColumnNameContext node : ctx.columnName()) {
            colNames.add(node.getText());
        }
        int counter = 0;
        for (SQLGrammarParser.ColumnValueContext node : ctx.columnValue()) {
            if (node.INT() != null){
                htblColNameValue.put(colNames.get(counter), Integer.parseInt(node.getText()));
            } else if (node.DOUBLE() != null){
                htblColNameValue.put(colNames.get(counter), Double.parseDouble(node.getText()));
            } else {
                htblColNameValue.put(colNames.get(counter), node.getText());
            }
            counter++;
        }
        try{
            dbApp.insertIntoTable(strTableName, htblColNameValue);
            System.out.println("Row Inserted Successfully");
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
    }

    public void enterCreateIndex(SQLGrammarParser.CreateIndexContext ctx) {
        String strTableName = ctx.tableName().getText();
        String strColName = ctx.columnName().getText();
        String strIndexName = ctx.indexName().getText();
        try {
            dbApp.createIndex(strTableName, strColName,strIndexName);
            System.out.println("Index Created Successfully");
        } catch (DBAppException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enterUpdateTable(SQLGrammarParser.UpdateTableContext ctx) {
        String strTableName = ctx.tableName().getText();
        String strClusteringKeyValue = ctx.clusteringColumnValue().getText();
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        Vector<String> colNames = new Vector<>();
        for (SQLGrammarParser.ColumnNameContext node : ctx.columnName()) {
            colNames.add(node.getText());
        }
        int counter = 0;
        for (SQLGrammarParser.ColumnValueContext node : ctx.columnValue()) {
            if (node.INT() != null){
                htblColNameValue.put(colNames.get(counter), Integer.parseInt(node.getText()));
            } else if (node.DOUBLE() != null){
                htblColNameValue.put(colNames.get(counter), Double.parseDouble(node.getText()));
            } else {
                htblColNameValue.put(colNames.get(counter), node.getText());
            }
            counter++;
        }
        try {
            dbApp.updateTable(strTableName,strClusteringKeyValue,htblColNameValue);
            System.out.println("Row Updated Successfully");
        } catch (DBAppException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enterDeleteFromTable(SQLGrammarParser.DeleteFromTableContext ctx) {
        String strTableName = ctx.tableName().getText();
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        Vector<String> colNames = new Vector<>();
        for (SQLGrammarParser.ColumnNameContext node : ctx.columnName()) {
            colNames.add(node.getText());
        }
        int counter = 0;
        for (SQLGrammarParser.ColumnValueContext node : ctx.columnValue()) {
            if (node.INT() != null){
                htblColNameValue.put(colNames.get(counter), Integer.parseInt(node.getText()));
            } else if (node.DOUBLE() != null){
                htblColNameValue.put(colNames.get(counter), Double.parseDouble(node.getText()));
            } else {
                htblColNameValue.put(colNames.get(counter), node.getText());
            }
            counter++;
        }
        try {
            dbApp.deleteFromTable(strTableName,htblColNameValue);
            System.out.println("Row(s) Deleted Successfully");
        } catch (DBAppException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enterSelectFromTable(SQLGrammarParser.SelectFromTableContext ctx) {
        SQLTerm[] arrSQLTerms = new SQLTerm[ctx.sqlTerm().size()];
        String[] strarrOperators = new String[ctx.sqlTerm().size()-1];
        int counter = 0;
        for (SQLGrammarParser.SqlTermContext node : ctx.sqlTerm()) {
            arrSQLTerms[counter] = new SQLTerm(node.columnName().getText(),node.operator().getText(),node.columnValue().getText());
            counter++;
        }
        counter = 0;
        for (SQLGrammarParser.OperatorContext node : ctx.operator()) {
            strarrOperators[counter] = node.getText();
            counter++;
        }
        try {
            dbApp.selectFromTable(arrSQLTerms,strarrOperators);
        } catch (DBAppException e) {
            System.out.println(e.getMessage());
        }
    }






}
