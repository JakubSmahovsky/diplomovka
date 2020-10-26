package simple_tamarin.dataStructures.term;

import simple_tamarin.Constants;

public class FunctionSdec extends Term{
  public Term key;
  public Term value;

  public FunctionSdec(Term key, Term value) {
    this.key = key;
    this.value = value;
  }

  @Override public String toString() {
    return Constants.SDEC + "(" + key.toString() + ", " + value.toString() + ")";
  }

  @Override public boolean equals(Term term) {
    if (this == term) {
      return true;
    }
    if (term.getClass() == FunctionSdec.class) {
      if (key.equals(((FunctionSdec)term).key) && value.equals(((FunctionSdec)term).value)) {
        return true;
      }
    }
    return false;
  }
}
