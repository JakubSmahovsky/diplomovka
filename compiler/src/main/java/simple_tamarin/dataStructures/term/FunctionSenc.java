package simple_tamarin.dataStructures.term;

import simple_tamarin.Constants;

public class FunctionSenc extends Term{
  public Term key;
  public Term value;

  public FunctionSenc(Term key, Term value) {
    this.key = key;
    this.value = value;
  }

  @Override public String toString() {
    return Constants.SENC + "(" + value.toString() + ", " + key.toString() + ")";
  }

  @Override public boolean equals(Object term) {
    if (this == term) {
      return true;
    }
    if (term instanceof FunctionSenc) {
      if (key.equals(((FunctionSenc)term).key) && value.equals(((FunctionSenc)term).value)) {
        return true;
      }
    }
    return false;
  }

  @Override public Term deconstructTerm() {
    return this;
  }
}
