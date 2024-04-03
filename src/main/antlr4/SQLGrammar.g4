grammar SQLGrammar;

startRule
    : createTable
    | createIndex
    | insertIntoTable
    | updateTable
    | deleteFromTable
    | selectFromTable
     EOF;

createTable: 'CREATE' 'TABLE' tableName '(' primaryColumnDefinition (',' columnDefinition)* ')';
createIndex: 'CREATE' 'INDEX' 'ON' tableName '(' columnName ')';
insertIntoTable: 'INSERT' 'INTO' tableName 'VALUES' '(' columnName (',' columnName)* ')';
updateTable: 'UPDATE' tableName 'SET' '(' columnName '=' columnValue (',' columnName '=' columnValue)* ')' 'WHERE' columnName '=' columnValue;
deleteFromTable: 'DELETE' 'FROM' tableName 'WHERE' columnName '=' columnValue;
selectFromTable: 'SELECT' '*' 'FROM' tableName 'WHERE' columnName operator columnValue (starrOperators columnName operator columnValue)*;

primaryColumnDefinition : columnDefinition 'PRIMARY KEY';
columnDefinition : columnName ':' dataType ;
columnName : STRING;
columnValue : STRING;
dataType : 'INT' | 'DOUBLE' | 'STRING';
operator : '=' | '<' | '>' | '<=' | '>=' | '!=';
starrOperators : 'AND' | 'OR' | 'XOR' ;
tableName : STRING;
STRING : [aA-zZ0-9.]+;

// ... rest of your grammar ...