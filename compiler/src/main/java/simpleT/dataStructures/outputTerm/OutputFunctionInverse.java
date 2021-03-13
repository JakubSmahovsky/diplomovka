package simpleT.dataStructures.outputTerm;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionInverse extends OutputTerm{
  private final OutputTerm base;
  
  public OutputFunctionInverse(OutputTerm base) {
    this.base = base;
  }

  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof OutputFunctionInverse) && 
           (base.unify(((OutputFunctionInverse)term).base));
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.VPINVERSE, base);
  }
}
