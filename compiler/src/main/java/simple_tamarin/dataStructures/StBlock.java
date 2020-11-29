package simple_tamarin.dataStructures;

import java.util.ArrayList;

import simple_tamarin.dataStructures.term.Term;

/**
 * Simple_tamarin Block
 */
public class StBlock {
  public Principal principal;
  public ArrayList<Alias> aliases;
  public ArrayList<Deconstruction> deconstructed;
  public ArrayList<Command> premise;
  public ArrayList<Fact> actions;
  public ArrayList<Command> result;
  public ArrayList<Term> state;
  public int rangeBegin;
  public int rangeEnd;
  
  public StBlock(Principal principal, int index){
    this.principal = principal;
    this.aliases = new ArrayList<>();
    this.deconstructed = new ArrayList<>();
    this.premise = new ArrayList<>();
    this.actions = new ArrayList<>();
    this.result = new ArrayList<>();
    this.state = new ArrayList<>();
    this.rangeBegin = index;
    this.rangeEnd = index;
  }

  public StBlock(Principal principal, int index, ArrayList<Term> state){
    this(principal, index);
    this.state.addAll(state);
  }

  /**
   * Merges the provided block AFTER this block.
   */
  public void merge(StBlock block) {
    aliases.addAll(block.aliases);
    deconstructed.addAll(block.deconstructed);
    premise.addAll(block.premise);
    actions.addAll(block.actions);
    result.addAll(block.result);
    for (Term term : block.state) {
      if (!state.contains(term)) {
        state.add(term);
      }
    }
    rangeEnd = block.rangeEnd;
  }

  public ArrayList<Term> completeState() {
    ArrayList<Term> result = new ArrayList<>();
    result.addAll(principal.initState);
    result.addAll(state);
    return result;
  }
}
