package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;

/**
 * Term holding an instance of symmetric encoding.
 * Variable senc holds the term being decoded.
 * It can be either an instance of FunctionSenc 
 * or a variable which can be deconstructed to FunctionSenc.
 * Variable decodedValue holds the value received by decoding (the deconstruction of) senc.
 * These conditions are not verified here, because we need to log more speciffic errors in VisitorImp.
 */
public class FunctionSdec extends Term{
  public Term key;
  public Term senc;
  public Term decodedValue;

  public FunctionSdec(Term key, Term senc, Term decodedValue) {
    this.key = key;
    this.senc = senc;
    this.decodedValue = decodedValue;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    return this.deconstructTerm().equals(((Term)obj).deconstructTerm());
  }

  @Override public Term deconstructTerm() {
    return decodedValue;
  }

  /**
   * TODO: Info message - don't define variables this way, it's silly
   */
  @Override public List<Variable> unify(Term right) {
    if (!(right instanceof FunctionSdec)) {
      return null;
    }
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.unify(((FunctionSdec)right).key));
    result.addAll(senc.unify(((FunctionSdec)right).senc));
    return result;
  }

  @Override public List<Variable> extractKnowledge() {
    return null;
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.SDEC, Arrays.asList(senc, key));
  }

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(Constants.SDEC, Arrays.asList(senc, key));
  }

  @Override public void removeFresh() {
    key.removeFresh();
    senc.removeFresh();
    decodedValue.removeFresh();    
  }

  @Override public boolean canBeLearnt() {
    return false;
  }
}
