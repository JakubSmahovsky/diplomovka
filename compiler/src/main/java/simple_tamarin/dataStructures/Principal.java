package simple_tamarin.dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

import simple_tamarin.dataStructures.term.Variable;

public class Principal {
  public final StModel model;
  public final Variable principalID;

  public String name;
  private ArrayList<Variable> knowledge;
  private ArrayList<StBlock> blocks;
  public StBlock nextBlock;
  public ArrayList<Variable> initState;
  public ArrayList<Fact> initResults;

  public Principal(StModel model, Variable principalID, String name){
    this.model = model;
    this.principalID = principalID;
    this.initState = new ArrayList<>();
    initState.add(model.runID);
    initState.add(principalID);

    this.name = name;
    this.knowledge = new ArrayList<>();
    this.blocks = new ArrayList<>();
    nextBlock();
    this.initResults = new ArrayList<>();
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
   * Add all variables from a transparent Term to knowledge.
   * Undefined bahavior for non-transparent Terms (likely crash).
   */
  public void learn(Variable variable) {
    if (!knowledge.contains(variable)) {
      knowledge.add(variable);
    }
  }

  /**
   * Pushes the current nextBlock (if it exists) to the list of blocks and initialises a new nextBlock
   */
  public void nextBlock() {
    if (nextBlock != null) {
      blocks.add(nextBlock);
      nextBlock = new StBlock(model, this, blocks.size(), nextBlock.state);
    } else {
      nextBlock = new StBlock(model, this, 0);
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
      boolean sends = !last.resultOutputs.isEmpty();
      boolean receives = !following.premiseInputs.isEmpty();
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

  public ArrayList<StBlock> getBlocks() {
    return blocks;
  }

  public StBlock getFirstBlock() {
    return blocks.get(0);
  }

  public StBlock getLastBlock() {
    return blocks.get(blocks.size()-1);
  }
}