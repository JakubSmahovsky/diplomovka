package simple_tamarin.dataStructures;

import java.util.ArrayList;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.dataStructures.term.Term;

/**
 * A seldom used class used to store information needed to render an action fact
 * in Tamarin
 */
public class Fact {
  public String name;
  public ArrayList<Term> terms;

  public Fact(String name, ArrayList<Term> terms) {
    this.name = name;
    this.terms = new ArrayList<>(terms);
  }

  public String render(StBlock block){
    return BuilderFormatting.fact(name, terms, block);
  }

  public String renderLemma(){
    return BuilderFormatting.lemmaFact(name, terms);
  }
}
