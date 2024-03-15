package Structures;

import DBMain.DBAppException;
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


    public Table(String tableName, String clusteringKeyColumn) {
        this.tableName = tableName;
        this.ClusteringKeyColumn = clusteringKeyColumn;
        this.pageNames = new Vector<String>();
    }

//    public void insertTuple(Hashtable<String,Object> htblColNameValue)  {
//        if (! this.isValid(htblColNameValue)){
//            return;
//        }
//        Tuple tuple = new Tuple(htblColNameValue);
//        Hashtable<String,String> indexedCols=wasIndexMade(this.tableName);
//        boolean flag=false;
//        if(indexedCols != null){
//            flag=true;
//        }
//        if (pageNames.isEmpty()){
//            Page page = new Page();
//            page.addTuple(tuple);
//            this.PKeys.add(htblColNameValue.get(this.ClusteringKeyColumn));
//            String pageName = tableName+pageNames.size();
//            Serializer.serialize(page,pageName);
//            pageNames.add(pageName);
//            return;
//        } else {
//            for (int i = 0; i < pageNames.size(); i++){ // Handles the 2 Cases of the Page being Not Full and a Page being full not until last pag
//                Page currPage = (Page) deserialize(pageNames.get(i));
//                if (! currPage.isFull()){
//                    this.PKeys.add(htblColNameValue.get(this.ClusteringKeyColumn));
//                    currPage.addTuple(tuple);
//                    Serializer.serialize(currPage,pageNames.get(i));
//                    return;
//                } else {
//                    Tuple newTuple = currPage.getTuples().removeLast();
//                    currPage.addTuple(tuple);
//                    Serializer.serialize(currPage,pageNames.get(i));
//                    tuple = newTuple;
//                }
//            }
//            Page newPage = new Page(); // in Case all the Pages were full till the last page create a new page
//            newPage.addTuple(tuple);
//            String pageName = tableName+pageNames.size();
//            pageNames.add(pageName);
//            Serializer.serialize(newPage,pageName);
//            this.PKeys.add(htblColNameValue.get(this.ClusteringKeyColumn));
//            return;
//        }
//    }


    public void insertTuple(Hashtable<String,Object> htblColNameValue) throws DBAppException {
        if (! this.isValid(htblColNameValue)){
            throw new DBAppException("Invalid Data Types");
        }


        Tuple newTuple = new Tuple(htblColNameValue);
        Hashtable<String,String> indexedCols=wasIndexMade(this.tableName);

        if (pageNames.isEmpty()){ // First Tuple to get Inserted
            Page newPage = new Page();
            newPage.addTuple(0,newTuple);
            String pageName = tableName+pageNames.size();
            pageNames.add(pageName);
            Serializer.serialize(newPage,pageName);
            return;
        }

        for (int i = 0; i < pageNames.size(); i++) {
            String currPageName = pageNames.get(i);
            Page currPage = (Page) deserialize(currPageName);
           if (((Comparable) currPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0) { // Insert if Tuple Clustering Key is within Page Range
               if (currPage.isFull()){
                    Tuple lastTuple = currPage.getTuples().removeLast();
                    int InsertIndex = helperSearchInsert(currPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
                    if (InsertIndex == -1) {
                        throw new DBAppException("Primary Key Violation");
                    }
                    currPage.getTuples().insertElementAt(newTuple, InsertIndex);
                    Serializer.serialize(currPage,pageNames.get(i));
                    for (int j = i+1 ; j < pageNames.size(); j++){
                        Page nextPage = (Page) deserialize(pageNames.get(j));
                        if (! nextPage.isFull()){
                            nextPage.getTuples().addFirst(lastTuple);
                            Serializer.serialize(nextPage,pageNames.get(j));
                            return;
                        } else {
                            lastTuple = nextPage.getTuples().removeLast();
                        }
                    }
                    Page newPage = new Page();
                    newPage.addTuple(0,lastTuple);
                    String newPageName = tableName+pageNames.size();
                    pageNames.add(newPageName);
                    Serializer.serialize(newPage,newPageName);
                    return;
                } else {
                    int InsertIndex = helperSearchInsert(currPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
                    if (InsertIndex == -1) {
                        throw new DBAppException("Primary Key Violation");
                    }
                    currPage.getTuples().insertElementAt(newTuple, InsertIndex);
                    Serializer.serialize(currPage,pageNames.get(i));
                    return;
                }
            } else {
               if (i < pageNames.size()-1){
                   Page nextPage = (Page) deserialize(pageNames.get(i+1));
                   if (((Comparable) nextPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0){
                       if (! currPage.isFull()){
                           currPage.getTuples().addLast(newTuple);
                           Serializer.serialize(currPage,pageNames.get(i));
                           return;
                       }
                   }
               }
           }
        }

        Page lastPage = (Page) deserialize(pageNames.get(pageNames.size()-1));
        if (lastPage.isFull()){
            Page newPage = new Page();
            newPage.addTuple(0,newTuple);
            String newPageName = tableName+pageNames.size();
            pageNames.add(newPageName);
            Serializer.serialize(newPage,newPageName);
            return;
        }

        lastPage.addTuple(lastPage.getTuples().size(),newTuple);
        Serializer.serialize(lastPage,pageNames.get(pageNames.size()-1));
        return;

    }

    public boolean helper_TupleInRangePage(Page page,Comparable clusteringKeyValue){
        return ((Comparable<Comparable>) page.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo(clusteringKeyValue) <= 0
                && ((Comparable<Comparable>) page.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo(clusteringKeyValue) >= 0;
    }

    public int helperSearchInsert(Vector<Tuple> page, Comparable target) {

        int maxIndex = page.size() - 1;
        int minIndex = 0;
        if (target.compareTo(page.get(minIndex).getValue(ClusteringKeyColumn)) < 0) return 0;
        if (target.compareTo(page.get(maxIndex).getValue(ClusteringKeyColumn)) > 0 ) return maxIndex + 1;
        if (target.compareTo(page.get(minIndex).getValue(ClusteringKeyColumn)) == 0 ) return -1;
        if (target.compareTo(page.get(maxIndex).getValue(ClusteringKeyColumn)) == 0 ) return -1;

        while (minIndex < maxIndex){
            if (target.compareTo(page.get(minIndex).getValue(ClusteringKeyColumn)) == 0 ) return -1;
            if (target.compareTo(page.get(maxIndex).getValue(ClusteringKeyColumn)) == 0 ) return -1;
            int half = (maxIndex+minIndex)/2;
            if (target.compareTo(page.get(half).getValue(ClusteringKeyColumn)) == 0 ) return -1;
            if (target.compareTo(page.get(half).getValue(ClusteringKeyColumn)) < 0 ) maxIndex = half;
            if (target.compareTo(page.get(half).getValue(ClusteringKeyColumn)) > 0 ) minIndex = half;
            if (maxIndex == minIndex + 1 && target.compareTo(page.get(maxIndex).getValue(ClusteringKeyColumn)) != 0  && target.compareTo(page.get(minIndex).getValue(ClusteringKeyColumn)) != 0) return maxIndex;
        }

        return 0;

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
            sb.append(page.toString());
        }
        return sb.toString();
    }



    public Vector<String> getPageNames() {
        return pageNames;
    }


    public static void main(String[] args) {
//        Hashtable<String,Object> htblColNameValue1 = new Hashtable<>();
//
//
//        htblColNameValue1.put("Name", "Ahmed");
//        htblColNameValue1.put("Age", 20);
//        htblColNameValue1.put("ID", 1);
//        Tuple t1 = new Tuple(htblColNameValue1);
//
//
//        Hashtable<String,Object> htblColNameValue2 = new Hashtable<>();
//        htblColNameValue2.put("Name", "Ali");
//        htblColNameValue2.put("Age", 25);
//        htblColNameValue2.put("ID", 2);
//        Tuple t2 = new Tuple(htblColNameValue2);
//
//
//        Hashtable<String,Object> htblColNameValue3 = new Hashtable<>();
//        htblColNameValue3.put("Name", "Omar");
//        htblColNameValue3.put("Age", 30);
//        htblColNameValue3.put("ID", 3);
//        Tuple t3 = new Tuple(htblColNameValue3);
//
//
//        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
//        htblColNameValue.put("Name", "Sara");
//        htblColNameValue.put("Age", 35);
//        htblColNameValue.put("ID", 4);
//        Tuple t4 = new Tuple(htblColNameValue);
//
//        Page p1 = new Page();
//        p1.addTuple(0,t1);
//        p1.addTuple(1,t2);
//        p1.addTuple(2,t4);
//        System.out.println(p1);




    }

}
