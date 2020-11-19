package simple_tamarin.dataStructures.term;

import java.util.ArrayList;

public class ActionFact implements Term{
  public String name;
  public ArrayList<Term> terms;

  public ActionFact(String name, ArrayList<Term> terms) {
    this.name = name;
    this.terms = terms;
  }

  @Override public Term deconstructTerm() {
    return this;
  } 
}
