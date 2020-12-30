package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Alias;
import simple_tamarin.dataStructures.Deconstruction;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.errors.Errors;

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

  public static Variable nextTemporal(){
    String tName = Constants.TEMPORAL_NAME + temporals;
    temporals ++;
    return new Variable(tName, VariableSort.TEMPORAL);
  }

  @Override public Term toCanonical() {
    if (subterm != null) {
      return subterm.toCanonical();
    }
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
    Term thisTerm = this.toCanonical();
    if (thisTerm instanceof Variable) {
      return thisTerm == term;
    } else {
      return thisTerm.equals(term);
    }
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
        return this.toCanonical().render(dec.substitution);
      }
    }
    return render();
  }

  @Override public String render(Term substitution) {
    if (this.toCanonical() == this) {
      Errors.DebugUnexpectedCall("render(substitution)", render());
    }
    return this.toCanonical().render(substitution);
  }

  @Override public String renderLemma() {
    return sort == VariableSort.TEMPORAL ? render() : name;
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

  @Override public List<Variable> freeVariables() {
    return Arrays.asList(this);    
  }

  @Override public Term encoded(){
    return subterm.encoded();
  }

  @Override public boolean unify(Term right, StBlock block, Principal principal) {
    if (this.equals(right)) {
      return true;
    }
    this.subterm = right.toCanonical();
    if (right.isDeconstructionTerm()) {
      Deconstruction dec = new Deconstruction(right.encoded(), this);
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
    } else {
      // add alias if it wouldn't create an alias like "x = x"
      if (!(right instanceof Variable) || !((Variable)right).name.equals(this.name)) {
        block.aliases.add(new Alias(this, right));
      }
    }
    principal.learn(this);
    return true;
  }
}
