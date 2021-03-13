package simpleT.dataStructures.command;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.term.Term;

/**
 * A class holding information about a use of Tamarin's command function "Out(..)".
 */
public class CommandOut {
  private final Term term;
  private final STBlock block;
  
  public CommandOut(Term term, STBlock block){
    this.term = term;
    this.block = block;
  }

  public String render() {
    return BuilderFormatting.fact(Constants.COMMAND_OUT, term, block);
  }
}
