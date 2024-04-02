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

//  private void insertIntoTree(Comparable key,String indexId,String pageName){
//
//        bplustree bp= (bplustree) Serializer.deserialize(indexId);
//      if(bp.search(key)!=null){
//          HashSet<String> hashSet1=bp.search(key);
//          hashSet1.add(pageName);
//      }
//      else{
//          HashSet<String> hashSet=new HashSet<String>();
//          hashSet.add(pageName);
//          bp.insert(key,hashSet);
//      }
//      Serializer.serialize(bp,indexId);
//
//  }

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



    public Vector<String> getPageNames() {
        return pageNames;
    }



}
