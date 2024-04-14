package Utils.Selection;

import DBMain.DBAppException;
import DBMain.SQLTerm;
import Structures.Page;
import Structures.Table;
import Structures.Tuple;
import Utils.Serializer;

import java.util.Iterator;
import java.util.Vector;

import static Utils.TableLookupOps.helper_getPageIndex;

public class ClusteringKeySelect {
    public static Iterator<Tuple> ClusteringKeySelect(SQLTerm[] arrSQLTerms, String[]  strarrOperators,
                                                      String clusteringKeyCol, Table myTable) throws DBAppException {
        String operator = "";
        Comparable targetIndex = null;
        for (SQLTerm sqlTerm : arrSQLTerms){
            if (sqlTerm._strColumnName.equals(clusteringKeyCol)){
                operator = sqlTerm._strOperator;
                targetIndex = (Comparable) sqlTerm._objValue;
            }
        }

        int targetPageIndex = helper_getPageIndex(myTable.getPageIntervals(), targetIndex);
        Vector<Tuple> result = new Vector<>();
        if (operator.equals("=")){
            Page targetPage = (Page) Serializer.deserialize(myTable.getPageNames().get(targetPageIndex));
            for (Tuple tuple : targetPage.getTuples()){
                if (tuple.satisfySQLConditions(arrSQLTerms, strarrOperators)){
                    result.add(tuple);
                    return result.iterator();
                }
            }
        }
        if (operator.equals(">=") || operator.equals(">")){
            for (int i = targetPageIndex; i < myTable.getPageNames().size(); i++){
                Page page = (Page) Serializer.deserialize(myTable.getPageNames().get(i));
                for (Tuple tuple : page.getTuples()){
                    if (tuple.satisfySQLConditions(arrSQLTerms, strarrOperators)){
                        result.add(tuple);
                    }
                }
            }
        } else {
            for (int i = targetPageIndex; i >= 0; i--){
                Page page = (Page) Serializer.deserialize(myTable.getPageNames().get(i));
                for (Tuple tuple : page.getTuples()){
                    if (tuple.satisfySQLConditions(arrSQLTerms, strarrOperators)){
                        result.add(tuple);
                    }
                }
            }
        }
        return result.iterator();
    }
}
