package simple_tamarin.dataStructures;

import java.util.ArrayList;

public class Principal {
  public String name;
  public ArrayList<Variable> variables;
  public ArrayList<StBlock> blocks;
  public StBlock nextBlock;
  public ArrayList<Variable> initState;

  public Principal(String name){
    this.name = name;
    this.variables = new ArrayList<>();
    this.blocks = new ArrayList<>();
    this.nextBlock = new StBlock(this);
    this.initState = new ArrayList<>();
  };

  /**
   * @return known variable with given parameters or null if it doesn't exist
   */
  public Variable findVariable(String name) {
    for (Variable variable : variables) {
      if (variable.name.equals(name)) {
        return variable;
      }
    }
    return null;
  }  
}