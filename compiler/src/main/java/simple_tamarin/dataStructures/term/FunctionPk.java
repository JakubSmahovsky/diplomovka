package simple_tamarin.dataStructures.term;

import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.stParser.Simple_tamarinParser.TermContext;

public class FunctionPk extends Term {
  private final Term sk;
  private final FunctionPk normalForm;

  public FunctionPk(Term sk) {
    this.sk = sk;
    this.normalForm = new FunctionPk(this);
  }

  /**
   * normal form constructor
   */
  private FunctionPk(FunctionPk original) {
    this.normalForm = this;
    this.sk = original.sk.getNormalForm();
  }

  @Override public NormalFormTypeOrder getTypeOrder(){
    return NormalFormTypeOrder.FunctionPk;
  }

  @Override public int normalFormCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionPk, compare based on subterms
    FunctionPk functionPk = (FunctionPk)term;
    return sk.normalFormCompareTo(functionPk.sk);
  }

  @Override public Term getNormalForm() {
    return normalForm;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).getNormalForm();
    if (!(term instanceof FunctionPk)) {
      return false;
    }
    return sk.equals(((FunctionPk)term).sk);
  }

  @Override public String render() {
    return BuilderFormatting.fact(Constants.PK, sk, null);
  }

  @Override public String render(STBlock block) {
    return BuilderFormatting.fact(Constants.PK, sk, block);
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public List<Variable> freeVariables(){
    return sk.freeVariables();
  }

  @Override public boolean verifyPk(Term sk, TermContext pkCtx) {
    return this.sk.equals(sk);
  }
}
