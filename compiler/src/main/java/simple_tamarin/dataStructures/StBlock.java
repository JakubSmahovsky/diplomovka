package simple_tamarin.dataStructures;

import java.util.ArrayList;

import simple_tamarin.dataStructures.term.Variable;

/**
 * Simple_tamarin Block
 */
public class StBlock {
  public Principal principal;
  public ArrayList<Variable> aliases;
  public ArrayList<Command> premise;
  public ArrayList<Command> result;
  public ArrayList<Variable> finalState;
  
  public StBlock(Principal principal){
    this.principal = principal;
    this.aliases = new ArrayList<>();
    this.premise = new ArrayList<>();
    this.result = new ArrayList<>();
  }
}
