package dipl.dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

import dipl.dataStructures.term.Variable;

public class Principal {
  public final Model model;
  public final Variable principalID;

  private final String name;
  private final ArrayList<Variable> knownEphemeralPrivate;
  private final ArrayList<Variable> knownLongTermPrivate;
  private final ArrayList<Variable> knownPublic;
  private ArrayList<Block> blocks;
  public Block nextBlock;
  // list of variables that the principal delcared himself
  private final ArrayList<Variable> declaredFresh;
  private final ArrayList<Variable> declaredPublic;

  public Principal(Model model, Variable principalID, String name){
    this.model = model;
    this.principalID = principalID;

    this.name = name;
    knownEphemeralPrivate = new ArrayList<>();
    knownLongTermPrivate = new ArrayList<>();
    knownPublic = new ArrayList<>();
    knownPublic.add(principalID);
    blocks = new ArrayList<>();
    nextBlock();
    declaredFresh = new ArrayList<>();
    declaredPublic = new ArrayList<>();
  }

  /**
  * @return ANY known variable with given name or null if principal doesn't know it
  */
  public Variable knowsAnyVariableByName(Variable variable) {
    Variable known = knowsEphemeralPrivateByName(variable);
    if (known != null) {
      return known;
    }
    known = knowsLongTermPrivateByName(variable);
    if (known != null) {
      return known;
    }
    return knowsPublicByName(variable);
  }

  /**
   * @return known ephemeral private variable with given name or null if principal doesn't know it
   */
  public Variable knowsEphemeralPrivate(String name) {
    for (Variable variable : knownEphemeralPrivate) {
      if (variable.equalsByName(name)) {
        return variable;
      }
    }
    return null;
  }

  /**
   * Finds the variable among known ephemeral private variables, does NOT compare normal forms, only names
   * @return known variable with the same name or null if principal doesn't know it
   */
  public Variable knowsEphemeralPrivateByName(Variable variable) {
    for (Variable known : knownEphemeralPrivate) {
      if (known.equalsByName(variable)) {
        return known;
      }
    }
    return null;
  }

  /**
   * @return known long-term private variable with given name or null if principal doesn't know it
   */
  public Variable knowsLongTermPrivate(String name) {
    for (Variable variable : knownLongTermPrivate) {
      if (variable.equalsByName(name)) {
        return variable;
      }
    }
    return null;
  }

  /**
   * Finds the variable among known long-term private variables, does NOT compare normal forms, only names
   * @return known variable with the same name or null if principal doesn't know it
   */
  public Variable knowsLongTermPrivateByName(Variable variable) {
    for (Variable known : knownLongTermPrivate) {
      if (known.equalsByName(variable)) {
        return known;
      }
    }
    return null;
  }

  /**
   * @return known long term public variable with given name or null if principal doesn't know it
   */
  public Variable knowsPublic(String name) {
    for (Variable variable : knownPublic) {
      if (variable.equalsByName(name)) {
        return variable;
      }
    }
    return null;
  }

  /**
   * Finds the variable among known public variables, does NOT compare normal forms, only names
   * @return known variable with the same name or null if principal doesn't know it
   */
  public Variable knowsPublicByName(Variable variable) {
    for (Variable known : knownPublic) {
      if (known.equalsByName(variable)) {
        return known;
      }
    }
    return null;
  }

  public void learnEphemeralPrivate(Variable variable, boolean baseVariableFromGenerates) {
    if (baseVariableFromGenerates) {
      declaredFresh.add(variable);
    }
    if (knowsEphemeralPrivateByName(variable) == null) {
      knownEphemeralPrivate.add(variable);
    }
  }

  public void learnLongTermPrivate(Variable variable) {
    declaredFresh.add(variable);
    if (knowsLongTermPrivateByName(variable) == null) {
      knownLongTermPrivate.add(variable);
    }
  }

  public void learnPublic(Variable variable, boolean baseVariableFromKnows) {
    if (baseVariableFromKnows) {
      declaredPublic.add(variable);
    }
    if (knowsPublicByName(variable) == null) {
      knownPublic.add(variable);
    }
  }

  public ArrayList<Variable> composeInstanceState() {
    ArrayList<Variable> result = new ArrayList<>();
    result.add(model.instanceID);
    result.addAll(knownPublic);
    result.addAll(knownLongTermPrivate);
    return result;
  }

  public ArrayList<Variable> composeSessionState() {
    ArrayList<Variable> result = new ArrayList<>();
    result.add(model.instanceID);
    result.add(model.sessionID);
    result.addAll(knownPublic);
    result.addAll(knownLongTermPrivate);
    return result;
  }

  public ArrayList<Variable> getLongTermPrivate() {
    return knownLongTermPrivate;    
  }

  /**
   * Pushes the current nextBlock (if it exists) to the list of blocks and initialises a new nextBlock
   */
  public void nextBlock() {
    if (nextBlock != null) {
      blocks.add(nextBlock);
      nextBlock = new Block(model, this, blocks.size(), nextBlock.getState());
    } else {
      nextBlock = new Block(model, this, 0);
    }
    return;
  }

  /**
   * Squishes blocks that don't send or receive any messages, merging them with neighbouring blocks.
   */
  public void squishBlocks(){
    if (blocks.isEmpty()) {
      return;
    }
    ArrayList<Block> newBlocks = new ArrayList<>();
    Iterator<Block> it = blocks.iterator();
    Block last = it.next();
    while (it.hasNext()) {
      Block following = it.next();
      boolean sends = !last.outputs.isEmpty();
      if (sends) {
        newBlocks.add(last);
        last = following;
      } else { // merge
        last.merge(following);
      }
    }
    
    if (!last.isEmptyBlock()) {
      newBlocks.add(last);
    }
    blocks = newBlocks;
  }

  public boolean nameEquals(String name) {
    return this.name.equals(name);
  }

  /**
   * Render the principal's identifier for use in fact and rule labels.
   */
  public String render() {
    return principalID.renderFactName();
  }

  public String renderOutput() {
    return name;
  }

  public ArrayList<Variable> getKnownPublic() {
    return knownPublic;
  }

  public ArrayList<Block> getBlocks() {
    return blocks;
  }

  public Block getLastBlock() {
    return blocks.get(blocks.size()-1);
  }

  public boolean doesNothing() {
    for (Block block : blocks) {
      if (!block.isEmptyBlock()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Add sorts, so that one of this principal's blocks can be rendered.
   */
  public void addSorts() {
    for (Variable variable : declaredFresh) {
      variable.addFresh();
    }
    for (Variable variable : declaredPublic) {
      variable.addPublic();
    }
  }

  public void removeSorts() {
    for (Variable variable : declaredFresh) {
      variable.removeFresh();
    }
    for (Variable variable : declaredPublic) {
      variable.removePublic();
    }
  }
}