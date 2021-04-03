package simpleT.dataStructures.outputTerm;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionInverse extends OutputTerm{
  private final OutputTerm base;
  
  public OutputFunctionInverse(OutputTerm base) {
    this.base = base;
  }

  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputFunctionInverse) && 
           (base.match(((OutputFunctionInverse)term).base));
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.ST_INVERSE, base);
  }
}
