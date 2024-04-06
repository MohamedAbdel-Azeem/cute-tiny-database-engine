grammar SQLGrammar;

sqlRule
    : createTable
    |  createIndex
    | insertIntoTable
    | updateTable
    | deleteFromTable
    | selectFromTable
    | OTHER
     EOF;

createTable: 'CREATE TABLE 'tableName ' ('primaryColumnDefinition(','columnDefinition)*')';
createIndex:'CREATE INDEX ' indexName ' ON ' tableName '('columnName')';
insertIntoTable: 'INSERT INTO 'tableName '('columnName(','columnName)*')' ' VALUES ''('columnValue(','columnValue)*')';
updateTable: 'UPDATE ' tableName ' SET ' '(' columnName '=' columnValue (',' columnName '=' columnValue)* ')' ' WHERE ' clusteringColumnName '=' clusteringColumnValue;
deleteFromTable: 'DELETE FROM ' tableName ' WHERE ' columnName '=' columnValue (' AND ' columnName '=' columnValue)*;
selectFromTable: 'SELECT * FROM ' tableName ' WHERE ' columnName operator columnValue (starrOperators columnName operator columnValue)*
;



primaryColumnDefinition : columnDefinition ' PRIMARY KEY';
columnDefinition : dataType ':' columnName ;
clusteringColumnName : columnName;
clusteringColumnValue : columnValue;
columnName : STRING;
columnValue : INT | DOUBLE | GenericString;
indexName : STRING;
dataType : 'INT' | 'DOUBLE' | 'STRING';
operator : '=' | '<' | '>' | '<=' | '>=' | '!=';
starrOperators : 'AND' | 'OR' | 'XOR' ;
tableName : STRING;



INT : [0-9]+;
STRING : [aA-zZ0-9]+;
GenericString: '\'' STRING '\'';
DOUBLE : [0-9]+ '.' [0-9]+;
OTHER : . ;
WHITESPACE: (' ' | '\t')+  -> skip;
NEWLINE: ('\r'? '\n' | '\r')+ -> skip;

