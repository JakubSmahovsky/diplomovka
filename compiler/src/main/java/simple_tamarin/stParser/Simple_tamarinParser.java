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
		T__17=18, T__18=19, T__19=20, T__20=21, FUNCTION=22, CHECKED=23, POWER_OP=24, 
		KEYWORD_PRINCIPALS=25, IDENTIFIER=26, WHITESPACE=27;
	public static final int
		RULE_model = 0, RULE_declaration = 1, RULE_decPrincipals = 2, RULE_specificationSegment = 3, 
		RULE_principalBlock = 4, RULE_command = 5, RULE_distributed = 6, RULE_knows = 7, 
		RULE_generates = 8, RULE_assignment = 9, RULE_check = 10, RULE_message = 11, 
		RULE_queriesBlock = 12, RULE_term = 13, RULE_constant = 14, RULE_variable = 15, 
		RULE_functionCall = 16, RULE_tuple = 17, RULE_checkedCall = 18, RULE_query = 19, 
		RULE_confidentiality = 20, RULE_executable = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "declaration", "decPrincipals", "specificationSegment", "principalBlock", 
			"command", "distributed", "knows", "generates", "assignment", "check", 
			"message", "queriesBlock", "term", "constant", "variable", "functionCall", 
			"tuple", "checkedCall", "query", "confidentiality", "executable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "','", "'['", "']'", "'distributed'", "'='", "'knows'", 
			"'public'", "'private'", "'generates'", "'?'", "'->'", "'queries'", "'('", 
			"')'", "'''", "'{'", "'}'", "'confidentiality?'", "''s'", "'executable?'", 
			null, null, "'^'", "'principals'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "FUNCTION", 
			"CHECKED", "POWER_OP", "KEYWORD_PRINCIPALS", "IDENTIFIER", "WHITESPACE"
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
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEYWORD_PRINCIPALS) {
				{
				{
				setState(44);
				declaration();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				specificationSegment();
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(55);
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
			setState(58);
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
			setState(60);
			match(KEYWORD_PRINCIPALS);
			setState(61);
			match(T__0);
			setState(62);
			((DecPrincipalsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			((DecPrincipalsContext)_localctx).principal.add(((DecPrincipalsContext)_localctx).IDENTIFIER);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(63);
				match(T__1);
				setState(64);
				((DecPrincipalsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((DecPrincipalsContext)_localctx).principal.add(((DecPrincipalsContext)_localctx).IDENTIFIER);
				}
				}
				setState(69);
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
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				principalBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
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
			setState(74);
			((PrincipalBlockContext)_localctx).principal = match(IDENTIFIER);
			setState(75);
			match(T__2);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__9) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << FUNCTION) | (1L << CHECKED) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(76);
				command();
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
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
		public DistributedContext distributed() {
			return getRuleContext(DistributedContext.class,0);
		}
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
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				distributed();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				knows();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				generates();
				}
				break;
			case T__13:
			case T__15:
			case T__16:
			case FUNCTION:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(87);
				assignment();
				}
				break;
			case CHECKED:
				enterOuterAlt(_localctx, 5);
				{
				setState(88);
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

	public static class DistributedContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public DistributedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distributed; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Simple_tamarinVisitor ) return ((Simple_tamarinVisitor<? extends T>)visitor).visitDistributed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistributedContext distributed() throws RecognitionException {
		DistributedContext _localctx = new DistributedContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_distributed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__4);
			setState(92);
			variable();
			setState(93);
			match(T__5);
			setState(94);
			term(0);
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
		enterRule(_localctx, 14, RULE_knows);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__6);
			setState(97);
			((KnowsContext)_localctx).modifier = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__7 || _la==T__8) ) {
				((KnowsContext)_localctx).modifier = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(98);
			variable();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(99);
				match(T__1);
				setState(100);
				variable();
				}
				}
				setState(105);
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
		enterRule(_localctx, 16, RULE_generates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__9);
			setState(107);
			variable();
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(108);
				match(T__1);
				setState(109);
				variable();
				}
				}
				setState(114);
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
		enterRule(_localctx, 18, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			((AssignmentContext)_localctx).left = term(0);
			setState(116);
			match(T__5);
			setState(117);
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
		enterRule(_localctx, 20, RULE_check);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			checkedCall();
			setState(120);
			match(T__10);
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
		enterRule(_localctx, 22, RULE_message);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			((MessageContext)_localctx).sender = match(IDENTIFIER);
			setState(123);
			match(T__11);
			setState(124);
			((MessageContext)_localctx).receiver = match(IDENTIFIER);
			setState(125);
			match(T__0);
			setState(126);
			term(0);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(127);
				match(T__1);
				setState(128);
				term(0);
				}
				}
				setState(133);
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
		enterRule(_localctx, 24, RULE_queriesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__12);
			setState(135);
			match(T__2);
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18 || _la==T__20) {
				{
				{
				setState(136);
				query();
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(142);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				{
				setState(145);
				match(T__13);
				setState(146);
				term(0);
				setState(147);
				match(T__14);
				}
				break;
			case T__15:
				{
				setState(149);
				constant();
				}
				break;
			case IDENTIFIER:
				{
				setState(150);
				variable();
				}
				break;
			case FUNCTION:
				{
				setState(151);
				functionCall();
				}
				break;
			case T__16:
				{
				setState(152);
				tuple();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(160);
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
					setState(155);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(156);
					match(POWER_OP);
					setState(157);
					term(6);
					}
					} 
				}
				setState(162);
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
		enterRule(_localctx, 28, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__15);
			setState(164);
			((ConstantContext)_localctx).word = match(IDENTIFIER);
			setState(165);
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
		enterRule(_localctx, 30, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
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
		enterRule(_localctx, 32, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(FUNCTION);
			setState(170);
			match(T__13);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << FUNCTION) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(171);
				((FunctionCallContext)_localctx).term = term(0);
				((FunctionCallContext)_localctx).argument.add(((FunctionCallContext)_localctx).term);
				}
			}

			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(174);
				match(T__1);
				setState(175);
				((FunctionCallContext)_localctx).term = term(0);
				((FunctionCallContext)_localctx).argument.add(((FunctionCallContext)_localctx).term);
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
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
		enterRule(_localctx, 34, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__16);
			setState(184);
			term(0);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(185);
				match(T__1);
				setState(186);
				term(0);
				}
				}
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(192);
			match(T__17);
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
		enterRule(_localctx, 36, RULE_checkedCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(CHECKED);
			setState(195);
			match(T__13);
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << FUNCTION) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(196);
				((CheckedCallContext)_localctx).term = term(0);
				((CheckedCallContext)_localctx).argument.add(((CheckedCallContext)_localctx).term);
				}
			}

			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(199);
				match(T__1);
				setState(200);
				((CheckedCallContext)_localctx).term = term(0);
				((CheckedCallContext)_localctx).argument.add(((CheckedCallContext)_localctx).term);
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206);
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
		enterRule(_localctx, 38, RULE_query);
		try {
			setState(210);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				executable();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
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
		enterRule(_localctx, 40, RULE_confidentiality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__18);
			setState(215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(213);
				((ConfidentialityContext)_localctx).principal = match(IDENTIFIER);
				setState(214);
				match(T__19);
				}
				break;
			}
			setState(217);
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
		enterRule(_localctx, 42, RULE_executable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00e0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\7\2\60\n\2\f\2"+
		"\16\2\63\13\2\3\2\6\2\66\n\2\r\2\16\2\67\3\2\5\2;\n\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\7\4D\n\4\f\4\16\4G\13\4\3\5\3\5\5\5K\n\5\3\6\3\6\3\6\7\6"+
		"P\n\6\f\6\16\6S\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7\\\n\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\7\th\n\t\f\t\16\tk\13\t\3\n\3\n\3\n\3\n\7"+
		"\nq\n\n\f\n\16\nt\13\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\7\r\u0084\n\r\f\r\16\r\u0087\13\r\3\16\3\16\3\16\7\16\u008c"+
		"\n\16\f\16\16\16\u008f\13\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\5\17\u009c\n\17\3\17\3\17\3\17\7\17\u00a1\n\17\f\17\16\17"+
		"\u00a4\13\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\5\22\u00af\n"+
		"\22\3\22\3\22\7\22\u00b3\n\22\f\22\16\22\u00b6\13\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\23\7\23\u00be\n\23\f\23\16\23\u00c1\13\23\3\23\3\23\3\24\3"+
		"\24\3\24\5\24\u00c8\n\24\3\24\3\24\7\24\u00cc\n\24\f\24\16\24\u00cf\13"+
		"\24\3\24\3\24\3\25\3\25\5\25\u00d5\n\25\3\26\3\26\3\26\5\26\u00da\n\26"+
		"\3\26\3\26\3\27\3\27\3\27\2\3\34\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,\2\3\3\2\n\13\2\u00e3\2\61\3\2\2\2\4<\3\2\2\2\6>\3\2\2\2\b"+
		"J\3\2\2\2\nL\3\2\2\2\f[\3\2\2\2\16]\3\2\2\2\20b\3\2\2\2\22l\3\2\2\2\24"+
		"u\3\2\2\2\26y\3\2\2\2\30|\3\2\2\2\32\u0088\3\2\2\2\34\u009b\3\2\2\2\36"+
		"\u00a5\3\2\2\2 \u00a9\3\2\2\2\"\u00ab\3\2\2\2$\u00b9\3\2\2\2&\u00c4\3"+
		"\2\2\2(\u00d4\3\2\2\2*\u00d6\3\2\2\2,\u00dd\3\2\2\2.\60\5\4\3\2/.\3\2"+
		"\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2"+
		"\2\64\66\5\b\5\2\65\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\2"+
		"8:\3\2\2\29;\5\32\16\2:9\3\2\2\2:;\3\2\2\2;\3\3\2\2\2<=\5\6\4\2=\5\3\2"+
		"\2\2>?\7\33\2\2?@\7\3\2\2@E\7\34\2\2AB\7\4\2\2BD\7\34\2\2CA\3\2\2\2DG"+
		"\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\7\3\2\2\2GE\3\2\2\2HK\5\n\6\2IK\5\30\r\2"+
		"JH\3\2\2\2JI\3\2\2\2K\t\3\2\2\2LM\7\34\2\2MQ\7\5\2\2NP\5\f\7\2ON\3\2\2"+
		"\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7\6\2\2U\13\3\2"+
		"\2\2V\\\5\16\b\2W\\\5\20\t\2X\\\5\22\n\2Y\\\5\24\13\2Z\\\5\26\f\2[V\3"+
		"\2\2\2[W\3\2\2\2[X\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\\r\3\2\2\2]^\7\7\2\2^"+
		"_\5 \21\2_`\7\b\2\2`a\5\34\17\2a\17\3\2\2\2bc\7\t\2\2cd\t\2\2\2di\5 \21"+
		"\2ef\7\4\2\2fh\5 \21\2ge\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\21\3\2"+
		"\2\2ki\3\2\2\2lm\7\f\2\2mr\5 \21\2no\7\4\2\2oq\5 \21\2pn\3\2\2\2qt\3\2"+
		"\2\2rp\3\2\2\2rs\3\2\2\2s\23\3\2\2\2tr\3\2\2\2uv\5\34\17\2vw\7\b\2\2w"+
		"x\5\34\17\2x\25\3\2\2\2yz\5&\24\2z{\7\r\2\2{\27\3\2\2\2|}\7\34\2\2}~\7"+
		"\16\2\2~\177\7\34\2\2\177\u0080\7\3\2\2\u0080\u0085\5\34\17\2\u0081\u0082"+
		"\7\4\2\2\u0082\u0084\5\34\17\2\u0083\u0081\3\2\2\2\u0084\u0087\3\2\2\2"+
		"\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\31\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0088\u0089\7\17\2\2\u0089\u008d\7\5\2\2\u008a\u008c\5(\25\2"+
		"\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e"+
		"\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7\6\2\2\u0091"+
		"\33\3\2\2\2\u0092\u0093\b\17\1\2\u0093\u0094\7\20\2\2\u0094\u0095\5\34"+
		"\17\2\u0095\u0096\7\21\2\2\u0096\u009c\3\2\2\2\u0097\u009c\5\36\20\2\u0098"+
		"\u009c\5 \21\2\u0099\u009c\5\"\22\2\u009a\u009c\5$\23\2\u009b\u0092\3"+
		"\2\2\2\u009b\u0097\3\2\2\2\u009b\u0098\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009a\3\2\2\2\u009c\u00a2\3\2\2\2\u009d\u009e\f\7\2\2\u009e\u009f\7\32"+
		"\2\2\u009f\u00a1\5\34\17\b\u00a0\u009d\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\35\3\2\2\2\u00a4\u00a2\3\2\2"+
		"\2\u00a5\u00a6\7\22\2\2\u00a6\u00a7\7\34\2\2\u00a7\u00a8\7\22\2\2\u00a8"+
		"\37\3\2\2\2\u00a9\u00aa\7\34\2\2\u00aa!\3\2\2\2\u00ab\u00ac\7\30\2\2\u00ac"+
		"\u00ae\7\20\2\2\u00ad\u00af\5\34\17\2\u00ae\u00ad\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\u00b4\3\2\2\2\u00b0\u00b1\7\4\2\2\u00b1\u00b3\5\34\17\2"+
		"\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\21\2\2"+
		"\u00b8#\3\2\2\2\u00b9\u00ba\7\23\2\2\u00ba\u00bf\5\34\17\2\u00bb\u00bc"+
		"\7\4\2\2\u00bc\u00be\5\34\17\2\u00bd\u00bb\3\2\2\2\u00be\u00c1\3\2\2\2"+
		"\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf"+
		"\3\2\2\2\u00c2\u00c3\7\24\2\2\u00c3%\3\2\2\2\u00c4\u00c5\7\31\2\2\u00c5"+
		"\u00c7\7\20\2\2\u00c6\u00c8\5\34\17\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\u00cd\3\2\2\2\u00c9\u00ca\7\4\2\2\u00ca\u00cc\5\34\17\2"+
		"\u00cb\u00c9\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce"+
		"\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\7\21\2\2"+
		"\u00d1\'\3\2\2\2\u00d2\u00d5\5,\27\2\u00d3\u00d5\5*\26\2\u00d4\u00d2\3"+
		"\2\2\2\u00d4\u00d3\3\2\2\2\u00d5)\3\2\2\2\u00d6\u00d9\7\25\2\2\u00d7\u00d8"+
		"\7\34\2\2\u00d8\u00da\7\26\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2"+
		"\u00da\u00db\3\2\2\2\u00db\u00dc\5 \21\2\u00dc+\3\2\2\2\u00dd\u00de\7"+
		"\27\2\2\u00de-\3\2\2\2\26\61\67:EJQ[ir\u0085\u008d\u009b\u00a2\u00ae\u00b4"+
		"\u00bf\u00c7\u00cd\u00d4\u00d9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}