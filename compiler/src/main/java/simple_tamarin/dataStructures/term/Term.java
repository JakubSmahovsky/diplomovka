package simple_tamarin.dataStructures.term;

import java.util.List;

public abstract class Term{
  public abstract Term deconstructTerm();
  /**
   * Function for assigning variables from left side of an assignemt
   * their respective values from the right side of the assignment.
   * The left term (this) is populated with correct variable. 
   * @return list of all reassigned variables or null in case of error
   * TODO: throw runtimeException in case of errors
   */
  public abstract List<Variable> unify(Term right);
  
  /**
   * Extracts variables (syntactically) from Terms that can be learnt
   * TODO: throw runtimeException in Terms that cannot be learnt
   */
  public abstract List<Variable> extractKnowledge();

  /**
   * Render Term to text in Tamarin syntax
   */
  public abstract String render();

  /**
   * Render Term for use in a lemma, removing all sorts from Variables
   */
  public abstract String renderLemma();

  /**
   * Removed Fresh tag (~) from variables in Term
   */
  public abstract void removeFresh();

  /**
   * Terms can be learnt if they are Variables
   * or if they can be deconstructed and constructed again without a key
   * @return true if Term can be learnt without missing any names of varibales
   */
  public abstract boolean canBeLearnt();
}
