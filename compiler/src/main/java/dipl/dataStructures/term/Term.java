package dipl.dataStructures.term;

import java.util.ArrayList;
import java.util.List;

import dipl.dataStructures.Deconstruction;
import dipl.dataStructures.Principal;
import dipl.dataStructures.Block;
import dipl.dataStructures.Model;
import dipl.errors.Errors;
import dipl.inputParser.InputParser.TermContext;
public abstract class Term implements Comparable<Term>{
  protected static enum NormalFormTypeOrder {
    Constant,
    Variable,
    Tuple,
    FunctionPk,
    FunctionSign,
    FunctionSenc,
    FunctionAenc,
    FunctionHash,
    Exponentiation,
    ValueTrue,
    NON_NORMAL
  }

  public static boolean containsByObjectEquality(ArrayList<? extends Term> termList, Term term) {
    for (Term inList : termList) {
      if (inList == term) {
        return true;
      }
    }
    return false;
  }

  /**
   * Should return a static order number of term class.
   * It should be declared as a constant in Constants
   * so that each subclass has a different order.
   */
  public abstract NormalFormTypeOrder getTypeOrder();

  /**
   * Compare terms based on normal forms.
   */
  public final int compareTo(Term term) {
    return this.getNormalForm().normalFormCompareTo(term.getNormalForm());
  }

  /**
   * Compare 2 cannonical forms - this and term
   * The idea is to compare normal forms lexicographically, however individual lexems are not 
   *    simple characters but rather entire function names / atomic term identifiers.
   *    Order of individual lexems is well defined, but arbitrary.
   * Compare term types based on getTypeOrder().
   * Compare atomic terms (constants to constants, variables to variables) based on their id.
   * Compare subterm in order (based on INPUT/VERIFPAL order, e.g. ENC(k, v)).
   * Must be consistent with .equals(Term term)
   */
  public abstract int normalFormCompareTo(Term term);

  public abstract Term getNormalForm();

  @Override
  public abstract boolean equals(Object obj);

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
   * If "this" might have been substituted, check if there is a deconstruction substituting "this"
   * for something else and render that instead. Otherwise render normally but recursively call with block.
   */
  public abstract String render(Block block);
  
  /**
   * Deconstruction terms are sort of abstract terms in the context
   * of a protocol model. We don't want to use them in the output, but 
   * they hold information about how a value was created. They are mostly
   * decryption functions.
   */
  public abstract boolean isDeconstructionTerm();

  /**
   * Generate list of free variables of a Term to be declared when
   * rendering a lemma. Deconstruction Terms don't need to Override it.
   */
  public List<Variable> freeVariables(){
    Errors.DebugUnexpectedCall("freeVariables", render());
    return null;
  }

  /**
   * Create a deconstruction that replaces the term that "this" deconstructs by the 
   * correct substitution according to the term that the result of deconstruction was assigned to.
   * e.g. v2 = sdec(senc(v, k), k2) makes senc(v, k) render as senc(v2, k2).  
   * Only deconstruction terms should override this.
   */
  public Deconstruction createDeconstruction(Term assignedTo) {
    Errors.DebugUnexpectedCall("createDeconstruction", render());
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
  public boolean assign(Term right, boolean rightIndirection, Block block) {
    Errors.DebugUnexpectedCall("assign", render());
    return false;
  }

  /**
   * Clone this Term for a new owner when receiving it in a message.
   * This function should be overriden by "transparent" Terms only
  */
  public Term sentToReceived(Model model, Principal recipient, TermContext messageCtx) {
    Errors.DebugUnexpectedCall("clone", render());
    return null;
  }

  /**
   * Decrypt a symmetrically encrypted term.
   */
  public Term symmetric_decrypt(Term key, TermContext keyCtx, TermContext valueCtx) {
    Errors.ErrorSymmetricNotEncrypted(valueCtx);
    return null;
  }
  
  /**
   * Decrypt an asymmetrically encrypted term.
   */
  public Term asymmetric_decrypt(Term key, TermContext keyCtx, TermContext valueCtx) {
    Errors.ErrorAsymmetricNotEncrypted(valueCtx);
    return null;
  }

  /**
   * Verify this signature. (chceck that pk and message fit).
   * Should only be used on a nornal form.
   */
  public void verifySignature(Term pk, Term message, TermContext pkCtx, TermContext messageCtx, TermContext signatureCtx) {
    Errors.ErrorSignVerifNotSigned(signatureCtx);
  }

  /**
   * Verify the provied secret key against "this" public key.
   * Should only be used on a normal form.
   */
  public boolean verifyPk(Term sk, TermContext pkCtx) {
    Errors.ErrorKeyNotPublicKey(pkCtx);
    return false;
  }

  /**
   * Verify the provied secret key against "this" public key.
   * Should only be used on a normal form.
   */
  public boolean verifyPk(Term sk) {
    Errors.DebugUnexpectedCall("verifyPk", "Term");
    return false;
  }
}
