package simple_tamarin.dataStructures.term;

import java.util.List;

import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.dataStructures.STModel;
import simple_tamarin.errors.Errors;
import simple_tamarin.stParser.Simple_tamarinParser.TermContext;
public abstract class Term implements Comparable<Term>{
  protected static enum CanonicalTypeOrder {
    Constant,
    Variable,
    Tuple,
    FunctionSenc,
    FunctionHash,
    Exponentiation,
    NON_CANONICAL
  }

  /**
   * Should return a static order number of term class.
   * It should be declared as a constant in Constants
   * so that each subclass has a different order.
   */
  public abstract CanonicalTypeOrder getTypeOrder();

  /**
   * Compare terms based on canonical forms.
   */
  public final int compareTo(Term term) {
    return this.toCanonical().canonicalCompareTo(term.toCanonical());
  }

  /**
   * Compare 2 cannonical forms - this and term
   * The idea is to compare canonical forms lexicographically, however individual lexems are not 
   *    simple characters but rather entire function names / atomic term identifiers.
   *    Order of individual lexems is well defined, but arbitrary.
   * Compare term types based on getTypeOrder().
   * Compare atomic terms (constants to constants, variables to variables) based on their id.
   * Compare subterm in order (based on INPUT/VERIFPAL order, e.g. ENC(k, v)).
   * Must be consistent with .equals(Term term)
   */
  public abstract int canonicalCompareTo(Term term);

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
   */
  public abstract String render(STBlock block);

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
   * Return the encoded form of the value in a decode function
   * e.g. return e for DEC(k, e)
   * Does NOT return a value if "this" is not an instance of decode function (e.g. variable with subterm of decode)
   */
  public Term getEncodedValue() {
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
  public boolean assign(Term right, STBlock block, Principal principal) {
    Errors.DebugUnexpectedCall("assign", render());
    return false;
  }

  /**
   * Decode an encoded term.
   * Should be extended by an enum to indicate type of encoding function
   * once there is more than 1 encoding function.
   */
  public Term decode(Term key, TermContext keyCtx, TermContext valueCtx) {
    Errors.ErrorDecodingNotEncoded(valueCtx);
    return null;
  }

  public boolean isPublicVariable(STModel model) {
    return false;
  }
}
