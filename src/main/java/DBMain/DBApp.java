package DBMain;
/** * @author Wael Abouelsaadat */

import Structures.Table;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import static Utils.Serializer.deserialize;

import static Utils.Serializer.serialize;
import static Utils.metaFile.*;

import Utils.Serializer;
import Utils.bplustree;
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
		String path= "./DB/"+strIndexName+".class.";
		File file =new File(path);

			if (file.exists()) {
				throw new DBAppException("There already exists an index with that name");
		}
			if(wasIndexMade(strTableName).containsKey(strColName))
				throw new DBAppException("There already exists an index for that column");
	}


	// following method inserts one row only. 
	// htblColNameValue must include a value for the primary key
	public void insertIntoTable(String strTableName, 
								Hashtable<String,Object>  htblColNameValue)  {
	
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

		Table myTable = (Table) deserialize(strTableName);
		myTable.deleteTuple(htblColNameValue);
		serialize(myTable,strTableName);


		throw new DBAppException("not implemented yet");
	}


	public Iterator selectFromTable(SQLTerm[] arrSQLTerms, 
									String[]  strarrOperators) throws DBAppException{
										
		return null;
	}


	public static void main( String[] args )  {

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

//		DBApp myDB = new DBApp();
//		myDB.init();
//		try {
//			Class cls = Class.forName("java.lang.String");
//			boolean b1
//					= cls.isInstance("hello");
//			System.out.println(b1);
////			boolean b2 = cls.isInstance(new A());
////			System.out.println(b2);
//		}
//		catch (Throwable e) {
//			System.err.println(e);
//		}
//

		DBApp myDB = new DBApp();
		myDB.init();
//		BPlusTree<Long, String> tree =
//				BPlusTree
//						.file()
//						.directory("./DB/")
//						.maxLeafKeys(32)
//						.maxNonLeafKeys(8)
//						.segmentSizeMB(1)
//						.keySerializer(Serializer.LONG)
//						.valueSerializer(Serializer.utf8())
//						.naturalOrder();
//
//// insert some values
//		tree.insert(1000L, "hello");
//		tree.insert(2000L, "there");
//
//// search the tree for values with keys between 0 and 3000
//// and print out key value pairs
//		tree.findEntries(0L, 3000L).forEach(entry -> System.out.println(entry));
//		bplustree bp=new bplustree(3);
//		HashSet<String> hashSet=new HashSet<String>();
//		hashSet.add("page1");
//		bp.insert(3,hashSet);
////		Serializer.serialize(bp,"trial");
		bplustree bp= (bplustree) Serializer.deserialize("trial");
//		HashSet<String> hashSet=new HashSet<String>();
//		hashSet.add("page3");
		if(bp.search(3)!=null){
			HashSet<String> hashSet1=bp.search(3);
			hashSet1.add("page5");
//			bp.insert(3,hashSet1);
		}
		System.out.println(bp.search(3));


// search the tree for values with keys between 0 and 3000
// and print out values only
		//tree.find(0L,3000L).forEach(entry -> System.out.println(entry));
//		Hashtable htblColNameType = new Hashtable( );
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String");
//		htblColNameType.put("gpa", "java.lang.Double");
//		myDB.createTable( "First_Test", "id", htblColNameType );
//
//		Hashtable htblColNameValue = new Hashtable( );
//		htblColNameValue.put("id", 2343432 );
//		htblColNameValue.put("name", "Abd el satar");
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
////
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", 23498 );
//		htblColNameValue.put("name", "John Noor");
//		htblColNameValue.put("gpa",  "kk" );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id",  45345 );
//		htblColNameValue.put("name", "Zaky Noor");
//		htblColNameValue.put("gpa",  0.88 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		Table first_test = (Table) deserialize("First_Test");
//		System.out.println(first_test);

	}

}