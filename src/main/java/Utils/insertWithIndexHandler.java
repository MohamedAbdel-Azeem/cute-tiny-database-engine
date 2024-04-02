package Utils;
import Structures.Tuple;
import Utils.metaFile;

import java.util.Hashtable;
import java.util.Vector;

public class insertWithIndexHandler {

    public static void insertIntoIndex(String strTableName , String pageName , Tuple tuple){
        Hashtable<String,String> indexes = metaFile.wasIndexMade(strTableName);
        if (indexes == null || indexes.isEmpty()){
            return;
        }
        for (String indexedCol : indexes.keySet()) {
            String indexName = indexes.get(indexedCol);
            insertIntoSingleIndex(indexName,(Comparable) tuple.getValue(indexedCol),pageName);
        }
    }
    
    
    private static Vector<String> getIndexValueVector(Comparable indexKey , String indexName){
        bplustree index = (bplustree) Serializer.deserialize(indexName);
        return index.search(indexKey);
    }
    
    public static void insertIntoSingleIndex(String indexName , Comparable indexKey , String pageName){
        bplustree index = (bplustree) Serializer.deserialize(indexName);
        Vector<String> duplicates = getIndexValueVector(indexKey,indexName);
        if (duplicates == null){ // with no Duplicates
            duplicates = new Vector<>();
            duplicates.add(pageName);
            index.insert(indexKey,duplicates);
            Serializer.serialize(index,indexName);
            return;
        }
        duplicates.add(pageName);
        Serializer.serialize(index,indexName);
        return;
    }
    
    public static void handleInsertionShifting(String strTableName , String oldPageName , String newPageName , Tuple tuple){
        Hashtable<String,String> indexes = metaFile.wasIndexMade(strTableName);
        if (indexes == null || indexes.isEmpty()){
            return;
        }
        for (String indexedCol : indexes.keySet()) {
            String indexName = indexes.get(indexedCol);
            handleSingleIndexShifting(indexName,(Comparable) tuple.getValue(indexedCol),oldPageName,newPageName);
        }
    }

    private static void handleSingleIndexShifting(String indexName, Comparable value, String oldPageName, String newPageName) {
        bplustree index = (bplustree) Serializer.deserialize(indexName);
        Vector<String> PageValues = index.search(value);
        if (PageValues == null){
            return;
        }
        PageValues.remove(oldPageName);
        PageValues.add(newPageName);
        Serializer.serialize(index,indexName);
    }



}
