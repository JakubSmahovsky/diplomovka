package simple_tamarin.dataStructures.command;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.dataStructures.term.Term;

/**
 * A class holding information about a use of Tamarin's command function "In(..)".
 */
public class CommandIn {
  private final Term term;
  private final STBlock block;
  
  public CommandIn(Term term, STBlock block){
    this.term = term;
    this.block = block;
  }

  public String render() {
    return BuilderFormatting.fact(Constants.COMMAND_IN, term, block);
  }
}
