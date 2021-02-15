package simple_tamarin.dataStructures.term;

import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StBlock;

/**
 * An unary hash function, should be created with a tuple
 * to simulate an n-ary hash function.
 */
public class FunctionHash extends Term {
  public Term subterm;

  public FunctionHash(Term subterm) {
    this.subterm = subterm;
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.FunctionHash;
  }

  @Override public int canonicalCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionHash, compare based on subterms
    FunctionHash functionHash = (FunctionHash)term;
    return subterm.canonicalCompareTo(functionHash.subterm);
  }

  @Override public Term toCanonical() {
    return this;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).toCanonical();
    if (!(term instanceof FunctionHash)) {
      return false;
    }
    return subterm.equals(((FunctionHash)term).subterm);
  }

  @Override public String render() {
    return BuilderFormatting.fact(Constants.HASH, subterm, null);
  }

  @Override public String render(StBlock block) {
    return BuilderFormatting.fact(Constants.HASH, subterm, block);
  }

  @Override public String renderLemma() {
    return BuilderFormatting.lemmaFact(Constants.HASH, subterm);
  }

  @Override public void removeFresh() {
    subterm.removeFresh();
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public boolean unify(Term term) {
    return ((term instanceof FunctionHash) && subterm.unify(((FunctionHash)term).subterm));
  }

  @Override public List<Variable> freeVariables() {
    return subterm.freeVariables();
  }
}
