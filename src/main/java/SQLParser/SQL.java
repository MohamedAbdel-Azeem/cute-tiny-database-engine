package SQLParser;

import gen.SQLParser.SQLGrammarLexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class SQL {

    public static void main(String[] args) {
        String query = "CREATE TABLE student (INT: id PRIMARY KEY)";
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(query);

    }
}
