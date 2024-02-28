package Structures;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import Structures.Tuple;
import Structures.Page;
import Utils.Serializer;

public class Table {
    private String tableName;
    private String ClusteringKeyColumn;
    private Hashtable<String, String> colNmTypes;
    private Vector<Page> pages;


    public Table(String tableName, String clusteringKeyColumn, Hashtable<String, String> colNameType){
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.colNmTypes = colNameType;
        this.pages = new Vector<Page>();
    }

    public static void main(String[] args) {
//        Tuple tuple1 = new Tuple(new HashMap<>());
//        tuple1.setValue("column1", "value1");
//        tuple1.setValue("column2", 123);
//
//        Tuple tuple2 = new Tuple(new HashMap<>());
//        tuple2.setValue("column1", "value2");
//        tuple2.setValue("column2", 456);
//
//// Create a page
//        Page page = new Page();
//
//// Add tuples to the page
//        page.addTuple(tuple1);
//        page.addTuple(tuple2);
//
//        Serializer.serialize(page, "./DB/page1.class");
        Page page = Serializer.deserialize("page1");
        System.out.println(page.getTuples());
    }

}
