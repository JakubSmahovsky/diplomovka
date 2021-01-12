package simple_tamarin.sourcesCompiler.term;

import simple_tamarin.dataStructures.term.Term;

public class OutputVariable extends OutputTerm {
  public String name;
  public String number;
  
  public OutputVariable(String name, String number) {
    this.name = name;
    this.number = number;
  }

  @Override public boolean unify(Term term) {
    return true;
  }

  @Override public String render() {
    return name + number;
  }
}
