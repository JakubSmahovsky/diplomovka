package simple_tamarin.dataStructures.outputTerm;

import simple_tamarin.Constants;

public class OutputExponentiation extends OutputTerm{
  private final OutputTerm base;
  private final OutputTerm exponent;

  public OutputExponentiation(OutputTerm base, OutputTerm exponent) {
    this.base = base;
    this.exponent = exponent;
  }
  
  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof OutputExponentiation) && 
           ((OutputExponentiation)term).base.unify(base) &&
           ((OutputExponentiation)term).exponent.unify(exponent);
  }
  @Override
  public String render() {
    return base.render() + Constants.EXP + exponent.render();
  }
}
