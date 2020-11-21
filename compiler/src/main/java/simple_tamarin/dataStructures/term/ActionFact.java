package simple_tamarin.dataStructures.term;

import simple_tamarin.BuilderFormatting;

import java.util.ArrayList;
import java.util.List;

public class ActionFact extends Term{
  public String name;
  public ArrayList<Term> terms;

  public ActionFact(String name, ArrayList<Term> terms) {
    this.name = name;
    this.terms = terms;
  }

  @Override public Term deconstructTerm() {
    return this;
  }
  
  /**
   * Unify does not make sense for action facts and should never be called.
   * TODO: refactor this somehow
   */
  @Override public List<Variable> unify(Term right) {
    return null;
  }

  /**
   * extractKnowledge does not make sense for action facts and should never be called.
   * TODO: refactor this somehow
   */
  @Override public List<Term> extractKnowledge() {
    return null;
  }

  @Override public String render(){
    return BuilderFormatting.fact(name, terms);
  }

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(name, terms);
  }

  @Override public void removeFresh() {
    for (Term term : terms) {
      term.removeFresh();
    }
  }
}
