package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class metaFile {

    public static String metaPath = "./DB/metaFile.txt";
    public static void generateMetaDataFile() {
        File file = new File("./DB/metadata.csv");
        if (file.exists()) {
            return;
        } else {
            try{
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void appendOnMetaDataFile(String strTableName, String strClusteringKeyColumn, Hashtable<String,String> htblColNameType){
        String path = "./DB/metadata.csv";
        Iterator<Map.Entry<String, String>> iterator = htblColNameType.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String isClusteringKey = (strClusteringKeyColumn.equals(entry.getKey())) ? "true" : "false";
            String line =  strTableName + "," + entry.getKey() + "," + entry.getValue() + "," + isClusteringKey + ",null,null" + "\n";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
                writer.write(line);
            } catch (IOException e) {
                System.err.println("Error appending to the CSV file: " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        Hashtable htblColNameType = new Hashtable( );
        htblColNameType.put("id", "java.lang.Integer");
        htblColNameType.put("name", "java.lang.String");
        htblColNameType.put("gpa", "java.lang.double");
        appendOnMetaDataFile("table1", "id",htblColNameType);
    }


}
