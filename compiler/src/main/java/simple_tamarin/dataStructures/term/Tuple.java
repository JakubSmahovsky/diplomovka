package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import simple_tamarin.dataStructures.Deconstruction;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;

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

  @Override public boolean unify (Term right, StBlock block, Principal principal) {
    Term deconstructed = right.deconstructTerm();
    if (right.isDeconstructionTerm()) {
      Deconstruction dec = new Deconstruction(right.encoded(), Arrays.asList(this));
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
      deconstructed = right.deconstructTerm();
    }
    if (!(deconstructed instanceof Tuple) || subterms.size() != ((Tuple)deconstructed).subterms.size()) {
      return false;
    }

    // unify subterms
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).unify(((Tuple)deconstructed).subterms.get(i), block, principal)) {
        return false;
      }
    }

    /**
     * Unifying tuple with non-tuple
     * We want to spread this tuple instead of using the rendered value of deconstructed
     */
    if (!(right instanceof Tuple)) {
      ArrayList<Term> substitutions = new ArrayList<>();
      for (Term subterm : subterms) {
        substitutions.add(subterm);
      }
      Deconstruction dec = new Deconstruction(deconstructed, substitutions);
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
    }
    return true;
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

  @Override public String render(StBlock block) {
    return render();
  }

  @Override public String render(List<Term> substitutions) {
    return (new Tuple(new ArrayList<>(substitutions))).render();
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

  @Override public boolean isDeconstructionTerm() {
    return false;
  }
}
