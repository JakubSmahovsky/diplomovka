package simple_tamarin.dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

import simple_tamarin.dataStructures.term.Variable;

public class Principal {
  public final STModel model;
  public final Variable principalID;

  public String name;
  private ArrayList<Variable> knowledge;
  private ArrayList<STBlock> blocks;
  public STBlock nextBlock;
  public ArrayList<Variable> initState;
  public ArrayList<Fact> initResults;

  public Principal(STModel model, Variable principalID, String name){
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
      if (variable.equalsByName(name)) {
        return variable;
      }
    }
    return null;
  }

  /**
   * Finds the variable among known variables, does NOT compare canonical forms, only names
   * @return known variable with the same name or null if principal doesn't know it
   */
  public Variable knows(Variable variable) {
    for (Variable known : knowledge) {
      if (known.equalsByName(variable)) {
        return known;
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
      nextBlock = new STBlock(model, this, blocks.size(), nextBlock.state);
    } else {
      nextBlock = new STBlock(model, this, 0);
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
    ArrayList<STBlock> newBlocks = new ArrayList<>();
    Iterator<STBlock> it = blocks.iterator();
    STBlock last = it.next();
    while (it.hasNext()) {
      STBlock following = it.next();
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

  public ArrayList<STBlock> getBlocks() {
    return blocks;
  }

  public STBlock getFirstBlock() {
    return blocks.get(0);
  }

  public STBlock getLastBlock() {
    return blocks.get(blocks.size()-1);
  }
}