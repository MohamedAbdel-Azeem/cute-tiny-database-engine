package DBMain;
/** * @author Wael Abouelsaadat */

import Structures.Table;


import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;

import static Utils.Serializer.deserialize;
import static Utils.metaFile.generateMetaDataFile;
import static Utils.metaFile.appendOnMetaDataFile;

import static Utils.Serializer.serialize;

public class DBApp {



	public DBApp( ){
		
	}

	// this does whatever initialization you would like 
	// or leave it empty if there is no code you want to 
	// execute at application startup 
	public void init( ){
		generateMetaDataFile();
	}


	// following method creates one table only
	// strClusteringKeyColumn is the name of the column that will be the primary
	// key and the clustering column as well. The data type of that column will
	// be passed in htblColNameType
	// htblColNameValue will have the column name as key and the data 
	// type as value
	public void createTable(String strTableName, 
							String strClusteringKeyColumn,  
							Hashtable<String,String> htblColNameType){
		try{
			File file = new File("DB/"+strTableName+".class");
			if (file.exists()){
				throw new DBAppException("Table already exists");
			}
			Table newTable = new Table(strTableName, strClusteringKeyColumn, htblColNameType);
			appendOnMetaDataFile(strTableName,strClusteringKeyColumn,htblColNameType);
			serialize(newTable,strTableName);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}


	// following method creates a B+tree index 
	public void createIndex(String   strTableName,
							String   strColName,
							String   strIndexName) throws DBAppException{

		throw new DBAppException("not implemented yet");
	}


	// following method inserts one row only. 
	// htblColNameValue must include a value for the primary key
	public void insertIntoTable(String strTableName, 
								Hashtable<String,Object>  htblColNameValue){
	
		Table myTable = (Table) deserialize(strTableName);
		myTable.insertTuple(htblColNameValue);
		serialize(myTable,strTableName);
		return;
	}


	// following method updates one row only
	// htblColNameValue holds the key and new value 
	// htblColNameValue will not include clustering key as column name
	// strClusteringKeyValue is the value to look for to find the row to update.
	public void updateTable(String strTableName, 
							String strClusteringKeyValue,
							Hashtable<String,Object> htblColNameValue   )  throws DBAppException{
	
		throw new DBAppException("not implemented yet");
	}


	// following method could be used to delete one or more rows.
	// htblColNameValue holds the key and value. This will be used in search 
	// to identify which rows/tuples to delete. 	
	// htblColNameValue enteries are ANDED together
	public void deleteFromTable(String strTableName, 
								Hashtable<String,Object> htblColNameValue) throws DBAppException{
	
		throw new DBAppException("not implemented yet");
	}


	public Iterator selectFromTable(SQLTerm[] arrSQLTerms, 
									String[]  strarrOperators) throws DBAppException{
										
		return null;
	}


	public static void main( String[] args ){

//	try{
//			String strTableName = "Student";
//			DBMain.DBApp	dbApp = new DBMain.DBApp( );
//
//			Hashtable htblColNameType = new Hashtable( );
//			htblColNameType.put("id", "java.lang.Integer");
//			htblColNameType.put("name", "java.lang.String");
//			htblColNameType.put("gpa", "java.lang.double");
//			dbApp.createTable( strTableName, "id", htblColNameType );
//			dbApp.createIndex( strTableName, "gpa", "gpaIndex" );
//
//			Hashtable htblColNameValue = new Hashtable( );
//			htblColNameValue.put("id", new Integer( 2343432 ));
//			htblColNameValue.put("name", new String("Ahmed Noor" ) );
//			htblColNameValue.put("gpa", new Double( 0.95 ) );
//			dbApp.insertIntoTable( strTableName , htblColNameValue );
//
//			htblColNameValue.clear( );
//			htblColNameValue.put("id", new Integer( 453455 ));
//			htblColNameValue.put("name", new String("Ahmed Noor" ) );
//			htblColNameValue.put("gpa", new Double( 0.95 ) );
//			dbApp.insertIntoTable( strTableName , htblColNameValue );
//
//			htblColNameValue.clear( );
//			htblColNameValue.put("id", new Integer( 5674567 ));
//			htblColNameValue.put("name", new String("Dalia Noor" ) );
//			htblColNameValue.put("gpa", new Double( 1.25 ) );
//			dbApp.insertIntoTable( strTableName , htblColNameValue );
//
//			htblColNameValue.clear( );
//			htblColNameValue.put("id", new Integer( 23498 ));
//			htblColNameValue.put("name", new String("John Noor" ) );
//			htblColNameValue.put("gpa", new Double( 1.5 ) );
//			dbApp.insertIntoTable( strTableName , htblColNameValue );
//
//			htblColNameValue.clear( );
//			htblColNameValue.put("id", new Integer( 78452 ));
//			htblColNameValue.put("name", new String("Zaky Noor" ) );
//			htblColNameValue.put("gpa", new Double( 0.88 ) );
//			dbApp.insertIntoTable( strTableName , htblColNameValue );
//
//
//			DBMain.SQLTerm[] arrSQLTerms;
//			arrSQLTerms = new DBMain.SQLTerm[2];
//			arrSQLTerms[0]._strTableName =  "Student";
//			arrSQLTerms[0]._strColumnName=  "name";
//			arrSQLTerms[0]._strOperator  =  "=";
//			arrSQLTerms[0]._objValue     =  "John Noor";
//
//			arrSQLTerms[1]._strTableName =  "Student";
//			arrSQLTerms[1]._strColumnName=  "gpa";
//			arrSQLTerms[1]._strOperator  =  "=";
//			arrSQLTerms[1]._objValue     =  new Double( 1.5 );
//
//			String[]strarrOperators = new String[1];
//			strarrOperators[0] = "OR";
//			// select * from Student where name = "John Noor" or gpa = 1.5;
//			Iterator resultSet = dbApp.selectFromTable(arrSQLTerms , strarrOperators);
//		}
//		catch(Exception exp){
//			exp.printStackTrace( );
//		}

		DBApp myDB = new DBApp();
		myDB.init();
//
//		Hashtable htblColNameType = new Hashtable( );
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String");
//		htblColNameType.put("gpa", "java.lang.double");
//		myDB.createTable( "First_Test", "id", htblColNameType );
//
//		Hashtable htblColNameValue = new Hashtable( );
//		htblColNameValue.put("id", 2343432 );
//		htblColNameValue.put("name", "Ahmed Noor");
//		htblColNameValue.put("gpa", 0.95 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", 453455 );
//		htblColNameValue.put("name", "Ahmed Noor");
//		htblColNameValue.put("gpa", 0.95);
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", 5674567 );
//		htblColNameValue.put("name", "Dalia Noor");
//		htblColNameValue.put("gpa", 1.25 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", 23498 );
//		htblColNameValue.put("name", "John Noor");
//		htblColNameValue.put("gpa",  1.5 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id",  78452 );
//		htblColNameValue.put("name", "Zaky Noor");
//		htblColNameValue.put("gpa",  0.88 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );

		Table first_test = (Table) deserialize("First_Test");
		System.out.println(first_test);
	}

}