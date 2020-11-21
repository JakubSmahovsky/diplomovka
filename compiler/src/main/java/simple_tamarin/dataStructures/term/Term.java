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
   * Extracts subterms from source term, but doesn't deconstruct them.
   * Eg. if "x" is a variable with subterm "y",
   * then "x" is extracted from "DEC(k, x)", but "y" isn't (neither is "k")
   * Eg2. "ENC(k, x)" is returned as is, neither "k" nor "x" is extracted
   */
  public abstract List<Term> extractKnowledge();

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
