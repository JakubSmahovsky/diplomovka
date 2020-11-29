package simple_tamarin.dataStructures.term;

import java.util.List;

import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.errors.Errors;
import simple_tamarin.errors.STException;

public abstract class Term{
  public abstract Term deconstructTerm();

  /**
   * Return contained encoded value for deconstruction terms
   */
  public Term encoded() {
    if (isDeconstructionTerm()) {
      throw new STException("DEBUG: Unimplemented required encoded() method");
    }
    throw new STException("DEBUG: Unexpected call to encoded() method");
  }

  /**
   * Function for assigning variables from left side of an assignemt
   * their respective values from the right side of the assignment.
   * In case of complex terms on the right side, variables are
   * substituted instead.
   * The left term (this) is populated with correct variable.
   * Aliases created during substitution are added to the block.
   * @return false in case of errors
   */
  public boolean unify(Term right, StBlock block, Principal principal) {
    Errors.DebugUnexpectedFunction(render(), "unify");
    return false;
  };
  
  /**
   * Extracts variables (syntactically) from Terms that can be learnt
   */
  public abstract List<Variable> extractKnowledge();

  /**
   * Render Term to text in Tamarin syntax
   */
  public abstract String render();

  /**
   * Like render(); but with respect to a block.
   * The term may be deconstructed in which case it is rendered
   * as it's deconstruction with the substitutions given by the deconstruction.
   */
  public abstract String render(StBlock block);

  /**
   * Like render, but substitute Variables which may be unnamed
   * by Variables from the substitutions list
   */
  public abstract String render(List<Term> substitutions);

  /**
   * Render Term for use in a lemma, removing all sorts from Variables
   */
  public abstract String renderLemma();

  /**
   * Removed Fresh tag (~) from variables in Term
   */
  public abstract void removeFresh();

  /**
   * Deconstruction terms are sort of abstract terms in the context
   * of a protocol model. We don't want to use them in the output, but 
   * they hold information about how a value was created. They are mostly
   * decryption functions.
   */
  public abstract boolean isDeconstructionTerm();
}
