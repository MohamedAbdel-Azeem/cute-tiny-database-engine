package MyCuteSQLParser;

import DBMain.DBApp;
import DBMain.SQLTerm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import static MyCuteSQLParser.MyParseSQL.showSelectValues;

public class Main {
    public static void main(String[] args) {
        String pathName = "src/main/java/MyCuteSQLParser/queries";
        readFromTxtFileMethod(pathName);
    }

    public static void readFromTxtFileMethod(String pathName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathName));
            String line = reader.readLine();
            MyParseSQL myParseSQL = new MyParseSQL();
            while (line != null){
                Iterator result = myParseSQL.parseSQL(new StringBuffer(line));
                System.out.println(result);
                if (result != null) showSelectValues(result);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readFromCLIMethod(){
        try{
            String query = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your SQL Queries, Enter 'exit' to exit.");
            MyParseSQL myParseSQL = new MyParseSQL();
            while (true){
                System.out.print("$ ");
                query = reader.readLine();
                if (query.equals("exit")){
                    break;
                }
                Iterator result = myParseSQL.parseSQL(new StringBuffer(query));
                if (result != null) showSelectValues(result);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
