package simpleT.dataStructures.term;

import java.util.ArrayList;
import java.util.List;

import simpleT.Constants;
import simpleT.dataStructures.Deconstruction;
import simpleT.dataStructures.Principal;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.STModel;
import simpleT.stParser.SimpleTParser.TermContext;

public class Tuple extends Term{
  private final ArrayList<Term> subterms; // invariant: never add to or remove form
  private final Tuple normalForm;

  public Tuple(ArrayList<Term> subterms) {
    this.subterms = subterms;
    this.normalForm = new Tuple(this);
  }

  /**
   * normal form constructor
   */
  private Tuple(Tuple original) {
    this.normalForm = this;
    this.subterms = new ArrayList<>();
    for (Term subterm : original.subterms) {
      this.subterms.add(subterm.getNormalForm());
    }
  }

  @Override public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.Tuple;
  }

  @Override public int normalFormCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be Tuples, compare based on subterms
    Tuple tuple = (Tuple)term;
    for (int i = 0; i < Math.min(this.subterms.size(), tuple.subterms.size()); i++) {
      result = this.subterms.get(i).normalFormCompareTo(tuple.subterms.get(i));
      if (result != 0) {
        return result;
      }
    }
    // one is a prefix of the other, the shorter one is smaller
    return Integer.compare(this.subterms.size(), tuple.subterms.size());
  }

  @Override public Term getNormalForm() {
    return normalForm;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).getNormalForm();
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
    return Constants.T_TUPLE_OPEN + String.join(Constants.COMMA_SEPARATOR, renderedSubterms) + Constants.T_TUPLE_CLOSE;
  }

  @Override public String render(STBlock block) {
    for (Deconstruction dec : block.deconstructed) {
      if (dec.substituted.equals(this)) {
        return dec.substitution.render();
      }
    }

    ArrayList<String> renderedSubterms = new ArrayList<>();
    for (Term subterm : subterms) {
      renderedSubterms.add(subterm.render(block));
    }
    return Constants.T_TUPLE_OPEN + String.join(Constants.COMMA_SEPARATOR, renderedSubterms) + Constants.T_TUPLE_CLOSE;
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
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
   * it for the value that was deconstructed (for the encrypted one, not the result).
   * Otherwise a tuple is being assigned a Variable or another Tuple in which case we
   * want to substitute this Tuple for the entire Term on the right.
   * Afterwards we want to recursively assign subterms.
   */
  @Override public boolean assign(Term right, boolean rightIndirection, STBlock block) {
    // spread
    if (right.isDeconstructionTerm()) {
      Deconstruction dec = right.createDeconstruction(this);
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
    Term rightTuple = right;
    if (!(rightTuple instanceof Tuple)) {
      rightTuple = right.getNormalForm();
      rightIndirection = true;
    }
    if (!(rightTuple instanceof Tuple) || subterms.size() != ((Tuple)rightTuple).subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).assign(((Tuple)rightTuple).subterms.get(i), rightIndirection, block)) {
        return false;
      }
    }

    return true;
  }

  @Override
  public Term sentToReceived(STModel model, Principal recipient, TermContext messageCtx) {
    ArrayList<Term> clonedSubterms = new ArrayList<>();
    for (Term subterm : subterms) {
      clonedSubterms.add(subterm.sentToReceived(model, recipient, messageCtx));
    }
    return new Tuple(clonedSubterms);
  }
}
