package simpleT.dataStructures.outputTerm;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionHash extends OutputTerm{
  private final OutputTerm subterm;

  public OutputFunctionHash(OutputTerm subterm) {
    this.subterm = subterm;
  }

  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof OutputFunctionHash) && subterm.unify(((OutputFunctionHash)term).subterm);
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.ST_HASH, subterm);
  }
}
