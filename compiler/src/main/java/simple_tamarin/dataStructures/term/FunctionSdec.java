package simple_tamarin.dataStructures.term;

import java.util.Arrays;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.errors.Errors;

/**
 * Term holding an instance of symmetric encoding.
 * Variable senc holds the term being decoded.
 * It can be either an instance of FunctionSenc 
 * or a variable which can be deconstructed to FunctionSenc.
 * Variable decodedValue holds the value received by decoding (the deconstruction of) senc.
 * These conditions are not verified here, because we need to log more speciffic errors in VisitorImp.
 */
public class FunctionSdec extends Term{
  private final Term key;
  private final Term encodedValue;
  private final Term decodedValue;

  public FunctionSdec(Term key, Term encodedValue, Term decodedValue) {
    this.key = key;
    this.encodedValue = encodedValue;
    this.decodedValue = decodedValue;
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.NON_CANONICAL;
  }

  @Override public int canonicalCompareTo(Term term) {
    // throw error, this is not a canonicalForm
    Errors.DebugUnexpectedCall("canonicalCompareTo", "FunctionSdec");
    return 0;
  }

  @Override public Term toCanonical() {
    return decodedValue.toCanonical();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    return this.toCanonical().equals(((Term)obj).toCanonical());
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.SDEC, Arrays.asList(encodedValue, key), null);
  }

  @Override public String render(STBlock block){
    return BuilderFormatting.fact(Constants.SDEC, Arrays.asList(encodedValue, key), block);
  }

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(Constants.SDEC, Arrays.asList(encodedValue, key));
  }

  @Override public boolean isDeconstructionTerm() {
    return true;
  }

  @Override public boolean unify(Term term) {
    if (!(term instanceof FunctionSdec)) {
      return false;
    }
    FunctionSdec sdec = (FunctionSdec)term;
    return (key.unify(sdec.key) && encodedValue.unify(sdec.encodedValue) && decodedValue.unify(sdec.decodedValue)); 
  }

  @Override public Term getEncodedValue(){
    return encodedValue;
  }
}
