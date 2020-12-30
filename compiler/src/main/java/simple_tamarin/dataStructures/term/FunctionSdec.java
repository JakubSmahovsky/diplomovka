package simple_tamarin.dataStructures.term;

import java.util.Arrays;

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
  public Term encodedValue;
  public Term decodedValue;

  public FunctionSdec(Term key, Term encodedValue, Term decodedValue) {
    this.key = key;
    this.encodedValue = encodedValue;
    this.decodedValue = decodedValue;
  }

  @Override public Term toCanonical() {
    return decodedValue;
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

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(Constants.SDEC, Arrays.asList(encodedValue, key));
  }

  @Override public void removeFresh() {
    key.removeFresh();
    encodedValue.removeFresh();
    decodedValue.removeFresh();    
  }

  @Override public boolean isDeconstructionTerm() {
    return true;
  }

  @Override public Term encoded(){
    return encodedValue;
  }
}
