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


    public void updateValues(Hashtable<String,Object> newValue){
        values = newValue;
    }

    public boolean satisfySQLTerm(String strColName , String Operator , Object comparingObject){
        return switch (Operator) {
            case ">" -> ((Comparable) values.get(strColName)).compareTo(comparingObject) > 0 ;
            case "<" -> ((Comparable) values.get(strColName)).compareTo(comparingObject) < 0;
            case "=" -> ((Comparable) values.get(strColName)).compareTo(comparingObject) == 0;
            case ">=" -> ((Comparable) values.get(strColName)).compareTo(comparingObject) >= 0;
            case "<=" -> ((Comparable) values.get(strColName)).compareTo(comparingObject) <= 0;
            case "!=" -> ((Comparable) values.get(strColName)).compareTo(comparingObject) != 0;
            default -> false;
        };
    }



    public boolean satisfySQLConditions(SQLTerm[] sqlTerms, String[] starrOperations) throws DBAppException {
        if (starrOperations.length + 1 != sqlTerms.length) {
            throw new DBAppException("Invalid SQL Operation");
        }

        // Compare the Tuple with all the Conditions
        LinkedList<Boolean> literals = new LinkedList<>();
        for (SQLTerm sqlTerm : sqlTerms) {
            System.out.println(sqlTerm._strColumnName + " " + sqlTerm._strOperator + " " + sqlTerm._objValue+" "+ this.values.get(sqlTerm._strColumnName));
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
                i--;
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
                i--;
            }
        }

        // Remove XOR last
        for (int i = 0; i < starrOperandsList.size() ; i++){
            if (starrOperandsList.get(i).equalsIgnoreCase("XOR")){
                boolean leftOperand = literals.remove(i);
                boolean rightOperand = literals.remove(i);
                literals.add(i, leftOperand ^ rightOperand);
                starrOperandsList.remove(i);
                i--;
            }
        }

        return literals.get(0);

    }

    //ignore if the htblColNameValue has less columns than the Original tuple
    public boolean isEqual(Hashtable<String, Object> htblColNameValue) {
        for (String key : htblColNameValue.keySet()) {
            if (!values.get(key).equals(htblColNameValue.get(key))) {
                return false;
            }
        }
        return true;
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
        return sb.toString();
    }

    public Hashtable<String, Object> getValues() {
        return values;
    }
    public Object getValue(String columnName) {
        return values.get(columnName);
    }

}
