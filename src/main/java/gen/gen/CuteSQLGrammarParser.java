// Generated from C:/Users/moham/OneDrive/Desktop/cute-tiny-database-engine/src/main/java/gen/CuteSQLGrammar.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CuteSQLGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTValue=1, DOUBLEValue=2, StringValue=3, CREATE=4, INSERT=5, INTO=6, 
		TABLE=7, PRIMARY=8, KEY=9, STRING=10, INT=11, DOUBLE=12, INDEX=13, ON=14, 
		VALUES=15, UPDATE=16, SET=17, WHERE=18, DELETE=19, FROM=20, SELECT=21, 
		AND=22, OR=23, XOR=24, OpeningParenthesis=25, ClosingParenthesis=26, Comma=27, 
		SemiColon=28, Equals=29, Colon=30, Star=31, GreaterThan=32, GreaterThanEquals=33, 
		LessThan=34, LessThanEquals=35, NotEquals=36, GenericSTRING=37, SPACE=38;
	public static final int
		RULE_cuteSQL = 0, RULE_createTable = 1, RULE_createIndex = 2, RULE_insertIntoTable = 3, 
		RULE_updateTable = 4, RULE_deleteFromTable = 5, RULE_selectFromTable = 6, 
		RULE_primaryColumn = 7, RULE_columnDef = 8, RULE_tableName = 9, RULE_columnName = 10, 
		RULE_columnType = 11, RULE_indexName = 12, RULE_columnValue = 13, RULE_clusteringColumnName = 14, 
		RULE_clusteringColumnValue = 15, RULE_condition = 16, RULE_operator = 17, 
		RULE_starrOperator = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"cuteSQL", "createTable", "createIndex", "insertIntoTable", "updateTable", 
			"deleteFromTable", "selectFromTable", "primaryColumn", "columnDef", "tableName", 
			"columnName", "columnType", "indexName", "columnValue", "clusteringColumnName", 
			"clusteringColumnValue", "condition", "operator", "starrOperator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "'('", "')'", "','", "';'", "'='", "':'", "'*'", "'>'", "'>='", 
			"'<'", "'<='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INTValue", "DOUBLEValue", "StringValue", "CREATE", "INSERT", "INTO", 
			"TABLE", "PRIMARY", "KEY", "STRING", "INT", "DOUBLE", "INDEX", "ON", 
			"VALUES", "UPDATE", "SET", "WHERE", "DELETE", "FROM", "SELECT", "AND", 
			"OR", "XOR", "OpeningParenthesis", "ClosingParenthesis", "Comma", "SemiColon", 
			"Equals", "Colon", "Star", "GreaterThan", "GreaterThanEquals", "LessThan", 
			"LessThanEquals", "NotEquals", "GenericSTRING", "SPACE"
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

	@Override
	public String getGrammarFileName() { return "CuteSQLGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CuteSQLGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CuteSQLContext extends ParserRuleContext {
		public CreateTableContext createTable() {
			return getRuleContext(CreateTableContext.class,0);
		}
		public CreateIndexContext createIndex() {
			return getRuleContext(CreateIndexContext.class,0);
		}
		public InsertIntoTableContext insertIntoTable() {
			return getRuleContext(InsertIntoTableContext.class,0);
		}
		public UpdateTableContext updateTable() {
			return getRuleContext(UpdateTableContext.class,0);
		}
		public DeleteFromTableContext deleteFromTable() {
			return getRuleContext(DeleteFromTableContext.class,0);
		}
		public SelectFromTableContext selectFromTable() {
			return getRuleContext(SelectFromTableContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CuteSQLGrammarParser.EOF, 0); }
		public CuteSQLContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cuteSQL; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterCuteSQL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitCuteSQL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitCuteSQL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CuteSQLContext cuteSQL() throws RecognitionException {
		CuteSQLContext _localctx = new CuteSQLContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cuteSQL);
		try {
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				createTable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				createIndex();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				insertIntoTable();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				updateTable();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				deleteFromTable();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(43);
				selectFromTable();
				setState(44);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CuteSQLGrammarParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(CuteSQLGrammarParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode OpeningParenthesis() { return getToken(CuteSQLGrammarParser.OpeningParenthesis, 0); }
		public PrimaryColumnContext primaryColumn() {
			return getRuleContext(PrimaryColumnContext.class,0);
		}
		public TerminalNode ClosingParenthesis() { return getToken(CuteSQLGrammarParser.ClosingParenthesis, 0); }
		public List<TerminalNode> Comma() { return getTokens(CuteSQLGrammarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CuteSQLGrammarParser.Comma, i);
		}
		public List<ColumnDefContext> columnDef() {
			return getRuleContexts(ColumnDefContext.class);
		}
		public ColumnDefContext columnDef(int i) {
			return getRuleContext(ColumnDefContext.class,i);
		}
		public TerminalNode SemiColon() { return getToken(CuteSQLGrammarParser.SemiColon, 0); }
		public CreateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterCreateTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitCreateTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitCreateTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableContext createTable() throws RecognitionException {
		CreateTableContext _localctx = new CreateTableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_createTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(CREATE);
			setState(49);
			match(TABLE);
			setState(50);
			tableName();
			setState(51);
			match(OpeningParenthesis);
			setState(52);
			primaryColumn();
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(53);
				match(Comma);
				setState(54);
				columnDef();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(ClosingParenthesis);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SemiColon) {
				{
				setState(61);
				match(SemiColon);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateIndexContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CuteSQLGrammarParser.CREATE, 0); }
		public TerminalNode INDEX() { return getToken(CuteSQLGrammarParser.INDEX, 0); }
		public IndexNameContext indexName() {
			return getRuleContext(IndexNameContext.class,0);
		}
		public TerminalNode ON() { return getToken(CuteSQLGrammarParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode OpeningParenthesis() { return getToken(CuteSQLGrammarParser.OpeningParenthesis, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode ClosingParenthesis() { return getToken(CuteSQLGrammarParser.ClosingParenthesis, 0); }
		public TerminalNode SemiColon() { return getToken(CuteSQLGrammarParser.SemiColon, 0); }
		public CreateIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterCreateIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitCreateIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitCreateIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateIndexContext createIndex() throws RecognitionException {
		CreateIndexContext _localctx = new CreateIndexContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_createIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(CREATE);
			setState(65);
			match(INDEX);
			setState(66);
			indexName();
			setState(67);
			match(ON);
			setState(68);
			tableName();
			setState(69);
			match(OpeningParenthesis);
			setState(70);
			columnName();
			setState(71);
			match(ClosingParenthesis);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SemiColon) {
				{
				setState(72);
				match(SemiColon);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertIntoTableContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(CuteSQLGrammarParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(CuteSQLGrammarParser.INTO, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<TerminalNode> OpeningParenthesis() { return getTokens(CuteSQLGrammarParser.OpeningParenthesis); }
		public TerminalNode OpeningParenthesis(int i) {
			return getToken(CuteSQLGrammarParser.OpeningParenthesis, i);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> ClosingParenthesis() { return getTokens(CuteSQLGrammarParser.ClosingParenthesis); }
		public TerminalNode ClosingParenthesis(int i) {
			return getToken(CuteSQLGrammarParser.ClosingParenthesis, i);
		}
		public TerminalNode VALUES() { return getToken(CuteSQLGrammarParser.VALUES, 0); }
		public List<ColumnValueContext> columnValue() {
			return getRuleContexts(ColumnValueContext.class);
		}
		public ColumnValueContext columnValue(int i) {
			return getRuleContext(ColumnValueContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CuteSQLGrammarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CuteSQLGrammarParser.Comma, i);
		}
		public TerminalNode SemiColon() { return getToken(CuteSQLGrammarParser.SemiColon, 0); }
		public InsertIntoTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertIntoTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterInsertIntoTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitInsertIntoTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitInsertIntoTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertIntoTableContext insertIntoTable() throws RecognitionException {
		InsertIntoTableContext _localctx = new InsertIntoTableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_insertIntoTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(INSERT);
			setState(76);
			match(INTO);
			setState(77);
			tableName();
			setState(78);
			match(OpeningParenthesis);
			setState(79);
			columnName();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(80);
				match(Comma);
				setState(81);
				columnName();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
			match(ClosingParenthesis);
			setState(88);
			match(VALUES);
			setState(89);
			match(OpeningParenthesis);
			setState(90);
			columnValue();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(91);
				match(Comma);
				setState(92);
				columnValue();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			match(ClosingParenthesis);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SemiColon) {
				{
				setState(99);
				match(SemiColon);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateTableContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(CuteSQLGrammarParser.UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SET() { return getToken(CuteSQLGrammarParser.SET, 0); }
		public TerminalNode OpeningParenthesis() { return getToken(CuteSQLGrammarParser.OpeningParenthesis, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> Equals() { return getTokens(CuteSQLGrammarParser.Equals); }
		public TerminalNode Equals(int i) {
			return getToken(CuteSQLGrammarParser.Equals, i);
		}
		public List<ColumnValueContext> columnValue() {
			return getRuleContexts(ColumnValueContext.class);
		}
		public ColumnValueContext columnValue(int i) {
			return getRuleContext(ColumnValueContext.class,i);
		}
		public TerminalNode ClosingParenthesis() { return getToken(CuteSQLGrammarParser.ClosingParenthesis, 0); }
		public TerminalNode WHERE() { return getToken(CuteSQLGrammarParser.WHERE, 0); }
		public ClusteringColumnNameContext clusteringColumnName() {
			return getRuleContext(ClusteringColumnNameContext.class,0);
		}
		public ClusteringColumnValueContext clusteringColumnValue() {
			return getRuleContext(ClusteringColumnValueContext.class,0);
		}
		public List<TerminalNode> Comma() { return getTokens(CuteSQLGrammarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CuteSQLGrammarParser.Comma, i);
		}
		public TerminalNode SemiColon() { return getToken(CuteSQLGrammarParser.SemiColon, 0); }
		public UpdateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterUpdateTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitUpdateTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitUpdateTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateTableContext updateTable() throws RecognitionException {
		UpdateTableContext _localctx = new UpdateTableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_updateTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(UPDATE);
			setState(103);
			tableName();
			setState(104);
			match(SET);
			setState(105);
			match(OpeningParenthesis);
			setState(106);
			columnName();
			setState(107);
			match(Equals);
			setState(108);
			columnValue();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(109);
				match(Comma);
				setState(110);
				columnName();
				setState(111);
				match(Equals);
				setState(112);
				columnValue();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
			match(ClosingParenthesis);
			setState(120);
			match(WHERE);
			setState(121);
			clusteringColumnName();
			setState(122);
			match(Equals);
			setState(123);
			clusteringColumnValue();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SemiColon) {
				{
				setState(124);
				match(SemiColon);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteFromTableContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(CuteSQLGrammarParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(CuteSQLGrammarParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(CuteSQLGrammarParser.WHERE, 0); }
		public TerminalNode OpeningParenthesis() { return getToken(CuteSQLGrammarParser.OpeningParenthesis, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> Equals() { return getTokens(CuteSQLGrammarParser.Equals); }
		public TerminalNode Equals(int i) {
			return getToken(CuteSQLGrammarParser.Equals, i);
		}
		public List<ColumnValueContext> columnValue() {
			return getRuleContexts(ColumnValueContext.class);
		}
		public ColumnValueContext columnValue(int i) {
			return getRuleContext(ColumnValueContext.class,i);
		}
		public TerminalNode ClosingParenthesis() { return getToken(CuteSQLGrammarParser.ClosingParenthesis, 0); }
		public List<TerminalNode> Comma() { return getTokens(CuteSQLGrammarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CuteSQLGrammarParser.Comma, i);
		}
		public TerminalNode SemiColon() { return getToken(CuteSQLGrammarParser.SemiColon, 0); }
		public DeleteFromTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteFromTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterDeleteFromTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitDeleteFromTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitDeleteFromTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteFromTableContext deleteFromTable() throws RecognitionException {
		DeleteFromTableContext _localctx = new DeleteFromTableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_deleteFromTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(DELETE);
			setState(128);
			match(FROM);
			setState(129);
			tableName();
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(130);
				match(WHERE);
				setState(131);
				match(OpeningParenthesis);
				setState(132);
				columnName();
				setState(133);
				match(Equals);
				setState(134);
				columnValue();
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(135);
					match(Comma);
					setState(136);
					columnName();
					setState(137);
					match(Equals);
					setState(138);
					columnValue();
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				match(ClosingParenthesis);
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SemiColon) {
					{
					setState(146);
					match(SemiColon);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectFromTableContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(CuteSQLGrammarParser.SELECT, 0); }
		public TerminalNode Star() { return getToken(CuteSQLGrammarParser.Star, 0); }
		public TerminalNode FROM() { return getToken(CuteSQLGrammarParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(CuteSQLGrammarParser.WHERE, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<StarrOperatorContext> starrOperator() {
			return getRuleContexts(StarrOperatorContext.class);
		}
		public StarrOperatorContext starrOperator(int i) {
			return getRuleContext(StarrOperatorContext.class,i);
		}
		public TerminalNode SemiColon() { return getToken(CuteSQLGrammarParser.SemiColon, 0); }
		public SelectFromTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectFromTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterSelectFromTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitSelectFromTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitSelectFromTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectFromTableContext selectFromTable() throws RecognitionException {
		SelectFromTableContext _localctx = new SelectFromTableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_selectFromTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(SELECT);
			setState(152);
			match(Star);
			setState(153);
			match(FROM);
			setState(154);
			tableName();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(155);
				match(WHERE);
				setState(156);
				condition();
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) {
					{
					{
					setState(157);
					starrOperator();
					setState(158);
					condition();
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SemiColon) {
					{
					setState(165);
					match(SemiColon);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryColumnContext extends ParserRuleContext {
		public ColumnDefContext columnDef() {
			return getRuleContext(ColumnDefContext.class,0);
		}
		public TerminalNode PRIMARY() { return getToken(CuteSQLGrammarParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(CuteSQLGrammarParser.KEY, 0); }
		public PrimaryColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryColumn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterPrimaryColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitPrimaryColumn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitPrimaryColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryColumnContext primaryColumn() throws RecognitionException {
		PrimaryColumnContext _localctx = new PrimaryColumnContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_primaryColumn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			columnDef();
			setState(171);
			match(PRIMARY);
			setState(172);
			match(KEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnDefContext extends ParserRuleContext {
		public ColumnTypeContext columnType() {
			return getRuleContext(ColumnTypeContext.class,0);
		}
		public TerminalNode Colon() { return getToken(CuteSQLGrammarParser.Colon, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public ColumnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterColumnDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitColumnDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitColumnDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnDefContext columnDef() throws RecognitionException {
		ColumnDefContext _localctx = new ColumnDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_columnDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			columnType();
			setState(175);
			match(Colon);
			setState(176);
			columnName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode GenericSTRING() { return getToken(CuteSQLGrammarParser.GenericSTRING, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(GenericSTRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode GenericSTRING() { return getToken(CuteSQLGrammarParser.GenericSTRING, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(GenericSTRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnTypeContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CuteSQLGrammarParser.STRING, 0); }
		public TerminalNode INT() { return getToken(CuteSQLGrammarParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(CuteSQLGrammarParser.DOUBLE, 0); }
		public ColumnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterColumnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitColumnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitColumnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnTypeContext columnType() throws RecognitionException {
		ColumnTypeContext _localctx = new ColumnTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_columnType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexNameContext extends ParserRuleContext {
		public TerminalNode GenericSTRING() { return getToken(CuteSQLGrammarParser.GenericSTRING, 0); }
		public IndexNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterIndexName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitIndexName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitIndexName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexNameContext indexName() throws RecognitionException {
		IndexNameContext _localctx = new IndexNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_indexName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(GenericSTRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnValueContext extends ParserRuleContext {
		public TerminalNode StringValue() { return getToken(CuteSQLGrammarParser.StringValue, 0); }
		public TerminalNode INTValue() { return getToken(CuteSQLGrammarParser.INTValue, 0); }
		public TerminalNode DOUBLEValue() { return getToken(CuteSQLGrammarParser.DOUBLEValue, 0); }
		public ColumnValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterColumnValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitColumnValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitColumnValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnValueContext columnValue() throws RecognitionException {
		ColumnValueContext _localctx = new ColumnValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_columnValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClusteringColumnNameContext extends ParserRuleContext {
		public TerminalNode GenericSTRING() { return getToken(CuteSQLGrammarParser.GenericSTRING, 0); }
		public ClusteringColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clusteringColumnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterClusteringColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitClusteringColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitClusteringColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClusteringColumnNameContext clusteringColumnName() throws RecognitionException {
		ClusteringColumnNameContext _localctx = new ClusteringColumnNameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_clusteringColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(GenericSTRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClusteringColumnValueContext extends ParserRuleContext {
		public ColumnValueContext columnValue() {
			return getRuleContext(ColumnValueContext.class,0);
		}
		public ClusteringColumnValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clusteringColumnValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterClusteringColumnValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitClusteringColumnValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitClusteringColumnValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClusteringColumnValueContext clusteringColumnValue() throws RecognitionException {
		ClusteringColumnValueContext _localctx = new ClusteringColumnValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_clusteringColumnValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			columnValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ColumnValueContext columnValue() {
			return getRuleContext(ColumnValueContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			columnName();
			setState(193);
			operator();
			setState(194);
			columnValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode Equals() { return getToken(CuteSQLGrammarParser.Equals, 0); }
		public TerminalNode LessThan() { return getToken(CuteSQLGrammarParser.LessThan, 0); }
		public TerminalNode GreaterThan() { return getToken(CuteSQLGrammarParser.GreaterThan, 0); }
		public TerminalNode LessThanEquals() { return getToken(CuteSQLGrammarParser.LessThanEquals, 0); }
		public TerminalNode GreaterThanEquals() { return getToken(CuteSQLGrammarParser.GreaterThanEquals, 0); }
		public TerminalNode NotEquals() { return getToken(CuteSQLGrammarParser.NotEquals, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 133680857088L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StarrOperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(CuteSQLGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(CuteSQLGrammarParser.OR, 0); }
		public TerminalNode XOR() { return getToken(CuteSQLGrammarParser.XOR, 0); }
		public StarrOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_starrOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).enterStarrOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CuteSQLGrammarListener ) ((CuteSQLGrammarListener)listener).exitStarrOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CuteSQLGrammarVisitor ) return ((CuteSQLGrammarVisitor<? extends T>)visitor).visitStarrOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StarrOperatorContext starrOperator() throws RecognitionException {
		StarrOperatorContext _localctx = new StarrOperatorContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_starrOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u00c9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0003\u0000/\b\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"8\b\u0001\n\u0001\f\u0001;\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"?\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002J\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003S\b\u0003\n\u0003\f\u0003V\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"^\b\u0003\n\u0003\f\u0003a\t\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"e\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004s\b\u0004\n\u0004\f\u0004v\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"~\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005\u008d\b\u0005\n\u0005\f\u0005\u0090"+
		"\t\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0094\b\u0005\u0003\u0005"+
		"\u0096\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a1\b\u0006"+
		"\n\u0006\f\u0006\u00a4\t\u0006\u0001\u0006\u0003\u0006\u00a7\b\u0006\u0003"+
		"\u0006\u00a9\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0000\u0000\u0013"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$\u0000\u0004\u0001\u0000\n\f\u0001\u0000\u0001\u0003\u0002"+
		"\u0000\u001d\u001d $\u0001\u0000\u0016\u0018\u00c8\u0000.\u0001\u0000"+
		"\u0000\u0000\u00020\u0001\u0000\u0000\u0000\u0004@\u0001\u0000\u0000\u0000"+
		"\u0006K\u0001\u0000\u0000\u0000\bf\u0001\u0000\u0000\u0000\n\u007f\u0001"+
		"\u0000\u0000\u0000\f\u0097\u0001\u0000\u0000\u0000\u000e\u00aa\u0001\u0000"+
		"\u0000\u0000\u0010\u00ae\u0001\u0000\u0000\u0000\u0012\u00b2\u0001\u0000"+
		"\u0000\u0000\u0014\u00b4\u0001\u0000\u0000\u0000\u0016\u00b6\u0001\u0000"+
		"\u0000\u0000\u0018\u00b8\u0001\u0000\u0000\u0000\u001a\u00ba\u0001\u0000"+
		"\u0000\u0000\u001c\u00bc\u0001\u0000\u0000\u0000\u001e\u00be\u0001\u0000"+
		"\u0000\u0000 \u00c0\u0001\u0000\u0000\u0000\"\u00c4\u0001\u0000\u0000"+
		"\u0000$\u00c6\u0001\u0000\u0000\u0000&/\u0003\u0002\u0001\u0000\'/\u0003"+
		"\u0004\u0002\u0000(/\u0003\u0006\u0003\u0000)/\u0003\b\u0004\u0000*/\u0003"+
		"\n\u0005\u0000+,\u0003\f\u0006\u0000,-\u0005\u0000\u0000\u0001-/\u0001"+
		"\u0000\u0000\u0000.&\u0001\u0000\u0000\u0000.\'\u0001\u0000\u0000\u0000"+
		".(\u0001\u0000\u0000\u0000.)\u0001\u0000\u0000\u0000.*\u0001\u0000\u0000"+
		"\u0000.+\u0001\u0000\u0000\u0000/\u0001\u0001\u0000\u0000\u000001\u0005"+
		"\u0004\u0000\u000012\u0005\u0007\u0000\u000023\u0003\u0012\t\u000034\u0005"+
		"\u0019\u0000\u000049\u0003\u000e\u0007\u000056\u0005\u001b\u0000\u0000"+
		"68\u0003\u0010\b\u000075\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u0000"+
		"97\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000"+
		"\u0000;9\u0001\u0000\u0000\u0000<>\u0005\u001a\u0000\u0000=?\u0005\u001c"+
		"\u0000\u0000>=\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?\u0003"+
		"\u0001\u0000\u0000\u0000@A\u0005\u0004\u0000\u0000AB\u0005\r\u0000\u0000"+
		"BC\u0003\u0018\f\u0000CD\u0005\u000e\u0000\u0000DE\u0003\u0012\t\u0000"+
		"EF\u0005\u0019\u0000\u0000FG\u0003\u0014\n\u0000GI\u0005\u001a\u0000\u0000"+
		"HJ\u0005\u001c\u0000\u0000IH\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000"+
		"\u0000J\u0005\u0001\u0000\u0000\u0000KL\u0005\u0005\u0000\u0000LM\u0005"+
		"\u0006\u0000\u0000MN\u0003\u0012\t\u0000NO\u0005\u0019\u0000\u0000OT\u0003"+
		"\u0014\n\u0000PQ\u0005\u001b\u0000\u0000QS\u0003\u0014\n\u0000RP\u0001"+
		"\u0000\u0000\u0000SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000"+
		"TU\u0001\u0000\u0000\u0000UW\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000"+
		"\u0000WX\u0005\u001a\u0000\u0000XY\u0005\u000f\u0000\u0000YZ\u0005\u0019"+
		"\u0000\u0000Z_\u0003\u001a\r\u0000[\\\u0005\u001b\u0000\u0000\\^\u0003"+
		"\u001a\r\u0000][\u0001\u0000\u0000\u0000^a\u0001\u0000\u0000\u0000_]\u0001"+
		"\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`b\u0001\u0000\u0000\u0000"+
		"a_\u0001\u0000\u0000\u0000bd\u0005\u001a\u0000\u0000ce\u0005\u001c\u0000"+
		"\u0000dc\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000e\u0007\u0001"+
		"\u0000\u0000\u0000fg\u0005\u0010\u0000\u0000gh\u0003\u0012\t\u0000hi\u0005"+
		"\u0011\u0000\u0000ij\u0005\u0019\u0000\u0000jk\u0003\u0014\n\u0000kl\u0005"+
		"\u001d\u0000\u0000lt\u0003\u001a\r\u0000mn\u0005\u001b\u0000\u0000no\u0003"+
		"\u0014\n\u0000op\u0005\u001d\u0000\u0000pq\u0003\u001a\r\u0000qs\u0001"+
		"\u0000\u0000\u0000rm\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000"+
		"tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uw\u0001\u0000\u0000"+
		"\u0000vt\u0001\u0000\u0000\u0000wx\u0005\u001a\u0000\u0000xy\u0005\u0012"+
		"\u0000\u0000yz\u0003\u001c\u000e\u0000z{\u0005\u001d\u0000\u0000{}\u0003"+
		"\u001e\u000f\u0000|~\u0005\u001c\u0000\u0000}|\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~\t\u0001\u0000\u0000\u0000\u007f\u0080\u0005"+
		"\u0013\u0000\u0000\u0080\u0081\u0005\u0014\u0000\u0000\u0081\u0095\u0003"+
		"\u0012\t\u0000\u0082\u0083\u0005\u0012\u0000\u0000\u0083\u0084\u0005\u0019"+
		"\u0000\u0000\u0084\u0085\u0003\u0014\n\u0000\u0085\u0086\u0005\u001d\u0000"+
		"\u0000\u0086\u008e\u0003\u001a\r\u0000\u0087\u0088\u0005\u001b\u0000\u0000"+
		"\u0088\u0089\u0003\u0014\n\u0000\u0089\u008a\u0005\u001d\u0000\u0000\u008a"+
		"\u008b\u0003\u001a\r\u0000\u008b\u008d\u0001\u0000\u0000\u0000\u008c\u0087"+
		"\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0091"+
		"\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0093"+
		"\u0005\u001a\u0000\u0000\u0092\u0094\u0005\u001c\u0000\u0000\u0093\u0092"+
		"\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0096"+
		"\u0001\u0000\u0000\u0000\u0095\u0082\u0001\u0000\u0000\u0000\u0095\u0096"+
		"\u0001\u0000\u0000\u0000\u0096\u000b\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0005\u0015\u0000\u0000\u0098\u0099\u0005\u001f\u0000\u0000\u0099\u009a"+
		"\u0005\u0014\u0000\u0000\u009a\u00a8\u0003\u0012\t\u0000\u009b\u009c\u0005"+
		"\u0012\u0000\u0000\u009c\u00a2\u0003 \u0010\u0000\u009d\u009e\u0003$\u0012"+
		"\u0000\u009e\u009f\u0003 \u0010\u0000\u009f\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a0\u009d\u0001\u0000\u0000\u0000\u00a1\u00a4\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a6\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a7\u0005\u001c\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a8\u009b\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a9\r\u0001\u0000\u0000\u0000\u00aa\u00ab\u0003\u0010\b\u0000\u00ab"+
		"\u00ac\u0005\b\u0000\u0000\u00ac\u00ad\u0005\t\u0000\u0000\u00ad\u000f"+
		"\u0001\u0000\u0000\u0000\u00ae\u00af\u0003\u0016\u000b\u0000\u00af\u00b0"+
		"\u0005\u001e\u0000\u0000\u00b0\u00b1\u0003\u0014\n\u0000\u00b1\u0011\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b3\u0005%\u0000\u0000\u00b3\u0013\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b5\u0005%\u0000\u0000\u00b5\u0015\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b7\u0007\u0000\u0000\u0000\u00b7\u0017\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u0005%\u0000\u0000\u00b9\u0019\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bb\u0007\u0001\u0000\u0000\u00bb\u001b\u0001\u0000\u0000\u0000"+
		"\u00bc\u00bd\u0005%\u0000\u0000\u00bd\u001d\u0001\u0000\u0000\u0000\u00be"+
		"\u00bf\u0003\u001a\r\u0000\u00bf\u001f\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0003\u0014\n\u0000\u00c1\u00c2\u0003\"\u0011\u0000\u00c2\u00c3\u0003"+
		"\u001a\r\u0000\u00c3!\u0001\u0000\u0000\u0000\u00c4\u00c5\u0007\u0002"+
		"\u0000\u0000\u00c5#\u0001\u0000\u0000\u0000\u00c6\u00c7\u0007\u0003\u0000"+
		"\u0000\u00c7%\u0001\u0000\u0000\u0000\u000f.9>IT_dt}\u008e\u0093\u0095"+
		"\u00a2\u00a6\u00a8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}