package simpleT.dataStructures;

import java.util.ArrayList;

import simpleT.Constants;
import simpleT.dataStructures.command.*;
import simpleT.dataStructures.term.Term;
import simpleT.dataStructures.term.Variable;

/**
 * SimpleT Block
 */
public class STBlock {
  public final STModel model;
  public final int indexInModel;
  public final Principal principal;

  public ArrayList<Variable> unaryEqualsPending;
  public ArrayList<Variable> aliases;
  public ArrayList<Deconstruction> deconstructed;
  public ArrayList<CommandFr> fresh;
  public ArrayList<CommandIn> inputs;
  public ArrayList<Fact> actions;
  public ArrayList<CommandOut> outputs;
  private final ArrayList<Term> state;
  public int rangeBegin;
  public int rangeEnd;
  
  public STBlock(STModel model, Principal principal, int index){
    this.model = model;
    this.indexInModel = model.registerBlock(this);
    this.principal = principal;

    this.unaryEqualsPending = new ArrayList<>();
    this.aliases = new ArrayList<>();
    this.deconstructed = new ArrayList<>();
    this.fresh = new ArrayList<>();
    this.inputs = new ArrayList<>();
    this.actions = new ArrayList<>();
    this.outputs = new ArrayList<>();
    this.state = new ArrayList<>();
    this.rangeBegin = index;
    this.rangeEnd = index;
  }

  public STBlock(STModel model, Principal principal, int index, ArrayList<Term> state){
    this(model, principal, index);
    this.state.addAll(state);
  }

  /**
  * Render the label of this block.
  */
  public String render() {
    return principal.render() + Constants.NAME_SEPARATOR + indexInModel;
  }

  /**
   * Merges the provided block AFTER this block.
   */
  public void merge(STBlock block) {
    aliases.addAll(block.aliases);
    deconstructed.addAll(block.deconstructed);
    fresh.addAll(block.fresh);
    inputs.addAll(block.inputs);
    actions.addAll(block.actions);
    outputs.addAll(block.outputs);
    for (Term term : block.state) {
      addToState(term);
    }
    rangeEnd = block.rangeEnd;
  }

  public ArrayList<Term> completeState() {
    ArrayList<Term> result = new ArrayList<>();
    result.addAll(principal.composeSessionState());
    result.addAll(state);
    return result;
  }

  public ArrayList<Term> getState() {
    return state;
  }

  public void addToState(Term term) {
    if (!Term.containsByObjectEquality(state, term)) {
      state.add(term);
    }
  }

  public boolean isEmptyBlock() {
    return (fresh.isEmpty() && 
            inputs.isEmpty() && 
            actions.isEmpty() && 
            outputs.isEmpty());
  }
}
