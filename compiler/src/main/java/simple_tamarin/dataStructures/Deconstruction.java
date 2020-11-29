package simple_tamarin.dataStructures;

import java.util.List;

import simple_tamarin.dataStructures.term.Term;

public class Deconstruction {
  public Term term;
  public List<Term> substitutions;

  public Deconstruction(Term term, List<Term> substitutions) {
    this.term = term;
    this.substitutions = substitutions;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Deconstruction && term.equals(((Deconstruction)obj).term)) {
      return true;
    }
    return false;
  }
}
