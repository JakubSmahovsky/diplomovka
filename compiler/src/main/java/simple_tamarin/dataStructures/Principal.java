package simple_tamarin.dataStructures;

import java.util.ArrayList;

public class Principal {
  public String name;
  public ArrayList<Variable> variables;
  public ArrayList<StBlock> blocks;
  public StBlock nextBlock;

  public Principal(String name){
    this.name = name;
    this.variables = new ArrayList<>();
    this.blocks = new ArrayList<>();
    this.nextBlock = new StBlock(this);
  };

  /**
   * @return true if principal knows a variable called name
   */
  public boolean knows(String name) {
    for (Variable variable : variables) {
      if (variable.name.equals(name)) {
        return true;
      }
    }
    return false;
  }
}