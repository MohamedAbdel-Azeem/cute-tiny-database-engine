package Utils;

import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class metaFile {

    public static String metaPath = "./DB/metaFile.txt";
    public static void generateMetaDataFile() {
        File file = new File("./DB/metadata.csv");
        if (file.exists()) {
            return;
        } else {
            try{
                file.createNewFile();
                String[] header = {"Table Name", "Column Name", "Column Type", "ClusteringKey", "IndexName", "IndexType"};
                // Create FileWriter and CSVPrinter
                try (FileWriter fileWriter = new FileWriter(file);
                     CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(header))) {} // inserts Table header
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

    public static <CSVReader> void updateOnMetaDataFile(String strTableName, String colName, String indexName, String indexType) {
        String path = "./DB/metadata.csv";
        try (
                Reader reader = new FileReader(path);
                Writer writer = new FileWriter(path, true); // Append mode to avoid overwriting existing data
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)
        ) {
            // Read existing records
            for (CSVRecord csvRecord : csvParser) {
                System.out.println(csvRecord);
            }

            // Add your update logic here

            // Write updated records back to the file
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Hashtable<String, String> htblColNameType = new Hashtable<>();
        htblColNameType.put("column1", "String");
        htblColNameType.put("column2", "Integer");
        updateOnMetaDataFile("tableName", "column1", "indexName", "indexType");
    }


}
