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

public class FunctionSenc extends Term {
  private final Term key;
  private final Term value;
  private final FunctionSenc normalForm;

  public FunctionSenc(Term key, Term value) {
    this.key = key;
    this.value = value;
    this.normalForm = new FunctionSenc(this);
  }

  /**
   * normal form constructor
   */
  private FunctionSenc(FunctionSenc original) {
    this.normalForm = this;
    this.key = original.key.getNormalForm();
    this.value = original.value.getNormalForm();
  }

  @Override public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.FunctionSenc;
  }

  @Override public int normalFormCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionSenc, compare based on subterms
    FunctionSenc functionSenc = (FunctionSenc)term;  
    result = key.compareTo(functionSenc.key);
    if (result != 0) {
      return result;
    }
    return value.compareTo(functionSenc.value);
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
    if (!(term instanceof FunctionSenc)) {
      return false;
    }
    return (key.equals(((FunctionSenc)term).key) && value.equals(((FunctionSenc)term).value));
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.T_SENC, Arrays.asList(value, key), null);
  }

  @Override public String render(STBlock block){
    for (Deconstruction dec : block.deconstructed) {
      if (dec.substituted.equals(this)) {
        return dec.substitution.render();
      }
    }

    return BuilderFormatting.fact(Constants.T_SENC, Arrays.asList(value, key), block);
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

  @Override public Term symmetric_decrypt(Term key, TermContext keyCtx, TermContext valueCtx) {
    if (!this.key.equals(key)) {
      Errors.ErrorSymmetricKeyNotMatch(keyCtx);
    }
    return value;
  }
}
