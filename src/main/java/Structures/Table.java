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

}
