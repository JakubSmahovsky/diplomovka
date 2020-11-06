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
  public int rangeBegin;
  public int rangeEnd;
  
  public StBlock(Principal principal, int index){
    this.principal = principal;
    this.aliases = new ArrayList<>();
    this.premise = new ArrayList<>();
    this.result = new ArrayList<>();
    this.rangeBegin = index;
    this.rangeEnd = index;
  }

  /**
   * Merges the provided block AFTER this block. FinalState is NOT merged
   */
  public void merge(StBlock block) {
    aliases.addAll(block.aliases);
    premise.addAll(block.premise);
    result.addAll(block.result);
    rangeEnd = block.rangeEnd;
  }
}
