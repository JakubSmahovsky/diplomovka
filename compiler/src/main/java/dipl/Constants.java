package dipl;

public final class Constants{
  // private contrustor to forbid instances of Constants
  private Constants(){}

  public static enum VariableSort {
    NOSORT,
    PUBLIC,
    FRESH,
    TEMPORAL
  }

  public static enum VariableDefined {
    DISTRIBUTED_LEFT,
    DISTRIBUTED_RIGHT,
    KNOWS_PUBLIC,
    KNOWS_PRIVATE,
    GENERATES,
    ASSIGNMENT_LEFT,
    ASSIGNMENT_RIGHT,
    CHECK,
    MESSAGE,
    QUERY
  }

  public static String sortString(VariableSort sort) {
    switch (sort) {
      case NOSORT: return "";
      case FRESH: return "~";
      case PUBLIC: return "$";
      case TEMPORAL: return "#";
      default: return "Invalid sort, impossible!";
    }
  }

  // prefabs
  public static final String RESTRICTION_EQUALITY = 
    "restriction Equality:\r\n" +
    Constants.INDENTATION + "\"All x y #i. " + Constants.FACT_EQUALITY + "(x,y) @i ==> x = y\"\r\n" +
    "\r\n";

  // input declaration options
  public static final String IN_DEFAULT = "default";
  public static final String IN_EXPLICIT = "explicit";
  public static final String IN_IMPLICIT = "implicit";
  public static final String IN_TRUE = "true";
  public static final String IN_FALSE = "false";
  public static final String IN_PUBLIC = "public";
  public static final String IN_PRIVATE = "private";
  // input cryptographic function symbols
  public static final String IN_PK = "PK";
  public static final String IN_SIGN = "SIGN";
  public static final String IN_SENC = "ENC";
  public static final String IN_SDEC = "DEC";
  public static final String IN_AENC = "AENC";
  public static final String IN_ADEC = "ADEC";
  public static final String IN_HASH = "HASH";
  public static final String IN_TUPLE_OPEN = "{";
  public static final String IN_TUPLE_CLOSE = "}";
  // input cryptographic function symbols - output only
  public static final String IN_FIRST = "FIRST";
  public static final String IN_SECOND = "SECOND";
  public static final String IN_INVERSE = "INV";
  // input check symbols
  public static final String IN_EQUALS = "EQUALS";
  public static final String IN_SIGNVERIF = "SIGNVERIF";
  // input special variables
  public static final String IN_INSTANCEID = "instanceID";
  public static final String IN_SESSIONID = "sessionID";
  
  // builtin names (Tamarin code)
  public static final String BUILTIN_SYMMETRIC_ENCRYPTION = "symmetric-encryption";
  public static final String BUILTIN_ASYMMETRIC_ENCRYPTION = "asymmetric-encryption";
  public static final String BUILTIN_SIGNING = "signing";
  public static final String BUILTIN_HASHING = "hashing";
  public static final String BUILTIN_DH = "diffie-hellman";
  // custom fact names (Tamarin code)
  public static final String FACT_EQUALITY = "Eq";
  public static final String FACT_LONG_TERM_PRIVATE = "LTP";
  public static final String FACT_AUTHENTICATION_SENT = "Sent";
  public static final String FACT_INJ_AUTHENTICATION_SENT = "InjSent";
  public static final String FACT_PRINCIPALS = "Principals";
  public static final String FACT_DISHONEST= "Dishonest";
  public static final String FACT_PREFIX_PRINCIPALID = "Pal";
  // custom rule names (Tamarin code)
  public static final String RULE_LONG_TERM_REVEAL = "RevealLT";
  public static final String RULE_INSTANCE = "INSTANCE";
  public static final String RULE_SESSION = "SESSION";
  // custom variable names (Tamarin code)
  public static final String PREFIX_VARIABLEID = "var";
  public static final String PREFIX_TEMPORALID = "tmp";

  // Tamarin predefined fact names
  public static final String COMMAND_IN = "In";
  public static final String COMMAND_OUT = "Out";
  public static final String COMMAND_FRESH = "Fr";
  public static final String INTRUDER_KNOWS_OUTPUT = "KU";
  public static final String INTRUDER_KNOWS_LEMMA = "K";
  // Tamarin functions
  public static final String T_FIRST = "fst";
  public static final String T_SECOND = "snd";
  public static final String T_PK = "pk";
  public static final String T_SIGN = "sign";
  public static final String T_VERIFY = "verify";
  public static final String T_SENC = "senc";
  public static final String T_SDEC = "sdec";
  public static final String T_AENC = "aenc";
  public static final String T_ADEC = "adec";
  public static final String T_HASH = "h";
  public static final String T_EXP = "^";
  public static final String T_MUL = "*";
  public static final String T_INVERSE = "inv";
  public static final String T_EXP_WORD = "exp";
  public static final String T_MUL_WORD = "Mult";
  public static final String T_TUPLE_OPEN = "<";
  public static final String T_TUPLE_CLOSE = ">";
  public static final String T_TUPLE_WORD = "pair";
  // JSON Graph keys
  public static final String JSON_EDGES = "jgEdges";
  public static final String JSON_NODES = "jgNodes";
  public static final String JSON_NODEID = "jgnId";
  public static final String JSON_NODELABEL = "jgnLabel";
  public static final String JSON_NODETYPE = "jgnType";
  public static final String JSON_NODEMETA = "jgnMetadata";
  public static final String JSON_EDGEFROM = "jgeSource";
  public static final String JSON_EDGETO = "jgeTarget";
  public static final String JSON_NODE_BLOCK = "isProtocolRule";
  public static final String JSON_NODE_MISSING = "missingNodePrem";
  public static final String JSON_NODE_ADVERSARY = "isIntruderRule";
  public static final String JSON_NODE_FRESH = "isFreshRule";
  public static final String JSON_NODE_UNSOLVED = "unsolvedActionAtom";
  public static final String JSON_NODEMETA_CONCLUSIONS = "jgnConcs";
  public static final String JSON_NODEMETA_ACTIONS = "jgnActs";
  public static final String JSON_NODEMETA_TERMS = "jgnFactTerms";
  public static final String JSON_NODEMETA_FULLTERM = "jgnShow";
  public static final String JSON_NODEMETA_CONSTANT = "jgnConst";
  public static final String JSON_FUNCTION_LABEL_SEND = "Send";
  public static final String JSON_FUNCTION_LABEL_RECEIVE = "Recv";
  public static final String JSON_FUNCTION_LABEL_COERCE = "Coerce";
  public static final String JSON_FUNCTION_LABEL_FRESH = "FreshConstr";
  public static final String JSON_FUNCTION_PREFIX_CONSTRUCT = "Constrc";
  public static final String JSON_FUNCTION_PREFIX_DECONSTRUCT = "Destrd";
  // target query names
  public static final String EXECUTABLE = "executable";
  public static final String CONFIDENTIALITY = "secrecy";
  public static final String FORWARD_SECRECY = "forwardSecrecy";
  public static final String AUTHENTICATION = "authentication";
  public static final String INJ_AUTHENTICATION = "injAuthentication";
  // output parts
  public static final String CONSTANT_OPEN = "\'";
  public static final String CONSTANT_CLOSE = "\'";
  public static final String INDENTATION = "  ";
  public static final String NAME_SEPARATOR = "_";
  public static final String COMMA_SEPARATOR = ", ";
  public static final String FACT_SEPARATOR = "," + Constants.LINE_BREAK;
  public static final String QUANTIFICATION_SEPARATOR = " ";
  public static final String PERSISTENT = "!";
  public static final String OPEN_BR = "(";
  public static final String CLOSE_BR = ")";
  public static final String COLON = ":";
  public static final String ALIAS_OPERATOR = " = ";
  public static final String LINE_BREAK = "\r\n";
  public static final String QUANTIFIER_TRACE_EXISTS = "exists-trace";
  public static final String QUANTIFIER_TRACE_FORALL = "all-traces";
  public static final String QUANTIFIER_EXISTS = "Ex ";
  public static final String QUANTIFIER_FORALL = "All ";
  public static final String QUANTIFICATION_CLOSE = ".";
  // Tamarin lemma operators
  public static final String LEMMA_EQUALS = " = ";
  public static final String LEMMA_BEFORE = " < ";
  public static final String LEMMA_ATTEMPORAL = " @ ";
  public static final String LEMMA_NEGAION = "not ";
  public static final String LEMMA_CONJUNCTION = " &";
  public static final String LEMMA_DISJUNCTION = " |";
  public static final String LEMMA_IMPLICATION = "==>";
  // Tamarin clauses
  public static final String THEORY_OPEN = "theory SimpleTheory" + Constants.LINE_BREAK
      + "begin" + Constants.LINE_BREAK + Constants.LINE_BREAK; 
  public static final String THEORY_CLOSE = "end" + Constants.LINE_BREAK;    
  public static final String CLAUSE_BUILTINS = "builtins: ";
  public static final String CLAUSE_RULE = "rule ";
  public static final String ALIASES_OPEN = "let" + Constants.LINE_BREAK;
  public static final String ALIASES_CLOSE = "in" + Constants.LINE_BREAK;
  public static final String PREMISES_OPEN = "[" + Constants.LINE_BREAK;
  public static final String PREMISES_CLOSE = "]-";
  public static final String ACTIONS_OPEN = "-[" + Constants.LINE_BREAK;
  public static final String ACTIONS_CLOSE = "]";
  public static final String CONCLUSIONS_OPEN = "->[" + Constants.LINE_BREAK;
  public static final String CONCLUSIONS_CLOSE = "]" + Constants.LINE_BREAK + Constants.LINE_BREAK;
  public static final String CLAUSE_LEMMA = "lemma ";
  public static final String LEMMA_OPEN = " \"";
  public static final String LEMMA_CLOSE = "\"";

  // miscelaneous
  public static final String THEORY_FILE_EXTENSION = ".spthy";
  public static final String DEFAULT_THEORY_PATH = "theory";
  public static final String DEFAULT_SOURCES_PATH = "sources.txt";
  public static final String OUTPUT_SEPARATOR = "==============================================================================";
  public static final String SOURCES_HEADER = OUTPUT_SEPARATOR + "SOURCES" + OUTPUT_SEPARATOR;
  public static final String LOGGING_SOLVEDHOW_DIRECTLY = "(directly)";
}