package simple_tamarin.sourcesCompiler.term;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.term.Term;

/**
 * Function used purely by tamarin to extract the first value of a pair 
 * (or all except last value of a Tuple).
 */
public class FunctionFirst extends OutputTerm{
  public Term subterm;

  public FunctionFirst(Term subterm) {
    this.subterm = subterm;
  }

  @Override public boolean unify(Term term) {
    return (term instanceof FunctionSecond && subterm.unify(((FunctionSecond)term).subterm));
  }

  @Override public String render() {
    return BuilderFormatting.fact(Constants.FIRST, subterm, null);
  }
}
