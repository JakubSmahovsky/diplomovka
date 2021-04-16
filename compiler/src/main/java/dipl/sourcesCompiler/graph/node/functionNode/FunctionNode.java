package dipl.sourcesCompiler.graph.node.functionNode;

import dipl.Constants;
import dipl.dataStructures.outputTerm.OutputTerm;
import dipl.errors.Errors;
import dipl.sourcesCompiler.graph.node.Node;

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
        return Constants.IN_SENC;
      case (Constants.T_SDEC):
        return Constants.IN_SDEC;
      case (Constants.T_AENC):
        return Constants.IN_AENC;
      case (Constants.T_ADEC):
        return Constants.IN_ADEC;
      case (Constants.T_PK):
        return Constants.IN_PK;
      case (Constants.T_SIGN):
        return Constants.IN_SIGN;
      case (Constants.T_VERIFY):
        return Constants.IN_SIGNVERIF;
      case (Constants.T_HASH):
        return Constants.T_HASH;
      case (Constants.T_FIRST):
        return Constants.IN_FIRST;
      case (Constants.T_SECOND):
        return Constants.IN_SECOND;
      case (Constants.T_EXP_WORD):
        return Constants.T_EXP;
      case (Constants.T_INVERSE):
        return Constants.IN_INVERSE;
    }
    Errors.DebugUnexpectedValueType("function", function, " tamarinToVP in FunctionNode");
    return null;
  }
  
  @Override
  public String render(){
    return this.printLabel;
  }
}