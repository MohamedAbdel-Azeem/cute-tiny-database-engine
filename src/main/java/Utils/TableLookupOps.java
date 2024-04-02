package Utils;

import Structures.Page;
import Structures.Tuple;

import java.util.Hashtable;
import java.util.Vector;

import static Utils.Serializer.deserialize;

public class TableLookupOps {

    public static int helperSearchInsert(Vector<Tuple> page, Comparable target , String ClusteringKeyColumn) {
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

    public static boolean helper_TupleInRangePage(Page page, Comparable clusteringKeyValue,String ClusteringKeyColumn){
        return ((Comparable<Comparable>) page.getTuples().getFirst().getValue(ClusteringKeyColumn)).compareTo(clusteringKeyValue) <= 0
                && ((Comparable<Comparable>) page.getTuples().getLast().getValue(ClusteringKeyColumn)).compareTo(clusteringKeyValue) >= 0;
    }


    public static int helper_getPageIndex(Tuple newTuple, Vector<String> pageNames, String ClusteringKeyColumn){
        // Check if the Tuple is having the Min Clustering Key Value
        Page firstPage = (Page) deserialize(pageNames.getFirst());
        if (((Comparable) firstPage.getTuples().getFirst().getValue(ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(ClusteringKeyColumn)) >= 0){
            return 0;
        }
        // Check if the Tuple is having the Max Clustering Key Value
        Page lastPage = (Page) deserialize(pageNames.getLast());
        if (((Comparable) lastPage.getTuples().getLast().getValue(ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(ClusteringKeyColumn)) <= 0){
            return pageNames.size()-1;
        }
        // Performing Binary Search to get the Correct Page to Insert the Tuple
        int HighPageIndex = pageNames.size()-1;
        int LowPageIndex = 0;
        int MidPageIndex = (HighPageIndex+LowPageIndex)/2;
        while (LowPageIndex <= HighPageIndex){
            Page MidPage = (Page) deserialize(pageNames.get(MidPageIndex));
            if (helper_TupleInRangePage(MidPage,(Comparable) newTuple.getValue(ClusteringKeyColumn), ClusteringKeyColumn)){ // Ideal Case Where Tuple is Within Range of the Mid Page
                return MidPageIndex;
            }
            // Check if Clustering Key is less than the Min of the Mid Page but greater than the Max of the Previous Page then return the Mid Page Index
            if (((Comparable) MidPage.getTuples().getFirst().getValue(ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(ClusteringKeyColumn)) > 0){
                Page prevPage = (Page) deserialize(pageNames.get(MidPageIndex-1));
                if (((Comparable) prevPage.getTuples().getLast().getValue(ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(ClusteringKeyColumn)) < 0){
                    return MidPageIndex;
                }
                HighPageIndex = MidPageIndex-1;
                MidPageIndex = (HighPageIndex+LowPageIndex)/2;
                continue;
            }
            // Check if Clustering Key is greater than the Max of the Mid Page but less than the Min of the Next Page then return the next Page Index
            if (((Comparable) MidPage.getTuples().getLast().getValue(ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(ClusteringKeyColumn)) < 0){
                Page nextPage = (Page) deserialize(pageNames.get(MidPageIndex+1));
                if (((Comparable) nextPage.getTuples().getFirst().getValue(ClusteringKeyColumn)).compareTo((Comparable) newTuple.getValue(ClusteringKeyColumn)) > 0){
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


    public static int helper_getPageIndex(Comparable ClusteringKeyValue, Vector<String> pageNames, String ClusteringKeyColumn){
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        htblColNameValue.put(ClusteringKeyColumn, ClusteringKeyValue);
        Tuple adapterTuple = new Tuple(htblColNameValue);
        return helper_getPageIndex(adapterTuple, pageNames, ClusteringKeyColumn);
    }

}
