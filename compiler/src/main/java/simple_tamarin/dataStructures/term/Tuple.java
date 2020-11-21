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

    if (!(obj instanceof Tuple)) {
      return false;
    }

    if (subterms.size() != ((Tuple)obj).subterms.size()) {
      return false;
    }

    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).equals(((Tuple)obj).subterms.get(i))) {
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
      result.addAll(subterms.get(i).unify(((Tuple)right).subterms.get(i)));
    }
    return result;
  }

  @Override public List<Term> extractKnowledge() {
    List<Term> result = new ArrayList<>();
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
}
