package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
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
  private static int temporals = 0;
  private final int id;
  private Term subterm = null; // invariant: only change using setSubterm
  private Term canonical = this; // invariant: only change using setSubterm
  private boolean placeholder = false; // invariant: only set to true in the placeholder() method

  private final String name;
  private VariableSort sort; // invariant: fresh a public sorts are added only when rendering the origination block
  
  public Variable(STModel model, String name) {
    this.id = model.registerVariable(this);

    this.name = name;
    this.sort = VariableSort.NOSORT;
  }

  /**
   * Private constructor for temporal only, we don't need to register temporals.
   */
  private Variable() {
    this.id = -1;

    this.name = Constants.TEMPORAL_NAME + (temporals++);
    this.sort = VariableSort.TEMPORAL;
  }

  /**
   * Clone this variable for a new owner when receiving it in a message.
   */
  public Variable clone(STModel model) {
    Variable result = new Variable(model, name);
    result.setSubterm(this);
    return result;
  }

  /**
   * return a new Variable marked as placeholder, this is considered a partially 
   * defined variable, which should not be rendered or used as a normal variable
   * until it is properly defined.
   */
  public static Variable placeholder(STModel model, String name) {
    Variable result = new Variable(model, name);
    result.placeholder = true;
    return result;
  }

  public static Variable nextTemporal(){
    return new Variable();
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
    // rename variables but not temporals
    String renderedName = sort == VariableSort.TEMPORAL ? name : Constants.VARIABLE_NAME + id;
    return Constants.sortString(sort) + renderedName;
  }

  @Override public String render(STBlock block) {
    for (Deconstruction dec : block.deconstructed) {
      if (dec.term.equals(this)) {
        return subterm.render(dec.substitution);
      }
    }
    return render();
  }

  @Override public String render(Term substitution) {
    return subterm.render(substitution);
  }

  public String renderOutput() {
    return name;
  }

  /**
   * Render an alias defined by this variable, simply as "this = subterm".
   */
  public String renderAlias() {
    return BuilderFormatting.alias(this.render(), subterm.render());
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

  public void addPublic() {
    if (sort != VariableSort.NOSORT && sort != VariableSort.PUBLIC) {
      Errors.DebugUnexpectedCall("addPublic", render());
    }
    this.sort = VariableSort.PUBLIC;
  }

  public void removePublic() {
    if (sort == VariableSort.PUBLIC) {
      sort = VariableSort.NOSORT;
    }
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
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
        block.aliases.add(this);
      }
    }
    if (!block.state.contains(this)) {
      block.state.add(this);
    }
    principal.learnEphemeralPrivate(this);
    return true;
  }

  /**
   * Assign for use in distributed command, much simpler than the assign for assignment.
   * Just assign the term to the subterm, make sure this is a placeholder before assigning.
   */
  public void distributedAssign(Term right) {
    if (!placeholder) {
      Errors.DebugUnexpectedCall("DistributedAssign", "placeholder " + render());
    }
    setSubterm(right);
  }

  /**
   * @return true if this has a defined subterm and false if it is a generated/declare variable with subterm == null
   */
  public boolean isConstructed() {
    return subterm != null;
  }

  private void setSubterm(Term subterm) {
    if (this.subterm != null) {
      Errors.DebugUnexpectedCall("setSubterm on a Variable with subterm", render());
    }
    this.subterm = subterm;
    this.canonical = subterm.getCanonical();
  }
}
