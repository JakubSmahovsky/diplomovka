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
		T__38=39, SEPARATOR=40, ATTIMEPOINT=41, PERSISTENT=42, SUBSTARROW=43, 
		USEFUL=44, NUMBER=45, IDENTIFIER=46, WHITESPACE=47;
	public static final int
		RULE_sources = 0, RULE_group = 1, RULE_source = 2, RULE_goal = 3, RULE_fact = 4, 
		RULE_term = 5, RULE_function = 6, RULE_tuple = 7, RULE_variable = 8, RULE_subst = 9, 
		RULE_formula = 10, RULE_lemma = 11, RULE_lemmaStmt = 12, RULE_jsonchars = 13, 
		RULE_jsonword = 14, RULE_json = 15, RULE_jsonKeyValue = 16, RULE_jsonValue = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"sources", "group", "source", "goal", "fact", "term", "function", "tuple", 
			"variable", "subst", "formula", "lemma", "lemmaStmt", "jsonchars", "jsonword", 
			"json", "jsonKeyValue", "jsonValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Sources of'", "'\"'", "'('", "'cases)'", "'Source'", "'of'", 
			"'/'", "'named'", "'last: none'", "'formulas:'", "'equations:'", "'subst:'", 
			"'conj:'", "'lemmas:'", "'allowed cases: refined'", "'solved formulas:'", 
			"'unsolved goals:'", "'// nr:'", "'solved goals:'", "'from rule'", "')'", 
			"'json graph:'", "','", "'<'", "'>'", "'$'", "'~'", "'#'", "'.'", "'{'", 
			"'}'", "'='", "'\u2200'", "'\u21D2'", "':'", "'true'", "'false'", "'['", 
			"']'", "'------------------------------------------------------------------------------'", 
			null, "'!'", "'<~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "SEPARATOR", "ATTIMEPOINT", "PERSISTENT", "SUBSTARROW", 
			"USEFUL", "NUMBER", "IDENTIFIER", "WHITESPACE"
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
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(36);
				group();
				}
				}
				setState(41);
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
			setState(42);
			match(T__0);
			setState(43);
			match(T__1);
			setState(44);
			goal();
			setState(45);
			match(T__1);
			setState(46);
			match(T__2);
			setState(47);
			match(NUMBER);
			setState(48);
			match(T__3);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(49);
				source();
				}
				}
				setState(54);
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
		public List<TerminalNode> NUMBER() { return getTokens(SourcesParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(SourcesParser.NUMBER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(SourcesParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SourcesParser.IDENTIFIER, i);
		}
		public List<GoalContext> goal() {
			return getRuleContexts(GoalContext.class);
		}
		public GoalContext goal(int i) {
			return getRuleContext(GoalContext.class,i);
		}
		public JsonContext json() {
			return getRuleContext(JsonContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(SourcesParser.SEPARATOR, 0); }
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
			setState(55);
			match(T__4);
			setState(56);
			match(NUMBER);
			setState(57);
			match(T__5);
			setState(58);
			match(NUMBER);
			setState(59);
			match(T__6);
			setState(60);
			match(T__7);
			setState(61);
			match(T__1);
			setState(62);
			match(IDENTIFIER);
			setState(63);
			match(T__1);
			setState(64);
			match(T__1);
			setState(65);
			goal();
			setState(66);
			match(T__1);
			setState(67);
			match(T__8);
			setState(68);
			match(T__9);
			setState(69);
			match(T__10);
			setState(70);
			match(T__11);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(71);
				subst();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(T__12);
			setState(78);
			match(T__13);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__32) {
				{
				{
				setState(79);
				lemma();
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85);
			match(T__14);
			setState(86);
			match(T__15);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(87);
				formula();
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
			match(T__16);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PERSISTENT || _la==IDENTIFIER) {
				{
				{
				setState(94);
				goal();
				setState(95);
				match(T__17);
				setState(96);
				match(NUMBER);
				setState(97);
				match(USEFUL);
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(T__18);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PERSISTENT || _la==IDENTIFIER) {
				{
				{
				setState(105);
				goal();
				setState(106);
				match(T__17);
				setState(107);
				match(NUMBER);
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(108);
					match(T__2);
					setState(109);
					match(T__19);
					setState(110);
					match(IDENTIFIER);
					setState(111);
					match(T__20);
					}
				}

				setState(114);
				match(USEFUL);
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			match(T__21);
			setState(122);
			json();
			setState(123);
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			fact();
			setState(126);
			match(ATTIMEPOINT);
			setState(127);
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
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERSISTENT) {
				{
				setState(129);
				match(PERSISTENT);
				}
			}

			setState(132);
			match(IDENTIFIER);
			setState(133);
			match(T__2);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(134);
				term();
				}
			}

			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(137);
				match(T__22);
				setState(138);
				term();
				}
				}
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(144);
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
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				tuple();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
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
			setState(151);
			match(IDENTIFIER);
			setState(152);
			match(T__2);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(153);
				term();
				}
			}

			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(156);
				match(T__22);
				setState(157);
				term();
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(163);
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
			setState(165);
			match(T__23);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(166);
				term();
				}
			}

			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(169);
				match(T__22);
				setState(170);
				term();
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(176);
			match(T__24);
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
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) {
				{
				setState(178);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(181);
			match(IDENTIFIER);
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(182);
				match(T__28);
				setState(183);
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
			setState(186);
			term();
			setState(187);
			match(SUBSTARROW);
			setState(188);
			match(T__29);
			setState(189);
			variable();
			setState(190);
			match(T__30);
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
			setState(192);
			term();
			setState(193);
			match(T__31);
			setState(194);
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
			setState(196);
			match(T__32);
			setState(198); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(197);
				variable();
				}
				}
				setState(200); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << IDENTIFIER))) != 0) );
			setState(202);
			match(T__28);
			setState(203);
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
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(206);
				match(T__2);
				setState(207);
				lemmaStmt(0);
				setState(208);
				match(T__20);
				}
				break;
			case 2:
				{
				setState(210);
				goal();
				}
				break;
			case 3:
				{
				setState(211);
				variable();
				setState(212);
				match(T__31);
				setState(213);
				variable();
				}
				break;
			case 4:
				{
				setState(215);
				variable();
				setState(216);
				match(T__23);
				setState(217);
				variable();
				}
				break;
			case 5:
				{
				setState(219);
				variable();
				setState(220);
				match(T__24);
				setState(221);
				variable();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LemmaStmtContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_lemmaStmt);
					setState(225);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(226);
					match(T__33);
					setState(227);
					lemmaStmt(2);
					}
					} 
				}
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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

	public static class JsoncharsContext extends ParserRuleContext {
		public TerminalNode PERSISTENT() { return getToken(SourcesParser.PERSISTENT, 0); }
		public JsoncharsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonchars; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonchars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsoncharsContext jsonchars() throws RecognitionException {
		JsoncharsContext _localctx = new JsoncharsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_jsonchars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << PERSISTENT))) != 0)) ) {
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

	public static class JsonwordContext extends ParserRuleContext {
		public List<JsoncharsContext> jsonchars() {
			return getRuleContexts(JsoncharsContext.class);
		}
		public JsoncharsContext jsonchars(int i) {
			return getRuleContext(JsoncharsContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(SourcesParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SourcesParser.IDENTIFIER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(SourcesParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(SourcesParser.NUMBER, i);
		}
		public JsonwordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonword; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJsonword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonwordContext jsonword() throws RecognitionException {
		JsonwordContext _localctx = new JsonwordContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_jsonword);
		int _la;
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				match(T__1);
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << PERSISTENT) | (1L << NUMBER) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(239);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__2:
					case T__20:
					case T__22:
					case T__23:
					case T__24:
					case T__25:
					case T__26:
					case T__27:
					case T__28:
					case T__29:
					case T__30:
					case T__34:
					case PERSISTENT:
						{
						setState(236);
						jsonchars();
						}
						break;
					case IDENTIFIER:
						{
						setState(237);
						match(IDENTIFIER);
						}
						break;
					case NUMBER:
						{
						setState(238);
						match(NUMBER);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(243);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(244);
				match(T__1);
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(T__35);
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				match(T__36);
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

	public static class JsonContext extends ParserRuleContext {
		public List<JsonKeyValueContext> jsonKeyValue() {
			return getRuleContexts(JsonKeyValueContext.class);
		}
		public JsonKeyValueContext jsonKeyValue(int i) {
			return getRuleContext(JsonKeyValueContext.class,i);
		}
		public JsonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitJson(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonContext json() throws RecognitionException {
		JsonContext _localctx = new JsonContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_json);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(T__29);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__35) | (1L << T__36))) != 0)) {
				{
				setState(250);
				jsonKeyValue();
				}
			}

			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(253);
				match(T__22);
				setState(254);
				jsonKeyValue();
				}
				}
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(260);
			match(T__30);
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
		public JsonwordContext jsonword() {
			return getRuleContext(JsonwordContext.class,0);
		}
		public List<JsonValueContext> jsonValue() {
			return getRuleContexts(JsonValueContext.class);
		}
		public JsonValueContext jsonValue(int i) {
			return getRuleContext(JsonValueContext.class,i);
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
		enterRule(_localctx, 32, RULE_jsonKeyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			jsonword();
			setState(263);
			match(T__34);
			setState(277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__29:
			case T__35:
			case T__36:
				{
				setState(264);
				jsonValue();
				}
				break;
			case T__37:
				{
				setState(265);
				match(T__37);
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__29) | (1L << T__35) | (1L << T__36))) != 0)) {
					{
					setState(266);
					jsonValue();
					}
				}

				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__22) {
					{
					{
					setState(269);
					match(T__22);
					setState(270);
					jsonValue();
					}
					}
					setState(275);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(276);
				match(T__38);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class JsonValueContext extends ParserRuleContext {
		public JsonContext json() {
			return getRuleContext(JsonContext.class,0);
		}
		public JsonwordContext jsonword() {
			return getRuleContext(JsonwordContext.class,0);
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
			setState(281);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__29:
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				json();
				}
				break;
			case T__1:
			case T__35:
			case T__36:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				jsonword();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u011e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\7\3\65\n\3\f\3\16\38\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\7\4K\n\4\f\4\16\4N\13\4\3\4\3\4\3\4\7\4S\n\4"+
		"\f\4\16\4V\13\4\3\4\3\4\3\4\7\4[\n\4\f\4\16\4^\13\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\7\4f\n\4\f\4\16\4i\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4s\n"+
		"\4\3\4\3\4\7\4w\n\4\f\4\16\4z\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6"+
		"\5\6\u0085\n\6\3\6\3\6\3\6\5\6\u008a\n\6\3\6\3\6\7\6\u008e\n\6\f\6\16"+
		"\6\u0091\13\6\3\6\3\6\3\7\3\7\3\7\5\7\u0098\n\7\3\b\3\b\3\b\5\b\u009d"+
		"\n\b\3\b\3\b\7\b\u00a1\n\b\f\b\16\b\u00a4\13\b\3\b\3\b\3\t\3\t\5\t\u00aa"+
		"\n\t\3\t\3\t\7\t\u00ae\n\t\f\t\16\t\u00b1\13\t\3\t\3\t\3\n\5\n\u00b6\n"+
		"\n\3\n\3\n\3\n\5\n\u00bb\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\6\r\u00c9\n\r\r\r\16\r\u00ca\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\5\16\u00e2\n\16\3\16\3\16\3\16\7\16\u00e7\n\16\f\16\16\16\u00ea\13"+
		"\16\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00f2\n\20\f\20\16\20\u00f5\13"+
		"\20\3\20\3\20\3\20\5\20\u00fa\n\20\3\21\3\21\5\21\u00fe\n\21\3\21\3\21"+
		"\7\21\u0102\n\21\f\21\16\21\u0105\13\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u010e\n\22\3\22\3\22\7\22\u0112\n\22\f\22\16\22\u0115\13\22"+
		"\3\22\5\22\u0118\n\22\3\23\3\23\5\23\u011c\n\23\3\23\2\3\32\24\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$\2\4\3\2\34\36\7\2\5\5\27\27\31!%%"+
		",,\2\u012f\2)\3\2\2\2\4,\3\2\2\2\69\3\2\2\2\b\177\3\2\2\2\n\u0084\3\2"+
		"\2\2\f\u0097\3\2\2\2\16\u0099\3\2\2\2\20\u00a7\3\2\2\2\22\u00b5\3\2\2"+
		"\2\24\u00bc\3\2\2\2\26\u00c2\3\2\2\2\30\u00c6\3\2\2\2\32\u00e1\3\2\2\2"+
		"\34\u00eb\3\2\2\2\36\u00f9\3\2\2\2 \u00fb\3\2\2\2\"\u0108\3\2\2\2$\u011b"+
		"\3\2\2\2&(\5\4\3\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\3\3\2\2"+
		"\2+)\3\2\2\2,-\7\3\2\2-.\7\4\2\2./\5\b\5\2/\60\7\4\2\2\60\61\7\5\2\2\61"+
		"\62\7/\2\2\62\66\7\6\2\2\63\65\5\6\4\2\64\63\3\2\2\2\658\3\2\2\2\66\64"+
		"\3\2\2\2\66\67\3\2\2\2\67\5\3\2\2\28\66\3\2\2\29:\7\7\2\2:;\7/\2\2;<\7"+
		"\b\2\2<=\7/\2\2=>\7\t\2\2>?\7\n\2\2?@\7\4\2\2@A\7\60\2\2AB\7\4\2\2BC\7"+
		"\4\2\2CD\5\b\5\2DE\7\4\2\2EF\7\13\2\2FG\7\f\2\2GH\7\r\2\2HL\7\16\2\2I"+
		"K\5\24\13\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2"+
		"\2OP\7\17\2\2PT\7\20\2\2QS\5\30\r\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3"+
		"\2\2\2UW\3\2\2\2VT\3\2\2\2WX\7\21\2\2X\\\7\22\2\2Y[\5\26\f\2ZY\3\2\2\2"+
		"[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_g\7\23\2\2`a\5\b"+
		"\5\2ab\7\24\2\2bc\7/\2\2cd\7.\2\2df\3\2\2\2e`\3\2\2\2fi\3\2\2\2ge\3\2"+
		"\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jx\7\25\2\2kl\5\b\5\2lm\7\24\2\2mr\7"+
		"/\2\2no\7\5\2\2op\7\26\2\2pq\7\60\2\2qs\7\27\2\2rn\3\2\2\2rs\3\2\2\2s"+
		"t\3\2\2\2tu\7.\2\2uw\3\2\2\2vk\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y"+
		"{\3\2\2\2zx\3\2\2\2{|\7\30\2\2|}\5 \21\2}~\7*\2\2~\7\3\2\2\2\177\u0080"+
		"\5\n\6\2\u0080\u0081\7+\2\2\u0081\u0082\5\22\n\2\u0082\t\3\2\2\2\u0083"+
		"\u0085\7,\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2"+
		"\2\2\u0086\u0087\7\60\2\2\u0087\u0089\7\5\2\2\u0088\u008a\5\f\7\2\u0089"+
		"\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008f\3\2\2\2\u008b\u008c\7\31"+
		"\2\2\u008c\u008e\5\f\7\2\u008d\u008b\3\2\2\2\u008e\u0091\3\2\2\2\u008f"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2"+
		"\2\2\u0092\u0093\7\27\2\2\u0093\13\3\2\2\2\u0094\u0098\5\16\b\2\u0095"+
		"\u0098\5\20\t\2\u0096\u0098\5\22\n\2\u0097\u0094\3\2\2\2\u0097\u0095\3"+
		"\2\2\2\u0097\u0096\3\2\2\2\u0098\r\3\2\2\2\u0099\u009a\7\60\2\2\u009a"+
		"\u009c\7\5\2\2\u009b\u009d\5\f\7\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u00a2\3\2\2\2\u009e\u009f\7\31\2\2\u009f\u00a1\5\f\7\2\u00a0"+
		"\u009e\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7\27\2\2\u00a6"+
		"\17\3\2\2\2\u00a7\u00a9\7\32\2\2\u00a8\u00aa\5\f\7\2\u00a9\u00a8\3\2\2"+
		"\2\u00a9\u00aa\3\2\2\2\u00aa\u00af\3\2\2\2\u00ab\u00ac\7\31\2\2\u00ac"+
		"\u00ae\5\f\7\2\u00ad\u00ab\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2"+
		"\u00b3\7\33\2\2\u00b3\21\3\2\2\2\u00b4\u00b6\t\2\2\2\u00b5\u00b4\3\2\2"+
		"\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00ba\7\60\2\2\u00b8"+
		"\u00b9\7\37\2\2\u00b9\u00bb\7/\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\23\3\2\2\2\u00bc\u00bd\5\f\7\2\u00bd\u00be\7-\2\2\u00be\u00bf"+
		"\7 \2\2\u00bf\u00c0\5\22\n\2\u00c0\u00c1\7!\2\2\u00c1\25\3\2\2\2\u00c2"+
		"\u00c3\5\f\7\2\u00c3\u00c4\7\"\2\2\u00c4\u00c5\5\f\7\2\u00c5\27\3\2\2"+
		"\2\u00c6\u00c8\7#\2\2\u00c7\u00c9\5\22\n\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca"+
		"\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00cd\7\37\2\2\u00cd\u00ce\5\32\16\2\u00ce\31\3\2\2\2\u00cf\u00d0\b\16"+
		"\1\2\u00d0\u00d1\7\5\2\2\u00d1\u00d2\5\32\16\2\u00d2\u00d3\7\27\2\2\u00d3"+
		"\u00e2\3\2\2\2\u00d4\u00e2\5\b\5\2\u00d5\u00d6\5\22\n\2\u00d6\u00d7\7"+
		"\"\2\2\u00d7\u00d8\5\22\n\2\u00d8\u00e2\3\2\2\2\u00d9\u00da\5\22\n\2\u00da"+
		"\u00db\7\32\2\2\u00db\u00dc\5\22\n\2\u00dc\u00e2\3\2\2\2\u00dd\u00de\5"+
		"\22\n\2\u00de\u00df\7\33\2\2\u00df\u00e0\5\22\n\2\u00e0\u00e2\3\2\2\2"+
		"\u00e1\u00cf\3\2\2\2\u00e1\u00d4\3\2\2\2\u00e1\u00d5\3\2\2\2\u00e1\u00d9"+
		"\3\2\2\2\u00e1\u00dd\3\2\2\2\u00e2\u00e8\3\2\2\2\u00e3\u00e4\f\3\2\2\u00e4"+
		"\u00e5\7$\2\2\u00e5\u00e7\5\32\16\4\u00e6\u00e3\3\2\2\2\u00e7\u00ea\3"+
		"\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\33\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ec\t\3\2\2\u00ec\35\3\2\2\2\u00ed\u00f3\7\4\2"+
		"\2\u00ee\u00f2\5\34\17\2\u00ef\u00f2\7\60\2\2\u00f0\u00f2\7/\2\2\u00f1"+
		"\u00ee\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f5\3\2"+
		"\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f6\3\2\2\2\u00f5"+
		"\u00f3\3\2\2\2\u00f6\u00fa\7\4\2\2\u00f7\u00fa\7&\2\2\u00f8\u00fa\7\'"+
		"\2\2\u00f9\u00ed\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa"+
		"\37\3\2\2\2\u00fb\u00fd\7 \2\2\u00fc\u00fe\5\"\22\2\u00fd\u00fc\3\2\2"+
		"\2\u00fd\u00fe\3\2\2\2\u00fe\u0103\3\2\2\2\u00ff\u0100\7\31\2\2\u0100"+
		"\u0102\5\"\22\2\u0101\u00ff\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3"+
		"\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u0103\3\2\2\2\u0106"+
		"\u0107\7!\2\2\u0107!\3\2\2\2\u0108\u0109\5\36\20\2\u0109\u0117\7%\2\2"+
		"\u010a\u0118\5$\23\2\u010b\u010d\7(\2\2\u010c\u010e\5$\23\2\u010d\u010c"+
		"\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u0113\3\2\2\2\u010f\u0110\7\31\2\2"+
		"\u0110\u0112\5$\23\2\u0111\u010f\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111"+
		"\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116"+
		"\u0118\7)\2\2\u0117\u010a\3\2\2\2\u0117\u010b\3\2\2\2\u0118#\3\2\2\2\u0119"+
		"\u011c\5 \21\2\u011a\u011c\5\36\20\2\u011b\u0119\3\2\2\2\u011b\u011a\3"+
		"\2\2\2\u011c%\3\2\2\2 )\66LT\\grx\u0084\u0089\u008f\u0097\u009c\u00a2"+
		"\u00a9\u00af\u00b5\u00ba\u00ca\u00e1\u00e8\u00f1\u00f3\u00f9\u00fd\u0103"+
		"\u010d\u0113\u0117\u011b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}