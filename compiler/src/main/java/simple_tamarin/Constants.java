package simple_tamarin;

public final class Constants{
  private Constants(){}

  public static enum VariableSort {
    NOSORT,
    PUBLIC,
    FRESH,
    TEMPORAL
  }

  public static enum CommandType {
    IN,
    OUT,
    FRESH,
    SDEC
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

  public static final String INDENT = "  ";
  public static final String DEFAULTTHEORYNAME = "spthy";
  public static final String BUILTIN_SYMMETRIC_ENCRYPTION = "symmetric-encryption";
  public static final String EXECUTABLE = "executable";
  public static final String TEMPORAL = "t";
  public static final String INIT = "init";
  public static final String SENC = "senc";
  public static final String SDEC = "sdec";
  public static final String VPSENC = "ENC";
  public static final String VPSDEC = "DEC";
}