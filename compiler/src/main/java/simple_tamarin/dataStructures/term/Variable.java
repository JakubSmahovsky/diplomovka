package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Alias;
import simple_tamarin.dataStructures.Deconstruction;
import simple_tamarin.dataStructures.Fact;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.dataStructures.STModel;
import simple_tamarin.errors.Errors;

/**
 * A Class representing a Simple Tamarin variable. In the thesis a variable
 * is fully defined by a name and an owner. In code it's fully defined by a java
 * object, so the owner is unnecessary (when looking for, say, Alice's Na we only
 * search among Alice's private varibales)
 */
public class Variable extends Term {
  private static int variables = 0;
  private static int temporals = 0;
  private final int id;
  private boolean placeholder = false; // invariant: only set to true in a constructor
  private final String name;
  private final STBlock cratedBy; // null for long term variables
  private Term subterm = null; // invariant: only change using setSubterm
  private Term canonical = this; // invariant: only change using setSubterm
  private VariableSort sort;

  /**
   * Create an unowned variable, this mean the variable should be static
   */
  public Variable(String name) {
    this.id = nextId();
    this.name = name;
    this.cratedBy = null;
    this.sort = VariableSort.NOSORT;
    this.canonical = this;
  }

  /**
   * Create an owned variable, the owner is taken from the creating block
   */
  public Variable(String name, STBlock block) {
    this.id = nextId();
    this.name = name;
    this.cratedBy = block;
    this.sort = VariableSort.NOSORT;
    this.canonical = this;
  }

  /**
   * Create an unowned variable with a defined sort, variables with sort
   * FRESH should not be created this way, they sould be assigned sort FRESH
   * while rendering a rule that generates them.
   */
  public Variable(String name, VariableSort sort) {
    this.id = nextId();
    this.name = name;
    this.cratedBy = null;
    this.sort = sort;
    this.canonical = this;
  }

  /**
   * Clone this variable for a new owner, e.g. when receiving it in a message.
   */
  public Variable clone() {
    Variable result = new Variable(name);
    result.setSubterm(this);
    return result;
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

  private static int nextId() {
    return variables++;
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.Variable;
  }

  @Override public int canonicalCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be Variables, compare based on id
    return Integer.compare(this.id, ((Variable)term).id);
  }

  @Override public Term getCanonical() {
    return canonical;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).getCanonical();
    Term thisTerm = this.getCanonical();
    if (thisTerm instanceof Variable) {
      return thisTerm == term;
    } else {
      return thisTerm.equals(term);
    }
  }

  public boolean equalsByName(String name){
    return this.name.equals(name);
  }

  public boolean equalsByName(Variable variable){
    return this.name.equals(variable.name);
  }

  @Override public List<Variable> extractKnowledge() {
    return Arrays.asList(this);
  }

  @Override public String render(){
    return Constants.sortString(sort) + name;
  }

  @Override public String render(STBlock block) {
    for (Deconstruction dec : block.deconstructed) {
      if (dec.term.equals(this)) {
        return this.getCanonical().render(dec.substitution);
      }
    }
    return render();
  }

  @Override public String render(Term substitution) {
    if (this.getCanonical() == this) {
      Errors.DebugUnexpectedCall("render(substitution)", render());
    }
    return this.getCanonical().render(substitution);
  }

  @Override public String renderLemma() {
    return sort == VariableSort.TEMPORAL ? render() : name;
  }

  public void addFresh() {
    if (sort != VariableSort.NOSORT && sort != VariableSort.FRESH) {
      Errors.DebugUnexpectedCall("addFresh", render());
    }
    this.sort = VariableSort.FRESH;
  }

  public void removeFresh() {
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

  @Override public boolean assign(Term right, STBlock block, Principal principal) {
    // if this is properly defined (principal already knew it) assert equality
    if (!this.placeholder) {
      if (right instanceof Variable && name.equals(((Variable)right).name)) { // equality guaranteed by usage of the same name, no action fact is needed
        return true;
      }
      block.actions.add(Fact.equality(this, right));
      return this.equals(right);
    }

    // this is not properly defined, assign
    this.setSubterm(right);
    this.placeholder = false;
    if (right.isDeconstructionTerm()) {
      Deconstruction dec = new Deconstruction(right.getEncodedValue(), this);
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

  public boolean isPublicInModel(STModel model) {
    return model.findVariable(name) != null;
  }

  public boolean isPublic() {
    return sort == VariableSort.PUBLIC;
  }

  public boolean isLongTerm(){
    return cratedBy == null;
  }

  private void setSubterm(Term subterm) {
    if (this.subterm != null) {
      Errors.DebugUnexpectedCall("setSubterm on a Variable with subterm", render());
    }
    this.subterm = subterm;
    this.canonical = subterm.getCanonical();
  }
}
