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

}