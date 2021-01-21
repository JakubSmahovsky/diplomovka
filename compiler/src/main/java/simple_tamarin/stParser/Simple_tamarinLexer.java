// Generated from Simple_tamarin.g4 by ANTLR 4.8
package simple_tamarin.stParser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Simple_tamarinLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, FUNCTION=20, IDENTIFIER=21, WHITESPACE=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "FUNCTION", "IDENTIFIER", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'knows'", "'public'", "'private'", "','", "'generates'", 
			"'='", "'?'", "'->'", "':'", "'queries'", "'('", "')'", "'{'", "'}'", 
			"'confidentiality?'", "''s'", "'executable?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "FUNCTION", "IDENTIFIER", 
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


	public Simple_tamarinLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Simple_tamarin.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00ab\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u009e\n\25\3\26\6\26\u00a1\n\26\r\26\16\26\u00a2\3\27\6\27\u00a6\n\27"+
		"\r\27\16\27\u00a7\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"\3\2\4\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u00af\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\61\3\2\2\2\7\63\3\2"+
		"\2\2\t9\3\2\2\2\13@\3\2\2\2\rH\3\2\2\2\17J\3\2\2\2\21T\3\2\2\2\23V\3\2"+
		"\2\2\25X\3\2\2\2\27[\3\2\2\2\31]\3\2\2\2\33e\3\2\2\2\35g\3\2\2\2\37i\3"+
		"\2\2\2!k\3\2\2\2#m\3\2\2\2%~\3\2\2\2\'\u0081\3\2\2\2)\u009d\3\2\2\2+\u00a0"+
		"\3\2\2\2-\u00a5\3\2\2\2/\60\7]\2\2\60\4\3\2\2\2\61\62\7_\2\2\62\6\3\2"+
		"\2\2\63\64\7m\2\2\64\65\7p\2\2\65\66\7q\2\2\66\67\7y\2\2\678\7u\2\28\b"+
		"\3\2\2\29:\7r\2\2:;\7w\2\2;<\7d\2\2<=\7n\2\2=>\7k\2\2>?\7e\2\2?\n\3\2"+
		"\2\2@A\7r\2\2AB\7t\2\2BC\7k\2\2CD\7x\2\2DE\7c\2\2EF\7v\2\2FG\7g\2\2G\f"+
		"\3\2\2\2HI\7.\2\2I\16\3\2\2\2JK\7i\2\2KL\7g\2\2LM\7p\2\2MN\7g\2\2NO\7"+
		"t\2\2OP\7c\2\2PQ\7v\2\2QR\7g\2\2RS\7u\2\2S\20\3\2\2\2TU\7?\2\2U\22\3\2"+
		"\2\2VW\7A\2\2W\24\3\2\2\2XY\7/\2\2YZ\7@\2\2Z\26\3\2\2\2[\\\7<\2\2\\\30"+
		"\3\2\2\2]^\7s\2\2^_\7w\2\2_`\7g\2\2`a\7t\2\2ab\7k\2\2bc\7g\2\2cd\7u\2"+
		"\2d\32\3\2\2\2ef\7*\2\2f\34\3\2\2\2gh\7+\2\2h\36\3\2\2\2ij\7}\2\2j \3"+
		"\2\2\2kl\7\177\2\2l\"\3\2\2\2mn\7e\2\2no\7q\2\2op\7p\2\2pq\7h\2\2qr\7"+
		"k\2\2rs\7f\2\2st\7g\2\2tu\7p\2\2uv\7v\2\2vw\7k\2\2wx\7c\2\2xy\7n\2\2y"+
		"z\7k\2\2z{\7v\2\2{|\7{\2\2|}\7A\2\2}$\3\2\2\2~\177\7)\2\2\177\u0080\7"+
		"u\2\2\u0080&\3\2\2\2\u0081\u0082\7g\2\2\u0082\u0083\7z\2\2\u0083\u0084"+
		"\7g\2\2\u0084\u0085\7e\2\2\u0085\u0086\7w\2\2\u0086\u0087\7v\2\2\u0087"+
		"\u0088\7c\2\2\u0088\u0089\7d\2\2\u0089\u008a\7n\2\2\u008a\u008b\7g\2\2"+
		"\u008b\u008c\7A\2\2\u008c(\3\2\2\2\u008d\u008e\7G\2\2\u008e\u008f\7P\2"+
		"\2\u008f\u009e\7E\2\2\u0090\u0091\7F\2\2\u0091\u0092\7G\2\2\u0092\u009e"+
		"\7E\2\2\u0093\u0094\7J\2\2\u0094\u0095\7C\2\2\u0095\u0096\7U\2\2\u0096"+
		"\u009e\7J\2\2\u0097\u0098\7C\2\2\u0098\u0099\7U\2\2\u0099\u009a\7U\2\2"+
		"\u009a\u009b\7G\2\2\u009b\u009c\7T\2\2\u009c\u009e\7V\2\2\u009d\u008d"+
		"\3\2\2\2\u009d\u0090\3\2\2\2\u009d\u0093\3\2\2\2\u009d\u0097\3\2\2\2\u009e"+
		"*\3\2\2\2\u009f\u00a1\t\2\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2"+
		"\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3,\3\2\2\2\u00a4\u00a6\t"+
		"\3\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\27\2\2\u00aa.\3\2\2\2"+
		"\6\2\u009d\u00a2\u00a7\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}