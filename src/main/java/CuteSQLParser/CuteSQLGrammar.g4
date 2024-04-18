grammar CuteSQLGrammar;

cuteSQL
    : createTable
    | createIndex
    | insertIntoTable
    | updateTable
    | deleteFromTable
    | selectFromTable
        EOF;

createTable : CREATE TABLE tableName OpeningParenthesis primaryColumn (Comma columnDef)* ClosingParenthesis SemiColon?;
createIndex : CREATE INDEX indexName ON tableName OpeningParenthesis columnName ClosingParenthesis SemiColon?;
insertIntoTable : INSERT INTO tableName OpeningParenthesis columnName (Comma columnName)* ClosingParenthesis VALUES OpeningParenthesis columnValue (Comma columnValue)* ClosingParenthesis SemiColon?;
updateTable: UPDATE tableName SET OpeningParenthesis columnName Equals columnValue (Comma columnName Equals columnValue )* ClosingParenthesis WHERE clusteringColumnName Equals clusteringColumnValue SemiColon?;
deleteFromTable: DELETE FROM tableName (WHERE OpeningParenthesis columnName Equals columnValue (Comma columnName Equals columnValue)* ClosingParenthesis SemiColon?)?;
selectFromTable: SELECT Star FROM tableName (WHERE condition (starrOperator condition)* SemiColon?)?;

primaryColumn : columnDef PRIMARY KEY;
columnDef : columnType Colon columnName;
tableName : GenericSTRING;
columnName : GenericSTRING;
columnType : STRING | INT | DOUBLE;
indexName : GenericSTRING;
columnValue : StringValue  | INTValue | DOUBLEValue;
clusteringColumnName : GenericSTRING;
clusteringColumnValue : columnValue;
condition : columnName operator columnValue;
operator : Equals | LessThan | GreaterThan | LessThanEquals | GreaterThanEquals | NotEquals;
starrOperator : AND | OR | XOR;

INTValue : [0-9]+;
DOUBLEValue : [0-9]+ '.' [0-9]+;
StringValue : '\'' GenericSTRING '\'';

CREATE: C_ R_ E_ A_ T_ E_ ;
INSERT: I_ N_ S_ E_ R_ T_ ;
INTO: I_ N_ T_ O_ ;
TABLE: T_ A_ B_ L_ E_ ;
PRIMARY: P_ R_ I_ M_ A_ R_ Y_ ;
KEY: K_ E_ Y_ ;
STRING: S_ T_ R_ I_ N_ G_ ;
INT: I_ N_ T_ ;
DOUBLE: D_ O_ U_ B_ L_ E_ ;
INDEX: I_ N_ D_ E_ X_ ;
ON: O_ N_ ;
VALUES: V_ A_ L_ U_ E_ S_ ;
UPDATE: U_ P_ D_ A_ T_ E_ ;
SET: S_ E_ T_ ;
WHERE: W_ H_ E_ R_ E_ ;
DELETE: D_ E_ L_ E_ T_ E_ ;
FROM: F_ R_ O_ M_ ;
SELECT: S_ E_ L_ E_ C_ T_ ;
AND: A_ N_ D_ ;
OR: O_ R_ ;
XOR: X_ O_ R_ ;

OpeningParenthesis : '(' ;
ClosingParenthesis : ')' ;
Comma : ',' ;
SemiColon : ';' ;
Equals : '=' ;
Colon : ':' ;
Star : '*' ;
GreaterThan : '>' ;
GreaterThanEquals : '>=' ;
LessThan : '<' ;
LessThanEquals : '<=' ;
NotEquals : '!=' ;

GenericSTRING : [a-zA-Z]+ ;

// Alphabets
fragment A_: 'a' | 'A';
fragment B_: 'b' | 'B';
fragment C_: 'c' | 'C';
fragment D_: 'd' | 'D';
fragment E_: 'e' | 'E';
fragment F_: 'f' | 'F';
fragment G_: 'g' | 'G';
fragment H_: 'h' | 'H';
fragment I_: 'i' | 'I';
fragment J_: 'j' | 'J';
fragment K_: 'k' | 'K';
fragment L_: 'l' | 'L';
fragment M_: 'm' | 'M';
fragment N_: 'n' | 'N';
fragment O_: 'o' | 'O';
fragment P_: 'p' | 'P';
fragment Q_: 'q' | 'Q';
fragment R_: 'r' | 'R';
fragment S_: 's' | 'S';
fragment T_: 't' | 'T';
fragment U_: 'u' | 'U';
fragment V_: 'v' | 'V';
fragment W_: 'w' | 'W';
fragment X_: 'x' | 'X';
fragment Y_: 'y' | 'Y';
fragment Z_: 'z' | 'Z';


SPACE : [ \t\r\n]+ -> skip ;