package simple_tamarin.dataStructures;

import simple_tamarin.dataStructures.term.Term;

/**
 * When deconstructing a non-transparent Term, for example when decoding an encoded value,
 * we spread the Term in Tamarin code instead of using aliases. To support that we create
 * a decondtruction object in the corresponding block, so that all occourences of such term
 * are being spread out. The decoded calue inside this term needs to be substituted by
 * the resut of this deconstrution, therefore we remember this substiturion term.
 */
public class Deconstruction {
  public Term term;
  public Term substitution;

  public Deconstruction(Term substituted, Term substitution) {
    this.term = substituted;
    this.substitution = substitution;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Deconstruction && term.equals(((Deconstruction)obj).term)) {
      return true;
    }
    return false;
  }
}
