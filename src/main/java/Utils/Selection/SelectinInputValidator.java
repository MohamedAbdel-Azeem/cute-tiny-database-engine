package Utils.Selection;

import DBMain.DBAppException;
import DBMain.SQLTerm;

import java.util.Hashtable;

import static Utils.metaFile.extractTblCols;

public class SelectinInputValidator {
    public static void  validateInput(SQLTerm[] arrSQLTerms) throws DBAppException {
        Hashtable<String,String> ColDataTypes= extractTblCols(arrSQLTerms[0]._strTableName);
        for (SQLTerm sqlTerm : arrSQLTerms){
            if (!ColDataTypes.containsKey(sqlTerm._strColumnName)){
                throw new DBAppException("Column "+sqlTerm._strColumnName+" does not exist in table "+sqlTerm._strTableName);
            }
            if (!ColDataTypes.get(sqlTerm._strColumnName).equals(sqlTerm._objValue.getClass().getName())){
                throw new DBAppException("Column "+sqlTerm._strColumnName+" is of type "+ColDataTypes.get(sqlTerm._strColumnName)+" but value is of type "+sqlTerm._objValue.getClass().getName());
            }
        }
    }
}
