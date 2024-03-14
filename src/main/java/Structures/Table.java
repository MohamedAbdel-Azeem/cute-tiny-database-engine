package Structures;

import Utils.Serializer;
import Utils.bplustree;
import Utils.metaFile;

import java.io.Serializable;
import java.util.*;

import static Utils.Serializer.deserialize;
import static Utils.metaFile.extractTblCols;
import static Utils.metaFile.wasIndexMade;

public class Table implements Serializable {
    private String tableName;
    private String ClusteringKeyColumn;
    private Vector<String> pageNames;
    private HashSet<Object> PKeys;


    public Table(String tableName, String clusteringKeyColumn) {
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.pageNames = new Vector<String>();
        this.PKeys=new HashSet<Object>();
    }

    public void insertTuple(Hashtable<String,Object> htblColNameValue)  {
       System.out.println(this.PKeys);
        if (! this.isValid(htblColNameValue)){
            return;
        }
        Tuple tuple = new Tuple(htblColNameValue);
        Hashtable<String, String> tablenames=extractTblCols(this.tableName);
        Hashtable<String,String> indexedCols=wasIndexMade(this.tableName);
        boolean flag=false;
        if(indexedCols != null){
            flag=true;
        }
        if (pageNames.isEmpty()){
            Page page = new Page();
            page.addTuple(tuple);
            this.PKeys.add(htblColNameValue.get(this.ClusteringKeyColumn));
            String pageName = tableName+pageNames.size();
            Serializer.serialize(page,pageName);
            pageNames.add(pageName);
            return;
        } else {
            for (int i = 0; i < pageNames.size(); i++){ // Handles the 2 Cases of the Page being Not Full and a Page being full not until last pag
                Page currPage = (Page) deserialize(pageNames.get(i));
                if (! currPage.isFull()){
                    this.PKeys.add(htblColNameValue.get(this.ClusteringKeyColumn));
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
            this.PKeys.add(htblColNameValue.get(this.ClusteringKeyColumn));
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
            Object PK=htblColNameValue.get(this.ClusteringKeyColumn);
            //System.out.println(this.PKeys);
            if(this.PKeys.size()!=0&&this.PKeys.contains(PK))
            {System.out.println("Tuple is already inserted");
                return  false;}
            return  true;
    }

  private void insertIntoTree(Comparable key,String indexId,String pageName){

        bplustree bp= (bplustree) Serializer.deserialize(indexId);
      if(bp.search(key)!=null){
          HashSet<String> hashSet1=bp.search(key);
          hashSet1.add(pageName);
      }
      else{
          HashSet<String> hashSet=new HashSet<String>();
          hashSet.add(pageName);
          bp.insert(key,hashSet);
      }
      Serializer.serialize(bp,indexId);

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



    public Vector<String> getPageNames() {
        return pageNames;
    }

}
