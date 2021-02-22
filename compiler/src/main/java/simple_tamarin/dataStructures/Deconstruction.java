package simple_tamarin.dataStructures;

import simple_tamarin.dataStructures.term.Term;

/**
 * When deconstructing a non-transparent Term, for example when decoding an encoded value,
 * we spread the Term in Tamarin code instead of deconstructing using aliases.
 * To do this we create a decondtruction object in the corresponding block:
 *   The term is the term that was deconstructed e.g. in "x = sdec(senc(v, k), k)" it is "senc(v, k)"
 *   The substitution is the Term that would have been assigned the value e.g. in "x = sdec(senc(v, k), k)" it is "x"
 *   We then want to render "term" in place of variables with the meaning of "term"
 *   And we want to render "substitution" in that term instead of the decodedValue
 *   e.g. instead of receiving "m" and deconstructing "x = sdec(m, k)" we receive "senc(x, k)"
 *   for tuples, instead of deconstructing "{x, y} = sdec(m, k)" we receive "senc({x, y}, k)"
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
