package Utils;

import DBMain.DBAppException;
import DBMain.SQLTerm;
import Structures.Tuple;

import java.util.Hashtable;

public class SQLEvaluater {

    public static boolean evaluateTuple(SQLTerm[] sqlTerms, String[] starrOperations, Tuple tuple) throws DBAppException {
        if (starrOperations.length + 1 != sqlTerms.length) {
            throw new DBAppException("Invalid SQL Operation");
        }

        boolean overallResult = true;

        // Evaluate AND first
        for (int i = 0; i < starrOperations.length; i++) {
            if (starrOperations[i].equalsIgnoreCase("AND")) {
                boolean leftOperand = tuple.satisfySQLTerm(sqlTerms[i]._strColumnName, sqlTerms[i]._strOperator, sqlTerms[i]._objValue);
                boolean rightOperand = tuple.satisfySQLTerm(sqlTerms[i + 1]._strColumnName, sqlTerms[i + 1]._strOperator, sqlTerms[i + 1]._objValue);
                overallResult = overallResult && (leftOperand && rightOperand);
            }
        }

        // Evaluate OR next
        for (int i = 0; i < starrOperations.length; i++) {
            if (starrOperations[i].equalsIgnoreCase("OR")) {
                boolean leftOperand = tuple.satisfySQLTerm(sqlTerms[i]._strColumnName, sqlTerms[i]._strOperator, sqlTerms[i]._objValue);
                boolean rightOperand = tuple.satisfySQLTerm(sqlTerms[i + 1]._strColumnName, sqlTerms[i + 1]._strOperator, sqlTerms[i + 1]._objValue);
                overallResult = overallResult && (leftOperand || rightOperand);
            }
        }

        // Evaluate XOR last
        for (int i = 0; i < starrOperations.length; i++) {
            if (starrOperations[i].equalsIgnoreCase("XOR")) {
                boolean leftOperand = tuple.satisfySQLTerm(sqlTerms[i]._strColumnName, sqlTerms[i]._strOperator, sqlTerms[i]._objValue);
                boolean rightOperand = tuple.satisfySQLTerm(sqlTerms[i + 1]._strColumnName, sqlTerms[i + 1]._strOperator, sqlTerms[i + 1]._objValue);
                overallResult = overallResult && (leftOperand ^ rightOperand);
            }
        }

        return overallResult;
    }


}
