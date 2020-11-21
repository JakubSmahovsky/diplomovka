package simple_tamarin.dataStructures;

import simple_tamarin.dataStructures.term.Term;

/**
 * A struct holding information required to construct an alias inside a 
 * "let ... in" block
 */
public class Alias {
  public Term left;
  public Term right;

  public Alias(Term left, Term right) {
    this.left = left;
    this.right = right;
  }
}
