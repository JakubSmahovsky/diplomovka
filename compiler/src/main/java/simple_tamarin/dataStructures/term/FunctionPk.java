package simple_tamarin.dataStructures.term;

import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;

public class FunctionPk extends Term {
  private final Term sk;
  private final FunctionPk canonical;

  public FunctionPk(Term sk) {
    this.sk = sk;
    this.canonical = new FunctionPk(this);
  }

  /**
   * Canonical form constructor
   */
  private FunctionPk(FunctionPk original) {
    this.canonical = this;
    this.sk = original.sk.getCanonical();
  }

  @Override public CanonicalTypeOrder getTypeOrder(){
    return CanonicalTypeOrder.FunctionPk;
  }

  @Override public int canonicalCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionPk, compare based on subterms
    FunctionPk functionPk = (FunctionPk)term;
    return sk.canonicalCompareTo(functionPk.sk);
  }

  @Override public Term getCanonical() {
    return canonical;
  }

  @Override public String render() {
    return BuilderFormatting.fact(Constants.PK, sk, null);
  }

  @Override public String render(STBlock block) {
    return BuilderFormatting.fact(Constants.PK, sk, block);
  }

  @Override public String renderLemma() {
    return BuilderFormatting.lemmaFact(Constants.PK, sk, null);
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }


  @Override public boolean unify(Term term) {
    return ((term instanceof FunctionPk) && sk.unify(((FunctionPk)term).sk));
  }

  @Override public List<Variable> freeVariables(){
    return sk.freeVariables();
  }
}