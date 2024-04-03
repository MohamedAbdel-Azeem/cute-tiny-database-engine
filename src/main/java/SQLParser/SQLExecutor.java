package SQLParser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class SQLExecutor {
    private SQLGrammarLexer lexer;
    private SQLGrammarParser parser;

    public SQLExecutor() {
        this.lexer = new SQLGrammarLexer(CharStreams.fromString(""));
        this.parser = new SQLGrammarParser(new CommonTokenStream(this.lexer));
    }

    public void execute(String input) {
        this.lexer.setInputStream(CharStreams.fromString(input));
        ParseTree tree = this.parser.startRule();

        execute(tree);
    }

    private void execute(ParseTree tree) {
        if (tree instanceof SQLGrammarParser.CreateTableContext) {
            executeCreateTable((SQLGrammarParser.CreateTableContext) tree);
        } else if (tree instanceof SQLGrammarParser.CreateIndexContext) {
            executeCreateIndex((SQLGrammarParser.CreateIndexContext) tree);
        } else if (tree instanceof SQLGrammarParser.InsertIntoTableContext) {
            executeInsertIntoTable((SQLGrammarParser.InsertIntoTableContext) tree);
        } else if (tree instanceof SQLGrammarParser.UpdateTableContext) {
            executeUpdateTable((SQLGrammarParser.UpdateTableContext) tree);
        } else if (tree instanceof SQLGrammarParser.DeleteFromTableContext) {
            executeDeleteFromTable((SQLGrammarParser.DeleteFromTableContext) tree);
        } else if (tree instanceof SQLGrammarParser.SelectFromTableContext) {
            executeSelectFromTable((SQLGrammarParser.SelectFromTableContext) tree);
        }
    }

    private void executeCreateTable(SQLGrammarParser.CreateTableContext ctx) {
        // Implement your logic for CREATE TABLE here
    }

    private void executeCreateIndex(SQLGrammarParser.CreateIndexContext ctx) {
        // Implement your logic for CREATE INDEX here
    }

    private void executeInsertIntoTable(SQLGrammarParser.InsertIntoTableContext ctx) {
        // Implement your logic for INSERT INTO TABLE here
    }

    private void executeUpdateTable(SQLGrammarParser.UpdateTableContext ctx) {
        // Implement your logic for UPDATE TABLE here
    }

    private void executeDeleteFromTable(SQLGrammarParser.DeleteFromTableContext ctx) {
        // Implement your logic for DELETE FROM TABLE here
    }

    private void executeSelectFromTable(SQLGrammarParser.SelectFromTableContext ctx) {
        // Implement your logic for SELECT FROM TABLE here
    }
}