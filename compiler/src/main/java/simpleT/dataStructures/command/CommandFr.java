package simpleT.dataStructures.command;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.term.Variable;

/**
 * A class holding information about a use of Tamarin's command function "Fr(..)".
 */
public class CommandFr {
  private final Variable variable;
  private final STBlock block;
  
  public CommandFr(Variable variable, STBlock block){
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
