package DBMain;
/** * @author Wael Abouelsaadat */

import Structures.Page;
import Structures.Table;
import Structures.Tuple;

import java.io.File;
import java.util.*;

import static Utils.Selection.CanSmartSearch.canSmartSearch;
import static Utils.Selection.ClusteringKeySelect.ClusteringKeySelect;
import static Utils.Selection.IndexSelect.indexSelect;
import static Utils.Selection.SelectinInputValidator.*;
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
import com.github.davidmoten.guavamini.Lists;

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
			File file = new File("DB/"+strTableName+".class");
			if (file.exists()){
				throw new DBAppException("Table already exists");
			}
			if (!htblColNameType.containsKey(strClusteringKeyColumn)){
				throw new DBAppException("Clustering Key Column does not exist in columns List");
			}
			for (String dataType : htblColNameType.values()){
				if (!dataType.equals("java.lang.Integer") && !dataType.equals("java.lang.String") && !dataType.equals("java.lang.Double")){
					throw new DBAppException("Invalid Data Type");
				}
			}
			Table newTable = new Table(strTableName, strClusteringKeyColumn);
			appendOnMetaDataFile(strTableName,strClusteringKeyColumn,htblColNameType);
			serialize(newTable,strTableName);
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
		File file = new File("DB/"+strTableName+".class");
		if (!file.exists()){
			throw new DBAppException("Table does not exist exists");
		}
		Table myTable = (Table) deserialize(strTableName);
		myTable.insertTuple(htblColNameValue);
		serialize(myTable,strTableName);
	}


	// following method updates one row only
	// htblColNameValue holds the key and new value 
	// htblColNameValue will not include clustering key as column name
	// strClusteringKeyValue is the value to look for to find the row to update.
	public void updateTable(String strTableName, 
							String strClusteringKeyValue,
							Hashtable<String,Object> htblColNameValue   )  throws DBAppException{
			File file = new File("DB/"+strTableName+".class");
			if (!file.exists()){
				throw new DBAppException("Table does not exist exists");
			}

			Table myTable = (Table) deserialize(strTableName);
			if(htblColNameValue.containsKey(myTable.getClusteringKeyColumn())){
				throw new DBAppException("Clustering Key cannot be updated");

			}
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
		File file = new File("DB/"+strTableName+".class");
		if (!file.exists()){
			throw new DBAppException("Table does not exist exists");
		}
		Table myTable = (Table) deserialize(strTableName);
		myTable.deleteTuple(htblColNameValue);
		serialize(myTable,strTableName);
	}


	public Iterator selectFromTable(SQLTerm[] arrSQLTerms, 
									String[]  strarrOperators) throws DBAppException{
		String tablename=arrSQLTerms[0]._strTableName;
		File file = new File("DB/"+tablename+".class");
		if (!file.exists()){
			throw new DBAppException("Table does not exist exists");
		}
		Table myTable = (Table) deserialize(tablename);
			for(int i=1;i<arrSQLTerms.length;i++){
			if(!tablename.equals(arrSQLTerms[i]._strTableName))
				throw new DBAppException("This Engine does not support joins");
		}if(arrSQLTerms.length==1&&arrSQLTerms[0]._strOperator==null)
			{
				Vector<Tuple> result=new Vector<Tuple>();
				Vector<String> pagenames=myTable.getPageNames();
				for(int i=0;i<pagenames.size();i++){
					Page page= (Page) Serializer.deserialize(pagenames.get(i));
					result.addAll(page.getTuples());
				}
				return result.iterator();

			}
			validateInput(arrSQLTerms);

				Hashtable<String,String> indexedCols = metaFile.wasIndexMade(arrSQLTerms[0]._strTableName);
		SelectionMethods selectionMethod = canSmartSearch(arrSQLTerms,strarrOperators,myTable.getClusteringKeyColumn(),indexedCols);
		if (selectionMethod == SelectionMethods.LINEAR){
			return LinearSelect.LinearSelect(arrSQLTerms,strarrOperators,myTable);
		} else if (selectionMethod == SelectionMethods.INDEX){
			return indexSelect(arrSQLTerms,strarrOperators,indexedCols,myTable);
		} else if (selectionMethod == SelectionMethods.CLUSTERINGKEY){
			return ClusteringKeySelect(arrSQLTerms,strarrOperators,myTable.getClusteringKeyColumn(),myTable);
		}
		throw new DBAppException("Invalid Selection Method");

	}


	public static void main( String[] args )  throws DBAppException{
		DBApp myDB = new DBApp();
//		Hashtable htblColNameType = new Hashtable( );
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String");
//		htblColNameType.put("gpa", "java.lang.Double");
//		htblColNameType.put("age", "java.lang.Integer");
//
//		myDB.createTable("First_Test", "id", htblColNameType);
		Hashtable htblColNameValue = new Hashtable( );
//		for (int i=0;i<10;i++){
//			htblColNameValue.clear( );
//			htblColNameValue.put("id", i+1);
//			htblColNameValue.put("name", "Abd el satar");
//			htblColNameValue.put("gpa", 0.8 );
//			htblColNameValue.put("age", 20 );
//			myDB.insertIntoTable( "First_Test" , htblColNameValue );
//
//		}
//		myDB.iniitiazlizTest();

//		htblColNameValue.clear( );

//		for(int i=0;i<10;i++){
//			htblColNameValue.put("gpa", i+0.1);
//			myDB.updateTable("First_Test",i+1+"",htblColNameValue);
//		}
//		myDB.updateTable("First_Test","10",htblColNameValue);


//		myDB.createIndex("First_Test","gpa","gpaIndex");
//		Page page = (Page) deserialize("First_Test0");
//		System.out.println(page);
		Table myTable = (Table) deserialize("First_Test");
		System.out.println(myTable);
//		System.out.println(myTable.getPageIntervalsString());
//		Hashtable htblColNameValue = new Hashtable( );
//		htblColNameValue.put("age",20);
//		myDB.deleteFromTable("First_Test",htblColNameValue);
//		bplustree myTree = (bplustree) deserialize("gpaIndex");
//		myTree.delete(0.2);
//		System.out.println(myTree.search(2.0));
//		htblColNameValue.put("gpa",0.8);
//		myDB.deleteFromTable("First_Test",htblColNameValue);
//		Vector<Integer> numbers=new Vector<Integer>();
//		for(int i=0;i<100;i++){htblColNameValue.clear( );
//		 int id = (int) (Math.random() * 1000 + 1);
//		if(numbers.contains(id))
//		{i--;
//			continue;
//
//		}numbers.add(id);
//		htblColNameValue.put("id", id );
//		htblColNameValue.put("name", "Abd el satar");
//		htblColNameValue.put("gpa", 0.8 );
//		myDB.insertIntoTable( "First_Test" , htblColNameValue );
////		}
//		SQLTerm[] arrSQLTerms;
//		arrSQLTerms= new SQLTerm[3];
//		arrSQLTerms[0]=new SQLTerm();
//		arrSQLTerms[0]._strTableName="First_Test";
//		arrSQLTerms[0]._strOperator="<";
//		arrSQLTerms[0]._strColumnName="gpa";
//		arrSQLTerms[0]._objValue=1.5;
//		arrSQLTerms[1]=new SQLTerm();
//		arrSQLTerms[1]._strTableName="First_Test";
//		arrSQLTerms[1]._strOperator="<";
//		arrSQLTerms[1]._strColumnName="id";
//		arrSQLTerms[1]._objValue=20;
//		arrSQLTerms[2]=new SQLTerm();
//		arrSQLTerms[2]._strTableName="First_Test";
//		arrSQLTerms[2]._strOperator="<=";
//		arrSQLTerms[2]._strColumnName="name";
//		arrSQLTerms[2]._objValue="John Noor";
//		String[] strarrOperators = new String[2];
//		strarrOperators[0] = "XOR";
//		strarrOperators[1] = "and";
//		Iterator<Tuple> result = myDB.selectFromTable( arrSQLTerms,strarrOperators);
//		while (result.hasNext()) {
//			System.out.println("Result Found="+result.next());
//		}
	}

	public void iniitiazlizTest() throws DBAppException {
		Hashtable htblColNameType = new Hashtable( );
		htblColNameType.put("id", "java.lang.Integer");
		htblColNameType.put("name", "java.lang.String");
		htblColNameType.put("gpa", "java.lang.Double");
		htblColNameType.put("age", "java.lang.Integer");

		this.createTable("First_Test", "id", htblColNameType);

//
		Hashtable htblColNameValue = new Hashtable( );
//////
		htblColNameValue.put("id", 23 );
		htblColNameValue.put("name", "Abd el satar");
		htblColNameValue.put("gpa", 0.88 );
		htblColNameValue.put("age", 20 );
		this.insertIntoTable( "First_Test" , htblColNameValue );
//
		htblColNameValue.clear( );
		htblColNameValue.put("id", 10 );
		htblColNameValue.put("name", "Ahmed Noor");
		htblColNameValue.put("gpa", 0.95);
		htblColNameValue.put("age", 22 );
		this.insertIntoTable( "First_Test" , htblColNameValue );
//
		htblColNameValue.clear( );
		htblColNameValue.put("id", 9 );
		htblColNameValue.put("name", "Dalia Noor");
		htblColNameValue.put("gpa", 1.25 );
		htblColNameValue.put("age", 32 );
		this.insertIntoTable( "First_Test" , htblColNameValue );


		htblColNameValue.clear( );
		htblColNameValue.put("id",  7500 );
		htblColNameValue.put("name", "Zaky Noor");
		htblColNameValue.put("gpa",  0.88 );
		htblColNameValue.put("age",  20 );
		this.insertIntoTable( "First_Test" , htblColNameValue );

		htblColNameValue.clear( );
		htblColNameValue.put("id",  5750);
		htblColNameValue.put("name", "John Noor");
		htblColNameValue.put("gpa",  1.5 );
		htblColNameValue.put("age",  25 );
		this.insertIntoTable( "First_Test" , htblColNameValue );

		htblColNameValue.clear( );
		htblColNameValue.put("id",  15);
		htblColNameValue.put("name", "Ahmed Noor");
		htblColNameValue.put("gpa",  1.5 );
		htblColNameValue.put("age",  24 );
		this.insertIntoTable( "First_Test" , htblColNameValue );
////
		htblColNameValue.clear( );
		htblColNameValue.put("id",  18);
		htblColNameValue.put("name", "John Noor");
		htblColNameValue.put("gpa",  1.0 );
		htblColNameValue.put("age",  20 );
		this.insertIntoTable( "First_Test" , htblColNameValue );

		htblColNameValue.clear( );
		htblColNameValue.put("id",  20);
		htblColNameValue.put("name", "John Noor");
		htblColNameValue.put("gpa",  1.0 );
		htblColNameValue.put("age",  28 );
		this.insertIntoTable( "First_Test" , htblColNameValue );



		this.createIndex("First_Test","gpa","gpaIndex");



		htblColNameValue.clear();

	}

}