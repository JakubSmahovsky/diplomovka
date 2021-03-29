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
  public ArrayList<CommandFr> premiseFresh;
  public ArrayList<CommandIn> premiseInputs;
  public ArrayList<Fact> actions;
  public ArrayList<CommandOut> resultOutputs;
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
    this.premiseFresh = new ArrayList<>();
    this.premiseInputs = new ArrayList<>();
    this.actions = new ArrayList<>();
    this.resultOutputs = new ArrayList<>();
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
    return principal.render() + Constants.NAMES_SEPARATOR + indexInModel;
  }

  /**
   * Merges the provided block AFTER this block.
   */
  public void merge(STBlock block) {
    aliases.addAll(block.aliases);
    deconstructed.addAll(block.deconstructed);
    premiseFresh.addAll(block.premiseFresh);
    premiseInputs.addAll(block.premiseInputs);
    actions.addAll(block.actions);
    resultOutputs.addAll(block.resultOutputs);
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
    return (premiseFresh.isEmpty() && 
            premiseInputs.isEmpty() && 
            actions.isEmpty() && 
            resultOutputs.isEmpty());
  }
}
