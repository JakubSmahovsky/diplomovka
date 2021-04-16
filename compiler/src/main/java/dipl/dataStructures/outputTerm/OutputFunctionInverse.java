package dipl.dataStructures.outputTerm;

import dipl.Constants;
import dipl.OutputFormatting;

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
    return OutputFormatting.term(Constants.IN_INVERSE, base);
  }
}
