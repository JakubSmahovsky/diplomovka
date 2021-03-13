package simpleT.dataStructures.term;

import java.util.Arrays;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.Deconstruction;
import simpleT.dataStructures.STBlock;
import simpleT.errors.Errors;

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
  private final Term encodedValue; // v in DEC(k, v) 
  private final Term normalForm;

  public FunctionSdec(Term key, Term encodedValue, Term decodedValue) {
    this.key = key;
    this.encodedValue = encodedValue;
    this.normalForm = decodedValue.getNormalForm();
  }

  @Override public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.NON_NORMAL;
  }

  @Override public int normalFormCompareTo(Term term) {
    // throw error, this is not a normal form
    Errors.DebugUnexpectedCall("normalFormCompareTo", "FunctionSdec");
    return 0;
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
    return this.getNormalForm().equals(((Term)obj).getNormalForm());
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.SDEC, Arrays.asList(encodedValue, key), null);
  }

  @Override public String render(STBlock block){
    return BuilderFormatting.fact(Constants.SDEC, Arrays.asList(encodedValue, key), block);
  }

  @Override public boolean isDeconstructionTerm() {
    return true;
  }

  @Override public Deconstruction createDeconstruction(Term assignedTo) {
    return new Deconstruction(encodedValue, new FunctionSenc(key, assignedTo));
  }
}
