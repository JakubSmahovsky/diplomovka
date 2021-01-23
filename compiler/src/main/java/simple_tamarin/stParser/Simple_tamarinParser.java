// Generated from Simple_tamarin.g4 by ANTLR 4.8
package simple_tamarin.stParser;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, FUNCTION=20, CHECKED=21, KEYWORD_PRINCIPALS=22, IDENTIFIER=23, 
		WHITESPACE=24;
	public static final int
		RULE_model = 0, RULE_segment = 1, RULE_declaration = 2, RULE_decPrincipals = 3, 
		RULE_principalBlock = 4, RULE_command = 5, RULE_knows = 6, RULE_generates = 7, 
		RULE_assignment = 8, RULE_check = 9, RULE_messageBlock = 10, RULE_queriesBlock = 11, 
		RULE_term = 12, RULE_variable = 13, RULE_functionCall = 14, RULE_checkedCall = 15, 
		RULE_tuple = 16, RULE_query = 17, RULE_confidentiality = 18, RULE_executable = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "segment", "declaration", "decPrincipals", "principalBlock", 
			"command", "knows", "generates", "assignment", "check", "messageBlock", 
			"queriesBlock", "term", "variable", "functionCall", "checkedCall", "tuple", 
			"query", "confidentiality", "executable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "','", "'['", "']'", "'knows'", "'public'", "'private'", 
			"'generates'", "'='", "'?'", "'->'", "'queries'", "'('", "')'", "'{'", 
			"'}'", "'confidentiality?'", "''s'", "'executable?'", null, "'ASSERT'", 
			"'principals'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "FUNCTION", "CHECKED", 
			"KEYWORD_PRINCIPALS", "IDENTIFIER", "WHITESPACE"
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
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << KEYWORD_PRINCIPALS) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(40);
				segment();
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

	public static class SegmentContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
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
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				principalBlock();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				messageBlock();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
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

	public static class DeclarationContext extends ParserRuleContext {
		public DecPrincipalsContext decPrincipals() {
			return getRuleContext(DecPrincipalsContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			decPrincipals();
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

	public static class DecPrincipalsContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public List<Token> principal = new ArrayList<Token>();
		public TerminalNode KEYWORD_PRINCIPALS() { return getToken(Simple_tamarinParser.KEYWORD_PRINCIPALS, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(Simple_tamarinParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(Simple_tamarinParser.IDENTIFIER, i);
		}
		public DecPrincipalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decPrincipals; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitDecPrincipals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecPrincipalsContext decPrincipals() throws RecognitionException {
		DecPrincipalsContext _localctx = new DecPrincipalsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decPrincipals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(KEYWORD_PRINCIPALS);
			setState(55);
			match(T__0);
			setState(56);
			((DecPrincipalsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			((DecPrincipalsContext)_localctx).principal.add(((DecPrincipalsContext)_localctx).IDENTIFIER);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(57);
				match(T__1);
				setState(58);
				((DecPrincipalsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((DecPrincipalsContext)_localctx).principal.add(((DecPrincipalsContext)_localctx).IDENTIFIER);
				}
				}
				setState(63);
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
		enterRule(_localctx, 8, RULE_principalBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			((PrincipalBlockContext)_localctx).principal = match(IDENTIFIER);
			setState(65);
			match(T__2);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__14) | (1L << FUNCTION) | (1L << CHECKED) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(66);
				command();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(T__3);
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
		public GeneratesContext generates() {
			return getRuleContext(GeneratesContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public CheckContext check() {
			return getRuleContext(CheckContext.class,0);
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
		enterRule(_localctx, 10, RULE_command);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				knows();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				generates();
				}
				break;
			case T__14:
			case FUNCTION:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				assignment();
				}
				break;
			case CHECKED:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				check();
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

	public static class KnowsContext extends ParserRuleContext {
		public Token modifier;
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
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
		enterRule(_localctx, 12, RULE_knows);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__4);
			setState(81);
			((KnowsContext)_localctx).modifier = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
				((KnowsContext)_localctx).modifier = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(82);
			variable();
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(83);
				match(T__1);
				setState(84);
				variable();
				}
				}
				setState(89);
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

	public static class GeneratesContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public GeneratesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generates; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitGenerates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratesContext generates() throws RecognitionException {
		GeneratesContext _localctx = new GeneratesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_generates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__7);
			setState(91);
			variable();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(92);
				match(T__1);
				setState(93);
				variable();
				}
				}
				setState(98);
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

	public static class AssignmentContext extends ParserRuleContext {
		public TermContext left;
		public TermContext right;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			((AssignmentContext)_localctx).left = term();
			setState(100);
			match(T__8);
			setState(101);
			((AssignmentContext)_localctx).right = term();
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

	public static class CheckContext extends ParserRuleContext {
		public CheckedCallContext checkedCall() {
			return getRuleContext(CheckedCallContext.class,0);
		}
		public CheckContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_check; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitCheck(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckContext check() throws RecognitionException {
		CheckContext _localctx = new CheckContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_check);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			checkedCall();
			setState(104);
			match(T__9);
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
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
		enterRule(_localctx, 20, RULE_messageBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			((MessageBlockContext)_localctx).sender = match(IDENTIFIER);
			setState(107);
			match(T__10);
			setState(108);
			((MessageBlockContext)_localctx).receiver = match(IDENTIFIER);
			setState(109);
			match(T__0);
			setState(110);
			term();
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(111);
				match(T__1);
				setState(112);
				term();
				}
				}
				setState(117);
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
		enterRule(_localctx, 22, RULE_queriesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__11);
			setState(119);
			match(T__2);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16 || _la==T__18) {
				{
				{
				setState(120);
				query();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(T__3);
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
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
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_term);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				variable();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				functionCall();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				tuple();
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Simple_tamarinParser.IDENTIFIER, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
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

	public static class FunctionCallContext extends ParserRuleContext {
		public TermContext term;
		public List<TermContext> argument = new ArrayList<TermContext>();
		public TerminalNode FUNCTION() { return getToken(Simple_tamarinParser.FUNCTION, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(FUNCTION);
			setState(136);
			match(T__12);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << FUNCTION) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(137);
				((FunctionCallContext)_localctx).term = term();
				((FunctionCallContext)_localctx).argument.add(((FunctionCallContext)_localctx).term);
				}
			}

			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(140);
				match(T__1);
				setState(141);
				((FunctionCallContext)_localctx).term = term();
				((FunctionCallContext)_localctx).argument.add(((FunctionCallContext)_localctx).term);
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147);
			match(T__13);
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

	public static class CheckedCallContext extends ParserRuleContext {
		public TermContext term;
		public List<TermContext> argument = new ArrayList<TermContext>();
		public TerminalNode CHECKED() { return getToken(Simple_tamarinParser.CHECKED, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public CheckedCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkedCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitCheckedCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckedCallContext checkedCall() throws RecognitionException {
		CheckedCallContext _localctx = new CheckedCallContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_checkedCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(CHECKED);
			setState(150);
			match(T__12);
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << FUNCTION) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(151);
				((CheckedCallContext)_localctx).term = term();
				((CheckedCallContext)_localctx).argument.add(((CheckedCallContext)_localctx).term);
				}
			}

			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(154);
				match(T__1);
				setState(155);
				((CheckedCallContext)_localctx).term = term();
				((CheckedCallContext)_localctx).argument.add(((CheckedCallContext)_localctx).term);
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(161);
			match(T__13);
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
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__14);
			setState(164);
			term();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(165);
				match(T__1);
				setState(166);
				term();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(172);
			match(T__15);
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
		public ExecutableContext executable() {
			return getRuleContext(ExecutableContext.class,0);
		}
		public ConfidentialityContext confidentiality() {
			return getRuleContext(ConfidentialityContext.class,0);
		}
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
		enterRule(_localctx, 34, RULE_query);
		try {
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				executable();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				confidentiality();
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

	public static class ConfidentialityContext extends ParserRuleContext {
		public Token principal;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(Simple_tamarinParser.IDENTIFIER, 0); }
		public ConfidentialityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_confidentiality; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitConfidentiality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfidentialityContext confidentiality() throws RecognitionException {
		ConfidentialityContext _localctx = new ConfidentialityContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_confidentiality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__16);
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(179);
				((ConfidentialityContext)_localctx).principal = match(IDENTIFIER);
				setState(180);
				match(T__17);
				}
				break;
			}
			setState(183);
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

	public static class ExecutableContext extends ParserRuleContext {
		public ExecutableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_executable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitExecutable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExecutableContext executable() throws RecognitionException {
		ExecutableContext _localctx = new ExecutableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_executable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(T__18);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u00be\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\3\3\3\3\3"+
		"\3\3\5\3\65\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3"+
		"\6\3\6\3\6\7\6F\n\6\f\6\16\6I\13\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7Q\n\7\3"+
		"\b\3\b\3\b\3\b\3\b\7\bX\n\b\f\b\16\b[\13\b\3\t\3\t\3\t\3\t\7\ta\n\t\f"+
		"\t\16\td\13\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\7\ft\n\f\f\f\16\fw\13\f\3\r\3\r\3\r\7\r|\n\r\f\r\16\r\177\13\r\3\r"+
		"\3\r\3\16\3\16\3\16\5\16\u0086\n\16\3\17\3\17\3\20\3\20\3\20\5\20\u008d"+
		"\n\20\3\20\3\20\7\20\u0091\n\20\f\20\16\20\u0094\13\20\3\20\3\20\3\21"+
		"\3\21\3\21\5\21\u009b\n\21\3\21\3\21\7\21\u009f\n\21\f\21\16\21\u00a2"+
		"\13\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u00aa\n\22\f\22\16\22\u00ad"+
		"\13\22\3\22\3\22\3\23\3\23\5\23\u00b3\n\23\3\24\3\24\3\24\5\24\u00b8\n"+
		"\24\3\24\3\24\3\25\3\25\3\25\2\2\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(\2\3\3\2\b\t\2\u00bf\2-\3\2\2\2\4\64\3\2\2\2\6\66\3\2\2\2\b8"+
		"\3\2\2\2\nB\3\2\2\2\fP\3\2\2\2\16R\3\2\2\2\20\\\3\2\2\2\22e\3\2\2\2\24"+
		"i\3\2\2\2\26l\3\2\2\2\30x\3\2\2\2\32\u0085\3\2\2\2\34\u0087\3\2\2\2\36"+
		"\u0089\3\2\2\2 \u0097\3\2\2\2\"\u00a5\3\2\2\2$\u00b2\3\2\2\2&\u00b4\3"+
		"\2\2\2(\u00bb\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2"+
		"\2.\3\3\2\2\2/-\3\2\2\2\60\65\5\6\4\2\61\65\5\n\6\2\62\65\5\26\f\2\63"+
		"\65\5\30\r\2\64\60\3\2\2\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63\3\2\2\2\65"+
		"\5\3\2\2\2\66\67\5\b\5\2\67\7\3\2\2\289\7\30\2\29:\7\3\2\2:?\7\31\2\2"+
		";<\7\4\2\2<>\7\31\2\2=;\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\t\3\2\2"+
		"\2A?\3\2\2\2BC\7\31\2\2CG\7\5\2\2DF\5\f\7\2ED\3\2\2\2FI\3\2\2\2GE\3\2"+
		"\2\2GH\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\7\6\2\2K\13\3\2\2\2LQ\5\16\b\2MQ"+
		"\5\20\t\2NQ\5\22\n\2OQ\5\24\13\2PL\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2"+
		"\2Q\r\3\2\2\2RS\7\7\2\2ST\t\2\2\2TY\5\34\17\2UV\7\4\2\2VX\5\34\17\2WU"+
		"\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\17\3\2\2\2[Y\3\2\2\2\\]\7\n\2"+
		"\2]b\5\34\17\2^_\7\4\2\2_a\5\34\17\2`^\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3"+
		"\2\2\2c\21\3\2\2\2db\3\2\2\2ef\5\32\16\2fg\7\13\2\2gh\5\32\16\2h\23\3"+
		"\2\2\2ij\5 \21\2jk\7\f\2\2k\25\3\2\2\2lm\7\31\2\2mn\7\r\2\2no\7\31\2\2"+
		"op\7\3\2\2pu\5\32\16\2qr\7\4\2\2rt\5\32\16\2sq\3\2\2\2tw\3\2\2\2us\3\2"+
		"\2\2uv\3\2\2\2v\27\3\2\2\2wu\3\2\2\2xy\7\16\2\2y}\7\5\2\2z|\5$\23\2{z"+
		"\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2"+
		"\u0080\u0081\7\6\2\2\u0081\31\3\2\2\2\u0082\u0086\5\34\17\2\u0083\u0086"+
		"\5\36\20\2\u0084\u0086\5\"\22\2\u0085\u0082\3\2\2\2\u0085\u0083\3\2\2"+
		"\2\u0085\u0084\3\2\2\2\u0086\33\3\2\2\2\u0087\u0088\7\31\2\2\u0088\35"+
		"\3\2\2\2\u0089\u008a\7\26\2\2\u008a\u008c\7\17\2\2\u008b\u008d\5\32\16"+
		"\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0092\3\2\2\2\u008e\u008f"+
		"\7\4\2\2\u008f\u0091\5\32\16\2\u0090\u008e\3\2\2\2\u0091\u0094\3\2\2\2"+
		"\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0092"+
		"\3\2\2\2\u0095\u0096\7\20\2\2\u0096\37\3\2\2\2\u0097\u0098\7\27\2\2\u0098"+
		"\u009a\7\17\2\2\u0099\u009b\5\32\16\2\u009a\u0099\3\2\2\2\u009a\u009b"+
		"\3\2\2\2\u009b\u00a0\3\2\2\2\u009c\u009d\7\4\2\2\u009d\u009f\5\32\16\2"+
		"\u009e\u009c\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1"+
		"\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7\20\2\2"+
		"\u00a4!\3\2\2\2\u00a5\u00a6\7\21\2\2\u00a6\u00ab\5\32\16\2\u00a7\u00a8"+
		"\7\4\2\2\u00a8\u00aa\5\32\16\2\u00a9\u00a7\3\2\2\2\u00aa\u00ad\3\2\2\2"+
		"\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab"+
		"\3\2\2\2\u00ae\u00af\7\22\2\2\u00af#\3\2\2\2\u00b0\u00b3\5(\25\2\u00b1"+
		"\u00b3\5&\24\2\u00b2\u00b0\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3%\3\2\2\2"+
		"\u00b4\u00b7\7\23\2\2\u00b5\u00b6\7\31\2\2\u00b6\u00b8\7\24\2\2\u00b7"+
		"\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\5\34"+
		"\17\2\u00ba\'\3\2\2\2\u00bb\u00bc\7\25\2\2\u00bc)\3\2\2\2\23-\64?GPYb"+
		"u}\u0085\u008c\u0092\u009a\u00a0\u00ab\u00b2\u00b7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}