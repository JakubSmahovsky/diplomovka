package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StBlock;

public class Multiplication extends Term {
  // subterms are kept sorted, therefore multiplication is stored in canonical form
  public ArrayList<Term> subterms;

  public Multiplication(ArrayList<Term> subterms) {
    super();
    this.subterms = subterms;
    Collections.sort(subterms);
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
    if (!(term instanceof Multiplication) ||  subterms.size() != ((Multiplication)term).subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).equals(((Multiplication)term).subterms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override public String render() {
    ArrayList<String> substrings = new ArrayList<>();
    for (Term subterm : subterms) {
      substrings.add(subterm.render());
    }
    return String.join(Constants.MULT, substrings);
  }

  @Override public String render(StBlock block) {
    ArrayList<String> substrings = new ArrayList<>();
    for (Term subterm : subterms) {
      substrings.add(subterm.render(block));
    }
    return String.join(Constants.MULT, substrings);
  }

  @Override public String renderLemma() {  
    ArrayList<String> substrings = new ArrayList<>();
    for (Term subterm : subterms) {
      substrings.add(subterm.renderLemma());
    }
    return String.join(Constants.MULT, substrings);
  }

  @Override public void removeFresh() {
    for (Term subterm : subterms) {
      subterm.removeFresh();
    }
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public boolean unify(Term term) {
    if (!(term instanceof Multiplication) ||  subterms.size() != ((Multiplication)term).subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).unify(((Multiplication)term).subterms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override public List<Variable> freeVariables(){
    return new ArrayList<>();
  }
}
