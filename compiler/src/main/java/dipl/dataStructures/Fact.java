package dipl.dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dipl.BuilderFormatting;
import dipl.Constants;
import dipl.dataStructures.term.Term;
import dipl.dataStructures.term.Variable;

/**
 * Used to store information needed to render a fact in Tamarin
 * e.g. special action fact or result fact like Eq(x,x) or PrincipalPrivate(p, v)
 */
public class Fact {
  private static int nextSentFactId;
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
  public String render(Block block){
    if (persistent) {
      return BuilderFormatting.persistentFact(name, terms, block);
    } else {
      return BuilderFormatting.fact(name, terms, block);
    }
  }

  /** render outside of a block
   */
  public String render(){
    if (persistent) {
      return BuilderFormatting.persistentFact(name, terms, null);
    } else {
      return BuilderFormatting.fact(name, terms, null);
    }
  }

  /**
   * Create a classic fact to declare equality of 2 terms
   */
  public static Fact equality(Term term1, Term term2) {
    return new Fact(false, Constants.FACT_EQUALITY, Arrays.asList(term1, term2));
  }


  /**
   * Create a classic fact for use in injective authentication declaring a principal has sent a variable in the correct session
   */
  public static Fact injAuthSent(Principal sender, Variable variable, Variable session) {
    String label = Constants.FACT_INJ_AUTHENTICATION_SENT + Constants.NAME_SEPARATOR + nextSentFactId++;
    return new Fact(false, label , Arrays.asList(sender.principalID, variable, session));
  }

  /**
   * Create a classic fact for use in authentication declaring a principal has sent a variable
   */
  public static Fact authSent(Principal sender, Variable variable) {
    String label = Constants.FACT_AUTHENTICATION_SENT + Constants.NAME_SEPARATOR + nextSentFactId++;
    return new Fact(false, label , Arrays.asList(sender.principalID, variable));
  }
}
