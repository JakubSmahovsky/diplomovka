// Generated from Logging.g4 by ANTLR 4.8
package simple_tamarin.loggingParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LoggingParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, ATTIMEPOINT=13, PERSISTENT=14, SOLVEDHOW=15, 
		NUMBER=16, IDENTIFIER=17, WHITESPACE=18;
	public static final int
		RULE_message = 0, RULE_solved = 1, RULE_by = 2, RULE_goal = 3, RULE_fact = 4, 
		RULE_term = 5, RULE_function = 6, RULE_tuple = 7, RULE_variable = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"message", "solved", "by", "goal", "fact", "term", "function", "tuple", 
			"variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'solved goal nr.'", "':'", "'by:'", "'('", "','", "')'", "'<'", 
			"'>'", "'$'", "'~'", "'#'", "'.'", null, "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "ATTIMEPOINT", "PERSISTENT", "SOLVEDHOW", "NUMBER", "IDENTIFIER", 
			"WHITESPACE"
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
	public String getGrammarFileName() { return "Logging.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LoggingParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MessageContext extends ParserRuleContext {
		public SolvedContext solved() {
			return getRuleContext(SolvedContext.class,0);
		}
		public ByContext by() {
			return getRuleContext(ByContext.class,0);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_message);
		try {
			setState(20);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				solved();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				by();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SolvedContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(LoggingParser.NUMBER, 0); }
		public TerminalNode SOLVEDHOW() { return getToken(LoggingParser.SOLVEDHOW, 0); }
		public GoalContext goal() {
			return getRuleContext(GoalContext.class,0);
		}
		public SolvedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solved; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitSolved(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SolvedContext solved() throws RecognitionException {
		SolvedContext _localctx = new SolvedContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_solved);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(T__0);
			setState(23);
			match(NUMBER);
			setState(24);
			match(SOLVEDHOW);
			setState(25);
			match(T__1);
			setState(26);
			goal();
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

	public static class ByContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(LoggingParser.NUMBER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(LoggingParser.IDENTIFIER, 0); }
		public ByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_by; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitBy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ByContext by() throws RecognitionException {
		ByContext _localctx = new ByContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_by);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(NUMBER);
			setState(29);
			match(T__2);
			setState(30);
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

	public static class GoalContext extends ParserRuleContext {
		public FactContext fact() {
			return getRuleContext(FactContext.class,0);
		}
		public TerminalNode ATTIMEPOINT() { return getToken(LoggingParser.ATTIMEPOINT, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_goal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			fact();
			setState(33);
			match(ATTIMEPOINT);
			setState(34);
			variable();
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

	public static class FactContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LoggingParser.IDENTIFIER, 0); }
		public TerminalNode PERSISTENT() { return getToken(LoggingParser.PERSISTENT, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public FactContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fact; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitFact(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactContext fact() throws RecognitionException {
		FactContext _localctx = new FactContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fact);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERSISTENT) {
				{
				setState(36);
				match(PERSISTENT);
				}
			}

			setState(39);
			match(IDENTIFIER);
			setState(40);
			match(T__3);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(41);
				term();
				}
			}

			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(44);
				match(T__4);
				setState(45);
				term();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			match(T__5);
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

	public static class TermContext extends ParserRuleContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_term);
		try {
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				tuple();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				variable();
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

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LoggingParser.IDENTIFIER, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(IDENTIFIER);
			setState(59);
			match(T__3);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(60);
				term();
				}
			}

			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(63);
				match(T__4);
				setState(64);
				term();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
			match(T__5);
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

	public static class TupleContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__6);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(73);
				term();
				}
			}

			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(76);
				match(T__4);
				setState(77);
				term();
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(83);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LoggingParser.IDENTIFIER, 0); }
		public TerminalNode NUMBER() { return getToken(LoggingParser.NUMBER, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LoggingVisitor ) return ((LoggingVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) {
				{
				setState(85);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(88);
			match(IDENTIFIER);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(89);
				match(T__11);
				setState(90);
				match(NUMBER);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24`\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\5\2"+
		"\27\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\5"+
		"\6(\n\6\3\6\3\6\3\6\5\6-\n\6\3\6\3\6\7\6\61\n\6\f\6\16\6\64\13\6\3\6\3"+
		"\6\3\7\3\7\3\7\5\7;\n\7\3\b\3\b\3\b\5\b@\n\b\3\b\3\b\7\bD\n\b\f\b\16\b"+
		"G\13\b\3\b\3\b\3\t\3\t\5\tM\n\t\3\t\3\t\7\tQ\n\t\f\t\16\tT\13\t\3\t\3"+
		"\t\3\n\5\nY\n\n\3\n\3\n\3\n\5\n^\n\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2"+
		"\3\3\2\13\r\2b\2\26\3\2\2\2\4\30\3\2\2\2\6\36\3\2\2\2\b\"\3\2\2\2\n\'"+
		"\3\2\2\2\f:\3\2\2\2\16<\3\2\2\2\20J\3\2\2\2\22X\3\2\2\2\24\27\5\4\3\2"+
		"\25\27\5\6\4\2\26\24\3\2\2\2\26\25\3\2\2\2\27\3\3\2\2\2\30\31\7\3\2\2"+
		"\31\32\7\22\2\2\32\33\7\21\2\2\33\34\7\4\2\2\34\35\5\b\5\2\35\5\3\2\2"+
		"\2\36\37\7\22\2\2\37 \7\5\2\2 !\7\23\2\2!\7\3\2\2\2\"#\5\n\6\2#$\7\17"+
		"\2\2$%\5\22\n\2%\t\3\2\2\2&(\7\20\2\2\'&\3\2\2\2\'(\3\2\2\2()\3\2\2\2"+
		")*\7\23\2\2*,\7\6\2\2+-\5\f\7\2,+\3\2\2\2,-\3\2\2\2-\62\3\2\2\2./\7\7"+
		"\2\2/\61\5\f\7\2\60.\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2"+
		"\63\65\3\2\2\2\64\62\3\2\2\2\65\66\7\b\2\2\66\13\3\2\2\2\67;\5\16\b\2"+
		"8;\5\20\t\29;\5\22\n\2:\67\3\2\2\2:8\3\2\2\2:9\3\2\2\2;\r\3\2\2\2<=\7"+
		"\23\2\2=?\7\6\2\2>@\5\f\7\2?>\3\2\2\2?@\3\2\2\2@E\3\2\2\2AB\7\7\2\2BD"+
		"\5\f\7\2CA\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GE\3\2\2\2H"+
		"I\7\b\2\2I\17\3\2\2\2JL\7\t\2\2KM\5\f\7\2LK\3\2\2\2LM\3\2\2\2MR\3\2\2"+
		"\2NO\7\7\2\2OQ\5\f\7\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2"+
		"\2TR\3\2\2\2UV\7\n\2\2V\21\3\2\2\2WY\t\2\2\2XW\3\2\2\2XY\3\2\2\2YZ\3\2"+
		"\2\2Z]\7\23\2\2[\\\7\16\2\2\\^\7\22\2\2][\3\2\2\2]^\3\2\2\2^\23\3\2\2"+
		"\2\r\26\',\62:?ELRX]";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}