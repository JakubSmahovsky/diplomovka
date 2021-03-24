package simpleT;

public final class Constants{
  // private contrustor to forbid instances of Constants
  private Constants(){}

  // compiler default values
  public static final boolean showInfo = true;
  public static final boolean quitOnWarning = false;

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

  public static final String[] reservedNames = {
    "principals",
    "knows",
    "queries",
    "generates",
    "public",
    "private",
    "instanceid"
  };

  public static String sortString(VariableSort sort) {
    switch (sort) {
      case NOSORT: return "";
      case FRESH: return "~";
      case PUBLIC: return "$";
      case TEMPORAL: return "#";
      default: return "Invalid sort, impossible!";
    }
  }

  // restrictions and prefabs; put an empty line in Tamarin code after every on of these
  public static final String RESTRICTION_EQUALITY = 
    "restriction Equality:\r\n" +
    Constants.INDENT + "\"All x y #i. " + Constants.EQUALITY + "(x,y) @i ==> x = y\"\r\n" +
    "\r\n";

  public static final String PREFAB_PRIVATE_REVEAL =
    "rule "+ Constants.PREFAB_PRIVATE_REVEAL_NAME +": [\r\n" +
    Constants.INDENT + "!" + Constants.PRINCIPAL_PRIVATE + "(Principal, Private)\r\n" +
    "]--[\r\n" +
    Constants.INDENT + Constants.FACT_DISHONEST + "(Principal)\r\n" +
    "]->[\r\n" +
    Constants.INDENT + "Out(Private)\r\n"
    + "]\r\n" +
    "\r\n";

  // builtins and prefabs
  public static final String BUILTIN_SYMMETRIC_ENCRYPTION = "symmetric-encryption";
  public static final String BUILTIN_SIGNING = "signing";
  public static final String BUILTIN_HASHING = "hashing";
  public static final String BUILTIN_DH = "diffie-hellman";
  public static final String PREFAB_PRIVATE_REVEAL_NAME = "privateReveal";
  public static final String FACT_DISHONEST= "Dishonest";
  // custom fact names (restriction and prefab sources)
  public static final String EQUALITY = "Eq";
  public static final String PRINCIPAL_PRIVATE = "PrincipalPrivate";
  public static final String FACT_PRINCIPALS = "Principals";
  // Tamarin functions
  public static final String COMMAND_IN = "In";
  public static final String COMMAND_OUT = "Out";
  public static final String COMMAND_FRESH = "Fr";
  public static final String FIRST = "fst";
  public static final String SECOND = "snd";
  public static final String PK = "pk";
  public static final String SIGN = "sign";
  public static final String VERIFY = "verify";
  public static final String SENC = "senc";
  public static final String SDEC = "sdec";
  public static final String HASH = "h";
  public static final String EXP = "^";
  public static final String MUL = "*";
  public static final String INVERSE = "inv";
  public static final String EXP_WORD = "exp";
  // VP functions
  public static final String VPPK = "PK";
  public static final String VPSIGN = "SIGN";
  public static final String VPSENC = "ENC";
  public static final String VPSDEC = "DEC";
  public static final String VPHASH = "HASH";
  // VP checks
  public static final String VPEQUALS = "EQUALS";
  public static final String VPSIGNVERIF = "SIGNVERIF";
  // VP forbiden in specification
  public static final String VPFIRST = "FIRST";
  public static final String VPSECOND = "SECOND";
  public static final String VPINVERSE = "INV";
  // JSON Graph keys
  public static final String JSON_EDGES = "jgEdges";
  public static final String JSON_NODES = "jgNodes";
  public static final String JSON_NODEID = "jgnId";
  public static final String JSON_NODELABEL = "jgnLabel";
  public static final String JSON_NODETYPE = "jgnType";
  public static final String JSON_EDGEFROM = "jgeSource";
  public static final String JSON_EDGETO = "jgeTarget";
  public static final String JSON_NODE_BLOCK = "isProtocolRule";
  public static final String JSON_NODE_MISSING = "missingNodePrem";
  public static final String JSON_NODE_FUNCTION = "isIntruderRule";
  public static final String JSON_NODE_FRESH = "isFreshRule";
  public static final String JSON_NODE_UNSOLVED = "unsolvedActionAtom";
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
  // miscelaneous
  public static final String OUTPUT_SEPARATOR = "==============================================================================";
  public static final String SOURCES_HEADER = OUTPUT_SEPARATOR + "SOURCES" + OUTPUT_SEPARATOR;
  public static final String INDENT = "  ";
  public static final String DEFAULT_THEORY_NAME = "spthy";
  public static final String MANDATORY_THEORY_EXTENSION = ".spthy";
  public static final String DEFAULT_THEORY_PATH = "theory";
  public static final String DEFAULT_SOURCES_OUTPUT_PATH = "sources.txt";
  public static final String INIT = "INIT";
  public static final String VARIABLE_NAME = "qq";
  public static final String FACT_NAME = "QQ";
  public static final String TEMPORAL_NAME = "t";
  public static final String NAMES_SEPARATOR = "_";
  public static final String INTRUDER_KNOWS_OUTPUT = "KU";
  public static final String INTRUDER_KNOWS_LEMMA = "K";
  public static final String SOLVEDHOW_DIRECTLY = "(directly)";
  public static final String VARIABLE_INSTANCEID = "instanceid";
}