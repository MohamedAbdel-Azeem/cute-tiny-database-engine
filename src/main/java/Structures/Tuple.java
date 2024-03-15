package Structures;

import DBMain.DBAppException;
import DBMain.SQLTerm;

import java.io.Serializable;
import java.util.*;

public class Tuple implements Serializable {
    private Hashtable<String,Object> values;

    public Tuple(Hashtable<String,Object> values){
        this.values = values;
    }
    public Object getValue(String columnName) {
        return values.get(columnName);
    }

    public void setValue(String columnName, Object value) {
        values.put(columnName, value);
    }

    public boolean satisfySQLTerm(String strColName , String Operator , Object comparingObject){
        switch (Operator){
            case ">": return ((Comparable)values.get(strColName)).compareTo(comparingObject) > 0;
            case "<": return ((Comparable)values.get(strColName)).compareTo(comparingObject) < 0;
            case "=": return ((Comparable)values.get(strColName)).compareTo(comparingObject) == 0;
            case ">=": return ((Comparable)values.get(strColName)).compareTo(comparingObject) >= 0;
            case "<=": return ((Comparable)values.get(strColName)).compareTo(comparingObject) <= 0;
            case "!=": return ((Comparable)values.get(strColName)).compareTo(comparingObject) != 0;
            default: return false;
        }
    }



    public boolean satisfySQLConditions(SQLTerm[] sqlTerms, String[] starrOperations) throws DBAppException {
        if (starrOperations.length + 1 != sqlTerms.length) {
            throw new DBAppException("Invalid SQL Operation");
        }

        // Compare the Tuple with all the Conditions
        LinkedList<Boolean> literals = new LinkedList<>();
        for (SQLTerm sqlTerm : sqlTerms) {
            literals.addLast(this.satisfySQLTerm(sqlTerm._strColumnName, sqlTerm._strOperator, sqlTerm._objValue));
        }

        LinkedList<String> starrOperandsList = new LinkedList<>(Arrays.asList(starrOperations));

        // Remove And first
        for (int i = 0; i < starrOperandsList.size() ; i++){
            if (starrOperandsList.get(i).equalsIgnoreCase("AND")){
                boolean leftOperand = literals.remove(i);
                boolean rightOperand = literals.remove(i);
                literals.add(i, leftOperand && rightOperand);
                starrOperandsList.remove(i);
            }
        }

        // Remove OR next
        for (int i = 0; i < starrOperandsList.size() ; i++){
            if (starrOperandsList.get(i).equalsIgnoreCase("OR")){
                boolean leftOperand = literals.remove(i);
                //System.out.println(literals+" "+leftOperand);
                boolean rightOperand = literals.remove(i);
                literals.add(i, leftOperand || rightOperand);
                starrOperandsList.remove(i);
            }
        }

        // Remove XOR last
        for (int i = 0; i < starrOperandsList.size() ; i++){
            if (starrOperandsList.get(i).equalsIgnoreCase("XOR")){
                boolean leftOperand = literals.remove(i);
                boolean rightOperand = literals.remove(i);
                literals.add(i, leftOperand ^ rightOperand);
                starrOperandsList.remove(i);
            }
        }

        return literals.get(0);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<String, Object> entry : values.entrySet()) {
            sb.append(entry.getValue()).append(",");
        }
        // Remove the last comma
        if (!values.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString() + "\n";
    }


    public static void main(String[] args) {
        Hashtable<String, Object> htblColNameValue = new Hashtable<>();
        htblColNameValue.put("id", 1);
        htblColNameValue.put("name", "Ahmed");

        Tuple t = new Tuple(htblColNameValue);

        htblColNameValue.clear();
    }

}
