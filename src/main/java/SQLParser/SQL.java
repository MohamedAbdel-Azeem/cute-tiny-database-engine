package SQLParser;

import SQLParser.SQLGrammarLexer;
import SQLParser.SQLGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.Scanner;

public class SQL {

    ParseTreeWalker walker;
    SQLWalker myWalker;

    public SQL() {
        walker = new ParseTreeWalker();
        myWalker = SQLWalker.getSQLWalkerInstance();
    }

    public void StartSQLExecution(){
        String query = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your SQL Queries, Enter 'exit' to exit.");
        do {
            System.out.print("$ ");
            query = sc.nextLine();
            if (query.equals("exit")) break;
            ANTLRInputStream antlrInputStream = new ANTLRInputStream(query);
            SQLGrammarLexer lexer = new SQLGrammarLexer(antlrInputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SQLGrammarParser parser = new SQLGrammarParser(tokens);
            walker.walk(myWalker, parser.sqlRule());
        } while (true);
        System.out.println("Exiting...");
    }

}
