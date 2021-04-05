package simpleT.sourcesCompiler.graph.node.functionNode;

import simpleT.Constants;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.errors.Errors;
import simpleT.sourcesCompiler.graph.node.Node;

public abstract class FunctionNode extends Node{
  protected final OutputTerm term;
  protected final String printLabel;
  public FunctionNode(String id, String label, OutputTerm term) {
    super(id, label);
    this.term = term;
    this.printLabel = tamarinToVP(label);
  }

  public static String tamarinToVP(String function) {
    switch (function) {
      case (Constants.T_SENC):
        return Constants.ST_SENC;
      case (Constants.T_SDEC):
        return Constants.ST_SDEC;
      case (Constants.T_AENC):
        return Constants.ST_AENC;
      case (Constants.T_ADEC):
        return Constants.ST_ADEC;
      case (Constants.T_PK):
        return Constants.ST_PK;
      case (Constants.T_SIGN):
        return Constants.ST_SIGN;
      case (Constants.T_VERIFY):
        return Constants.ST_SIGNVERIF;
      case (Constants.T_HASH):
        return Constants.T_HASH;
      case (Constants.T_FIRST):
        return Constants.ST_FIRST;
      case (Constants.T_SECOND):
        return Constants.ST_SECOND;
      case (Constants.T_EXP_WORD):
        return Constants.T_EXP;
      case (Constants.T_INVERSE):
        return Constants.ST_INVERSE;
    }
    Errors.DebugUnexpectedValueType("function", function, " tamarinToVP in FunctionNode");
    return null;
  }
  
  @Override
  public String render(){
    return this.printLabel;
  }
}