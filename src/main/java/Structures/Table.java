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
import Utils.deleteHandler;

public class Table implements Serializable {
    private String tableName;
    private String ClusteringKeyColumn;
    private Vector<String> pageNames;
    private Vector<Comparable[]> pageIntervals;

    public Table(String tableName, String clusteringKeyColumn) {
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.pageNames = new Vector<String>();
        this.pageIntervals = new Vector<Comparable[]>();
    }


    public void insertTuple(Hashtable<String, Object> htblColNameValue) throws DBAppException{
        if(!htblColNameValue.containsKey(this.getClusteringKeyColumn())){
            throw new DBAppException("Clustering Key is missing");
        }
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

            Comparable[] interval = new Comparable[2];
            interval[0] = (Comparable) newTuple.getValue(this.ClusteringKeyColumn);
            interval[1] = (Comparable) newTuple.getValue(this.ClusteringKeyColumn);
            pageIntervals.add(interval);

            return;
        }

        int PageIndex = helper_getPageIndex(newTuple,this.pageIntervals, this.ClusteringKeyColumn);
        Page page = (Page) deserialize(pageNames.get(PageIndex));
        int insertIndex = helperSearchInsert(page.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn),this.ClusteringKeyColumn);
        if (insertIndex == -1) {
            throw new DBAppException("Primary Key Violation");
        }
        insertWithIndexHandler.insertIntoIndex(this.tableName,this.getPageNames().get(PageIndex),newTuple);
        if (page.isFull()){
            page.getTuples().insertElementAt(newTuple, insertIndex);
            Tuple lastTuple = page.getTuples().removeLast();

            Comparable minValue = (Comparable) page.getTuples().getFirst().getValue(this.ClusteringKeyColumn);
            Comparable maxValue = (Comparable) page.getTuples().getLast().getValue(this.ClusteringKeyColumn);
            pageIntervals.get(PageIndex)[0] = minValue;
            pageIntervals.get(PageIndex)[1] = maxValue;

            Serializer.serialize(page,pageNames.get(PageIndex));
            for (int j = PageIndex+1 ; j < pageNames.size(); j++){
                Page nextPage = (Page) deserialize(pageNames.get(j));
                insertWithIndexHandler.handleInsertionShifting(this.tableName,pageNames.get(j-1),pageNames.get(j),lastTuple);
                if (! nextPage.isFull()){
                    nextPage.getTuples().addFirst(lastTuple);
                    minValue = (Comparable) nextPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn);
                    maxValue = (Comparable) nextPage.getTuples().getLast().getValue(this.ClusteringKeyColumn);
                    pageIntervals.get(j)[0] = minValue;
                    pageIntervals.get(j)[1] = maxValue;
                    Serializer.serialize(nextPage,pageNames.get(j));
                    return;
                } else {
                    nextPage.getTuples().addFirst(lastTuple);
                    lastTuple = nextPage.getTuples().removeLast();
                    minValue = (Comparable) nextPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn);
                    maxValue = (Comparable) nextPage.getTuples().getLast().getValue(this.ClusteringKeyColumn);
                    pageIntervals.get(j)[0] = minValue;
                    pageIntervals.get(j)[1] = maxValue;
                    Serializer.serialize(nextPage,pageNames.get(j));
                }
            }
            Page newPage = new Page();
            newPage.addTuple(0,lastTuple);
            String newPageName = tableName+(Integer.parseInt(pageNames.getLast().substring(tableName.length()))+1);

            pageNames.add(newPageName);
            Serializer.serialize(newPage,newPageName);

            Comparable[] interval = new Comparable[2];
            interval[0] = (Comparable) newPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn);
            interval[1] = (Comparable) newPage.getTuples().getLast().getValue(this.ClusteringKeyColumn);
            pageIntervals.add(interval);

            insertWithIndexHandler.handleInsertionShifting(this.tableName,pageNames.get(pageNames.size()-2),newPageName,lastTuple);
            return;
        } else {
            page.getTuples().insertElementAt(newTuple, insertIndex);
            Comparable minValue = (Comparable) page.getTuples().getFirst().getValue(this.ClusteringKeyColumn);
            Comparable maxValue = (Comparable) page.getTuples().getLast().getValue(this.ClusteringKeyColumn);
            pageIntervals.get(PageIndex)[0] = minValue;
            pageIntervals.get(PageIndex)[1] = maxValue;
            Serializer.serialize(page,pageNames.get(PageIndex));
            return;
        }
    }


    // Check Column Types
    // Check Uniqueness of Primary Key
    public  boolean isValid(Hashtable<String,Object> htblColNameValue)  { // Check Data Types and Uniqueness of Primary Key
            Hashtable<String,String> ColDataTypes= extractTblCols(this.tableName);
            if(ColDataTypes.size()!=htblColNameValue.size())
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
                    //System.out.println("Data Types are incorrect");
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
            if (page == null) continue;
            sb.append("-Page Name: ").append(pageName).append("\n");
            sb.append(page.toString());
        }
        return sb.toString();
    }

    public void updateTuple(Comparable clusteringKeyValue , Hashtable<String,Object> newValues) throws DBAppException{
        if (pageNames.isEmpty()){
            throw new DBAppException("Tuple is not Found");
        }
        int pageIndex = helper_getPageIndex(this.pageIntervals,clusteringKeyValue);
        Page page = (Page) deserialize(pageNames.get(pageIndex));
     int tupleIndex = helperExistingTuple(page.getTuples(),clusteringKeyValue,this.ClusteringKeyColumn);
        if (tupleIndex == -1){
            return;
        }
        Tuple tuple = page.getTuples().get(tupleIndex);
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

    public void deleteTuple(Hashtable<String,Object> htblColNameValue) throws DBAppException{
        if (pageNames.isEmpty()){
            throw new DBAppException("Tuple is not Found");
        }
        if (htblColNameValue.isEmpty()){
            for (String pageName : pageNames){
                Serializer.deleteFile(pageName);
            }
            pageNames.clear();
            pageIntervals.clear();
            System.out.println("Deleting All Tuples ...");
            return;
        }
        if (! deleteHandler.isValidDeleteQuery(this.tableName,htblColNameValue)){
            throw new DBAppException("Invalid Delete Query");
        }
        Vector<String> targetPages = new Vector<String>();
        Hashtable<String,String> indexedCols = wasIndexMade(this.tableName);
        if (htblColNameValue.get(this.ClusteringKeyColumn) != null){
            targetPages = deleteHandler.deleteWithClusteringKey(this.tableName,htblColNameValue);
        } else {
            if (indexedCols == null || indexedCols.isEmpty()){
                targetPages = deleteHandler.deleteWithNothing(this.tableName,htblColNameValue);
            } else {
                for (String colName : htblColNameValue.keySet()){
                    if (indexedCols.containsKey(colName)){
                        targetPages = deleteHandler.deleteWithIndex((Comparable) htblColNameValue.get(colName),indexedCols.get(colName),htblColNameValue);
                        break;
                    }
                }
                if (targetPages.isEmpty()){
                    targetPages = deleteHandler.deleteWithNothing(this.tableName,htblColNameValue);
                }
            }
        }

        deleteHandler.deleteFromIndexes(targetPages,htblColNameValue,indexedCols);
        // Look through each target Page and Check if the Tuple match if yes delete it
        for (String pageName : targetPages){
            Page page = (Page) deserialize(pageName);
            if(page==null)
                continue;
            page.getTuples().removeIf(tuple -> tuple.isEqual(htblColNameValue));
            int index = pageNames.indexOf(pageName);
            if (page.getTuples().isEmpty()){
                pageNames.remove(pageName);
                pageIntervals.remove(index);
                Serializer.deleteFile(pageName);
            } else {
                Comparable newMinValue = (Comparable) page.getTuples().getFirst().getValue(this.ClusteringKeyColumn);
                Comparable newMaxValue = (Comparable) page.getTuples().getLast().getValue(this.ClusteringKeyColumn);
                pageIntervals.get(index)[0] = newMinValue;
                pageIntervals.get(index)[1] = newMaxValue;
                Serializer.serialize(page,pageName);
            }
        }
        System.out.println("Tuples Deleted Successfully");
    }



    public Vector<String> getPageNames() {
        return pageNames;
    }
    public String getClusteringKeyColumn() {
        return ClusteringKeyColumn;
    }
    public Vector<Comparable[]> getPageIntervals() {
        return pageIntervals;
    }
    public String getPageIntervalsString() {
        StringBuilder sb = new StringBuilder();
        for (Comparable[] interval : pageIntervals){
            sb.append("[").append(interval[0]).append(",").append(interval[1]).append("] ");
        }
        return sb.toString();
    }


}
