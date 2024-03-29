package dipl.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import dipl.BuilderFormatting;
import dipl.Constants;
import dipl.Constants.VariableSort;
import dipl.dataStructures.Deconstruction;
import dipl.dataStructures.Fact;
import dipl.dataStructures.Principal;
import dipl.dataStructures.Block;
import dipl.dataStructures.Model;
import dipl.errors.Errors;
import dipl.inputParser.InputParser.TermContext;

public class Variable extends Term {
  private static int temporals = 0;
  private final int id;
  private Term subterm = null; // invariant: only change using setSubterm
  private Term normalForm = this; // invariant: only change using setSubterm
  private boolean placeholder = false; // invariant: only set to true in the placeholder() method

  private final String name;
  private VariableSort sort; // invariant: fresh a public sorts are added only when rendering the origination block
  
  public Variable(Model model, String name) {
    this.id = model.registerVariable(this);

    this.name = name;
    this.sort = VariableSort.NOSORT;
  }

  /**
   * Private constructor for temporal only, we don't need to register temporals.
   */
  private Variable() {
    this.id = -1;

    this.name = Constants.PREFIX_TEMPORALID + (temporals++);
    this.sort = VariableSort.TEMPORAL;
  }

  @Override
  public Term sentToReceived(Model model, Principal recipient, TermContext messageCtx) {
    // if the recipient knows a variable with the same name
    // compare equality to this and the recipient receives the known variable (makes implicit equality assertion in Tamarin)
    Variable alreadyKnown = recipient.knowsAnyVariableByName(this);
    if (alreadyKnown != null) {
      if (!this.equals(alreadyKnown)) {
        Errors.ErrorReceivedNotEqual(messageCtx.start, name);
      }
      return alreadyKnown;
    }
    // otherwise clone this variable
    Variable result = new Variable(model, name);
    result.setSubterm(this);
    return result;
  }

  /**
   * return a new Variable marked as placeholder, this is considered a partially 
   * defined variable, which should not be rendered or used as a normal variable
   * until it is properly defined.
   */
  public static Variable placeholder(Model model, String name) {
    Variable result = new Variable(model, name);
    result.placeholder = true;
    return result;
  }

  public static Variable nextTemporal(){
    return new Variable();
  }

  @Override
  public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.Variable;
  }

  @Override
  public int normalFormCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be Variables, compare based on id
    return Integer.compare(this.id, ((Variable)term).id);
  }

  @Override
  public Term getNormalForm() {
    return normalForm;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).getNormalForm();
    Term thisTerm = this.getNormalForm();
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

  @Override
  public List<Variable> extractKnowledge() {
    return Arrays.asList(this);
  }

  @Override
  public String render(){
    // rename variables but not temporals
    String renderedName = sort == VariableSort.TEMPORAL ? name : Constants.PREFIX_VARIABLEID + id;
    return Constants.sortString(sort) + renderedName;
  }

  @Override
  public String render(Block block) {
    for (Deconstruction dec : block.deconstructed) {
      if (dec.substituted.equals(this)) {
        return dec.substitution.render();
      }
    }
    return render();
  }

  public String renderOutput() {
    return name;
  }

  /**
   * Render an alias defined by this variable, simply as "this = subterm".
   */
  public String renderAlias(Block block) {
    return BuilderFormatting.alias(this.render(), block == null ? subterm.render() : subterm.render(block));
  }

  /**
   * Render as a (part of a) fact label for use in principal state facts and rule names.
   */
  public String renderFactName() {
    return Constants.FACT_PREFIX_PRINCIPALID + id;
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

  @Override
  public boolean isDeconstructionTerm() {
    return false;
  }

  @Override
  public List<Variable> freeVariables() {
    return Arrays.asList(this);    
  }

  @Override
  public boolean assign(Term right, boolean rightIndirection, Block block) {
    // if this is properly defined (principal already knew it) assert equality
    if (!this.placeholder) {
      block.unaryEqualsPending.add(this);
      if (!rightIndirection) {
        block.principal.model.builtins.restriction_eq = true;
        block.actions.add(Fact.equality(this, right));
      }
      return this.equals(right);
    }

    // this is not properly defined, assign
    this.setSubterm(right);
    this.placeholder = false;
    if (right.isDeconstructionTerm()) {
      Deconstruction dec = right.createDeconstruction(this);
      if (!block.deconstructed.contains(dec)) {
        block.deconstructed.add(dec);
      }
    } else {
      // add alias if it wouldn't create an alias like "x = x"
      if (!(right instanceof Variable) || !((Variable)right).name.equals(this.name)) {
        block.aliases.add(this);
      }
    }
    block.addToState(this);
    block.principal.learnEphemeralPrivate(this, false);
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
    this.normalForm = subterm.getNormalForm();
  }
}
