package Utils.Selection;

import DBMain.DBAppException;
import DBMain.SQLTerm;
import Structures.Page;
import Structures.Table;
import Structures.Tuple;
import Utils.Serializer;
import Utils.bplustree;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class IndexSelect {
    public static Iterator<Tuple> indexSelect(SQLTerm[] arrSQLTerms, String[]  strarrOperators,
                                              Hashtable<String,String> indexedCols, Table myTable) throws DBAppException {
        bplustree tree = null;
        int sqlTermIndex = 0;
        for (int i = 0; i < arrSQLTerms.length; i++){
            SQLTerm sqlTerm = arrSQLTerms[i];
            if ( indexedCols.containsKey(sqlTerm._strColumnName) && ! sqlTerm._strOperator.equals("!=")){
                tree = (bplustree) Serializer.deserialize(indexedCols.get(sqlTerm._strColumnName));
                sqlTermIndex = i;
                break;
            }
        }
        Vector<Tuple> result = new Vector<>();
        if (arrSQLTerms[sqlTermIndex]._strOperator.equals("=")){
            Vector<String> targetPageNames = tree.search((Comparable) arrSQLTerms[sqlTermIndex]._objValue);
            for (String pageName : targetPageNames){
                Page page = (Page) Serializer.deserialize(pageName);
                for (Tuple tuple : page.getTuples()){
                    if (tuple.satisfySQLConditions(arrSQLTerms, strarrOperators)){
                        result.add(tuple);
                    }
                }
            }
            return result.iterator();
        }
        if (arrSQLTerms[sqlTermIndex]._strOperator.equals(">=") || arrSQLTerms[sqlTermIndex]._strOperator.equals(">")){
            Vector<Vector<String>> targetPageNames = tree.search((Comparable) arrSQLTerms[sqlTermIndex]._objValue, tree.maxValue);
            Vector<String> flattenedVector = new Vector<>();

            for (Vector<String> innerVector : targetPageNames) {
                flattenedVector.addAll(innerVector);
            }
            for (String pageName : flattenedVector){
                Page page = (Page) Serializer.deserialize(pageName);
                for (Tuple tuple : page.getTuples()){
                    if (tuple.satisfySQLConditions(arrSQLTerms, strarrOperators)){
                        result.add(tuple);
                    }
                }
            }
            return result.iterator();
        } else {
            Vector<Vector<String>> targetPageNames = tree.search(tree.minValue, (Comparable) arrSQLTerms[sqlTermIndex]._objValue);
            Vector<String> flattenedVector = new Vector<>();

            for (Vector<String> innerVector : targetPageNames) {
                flattenedVector.addAll(innerVector);
            }
            for (String pageName : flattenedVector){
                Page page = (Page) Serializer.deserialize(pageName);
                for (Tuple tuple : page.getTuples()){
                    if (tuple.satisfySQLConditions(arrSQLTerms, strarrOperators)){
                        result.add(tuple);
                    }
                }
            }
            return result.iterator();
        }

    }
}
