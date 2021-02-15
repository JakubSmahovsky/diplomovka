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
		T__17=18, T__18=19, T__19=20, FUNCTION=21, CHECKED=22, POWER_OP=23, KEYWORD_PRINCIPALS=24, 
		IDENTIFIER=25, WHITESPACE=26;
	public static final int
		RULE_model = 0, RULE_declaration = 1, RULE_decPrincipals = 2, RULE_specificationSegment = 3, 
		RULE_principalBlock = 4, RULE_command = 5, RULE_knows = 6, RULE_generates = 7, 
		RULE_assignment = 8, RULE_check = 9, RULE_message = 10, RULE_queriesBlock = 11, 
		RULE_term = 12, RULE_constant = 13, RULE_variable = 14, RULE_functionCall = 15, 
		RULE_tuple = 16, RULE_checkedCall = 17, RULE_query = 18, RULE_confidentiality = 19, 
		RULE_executable = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "declaration", "decPrincipals", "specificationSegment", "principalBlock", 
			"command", "knows", "generates", "assignment", "check", "message", "queriesBlock", 
			"term", "constant", "variable", "functionCall", "tuple", "checkedCall", 
			"query", "confidentiality", "executable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "','", "'['", "']'", "'knows'", "'public'", "'private'", 
			"'generates'", "'='", "'?'", "'->'", "'queries'", "'('", "')'", "'''", 
			"'{'", "'}'", "'confidentiality?'", "''s'", "'executable?'", null, "'ASSERT'", 
			"'^'", "'principals'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "FUNCTION", "CHECKED", 
			"POWER_OP", "KEYWORD_PRINCIPALS", "IDENTIFIER", "WHITESPACE"
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
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<SpecificationSegmentContext> specificationSegment() {
			return getRuleContexts(SpecificationSegmentContext.class);
		}
		public SpecificationSegmentContext specificationSegment(int i) {
			return getRuleContext(SpecificationSegmentContext.class,i);
		}
		public QueriesBlockContext queriesBlock() {
			return getRuleContext(QueriesBlockContext.class,0);
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
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEYWORD_PRINCIPALS) {
				{
				{
				setState(42);
				declaration();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				specificationSegment();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(53);
				queriesBlock();
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
		enterRule(_localctx, 2, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
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
		enterRule(_localctx, 4, RULE_decPrincipals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(KEYWORD_PRINCIPALS);
			setState(59);
			match(T__0);
			setState(60);
			((DecPrincipalsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			((DecPrincipalsContext)_localctx).principal.add(((DecPrincipalsContext)_localctx).IDENTIFIER);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(61);
				match(T__1);
				setState(62);
				((DecPrincipalsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((DecPrincipalsContext)_localctx).principal.add(((DecPrincipalsContext)_localctx).IDENTIFIER);
				}
				}
				setState(67);
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

	public static class SpecificationSegmentContext extends ParserRuleContext {
		public PrincipalBlockContext principalBlock() {
			return getRuleContext(PrincipalBlockContext.class,0);
		}
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public SpecificationSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specificationSegment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitSpecificationSegment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecificationSegmentContext specificationSegment() throws RecognitionException {
		SpecificationSegmentContext _localctx = new SpecificationSegmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_specificationSegment);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				principalBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				message();
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
		enterRule(_localctx, 8, RULE_principalBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			((PrincipalBlockContext)_localctx).principal = match(IDENTIFIER);
			setState(73);
			match(T__2);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << FUNCTION) | (1L << CHECKED) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(74);
				command();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
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
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				knows();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				generates();
				}
				break;
			case T__12:
			case T__14:
			case T__15:
			case FUNCTION:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				assignment();
				}
				break;
			case CHECKED:
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
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
			setState(88);
			match(T__4);
			setState(89);
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
			setState(90);
			variable();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(91);
				match(T__1);
				setState(92);
				variable();
				}
				}
				setState(97);
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
			setState(98);
			match(T__7);
			setState(99);
			variable();
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(100);
				match(T__1);
				setState(101);
				variable();
				}
				}
				setState(106);
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
			setState(107);
			((AssignmentContext)_localctx).left = term(0);
			setState(108);
			match(T__8);
			setState(109);
			((AssignmentContext)_localctx).right = term(0);
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
			setState(111);
			checkedCall();
			setState(112);
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

	public static class MessageContext extends ParserRuleContext {
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
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_message);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			((MessageContext)_localctx).sender = match(IDENTIFIER);
			setState(115);
			match(T__10);
			setState(116);
			((MessageContext)_localctx).receiver = match(IDENTIFIER);
			setState(117);
			match(T__0);
			setState(118);
			term(0);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(119);
				match(T__1);
				setState(120);
				term(0);
				}
				}
				setState(125);
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
			setState(126);
			match(T__11);
			setState(127);
			match(T__2);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17 || _la==T__19) {
				{
				{
				setState(128);
				query();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TerminalNode POWER_OP() { return getToken(Simple_tamarinParser.POWER_OP, 0); }
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
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				{
				setState(137);
				match(T__12);
				setState(138);
				term(0);
				setState(139);
				match(T__13);
				}
				break;
			case T__14:
				{
				setState(141);
				constant();
				}
				break;
			case IDENTIFIER:
				{
				setState(142);
				variable();
				}
				break;
			case FUNCTION:
				{
				setState(143);
				functionCall();
				}
				break;
			case T__15:
				{
				setState(144);
				tuple();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(152);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(147);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(148);
					match(POWER_OP);
					setState(149);
					term(6);
					}
					} 
				}
				setState(154);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class ConstantContext extends ParserRuleContext {
		public Token word;
		public TerminalNode IDENTIFIER() { return getToken(Simple_tamarinParser.IDENTIFIER, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__14);
			setState(156);
			((ConstantContext)_localctx).word = match(IDENTIFIER);
			setState(157);
			match(T__14);
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
		enterRule(_localctx, 28, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
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
		enterRule(_localctx, 30, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(FUNCTION);
			setState(162);
			match(T__12);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << FUNCTION) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(163);
				((FunctionCallContext)_localctx).term = term(0);
				((FunctionCallContext)_localctx).argument.add(((FunctionCallContext)_localctx).term);
				}
			}

			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(166);
				match(T__1);
				setState(167);
				((FunctionCallContext)_localctx).term = term(0);
				((FunctionCallContext)_localctx).argument.add(((FunctionCallContext)_localctx).term);
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(173);
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
			setState(175);
			match(T__15);
			setState(176);
			term(0);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(177);
				match(T__1);
				setState(178);
				term(0);
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(T__16);
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
		enterRule(_localctx, 34, RULE_checkedCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(CHECKED);
			setState(187);
			match(T__12);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << FUNCTION) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(188);
				((CheckedCallContext)_localctx).term = term(0);
				((CheckedCallContext)_localctx).argument.add(((CheckedCallContext)_localctx).term);
				}
			}

			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(191);
				match(T__1);
				setState(192);
				((CheckedCallContext)_localctx).term = term(0);
				((CheckedCallContext)_localctx).argument.add(((CheckedCallContext)_localctx).term);
				}
				}
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(198);
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
		enterRule(_localctx, 36, RULE_query);
		try {
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				executable();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
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
		enterRule(_localctx, 38, RULE_confidentiality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(T__17);
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(205);
				((ConfidentialityContext)_localctx).principal = match(IDENTIFIER);
				setState(206);
				match(T__18);
				}
				break;
			}
			setState(209);
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
		enterRule(_localctx, 40, RULE_executable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__19);
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
			return term_sempred((TermContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u00d8\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\7\2.\n\2\f\2\16\2\61\13\2"+
		"\3\2\6\2\64\n\2\r\2\16\2\65\3\2\5\29\n\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\7"+
		"\4B\n\4\f\4\16\4E\13\4\3\5\3\5\5\5I\n\5\3\6\3\6\3\6\7\6N\n\6\f\6\16\6"+
		"Q\13\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7Y\n\7\3\b\3\b\3\b\3\b\3\b\7\b`\n\b\f"+
		"\b\16\bc\13\b\3\t\3\t\3\t\3\t\7\ti\n\t\f\t\16\tl\13\t\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f|\n\f\f\f\16\f\177\13\f"+
		"\3\r\3\r\3\r\7\r\u0084\n\r\f\r\16\r\u0087\13\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0094\n\16\3\16\3\16\3\16\7\16\u0099"+
		"\n\16\f\16\16\16\u009c\13\16\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\21\5\21\u00a7\n\21\3\21\3\21\7\21\u00ab\n\21\f\21\16\21\u00ae\13\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\7\22\u00b6\n\22\f\22\16\22\u00b9\13\22\3"+
		"\22\3\22\3\23\3\23\3\23\5\23\u00c0\n\23\3\23\3\23\7\23\u00c4\n\23\f\23"+
		"\16\23\u00c7\13\23\3\23\3\23\3\24\3\24\5\24\u00cd\n\24\3\25\3\25\3\25"+
		"\5\25\u00d2\n\25\3\25\3\25\3\26\3\26\3\26\2\3\32\27\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*\2\3\3\2\b\t\2\u00db\2/\3\2\2\2\4:\3\2\2\2"+
		"\6<\3\2\2\2\bH\3\2\2\2\nJ\3\2\2\2\fX\3\2\2\2\16Z\3\2\2\2\20d\3\2\2\2\22"+
		"m\3\2\2\2\24q\3\2\2\2\26t\3\2\2\2\30\u0080\3\2\2\2\32\u0093\3\2\2\2\34"+
		"\u009d\3\2\2\2\36\u00a1\3\2\2\2 \u00a3\3\2\2\2\"\u00b1\3\2\2\2$\u00bc"+
		"\3\2\2\2&\u00cc\3\2\2\2(\u00ce\3\2\2\2*\u00d5\3\2\2\2,.\5\4\3\2-,\3\2"+
		"\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\62\64"+
		"\5\b\5\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3"+
		"\2\2\2\679\5\30\r\28\67\3\2\2\289\3\2\2\29\3\3\2\2\2:;\5\6\4\2;\5\3\2"+
		"\2\2<=\7\32\2\2=>\7\3\2\2>C\7\33\2\2?@\7\4\2\2@B\7\33\2\2A?\3\2\2\2BE"+
		"\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\7\3\2\2\2EC\3\2\2\2FI\5\n\6\2GI\5\26\f\2"+
		"HF\3\2\2\2HG\3\2\2\2I\t\3\2\2\2JK\7\33\2\2KO\7\5\2\2LN\5\f\7\2ML\3\2\2"+
		"\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7\6\2\2S\13\3\2"+
		"\2\2TY\5\16\b\2UY\5\20\t\2VY\5\22\n\2WY\5\24\13\2XT\3\2\2\2XU\3\2\2\2"+
		"XV\3\2\2\2XW\3\2\2\2Y\r\3\2\2\2Z[\7\7\2\2[\\\t\2\2\2\\a\5\36\20\2]^\7"+
		"\4\2\2^`\5\36\20\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\17\3\2\2\2"+
		"ca\3\2\2\2de\7\n\2\2ej\5\36\20\2fg\7\4\2\2gi\5\36\20\2hf\3\2\2\2il\3\2"+
		"\2\2jh\3\2\2\2jk\3\2\2\2k\21\3\2\2\2lj\3\2\2\2mn\5\32\16\2no\7\13\2\2"+
		"op\5\32\16\2p\23\3\2\2\2qr\5$\23\2rs\7\f\2\2s\25\3\2\2\2tu\7\33\2\2uv"+
		"\7\r\2\2vw\7\33\2\2wx\7\3\2\2x}\5\32\16\2yz\7\4\2\2z|\5\32\16\2{y\3\2"+
		"\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\27\3\2\2\2\177}\3\2\2\2\u0080\u0081"+
		"\7\16\2\2\u0081\u0085\7\5\2\2\u0082\u0084\5&\24\2\u0083\u0082\3\2\2\2"+
		"\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088"+
		"\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0089\7\6\2\2\u0089\31\3\2\2\2\u008a"+
		"\u008b\b\16\1\2\u008b\u008c\7\17\2\2\u008c\u008d\5\32\16\2\u008d\u008e"+
		"\7\20\2\2\u008e\u0094\3\2\2\2\u008f\u0094\5\34\17\2\u0090\u0094\5\36\20"+
		"\2\u0091\u0094\5 \21\2\u0092\u0094\5\"\22\2\u0093\u008a\3\2\2\2\u0093"+
		"\u008f\3\2\2\2\u0093\u0090\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0092\3\2"+
		"\2\2\u0094\u009a\3\2\2\2\u0095\u0096\f\7\2\2\u0096\u0097\7\31\2\2\u0097"+
		"\u0099\5\32\16\b\u0098\u0095\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3"+
		"\2\2\2\u009a\u009b\3\2\2\2\u009b\33\3\2\2\2\u009c\u009a\3\2\2\2\u009d"+
		"\u009e\7\21\2\2\u009e\u009f\7\33\2\2\u009f\u00a0\7\21\2\2\u00a0\35\3\2"+
		"\2\2\u00a1\u00a2\7\33\2\2\u00a2\37\3\2\2\2\u00a3\u00a4\7\27\2\2\u00a4"+
		"\u00a6\7\17\2\2\u00a5\u00a7\5\32\16\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7\u00ac\3\2\2\2\u00a8\u00a9\7\4\2\2\u00a9\u00ab\5\32\16\2"+
		"\u00aa\u00a8\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad"+
		"\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\7\20\2\2"+
		"\u00b0!\3\2\2\2\u00b1\u00b2\7\22\2\2\u00b2\u00b7\5\32\16\2\u00b3\u00b4"+
		"\7\4\2\2\u00b4\u00b6\5\32\16\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2"+
		"\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7"+
		"\3\2\2\2\u00ba\u00bb\7\23\2\2\u00bb#\3\2\2\2\u00bc\u00bd\7\30\2\2\u00bd"+
		"\u00bf\7\17\2\2\u00be\u00c0\5\32\16\2\u00bf\u00be\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00c5\3\2\2\2\u00c1\u00c2\7\4\2\2\u00c2\u00c4\5\32\16\2"+
		"\u00c3\u00c1\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7\20\2\2"+
		"\u00c9%\3\2\2\2\u00ca\u00cd\5*\26\2\u00cb\u00cd\5(\25\2\u00cc\u00ca\3"+
		"\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\'\3\2\2\2\u00ce\u00d1\7\24\2\2\u00cf"+
		"\u00d0\7\33\2\2\u00d0\u00d2\7\25\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3"+
		"\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\5\36\20\2\u00d4)\3\2\2\2\u00d5"+
		"\u00d6\7\26\2\2\u00d6+\3\2\2\2\26/\658CHOXaj}\u0085\u0093\u009a\u00a6"+
		"\u00ac\u00b7\u00bf\u00c5\u00cc\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}