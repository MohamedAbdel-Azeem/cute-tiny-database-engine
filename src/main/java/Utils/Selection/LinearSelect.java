package Utils.Selection;

import DBMain.DBAppException;
import DBMain.SQLTerm;
import Structures.Page;
import Structures.Table;
import Structures.Tuple;

import java.util.Iterator;
import java.util.Vector;

import static Utils.Serializer.deserialize;

public class LinearSelect {
    public static Iterator<Tuple> LinearSelect(SQLTerm[] arrSQLTerms,
                                               String[]  strarrOperators) throws DBAppException {
        String strTableName = arrSQLTerms[0]._strTableName;
        Table myTable = (Table) deserialize(strTableName);
        Vector<Tuple> result = new Vector<>();
        for (String pageName : myTable.getPageNames()){
            Page page = (Page) deserialize(pageName);
            for (Tuple tuple : page.getTuples()){
                if (tuple.satisfySQLConditions(arrSQLTerms, strarrOperators)){
                    result.add(tuple);
                }
            }
        }
        return result.iterator();
    }
}
