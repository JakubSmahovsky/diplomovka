package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.List;

import simple_tamarin.dataStructures.Deconstruction;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.STBlock;

public class Tuple extends Term{
  public ArrayList<Term> subterms;

  public Tuple(ArrayList<Term> subterms) {
    this.subterms = subterms;
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.Tuple;
  }

  @Override public int canonicalCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be Tuples, compare based on subterms
    Tuple tuple = (Tuple)term;
    for (int i = 0; i < Math.min(this.subterms.size(), tuple.subterms.size()); i++) {
      result = this.subterms.get(i).canonicalCompareTo(tuple.subterms.get(i));
      if (result != 0) {
        return result;
      }
    }
    // one is a prefix of the other, the shorter one is smaller
    return Integer.compare(this.subterms.size(), tuple.subterms.size());
  }

  @Override public Term toCanonical() {
    ArrayList<Term> canonicalSubterms = new ArrayList<>();
    for (Term subterm : subterms) {
      canonicalSubterms.add(subterm.toCanonical());
    }
    return new Tuple(canonicalSubterms);
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

  @Override public String render(STBlock block) {
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

  /**
   * When assigning a value to a tuple we avoid using aliases. Instead we "spread" the tuple
   * in place of the term that's being assigned to it.
   * A Tuple may be assigned the result of a deconstructon Term in which case we substitute
   * it for the value that was deconstructed (for the encoded one, not the result).
   * Otherwise a tuple is being assigned a Variable or another Tuple in which case we
   * want to substitute this Tuple for the entire Term on the right.
   * Afterwards we want to recursively assign subterms.
   */
  @Override public boolean assign(Term right, STBlock block, Principal principal) {
    // spread
    if (right.getEncodedValue() != null) {
      Deconstruction dec = new Deconstruction(right.getEncodedValue(), this);
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
    } else {
      Deconstruction dec = new Deconstruction(right, this);
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
    }
    
    // recursively assign subterms
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
