package simple_tamarin;

import org.antlr.v4.runtime.Token;

import simple_tamarin.dataStructures.Principal;
import simple_tamarin.parser.Simple_tamarinParser.TermContext;

public final class Errors {
  public static boolean showInfo = true;
  private Errors(){};

  public static void ErrorWrongKey(TermContext got) {
    String message = "Key \"" + got.getText() + "\" does not match the key used for encoding!";
    print(ERROR, got.start, message);
  }

  public static void ErrorDecodingNotEncoded(TermContext value){
    String message = "Attempting to decode value \"" + value.getText() + "\" which is not symmetrically encoded!";
    print(ERROR, value.start, message);
  }

  public static void ErrorArgumentsCount(Token function, int expected, int got) {
    String message = "Wrong number of arguments for function \"" + function.getText() + "\" expected " + expected + ", but got " + got + "!";
    print(ERROR, function, message); 
  }

  public static void ErrorReservedName(Token name) {
    String message = "Name \"" + name.getText() + "\" is reserved!";
    print(ERROR, name, message);
  }

  public static void ErrorPrincipalNameCollision(Token principal) {
    String message = "Principal's name \"" + principal.getText() + "\" is allready used for a public variable!";
    print(ERROR, principal, message);
  }

  public static void ErrorPrincipalDoesNotExist(Token principal) {
    String message = "Principal \"" + principal.getText() + "\" does not exist at this point!";
    print(ERROR, principal, message); 
  }

  public static void ErrorVariableUnknown(Principal principal, Token variable) {
    String message = "Principal \"" + principal + "\" is trying to use a variable \"" + variable.getText() + "\", which he doesn't know!";
    print(ERROR, variable, message);
  }

  public static void ErrorVariableCollisionPrivate(Principal principal, Token variable) {
    String message = "Principal \"" + principal + "\" allready knows a variable with name \"" + variable.getText() + "\" as priate!";
    print(ERROR, variable, message);
  }

  public static void WarningVariableShadowed(Token variable) {
    String message = "Variable \"" + variable.getText() + "\" shadows a public variable!";
    print(WARNING, variable, message);
  }

  public static void WarningVariableKnownPrivate(Principal principal, Token variable) {
    String message = "Principal \"" + principal + "\" allready knows a private variable \"" + variable.getText() + "\"!";
    print(WARNING, variable, message);
  }

  public static void WarningVariableEphemeralShadowed(Token variable) {
    String message = "Ephemeral variable \"" + variable.getText() + "\" allready exist for some principal, this will create a different, long-term variable + \"" + variable.getText() + "\"";
    print(WARNING, variable, message);
  }

  public static void WarningQueryExecutableDuplicite(Token query) {
    String message = "Duplicite request for executable query. The query will only be verified once.";
    print(WARNING, query, message);
  }

  public static void InfoDeclareLongTermPubVariable(Token variable) {
    String message = "Long term public variable \"" + variable.getText() + "\" is not declared. It is recommended to declare all long-term public variables.";
    print(INFO, variable, message);
  }

  public static void InfoDeclarePrincipal(Token principal) {
    String message = "Principal \"" + principal.getText() + "\" is not declared. It is recommended to declare all principals.";
    print(INFO, principal, message);
  }

  public static void InfoKnowsInFirstBlock(Token start) {
    String message = "Effects of knows command are global, it is recommended to use it in the first block.";
    print(INFO, start, message);
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
}
