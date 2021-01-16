package simple_tamarin.dataStructures;

import java.util.ArrayList;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.dataStructures.term.Term;

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
  public String render(StBlock block){
    String persMark = persistent ? "!" : "";
    return BuilderFormatting.fact(persMark + name, terms, block);
  }

  public String renderLemma(){
    return BuilderFormatting.lemmaFact(name, terms);
  }
}
