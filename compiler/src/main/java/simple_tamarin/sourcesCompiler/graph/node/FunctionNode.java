package simple_tamarin.sourcesCompiler.graph.node;

import simple_tamarin.Constants;
import simple_tamarin.errors.Errors;

public class FunctionNode extends Node{
  public boolean nativeIntruder; // is one of intruder functions (/actions/commands)
  public boolean nativeTamarin; // is one of native functions
  public boolean construction;
  public boolean deconstruction;
  public String function;
  public String printLabel;


  public FunctionNode(String id, String label) {
    super(id, label);
    this.construction = false;
    this.deconstruction = false;

    this.nativeIntruder = isNativeIntruderFunction(label);
    if (nativeIntruder) {
      this.printLabel = label;
      return;
    }

    this.function = parseLabel(label);
    this.nativeTamarin = isNativeTamarinFunction(function);
    this.printLabel = nativeTamarin ? label : tamarinToVP(function);
  }

  /**
   * Take a label that is not a native Intruder function, break it apart,
   * return only function name and assign construnction and deconstruction flags.
   */
  public String parseLabel(String label) {
    String[] parts = label.split("_");
    switch (parts[0]) {
      case (Constants.JSON_FUNCTION_PREFIX_CONSTRUCT):
        construction = true;
        return parts[1];
      case (Constants.JSON_FUNCTION_PREFIX_DECONSTRUCT):
        deconstruction = true;
        return parts[2];
      default:
        Errors.DebugUnexpectedValueType("function prefix", parts[0], "parseLabel in FunctionNode");
        return null;
    }
  }

  public static boolean isNativeIntruderFunction(String function) {
    return (
      function.equals(Constants.JSON_FUNCTION_LABEL_COERCE) ||
      function.equals(Constants.JSON_FUNCTION_LABEL_FRESH) ||
      function.equals(Constants.JSON_FUNCTION_LABEL_RECEIVE) ||
      function.equals(Constants.JSON_FUNCTION_LABEL_SEND));
  }

  public static boolean isNativeTamarinFunction(String function) {
    return (
      function.equals(Constants.FIRST) ||
      function.equals(Constants.SECOND));
  }

  public static String tamarinToVP(String function) {
    switch (function) {
      case (Constants.SENC):
        return Constants.VPSENC;
      case (Constants.SDEC):
        return Constants.VPSDEC;
      case (Constants.HASH):
        return Constants.HASH;
    }
    Errors.DebugUnexpectedValueType("function", function, " tamarinToVP in FunctionNode");
    return null;
  }

  @Override public String toString(){
    return "Function(" + printLabel + ")";
  }
}
