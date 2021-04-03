package simpleT.dataStructures.outputTerm;

import simpleT.Constants;

public class OutputExponentiation extends OutputTerm{
  private final OutputTerm base;
  private final OutputTerm exponent;

  public OutputExponentiation(OutputTerm base, OutputTerm exponent) {
    this.base = base;
    this.exponent = exponent;
  }
  
  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputExponentiation) && 
           (base.match(((OutputExponentiation)term).base)) &&
           (exponent.match(((OutputExponentiation)term).exponent));
  }
  @Override
  public String render() {
    return base.render() + Constants.T_EXP + exponent.render();
  }
}
