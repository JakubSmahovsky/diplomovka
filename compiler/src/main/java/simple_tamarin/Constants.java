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
    FRESH
  }
  
  public static final String[] reservedNames = {
    "principals",
    "knows",
    "queries",
    "generates",
    "public",
    "private"
  };

  public static final String EXECUTABLE = "executable";
  public static final String TEMPORAL = "t";
  public static final String INIT = "init";
}