package dipl.dataStructures.command;

import dipl.BuilderFormatting;
import dipl.Constants;
import dipl.dataStructures.Block;
import dipl.dataStructures.term.Term;
import dipl.dataStructures.term.Variable;

/**
 * A class holding information about a use of Tamarin's command Out(..).
 */
public class CommandOut {
  private final Term term;
  private final Block block;
  
  public CommandOut(Term term, Block block){
    this.term = term;
    this.block = block;
  }

  /**
   * Check if the variable specified is among the ones sent by this command.
   * Compares by "=="
   */
  public boolean sentVariable(Variable variable) {
    for (Variable sent : term.extractKnowledge()) {
      if (sent == variable) {
        return true;
      }
    }
    return false;
  }

  public String render() {
    return BuilderFormatting.fact(Constants.COMMAND_OUT, term, block);
  }
}
