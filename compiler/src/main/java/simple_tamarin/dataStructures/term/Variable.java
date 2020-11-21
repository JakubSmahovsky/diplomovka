package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Principal;

public class Variable extends Term {
  private static int temporals = 0;
  public String name;
  public Term subterm;
  public Principal cratedBy; // null for long term variables
  public VariableSort sort;

  public Variable(String name) {
    this.name = name;
    this.subterm = null;
    this.cratedBy = null;
    this.sort = VariableSort.NOSORT;
  }

  public Variable(String name, VariableSort sort) {
    this.name = name;
    this.subterm = null;
    this.cratedBy = null;
    this.sort = sort;
  }

  @Override public boolean equals(Object term) {
    if (!(term instanceof Term)) {
      return false;
    }
    
    return this.deconstructTerm() == ((Term)term).deconstructTerm();
  }

  public static Variable nextTemporal(){
    String tName = Constants.TEMPORAL + temporals;
    temporals ++;
    return new Variable(tName, VariableSort.TEMPORAL);
  }

  @Override public Term deconstructTerm() {
    if (subterm != null) {
      return subterm.deconstructTerm();
    }
    return this;
  }

  @Override public List<Variable> unify(Term right) {
    Term deconstructed = right.deconstructTerm();
    if (this != deconstructed) {
      this.subterm = deconstructed;
    }
    return Arrays.asList(this);
  }

  @Override public List<Term> extractKnowledge() {
    return Arrays.asList(this);
  }

  @Override public String render(){
    return Constants.sortString(sort) + name;
  }

  @Override public String renderLemma() {
    return name;
  }

  @Override public void removeFresh() {
    if (subterm != null) {
      subterm.removeFresh();
    }
    if (sort == VariableSort.FRESH) {
      sort = VariableSort.NOSORT;
    }
  }
}
