package simpleT.dataStructures.term;

import java.util.Arrays;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.Deconstruction;
import simpleT.dataStructures.STBlock;
import simpleT.errors.Errors;

public class FunctionSdec extends Term{
  private final Term key;
  private final Term encryptedValue; // v in DEC(k, v) 
  private final Term normalForm;

  public FunctionSdec(Term key, Term encryptedValue, Term decryptedValue) {
    this.key = key;
    this.encryptedValue = encryptedValue;
    this.normalForm = decryptedValue.getNormalForm();
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
    return BuilderFormatting.fact(Constants.T_SDEC, Arrays.asList(encryptedValue, key), null);
  }

  @Override public String render(STBlock block){
    return BuilderFormatting.fact(Constants.T_SDEC, Arrays.asList(encryptedValue, key), block);
  }

  @Override public boolean isDeconstructionTerm() {
    return true;
  }

  @Override public Deconstruction createDeconstruction(Term assignedTo) {
    return new Deconstruction(encryptedValue, new FunctionSenc(key, assignedTo));
  }
}
