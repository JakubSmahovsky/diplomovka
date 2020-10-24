package simple_tamarin.dataStructures;

import java.util.ArrayList;

/**
 * Simple_tamarin Block
 */
public class StBlock {
  public Principal principal;
  public ArrayList<Command> premise;
  public ArrayList<Command> result;
  public ArrayList<Variable> finalState;
  
  public StBlock(Principal principal){
    this.principal = principal;
    this.premise = new ArrayList<>();
    this.result = new ArrayList<>();
  }
}
