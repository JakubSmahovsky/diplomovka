package simpleT.dataStructures.outputTerm;

import simpleT.Constants;
import simpleT.OutputFormatting;

/**
 * Function used purely by tamarin to extract the second value of a pair
 * (or last value of a Tuple).
 */
public class FunctionSecond extends OutputTerm{
  private final OutputTerm subterm;

  public FunctionSecond(OutputTerm subterm) {
    this.subterm = subterm;
  }

  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof FunctionSecond && subterm.unify(((FunctionSecond)term).subterm));
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.SECOND, subterm);
  }
}