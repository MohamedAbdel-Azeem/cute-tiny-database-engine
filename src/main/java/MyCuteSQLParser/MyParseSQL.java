package MyCuteSQLParser;

import DBMain.DBApp;
import Structures.Tuple;
import gen.CuteSQLGrammarLexer;
import gen.CuteSQLGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Iterator;

public class MyParseSQL {

    DBApp myDBApp;

    public MyParseSQL(){
        myDBApp = new DBApp();
    }

    public Iterator parseSQL(StringBuffer strbufSQL){
        ANTLRInputStream input = new ANTLRInputStream(strbufSQL.toString());
        CuteSQLGrammarLexer lexer = new CuteSQLGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CuteSQLGrammarParser parser = new CuteSQLGrammarParser(tokens);
        ParseTree tree = parser.cuteSQL();
        CuteSQLWalker visitor = new CuteSQLWalker(myDBApp);
        visitor.visit(tree);
        Iterator result = visitor.getResult();
        return result;
    }

    public static void showSelectValues(Iterator<Tuple> result){
        while (result.hasNext()){
            System.out.println(result.next());
        }
    }

}
