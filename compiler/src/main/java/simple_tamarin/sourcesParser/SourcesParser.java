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
		T__38=39, T__39=40, T__40=41, SEPARATOR=42, ATTIMEPOINT=43, PERSISTENT=44, 
		PARTIAL_DECONSTRUCTIONS=45, USEFUL=46, NUMBER=47, IDENTIFIER=48, WHITESPACE=49;
	public static final int
		RULE_sources = 0, RULE_group = 1, RULE_source = 2, RULE_goal = 3, RULE_fact = 4, 
		RULE_term = 5, RULE_constant = 6, RULE_function = 7, RULE_tuple = 8, RULE_variable = 9, 
		RULE_subst = 10, RULE_formula = 11, RULE_lemma = 12, RULE_lemmaStmt = 13, 
		RULE_jsonObj = 14, RULE_jsonArray = 15, RULE_jsonKeyValue = 16, RULE_jsonKey = 17, 
		RULE_jsonValue = 18, RULE_jsonChars = 19, RULE_jsonString = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"sources", "group", "source", "goal", "fact", "term", "constant", "function", 
			"tuple", "variable", "subst", "formula", "lemma", "lemmaStmt", "jsonObj", 
			"jsonArray", "jsonKeyValue", "jsonKey", "jsonValue", "jsonChars", "jsonString"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Sources of'", "'\"'", "'('", "'cases)'", "'Source'", "'of'", 
			"'/'", "'named'", "'last: none'", "'formulas:'", "'equations:'", "'subst:'", 
			"'conj:'", "'lemmas:'", "'allowed cases: refined'", "'solved formulas:'", 
			"'unsolved goals:'", "'// nr:'", "'solved goals:'", "'from rule'", "')'", 
			"'json graph:'", "','", "'~~>'", "'''", "'<'", "'>'", "'$'", "'~'", "'#'", 
			"'.'", "'{'", "'}'", "'='", "'\u2200'", "'\u21D2'", "'['", "']'", "':'", 
			"'true'", "'false'", "'------------------------------------------------------------------------------'", 
			null, "'!'", "'(partial deconstructions)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "SEPARATOR", "ATTIMEPOINT", "PERSISTENT", 
			"PARTIAL_DECONSTRUCTIONS", "USEFUL", "NUMBER", "IDENTIFIER", "WHITESPACE"
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
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(42);
				group();
				}
				}
				setState(47);
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
			setState(48);
			match(T__0);
			setState(49);
			match(T__1);
			setState(50);
			goal();
			setState(51);
			match(T__1);
			setState(52);
			match(T__2);
			setState(53);
			match(NUMBER);
			setState(54);
			match(T__3);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(55);
				source();
				}
				}
				setState(60);
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
			setState(61);
			match(T__4);
			setState(62);
			match(NUMBER);
			setState(63);
			match(T__5);
			setState(64);
			match(NUMBER);
			setState(65);
			match(T__6);
			setState(66);
			match(T__7);
			setState(67);
			match(T__1);
			setState(68);
			((SourceContext)_localctx).name = match(IDENTIFIER);
			setState(69);
			match(T__1);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PARTIAL_DECONSTRUCTIONS) {
				{
				setState(70);
				match(PARTIAL_DECONSTRUCTIONS);
				}
			}

			setState(73);
			match(T__1);
			setState(74);
			goal();
			setState(75);
			match(T__1);
			setState(76);
			match(T__8);
			setState(77);
			match(T__9);
			setState(78);
			match(T__10);
			setState(79);
			match(T__11);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(80);
				subst();
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86);
			match(T__12);
			setState(87);
			match(T__13);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__34) {
				{
				{
				setState(88);
				lemma();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(94);
			match(T__14);
			setState(95);
			match(T__15);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(96);
				formula();
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102);
			match(T__16);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << PERSISTENT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(103);
				goal();
				setState(104);
				match(T__17);
				setState(105);
				match(NUMBER);
				setState(106);
				match(USEFUL);
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113);
			match(T__18);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << PERSISTENT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(114);
				goal();
				setState(115);
				match(T__17);
				setState(116);
				match(NUMBER);
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(117);
					match(T__2);
					setState(118);
					match(T__19);
					setState(119);
					match(IDENTIFIER);
					setState(120);
					match(T__20);
					}
				}

				setState(123);
				match(USEFUL);
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130);
			match(T__21);
			setState(131);
			jsonObj();
			setState(132);
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
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PERSISTENT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				fact();
				setState(135);
				match(ATTIMEPOINT);
				setState(136);
				variable();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				match(T__2);
				setState(139);
				variable();
				setState(140);
				match(T__22);
				setState(141);
				match(NUMBER);
				setState(142);
				match(T__20);
				setState(143);
				match(T__23);
				setState(144);
				match(T__2);
				setState(145);
				variable();
				setState(146);
				match(T__22);
				setState(147);
				match(NUMBER);
				setState(148);
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
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERSISTENT) {
				{
				setState(152);
				match(PERSISTENT);
				}
			}

			setState(155);
			match(IDENTIFIER);
			setState(156);
			match(T__2);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(157);
				term();
				}
			}

			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(160);
				match(T__22);
				setState(161);
				term();
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
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
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
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
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(172);
				tuple();
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

	public static class ConstantContext extends ParserRuleContext {
		public Token word;
		public TerminalNode IDENTIFIER() { return getToken(SourcesParser.IDENTIFIER, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__24);
			setState(176);
			((ConstantContext)_localctx).word = match(IDENTIFIER);
			setState(177);
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
		enterRule(_localctx, 14, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(IDENTIFIER);
			setState(180);
			match(T__2);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(181);
				term();
				}
			}

			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(184);
				match(T__22);
				setState(185);
				term();
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191);
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
		enterRule(_localctx, 16, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(T__25);
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(194);
				term();
				}
			}

			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(197);
				match(T__22);
				setState(198);
				term();
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			match(T__26);
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
		enterRule(_localctx, 18, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) {
				{
				setState(206);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(209);
			match(IDENTIFIER);
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(210);
				match(T__30);
				setState(211);
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
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
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
		enterRule(_localctx, 20, RULE_subst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			term();
			setState(215);
			match(T__25);
			setState(216);
			match(T__28);
			setState(217);
			match(T__31);
			setState(218);
			variable();
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(219);
				match(T__22);
				setState(220);
				variable();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(T__32);
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
		enterRule(_localctx, 22, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			term();
			setState(229);
			match(T__33);
			setState(230);
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
		enterRule(_localctx, 24, RULE_lemma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(T__34);
			setState(234); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(233);
				variable();
				}
				}
				setState(236); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << IDENTIFIER))) != 0) );
			setState(238);
			match(T__30);
			setState(239);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_lemmaStmt, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(242);
				match(T__2);
				setState(243);
				lemmaStmt(0);
				setState(244);
				match(T__20);
				}
				break;
			case 2:
				{
				setState(246);
				goal();
				}
				break;
			case 3:
				{
				setState(247);
				variable();
				setState(248);
				match(T__33);
				setState(249);
				variable();
				}
				break;
			case 4:
				{
				setState(251);
				variable();
				setState(252);
				match(T__25);
				setState(253);
				variable();
				}
				break;
			case 5:
				{
				setState(255);
				variable();
				setState(256);
				match(T__26);
				setState(257);
				variable();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LemmaStmtContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_lemmaStmt);
					setState(261);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(262);
					match(T__35);
					setState(263);
					lemmaStmt(2);
					}
					} 
				}
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
		enterRule(_localctx, 28, RULE_jsonObj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(T__31);
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(270);
				jsonKeyValue();
				}
			}

			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(273);
				match(T__22);
				setState(274);
				jsonKeyValue();
				}
				}
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(280);
			match(T__32);
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
		enterRule(_localctx, 30, RULE_jsonArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__36);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__31) | (1L << T__36) | (1L << T__39) | (1L << T__40))) != 0)) {
				{
				setState(283);
				jsonValue();
				}
			}

			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(286);
				match(T__22);
				setState(287);
				jsonValue();
				}
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(293);
			match(T__37);
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
		enterRule(_localctx, 32, RULE_jsonKeyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			jsonKey();
			setState(296);
			match(T__38);
			setState(297);
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
		enterRule(_localctx, 34, RULE_jsonKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(T__1);
			setState(300);
			jsonString();
			setState(301);
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
		enterRule(_localctx, 36, RULE_jsonValue);
		try {
			setState(311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				match(T__1);
				setState(304);
				jsonString();
				setState(305);
				match(T__1);
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				jsonObj();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 3);
				{
				setState(308);
				jsonArray();
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 4);
				{
				setState(309);
				match(T__39);
				}
				break;
			case T__40:
				enterOuterAlt(_localctx, 5);
				{
				setState(310);
				match(T__40);
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
		enterRule(_localctx, 38, RULE_jsonChars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__38) | (1L << PERSISTENT))) != 0)) ) {
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
		enterRule(_localctx, 40, RULE_jsonString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__38) | (1L << PERSISTENT) | (1L << NUMBER) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(318);
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
				case T__32:
				case T__38:
				case PERSISTENT:
					{
					setState(315);
					jsonChars();
					}
					break;
				case IDENTIFIER:
					{
					setState(316);
					match(IDENTIFIER);
					}
					break;
				case NUMBER:
					{
					setState(317);
					match(NUMBER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(322);
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
		case 13:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u0146\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\7\2.\n\2\f\2\16\2\61\13\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3;\n\3\f\3\16\3>\13\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4J\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4T\n\4\f\4\16\4W\13\4\3\4\3\4\3\4\7\4\\\n\4\f\4\16\4_\13\4\3\4\3\4\3"+
		"\4\7\4d\n\4\f\4\16\4g\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4o\n\4\f\4\16\4r"+
		"\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4|\n\4\3\4\3\4\7\4\u0080\n\4\f"+
		"\4\16\4\u0083\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0099\n\5\3\6\5\6\u009c\n\6\3\6\3\6\3"+
		"\6\5\6\u00a1\n\6\3\6\3\6\7\6\u00a5\n\6\f\6\16\6\u00a8\13\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\5\7\u00b0\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\5\t\u00b9\n\t\3"+
		"\t\3\t\7\t\u00bd\n\t\f\t\16\t\u00c0\13\t\3\t\3\t\3\n\3\n\5\n\u00c6\n\n"+
		"\3\n\3\n\7\n\u00ca\n\n\f\n\16\n\u00cd\13\n\3\n\3\n\3\13\5\13\u00d2\n\13"+
		"\3\13\3\13\3\13\5\13\u00d7\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e0"+
		"\n\f\f\f\16\f\u00e3\13\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\6\16\u00ed"+
		"\n\16\r\16\16\16\u00ee\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0106"+
		"\n\17\3\17\3\17\3\17\7\17\u010b\n\17\f\17\16\17\u010e\13\17\3\20\3\20"+
		"\5\20\u0112\n\20\3\20\3\20\7\20\u0116\n\20\f\20\16\20\u0119\13\20\3\20"+
		"\3\20\3\21\3\21\5\21\u011f\n\21\3\21\3\21\7\21\u0123\n\21\f\21\16\21\u0126"+
		"\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u013a\n\24\3\25\3\25\3\26\3\26\3\26\7\26"+
		"\u0141\n\26\f\26\16\26\u0144\13\26\3\26\2\3\34\27\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*\2\4\3\2\36 \b\2\5\5\27\27\31\31\33#))..\2\u0158"+
		"\2/\3\2\2\2\4\62\3\2\2\2\6?\3\2\2\2\b\u0098\3\2\2\2\n\u009b\3\2\2\2\f"+
		"\u00af\3\2\2\2\16\u00b1\3\2\2\2\20\u00b5\3\2\2\2\22\u00c3\3\2\2\2\24\u00d1"+
		"\3\2\2\2\26\u00d8\3\2\2\2\30\u00e6\3\2\2\2\32\u00ea\3\2\2\2\34\u0105\3"+
		"\2\2\2\36\u010f\3\2\2\2 \u011c\3\2\2\2\"\u0129\3\2\2\2$\u012d\3\2\2\2"+
		"&\u0139\3\2\2\2(\u013b\3\2\2\2*\u0142\3\2\2\2,.\5\4\3\2-,\3\2\2\2.\61"+
		"\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\3\3\2\2\2\61/\3\2\2\2\62\63\7\3\2\2"+
		"\63\64\7\4\2\2\64\65\5\b\5\2\65\66\7\4\2\2\66\67\7\5\2\2\678\7\61\2\2"+
		"8<\7\6\2\29;\5\6\4\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\5\3\2\2"+
		"\2><\3\2\2\2?@\7\7\2\2@A\7\61\2\2AB\7\b\2\2BC\7\61\2\2CD\7\t\2\2DE\7\n"+
		"\2\2EF\7\4\2\2FG\7\62\2\2GI\7\4\2\2HJ\7/\2\2IH\3\2\2\2IJ\3\2\2\2JK\3\2"+
		"\2\2KL\7\4\2\2LM\5\b\5\2MN\7\4\2\2NO\7\13\2\2OP\7\f\2\2PQ\7\r\2\2QU\7"+
		"\16\2\2RT\5\26\f\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2W"+
		"U\3\2\2\2XY\7\17\2\2Y]\7\20\2\2Z\\\5\32\16\2[Z\3\2\2\2\\_\3\2\2\2][\3"+
		"\2\2\2]^\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\7\21\2\2ae\7\22\2\2bd\5\30\r\2"+
		"cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hp\7\23\2"+
		"\2ij\5\b\5\2jk\7\24\2\2kl\7\61\2\2lm\7\60\2\2mo\3\2\2\2ni\3\2\2\2or\3"+
		"\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2s\u0081\7\25\2\2tu\5\b\5"+
		"\2uv\7\24\2\2v{\7\61\2\2wx\7\5\2\2xy\7\26\2\2yz\7\62\2\2z|\7\27\2\2{w"+
		"\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7\60\2\2~\u0080\3\2\2\2\177t\3\2\2\2\u0080"+
		"\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2"+
		"\2\u0083\u0081\3\2\2\2\u0084\u0085\7\30\2\2\u0085\u0086\5\36\20\2\u0086"+
		"\u0087\7,\2\2\u0087\7\3\2\2\2\u0088\u0089\5\n\6\2\u0089\u008a\7-\2\2\u008a"+
		"\u008b\5\24\13\2\u008b\u0099\3\2\2\2\u008c\u008d\7\5\2\2\u008d\u008e\5"+
		"\24\13\2\u008e\u008f\7\31\2\2\u008f\u0090\7\61\2\2\u0090\u0091\7\27\2"+
		"\2\u0091\u0092\7\32\2\2\u0092\u0093\7\5\2\2\u0093\u0094\5\24\13\2\u0094"+
		"\u0095\7\31\2\2\u0095\u0096\7\61\2\2\u0096\u0097\7\27\2\2\u0097\u0099"+
		"\3\2\2\2\u0098\u0088\3\2\2\2\u0098\u008c\3\2\2\2\u0099\t\3\2\2\2\u009a"+
		"\u009c\7.\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009e\7\62\2\2\u009e\u00a0\7\5\2\2\u009f\u00a1\5\f\7\2\u00a0"+
		"\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a6\3\2\2\2\u00a2\u00a3\7\31"+
		"\2\2\u00a3\u00a5\5\f\7\2\u00a4\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a6\3\2"+
		"\2\2\u00a9\u00aa\7\27\2\2\u00aa\13\3\2\2\2\u00ab\u00b0\5\16\b\2\u00ac"+
		"\u00b0\5\24\13\2\u00ad\u00b0\5\20\t\2\u00ae\u00b0\5\22\n\2\u00af\u00ab"+
		"\3\2\2\2\u00af\u00ac\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0"+
		"\r\3\2\2\2\u00b1\u00b2\7\33\2\2\u00b2\u00b3\7\62\2\2\u00b3\u00b4\7\33"+
		"\2\2\u00b4\17\3\2\2\2\u00b5\u00b6\7\62\2\2\u00b6\u00b8\7\5\2\2\u00b7\u00b9"+
		"\5\f\7\2\u00b8\u00b7\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00be\3\2\2\2\u00ba"+
		"\u00bb\7\31\2\2\u00bb\u00bd\5\f\7\2\u00bc\u00ba\3\2\2\2\u00bd\u00c0\3"+
		"\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0"+
		"\u00be\3\2\2\2\u00c1\u00c2\7\27\2\2\u00c2\21\3\2\2\2\u00c3\u00c5\7\34"+
		"\2\2\u00c4\u00c6\5\f\7\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00cb\3\2\2\2\u00c7\u00c8\7\31\2\2\u00c8\u00ca\5\f\7\2\u00c9\u00c7\3"+
		"\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\35\2\2\u00cf\23\3\2\2"+
		"\2\u00d0\u00d2\t\2\2\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3"+
		"\3\2\2\2\u00d3\u00d6\7\62\2\2\u00d4\u00d5\7!\2\2\u00d5\u00d7\7\61\2\2"+
		"\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\25\3\2\2\2\u00d8\u00d9"+
		"\5\f\7\2\u00d9\u00da\7\34\2\2\u00da\u00db\7\37\2\2\u00db\u00dc\7\"\2\2"+
		"\u00dc\u00e1\5\24\13\2\u00dd\u00de\7\31\2\2\u00de\u00e0\5\24\13\2\u00df"+
		"\u00dd\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2"+
		"\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7#\2\2\u00e5"+
		"\27\3\2\2\2\u00e6\u00e7\5\f\7\2\u00e7\u00e8\7$\2\2\u00e8\u00e9\5\f\7\2"+
		"\u00e9\31\3\2\2\2\u00ea\u00ec\7%\2\2\u00eb\u00ed\5\24\13\2\u00ec\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0\u00f1\7!\2\2\u00f1\u00f2\5\34\17\2\u00f2\33\3\2\2"+
		"\2\u00f3\u00f4\b\17\1\2\u00f4\u00f5\7\5\2\2\u00f5\u00f6\5\34\17\2\u00f6"+
		"\u00f7\7\27\2\2\u00f7\u0106\3\2\2\2\u00f8\u0106\5\b\5\2\u00f9\u00fa\5"+
		"\24\13\2\u00fa\u00fb\7$\2\2\u00fb\u00fc\5\24\13\2\u00fc\u0106\3\2\2\2"+
		"\u00fd\u00fe\5\24\13\2\u00fe\u00ff\7\34\2\2\u00ff\u0100\5\24\13\2\u0100"+
		"\u0106\3\2\2\2\u0101\u0102\5\24\13\2\u0102\u0103\7\35\2\2\u0103\u0104"+
		"\5\24\13\2\u0104\u0106\3\2\2\2\u0105\u00f3\3\2\2\2\u0105\u00f8\3\2\2\2"+
		"\u0105\u00f9\3\2\2\2\u0105\u00fd\3\2\2\2\u0105\u0101\3\2\2\2\u0106\u010c"+
		"\3\2\2\2\u0107\u0108\f\3\2\2\u0108\u0109\7&\2\2\u0109\u010b\5\34\17\4"+
		"\u010a\u0107\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d"+
		"\3\2\2\2\u010d\35\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0111\7\"\2\2\u0110"+
		"\u0112\5\"\22\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0117\3"+
		"\2\2\2\u0113\u0114\7\31\2\2\u0114\u0116\5\"\22\2\u0115\u0113\3\2\2\2\u0116"+
		"\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\3\2"+
		"\2\2\u0119\u0117\3\2\2\2\u011a\u011b\7#\2\2\u011b\37\3\2\2\2\u011c\u011e"+
		"\7\'\2\2\u011d\u011f\5&\24\2\u011e\u011d\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\u0124\3\2\2\2\u0120\u0121\7\31\2\2\u0121\u0123\5&\24\2\u0122\u0120\3"+
		"\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0127\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0128\7(\2\2\u0128!\3\2\2\2\u0129"+
		"\u012a\5$\23\2\u012a\u012b\7)\2\2\u012b\u012c\5&\24\2\u012c#\3\2\2\2\u012d"+
		"\u012e\7\4\2\2\u012e\u012f\5*\26\2\u012f\u0130\7\4\2\2\u0130%\3\2\2\2"+
		"\u0131\u0132\7\4\2\2\u0132\u0133\5*\26\2\u0133\u0134\7\4\2\2\u0134\u013a"+
		"\3\2\2\2\u0135\u013a\5\36\20\2\u0136\u013a\5 \21\2\u0137\u013a\7*\2\2"+
		"\u0138\u013a\7+\2\2\u0139\u0131\3\2\2\2\u0139\u0135\3\2\2\2\u0139\u0136"+
		"\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u0138\3\2\2\2\u013a\'\3\2\2\2\u013b"+
		"\u013c\t\3\2\2\u013c)\3\2\2\2\u013d\u0141\5(\25\2\u013e\u0141\7\62\2\2"+
		"\u013f\u0141\7\61\2\2\u0140\u013d\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u013f"+
		"\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"+\3\2\2\2\u0144\u0142\3\2\2\2!/<IU]ep{\u0081\u0098\u009b\u00a0\u00a6\u00af"+
		"\u00b8\u00be\u00c5\u00cb\u00d1\u00d6\u00e1\u00ee\u0105\u010c\u0111\u0117"+
		"\u011e\u0124\u0139\u0140\u0142";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}