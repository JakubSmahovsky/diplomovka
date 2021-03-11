package simple_tamarin.dataStructures;

import java.util.ArrayList;

import simple_tamarin.dataStructures.command.*;
import simple_tamarin.dataStructures.term.Term;
import simple_tamarin.dataStructures.term.Variable;

/**
 * Simple_tamarin Block
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
  public ArrayList<Term> state;
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
      if (!state.contains(term)) {
        state.add(term);
      }
    }
    rangeEnd = block.rangeEnd;
  }

  public ArrayList<Term> completeState() {
    ArrayList<Term> result = new ArrayList<>();
    result.addAll(principal.composeInitState());
    result.addAll(state);
    return result;
  }
}
