package DBMain;
/** * @author Wael Abouelsaadat */

import Structures.Page;
import Structures.Table;
import Structures.Tuple;

import java.io.File;
import java.util.*;

import static Utils.Selection.CanSmartSearch.canSmartSearch;
import static Utils.Serializer.deserialize;

import static Utils.Serializer.serialize;

import static Utils.metaFile.*;

import Utils.Selection.SelectionMethods;
import Utils.Serializer;
import Utils.bplustree;
import Utils.insertWithIndexHandler;
import Utils.metaFile;
import Utils.Configurator;
import Utils.Selection.LinearSelect;

public class DBApp {



	public DBApp( ){
		this.init();
	}

	// this does whatever initialization you would like 
	// or leave it empty if there is no code you want to 
	// execute at application startup 
	public void init( ){
		File DBDirectory = new File("DB");
		if (! DBDirectory.exists()){
			DBDirectory.mkdir();
		}
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
							Hashtable<String,String> htblColNameType) throws DBAppException{
		try{
			File file = new File("DB/"+strTableName+".class");
			if (file.exists()){
				throw new DBAppException("Table already exists");
			}
			for (String dataType : htblColNameType.values()){
				if (!dataType.equals("java.lang.Integer") && !dataType.equals("java.lang.String") && !dataType.equals("java.lang.Double")){
					throw new DBAppException("Invalid Data Type");
				}
			}
			Table newTable = new Table(strTableName, strClusteringKeyColumn);
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
		// check if index with that name
		// check there does not index in that column and
		//check if already data exists reinsert them in bplus tree
		// there does not create bplus tree then serialize it and update metafile
		String indexPath= "./DB/"+strIndexName+".class.";
		File file =new File(indexPath);

		if (file.exists())
			throw new DBAppException("There already exists an index with that name");
		if(wasIndexMade(strTableName).containsKey(strColName))
			throw new DBAppException("There already exists an index for that column");

		String tablePath = "./DB/"+strTableName+".class";
		file = new File(tablePath);
		if (!file.exists())
			throw new DBAppException("Table does not exist");

		int m =Configurator.getIndexNodeSize();
		bplustree myTree = new bplustree(m);
		Table myTable = (Table) deserialize(strTableName);
		Serializer.serialize(myTree,strIndexName);
        try {
            metaFile.updateOnMetaDataFile(strTableName,strColName,strIndexName,"B+tree");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (myTable.getPageNames().isEmpty())
			return;
		for (String pageName : myTable.getPageNames()){
			Page page = (Page) deserialize(pageName);
			for (Tuple tuple : page.getTuples()){
				insertWithIndexHandler.insertIntoSingleIndex(strIndexName,(Comparable) tuple.getValue(strColName),pageName);
			}
		}
		return;
	}


	// following method inserts one row only. 
	// htblColNameValue must include a value for the primary key

	// We don't Handle if the table doesn't Exist
	public void insertIntoTable(String strTableName, 
								Hashtable<String,Object>  htblColNameValue)  throws DBAppException{
		try{
			Table myTable = (Table) deserialize(strTableName);
			myTable.insertTuple(htblColNameValue);
			serialize(myTable,strTableName);
			return;
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}


	// following method updates one row only
	// htblColNameValue holds the key and new value 
	// htblColNameValue will not include clustering key as column name
	// strClusteringKeyValue is the value to look for to find the row to update.
	public void updateTable(String strTableName, 
							String strClusteringKeyValue,
							Hashtable<String,Object> htblColNameValue   )  throws DBAppException{
		Table myTable = (Table) deserialize(strTableName);
		Hashtable<String,String> htblColNameType = metaFile.extractTblCols(strTableName);
		String ClusteringKeyType = htblColNameType.get(myTable.getClusteringKeyColumn());
		Comparable ClusteringKeyValueObject;
		switch (ClusteringKeyType){
			case "java.lang.Integer":
				ClusteringKeyValueObject = Integer.parseInt(strClusteringKeyValue);
				break;
			case "java.lang.String":
				ClusteringKeyValueObject = strClusteringKeyValue;
				break;
			case "java.lang.Double":
				ClusteringKeyValueObject = Double.parseDouble(strClusteringKeyValue);
				break;
			default:
				throw new DBAppException("Invalid Data Type");
		}
		myTable.updateTuple(ClusteringKeyValueObject,htblColNameValue);
	}


	// following method could be used to delete one or more rows.
	// htblColNameValue holds the key and value. This will be used in search 
	// to identify which rows/tuples to delete. 	
	// htblColNameValue enteries are ANDED together
	public void deleteFromTable(String strTableName, 
								Hashtable<String,Object> htblColNameValue) throws DBAppException{
		Table myTable = (Table) deserialize(strTableName);
		myTable.deleteTuple(htblColNameValue);
		serialize(myTable,strTableName);
	}


	public Iterator selectFromTable(SQLTerm[] arrSQLTerms, 
									String[]  strarrOperators) throws DBAppException{
		Table myTable = (Table) deserialize(arrSQLTerms[0]._strTableName);
		Hashtable<String,String> indexedCols = metaFile.wasIndexMade(arrSQLTerms[0]._strTableName);
		SelectionMethods selectionMethod = canSmartSearch(arrSQLTerms,strarrOperators,myTable.getClusteringKeyColumn(),indexedCols);
		if (selectionMethod == SelectionMethods.LINEAR){
			return LinearSelect.LinearSelect(arrSQLTerms,strarrOperators);
		} else if (selectionMethod == SelectionMethods.INDEX){
			return null;
		} else if (selectionMethod == SelectionMethods.CLUSTERINGKEY){
			return null;
		}
	}


	public static void main( String[] args )  throws DBAppException{
		DBApp myDB = new DBApp();
//
//		Hashtable htblColNameType = new Hashtable( );
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String");
//		htblColNameType.put("gpa", "java.lang.Double");
//
//		myDB.createTable("First_Test", "id", htblColNameType);
////
		Hashtable htblColNameValue = new Hashtable( );
////
//		htblColNameValue.put("id", 1 );
//		htblColNameValue.put("name", "Abd el satar");
//		htblColNameValue.put("gpa", 0.95 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", 10 );
//		htblColNameValue.put("name", "Ahmed Noor");
//		htblColNameValue.put("gpa", 0.95);
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
////
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", 9 );
//		htblColNameValue.put("name", "Dalia Noor");
//		htblColNameValue.put("gpa", 1.25 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id",  7500 );
//		htblColNameValue.put("name", "Zaky Noor");
//		htblColNameValue.put("gpa",  0.88 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id",  5750);
//		htblColNameValue.put("name", "John Noor");
//		htblColNameValue.put("gpa",  1.5 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id",  15);
//		htblColNameValue.put("name", "Ahmed Noor");
//		htblColNameValue.put("gpa",  1.5 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
////////
//		htblColNameValue.clear( );
//		htblColNameValue.put("id",  18);
//		htblColNameValue.put("name", "John Noor");
//		htblColNameValue.put("gpa",  1.0 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//
//
//		myDB.createIndex("First_Test","gpa","gpaIndex");


//
		htblColNameValue.clear();
		htblColNameValue.put("gpa",  1.5);
		htblColNameValue.put("name", "Ahmed Noor");
//
//
		Table first_test = (Table) deserialize("First_Test");
//		myDB.deleteFromTable("First_Test", htblColNameValue);
		System.out.println(first_test);
//
		bplustree mygpaIndex = (bplustree) deserialize("gpaIndex");
		System.out.println(mygpaIndex.search(1.5));


//		Hashtable<String,Object> htblColNameValue = new Hashtable<>();
//		htblColNameValue.put("id",  10);
//		htblColNameValue.put("name", "Ahmed Noor");
//		htblColNameValue.put("gpa", 0.95);

//		System.out.println(first_test.isValid(htblColNameValue));
//		System.out.println(first_test);
//
//		bplustree myGpaIndex = (bplustree) deserialize("gpaIndex");
//		System.out.println(myGpaIndex.search(1.0));
//		System.out.println(myGpaIndex.search(0.95));

//		htblColNameValue.clear( );
//		htblColNameValue.put("id",  5);
//		htblColNameValue.put("name", "John Noor");
//		htblColNameValue.put("gpa",  1.5 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );

//		Table first_test = (Table) deserialize("First_Test");
//		System.out.println(first_test);
//
//		bplustree myidIndex = (bplustree) deserialize("myIndex");
//		System.out.println(Arrays.toString(myidIndex.getRoot()));

//		SQLTerm[] arrSQLTerms;
//		arrSQLTerms = new SQLTerm[3];
//
//		arrSQLTerms[0] = new SQLTerm();
//		arrSQLTerms[1] = new SQLTerm();
//		arrSQLTerms[2] = new SQLTerm();
//
//		arrSQLTerms[0]._strTableName = "First_Test";
//		arrSQLTerms[0]._strColumnName= "gpa";
//		arrSQLTerms[0]._strOperator = "<";
//		arrSQLTerms[0]._objValue = new Double(1.0);
//		arrSQLTerms[1]._strTableName = "First_Test";
//		arrSQLTerms[1]._strColumnName= "gpa";
//		arrSQLTerms[1]._strOperator = ">";
//		arrSQLTerms[1]._objValue = new Double(0.9);
//		arrSQLTerms[2]._strTableName = "First_Test";
//		arrSQLTerms[2]._strColumnName= "name";
//		arrSQLTerms[2]._strOperator = "=";
//		arrSQLTerms[2]._objValue = "Zaky Noor";
//		String[] strarrOperators = new String[2];
//		strarrOperators[0] = "AND";
//		strarrOperators[1] = "OR";
//
//
////		 Should Output 3 Records , Two with GPA 0.95 and One with GPA 0.88
//		Iterator resultSet = myDB.selectFromTable(arrSQLTerms , strarrOperators);
//		while (resultSet.hasNext()){
//			System.out.print("Record Found: ");
//			System.out.print(resultSet.next());
//		}


	}

}