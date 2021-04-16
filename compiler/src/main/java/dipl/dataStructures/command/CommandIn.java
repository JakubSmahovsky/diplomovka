package dipl.dataStructures.command;

import dipl.BuilderFormatting;
import dipl.Constants;
import dipl.dataStructures.Block;
import dipl.dataStructures.term.Term;
import dipl.dataStructures.term.Variable;

/**
 * A class holding information about a use of Tamarin's command In(..).
 */
public class CommandIn {
  private final Term term;
  private final Block block;
  
  public CommandIn(Term term, Block block){
    this.term = term;
    this.block = block;
  }

  /**
   * Check if the variable specified is among the ones received by this command.
   * Compares by "=="
   */
  public boolean receivedVariable(Variable variable) {
    for (Variable received : term.extractKnowledge()) {
      if (received == variable) {
        return true;
      }
    }
    return false;
  }

  public String render() {
    return BuilderFormatting.fact(Constants.COMMAND_IN, term, block);
  }
}
