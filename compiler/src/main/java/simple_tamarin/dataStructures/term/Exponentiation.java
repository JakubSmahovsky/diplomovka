package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StBlock;

public class Exponentiation extends Term {
  public Term base;
  public ArrayList<Term> exponent; // exponent is kept sorted (for canonical form)

  /**
   * An exponenetiation object with a simple exponent, other exponents may be added using addExponenet(Term exponent)
   */
  public Exponentiation(Term base, Term exponent) {
    super();
    this.base = base;
    this.exponent = new ArrayList<>();
    this.exponent.add(exponent);
  }

  public void addExponent(Term exponent) {
    this.exponent.add(exponent);
    Collections.sort(this.exponent);
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
    if (!(term instanceof Exponentiation) ||
        !base.equals(((Exponentiation)term).base) || 
        exponent.size() != ((Exponentiation)term).exponent.size()) {
      return false;
    }
    for (int i = 0; i < exponent.size(); i++) {
      if (!exponent.get(i).equals(((Exponentiation)term).exponent.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override public String render() {
    ArrayList<Term> subterms = new ArrayList<>();
    subterms.add(base);
    subterms.addAll(exponent);
    ArrayList<String> substrings = new ArrayList<>();    
    for (Term subterm : subterms) {
      String substring = subterm.render();
      if (subterm instanceof Exponentiation) {
        substring = '(' + substring + ')';
      }
      substrings.add(substring);
    }
    return String.join(Constants.EXP, substrings);
  }

  @Override public String render(StBlock block) {
    ArrayList<Term> subterms = new ArrayList<>();
    subterms.add(base);
    subterms.addAll(exponent);
    ArrayList<String> substrings = new ArrayList<>();    
    for (Term subterm : subterms) {
      String substring = subterm.render(block);
      if (subterm instanceof Exponentiation) {
        substring = '(' + substring + ')';
      }
      substrings.add(substring);
    }
    return String.join(Constants.EXP, substrings);
  }

  @Override public String renderLemma() {
    ArrayList<Term> subterms = new ArrayList<>();
    subterms.add(base);
    subterms.addAll(exponent);
    ArrayList<String> substrings = new ArrayList<>();    
    for (Term subterm : subterms) {
      String substring = subterm.renderLemma();
      if (subterm instanceof Exponentiation) {
        substring = '(' + substring + ')';
      }
      substrings.add(substring);
    }
    return String.join(Constants.EXP, substrings);
  }

  @Override public void removeFresh() {
    base.removeFresh();
    for (Term subterm : exponent) {
      subterm.removeFresh();
    }
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public boolean unify(Term term) {
    if (!(term instanceof Exponentiation) || !base.unify(((Exponentiation)term).base) || exponent.size() != ((Exponentiation)term).exponent.size()) {
      return false;
    }
    for (int i = 0; i < exponent.size(); i++) {
      if (!exponent.get(i).unify(((Exponentiation)term).exponent.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override public List<Variable> freeVariables(){
    return new ArrayList<>();
  }
}
