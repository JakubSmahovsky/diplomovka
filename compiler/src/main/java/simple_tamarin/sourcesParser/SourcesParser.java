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
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, SEPARATOR=44, ATTIMEPOINT=45, 
		PERSISTENT=46, PARTIAL_DECONSTRUCTIONS=47, USEFUL=48, NUMBER=49, IDENTIFIER=50, 
		WHITESPACE=51;
	public static final int
		RULE_sources = 0, RULE_group = 1, RULE_source = 2, RULE_goal = 3, RULE_fact = 4, 
		RULE_term = 5, RULE_terminatingTerm = 6, RULE_multiplication = 7, RULE_exponentiation = 8, 
		RULE_constant = 9, RULE_function = 10, RULE_tuple = 11, RULE_variable = 12, 
		RULE_subst = 13, RULE_formula = 14, RULE_lemma = 15, RULE_lemmaStmt = 16, 
		RULE_jsonObj = 17, RULE_jsonArray = 18, RULE_jsonKeyValue = 19, RULE_jsonKey = 20, 
		RULE_jsonValue = 21, RULE_jsonChars = 22, RULE_jsonString = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"sources", "group", "source", "goal", "fact", "term", "terminatingTerm", 
			"multiplication", "exponentiation", "constant", "function", "tuple", 
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
			"'json graph:'", "','", "'~~>'", "'*'", "'^'", "'''", "'<'", "'>'", "'$'", 
			"'~'", "'#'", "'.'", "'{'", "'}'", "'='", "'\u2200'", "'\u21D2'", "'['", 
			"']'", "':'", "'true'", "'false'", "'------------------------------------------------------------------------------'", 
			null, "'!'", "'(partial deconstructions)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "SEPARATOR", "ATTIMEPOINT", 
			"PERSISTENT", "PARTIAL_DECONSTRUCTIONS", "USEFUL", "NUMBER", "IDENTIFIER", 
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
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(48);
				group();
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
			setState(54);
			match(T__0);
			setState(55);
			match(T__1);
			setState(56);
			goal();
			setState(57);
			match(T__1);
			setState(58);
			match(T__2);
			setState(59);
			match(NUMBER);
			setState(60);
			match(T__3);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(61);
				source();
				}
				}
				setState(66);
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
			setState(67);
			match(T__4);
			setState(68);
			match(NUMBER);
			setState(69);
			match(T__5);
			setState(70);
			match(NUMBER);
			setState(71);
			match(T__6);
			setState(72);
			match(T__7);
			setState(73);
			match(T__1);
			setState(74);
			((SourceContext)_localctx).name = match(IDENTIFIER);
			setState(75);
			match(T__1);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PARTIAL_DECONSTRUCTIONS) {
				{
				setState(76);
				match(PARTIAL_DECONSTRUCTIONS);
				}
			}

			setState(79);
			match(T__1);
			setState(80);
			goal();
			setState(81);
			match(T__1);
			setState(82);
			match(T__8);
			setState(83);
			match(T__9);
			setState(84);
			match(T__10);
			setState(85);
			match(T__11);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__26) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(86);
				subst();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			match(T__12);
			setState(93);
			match(T__13);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__36) {
				{
				{
				setState(94);
				lemma();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(T__14);
			setState(101);
			match(T__15);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__26) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(102);
				formula();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(T__16);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << PERSISTENT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(109);
				goal();
				setState(110);
				match(T__17);
				setState(111);
				match(NUMBER);
				setState(112);
				match(USEFUL);
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
			match(T__18);
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << PERSISTENT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(120);
				goal();
				setState(121);
				match(T__17);
				setState(122);
				match(NUMBER);
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(123);
					match(T__2);
					setState(124);
					match(T__19);
					setState(125);
					match(IDENTIFIER);
					setState(126);
					match(T__20);
					}
				}

				setState(129);
				match(USEFUL);
				}
				}
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
			match(T__21);
			setState(137);
			jsonObj();
			setState(138);
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
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PERSISTENT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				fact();
				setState(141);
				match(ATTIMEPOINT);
				setState(142);
				variable();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
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
				setState(149);
				match(T__23);
				setState(150);
				match(T__2);
				setState(151);
				variable();
				setState(152);
				match(T__22);
				setState(153);
				match(NUMBER);
				setState(154);
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
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERSISTENT) {
				{
				setState(158);
				match(PERSISTENT);
				}
			}

			setState(161);
			match(IDENTIFIER);
			setState(162);
			match(T__2);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__26) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(163);
				term();
				}
			}

			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(166);
				match(T__22);
				setState(167);
				term();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(173);
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
		public TerminatingTermContext terminatingTerm() {
			return getRuleContext(TerminatingTermContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public MultiplicationContext multiplication() {
			return getRuleContext(MultiplicationContext.class,0);
		}
		public ExponentiationContext exponentiation() {
			return getRuleContext(ExponentiationContext.class,0);
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
			setState(182);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				terminatingTerm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(T__2);
				setState(177);
				term();
				setState(178);
				match(T__20);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(180);
				multiplication();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(181);
				exponentiation();
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

	public static class TerminatingTermContext extends ParserRuleContext {
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
		public TerminatingTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminatingTerm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitTerminatingTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminatingTermContext terminatingTerm() throws RecognitionException {
		TerminatingTermContext _localctx = new TerminatingTermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_terminatingTerm);
		try {
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(187);
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

	public static class MultiplicationContext extends ParserRuleContext {
		public List<TerminatingTermContext> terminatingTerm() {
			return getRuleContexts(TerminatingTermContext.class);
		}
		public TerminatingTermContext terminatingTerm(int i) {
			return getRuleContext(TerminatingTermContext.class,i);
		}
		public MultiplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplication; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitMultiplication(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicationContext multiplication() throws RecognitionException {
		MultiplicationContext _localctx = new MultiplicationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_multiplication);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			terminatingTerm();
			setState(193); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(191);
				match(T__24);
				setState(192);
				terminatingTerm();
				}
				}
				setState(195); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__24 );
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

	public static class ExponentiationContext extends ParserRuleContext {
		public List<TerminatingTermContext> terminatingTerm() {
			return getRuleContexts(TerminatingTermContext.class);
		}
		public TerminatingTermContext terminatingTerm(int i) {
			return getRuleContext(TerminatingTermContext.class,i);
		}
		public List<MultiplicationContext> multiplication() {
			return getRuleContexts(MultiplicationContext.class);
		}
		public MultiplicationContext multiplication(int i) {
			return getRuleContext(MultiplicationContext.class,i);
		}
		public ExponentiationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exponentiation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SourcesVisitor ) return ((SourcesVisitor<? extends T>)visitor).visitExponentiation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExponentiationContext exponentiation() throws RecognitionException {
		ExponentiationContext _localctx = new ExponentiationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_exponentiation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(197);
				terminatingTerm();
				}
				break;
			case 2:
				{
				setState(198);
				multiplication();
				}
				break;
			}
			setState(206); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(201);
				match(T__25);
				setState(204);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(202);
					terminatingTerm();
					}
					break;
				case 2:
					{
					setState(203);
					multiplication();
					}
					break;
				}
				}
				}
				setState(208); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__25 );
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
		enterRule(_localctx, 18, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(T__26);
			setState(211);
			((ConstantContext)_localctx).word = match(IDENTIFIER);
			setState(212);
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
		enterRule(_localctx, 20, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(IDENTIFIER);
			setState(215);
			match(T__2);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__26) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(216);
				term();
				}
			}

			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(219);
				match(T__22);
				setState(220);
				term();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
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
		enterRule(_localctx, 22, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(T__27);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__26) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(229);
				term();
				}
			}

			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(232);
				match(T__22);
				setState(233);
				term();
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(239);
			match(T__28);
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
		enterRule(_localctx, 24, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) {
				{
				setState(241);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(244);
			match(IDENTIFIER);
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(245);
				match(T__32);
				setState(246);
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
		enterRule(_localctx, 26, RULE_subst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			term();
			setState(250);
			match(T__27);
			setState(251);
			match(T__30);
			setState(252);
			match(T__33);
			setState(253);
			variable();
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(254);
				match(T__22);
				setState(255);
				variable();
				}
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(261);
			match(T__34);
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
		enterRule(_localctx, 28, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			term();
			setState(264);
			match(T__35);
			setState(265);
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
		enterRule(_localctx, 30, RULE_lemma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(T__36);
			setState(269); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(268);
				variable();
				}
				}
				setState(271); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << IDENTIFIER))) != 0) );
			setState(273);
			match(T__32);
			setState(274);
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_lemmaStmt, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(277);
				match(T__2);
				setState(278);
				lemmaStmt(0);
				setState(279);
				match(T__20);
				}
				break;
			case 2:
				{
				setState(281);
				goal();
				}
				break;
			case 3:
				{
				setState(282);
				variable();
				setState(283);
				match(T__35);
				setState(284);
				variable();
				}
				break;
			case 4:
				{
				setState(286);
				variable();
				setState(287);
				match(T__27);
				setState(288);
				variable();
				}
				break;
			case 5:
				{
				setState(290);
				variable();
				setState(291);
				match(T__28);
				setState(292);
				variable();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(301);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LemmaStmtContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_lemmaStmt);
					setState(296);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(297);
					match(T__37);
					setState(298);
					lemmaStmt(2);
					}
					} 
				}
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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
		enterRule(_localctx, 34, RULE_jsonObj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(T__33);
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(305);
				jsonKeyValue();
				}
			}

			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(308);
				match(T__22);
				setState(309);
				jsonKeyValue();
				}
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(315);
			match(T__34);
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
		enterRule(_localctx, 36, RULE_jsonArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(T__38);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__33) | (1L << T__38) | (1L << T__41) | (1L << T__42))) != 0)) {
				{
				setState(318);
				jsonValue();
				}
			}

			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(321);
				match(T__22);
				setState(322);
				jsonValue();
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(328);
			match(T__39);
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
		enterRule(_localctx, 38, RULE_jsonKeyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			jsonKey();
			setState(331);
			match(T__40);
			setState(332);
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
		enterRule(_localctx, 40, RULE_jsonKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			match(T__1);
			setState(335);
			jsonString();
			setState(336);
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
		enterRule(_localctx, 42, RULE_jsonValue);
		try {
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				match(T__1);
				setState(339);
				jsonString();
				setState(340);
				match(T__1);
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
				jsonObj();
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 3);
				{
				setState(343);
				jsonArray();
				}
				break;
			case T__41:
				enterOuterAlt(_localctx, 4);
				{
				setState(344);
				match(T__41);
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 5);
				{
				setState(345);
				match(T__42);
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
		enterRule(_localctx, 44, RULE_jsonChars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__40) | (1L << PERSISTENT))) != 0)) ) {
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
		enterRule(_localctx, 46, RULE_jsonString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__20) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__40) | (1L << PERSISTENT) | (1L << NUMBER) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(353);
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
				case T__33:
				case T__34:
				case T__40:
				case PERSISTENT:
					{
					setState(350);
					jsonChars();
					}
					break;
				case IDENTIFIER:
					{
					setState(351);
					match(IDENTIFIER);
					}
					break;
				case NUMBER:
					{
					setState(352);
					match(NUMBER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(357);
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
		case 16:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u0169\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\7\2\64\n\2\f\2\16\2\67\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3A"+
		"\n\3\f\3\16\3D\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4P\n\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\4\3\4\3\4\7\4"+
		"b\n\4\f\4\16\4e\13\4\3\4\3\4\3\4\7\4j\n\4\f\4\16\4m\13\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\4u\n\4\f\4\16\4x\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"\u0082\n\4\3\4\3\4\7\4\u0086\n\4\f\4\16\4\u0089\13\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u009f"+
		"\n\5\3\6\5\6\u00a2\n\6\3\6\3\6\3\6\5\6\u00a7\n\6\3\6\3\6\7\6\u00ab\n\6"+
		"\f\6\16\6\u00ae\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00b9\n\7"+
		"\3\b\3\b\3\b\3\b\5\b\u00bf\n\b\3\t\3\t\3\t\6\t\u00c4\n\t\r\t\16\t\u00c5"+
		"\3\n\3\n\5\n\u00ca\n\n\3\n\3\n\3\n\5\n\u00cf\n\n\6\n\u00d1\n\n\r\n\16"+
		"\n\u00d2\3\13\3\13\3\13\3\13\3\f\3\f\3\f\5\f\u00dc\n\f\3\f\3\f\7\f\u00e0"+
		"\n\f\f\f\16\f\u00e3\13\f\3\f\3\f\3\r\3\r\5\r\u00e9\n\r\3\r\3\r\7\r\u00ed"+
		"\n\r\f\r\16\r\u00f0\13\r\3\r\3\r\3\16\5\16\u00f5\n\16\3\16\3\16\3\16\5"+
		"\16\u00fa\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0103\n\17\f\17"+
		"\16\17\u0106\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\6\21\u0110"+
		"\n\21\r\21\16\21\u0111\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0129"+
		"\n\22\3\22\3\22\3\22\7\22\u012e\n\22\f\22\16\22\u0131\13\22\3\23\3\23"+
		"\5\23\u0135\n\23\3\23\3\23\7\23\u0139\n\23\f\23\16\23\u013c\13\23\3\23"+
		"\3\23\3\24\3\24\5\24\u0142\n\24\3\24\3\24\7\24\u0146\n\24\f\24\16\24\u0149"+
		"\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\5\27\u015d\n\27\3\30\3\30\3\31\3\31\3\31\7\31"+
		"\u0164\n\31\f\31\16\31\u0167\13\31\3\31\2\3\"\32\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\2\4\3\2 \"\b\2\5\5\27\27\31\31\33%++\60"+
		"\60\2\u017f\2\65\3\2\2\2\48\3\2\2\2\6E\3\2\2\2\b\u009e\3\2\2\2\n\u00a1"+
		"\3\2\2\2\f\u00b8\3\2\2\2\16\u00be\3\2\2\2\20\u00c0\3\2\2\2\22\u00c9\3"+
		"\2\2\2\24\u00d4\3\2\2\2\26\u00d8\3\2\2\2\30\u00e6\3\2\2\2\32\u00f4\3\2"+
		"\2\2\34\u00fb\3\2\2\2\36\u0109\3\2\2\2 \u010d\3\2\2\2\"\u0128\3\2\2\2"+
		"$\u0132\3\2\2\2&\u013f\3\2\2\2(\u014c\3\2\2\2*\u0150\3\2\2\2,\u015c\3"+
		"\2\2\2.\u015e\3\2\2\2\60\u0165\3\2\2\2\62\64\5\4\3\2\63\62\3\2\2\2\64"+
		"\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\3\3\2\2\2\67\65\3\2\2\289\7"+
		"\3\2\29:\7\4\2\2:;\5\b\5\2;<\7\4\2\2<=\7\5\2\2=>\7\63\2\2>B\7\6\2\2?A"+
		"\5\6\4\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\5\3\2\2\2DB\3\2\2\2"+
		"EF\7\7\2\2FG\7\63\2\2GH\7\b\2\2HI\7\63\2\2IJ\7\t\2\2JK\7\n\2\2KL\7\4\2"+
		"\2LM\7\64\2\2MO\7\4\2\2NP\7\61\2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\7\4"+
		"\2\2RS\5\b\5\2ST\7\4\2\2TU\7\13\2\2UV\7\f\2\2VW\7\r\2\2W[\7\16\2\2XZ\5"+
		"\34\17\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2"+
		"^_\7\17\2\2_c\7\20\2\2`b\5 \21\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2"+
		"\2df\3\2\2\2ec\3\2\2\2fg\7\21\2\2gk\7\22\2\2hj\5\36\20\2ih\3\2\2\2jm\3"+
		"\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2nv\7\23\2\2op\5\b\5\2pq"+
		"\7\24\2\2qr\7\63\2\2rs\7\62\2\2su\3\2\2\2to\3\2\2\2ux\3\2\2\2vt\3\2\2"+
		"\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2y\u0087\7\25\2\2z{\5\b\5\2{|\7\24\2\2"+
		"|\u0081\7\63\2\2}~\7\5\2\2~\177\7\26\2\2\177\u0080\7\64\2\2\u0080\u0082"+
		"\7\27\2\2\u0081}\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0084\7\62\2\2\u0084\u0086\3\2\2\2\u0085z\3\2\2\2\u0086\u0089\3\2\2\2"+
		"\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u008a\u008b\7\30\2\2\u008b\u008c\5$\23\2\u008c\u008d\7.\2\2\u008d"+
		"\7\3\2\2\2\u008e\u008f\5\n\6\2\u008f\u0090\7/\2\2\u0090\u0091\5\32\16"+
		"\2\u0091\u009f\3\2\2\2\u0092\u0093\7\5\2\2\u0093\u0094\5\32\16\2\u0094"+
		"\u0095\7\31\2\2\u0095\u0096\7\63\2\2\u0096\u0097\7\27\2\2\u0097\u0098"+
		"\7\32\2\2\u0098\u0099\7\5\2\2\u0099\u009a\5\32\16\2\u009a\u009b\7\31\2"+
		"\2\u009b\u009c\7\63\2\2\u009c\u009d\7\27\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u008e\3\2\2\2\u009e\u0092\3\2\2\2\u009f\t\3\2\2\2\u00a0\u00a2\7\60\2"+
		"\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4"+
		"\7\64\2\2\u00a4\u00a6\7\5\2\2\u00a5\u00a7\5\f\7\2\u00a6\u00a5\3\2\2\2"+
		"\u00a6\u00a7\3\2\2\2\u00a7\u00ac\3\2\2\2\u00a8\u00a9\7\31\2\2\u00a9\u00ab"+
		"\5\f\7\2\u00aa\u00a8\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\7\27"+
		"\2\2\u00b0\13\3\2\2\2\u00b1\u00b9\5\16\b\2\u00b2\u00b3\7\5\2\2\u00b3\u00b4"+
		"\5\f\7\2\u00b4\u00b5\7\27\2\2\u00b5\u00b9\3\2\2\2\u00b6\u00b9\5\20\t\2"+
		"\u00b7\u00b9\5\22\n\2\u00b8\u00b1\3\2\2\2\u00b8\u00b2\3\2\2\2\u00b8\u00b6"+
		"\3\2\2\2\u00b8\u00b7\3\2\2\2\u00b9\r\3\2\2\2\u00ba\u00bf\5\24\13\2\u00bb"+
		"\u00bf\5\32\16\2\u00bc\u00bf\5\26\f\2\u00bd\u00bf\5\30\r\2\u00be\u00ba"+
		"\3\2\2\2\u00be\u00bb\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bd\3\2\2\2\u00bf"+
		"\17\3\2\2\2\u00c0\u00c3\5\16\b\2\u00c1\u00c2\7\33\2\2\u00c2\u00c4\5\16"+
		"\b\2\u00c3\u00c1\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\21\3\2\2\2\u00c7\u00ca\5\16\b\2\u00c8\u00ca\5\20"+
		"\t\2\u00c9\u00c7\3\2\2\2\u00c9\u00c8\3\2\2\2\u00ca\u00d0\3\2\2\2\u00cb"+
		"\u00ce\7\34\2\2\u00cc\u00cf\5\16\b\2\u00cd\u00cf\5\20\t\2\u00ce\u00cc"+
		"\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00cb\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\23\3\2\2"+
		"\2\u00d4\u00d5\7\35\2\2\u00d5\u00d6\7\64\2\2\u00d6\u00d7\7\35\2\2\u00d7"+
		"\25\3\2\2\2\u00d8\u00d9\7\64\2\2\u00d9\u00db\7\5\2\2\u00da\u00dc\5\f\7"+
		"\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00e1\3\2\2\2\u00dd\u00de"+
		"\7\31\2\2\u00de\u00e0\5\f\7\2\u00df\u00dd\3\2\2\2\u00e0\u00e3\3\2\2\2"+
		"\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1"+
		"\3\2\2\2\u00e4\u00e5\7\27\2\2\u00e5\27\3\2\2\2\u00e6\u00e8\7\36\2\2\u00e7"+
		"\u00e9\5\f\7\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ee\3\2"+
		"\2\2\u00ea\u00eb\7\31\2\2\u00eb\u00ed\5\f\7\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2"+
		"\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\7\37\2\2\u00f2\31\3\2\2\2\u00f3\u00f5"+
		"\t\2\2\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"\u00f9\7\64\2\2\u00f7\u00f8\7#\2\2\u00f8\u00fa\7\63\2\2\u00f9\u00f7\3"+
		"\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\33\3\2\2\2\u00fb\u00fc\5\f\7\2\u00fc"+
		"\u00fd\7\36\2\2\u00fd\u00fe\7!\2\2\u00fe\u00ff\7$\2\2\u00ff\u0104\5\32"+
		"\16\2\u0100\u0101\7\31\2\2\u0101\u0103\5\32\16\2\u0102\u0100\3\2\2\2\u0103"+
		"\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\3\2"+
		"\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7%\2\2\u0108\35\3\2\2\2\u0109\u010a"+
		"\5\f\7\2\u010a\u010b\7&\2\2\u010b\u010c\5\f\7\2\u010c\37\3\2\2\2\u010d"+
		"\u010f\7\'\2\2\u010e\u0110\5\32\16\2\u010f\u010e\3\2\2\2\u0110\u0111\3"+
		"\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\7#\2\2\u0114\u0115\5\"\22\2\u0115!\3\2\2\2\u0116\u0117\b\22\1\2"+
		"\u0117\u0118\7\5\2\2\u0118\u0119\5\"\22\2\u0119\u011a\7\27\2\2\u011a\u0129"+
		"\3\2\2\2\u011b\u0129\5\b\5\2\u011c\u011d\5\32\16\2\u011d\u011e\7&\2\2"+
		"\u011e\u011f\5\32\16\2\u011f\u0129\3\2\2\2\u0120\u0121\5\32\16\2\u0121"+
		"\u0122\7\36\2\2\u0122\u0123\5\32\16\2\u0123\u0129\3\2\2\2\u0124\u0125"+
		"\5\32\16\2\u0125\u0126\7\37\2\2\u0126\u0127\5\32\16\2\u0127\u0129\3\2"+
		"\2\2\u0128\u0116\3\2\2\2\u0128\u011b\3\2\2\2\u0128\u011c\3\2\2\2\u0128"+
		"\u0120\3\2\2\2\u0128\u0124\3\2\2\2\u0129\u012f\3\2\2\2\u012a\u012b\f\3"+
		"\2\2\u012b\u012c\7(\2\2\u012c\u012e\5\"\22\4\u012d\u012a\3\2\2\2\u012e"+
		"\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130#\3\2\2\2"+
		"\u0131\u012f\3\2\2\2\u0132\u0134\7$\2\2\u0133\u0135\5(\25\2\u0134\u0133"+
		"\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u013a\3\2\2\2\u0136\u0137\7\31\2\2"+
		"\u0137\u0139\5(\25\2\u0138\u0136\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138"+
		"\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\3\2\2\2\u013c\u013a\3\2\2\2\u013d"+
		"\u013e\7%\2\2\u013e%\3\2\2\2\u013f\u0141\7)\2\2\u0140\u0142\5,\27\2\u0141"+
		"\u0140\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0147\3\2\2\2\u0143\u0144\7\31"+
		"\2\2\u0144\u0146\5,\27\2\u0145\u0143\3\2\2\2\u0146\u0149\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147\3\2"+
		"\2\2\u014a\u014b\7*\2\2\u014b\'\3\2\2\2\u014c\u014d\5*\26\2\u014d\u014e"+
		"\7+\2\2\u014e\u014f\5,\27\2\u014f)\3\2\2\2\u0150\u0151\7\4\2\2\u0151\u0152"+
		"\5\60\31\2\u0152\u0153\7\4\2\2\u0153+\3\2\2\2\u0154\u0155\7\4\2\2\u0155"+
		"\u0156\5\60\31\2\u0156\u0157\7\4\2\2\u0157\u015d\3\2\2\2\u0158\u015d\5"+
		"$\23\2\u0159\u015d\5&\24\2\u015a\u015d\7,\2\2\u015b\u015d\7-\2\2\u015c"+
		"\u0154\3\2\2\2\u015c\u0158\3\2\2\2\u015c\u0159\3\2\2\2\u015c\u015a\3\2"+
		"\2\2\u015c\u015b\3\2\2\2\u015d-\3\2\2\2\u015e\u015f\t\3\2\2\u015f/\3\2"+
		"\2\2\u0160\u0164\5.\30\2\u0161\u0164\7\64\2\2\u0162\u0164\7\63\2\2\u0163"+
		"\u0160\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0162\3\2\2\2\u0164\u0167\3\2"+
		"\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\61\3\2\2\2\u0167\u0165"+
		"\3\2\2\2&\65BO[ckv\u0081\u0087\u009e\u00a1\u00a6\u00ac\u00b8\u00be\u00c5"+
		"\u00c9\u00ce\u00d2\u00db\u00e1\u00e8\u00ee\u00f4\u00f9\u0104\u0111\u0128"+
		"\u012f\u0134\u013a\u0141\u0147\u015c\u0163\u0165";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}