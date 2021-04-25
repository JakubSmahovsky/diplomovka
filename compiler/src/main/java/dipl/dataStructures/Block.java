package dipl.dataStructures;

import java.util.ArrayList;

import dipl.Constants;
import dipl.dataStructures.command.*;
import dipl.dataStructures.term.Term;
import dipl.dataStructures.term.Variable;

public class Block {
  public final Model model;
  public final int indexInModel;
  public final Principal principal;

  public ArrayList<Variable> unaryEqualsPending;
  public ArrayList<Variable> aliases;
  public ArrayList<Deconstruction> deconstructed;
  public ArrayList<CommandFr> fresh;
  public ArrayList<CommandIn> inputs;
  public ArrayList<Fact> actions;
  public ArrayList<CommandOut> outputs;
  public ArrayList<Term> state;
  public int rangeBegin;
  public int rangeEnd;
  
  /**
   * @param blockNo the block's number for this principal counting from 1
   */
  public Block(Model model, Principal principal, int blockNo){
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
    this.rangeBegin = blockNo;
    this.rangeEnd = blockNo;
  }

  /**
   * @param blockNo the block's number for this principal counting from 1
   */
  public Block(Model model, Principal principal, int blockNo, ArrayList<Term> state){
    this(model, principal, blockNo);
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
   * Some objects may be refecencing the provided block,
   * that is why the provided block needs to "become" this block after merging.
   */
  public void merge(Block block) {
    aliases.addAll(block.aliases);
    deconstructed.addAll(block.deconstructed);
    for (CommandFr fr : block.fresh) {
      fr.block = this;
      fresh.add(fr);
    }
    for (CommandIn in : block.inputs) {
      in.block = this;
      inputs.add(in);
    }
    actions.addAll(block.actions);
    for (CommandOut out : block.outputs) {
      out.block = this;
      outputs.add(out);
    }
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
