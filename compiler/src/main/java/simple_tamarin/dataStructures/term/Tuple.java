package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.List;

import simple_tamarin.dataStructures.Deconstruction;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;

public class Tuple extends Term{
  public ArrayList<Term> subterms;

  public Tuple(ArrayList<Term> subterms) {
    super();
    this.subterms = subterms;
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
    if (!(term instanceof Tuple) || subterms.size() != ((Tuple)term).subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).equals(((Tuple)term).subterms.get(i))) {
        return false;
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
    ArrayList<String> renderedSubterms = new ArrayList<>();
    for (Term subterm : subterms) {
      renderedSubterms.add(subterm.render());
    }
    return "<" + String.join(", ", renderedSubterms) + ">";
  }

  @Override public String render(StBlock block) {
    ArrayList<String> renderedSubterms = new ArrayList<>();
    for (Term subterm : subterms) {
      renderedSubterms.add(subterm.render(block));
    }
    return "<" + String.join(", ", renderedSubterms) + ">";
  }

  @Override public String render(Term substitution) {
    // substitute this entire Tuple
    return substitution.render();
  }

  @Override public String renderLemma() {
    ArrayList<String> renderedSubterms = new ArrayList<>();
    for (Term subterm : subterms) {
      renderedSubterms.add(subterm.renderLemma());
    }
    return "<" + String.join(", ", renderedSubterms) + ">";
  }

  @Override public void removeFresh() {
    for (Term term : subterms) {
      term.removeFresh();
    }
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public boolean unify(Term term) {
    if (!(term instanceof Tuple) || subterms.size() != ((Tuple)term).subterms.size() ) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).unify(((Tuple)term).subterms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override public List<Variable> freeVariables() {
    ArrayList<Variable> result = new ArrayList<>();
    for (Term subterm : subterms) {
      result.addAll(subterm.freeVariables());
    }
    return result;
  }

  @Override public boolean assign(Term right, StBlock block, Principal principal) {
    // spread Tuple to deconstruct a deconstruction Term
    if (right.isDeconstructionTerm()) {
      Deconstruction dec = new Deconstruction(right.encoded(), this);
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
    }

    // spread Tuple instead of making an Alias when assigning a Variable
    if (right instanceof Variable) {
      Deconstruction dec = new Deconstruction(right, this);
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
    }
    
    // assign
    Term canonical = right.toCanonical();
    if (!(canonical instanceof Tuple) || subterms.size() != ((Tuple)canonical).subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).assign(((Tuple)canonical).subterms.get(i), block, principal)) {
        return false;
      }
    }

    return true;
  }
}
