package Structures;

import Utils.Serializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import static Utils.Serializer.deserialize;

public class Table implements Serializable {
    private String tableName;
    private String ClusteringKeyColumn;
    private transient Hashtable<String, String> colNmTypes;
    private Vector<String> pageNames;


    public Table(String tableName, String clusteringKeyColumn, Hashtable<String, String> colNameType) {
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.colNmTypes = colNameType;
        this.pageNames = new Vector<String>();
    }

    public void insertTuple(Hashtable<String,Object> htblColNameValue){
        if (! isValid(htblColNameValue,this.tableName)){
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

    // Check Column Types
    // Check Uniqueness of Primary Key
    public static boolean isValid(Hashtable<String,Object> htblColNameValue, String tableName) throws ClassNotFoundException { // Check Data Types and Uniqueness of Primary Key
            Hashtable<String,String> ColDataTypes= extractTblCols(tableName);
            ArrayList<String> colname = (ArrayList<String>) htblColNameValue.keys();
            boolean flag=true;
            // check for correct data types
            for(int i=0;i<colname.size();i++){
                Object value=htblColNameValue.get(colname.get(i));
                String type=ColDataTypes.get(colname.get(i));
                Class cls = Class.forName(type);
                flag=flag&&cls.isInstance(value);}







        return true;
    }

    private static Hashtable<String, String> extractTblCols(String tableName) {
        return null;
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




}
