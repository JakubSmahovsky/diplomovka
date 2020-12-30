package simple_tamarin;

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

  public static enum CommandType {
    IN,
    OUT,
    FRESH
  }

  public static enum VariableDefined {
    PUBLIC_DEFINITION,
    PUBLIC_KNOWS,
    PRIVATE_DEFINITION, // generates or knows private
    PRIVATE_LEFT,
    USE_RIGHT,
    USE_MESSAGE
  }

  public static final String[] reservedNames = {
    "principals",
    "knows",
    "queries",
    "generates",
    "public",
    "private"
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

  // restrictions; put an empty line in Tamarin code after every restriction
  public static final String RESTRICTION_EQUALITY = 
    "restriction Equality:\r\n" +
    Constants.INDENT + "\"All x y #i. Eq(x,y) @i ==> x = y\"\r\n" +
    "\r\n";

  // builtins
  public static final String BUILTIN_SYMMETRIC_ENCRYPTION = "symmetric-encryption";
  public static final String BUILTIN_HASHING = "hashing";
  // Tamarin functions
  public static final String COMMAND_IN = "In";
  public static final String COMMAND_OUT = "Out";
  public static final String COMMAND_FRESH = "Fr";
  public static final String INIT = "init";
  public static final String SENC = "senc";
  public static final String SDEC = "sdec";
  public static final String HASH = "h";
  // VP functions
  public static final String VPSENC = "ENC";
  public static final String VPSDEC = "DEC";
  public static final String VPASSERT = "ASSERT";
  public static final String VPHASH = "HASH";

  // miscelaneous
  public static final String OUTPUT_SEPARATOR = "==============================================================================";
  public static final String SOURCES_HEADER = OUTPUT_SEPARATOR + "SOURCES" + OUTPUT_SEPARATOR;
  public static final String INDENT = "  ";
  public static final String DEFAULT_THEORY_NAME = "spthy";
  public static final String TEMPORAL_NAME = "t";
  public static final String EXECUTABLE = "executable";
  public static final String EQUALITY = "Eq";
}