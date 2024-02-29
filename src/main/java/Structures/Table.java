package Structures;

import Utils.Serializer;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public class Table {
    private String tableName;
    private String ClusteringKeyColumn;
    private Hashtable<String, String> colNmTypes;
    private Vector<Page> pages;

    private HashSet primaryKeys;


    public Table(String tableName, String clusteringKeyColumn, Hashtable<String, String> colNameType) {
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.colNmTypes = colNameType;
        this.pages = new Vector<Page>();
        this.primaryKeys = new HashSet();
    }

    public void insertTuple(Hashtable<String,Object> htblColNameValue){
        if (! isValid(htblColNameValue)){
            return;
        }
        HashMap<String,Object> values = new HashMap<String,Object>(htblColNameValue);
        Tuple tuple = new Tuple(values);
        if (pages.isEmpty()){
            Page page = new Page();
            page.addTuple(tuple);
            pages.add(page);
            Serializer.serialize(page,tableName+pages.size());
            return;
        } else {
            for (int i = 0; i < pages.size(); i++){ // Handles the 2 Cases of the Page being Not Full and a Page being not full
                Page currPage = pages.get(i);
                if (!currPage.isFull()){
                    currPage.addTuple(tuple);
                    Serializer.serialize(currPage,tableName+(i+1));
                    return;
                } else {
                    Tuple newTuple = currPage.getTuples().removeLast();
                    currPage.addTuple(tuple);
                    Serializer.serialize(currPage,tableName+(i+1));
                    tuple = newTuple;
                }
            }
            Page newPage = new Page(); // in Case all the Pages were full till the last page create a new page
            newPage.addTuple(tuple);
            pages.add(newPage);
            Serializer.serialize(newPage,tableName+pages.size());
            return;
        }
    }

    public static boolean isValid(Hashtable<String,Object> htblColNameValue){ // Check Data Types and Uniqueness of Primary Key
        return true;
    }

    public String toString(){
        return pages.toString();
    }



}
