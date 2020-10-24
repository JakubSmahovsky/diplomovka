package simple_tamarin.dataStructures;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;

public class Variable {
  private static int temporals = 0;
  public String name;
  public Principal cratedBy; // null for long term variables
  public VariableSort sort;

  public Variable(String name, VariableSort sort) {
    this.name = name;
    this.cratedBy = null;
    this.sort = sort;
  }

  public Variable(String name, Principal createdBy, VariableSort sort) {
    this.name = name;
    this.cratedBy = createdBy;
    this.sort = sort;
  }

  public static Variable nextTemporal(){
    String tName = Constants.TEMPORAL + temporals;
    temporals ++;
    return new Variable(tName, VariableSort.TEMPORAL);
  }
}
