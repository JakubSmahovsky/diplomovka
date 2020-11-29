package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Alias;
import simple_tamarin.dataStructures.Deconstruction;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;

public class Variable extends Term {
  private static int temporals = 0;
  public String name;
  public Term subterm;
  public Principal cratedBy; // null for long term variables
  public VariableSort sort;

  public Variable(String name) {
    this.name = name;
    this.subterm = null;
    this.cratedBy = null;
    this.sort = VariableSort.NOSORT;
  }

  public Variable(String name, VariableSort sort) {
    this.name = name;
    this.subterm = null;
    this.cratedBy = null;
    this.sort = sort;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).deconstructTerm();
    Term thisTerm = this.deconstructTerm();
    if (thisTerm instanceof Variable) {
      return thisTerm == term;
    } else {
      return thisTerm.equals(term);
    }
  }

  public static Variable nextTemporal(){
    String tName = Constants.TEMPORAL + temporals;
    temporals ++;
    return new Variable(tName, VariableSort.TEMPORAL);
  }

  @Override public Term deconstructTerm() {
    if (subterm != null) {
      return subterm.deconstructTerm();
    }
    return this;
  }

  @Override public Term encoded(){
    return subterm.encoded();
  }

  @Override public boolean unify(Term right, StBlock block, Principal principal) {
    if (this.equals(right)) {
      return true;
    }
    if (right.isDeconstructionTerm()) {
      Deconstruction dec = new Deconstruction(right.encoded(), Arrays.asList(this));
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
      this.subterm = right.deconstructTerm();
    } else {
      Term deconstructed = right.deconstructTerm();
      if (this != deconstructed) {
        this.subterm = deconstructed;
      }
      // add alias if it wouldn't create an alias like "x = x"
      if (!(right instanceof Variable) || !((Variable)right).name.equals(this.name)) {
        block.aliases.add(new Alias(this, right));
      }
    }
    principal.learn(this);
    return true;
  }

  @Override public List<Variable> extractKnowledge() {
    return Arrays.asList(this);
  }

  @Override public String render(){
    return Constants.sortString(sort) + name;
  }

  @Override public String render(StBlock block) {
    for (Deconstruction dec : block.deconstructed) {
      if (dec.term.equals(this)) {
        return this.deconstructTerm().render(dec.substitutions);
      }
    }
    return render();
  }

  @Override public String render(List<Term> substitutions) {
    return render();
  }

  @Override public String renderLemma() {
    return name;
  }

  @Override public void removeFresh() {
    if (subterm != null) {
      subterm.removeFresh();
    }
    if (sort == VariableSort.FRESH) {
      sort = VariableSort.NOSORT;
    }
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }
}
