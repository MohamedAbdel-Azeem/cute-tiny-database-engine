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




}
