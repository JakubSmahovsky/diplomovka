package simple_tamarin.dataStructures.term;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Principal;

public class Variable implements Term {
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
}
