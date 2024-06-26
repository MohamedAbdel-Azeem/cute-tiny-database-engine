package Utils;

import Structures.Page;
import Structures.Table;
import Structures.Tuple;

import java.util.Hashtable;
import java.util.Vector;
import Utils.TableLookupOps;

public class deleteHandler {

    public static Vector<String> deleteWithClusteringKey(String strTableName, Hashtable<String,Object> htblColNameValue){
        Vector<String> resultPages = new Vector<>();
        Table table = (Table) Serializer.deserialize(strTableName);
        String clusteringKey = table.getClusteringKeyColumn();
        int pageIndex = TableLookupOps.helper_getPageIndex(table.getPageIntervals(),(Comparable) htblColNameValue.get(clusteringKey));
        resultPages.add(table.getPageNames().get(pageIndex));
        return resultPages;
    }

    public static Vector<String> deleteWithIndex(Comparable searchValue, String indexName,Hashtable<String,Object> htblColNameValue){
        bplustree index = (bplustree) Serializer.deserialize(indexName);
        Vector<String> duplicates =  index.search(searchValue);
        Vector<String> resultPages = new Vector<>();
        if (duplicates == null){
            return resultPages;
        }
        for (String pageName : duplicates){
            Page page = (Page) Serializer.deserialize(pageName);
            for (Tuple tuple : page.getTuples()){
                if (tuple.isEqual(htblColNameValue)){
                    resultPages.add(pageName);
                }
            }
        }
        return resultPages;
    }

    public static Vector<String> deleteWithNothing(String strTableName, Hashtable<String,Object> htblColNameValue){
        Vector<String> resultPages = new Vector<>();
        Table table = (Table) Serializer.deserialize(strTableName);
        for (String pageName : table.getPageNames()){
            Page page = (Page) Serializer.deserialize(pageName);
            if(page==null){
                continue;
            }
            for (Tuple tuple : page.getTuples()){
                if (tuple.isEqual(htblColNameValue)){
                    resultPages.add(pageName);
                }
            }
        }
        return resultPages;
    }

    public static boolean isValidDeleteQuery(String strTableName, Hashtable<String,Object> htblColNameValue){
        Hashtable<String,String> htblColNameType = metaFile.extractTblCols(strTableName);
        for (String colName : htblColNameValue.keySet()){
            if (! htblColNameType.containsKey(colName)){
                return false;
            }
            if (! htblColNameValue.get(colName).getClass().getName().equalsIgnoreCase(htblColNameType.get(colName))){
                return false;
            }
        }
        return true;
    }

    public static void deleteFromIndexes(Vector<String> targetPages, Hashtable<String,Object> htblColNameValue, Hashtable<String,String> indexedCols){
    if(indexedCols==null){
        return;
    }
        for (String colName : htblColNameValue.keySet()){
            if (indexedCols.containsKey(colName)){

                bplustree index = (bplustree) Serializer.deserialize(indexedCols.get(colName));

                Vector<String> duplicates = index.search((Comparable) htblColNameValue.get(colName));
                for (String pageName : targetPages){
                    duplicates.remove(pageName);
                }
                index.delete((Comparable) htblColNameValue.get(colName));
                if (duplicates != null){
                    index.insert((Comparable) htblColNameValue.get(colName),duplicates);
                }
                Serializer.serialize(index,indexedCols.get(colName));
            }
            else{
                for(String indexName : indexedCols.keySet()){
                    bplustree index = (bplustree) Serializer.deserialize(indexedCols.get(indexName));

                    for (String pageName : targetPages){
                       Page page = (Page) Serializer.deserialize(pageName);
                        for (Tuple tuple : page.getTuples()){
                            if (tuple.isEqual(htblColNameValue)){
                                Vector<String> duplicates = index.search((Comparable) tuple.getValue(indexName));
                                duplicates.remove(pageName);
                                index.delete((Comparable) tuple.getValue(indexName));
                                if (duplicates.size()!=0){
                                    index.insert((Comparable) tuple.getValue(indexName),duplicates);
                                }
                            }
                        }
                    }

                    Serializer.serialize(index,indexedCols.get(indexName));
                }
            }
        }
    }


}
