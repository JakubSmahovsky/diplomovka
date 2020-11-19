package simple_tamarin.dataStructures.term;

/**
 * Term holding an instance of symmetric encoding.
 * Variable senc holds the term being decoded.
 * It can be either an instance of FunctionSenc 
 * or a variable which can be deconstructed to FunctionSenc.
 * Variable decodedValue holds the value received by decoding (the deconstruction of) senc.
 * These conditions are not verified here, because we need to log more speciffic errors in VisitorImp.
 */
public class FunctionSdec implements Term{
  public Term key;
  public Term senc;
  public Term decodedValue;

  public FunctionSdec(Term key, Term senc, Term decodedValue) {
    this.key = key;
    this.senc = senc;
    this.decodedValue = decodedValue;
  }

  @Override public boolean equals(Object term) {
    if (this == term) {
      return true;
    }
    if (term instanceof FunctionSdec) {
      if (key.equals(((FunctionSdec)term).key) && senc.equals(((FunctionSdec)term).senc)) {
        return true;
      }
    }
    return false;
  }

  @Override public Term deconstructTerm() {
    return decodedValue;
  }
}
