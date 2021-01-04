package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.dataStructures.term.Term;

public class Fact {
  public boolean persistent;
  public String name; 
  public ArrayList<Term> terms;

  public Fact(boolean persistent, String name, ArrayList<Term> terms) {
    this.persistent = persistent;
    this.name = name;
    this.terms = terms;
  }

  @Override public String toString() {
    return (persistent ? "!" : "") + BuilderFormatting.fact(name, terms, null);
  }
}
