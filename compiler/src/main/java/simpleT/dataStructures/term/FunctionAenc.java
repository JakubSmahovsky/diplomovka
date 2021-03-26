package simpleT.dataStructures.term;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.Deconstruction;
import simpleT.dataStructures.STBlock;
import simpleT.errors.Errors;
import simpleT.stParser.SimpleTParser.TermContext;

public class FunctionAenc extends Term {
  private final Term key;
  private final Term value;
  private final FunctionAenc normalForm;

  public FunctionAenc(Term key, Term value) {
    this.key = key;
    this.value = value;
    this.normalForm = new FunctionAenc(this);
  }

  /**
   * normal form constructor
   */
  private FunctionAenc(FunctionAenc original) {
    this.normalForm = this;
    this.key = original.key.getNormalForm();
    this.value = original.value.getNormalForm();
  }

  @Override public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.FunctionAenc;
  }

  @Override public int normalFormCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionAenc, compare based on subterms
    FunctionAenc functionAenc = (FunctionAenc)term;  
    result = key.compareTo(functionAenc.key);
    if (result != 0) {
      return result;
    }
    return value.compareTo(functionAenc.value);
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
    if (!(term instanceof FunctionAenc)) {
      return false;
    }
    return (key.equals(((FunctionAenc)term).key) && value.equals(((FunctionAenc)term).value));
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.AENC, Arrays.asList(value, key), null);
  }

  @Override public String render(STBlock block){
    for (Deconstruction dec : block.deconstructed) {
      if (dec.substituted.equals(this)) {
        return dec.substitution.render();
      }
    }

    return BuilderFormatting.fact(Constants.AENC, Arrays.asList(value, key), block);
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public List<Variable> freeVariables() {
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.freeVariables());
    result.addAll(value.freeVariables());
    return result;
  }

  @Override public Term asymmetric_decrypt(Term sk, TermContext skCtx, TermContext valueCtx) {
    if (!this.key.verifyPk(sk)) {
      Errors.ErrorWrongSecretKeyAdec(skCtx);
    }
    return value;
  }
}
