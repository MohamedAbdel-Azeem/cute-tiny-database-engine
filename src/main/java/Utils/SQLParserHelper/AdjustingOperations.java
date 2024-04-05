package Utils.SQLParserHelper;

import DBMain.DBAppException;

public class AdjustingOperations {
    public static String getDataTypes(String dataype) throws DBAppException {
        return switch (dataype) {
            case "INT" -> "java.lang.Integer";
            case "STRING" -> "java.lang.String";
            case "DOUBLE" -> "java.lang.Double";
            default -> throw new DBAppException("Invalid data type");
        };
    }

}
