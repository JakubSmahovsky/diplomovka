package simple_tamarin.dataStructures.term;

public class FunctionSenc implements Term{
  public Term key;
  public Term value;

  public FunctionSenc(Term key, Term value) {
    this.key = key;
    this.value = value;
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
