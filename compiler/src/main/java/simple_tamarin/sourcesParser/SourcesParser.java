// Generated from Sources.g4 by ANTLR 4.8
package simple_tamarin.sourcesParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SourcesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, SEPARATOR=41, ATTIMEPOINT=42, PERSISTENT=43, SUBSTARROW=44, 
		PARTIAL_DECONSTRUCTIONS=45, USEFUL=46, NUMBER=47, IDENTIFIER=48, WHITESPACE=49;
	public static final int
		RULE_sources = 0, RULE_group = 1, RULE_source = 2, RULE_goal = 3, RULE_fact = 4, 
		RULE_term = 5, RULE_function = 6, RULE_tuple = 7, RULE_variable = 8, RULE_subst = 9, 
		RULE_formula = 10, RULE_lemma = 11, RULE_lemmaStmt = 12, RULE_jsonObj = 13, 
		RULE_jsonArray = 14, RULE_jsonKeyValue = 15, RULE_jsonKey = 16, RULE_jsonValue = 17, 
		RULE_jsonChars = 18, RULE_jsonString = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"sources", "group", "source", "goal", "fact", "term", "function", "tuple", 
			"variable", "subst", "formula", "lemma", "lemmaStmt", "jsonObj", "jsonArray", 
			"jsonKeyValue", "jsonKey", "jsonValue", "jsonChars", "jsonString"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Sources of'", "'\"'", "'('", "'cases)'", "'Source'", "'of'", 
			"'/'", "'named'", "'last: none'", "'formulas:'", "'equations:'", "'subst:'", 
			"'conj:'", "'lemmas:'", "'allowed cases: refined'", "'solved formulas:'", 
			"'unsolved goals:'", "'// nr:'", "'solved goals:'", "'from rule'", "')'", 
			"'json graph:'", "','", "'~~>'", "'<'", "'>'", "'$'", "'~'", "'#'", "'.'", 
			"'{'", "'}'", "'='", "'\u2200'", "'\u21D2'", "'['", "']'", "':'", "'true'", 
			"'false'", "'------------------------------------------------------------------------------'", 
			null, "'!'", "'<~'", "'(partial deconstructions)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "SEPARATOR", "ATTIMEPOINT", "PERSISTENT", 
			"SUBSTARROW", "PARTIAL_DECONSTRUCTIONS", "USEFUL", "NUMBER", "IDENTIFIER", 
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
	public String getGrammarFileName() { return "Sources.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SourcesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SourcesContext extends ParserRuleContext {
		public List<GroupContext> group() {
			return getRuleContexts(GroupContext.class);
		}
		public GroupContext group(int i) {
			return getRuleContext(GroupContext.class,i);
		}
		public SourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sources; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitSources(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourcesContext sources() throws RecognitionException {
		SourcesContext _localctx = new SourcesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sources);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(40);
				group();
				}
				}
				setState(45);
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

	public static class GroupContext extends ParserRuleContext {
		public GoalContext goal() {
			return getRuleContext(GoalContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(SourcesParser.NUMBER, 0); }
		public List<SourceContext> source() {
			return getRuleContexts(SourceContext.class);
		}
		public SourceContext source(int i) {
			return getRuleContext(SourceContext.class,i);
		}
		public GroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__0);
			setState(47);
			match(T__1);
			setState(48);
			goal();
			setState(49);
			match(T__1);
			setState(50);
			match(T__2);
			setState(51);
			match(NUMBER);
			setState(52);
			match(T__3);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(53);
				source();
				}
				}
				setState(58);
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

	public static class SourceContext extends ParserRuleContext {
		public Token name;
		public List<TerminalNode> NUMBER() { return getTokens(SourcesParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(SourcesParser.NUMBER, i);
		}
		public List<GoalContext> goal() {
			return getRuleContexts(GoalContext.class);
		}
		public GoalContext goal(int i) {
			return getRuleContext(GoalContext.class,i);
		}
		public JsonObjContext jsonObj() {
			return getRuleContext(JsonObjContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(SourcesParser.SEPARATOR, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(SourcesParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SourcesParser.IDENTIFIER, i);
		}
		public TerminalNode PARTIAL_DECONSTRUCTIONS() { return getToken(SourcesParser.PARTIAL_DECONSTRUCTIONS, 0); }
		public List<SubstContext> subst() {
			return getRuleContexts(SubstContext.class);
		}
		public SubstContext subst(int i) {
			return getRuleContext(SubstContext.class,i);
		}
		public List<LemmaContext> lemma() {
			return getRuleContexts(LemmaContext.class);
		}
		public LemmaContext lemma(int i) {
			return getRuleContext(LemmaContext.class,i);
		}
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public List<TerminalNode> USEFUL() { return getTokens(SourcesParser.USEFUL); }
		public TerminalNode USEFUL(int i) {
			return getToken(SourcesParser.USEFUL, i);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_source);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__4);
			setState(60);
			match(NUMBER);
			setState(61);
			match(T__5);
			setState(62);
			match(NUMBER);
			setState(63);
			match(T__6);
			setState(64);
			match(T__7);
			setState(65);
			match(T__1);
			setState(66);
			((SourceContext)_localctx).name = match(IDENTIFIER);
			setState(67);
			match(T__1);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PARTIAL_DECONSTRUCTIONS) {
				{
				setState(68);
				match(PARTIAL_DECONSTRUCTIONS);
				}
			}

			setState(71);
			match(T__1);
			setState(72);
			goal();
			setState(73);
			match(T__1);
			setState(74);
			match(T__8);
			setState(75);
			match(T__9);
			setState(76);
			match(T__10);
			setState(77);
			match(T__11);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(78);
				subst();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84);
			match(T__12);
			setState(85);
			match(T__13);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__33) {
				{
				{
				setState(86);
				lemma();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			match(T__14);
			setState(93);
			match(T__15);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(94);
				formula();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(T__16);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << PERSISTENT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(101);
				goal();
				setState(102);
				match(T__17);
				setState(103);
				match(NUMBER);
				setState(104);
				match(USEFUL);
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			match(T__18);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << PERSISTENT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(112);
				goal();
				setState(113);
				match(T__17);
				setState(114);
				match(NUMBER);
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(115);
					match(T__2);
					setState(116);
					match(T__19);
					setState(117);
					match(IDENTIFIER);
					setState(118);
					match(T__20);
					}
				}

				setState(121);
				match(USEFUL);
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			match(T__21);
			setState(129);
			jsonObj();
			setState(130);
			match(SEPARATOR);
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
		public TerminalNode ATTIMEPOINT() { return getToken(SourcesParser.ATTIMEPOINT, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(SourcesParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(SourcesParser.NUMBER, i);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_goal);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PERSISTENT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				fact();
				setState(133);
				match(ATTIMEPOINT);
				setState(134);
				variable();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				match(T__2);
				setState(137);
				variable();
				setState(138);
				match(T__22);
				setState(139);
				match(NUMBER);
				setState(140);
				match(T__20);
				setState(141);
				match(T__23);
				setState(142);
				match(T__2);
				setState(143);
				variable();
				setState(144);
				match(T__22);
				setState(145);
				match(NUMBER);
				setState(146);
				match(T__20);
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

	public static class FactContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SourcesParser.IDENTIFIER, 0); }
		public TerminalNode PERSISTENT() { return getToken(SourcesParser.PERSISTENT, 0); }
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
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitFact(this);
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
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERSISTENT) {
				{
				setState(150);
				match(PERSISTENT);
				}
			}

			setState(153);
			match(IDENTIFIER);
			setState(154);
			match(T__2);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(155);
				term();
				}
			}

			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(158);
				match(T__22);
				setState(159);
				term();
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			match(T__20);
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
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_term);
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				tuple();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(169);
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
		public TerminalNode IDENTIFIER() { return getToken(SourcesParser.IDENTIFIER, 0); }
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
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitFunction(this);
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
			setState(172);
			match(IDENTIFIER);
			setState(173);
			match(T__2);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(174);
				term();
				}
			}

			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(177);
				match(T__22);
				setState(178);
				term();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(T__20);
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
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitTuple(this);
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
			setState(186);
			match(T__24);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(187);
				term();
				}
			}

			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(190);
				match(T__22);
				setState(191);
				term();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
			match(T__25);
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
		public TerminalNode IDENTIFIER() { return getToken(SourcesParser.IDENTIFIER, 0); }
		public TerminalNode NUMBER() { return getToken(SourcesParser.NUMBER, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitVariable(this);
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
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) {
				{
				setState(199);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(202);
			match(IDENTIFIER);
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(203);
				match(T__29);
				setState(204);
				match(NUMBER);
				}
				break;
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

	public static class SubstContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode SUBSTARROW() { return getToken(SourcesParser.SUBSTARROW, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public SubstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitSubst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubstContext subst() throws RecognitionException {
		SubstContext _localctx = new SubstContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_subst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			term();
			setState(208);
			match(SUBSTARROW);
			setState(209);
			match(T__30);
			setState(210);
			variable();
			setState(211);
			match(T__31);
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

	public static class FormulaContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			term();
			setState(214);
			match(T__32);
			setState(215);
			term();
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

	public static class LemmaContext extends ParserRuleContext {
		public LemmaStmtContext lemmaStmt() {
			return getRuleContext(LemmaStmtContext.class,0);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public LemmaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lemma; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitLemma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LemmaContext lemma() throws RecognitionException {
		LemmaContext _localctx = new LemmaContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lemma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(T__33);
			setState(219); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(218);
				variable();
				}
				}
				setState(221); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << IDENTIFIER))) != 0) );
			setState(223);
			match(T__29);
			setState(224);
			lemmaStmt(0);
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

	public static class LemmaStmtContext extends ParserRuleContext {
		public List<LemmaStmtContext> lemmaStmt() {
			return getRuleContexts(LemmaStmtContext.class);
		}
		public LemmaStmtContext lemmaStmt(int i) {
			return getRuleContext(LemmaStmtContext.class,i);
		}
		public GoalContext goal() {
			return getRuleContext(GoalContext.class,0);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public LemmaStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lemmaStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitLemmaStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LemmaStmtContext lemmaStmt() throws RecognitionException {
		return lemmaStmt(0);
	}

	private LemmaStmtContext lemmaStmt(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LemmaStmtContext _localctx = new LemmaStmtContext(_ctx, _parentState);
		LemmaStmtContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_lemmaStmt, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(227);
				match(T__2);
				setState(228);
				lemmaStmt(0);
				setState(229);
				match(T__20);
				}
				break;
			case 2:
				{
				setState(231);
				goal();
				}
				break;
			case 3:
				{
				setState(232);
				variable();
				setState(233);
				match(T__32);
				setState(234);
				variable();
				}
				break;
			case 4:
				{
				setState(236);
				variable();
				setState(237);
				match(T__24);
				setState(238);
				variable();
				}
				break;
			case 5:
				{
				setState(240);
				variable();
				setState(241);
				match(T__25);
				setState(242);
				variable();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(251);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LemmaStmtContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_lemmaStmt);
					setState(246);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(247);
					match(T__34);
					setState(248);
					lemmaStmt(2);
					}
					} 
				}
				setState(253);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class JsonObjContext extends ParserRuleContext {
		public List<JsonKeyValueContext> jsonKeyValue() {
			return getRuleContexts(JsonKeyValueContext.class);
		}
		public JsonKeyValueContext jsonKeyValue(int i) {
			return getRuleContext(JsonKeyValueContext.class,i);
		}
		public JsonObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonObj; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonObj(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonObjContext jsonObj() throws RecognitionException {
		JsonObjContext _localctx = new JsonObjContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_jsonObj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(T__30);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(255);
				jsonKeyValue();
				}
			}

			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(258);
				match(T__22);
				setState(259);
				jsonKeyValue();
				}
				}
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(265);
			match(T__31);
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

	public static class JsonArrayContext extends ParserRuleContext {
		public List<JsonValueContext> jsonValue() {
			return getRuleContexts(JsonValueContext.class);
		}
		public JsonValueContext jsonValue(int i) {
			return getRuleContext(JsonValueContext.class,i);
		}
		public JsonArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArray; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArrayContext jsonArray() throws RecognitionException {
		JsonArrayContext _localctx = new JsonArrayContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_jsonArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(T__35);
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__30) | (1L << T__35) | (1L << T__38) | (1L << T__39))) != 0)) {
				{
				setState(268);
				jsonValue();
				}
			}

			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(271);
				match(T__22);
				setState(272);
				jsonValue();
				}
				}
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(278);
			match(T__36);
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

	public static class JsonKeyValueContext extends ParserRuleContext {
		public JsonKeyContext jsonKey() {
			return getRuleContext(JsonKeyContext.class,0);
		}
		public JsonValueContext jsonValue() {
			return getRuleContext(JsonValueContext.class,0);
		}
		public JsonKeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonKeyValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonKeyValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonKeyValueContext jsonKeyValue() throws RecognitionException {
		JsonKeyValueContext _localctx = new JsonKeyValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_jsonKeyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			jsonKey();
			setState(281);
			match(T__37);
			setState(282);
			jsonValue();
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

	public static class JsonKeyContext extends ParserRuleContext {
		public JsonStringContext jsonString() {
			return getRuleContext(JsonStringContext.class,0);
		}
		public JsonKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonKey; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonKeyContext jsonKey() throws RecognitionException {
		JsonKeyContext _localctx = new JsonKeyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_jsonKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(T__1);
			setState(285);
			jsonString();
			setState(286);
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

	public static class JsonValueContext extends ParserRuleContext {
		public JsonStringContext jsonString() {
			return getRuleContext(JsonStringContext.class,0);
		}
		public JsonObjContext jsonObj() {
			return getRuleContext(JsonObjContext.class,0);
		}
		public JsonArrayContext jsonArray() {
			return getRuleContext(JsonArrayContext.class,0);
		}
		public JsonValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonValueContext jsonValue() throws RecognitionException {
		JsonValueContext _localctx = new JsonValueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_jsonValue);
		try {
			setState(296);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(T__1);
				setState(289);
				jsonString();
				setState(290);
				match(T__1);
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				jsonObj();
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				jsonArray();
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 4);
				{
				setState(294);
				match(T__38);
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 5);
				{
				setState(295);
				match(T__39);
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

	public static class JsonCharsContext extends ParserRuleContext {
		public TerminalNode PERSISTENT() { return getToken(SourcesParser.PERSISTENT, 0); }
		public JsonCharsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonChars; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonChars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonCharsContext jsonChars() throws RecognitionException {
		JsonCharsContext _localctx = new JsonCharsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_jsonChars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__37) | (1L << PERSISTENT))) != 0)) ) {
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

	public static class JsonStringContext extends ParserRuleContext {
		public List<JsonCharsContext> jsonChars() {
			return getRuleContexts(JsonCharsContext.class);
		}
		public JsonCharsContext jsonChars(int i) {
			return getRuleContext(JsonCharsContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(SourcesParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SourcesParser.IDENTIFIER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(SourcesParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(SourcesParser.NUMBER, i);
		}
		public JsonStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonString; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonStringContext jsonString() throws RecognitionException {
		JsonStringContext _localctx = new JsonStringContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_jsonString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__37) | (1L << PERSISTENT) | (1L << NUMBER) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(303);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
				case T__20:
				case T__22:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__31:
				case T__37:
				case PERSISTENT:
					{
					setState(300);
					jsonChars();
					}
					break;
				case IDENTIFIER:
					{
					setState(301);
					match(IDENTIFIER);
					}
					break;
				case NUMBER:
					{
					setState(302);
					match(NUMBER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(307);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return lemmaStmt_sempred((LemmaStmtContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean lemmaStmt_sempred(LemmaStmtContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u0137\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\4H\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4R\n\4\f\4\16"+
		"\4U\13\4\3\4\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\4\3\4\3\4\7\4b\n\4\f\4"+
		"\16\4e\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4m\n\4\f\4\16\4p\13\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4z\n\4\3\4\3\4\7\4~\n\4\f\4\16\4\u0081\13\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\5\u0097\n\5\3\6\5\6\u009a\n\6\3\6\3\6\3\6\5\6\u009f\n\6\3\6"+
		"\3\6\7\6\u00a3\n\6\f\6\16\6\u00a6\13\6\3\6\3\6\3\7\3\7\3\7\5\7\u00ad\n"+
		"\7\3\b\3\b\3\b\5\b\u00b2\n\b\3\b\3\b\7\b\u00b6\n\b\f\b\16\b\u00b9\13\b"+
		"\3\b\3\b\3\t\3\t\5\t\u00bf\n\t\3\t\3\t\7\t\u00c3\n\t\f\t\16\t\u00c6\13"+
		"\t\3\t\3\t\3\n\5\n\u00cb\n\n\3\n\3\n\3\n\5\n\u00d0\n\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\6\r\u00de\n\r\r\r\16\r\u00df\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00f7\n\16\3\16\3\16\3\16\7\16\u00fc"+
		"\n\16\f\16\16\16\u00ff\13\16\3\17\3\17\5\17\u0103\n\17\3\17\3\17\7\17"+
		"\u0107\n\17\f\17\16\17\u010a\13\17\3\17\3\17\3\20\3\20\5\20\u0110\n\20"+
		"\3\20\3\20\7\20\u0114\n\20\f\20\16\20\u0117\13\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u012b\n\23\3\24\3\24\3\25\3\25\3\25\7\25\u0132\n\25\f\25\16\25\u0135"+
		"\13\25\3\25\2\3\32\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\4"+
		"\3\2\35\37\b\2\5\5\27\27\31\31\33\"((--\2\u0148\2-\3\2\2\2\4\60\3\2\2"+
		"\2\6=\3\2\2\2\b\u0096\3\2\2\2\n\u0099\3\2\2\2\f\u00ac\3\2\2\2\16\u00ae"+
		"\3\2\2\2\20\u00bc\3\2\2\2\22\u00ca\3\2\2\2\24\u00d1\3\2\2\2\26\u00d7\3"+
		"\2\2\2\30\u00db\3\2\2\2\32\u00f6\3\2\2\2\34\u0100\3\2\2\2\36\u010d\3\2"+
		"\2\2 \u011a\3\2\2\2\"\u011e\3\2\2\2$\u012a\3\2\2\2&\u012c\3\2\2\2(\u0133"+
		"\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\3\3\2\2\2"+
		"/-\3\2\2\2\60\61\7\3\2\2\61\62\7\4\2\2\62\63\5\b\5\2\63\64\7\4\2\2\64"+
		"\65\7\5\2\2\65\66\7\61\2\2\66:\7\6\2\2\679\5\6\4\28\67\3\2\2\29<\3\2\2"+
		"\2:8\3\2\2\2:;\3\2\2\2;\5\3\2\2\2<:\3\2\2\2=>\7\7\2\2>?\7\61\2\2?@\7\b"+
		"\2\2@A\7\61\2\2AB\7\t\2\2BC\7\n\2\2CD\7\4\2\2DE\7\62\2\2EG\7\4\2\2FH\7"+
		"/\2\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\4\2\2JK\5\b\5\2KL\7\4\2\2LM\7"+
		"\13\2\2MN\7\f\2\2NO\7\r\2\2OS\7\16\2\2PR\5\24\13\2QP\3\2\2\2RU\3\2\2\2"+
		"SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\7\17\2\2W[\7\20\2\2XZ\5\30"+
		"\r\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^_\7"+
		"\21\2\2_c\7\22\2\2`b\5\26\f\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2"+
		"df\3\2\2\2ec\3\2\2\2fn\7\23\2\2gh\5\b\5\2hi\7\24\2\2ij\7\61\2\2jk\7\60"+
		"\2\2km\3\2\2\2lg\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2"+
		"\2\2q\177\7\25\2\2rs\5\b\5\2st\7\24\2\2ty\7\61\2\2uv\7\5\2\2vw\7\26\2"+
		"\2wx\7\62\2\2xz\7\27\2\2yu\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\60\2\2|~\3"+
		"\2\2\2}r\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7\30\2\2\u0083\u0084\5\34\17\2"+
		"\u0084\u0085\7+\2\2\u0085\7\3\2\2\2\u0086\u0087\5\n\6\2\u0087\u0088\7"+
		",\2\2\u0088\u0089\5\22\n\2\u0089\u0097\3\2\2\2\u008a\u008b\7\5\2\2\u008b"+
		"\u008c\5\22\n\2\u008c\u008d\7\31\2\2\u008d\u008e\7\61\2\2\u008e\u008f"+
		"\7\27\2\2\u008f\u0090\7\32\2\2\u0090\u0091\7\5\2\2\u0091\u0092\5\22\n"+
		"\2\u0092\u0093\7\31\2\2\u0093\u0094\7\61\2\2\u0094\u0095\7\27\2\2\u0095"+
		"\u0097\3\2\2\2\u0096\u0086\3\2\2\2\u0096\u008a\3\2\2\2\u0097\t\3\2\2\2"+
		"\u0098\u009a\7-\2\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b"+
		"\3\2\2\2\u009b\u009c\7\62\2\2\u009c\u009e\7\5\2\2\u009d\u009f\5\f\7\2"+
		"\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a4\3\2\2\2\u00a0\u00a1"+
		"\7\31\2\2\u00a1\u00a3\5\f\7\2\u00a2\u00a0\3\2\2\2\u00a3\u00a6\3\2\2\2"+
		"\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4"+
		"\3\2\2\2\u00a7\u00a8\7\27\2\2\u00a8\13\3\2\2\2\u00a9\u00ad\5\16\b\2\u00aa"+
		"\u00ad\5\20\t\2\u00ab\u00ad\5\22\n\2\u00ac\u00a9\3\2\2\2\u00ac\u00aa\3"+
		"\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\r\3\2\2\2\u00ae\u00af\7\62\2\2\u00af"+
		"\u00b1\7\5\2\2\u00b0\u00b2\5\f\7\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b7\3\2\2\2\u00b3\u00b4\7\31\2\2\u00b4\u00b6\5\f\7\2\u00b5"+
		"\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7\27\2\2\u00bb"+
		"\17\3\2\2\2\u00bc\u00be\7\33\2\2\u00bd\u00bf\5\f\7\2\u00be\u00bd\3\2\2"+
		"\2\u00be\u00bf\3\2\2\2\u00bf\u00c4\3\2\2\2\u00c0\u00c1\7\31\2\2\u00c1"+
		"\u00c3\5\f\7\2\u00c2\u00c0\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7"+
		"\u00c8\7\34\2\2\u00c8\21\3\2\2\2\u00c9\u00cb\t\2\2\2\u00ca\u00c9\3\2\2"+
		"\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cf\7\62\2\2\u00cd"+
		"\u00ce\7 \2\2\u00ce\u00d0\7\61\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\23\3\2\2\2\u00d1\u00d2\5\f\7\2\u00d2\u00d3\7.\2\2\u00d3\u00d4"+
		"\7!\2\2\u00d4\u00d5\5\22\n\2\u00d5\u00d6\7\"\2\2\u00d6\25\3\2\2\2\u00d7"+
		"\u00d8\5\f\7\2\u00d8\u00d9\7#\2\2\u00d9\u00da\5\f\7\2\u00da\27\3\2\2\2"+
		"\u00db\u00dd\7$\2\2\u00dc\u00de\5\22\n\2\u00dd\u00dc\3\2\2\2\u00de\u00df"+
		"\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e2\7 \2\2\u00e2\u00e3\5\32\16\2\u00e3\31\3\2\2\2\u00e4\u00e5\b\16"+
		"\1\2\u00e5\u00e6\7\5\2\2\u00e6\u00e7\5\32\16\2\u00e7\u00e8\7\27\2\2\u00e8"+
		"\u00f7\3\2\2\2\u00e9\u00f7\5\b\5\2\u00ea\u00eb\5\22\n\2\u00eb\u00ec\7"+
		"#\2\2\u00ec\u00ed\5\22\n\2\u00ed\u00f7\3\2\2\2\u00ee\u00ef\5\22\n\2\u00ef"+
		"\u00f0\7\33\2\2\u00f0\u00f1\5\22\n\2\u00f1\u00f7\3\2\2\2\u00f2\u00f3\5"+
		"\22\n\2\u00f3\u00f4\7\34\2\2\u00f4\u00f5\5\22\n\2\u00f5\u00f7\3\2\2\2"+
		"\u00f6\u00e4\3\2\2\2\u00f6\u00e9\3\2\2\2\u00f6\u00ea\3\2\2\2\u00f6\u00ee"+
		"\3\2\2\2\u00f6\u00f2\3\2\2\2\u00f7\u00fd\3\2\2\2\u00f8\u00f9\f\3\2\2\u00f9"+
		"\u00fa\7%\2\2\u00fa\u00fc\5\32\16\4\u00fb\u00f8\3\2\2\2\u00fc\u00ff\3"+
		"\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\33\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\u0102\7!\2\2\u0101\u0103\5 \21\2\u0102\u0101\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0108\3\2\2\2\u0104\u0105\7\31\2\2\u0105"+
		"\u0107\5 \21\2\u0106\u0104\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2"+
		"\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b"+
		"\u010c\7\"\2\2\u010c\35\3\2\2\2\u010d\u010f\7&\2\2\u010e\u0110\5$\23\2"+
		"\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0115\3\2\2\2\u0111\u0112"+
		"\7\31\2\2\u0112\u0114\5$\23\2\u0113\u0111\3\2\2\2\u0114\u0117\3\2\2\2"+
		"\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0115"+
		"\3\2\2\2\u0118\u0119\7\'\2\2\u0119\37\3\2\2\2\u011a\u011b\5\"\22\2\u011b"+
		"\u011c\7(\2\2\u011c\u011d\5$\23\2\u011d!\3\2\2\2\u011e\u011f\7\4\2\2\u011f"+
		"\u0120\5(\25\2\u0120\u0121\7\4\2\2\u0121#\3\2\2\2\u0122\u0123\7\4\2\2"+
		"\u0123\u0124\5(\25\2\u0124\u0125\7\4\2\2\u0125\u012b\3\2\2\2\u0126\u012b"+
		"\5\34\17\2\u0127\u012b\5\36\20\2\u0128\u012b\7)\2\2\u0129\u012b\7*\2\2"+
		"\u012a\u0122\3\2\2\2\u012a\u0126\3\2\2\2\u012a\u0127\3\2\2\2\u012a\u0128"+
		"\3\2\2\2\u012a\u0129\3\2\2\2\u012b%\3\2\2\2\u012c\u012d\t\3\2\2\u012d"+
		"\'\3\2\2\2\u012e\u0132\5&\24\2\u012f\u0132\7\62\2\2\u0130\u0132\7\61\2"+
		"\2\u0131\u012e\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0130\3\2\2\2\u0132\u0135"+
		"\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134)\3\2\2\2\u0135"+
		"\u0133\3\2\2\2 -:GS[cny\177\u0096\u0099\u009e\u00a4\u00ac\u00b1\u00b7"+
		"\u00be\u00c4\u00ca\u00cf\u00df\u00f6\u00fd\u0102\u0108\u010f\u0115\u012a"+
		"\u0131\u0133";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}