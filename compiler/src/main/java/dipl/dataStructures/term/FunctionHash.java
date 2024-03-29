package dipl.dataStructures.term;

import java.util.List;

import dipl.BuilderFormatting;
import dipl.Constants;
import dipl.dataStructures.Block;

/**
 * An unary hash function, should be created with a tuple
 * to simulate an n-ary hash function.
 */
public class FunctionHash extends Term {
  private final Term subterm;
  private final FunctionHash normalForm;

  public FunctionHash(Term subterm) {
    this.subterm = subterm;
    this.normalForm = new FunctionHash(this);
  }

  /**
   * normal form constructor
   */
  private FunctionHash(FunctionHash original) {
    this.normalForm = this;
    this.subterm = original.subterm.getNormalForm();
  }

  @Override
  public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.FunctionHash;
  }

  @Override
  public int normalFormCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionHash, compare based on subterms
    FunctionHash functionHash = (FunctionHash)term;
    return subterm.normalFormCompareTo(functionHash.subterm);
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
    if (!(term instanceof FunctionHash)) {
      return false;
    }
    return subterm.equals(((FunctionHash)term).subterm);
  }

  @Override
  public String render() {
    return BuilderFormatting.fact(Constants.T_HASH, subterm, null);
  }

  @Override
  public String render(Block block) {
    return BuilderFormatting.fact(Constants.T_HASH, subterm, block);
  }

  @Override
  public boolean isDeconstructionTerm() {
    return false;
  }

  @Override
  public List<Variable> freeVariables() {
    return subterm.freeVariables();
  }
}
