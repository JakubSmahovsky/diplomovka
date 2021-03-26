package simpleT.errors;

import java.util.ArrayList;

import org.antlr.v4.runtime.Token;

import simpleT.Constants;
import simpleT.dataStructures.Principal;
import simpleT.stParser.SimpleTParser.TermContext;

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
    String message = "Key \"" + got.getText() + "\" does not match the key used for encoding!";
    error(got.start, message);
  }

  public static void ErrorKeyNotPublicKey(TermContext got) {
    String message = "Key \"" + got.getText() + "\" is not a public key!";
    error(got.start, message);
  }

  public static void ErrorWrongPublicKeySigning(TermContext got) {
    String message = "Public key \"" + got.getText() + "\" does not match the key used for signing!";
    error(got.start, message);
  }

  public static void ErrorWrongMessageSigning(TermContext got) {
    String message = "Message \"" + got.getText() + "\" does not match the message used for signing!";
    error(got.start, message);
  }

  public static void ErrorDecodingNotEncoded(TermContext value){
    String message = "Attempting to decode value \"" + value.getText() + "\" which is not symmetrically encoded!";
    error(value.start, message);
  }

  public static void ErrorVerifyingNotSigned(TermContext value){
    String message = "Attempting to verify signature \"" + value.getText() + "\" which is not actually a signature!";
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
    String message = "Principal's name \"" + principal.getText() + "\" is already used for a public variable!";
    error(principal, message);
  }

  public static void ErrorVariableNameCollisionPublic(Token variable) {
    String message = "A public variable name \"" + variable.getText() + "\" already exists!";
    error(variable, message);
  }

  public static void ErrorPrincipalDoesNotExist(Token principal) {
    String message = "Principal \"" + principal.getText() + "\" does not exist at this point!";
    error(principal, message);
  }

  public static void ErrorVariableUnknown(Principal principal, Token variable) {
    String message = "Principal \"" + principal + "\" is trying to use a variable \"" + variable.getText() + "\", which it doesn't know!";
    error(variable, message);
  }

  public static void ErrorVariableAlreadyKnown(Principal principal, Token variable, boolean pub) {
    String message = "Principal \"" + principal + "\" already knows a variable with name \"" + variable.getText() + "\" as " + (pub ? "public" : "private") + "!";
    error(variable, message);
  }

  public static void ErrorVariableNotLongTerm(Token variable) {
    String message = "Variable + \"" + variable + "\" is not long-term!";
    error(variable, message);
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
    String message = "Unable to unify term \"" + left.getText() + "\" with term \"" + right.getText() + "\"!";
    error(left.start, message);
  }

  public static void ErrorReceivedNotEqual(Token start, String variable) {
    String message = "Variable " + variable + " is not expected to be equal to the one known by the recipient!";
    error(start, message);
  }

  public static void ErrorUnaryEqualsNotVariable(Token start, String term) {
    String message = "Unexpected term " + term + " in unary EQUALS! Only variables make sense in this check!";
    error(start, message);
  }

  public static void ErrorUnaryEqualsNotPending(Token start, String variable) {
    String message = "Unary EQUALS not applicable to varibale " + variable + "! No implicit euqlity check was made for this variable!";
    error(start, message);
  }

  public static void ErrorEqualsNeverTrue(Token start) {
    String message = "The terms being compared can never be equal! This likely means the model will not be executable!";
    error(start, message);
  }

  public static void ErrorQueryVariableNotSent(Token start, String principal, String variable) {
    String message = "Principal " + principal + " never sent the variable " + variable + "!";
    error(start, message);
  }

  public static void ErrorQueryVariableNotReceived(Token start, String principal, String variable) {
    String message = "Principal " + principal + " never received the variable " + variable + "!";
    error(start, message);
  }

  public static void WarningUndeclaredPrincipal(Token principal) {
    String message = "Principal \"" + principal.getText() + "\" was not declared even though some principals were declared!";
    warning(principal, message);
  }

  public static void WarningShadowedPublic(Token variable) {
    String message = "A public variable \"" + variable.getText() + "\" already exists, this will declare a different private variable!";
    warning(variable, message);
  }

  public static void WarningShadowedLongTermPrivate(Token variable) {
    String message = "A long-term private variable \"" + variable.getText() + "\" already exists for some principal, this will declare a different variable!";
    warning(variable, message);
  }

  public static void WarningQueryExecutableDuplicite(Token query) {
    String message = "Duplicite request for executable query. The query will only be verified once!";
    warning(query, message);
  }

  public static void WarningQueryConfidentialityDuplicite(Token variable) {
    String message = "Duplicite request for confidentiality query of variable " + variable.getText() + ". The query will only be verified once!";
    warning(variable, message);
  }

  public static void InfoDeclarePrincipal(Token principal) {
    String message = "Principal \"" + principal.getText() + "\" is not declared. It is recommended to declare all principals.";
    info(principal, message);
  }

  public static void InfoUnaryEquals(Token block, ArrayList<String> pendingVariables) {
    String message = "Block performs implicit equality checks on variable(s) " + String.join(", ", pendingVariables) + ". Add unary EQUALS to explicitly mark these checks.";
    info(block, message);
  }

  public static void InfoKnowsInFirstBlock(Token variable) {
    String message = "It's recommended to use the knows command in the first block unless declaring knows on a distributed variable.";
    info(variable, message);
  }

  public static void DebugUnexpectedCall(String called, String where) {
    debug("Unexpected call to function " + called + " in " + where + "!");
  }

  public static void DebugUnexpectedTokenType(String token, String where) {
    debug("Unexpected token " + token + " found in " + where + "!");
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
