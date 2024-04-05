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
createIndex:'CREATE INDEX ON' tableName '('columnName')';
insertIntoTable: 'INSERT INTO 'tableName '('columnName(','columnName)*')' ' VALUES ''('columnValue(','columnValue)*')';
updateTable: 'UPDATE' tableName 'SET' '(' columnName '=' columnValue (',' columnName '=' columnValue)* ')' 'WHERE' columnName '=' columnValue;
deleteFromTable: 'DELETE' 'FROM' tableName 'WHERE' columnName '=' columnValue;
selectFromTable: 'SELECT' '*' 'FROM' tableName 'WHERE' columnName operator columnValue (starrOperators columnName operator columnValue)*;



primaryColumnDefinition : columnDefinition ' PRIMARY KEY';
columnDefinition : dataType ':' columnName ;
columnName : STRING;
columnValue : INT | DOUBLE | GenericString;
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

