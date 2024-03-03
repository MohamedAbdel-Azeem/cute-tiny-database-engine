package Structures;

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

}
