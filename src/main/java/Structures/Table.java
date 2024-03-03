package Structures;

import Utils.Serializer;

import java.io.Serializable;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import static Utils.Serializer.deserialize;

public class Table implements Serializable {
    private String tableName;
    private String ClusteringKeyColumn;
    private Hashtable<String, String> colNmTypes;
    private Vector<String> pageNames;


    public Table(String tableName, String clusteringKeyColumn, Hashtable<String, String> colNameType) {
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.colNmTypes = colNameType;
        this.pageNames = new Vector<String>();
    }

    public void insertTuple(Hashtable<String,Object> htblColNameValue){
        if (! isValid(htblColNameValue)){
            return;
        }
        HashMap<String,Object> values = new HashMap<String,Object>(htblColNameValue);
        Tuple tuple = new Tuple(values);
        if (pageNames.isEmpty()){
            Page page = new Page();
            page.addTuple(tuple);
            String pageName = tableName+pageNames.size();
            Serializer.serialize(page,pageName);
            pageNames.add(pageName);
            return;
        } else {
            for (int i = 0; i < pageNames.size(); i++){ // Handles the 2 Cases of the Page being Not Full and a Page being full not until last pag
                Page currPage = (Page) deserialize(pageNames.get(i));
                if (! currPage.isFull()){
                    currPage.addTuple(tuple);
                    Serializer.serialize(currPage,pageNames.get(i));
                    return;
                } else {
                    Tuple newTuple = currPage.getTuples().removeLast();
                    currPage.addTuple(tuple);
                    Serializer.serialize(currPage,pageNames.get(i));
                    tuple = newTuple;
                }
            }
            Page newPage = new Page(); // in Case all the Pages were full till the last page create a new page
            newPage.addTuple(tuple);
            String pageName = tableName+pageNames.size();
            pageNames.add(pageName);
            Serializer.serialize(newPage,pageName);
            return;
        }
    }

    public static boolean isValid(Hashtable<String,Object> htblColNameValue){ // Check Data Types and Uniqueness of Primary Key
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------- Table Name: ").append(tableName).append("-------------\n");
        for (String pageName : pageNames){
            Page page = (Page) deserialize(pageName);
            sb.append(page.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Create a table
        Hashtable<String, String> colNameType = new Hashtable<>();
        colNameType.put("column1", "String");
        colNameType.put("column2", "Integer");
        Table table = new Table("test", "ClusteringKeyColumn", colNameType);

// Insert tuples into the table for testing
        Hashtable<String, Object> tuple1 = new Hashtable<>();
        tuple1.put("column1", "value1");
        tuple1.put("column2", 100);
        table.insertTuple(tuple1);

        Hashtable<String, Object> tuple2 = new Hashtable<>();
        tuple2.put("column1", "value2");
        tuple2.put("column2", 200);
        table.insertTuple(tuple2);

        Hashtable<String, Object> tuple3 = new Hashtable<>();
        tuple3.put("column1", "value3");
        tuple3.put("column2", 300);
        table.insertTuple(tuple3);
        Hashtable<String, Object> tuple4 = new Hashtable<>();
        tuple4.put("column1", "value4");
        tuple4.put("column2", 400);
        table.insertTuple(tuple4);
        System.out.println(table);

    }


}
