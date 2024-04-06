package Utils.Selection;

import DBMain.SQLTerm;

import java.util.Hashtable;

import static Utils.metaFile.wasIndexMade;

public class CanSmartSearch {
    public static SelectionMethods canSmartSearch(SQLTerm[] arrSQLTerms,
                                         String[]  strarrOperators , String clusteringKeyCol , Hashtable<String,String> indexedCols){
        for (String operator : strarrOperators){
            if (! operator.equalsIgnoreCase("AND")){
                return SelectionMethods.LINEAR;
            }
        }
        for (SQLTerm sqlTerm : arrSQLTerms){
            if ( indexedCols.containsKey(sqlTerm._strColumnName) && !sqlTerm._strOperator.equals("!=")){
                return SelectionMethods.INDEX;
            }
            if (sqlTerm._strColumnName.equals(clusteringKeyCol) && !sqlTerm._strOperator.equals("!=")){
                return SelectionMethods.CLUSTERINGKEY;
            }
        }
        return SelectionMethods.LINEAR;
    }
}
