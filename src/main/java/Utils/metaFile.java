package Utils;

import java.io.*;
import java.util.*;

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

    public static void updateOnMetaDataFile(String inputStrTableName, String inputColName, String inputIndexName, String inputIndexType) {
        String path = "./DB/metadata.csv";
        String newCsvContent="";
        try (
                Reader reader = new FileReader(path);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        ) {
            // Read existing records and put them inside an ArrayList
            StringBuilder newStringRecords = new StringBuilder();
            newStringRecords.append("Table Name,Column Name,Column Type,ClusteringKey,IndexName,IndexType").append("\n"); // Header
            for (CSVRecord csvRecord : csvParser) {
                String tableName = csvRecord.get("Table Name");
                String columnName = csvRecord.get("Column Name");
                String columnType = csvRecord.get("Column Type");
                String clusteringKey = csvRecord.get("ClusteringKey");
                String indexName = csvRecord.get("IndexName");
                String indexType = csvRecord.get("IndexType");
                if (tableName.equals(inputStrTableName) && columnName.equals(inputColName)) {
                    indexName = inputIndexName;
                    indexType = inputIndexType;
                }
                newStringRecords.append(tableName + "," + columnName + "," + columnType + "," + clusteringKey + "," + indexName + "," + indexType).append("\n");
            }
            newCsvContent = newStringRecords.toString();
        } catch (IOException e) {
            System.out.println("Updating the metadata file failed. Problem in Reading File.");
        }

        try (
                Writer writer = new FileWriter(path); // Append mode to prevent overwriting existing data
            ){
            writer.write(newCsvContent);
        } catch (IOException e) {
            System.out.println("Updating the metadata file failed. Problem in Writing updated File.");
        }

    }





    public static void main(String[] args) {
//        Hashtable<String, String> colNameType = new Hashtable<>();
//        colNameType.put("name", "String");
//        colNameType.put("age", "Integer");
//        appendOnMetaDataFile("table2", "name", colNameType);
        updateOnMetaDataFile("table2", "age", "ageIndex", "B+Tree");
    }


}
