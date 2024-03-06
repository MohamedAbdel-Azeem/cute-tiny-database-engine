package Structures;

import DBMain.SQLTerm;

import java.io.Serializable;
import java.util.HashMap;

public class Tuple implements Serializable {
    private HashMap<String,Object> values;

    public Tuple(HashMap<String,Object> values){
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
        HashMap<String, Object> values = new HashMap<>();
        values.put("a", 1);
        values.put("b", 2);
        Tuple tuple = new Tuple(values);
        System.out.println(tuple.satisfySQLTerm("a", "!=", 2));

    }
}
