package simple_tamarin.sourcesCompiler.term;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.term.Term;

/**
 * Function used purely by tamarin to extract the second value of a pair
 * (or last value of a Tuple).
 */
public class FunctionSecond extends OutputTerm{
  public Term subterm;

  public FunctionSecond(Term subterm) {
    this.subterm = subterm;
  }

  @Override
  public String render() {
    return BuilderFormatting.fact(Constants.FIRST, subterm, null);
  }
}
