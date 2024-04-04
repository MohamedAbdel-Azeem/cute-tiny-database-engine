// Generated from C:/Users/moham/OneDrive/Desktop/cute-tiny-database-engine/src/main/java/SQLParser/SQLGrammar.g4 by ANTLR 4.13.1
package gen.SQLParser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SQLGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		STRING=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "STRING"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'CREATE'", "'TABLE'", "'('", "','", "')'", "'INDEX'", "'ON'", 
			"'INSERT'", "'INTO'", "'VALUES'", "'UPDATE'", "'SET'", "'='", "'WHERE'", 
			"'DELETE'", "'FROM'", "'SELECT'", "'*'", "'PRIMARY KEY'", "':'", "'INT'", 
			"'DOUBLE'", "'STRING'", "'<'", "'>'", "'<='", "'>='", "'!='", "'AND'", 
			"'OR'", "'XOR'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "STRING"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SQLGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SQLGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000 \u00d5\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001f\u0004\u001f\u00d2\b\u001f\u000b\u001f\f\u001f\u00d3\u0000"+
		"\u0000 \u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? \u0001"+
		"\u0000\u0001\u0003\u0000..09Az\u00d5\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000"+
		"-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001"+
		"\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000"+
		"\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000"+
		";\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001"+
		"\u0000\u0000\u0000\u0001A\u0001\u0000\u0000\u0000\u0003H\u0001\u0000\u0000"+
		"\u0000\u0005N\u0001\u0000\u0000\u0000\u0007P\u0001\u0000\u0000\u0000\t"+
		"R\u0001\u0000\u0000\u0000\u000bT\u0001\u0000\u0000\u0000\rZ\u0001\u0000"+
		"\u0000\u0000\u000f]\u0001\u0000\u0000\u0000\u0011d\u0001\u0000\u0000\u0000"+
		"\u0013i\u0001\u0000\u0000\u0000\u0015p\u0001\u0000\u0000\u0000\u0017w"+
		"\u0001\u0000\u0000\u0000\u0019{\u0001\u0000\u0000\u0000\u001b}\u0001\u0000"+
		"\u0000\u0000\u001d\u0083\u0001\u0000\u0000\u0000\u001f\u008a\u0001\u0000"+
		"\u0000\u0000!\u008f\u0001\u0000\u0000\u0000#\u0096\u0001\u0000\u0000\u0000"+
		"%\u0098\u0001\u0000\u0000\u0000\'\u00a4\u0001\u0000\u0000\u0000)\u00a6"+
		"\u0001\u0000\u0000\u0000+\u00aa\u0001\u0000\u0000\u0000-\u00b1\u0001\u0000"+
		"\u0000\u0000/\u00b8\u0001\u0000\u0000\u00001\u00ba\u0001\u0000\u0000\u0000"+
		"3\u00bc\u0001\u0000\u0000\u00005\u00bf\u0001\u0000\u0000\u00007\u00c2"+
		"\u0001\u0000\u0000\u00009\u00c5\u0001\u0000\u0000\u0000;\u00c9\u0001\u0000"+
		"\u0000\u0000=\u00cc\u0001\u0000\u0000\u0000?\u00d1\u0001\u0000\u0000\u0000"+
		"AB\u0005C\u0000\u0000BC\u0005R\u0000\u0000CD\u0005E\u0000\u0000DE\u0005"+
		"A\u0000\u0000EF\u0005T\u0000\u0000FG\u0005E\u0000\u0000G\u0002\u0001\u0000"+
		"\u0000\u0000HI\u0005T\u0000\u0000IJ\u0005A\u0000\u0000JK\u0005B\u0000"+
		"\u0000KL\u0005L\u0000\u0000LM\u0005E\u0000\u0000M\u0004\u0001\u0000\u0000"+
		"\u0000NO\u0005(\u0000\u0000O\u0006\u0001\u0000\u0000\u0000PQ\u0005,\u0000"+
		"\u0000Q\b\u0001\u0000\u0000\u0000RS\u0005)\u0000\u0000S\n\u0001\u0000"+
		"\u0000\u0000TU\u0005I\u0000\u0000UV\u0005N\u0000\u0000VW\u0005D\u0000"+
		"\u0000WX\u0005E\u0000\u0000XY\u0005X\u0000\u0000Y\f\u0001\u0000\u0000"+
		"\u0000Z[\u0005O\u0000\u0000[\\\u0005N\u0000\u0000\\\u000e\u0001\u0000"+
		"\u0000\u0000]^\u0005I\u0000\u0000^_\u0005N\u0000\u0000_`\u0005S\u0000"+
		"\u0000`a\u0005E\u0000\u0000ab\u0005R\u0000\u0000bc\u0005T\u0000\u0000"+
		"c\u0010\u0001\u0000\u0000\u0000de\u0005I\u0000\u0000ef\u0005N\u0000\u0000"+
		"fg\u0005T\u0000\u0000gh\u0005O\u0000\u0000h\u0012\u0001\u0000\u0000\u0000"+
		"ij\u0005V\u0000\u0000jk\u0005A\u0000\u0000kl\u0005L\u0000\u0000lm\u0005"+
		"U\u0000\u0000mn\u0005E\u0000\u0000no\u0005S\u0000\u0000o\u0014\u0001\u0000"+
		"\u0000\u0000pq\u0005U\u0000\u0000qr\u0005P\u0000\u0000rs\u0005D\u0000"+
		"\u0000st\u0005A\u0000\u0000tu\u0005T\u0000\u0000uv\u0005E\u0000\u0000"+
		"v\u0016\u0001\u0000\u0000\u0000wx\u0005S\u0000\u0000xy\u0005E\u0000\u0000"+
		"yz\u0005T\u0000\u0000z\u0018\u0001\u0000\u0000\u0000{|\u0005=\u0000\u0000"+
		"|\u001a\u0001\u0000\u0000\u0000}~\u0005W\u0000\u0000~\u007f\u0005H\u0000"+
		"\u0000\u007f\u0080\u0005E\u0000\u0000\u0080\u0081\u0005R\u0000\u0000\u0081"+
		"\u0082\u0005E\u0000\u0000\u0082\u001c\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0005D\u0000\u0000\u0084\u0085\u0005E\u0000\u0000\u0085\u0086\u0005L"+
		"\u0000\u0000\u0086\u0087\u0005E\u0000\u0000\u0087\u0088\u0005T\u0000\u0000"+
		"\u0088\u0089\u0005E\u0000\u0000\u0089\u001e\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0005F\u0000\u0000\u008b\u008c\u0005R\u0000\u0000\u008c\u008d\u0005"+
		"O\u0000\u0000\u008d\u008e\u0005M\u0000\u0000\u008e \u0001\u0000\u0000"+
		"\u0000\u008f\u0090\u0005S\u0000\u0000\u0090\u0091\u0005E\u0000\u0000\u0091"+
		"\u0092\u0005L\u0000\u0000\u0092\u0093\u0005E\u0000\u0000\u0093\u0094\u0005"+
		"C\u0000\u0000\u0094\u0095\u0005T\u0000\u0000\u0095\"\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0005*\u0000\u0000\u0097$\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0005P\u0000\u0000\u0099\u009a\u0005R\u0000\u0000\u009a\u009b\u0005"+
		"I\u0000\u0000\u009b\u009c\u0005M\u0000\u0000\u009c\u009d\u0005A\u0000"+
		"\u0000\u009d\u009e\u0005R\u0000\u0000\u009e\u009f\u0005Y\u0000\u0000\u009f"+
		"\u00a0\u0005 \u0000\u0000\u00a0\u00a1\u0005K\u0000\u0000\u00a1\u00a2\u0005"+
		"E\u0000\u0000\u00a2\u00a3\u0005Y\u0000\u0000\u00a3&\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a5\u0005:\u0000\u0000\u00a5(\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0005I\u0000\u0000\u00a7\u00a8\u0005N\u0000\u0000\u00a8\u00a9\u0005"+
		"T\u0000\u0000\u00a9*\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005D\u0000"+
		"\u0000\u00ab\u00ac\u0005O\u0000\u0000\u00ac\u00ad\u0005U\u0000\u0000\u00ad"+
		"\u00ae\u0005B\u0000\u0000\u00ae\u00af\u0005L\u0000\u0000\u00af\u00b0\u0005"+
		"E\u0000\u0000\u00b0,\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005S\u0000"+
		"\u0000\u00b2\u00b3\u0005T\u0000\u0000\u00b3\u00b4\u0005R\u0000\u0000\u00b4"+
		"\u00b5\u0005I\u0000\u0000\u00b5\u00b6\u0005N\u0000\u0000\u00b6\u00b7\u0005"+
		"G\u0000\u0000\u00b7.\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005<\u0000"+
		"\u0000\u00b90\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005>\u0000\u0000\u00bb"+
		"2\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005<\u0000\u0000\u00bd\u00be\u0005"+
		"=\u0000\u0000\u00be4\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005>\u0000"+
		"\u0000\u00c0\u00c1\u0005=\u0000\u0000\u00c16\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c3\u0005!\u0000\u0000\u00c3\u00c4\u0005=\u0000\u0000\u00c48\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c6\u0005A\u0000\u0000\u00c6\u00c7\u0005N\u0000"+
		"\u0000\u00c7\u00c8\u0005D\u0000\u0000\u00c8:\u0001\u0000\u0000\u0000\u00c9"+
		"\u00ca\u0005O\u0000\u0000\u00ca\u00cb\u0005R\u0000\u0000\u00cb<\u0001"+
		"\u0000\u0000\u0000\u00cc\u00cd\u0005X\u0000\u0000\u00cd\u00ce\u0005O\u0000"+
		"\u0000\u00ce\u00cf\u0005R\u0000\u0000\u00cf>\u0001\u0000\u0000\u0000\u00d0"+
		"\u00d2\u0007\u0000\u0000\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d2"+
		"\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d4@\u0001\u0000\u0000\u0000\u0002\u0000"+
		"\u00d3\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}