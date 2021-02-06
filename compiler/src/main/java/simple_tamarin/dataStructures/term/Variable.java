package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Alias;
import simple_tamarin.dataStructures.Deconstruction;
import simple_tamarin.dataStructures.Fact;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.errors.Errors;

public class Variable extends Term {
  private static int temporals = 0;
  protected boolean placeholder = false;

  public final Principal owner;
  public final String name;
  public final StBlock cratedBy; // null for long term variables

  public Term subterm;
  public VariableSort sort;

  /**
   * Create an unowned variable, this mean the variable should be static
   */
  public Variable(String name) {
    this.owner = null;
    this.name = name;
    this.cratedBy = null;

    this.subterm = null;
    this.sort = VariableSort.NOSORT;
  }

  /**
   * Create an owned variable, the owner is taken from the creating block
   */
  public Variable(String name, StBlock block) {
    this.owner = block.principal;
    this.name = name;
    this.cratedBy = block;

    this.subterm = null;
    this.sort = VariableSort.NOSORT;
  }

  /**
   * Create an unowned variable with a defined sort, variables with sort
   * FRESH should not be created this way, they sould be assigned sort FRESH
   * while rendering a rule that generates them.
   */
  public Variable(String name, VariableSort sort) {
    this.owner = null;
    this.name = name;
    this.cratedBy = null;

    this.subterm = null;
    this.sort = sort;
  }

  /**
   * Clone the variable for a new owner, e.g. when receiving it in a message.
   */
  public Variable(Variable variable, Principal newOwner) {
    this.owner = newOwner;
    this.name = variable.name;
    this.cratedBy = null;
    
    this.subterm = variable;
    this.sort = VariableSort.NOSORT;
  }

  /**
   * return a new Variable marked as placeholder, this is considered a partially 
   * defined variable, which should not be rendered or used as a normal variable
   * until it is properly defined.
   */
  public static Variable placeholder(String name) {
    Variable result = new Variable(name);
    result.placeholder = true;
    return result;
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

  @Override public void addFresh() {
    this.sort = VariableSort.FRESH;
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

  @Override public boolean unify(Term term) {
    return true;
  }

  @Override public List<Variable> freeVariables() {
    return Arrays.asList(this);    
  }

  @Override public Term encoded(){
    return subterm.encoded();
  }

  @Override public boolean assign(Term right, StBlock block, Principal principal) {
    // if this is properly defined (principal already knew it) assert equality
    if (!this.placeholder) {
      if (right instanceof Variable && name.equals(((Variable)right).name)) { // equality guaranteed by usage of the same name, no action fact is needed
        return true;
      }
      block.actions.add(Fact.equality(this, right));
      return this.equals(right);
    }

    // this is not properly defined, assign
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
    if (!block.state.contains(this)) {
      block.state.add(this);
    }
    principal.learn(this);
    return true;
  }
}
