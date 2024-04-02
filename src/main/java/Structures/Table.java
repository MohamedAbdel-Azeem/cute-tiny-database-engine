package Structures;

import DBMain.DBAppException;
import Utils.Serializer;
import Utils.bplustree;

import java.io.Serializable;
import java.util.*;

import static Utils.Serializer.deserialize;
import static Utils.metaFile.extractTblCols;
import static Utils.metaFile.wasIndexMade;
import static Utils.TableLookupOps.*;
import Utils.insertWithIndexHandler;
import Utils.updateWithIndexHandler;

public class Table implements Serializable {
    private String tableName;
    private String ClusteringKeyColumn;
    private Vector<String> pageNames;


    public Table(String tableName, String clusteringKeyColumn) {
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.pageNames = new Vector<String>();
    }


    public void insertTuple(Hashtable<String, Object> htblColNameValue) throws DBAppException{
        if (! this.isValid(htblColNameValue)){
            throw new DBAppException("Invalid Tuple Data Types");
        }
        Tuple newTuple = new Tuple(htblColNameValue);
        Hashtable<String,String> indexedCols=wasIndexMade(this.tableName);

        // Handle first Insert in the Table
        if (pageNames.isEmpty()){
            Page newPage = new Page();
            newPage.addTuple(0,newTuple);
            String pageName = tableName+pageNames.size();
            pageNames.add(pageName);
            Serializer.serialize(newPage,pageName);
            insertWithIndexHandler.insertIntoIndex(this.tableName,pageName,newTuple);
            return;
        }

        int PageIndex = helper_getPageIndex(newTuple,this.pageNames, this.ClusteringKeyColumn);
        Page page = (Page) deserialize(pageNames.get(PageIndex));
        int insertIndex = helperSearchInsert(page.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn),this.ClusteringKeyColumn);
        if (insertIndex == -1) {
            throw new DBAppException("Primary Key Violation");
        }
        insertWithIndexHandler.insertIntoIndex(this.tableName,this.getPageNames().get(PageIndex),newTuple);
        if (page.isFull()){
            Tuple lastTuple = page.getTuples().removeLast();
            page.getTuples().insertElementAt(newTuple, insertIndex);
            Serializer.serialize(page,pageNames.get(PageIndex));
            for (int j = PageIndex+1 ; j < pageNames.size(); j++){
                Page nextPage = (Page) deserialize(pageNames.get(j));
                insertWithIndexHandler.handleInsertionShifting(this.tableName,pageNames.get(j-1),pageNames.get(j),lastTuple);
                if (! nextPage.isFull()){
                    nextPage.getTuples().addFirst(lastTuple);
                    Serializer.serialize(nextPage,pageNames.get(j));
                    return;
                } else {
                    nextPage.getTuples().addFirst(lastTuple);
                    lastTuple = nextPage.getTuples().removeLast();
                    Serializer.serialize(nextPage,pageNames.get(j));
                }
            }
            Page newPage = new Page();
            newPage.addTuple(0,lastTuple);
            String newPageName = tableName+pageNames.size();
            pageNames.add(newPageName);
            Serializer.serialize(newPage,newPageName);
            insertWithIndexHandler.handleInsertionShifting(this.tableName,pageNames.get(pageNames.size()-2),newPageName,lastTuple);
            return;
        } else {
            page.getTuples().insertElementAt(newTuple, insertIndex);
            Serializer.serialize(page,pageNames.get(PageIndex));
            return;
        }
    }


    // Check Column Types
    // Check Uniqueness of Primary Key
    public  boolean isValid(Hashtable<String,Object> htblColNameValue)  { // Check Data Types and Uniqueness of Primary Key
            Hashtable<String,String> ColDataTypes= extractTblCols(this.tableName);
            if (ColDataTypes.size() != htblColNameValue.size())
                return false;
            Iterator<String>iterator = (Iterator<String>) ColDataTypes.keys();
            boolean flag=true;
            // check for correct data types
            while(iterator.hasNext()){
                String ColName=iterator.next();
                Object value=htblColNameValue.get(ColName);
                String type=ColDataTypes.get(ColName);
                Class cls = null;
                try {
                    cls = Class.forName(type);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                flag=flag&&cls.isInstance(value);
                if(!flag){
                    System.out.println("Data Types are incorrect");
                    return flag;
                   }
            }
            return  true;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------- Table Name: ").append(tableName).append("-------------\n");
        for (String pageName : pageNames){
            Page page = (Page) deserialize(pageName);
            sb.append("-Page Name: ").append(pageName).append("\n");
            sb.append(page.toString());
        }
        return sb.toString();
    }

    public void updateTuple(Comparable clusteringKeyValue , Hashtable<String,Object> newValues) throws DBAppException{
        if (pageNames.isEmpty()){
            throw new DBAppException("Tuple is not Found");
        }
        int pageIndex = helper_getPageIndex(clusteringKeyValue,pageNames,this.ClusteringKeyColumn);
        Page page = (Page) deserialize(pageNames.get(pageIndex));
        for (Tuple tuple : page.getTuples()){
            if (tuple.getValue(this.ClusteringKeyColumn).equals(clusteringKeyValue)){
                Hashtable<String,Object> updatedValues = new Hashtable<String,Object>(tuple.getValues());
                for (String colName : newValues.keySet()){ // Add new Values to the Tuple
                    updatedValues.put(colName,newValues.get(colName));
                }
                if (! this.isValid(updatedValues)){
                    throw new DBAppException("Invalid Tuple Data Types");
                }
                updateWithIndexHandler.updateIntoIndex(this.tableName,pageNames.get(pageIndex),newValues,tuple);
                tuple.updateValues(updatedValues);
                Serializer.serialize(page,pageNames.get(pageIndex));
                return;
            }
        }

    }



    public Vector<String> getPageNames() {
        return pageNames;
    }
    public String getClusteringKeyColumn() {
        return ClusteringKeyColumn;
    }


}
