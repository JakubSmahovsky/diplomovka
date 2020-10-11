// Generated from Simple_tamarin.g4 by ANTLR 4.8
package simple_tamarin.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Simple_tamarinParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, IDENTIFIER=9, 
		WHITESPACE=10;
	public static final int
		RULE_model = 0, RULE_segment = 1, RULE_principalBlock = 2, RULE_command = 3, 
		RULE_knows = 4, RULE_messageBlock = 5, RULE_queriesBlock = 6, RULE_query = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "segment", "principalBlock", "command", "knows", "messageBlock", 
			"queriesBlock", "query"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'knows'", "'->'", "':'", "','", "'queries'", "'executable?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "IDENTIFIER", "WHITESPACE"
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
	public String getGrammarFileName() { return "Simple_tamarin.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Simple_tamarinParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ModelContext extends ParserRuleContext {
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==IDENTIFIER) {
				{
				{
				setState(16);
				segment();
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class SegmentContext extends ParserRuleContext {
		public PrincipalBlockContext principalBlock() {
			return getRuleContext(PrincipalBlockContext.class,0);
		}
		public MessageBlockContext messageBlock() {
			return getRuleContext(MessageBlockContext.class,0);
		}
		public QueriesBlockContext queriesBlock() {
			return getRuleContext(QueriesBlockContext.class,0);
		}
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitSegment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_segment);
		try {
			setState(25);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				principalBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				messageBlock();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(24);
				queriesBlock();
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

	public static class PrincipalBlockContext extends ParserRuleContext {
		public Token principal;
		public TerminalNode IDENTIFIER() { return getToken(Simple_tamarinParser.IDENTIFIER, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public PrincipalBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_principalBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitPrincipalBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrincipalBlockContext principalBlock() throws RecognitionException {
		PrincipalBlockContext _localctx = new PrincipalBlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_principalBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			((PrincipalBlockContext)_localctx).principal = match(IDENTIFIER);
			setState(28);
			match(T__0);
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(29);
				command();
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(35);
			match(T__1);
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

	public static class CommandContext extends ParserRuleContext {
		public KnowsContext knows() {
			return getRuleContext(KnowsContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			knows();
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

	public static class KnowsContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Simple_tamarinParser.IDENTIFIER, 0); }
		public KnowsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_knows; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitKnows(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KnowsContext knows() throws RecognitionException {
		KnowsContext _localctx = new KnowsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_knows);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__2);
			setState(40);
			match(IDENTIFIER);
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

	public static class MessageBlockContext extends ParserRuleContext {
		public Token sender;
		public Token receiver;
		public Token message;
		public List<TerminalNode> IDENTIFIER() { return getTokens(Simple_tamarinParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(Simple_tamarinParser.IDENTIFIER, i);
		}
		public MessageBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitMessageBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageBlockContext messageBlock() throws RecognitionException {
		MessageBlockContext _localctx = new MessageBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_messageBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			((MessageBlockContext)_localctx).sender = match(IDENTIFIER);
			setState(43);
			match(T__3);
			setState(44);
			((MessageBlockContext)_localctx).receiver = match(IDENTIFIER);
			setState(45);
			match(T__4);
			setState(46);
			((MessageBlockContext)_localctx).message = match(IDENTIFIER);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(47);
				match(T__5);
				setState(48);
				((MessageBlockContext)_localctx).message = match(IDENTIFIER);
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class QueriesBlockContext extends ParserRuleContext {
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
		}
		public QueriesBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queriesBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitQueriesBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueriesBlockContext queriesBlock() throws RecognitionException {
		QueriesBlockContext _localctx = new QueriesBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_queriesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__6);
			setState(55);
			match(T__0);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(56);
				query();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(T__1);
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

	public static class QueryContext extends ParserRuleContext {
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__7);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\fE\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\7\2\24\n\2\f\2"+
		"\16\2\27\13\2\3\3\3\3\3\3\5\3\34\n\3\3\4\3\4\3\4\7\4!\n\4\f\4\16\4$\13"+
		"\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\64\n\7"+
		"\f\7\16\7\67\13\7\3\b\3\b\3\b\7\b<\n\b\f\b\16\b?\13\b\3\b\3\b\3\t\3\t"+
		"\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2B\2\25\3\2\2\2\4\33\3\2\2\2\6\35\3\2"+
		"\2\2\b\'\3\2\2\2\n)\3\2\2\2\f,\3\2\2\2\168\3\2\2\2\20B\3\2\2\2\22\24\5"+
		"\4\3\2\23\22\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\3\3"+
		"\2\2\2\27\25\3\2\2\2\30\34\5\6\4\2\31\34\5\f\7\2\32\34\5\16\b\2\33\30"+
		"\3\2\2\2\33\31\3\2\2\2\33\32\3\2\2\2\34\5\3\2\2\2\35\36\7\13\2\2\36\""+
		"\7\3\2\2\37!\5\b\5\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#%\3\2"+
		"\2\2$\"\3\2\2\2%&\7\4\2\2&\7\3\2\2\2\'(\5\n\6\2(\t\3\2\2\2)*\7\5\2\2*"+
		"+\7\13\2\2+\13\3\2\2\2,-\7\13\2\2-.\7\6\2\2./\7\13\2\2/\60\7\7\2\2\60"+
		"\65\7\13\2\2\61\62\7\b\2\2\62\64\7\13\2\2\63\61\3\2\2\2\64\67\3\2\2\2"+
		"\65\63\3\2\2\2\65\66\3\2\2\2\66\r\3\2\2\2\67\65\3\2\2\289\7\t\2\29=\7"+
		"\3\2\2:<\5\20\t\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@\3\2\2\2?="+
		"\3\2\2\2@A\7\4\2\2A\17\3\2\2\2BC\7\n\2\2C\21\3\2\2\2\7\25\33\"\65=";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}