package simple_tamarin.dataStructures.term;

import java.util.List;

import simple_tamarin.errors.Errors;

public abstract class Term{
  public abstract Term deconstructTerm();
  /**
   * Function for assigning variables from left side of an assignemt
   * their respective values from the right side of the assignment.
   * The left term (this) is populated with correct variable. 
   * @return list of all reassigned variables or null in case of error
   * TODO: throw runtimeException in case of errors
   */
  public List<Variable> unify(Term right) {
    Errors.DebugUnexpectedFunction(render(), "unify");
    return null;
  };
  
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
}
