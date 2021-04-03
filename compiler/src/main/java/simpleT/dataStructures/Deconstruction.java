package simpleT.dataStructures;

import simpleT.dataStructures.term.Term;

/**
 * When deconstructing a non-transparent Term, for example when decrypting an encrypted value,
 * we spread the Term in Tamarin code instead of deconstructing using aliases.
 * To do this we create a decondtruction object in the corresponding block:
 *   The substituted is the term that was deconstructed e.g. in "v2 = sdec(senc(v1, k1), k2)" it is "senc(v1, k1)"
 *   The substitution is the term that is rendered in it's place e.g. in the above example it is "senc(v2, k2)"
 *   For tuples, instead of deconstructing "{x, y} = t" we render "{x, y}" whenever "t" was supposed to be rendered
 */
public class Deconstruction {
  public Term substituted;
  public Term substitution;

  public Deconstruction(Term substituted, Term substitution) {
    this.substituted = substituted;
    this.substitution = substitution;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Deconstruction && substituted.equals(((Deconstruction)obj).substituted)) {
      return true;
    }
    return false;
  }
}
