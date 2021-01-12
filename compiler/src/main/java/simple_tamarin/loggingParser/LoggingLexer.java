// Generated from Logging.g4 by ANTLR 4.8
package simple_tamarin.loggingParser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LoggingLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, ATTIMEPOINT=13, PERSISTENT=14, SOLVEDHOW=15, 
		NUMBER=16, IDENTIFIER=17, WHITESPACE=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "ATTIMEPOINT", "PERSISTENT", "SOLVEDHOW", "NUMBER", 
			"IDENTIFIER", "WHITESPACE"
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


	public LoggingLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Logging.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u0081\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16S\n\16\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20n\n\20\3\21\6\21"+
		"q\n\21\r\21\16\21r\3\22\3\22\6\22w\n\22\r\22\16\22x\3\23\6\23|\n\23\r"+
		"\23\16\23}\3\23\3\23\2\2\24\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\3\2\5\3\2\62;\5\2C\\aac|\5"+
		"\2\13\f\17\17\"\"\2\u0086\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5\67\3\2\2\2\79\3"+
		"\2\2\2\t=\3\2\2\2\13?\3\2\2\2\rA\3\2\2\2\17C\3\2\2\2\21E\3\2\2\2\23G\3"+
		"\2\2\2\25I\3\2\2\2\27K\3\2\2\2\31M\3\2\2\2\33R\3\2\2\2\35T\3\2\2\2\37"+
		"m\3\2\2\2!p\3\2\2\2#v\3\2\2\2%{\3\2\2\2\'(\7u\2\2()\7q\2\2)*\7n\2\2*+"+
		"\7x\2\2+,\7g\2\2,-\7f\2\2-.\7\"\2\2./\7i\2\2/\60\7q\2\2\60\61\7c\2\2\61"+
		"\62\7n\2\2\62\63\7\"\2\2\63\64\7p\2\2\64\65\7t\2\2\65\66\7\60\2\2\66\4"+
		"\3\2\2\2\678\7<\2\28\6\3\2\2\29:\7d\2\2:;\7{\2\2;<\7<\2\2<\b\3\2\2\2="+
		">\7*\2\2>\n\3\2\2\2?@\7.\2\2@\f\3\2\2\2AB\7+\2\2B\16\3\2\2\2CD\7>\2\2"+
		"D\20\3\2\2\2EF\7@\2\2F\22\3\2\2\2GH\7&\2\2H\24\3\2\2\2IJ\7\u0080\2\2J"+
		"\26\3\2\2\2KL\7%\2\2L\30\3\2\2\2MN\7\60\2\2N\32\3\2\2\2OP\7\u25b8\2\2"+
		"PS\7\u2082\2\2QS\7B\2\2RO\3\2\2\2RQ\3\2\2\2S\34\3\2\2\2TU\7#\2\2U\36\3"+
		"\2\2\2VW\7*\2\2WX\7r\2\2XY\7t\2\2YZ\7g\2\2Z[\7e\2\2[\\\7q\2\2\\]\7o\2"+
		"\2]^\7r\2\2^_\7w\2\2_`\7v\2\2`a\7g\2\2ab\7f\2\2bn\7+\2\2cd\7*\2\2de\7"+
		"f\2\2ef\7k\2\2fg\7t\2\2gh\7g\2\2hi\7e\2\2ij\7v\2\2jk\7n\2\2kl\7{\2\2l"+
		"n\7+\2\2mV\3\2\2\2mc\3\2\2\2n \3\2\2\2oq\t\2\2\2po\3\2\2\2qr\3\2\2\2r"+
		"p\3\2\2\2rs\3\2\2\2s\"\3\2\2\2tw\t\3\2\2uw\5!\21\2vt\3\2\2\2vu\3\2\2\2"+
		"wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y$\3\2\2\2z|\t\4\2\2{z\3\2\2\2|}\3\2\2\2"+
		"}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\b\23\2\2\u0080&\3\2\2\2\t"+
		"\2Rmrvx}\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}