package dipl.dataStructures.command;

import dipl.BuilderFormatting;
import dipl.Constants;
import dipl.dataStructures.Block;
import dipl.dataStructures.term.Variable;

/**
 * A class holding information about a use of Tamarin's command Fr(..).
 */
public class CommandFr {
  private final Variable variable;
  public Block block;
  
  public CommandFr(Variable variable, Block block){
    this.variable = variable;
    this.block = block;
  }

  public void addFresh() {
    variable.addFresh();
  }

  public void removeFresh() {
    variable.removeFresh();
  }

  public String render() {
    return BuilderFormatting.fact(Constants.COMMAND_FRESH, variable, block);
  }
}
