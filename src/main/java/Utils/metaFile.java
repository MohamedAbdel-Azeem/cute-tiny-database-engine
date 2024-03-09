package Utils;

import java.io.*;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import DBMain.DBAppException;

public class metaFile {

    public static String metaPath = "./DB/metadata.csv";
    public static void generateMetaDataFile() {
        File file = new File(metaPath);
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
        System.out.println("Appending to metadata file");
        Iterator<Map.Entry<String, String>> iterator = htblColNameType.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String isClusteringKey = (strClusteringKeyColumn.equals(entry.getKey())) ? "true" : "false";
            String line =  strTableName + "," + entry.getKey() + "," + entry.getValue() + "," + isClusteringKey + ",null,null" + "\n";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(metaPath, true))) {
                writer.write(line);
            } catch (IOException e) {
                System.err.println("Error appending to the CSV file: " + e.getMessage());
            }
        }

    }

    public static void updateOnMetaDataFile(String inputStrTableName, String inputColName, String inputIndexName, String inputIndexType) throws Exception {
        String newCsvContent="";
        boolean found = false;
        try (
                Reader reader = new FileReader(metaPath);
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
                    found = true;
                }
                newStringRecords.append(tableName + "," + columnName + "," + columnType + "," + clusteringKey + "," + indexName + "," + indexType).append("\n");
            }
            newCsvContent = newStringRecords.toString();
        } catch (IOException e) {
            throw new IOException("MetaData Reading file Exception!");
        }

        if (!found){
            throw new DBAppException("Table or Column not found!");
        }

        try (
                Writer writer = new FileWriter(metaPath); // Append mode to prevent overwriting existing data
            ){
            writer.write(newCsvContent);
        } catch (IOException e) {
            throw new IOException("MetaData Writing file Exception!");
        }

    }

    //extract column name and data type
    public static Hashtable<String,String> extractTblCols(String strTableName){
        Hashtable<String,String> htblColNameType = new Hashtable<>();
        try (
                Reader reader = new FileReader(metaPath);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        ) {
            boolean isFound = false;
            for (CSVRecord csvRecord : csvParser) {
                String tableName = csvRecord.get("Table Name");
                if (isFound && ! tableName.equals(strTableName)){
                    return htblColNameType;
                }
                if (tableName.equals(strTableName)){
                    isFound = true;
                    String columnName = csvRecord.get("Column Name");
                    String columnType = csvRecord.get("Column Type");
                    htblColNameType.put(columnName, columnType);
                }
            }
            return htblColNameType;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Hashtable<String,String> wasIndexMade(String strTableName){
        Hashtable<String,String> indexNamesPerCol = new Hashtable<>();
        try
            (
                Reader reader = new FileReader(metaPath);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                ){
            for (CSVRecord csvRecord : csvParser) {
                String tableName = csvRecord.get("Table Name");
                String columnName = csvRecord.get("Column Name");
                String indexName = csvRecord.get("IndexName");
                if (tableName.equals(strTableName) && ! indexName.equals("null")){
                    indexNamesPerCol.put(columnName,indexName);
                }
            }
            return indexNamesPerCol;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }



    public static void main(String[] args) {
        Hashtable<String,String> htblColNameType = extractTblCols("First_Test");
        System.out.println(htblColNameType);
    }


}
