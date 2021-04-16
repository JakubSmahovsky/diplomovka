package dipl.dataStructures.outputTerm;

import dipl.Constants;
import dipl.OutputFormatting;

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
  public boolean match(OutputTerm term) {
    return (term instanceof FunctionSecond && subterm.match(((FunctionSecond)term).subterm));
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.IN_SECOND, subterm);
  }
}
