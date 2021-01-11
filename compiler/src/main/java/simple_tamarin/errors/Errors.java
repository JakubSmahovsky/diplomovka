package simple_tamarin.errors;

import org.antlr.v4.runtime.Token;

import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.term.Term;
import simple_tamarin.stParser.Simple_tamarinParser.TermContext;
import simple_tamarin.Constants;

/**
 * A fully static class (with no instances - private constructor)
 * containing methods for logging error messages. Logging that should
 * quit the program throws runtime exception.
 */

public final class Errors{
  public static boolean showInfo = Constants.showInfo;
  public static boolean quitOnWarning = Constants.quitOnWarning;
  private Errors(){};

  public static void ErrorWrongKey(TermContext got) {
    String message = "Key \"" + got.getText() + "\" does not match the" + "key used for encoding!";
    error(got.start, message);
  }

  public static void ErrorDecodingNotEncoded(TermContext value){
    String message = "Attempting to decode value \"" + value.getText() + "\" which is not symmetrically encoded!";
    error(value.start, message);
  }

  public static void ErrorArgumentsCount(Token function, int expected, int got) {
    String message = "Wrong number of arguments for function \"" + function.getText() + "\" expected " + expected + ", but got " + got + "!";
    error(function, message);
  }

  public static void ErrorArgumentsMinCount(Token function, int expected, int got) {
    String message = "Insufficient number of arguments for function \"" + function.getText() + "\" expected at least" + expected + ", but got " + got + "!";
    error(function, message);
  }

  public static void ErrorReservedName(Token name) {
    String message = "Name \"" + name.getText() + "\" is reserved!";
    error(name, message);
  }

  public static void ErrorPrincipalNameCollision(Token principal) {
    String message = "Principal's name \"" + principal.getText() + "\" is allready used for a public variable!";
    error(principal, message);
  }

  public static void ErrorPrincipalDoesNotExist(Token principal) {
    String message = "Principal \"" + principal.getText() + "\" does not exist at this point!";
    error(principal, message);
  }

  public static void ErrorVariableUnknown(Principal principal, Token variable) {
    String message = "Principal \"" + principal + "\" is trying to use a variable \"" + variable.getText() + "\", which it doesn't know!";
    error(variable, message);
  }

  public static void ErrorVariableCollisionPrivate(Principal principal, Token variable) {
    String message = "Principal \"" + principal + "\" allready knows a variable with name \"" + variable.getText() + "\" as priate!";
    error(variable, message);
  }

  public static void ErrorVariableCollisionPublic(Term variable, Token posToken) {
    String message = "Public variable \"" + variable + "\" allready exists!";
    error(posToken, message);
  }

  public static void ErrorMessageNontransparent(Token start) {
    String message = "Attempting to send message with non-transparent term \"" + start.getText() + "\"!";
    error(start, message);
  }

  public static void ErrorLeftNontransparent(Token start) {
    String message = "Attempting to use a non-transparent term \"" + start.getText() + "\" on the left side of an assignment!";
    error(start, message);
  }

  public static void ErrorCannotUnify(TermContext left, TermContext right) {
    String message = "Ubale to unify term \"" + left.getText() + "\" with term \"" + right.getText() + "\"!";
    error(left.start, message);
  }

  public static void WarningVariableShadowed(Token variable) {
    String message = "Variable \"" + variable.getText() + "\" shadows a public variable!";
    warning(variable, message);
  }

  public static void WarningVariableEphemeralShadowed(Token variable) {
    String message = "Ephemeral variable \"" + variable.getText() + "\" allready exist for some principal, this will create a different long-term variable + \"" + variable.getText() + "\"!";
    warning(variable, message);
  }

  public static void WarningAssertNeverTrue(Token assertStart) {
    String message = "The terms being compared can never be equal! This likely means the model will not be executable!";
    warning(assertStart, message);
  }

  public static void WarningQueryExecutableDuplicite(Token query) {
    String message = "Duplicite request for executable query. The query will only be verified once!";
    warning(query, message);
  }

  public static void InfoDeclareLongTermVariable(Token variable) {
    String message = "Long term variable \"" + variable.getText() + "\" is not declared. It is recommended to declare all long-term variables.";
    info(variable, message);
  }

  public static void InfoDeclarePrincipal(Token principal) {
    String message = "Principal \"" + principal.getText() + "\" is not declared. It is recommended to declare all principals.";
    info(principal, message);
  }

  public static void InfoKnowsInFirstBlock(Token start) {
    String message = "Effects of the \"knows\" command are global, it is recommended to use it in the first block.";
    info(start, message);
  }

  public static void DebugUnexpectedCall(String called, String where) {
    debug("Unexpected call to function " + called + " in " + where + "!");
  }

  public static void DebugUnexpectedTokenType(String token, String where) {
    debug("Unexpected token " + token + " found in " + where + "!");
  }

  public static void DebugNotTemporal(String function) {
    debug("Temporal variable argument is not of sort TEMPORAL in " + function + "!" );
  }

  public static void DebugCommandType(String command, String function) {
    debug("Unexpected command type " + command + " in " + function + "!");
  }

  public static void DebugUnexpectedValueType(String typeDescription, String value, String where) {
    debug("Unexpected " + typeDescription + " value " + value + " in " + where + "!");
  }

  public static void DebugMissingImplementation(String function, String where) {
    debug("Missing implementation of function " + function + " iherited by " + where + "!");
  }

  public static void error(Token posToken, String message) {
    print(ERROR, posToken, message);
    throw new STException();
  }

  public static void warning(Token posToken, String message) {
    print(WARNING, posToken, message);
    if (quitOnWarning) {
      throw new STException();
    }
  }

  public static void info(Token posToken, String message) {
    if (showInfo) {
      print(INFO, posToken, message);
    }
  }

  public static void debug(String message) {
    System.out.println(DEBUG + message);
    System.out.println("This means an internal error occoured in our compiler, please report this error to: smahovsky6@fmph.uniba.sk");
    throw new STException();
  }

  public static void print(String type, Token posToken, String message) {
    if (type == INFO && !showInfo) {
      return;
    }
    System.out.println(type + pos(posToken) + message);
  }

  public static String pos(Token token) {
    return "(" + token.getLine() + ", " + token.getCharPositionInLine() + "): ";
  }

  public static final String ERROR = "Error ";
  public static final String WARNING = "Warning ";
  public static final String INFO = "Info ";
  public static final String DEBUG = "DEBUG ";
}
