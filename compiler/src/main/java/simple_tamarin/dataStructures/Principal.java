package simple_tamarin.dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

import simple_tamarin.Constants.CommandType;
import simple_tamarin.dataStructures.term.Term;
import simple_tamarin.dataStructures.term.Variable;

public class Principal {
  public String name;
  public ArrayList<Variable> knowledge;
  public ArrayList<StBlock> blocks;
  public StBlock nextBlock;
  public ArrayList<Variable> initState;

  public Principal(String name){
    this.name = name;
    this.knowledge = new ArrayList<>();
    this.blocks = new ArrayList<>();
    nextBlock();
    this.initState = new ArrayList<>();
  }

  /**
   * @return known variable with given name or null if principal doesn't know it
   */
  public Variable knows(String name) {
    for (Variable variable : knowledge) {
      if (variable.name.equals(name)) {
        return variable;
      }
    }
    return null;
  }

  /**
   * @return equivalent known term or null if principal doesn't know it
   */
  public Term knows(Term term) {
    for (Term known : knowledge) {
      if (known.equals(term)) {
        return known;
      }
    }
    return null;
  }

  /**
   * Add all variables from a transparent Term to knowledge.
   * Undefined bahavior for non-transparent Terms (likely crash).
   */
  public void learn(Term term) {
    for (Variable extracted : term.extractKnowledge()) {
      if (!knowledge.contains(extracted)) {
        knowledge.add(extracted);
      }
    }
  }

  /**
   * Pushes the current nextBlock (if it exists) to the list of blocks and initialises a new nextBlock
   */
  public void nextBlock() {
    if (nextBlock != null) {
      blocks.add(nextBlock);
      nextBlock = new StBlock(this, blocks.size(), nextBlock.state);
    } else {
      nextBlock = new StBlock(this, 0);
    }
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