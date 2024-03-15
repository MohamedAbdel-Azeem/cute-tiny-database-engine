package Structures;

import DBMain.DBAppException;
import Utils.Serializer;
import Utils.bplustree;

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


//    public void insertTuple(Hashtable<String,Object> htblColNameValue) throws DBAppException {
//        if (! this.isValid(htblColNameValue)){
//            throw new DBAppException("Invalid Data Types");
//        }
//
//
//        Tuple newTuple = new Tuple(htblColNameValue);
//        Hashtable<String,String> indexedCols=wasIndexMade(this.tableName);
//
//        if (pageNames.isEmpty()){ // First Tuple to get Inserted
//            Page newPage = new Page();
//            newPage.addTuple(0,newTuple);
//            String pageName = tableName+pageNames.size();
//            pageNames.add(pageName);
//            Serializer.serialize(newPage,pageName);
//            return;
//        }
//
//        for (int i = 0; i < pageNames.size(); i++) {
//            String currPageName = pageNames.get(i);
//            Page currPage = (Page) deserialize(currPageName);
//           if (((Comparable) currPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0) { // Insert if Tuple Clustering Key is within Page Range
//               if (currPage.isFull()){
//                    Tuple lastTuple = currPage.getTuples().removeLast();
//                    int InsertIndex = helperSearchInsert(currPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
//                    if (InsertIndex == -1) {
//                        throw new DBAppException("Primary Key Violation");
//                    }
//                    currPage.getTuples().insertElementAt(newTuple, InsertIndex);
//                    Serializer.serialize(currPage,pageNames.get(i));
//                    for (int j = i+1 ; j < pageNames.size(); j++){
//                        Page nextPage = (Page) deserialize(pageNames.get(j));
//                        if (! nextPage.isFull()){
//                            nextPage.getTuples().addFirst(lastTuple);
//                            Serializer.serialize(nextPage,pageNames.get(j));
//                            return;
//                        } else {
//                            lastTuple = nextPage.getTuples().removeLast();
//                        }
//                    }
//                    Page newPage = new Page();
//                    newPage.addTuple(0,lastTuple);
//                    String newPageName = tableName+pageNames.size();
//                    pageNames.add(newPageName);
//                    Serializer.serialize(newPage,newPageName);
//                    return;
//                } else {
//                    int InsertIndex = helperSearchInsert(currPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
//                    if (InsertIndex == -1) {
//                        throw new DBAppException("Primary Key Violation");
//                    }
//                    currPage.getTuples().insertElementAt(newTuple, InsertIndex);
//                    Serializer.serialize(currPage,pageNames.get(i));
//                    return;
//                }
//            } else {
//               if (i < pageNames.size()-1){
//                   Page nextPage = (Page) deserialize(pageNames.get(i+1));
//                   if (((Comparable) nextPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0){
//                       if (! currPage.isFull()){
//                           currPage.getTuples().addLast(newTuple);
//                           Serializer.serialize(currPage,pageNames.get(i));
//                           return;
//                       }
//                   }
//               }
//           }
//        }
//
//        Page lastPage = (Page) deserialize(pageNames.get(pageNames.size()-1));
//        if (lastPage.isFull()){
//            Page newPage = new Page();
//            newPage.addTuple(0,newTuple);
//            String newPageName = tableName+pageNames.size();
//            pageNames.add(newPageName);
//            Serializer.serialize(newPage,newPageName);
//            return;
//        }
//
//        lastPage.addTuple(lastPage.getTuples().size(),newTuple);
//        Serializer.serialize(lastPage,pageNames.get(pageNames.size()-1));
//        return;
//
//    }

//    public void insertTuple(Hashtable<String,Object> htblColNameValue) throws DBAppException {
//        if (! this.isValid(htblColNameValue)){
//            throw new DBAppException("Invalid Tuple Data Types");
//        }
//        Tuple newTuple = new Tuple(htblColNameValue);
//        Hashtable<String,String> indexedCols=wasIndexMade(this.tableName);
//        if (pageNames.isEmpty()){ // First Tuple to get Inserted
//            Page newPage = new Page();
//            newPage.addTuple(0,newTuple);
//            String pageName = tableName+pageNames.size();
//            pageNames.add(pageName);
//            Serializer.serialize(newPage,pageName);
//            return;
//        }
//        int HighPageIndex = pageNames.size()-1;
//        int LowPageIndex = 0;
//        int MidPageIndex = (HighPageIndex+LowPageIndex)/2;
//        // Check if the Tuple is having the Min Clustering Key Value
//        Page firstPage = (Page) deserialize(pageNames.getFirst());
//        if (((Comparable) firstPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) >= 0){
//            if (firstPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn).equals(newTuple.getValue(this.ClusteringKeyColumn))){
//                throw new DBAppException("Primary Key Violation");
//            }
//            if (firstPage.isFull()){
//                Tuple lastTuple = firstPage.getTuples().removeLast();
//                firstPage.addTuple(0,newTuple);
//                Serializer.serialize(firstPage,pageNames.getFirst());
//                for (int i = 1 ; i < pageNames.size(); i++){
//                    Page nextPage = (Page) deserialize(pageNames.get(i));
//                    if (! nextPage.isFull()){
//                        nextPage.getTuples().addFirst(lastTuple);
//                        Serializer.serialize(nextPage,pageNames.get(i));
//                        return;
//                    } else {
//                        nextPage.getTuples().addFirst(lastTuple);
//                        lastTuple = nextPage.getTuples().removeLast();
//                        Serializer.serialize(nextPage,pageNames.get(i));
//                    }
//                }
//                Page newPage = new Page();
//                newPage.addTuple(0,lastTuple);
//                String newPageName = tableName+pageNames.size();
//                pageNames.add(newPageName);
//                Serializer.serialize(newPage,newPageName);
//                return;
//            }
//            // first page not full
//            firstPage.addTuple(0,newTuple);
//            Serializer.serialize(firstPage,pageNames.getFirst());
//            return;
//        }
//        // Check if the Tuple is having the Max Clustering Key Value
//        Page lastPage = (Page) deserialize(pageNames.get(pageNames.size()-1));
//        if (((Comparable) lastPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) <= 0){
//            if (lastPage.getTuples().getLast().getValue(this.ClusteringKeyColumn).equals(newTuple.getValue(this.ClusteringKeyColumn))){
//                throw new DBAppException("Primary Key Violation");
//            }
//            if (lastPage.isFull()){
//                Page newPage = new Page();
//                newPage.addTuple(0,newTuple);
//                String newPageName = tableName+pageNames.size();
//                pageNames.add(newPageName);
//                Serializer.serialize(newPage,newPageName);
//                return;
//            }
//            lastPage.addTuple(lastPage.getTuples().size(),newTuple);
//            Serializer.serialize(lastPage,pageNames.getLast());
//            return;
//        }
//        // Performing Binary Search to get the Correct Page to Insert the Tuple
//        while (LowPageIndex <= HighPageIndex){
//            Page MidPage = (Page) deserialize(pageNames.get(MidPageIndex));
//            if (helper_TupleInRangePage(MidPage,(Comparable) newTuple.getValue(ClusteringKeyColumn))){ // Ideal Case Where Tuple is Within Range of the Mid Page
//                if (MidPage.isFull()){
//                    Tuple lastTuple = MidPage.getTuples().removeLast();
//                    int InsertIndex = helperSearchInsert(MidPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
//                    if (InsertIndex == -1 || lastTuple.getValue(this.ClusteringKeyColumn).equals(newTuple.getValue(this.ClusteringKeyColumn))) {
//                        MidPage.getTuples().addLast(lastTuple);
//                        throw new DBAppException("Primary Key Violation");
//                    }
//                    MidPage.getTuples().insertElementAt(newTuple, InsertIndex);
//                    Serializer.serialize(MidPage,pageNames.get(MidPageIndex));
//                    for (int j = MidPageIndex+1 ; j < pageNames.size(); j++){
//                        Page nextPage = (Page) deserialize(pageNames.get(j));
//                        if (! nextPage.isFull()){
//                            nextPage.getTuples().addFirst(lastTuple);
//                            Serializer.serialize(nextPage,pageNames.get(j));
//                            return;
//                        } else {
//                            nextPage.getTuples().addFirst(lastTuple);
//                            lastTuple = nextPage.getTuples().removeLast();
//                            Serializer.serialize(nextPage,pageNames.get(j));
//                        }
//                    }
//                    Page newPage = new Page();
//                    newPage.addTuple(0,lastTuple);
//                    String newPageName = tableName+pageNames.size();
//                    pageNames.add(newPageName);
//                    Serializer.serialize(newPage,newPageName);
//                } else {
//                    int InsertIndex = helperSearchInsert(MidPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
//                    if (InsertIndex == -1) {
//                        throw new DBAppException("Primary Key Violation");
//                    }
//                    MidPage.getTuples().insertElementAt(newTuple, InsertIndex);
//                    Serializer.serialize(MidPage,pageNames.get(MidPageIndex));
//                }
//                return;
//            }
//            if (((Comparable) MidPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) < 0){ // Bigger than Mid Page Greater Index -> 2 Cases will Result!
//                // Case 2 & 3 Where a Next Page Exists , Case 1 where inserting Max Clustering Key is Handled before loop
//                    Page nextPage = (Page) deserialize(pageNames.get(MidPageIndex+1));
//                    if (((Comparable) nextPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) == 0){
//                        throw new DBAppException("Primary Key Violation");
//                    }
//                    if (((Comparable) nextPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0){ // Case 2 -> my Key is Smaller than the min of the Next Page , we Insert in Mid Page
//                        int insertIndex = helperSearchInsert(MidPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
//                        if (insertIndex == -1) {
//                            throw new DBAppException("Primary Key Violation");
//                        }
//                        if (! MidPage.isFull()){
//                            MidPage.getTuples().insertElementAt(newTuple, insertIndex);
//                            Serializer.serialize(MidPage,pageNames.get(MidPageIndex));
//                            return;
//                        } else {
//                            if (!nextPage.isFull()){
//                                nextPage.getTuples().addFirst(newTuple);
//                                Serializer.serialize(nextPage,pageNames.get(MidPageIndex+1));
//                            } else {
//                                Tuple lastTuple = nextPage.getTuples().removeLast();
//                                nextPage.getTuples().insertElementAt(newTuple, 0);
//                                Serializer.serialize(nextPage,pageNames.get(MidPageIndex));
//                                for (int j = MidPageIndex+2 ; j < pageNames.size(); j++){
//                                    Page nextNextPage = (Page) deserialize(pageNames.get(j));
//                                    if (! nextNextPage.isFull()){
//                                        nextNextPage.getTuples().addFirst(lastTuple);
//                                        Serializer.serialize(nextNextPage,pageNames.get(j));
//                                        return;
//                                    } else {
//                                        nextNextPage.getTuples().addFirst(lastTuple);
//                                        lastTuple = nextNextPage.getTuples().removeLast();
//                                        Serializer.serialize(nextNextPage,pageNames.get(j));
//                                    }
//                                }
//                                Page newPage = new Page();
//                                newPage.addTuple(0,lastTuple);
//                                String newPageName = tableName+pageNames.size();
//                                pageNames.add(newPageName);
//                                Serializer.serialize(newPage,newPageName);
//                            }
//                            return;
//                        }
//                    } else{ // Case 3 -> my Key is Bigger than the min of the Next Page , we re-calculate the Mid Page Index
//                        LowPageIndex = MidPageIndex+1;
//                        MidPageIndex = (HighPageIndex+LowPageIndex)/2;
//                    }
//            }
//            if (((Comparable) MidPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0){ // Smaller than Mid Page Smaller Index -> 3 Cases will Result!
//                // Case 2 & 3 Where a Previous Page Exists , Case 1 where inserting Min Clustering Key is handled before loop
//                Page prevPage = (Page) deserialize(pageNames.get(MidPageIndex-1));
//                if (((Comparable) prevPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) < 0){ // Case 2 -> my Key is Greater than the max of the prev Page , we Insert in Mid Page
//                    int insertIndex = helperSearchInsert(MidPage.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
//                    if (insertIndex == -1) {
//                        throw new DBAppException("Primary Key Violation");
//                    }
//                    if (! MidPage.isFull()){ // Will Shift
//                        MidPage.getTuples().insertElementAt(newTuple, insertIndex);
//                        Serializer.serialize(MidPage,pageNames.get(MidPageIndex));
//                    } else {
//                        Tuple lastTuple = MidPage.getTuples().removeLast();
//                        MidPage.getTuples().insertElementAt(newTuple, insertIndex);
//                        Serializer.serialize(MidPage,pageNames.get(MidPageIndex));
//                        for (int j = MidPageIndex+1 ; j < pageNames.size(); j++){
//                            Page nextPage = (Page) deserialize(pageNames.get(j));
//                            if (! nextPage.isFull()){
//                                nextPage.getTuples().addFirst(lastTuple);
//                                Serializer.serialize(nextPage,pageNames.get(j));
//                                return;
//                            } else {
//                                nextPage.getTuples().addFirst(lastTuple);
//                                lastTuple = nextPage.getTuples().removeLast();
//                                Serializer.serialize(nextPage,pageNames.get(j));
//                            }
//                        }
//                        Page newPage = new Page();
//                        newPage.addTuple(0,lastTuple);
//                        String newPageName = tableName+pageNames.size();
//                        pageNames.add(newPageName);
//                        Serializer.serialize(newPage,newPageName);
//                    }
//                    return;
//                } else{ // Case 3 -> my Key is Bigger than the min of the Next Page , we re-calculate the Mid Page Index
//                    HighPageIndex = MidPageIndex-1;
//                    MidPageIndex = (HighPageIndex+LowPageIndex)/2;
//                }
//            }
//        }
//    }

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
            return;
        }

        int PageIndex = helper_getPageIndex(newTuple);
        Page page = (Page) deserialize(pageNames.get(PageIndex));
        int insertIndex = helperSearchInsert(page.getTuples(), (Comparable) htblColNameValue.get(this.ClusteringKeyColumn));
        if (insertIndex == -1) {
            throw new DBAppException("Primary Key Violation");
        }
        if (page.isFull()){
            Tuple lastTuple = page.getTuples().removeLast();
            page.getTuples().insertElementAt(newTuple, insertIndex);
            Serializer.serialize(page,pageNames.get(PageIndex));
            for (int j = PageIndex+1 ; j < pageNames.size(); j++){
                Page nextPage = (Page) deserialize(pageNames.get(j));
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
            return;
        } else {
            page.getTuples().insertElementAt(newTuple, insertIndex);
            Serializer.serialize(page,pageNames.get(PageIndex));
            return;
        }
    }

    public int helper_getPageIndex(Tuple newTuple){
        // Check if the Tuple is having the Min Clustering Key Value
        Page firstPage = (Page) deserialize(pageNames.getFirst());
        if (((Comparable) firstPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) >= 0){
            return 0;
        }
        // Check if the Tuple is having the Max Clustering Key Value
        Page lastPage = (Page) deserialize(pageNames.getLast());
        if (((Comparable) lastPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) <= 0){
            return pageNames.size()-1;
        }
        // Performing Binary Search to get the Correct Page to Insert the Tuple
        int HighPageIndex = pageNames.size()-1;
        int LowPageIndex = 0;
        int MidPageIndex = (HighPageIndex+LowPageIndex)/2;
        while (LowPageIndex <= HighPageIndex){
            Page MidPage = (Page) deserialize(pageNames.get(MidPageIndex));
            if (helper_TupleInRangePage(MidPage,(Comparable) newTuple.getValue(ClusteringKeyColumn))){ // Ideal Case Where Tuple is Within Range of the Mid Page
                return MidPageIndex;
            }
            // Check if Clustering Key is less than the Min of the Mid Page but greater than the Max of the Previous Page then return the Mid Page Index
            if (((Comparable) MidPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0){
                Page prevPage = (Page) deserialize(pageNames.get(MidPageIndex-1));
                if (((Comparable) prevPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) < 0){
                    return MidPageIndex;
                }
                HighPageIndex = MidPageIndex-1;
                MidPageIndex = (HighPageIndex+LowPageIndex)/2;
                continue;
            }
            // Check if Clustering Key is greater than the Max of the Mid Page but less than the Min of the Next Page then return the next Page Index
            if (((Comparable) MidPage.getTuples().getLast().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) < 0){
                Page nextPage = (Page) deserialize(pageNames.get(MidPageIndex+1));
                if (((Comparable) nextPage.getTuples().getFirst().getValue(this.ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(this.ClusteringKeyColumn)) > 0){
                    return MidPageIndex+1;
                }
                LowPageIndex = MidPageIndex+1;
                MidPageIndex = (HighPageIndex+LowPageIndex)/2;
                continue;
            }
            LowPageIndex = MidPageIndex-1;
            MidPageIndex = (HighPageIndex+LowPageIndex)/2;
            continue;
        }
        return MidPageIndex;
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
            sb.append("-Page Name: ").append(pageName).append("\n");
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
