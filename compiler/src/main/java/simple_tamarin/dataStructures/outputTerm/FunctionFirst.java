package simple_tamarin.dataStructures.outputTerm;

import simple_tamarin.Constants;
import simple_tamarin.OutputFormatting;

/**
 * Function used purely by tamarin to extract the first value of a pair 
 * (or all except last value of a Tuple).
 */
public class FunctionFirst extends OutputTerm {
  private final OutputTerm subterm;

  public FunctionFirst(OutputTerm subterm) {
    this.subterm = subterm;
  }

  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof FunctionFirst && subterm.unify(((FunctionFirst)term).subterm));
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.FIRST, subterm);
  }
}
