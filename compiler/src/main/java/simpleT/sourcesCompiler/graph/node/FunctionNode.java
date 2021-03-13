package simpleT.sourcesCompiler.graph.node;

import simpleT.Constants;
import simpleT.dataStructures.document.Document;
import simpleT.errors.Errors;
import simpleT.sourcesCompiler.graph.Description;

public class FunctionNode extends Node{
  public boolean nativeIntruder; // is one of intruder functions (/actions/commands)
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
      this.function = label;
      this.printLabel = label;
      return;
    }

    this.function = parseLabel(label);
    this.printLabel = tamarinToVP(function);
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
        return parts[parts.length-1];
      case (Constants.JSON_FUNCTION_PREFIX_DECONSTRUCT):
        deconstruction = true;
        return parts[parts.length-1];
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

  public static String tamarinToVP(String function) {
    switch (function) {
      case (Constants.SENC):
        return Constants.VPSENC;
      case (Constants.SDEC):
        return Constants.VPSDEC;
      case (Constants.HASH):
        return Constants.HASH;
      case (Constants.FIRST):
        return Constants.VPFIRST;
      case (Constants.SECOND):
        return Constants.VPSECOND;
      case (Constants.EXP_WORD):
        return Constants.EXP;
    }
    Errors.DebugUnexpectedValueType("function", function, " tamarinToVP in FunctionNode");
    return null;
  }

  @Override public Description renderDescription() {
    if (function.equals(Constants.JSON_FUNCTION_LABEL_FRESH)) {
      Document doc = new Document();
      doc.doc.add(new StringBuilder("Intruder may generate it."));
      return new Description(doc, doc, null, "by generation");
    }

    if (function.equals(Constants.JSON_FUNCTION_LABEL_COERCE)){
      if (parents.size() != 1) {
        Errors.debug("FunctionNode of Coerce has " + parents.size() + " parents!");
      }
      return parents.get(0).renderDescription();
    }

    if (function.equals(Constants.JSON_FUNCTION_LABEL_RECEIVE)){
      if (parents.size() != 1) {
        Errors.debug("FunctionNode of Receive has " + parents.size() + " parents!");
      }
      if (!(parents.get(0) instanceof BlockNode)) {
        Errors.debug("FunctionNode of Receive has a parent that is not a block rule!");
      }
      BlockNode block = (BlockNode)parents.get(0);

      if (block.block == null) {
        // TODO: identities in reveals
        String myLine = "Intruder may receive it from a compromited principal.";
        return new Description(new Document(myLine), new Document(myLine), block, block.label);
      }

      String myLine = "Intruder may receive it from " + block.block.principal
          + " after block " + block.block.rangeEnd + ".";
      return new Description(new Document(myLine), new Document(myLine), block, block.label);
    }

    String intruderAction;
    if (construction) {
      intruderAction = "by construction";
    } else if (deconstruction) {
      intruderAction = "by deconstruction";
    } else {
      Errors.debug("FunctionNode has no matching representation in renderDescription()!");
      intruderAction = "";
    }

    StringBuilder myLine = new StringBuilder("Intruder may get it " + intruderAction 
        + " using function " + printLabel + " from values it got from:");
    Document shortDoc = new Document(myLine);
    Document longDoc = shortDoc.clone();
    Node rule = null;
    for (Node parent : parents) {
      Description parentDesc = parent.renderDescription();
      if (parentDesc.rule != null) {
        if (rule != null) {
          Errors.debug("FunctionNode of function " + label + " has more than 1 parent comming from blockNode!");
        }
        rule = parentDesc.rule;
        shortDoc.doc.addAll(parentDesc.shortDoc.doc);
      }
      parentDesc.longDoc.indent();
      longDoc.doc.addAll(parentDesc.longDoc.doc);
    }
    return new Description(shortDoc, longDoc, rule, intruderAction);
  }

  @Override public String toString(){
    return "Function(" + printLabel + ")";
  }
}
