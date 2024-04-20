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

    public static int helperExistingTuple(Vector<Tuple> page, Comparable target , String ClusteringKeyColumn) {
        int maxIndex = page.size() - 1;
        int minIndex = 0;
        if (target.compareTo(page.get(minIndex).getValue(ClusteringKeyColumn)) < 0) return -1;
        if (target.compareTo(page.get(maxIndex).getValue(ClusteringKeyColumn)) > 0 ) return -1;
        if (target.compareTo(page.get(minIndex).getValue(ClusteringKeyColumn)) == 0 ) return minIndex;
        if (target.compareTo(page.get(maxIndex).getValue(ClusteringKeyColumn)) == 0 ) return maxIndex;

        while (minIndex < maxIndex-1){
            if (target.compareTo(page.get(minIndex).getValue(ClusteringKeyColumn)) == 0 ) return minIndex;
            if (target.compareTo(page.get(maxIndex).getValue(ClusteringKeyColumn)) == 0 ) return maxIndex;
            int half = (maxIndex+minIndex)/2;
            if (target.compareTo(page.get(half).getValue(ClusteringKeyColumn)) == 0 ) return half;
            if (target.compareTo(page.get(half).getValue(ClusteringKeyColumn)) < 0 ) maxIndex = half;
            if (target.compareTo(page.get(half).getValue(ClusteringKeyColumn)) > 0 ) minIndex = half;
        }

        return -1;
    }

    public static int helper_getPageIndex(Vector<Comparable[]> intervals, Comparable clusteringKeyValue) {
        int low = 0;
        int high = intervals.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Comparable[] interval = intervals.get(mid);
            Comparable min = interval[0];
            Comparable max = interval[1];

            if (clusteringKeyValue.compareTo(min) >= 0 && clusteringKeyValue.compareTo(max) <= 0) {
                return mid;
            } else if (clusteringKeyValue.compareTo(min) < 0) {
                if (mid == 0) {
                    // Special case: clusteringKeyValue is less than the minimum value in the first interval
                    return 0;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // If not in any interval, return the index of the last interval
        return high;
    }


    public static int helper_getPageIndex(Tuple tuple , Vector<Comparable[]> intervals, String ClusteringKeyColumn) {
        return helper_getPageIndex(intervals, (Comparable) tuple.getValue(ClusteringKeyColumn));
    }

}
