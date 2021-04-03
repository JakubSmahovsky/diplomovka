package simpleT.dataStructures.outputTerm;

import simpleT.Constants;
import simpleT.OutputFormatting;

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
  public boolean match(OutputTerm term) {
    return (term instanceof FunctionFirst && subterm.match(((FunctionFirst)term).subterm));
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.T_FIRST, subterm);
  }
}
