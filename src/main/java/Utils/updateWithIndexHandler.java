package Utils;

import Structures.Tuple;

import java.util.Hashtable;
import java.util.Vector;

public class updateWithIndexHandler {

    public static void updateIntoIndex(String strTableName , String pageName , Hashtable<String,Object> updatedTupleCols , Tuple oldTuple){
        Hashtable<String,String> indexes = metaFile.wasIndexMade(strTableName);
        if (indexes == null || indexes.isEmpty()){
            return;
        }
        for (String indexedCol : indexes.keySet()) {
            String indexName = indexes.get(indexedCol);
            if (updatedTupleCols.get(indexedCol) != null)
                updateSingleIndex(indexName,(Comparable) updatedTupleCols.get(indexedCol),(Comparable) oldTuple.getValue(indexedCol),pageName);
        }
    }

    private static void updateSingleIndex(String indexName , Comparable newIndexKey , Comparable oldIndexKey , String pageName){
        bplustree index = (bplustree) Serializer.deserialize(indexName);
        Vector<String> duplicates = index.search(oldIndexKey);
        if (duplicates == null){
            return;
        }
        duplicates.remove(pageName);
        if (duplicates.isEmpty())
            index.delete(oldIndexKey);
        System.out.println(newIndexKey+" Test");
        Vector<String> newDuplicates = index.search(newIndexKey);
        if (newDuplicates == null)
            newDuplicates = new Vector<String>();
        newDuplicates.add(pageName);
        index.insert(newIndexKey,newDuplicates);
        Serializer.serialize(index,indexName);
    }

}
