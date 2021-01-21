package simple_tamarin.dataStructures.term;

import java.util.List;

import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.errors.Errors;
public abstract class Term{
  public abstract Term toCanonical();

  /**
   * Extracts variables that can be learnt from a transparent Term.
   * Non-transparent Terms don't need to Override it.
   */
  public List<Variable> extractKnowledge() {
    Errors.DebugUnexpectedCall("extractKnowledge", render());
    return null;
  };

  /**
   * Render Term to text in Tamarin syntax
   */
  public abstract String render();

  /**
   * Like render, but with respect to a block.
   * The term may be deconstructed in which case it is rendered
   * as it's deconstruction with the substitutions given by the deconstruction.
   * Should be overriden where it makes sense. See Decontrustion class for more info.
   */
  public String render(StBlock block) {
    return render();
  };

  /**
   * Like render but substitute a value that may be subtituted.
   * Only makes sense for Terms that may be canonical forms of Variables
   * that are being deconstructed, e.g. Variable x with canoncical form
   * of senc(k, v) may get deconstructed and assigned to substitution 
   * in which case we want to render "senc(k, substitution)" instead of "x".
   */
  public String render(Term substitution) {
    Errors.DebugUnexpectedCall("render(substiturion)", render());
    return null;
  }

  /**
   * Render Term for use in a lemma, rendering Variables without sorts
   * other than temporal.
   */
  public abstract String renderLemma();

  /**
   * Variable is about to be generated, add fresh sort (tag ~)
   */
  public void addFresh() {
    Errors.DebugUnexpectedCall("addFresh", render());
  }

  /**
   * Removed Fresh sort (tag ~) from variables in Term.
   */
  public abstract void removeFresh();

  /**
   * Deconstruction terms are sort of abstract terms in the context
   * of a protocol model. We don't want to use them in the output, but 
   * they hold information about how a value was created. They are mostly
   * decryption functions.
   */
  public abstract boolean isDeconstructionTerm();


  /**
   * Check if terms have the same structure, variable unifies with anything
   */
  public abstract boolean unify(Term term);

  /**
   * Generate list of free variables of a Term to be declared when
   * rendering a lemma. Deconstruction Terms don't need to Override it.
   */
  public List<Variable> freeVariables(){
    Errors.DebugUnexpectedCall("freeVariables", render());
    return null;  
  }

  /**
   * Return encoded value for deconstruction terms
   * @return e for dec(k, e)
   */
  public Term encoded() {
    if (isDeconstructionTerm()) {
      Errors.DebugMissingImplementation("encoded()", render());
    }
    Errors.DebugUnexpectedCall("encoded()", render());
    return null;
  }

  /**
   * Function for assigning variables from left side of an assignemt
   * their respective values from the right side of the assignment.
   * Creates alises in case of construction right terms and
   * deconstructions in case of deconstruction right terms.
   * The left term (this) is populated with correct variable.
   * This function should be overriden by "transparent" Terms and no other.
   * @return false if left and right cannot be unified
   */
  public boolean assign(Term right, StBlock block, Principal principal) {
    Errors.DebugUnexpectedCall("assign", render());
    return false;
  }
}
