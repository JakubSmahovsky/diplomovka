package simple_tamarin.dataStructures.term;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Principal;

public class Variable extends Term {
  private static int temporals = 0;
  public String name;
  public Term subterm;
  public Principal cratedBy; // null for long term variables
  public VariableSort sort;

  public Variable(String name, VariableSort sort) {
    this.name = name;
    this.subterm = null;
    this.cratedBy = null;
    this.sort = sort;
  }

  public Variable(String name, Principal createdBy, VariableSort sort) {
    this.name = name;
    this.subterm = null;
    this.cratedBy = createdBy;
    this.sort = sort;
  }

  public Variable(String name, Term subterm, Principal createdBy, VariableSort sort) {
    this.name = name;
    this.subterm = subterm;
    this.cratedBy = createdBy;
    this.sort = sort;
  }

  @Override public String toString() {
    return name;
  }

  @Override public boolean equals(Term term) {
    if (this == term) {
      return true;
    }
    if (subterm != null && subterm.equals(term)) {
      return true;
    }
    if (term.getClass() == Variable.class && ((Variable)term).subterm != null && ((Variable)term).subterm.equals(this)) {
      return true;
    }
    return false;
  }

  public static Variable nextTemporal(){
    String tName = Constants.TEMPORAL + temporals;
    temporals ++;
    return new Variable(tName, VariableSort.TEMPORAL);
  }
}
