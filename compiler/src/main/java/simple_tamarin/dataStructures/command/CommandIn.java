package simple_tamarin.dataStructures.command;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.dataStructures.term.Term;

/**
 * A class holding information about a use of Tamarin's command function "In(..)".
 */
public class CommandIn {
  private final Term term;
  private final StBlock block;
  
  public CommandIn(Term term, StBlock block){
    this.term = term;
    this.block = block;
  }

  public String render() {
    return BuilderFormatting.fact(Constants.COMMAND_IN, term, block);
  }
}
