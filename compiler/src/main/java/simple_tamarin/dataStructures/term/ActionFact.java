package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import simple_tamarin.BuilderFormatting;

public class ActionFact extends Term{
  public String name;
  public ArrayList<Term> terms;

  public ActionFact(String name, ArrayList<Term> terms) {
    this.name = name;
    this.terms = terms;
  }

  @Override
  public String toString() {
    return BuilderFormatting.fact(name, terms);
  }

  @Override
  public Term deconstructTerm() {
    return this;
  } 
}
