package simple_tamarin.dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

import simple_tamarin.Constants.CommandType;
import simple_tamarin.dataStructures.term.Variable;

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
    nextBlock();
    this.initState = new ArrayList<>();
  }

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

  /**
   * Pushes the current nextBlock (if it exists) to the list of blocks and initialises a new nextBlock
   */
  public void nextBlock() {
    if (nextBlock != null) {
      blocks.add(nextBlock);
    }
    nextBlock = new StBlock(this, blocks.size());
    return;
  }

  /**
   * Squishes blocks that don't send or don't receive any messages, merging them with neighbouging blocks.
   */
  public void squishBlocks(){
    if (blocks.isEmpty()) {
      return;
    }
    ArrayList<StBlock> newBlocks = new ArrayList<>();
    Iterator<StBlock> it = blocks.iterator();
    StBlock last = it.next();
    while (it.hasNext()) {      
      StBlock following = it.next();
      
      boolean sends = false;
      for (Command command : last.result) {
        if (command.type == CommandType.OUT) {
          sends = true;
          break;
        }
      }
      boolean receives = false;
      for (Command command : following.premise) {
        if (command.type == CommandType.IN) {
          receives = true;
          break;
        }
      }
      
      if (sends && receives) {
        newBlocks.add(last);
        last = following;
      } else { // merge
        last.merge(following);
      }
    }
    
    newBlocks.add(last);
    blocks = newBlocks;
  }

  @Override public String toString() {
    return name;
  }

}