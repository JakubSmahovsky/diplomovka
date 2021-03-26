package simpleT.dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.term.Term;
import simpleT.dataStructures.term.Variable;

/**
 * Used to store information needed to render a fact in Tamarin
 * e.g. special action fact or result fact like Eq(x,x) or PrincipalPrivate(p, v)
 */
public class Fact {
  public boolean persistent;
  public String name;
  public ArrayList<Term> terms;

  public Fact(boolean persistent, String name, List<Term> terms) {
    this.persistent = persistent;
    this.name = name;
    this.terms = new ArrayList<>(terms);
  }

  /** render with respect to block (for substitutions)
   * @param block to take substitutions from, pass null if no substitutions are needed
   */
  public String render(STBlock block){
    String persMark = persistent ? "!" : "";
    return BuilderFormatting.fact(persMark + name, terms, block);
  }

  /**
   * Create a classic fact to declare equality of 2 terms
   */
  public static Fact equality(Term term1, Term term2) {
    return new Fact(false, Constants.EQUALITY, Arrays.asList(term1, term2));
  }

  /**
   * Create a classic fact for use in authentication declaring a principal has sent a variable
   */
  public static Fact authSent(Principal sender, Variable variable) {
    return new Fact(false, Constants.AUTH_SENT, Arrays.asList(sender.principalID, variable));
  }
}
