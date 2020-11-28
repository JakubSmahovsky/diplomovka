package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tuple extends Term{
  public ArrayList<Term> subterms;

  public Tuple(ArrayList<Term> subterms) {
    this.subterms = subterms;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).deconstructTerm();
    if (!(term instanceof Tuple)) {
      return false;
    }
    if (subterms.size() != ((Tuple)term).subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).equals(((Tuple)term).subterms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override public Term deconstructTerm() {
    return this;
  }

  @Override public List<Variable> unify (Term right) {
    if (!(right instanceof Tuple) || subterms.size() != ((Tuple)right).subterms.size()) {
      return null;
    }
    ArrayList<Variable> result = new ArrayList<>();
    for (int i = 0; i < subterms.size(); i++) {
      List<Variable> subresult = subterms.get(i).unify(((Tuple)right).subterms.get(i));
      if (subresult == null) {
        return null;
      }
      result.addAll(subresult);
    }
    return result;
  }

  @Override public List<Variable> extractKnowledge() {
    List<Variable> result = new ArrayList<>();
    for (Term term : subterms) {
      result.addAll(term.extractKnowledge());
    }
    return result;
  }

  @Override public String render() {
    return render(false);
  }

  @Override public String renderLemma() {
    return render(true);
  }

  private String render(boolean asLemma) {
    StringBuilder result = new StringBuilder();
    result.append('<');

    Iterator<? extends Term> it = subterms.iterator();
    while (it.hasNext()) {
      String subString = asLemma ? it.next().renderLemma() : it.next().render(); 
      result.append(subString);
      if (it.hasNext()) {
        result.append(", ");
      }
    }
  
    result.append('>');
    return result.toString();
  }

  @Override public void removeFresh() {
    for (Term term : subterms) {
      term.removeFresh();
    }
  }

  @Override public boolean canBeLearnt() {
    for (Term term : subterms) {
      if (!term.canBeLearnt()) {
        return false;
      }
    }
    return true;
  }
}
