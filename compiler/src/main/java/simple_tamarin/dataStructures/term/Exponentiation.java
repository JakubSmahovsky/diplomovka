package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;

public class Exponentiation extends Term {
  private final Exponentiation canoncical;
  private final Term base;
  private final ArrayList<Term> exponent; // invariant: never add to or remove form other than in the constructor

  public Exponentiation(Term base, ArrayList<Term> exponent) {
    this.base = base;
    this.exponent = exponent;
    this.canoncical = new Exponentiation(this);
  }

  /**
   * Canonical form constructor
   */
  private Exponentiation(Exponentiation original) {
    this.canoncical = this;
    this.exponent = new ArrayList<>();
    // if the base is already an exponentiation we need to add all exponents together and the base is it's base
    if (original.base.getCanonical() instanceof Exponentiation) {
      Exponentiation canonicalBase = (Exponentiation)original.base.getCanonical(); 
      this.exponent.addAll(canonicalBase.exponent);
      this.base = canonicalBase.base;
    } else {
      this.base = original.base.getCanonical();
    }
    for (Term e : original.exponent) {
      this.exponent.add(e.getCanonical());
    }
    Collections.sort(this.exponent);
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.Exponentiation;
  }

  @Override public int canonicalCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be Exponentiation, compare based on subterms
    Exponentiation exponentiation = (Exponentiation)term;
    for (int i = 0; i < Math.min(this.exponent.size(), exponentiation.exponent.size()); i++) {
      result = this.exponent.get(i).canonicalCompareTo(exponentiation.exponent.get(i));
      if (result != 0) {
        return result;
      }
    }
    // one is a prefix of the other, the shorter one is smaller (or they might be equal)
    return Integer.compare(this.exponent.size(), exponentiation.exponent.size());
  }

  @Override public Term getCanonical() {
    return canoncical;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).getCanonical();
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

  @Override public String render(STBlock block) {
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

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public List<Variable> freeVariables(){
    return new ArrayList<>();
  }
}
