package simple_tamarin.dataStructures;

import simple_tamarin.Constants.VariableSort;

public class Variable {
  public String name;
  public Principal cratedBy; // null for long term variables
  public VariableSort sort;

  public Variable(String name, VariableSort sort) {
    this.name = name;
    this.cratedBy = null;
    this.sort = sort;
  }
}
