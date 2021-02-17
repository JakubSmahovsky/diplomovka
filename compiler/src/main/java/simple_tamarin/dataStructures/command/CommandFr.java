package simple_tamarin.dataStructures.command;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.dataStructures.term.Variable;

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
