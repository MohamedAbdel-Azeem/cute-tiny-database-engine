package MyCuteSQLParser;

import DBMain.DBApp;
import DBMain.DBAppException;
import DBMain.SQLTerm;
import gen.CuteSQLGrammarBaseVisitor;
import gen.CuteSQLGrammarParser;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class CuteSQLWalker extends CuteSQLGrammarBaseVisitor<Iterator> {

    DBApp dbApp;
    private static Iterator result;

    public CuteSQLWalker(DBApp dbApp) {
        this.dbApp = dbApp;
    }

    public Iterator getResult(){
        return result;
    }

    @Override
    public Iterator visitCreateTable(CuteSQLGrammarParser.CreateTableContext ctx) {
        String tableName = ctx.tableName().getText();
        String clusteringKeyColumn = ctx.primaryColumn().columnDef().columnName().getText();
        String inputclusteringKeyDataType = ctx.primaryColumn().columnDef().columnType().getText();
        String clusteringKeyDataType = "";
        switch (inputclusteringKeyDataType){
            case "INT": clusteringKeyDataType="java.lang.Integer"; break;
            case "DOUBLE": clusteringKeyDataType = "java.lang.Double"; break;
            case "STRING": clusteringKeyDataType = "java.lang.String";break;
            default:
                System.out.println("Unsupported data type");
                result = null;
                return null;
        }
        Hashtable<String,String> htblColNameType = new Hashtable<>();
        htblColNameType.put(clusteringKeyColumn, clusteringKeyDataType);
        for (CuteSQLGrammarParser.ColumnDefContext node : ctx.columnDef()) {
            String columnName = node.columnName().getText();
            String inputColumnType = node.columnType().getText().toUpperCase();
            String columnType = "";
            switch (inputColumnType){
                case "INT": columnType="java.lang.Integer"; break;
                case "DOUBLE": columnType = "java.lang.Double"; break;
                case "STRING": columnType = "java.lang.String";break;
                default:
                    System.out.println("Unsupported data type");
                    result = null;
                    return null;
            }
            htblColNameType.put(columnName, columnType);
        }
        try {
            this.dbApp.createTable(tableName, clusteringKeyColumn, htblColNameType);
        } catch (DBAppException e) {
            System.out.println(e.getMessage());
        }

        result = null;
        return null;
    }

    @Override
    public Iterator visitCreateIndex(CuteSQLGrammarParser.CreateIndexContext ctx) {
        String indexName = ctx.indexName().getText();
        String tableName = ctx.tableName().getText();
        String columnName = ctx.columnName().getText();
        try {
            this.dbApp.createIndex(tableName, columnName, indexName);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
        result = null;
        return null;
    }

    @Override
    public Iterator visitInsertIntoTable(CuteSQLGrammarParser.InsertIntoTableContext ctx) {
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
                result = null;
                return null;
            }
            counter++;
        }
        try {
            this.dbApp.insertIntoTable(tableName, htblColNameValue);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
        result = null;
        return null;
    }

    @Override
    public Iterator visitUpdateTable(CuteSQLGrammarParser.UpdateTableContext ctx) {
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
                result = null;
                return null;
            }
            counter++;
        }
        try {
            this.dbApp.updateTable(tableName, clusteringKeyColumnValue,htblColNameValue);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
        result = null;
        return null;
    }

    @Override
    public Iterator visitDeleteFromTable(CuteSQLGrammarParser.DeleteFromTableContext ctx) {
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
                result = null;
                return null;
            }
            counter++;
        }
        try {
            this.dbApp.deleteFromTable(tableName, htblColNameValue);
        } catch (DBAppException e){
            System.out.println(e.getMessage());
        }
        result = null;
        return null;
    }


    @Override
    public Iterator visitSelectFromTable(CuteSQLGrammarParser.SelectFromTableContext ctx) {
        String tableName = ctx.tableName().getText();
        System.out.println("Table Name: " + tableName);
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
                result = null;
                return null;
            }
            sqlTerms.add(sqlTerm);
        }


        for (CuteSQLGrammarParser.StarrOperatorContext node : ctx.starrOperator()) {
            strarrOperators.add(node.getText());
        }

        SQLTerm[] arrSQLTerms = sqlTerms.toArray(new SQLTerm[sqlTerms.size()]);
        String[] arrStarrOperators = strarrOperators.toArray(new String[strarrOperators.size()]);

        try {
            if (arrSQLTerms.length == 0){
                System.out.println("Case 1");
                SQLTerm sqlTerm = new SQLTerm();
                sqlTerm._strTableName = tableName;
                SQLTerm[] arrSQLTerms1 = new SQLTerm[1];
                arrSQLTerms1[0] = sqlTerm;
                result = this.dbApp.selectFromTable(arrSQLTerms1,null);
                return result;
            } else if (arrSQLTerms.length == 1 && arrStarrOperators.length == 0){
                System.out.println("Case 2");
                result = this.dbApp.selectFromTable(arrSQLTerms,new String[0]);
                return result;
            } else {
                System.out.println("Case 3");
                result = this.dbApp.selectFromTable(arrSQLTerms, arrStarrOperators);
                return result;
            }
        } catch (DBAppException e){
            System.out.println("Exception");
            System.out.println(e.getMessage());
            return null;
        }

    }

}
