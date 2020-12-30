package simple_tamarin.dataStructures;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.term.Term;
import simple_tamarin.errors.Errors;

/**
 * A class holding information about "special" Tamarin functions.
 * These are only "In", "Out", "Fr" and it's unlikely other will be added.
 * They are all unary, so a simple term is enough to hold arguments.
 */
public class Command {
  public Constants.CommandType type;
  public Term term;
  
  public Command(Constants.CommandType type, Term term){
    this.type = type;
    this.term = term;
  }

  @Override public String toString() {
    switch (type) {
      case IN: 
        return Constants.COMMAND_IN;
      case OUT:
        return Constants.COMMAND_OUT;
      case FRESH:
        return Constants.COMMAND_FRESH;
      default:
        Errors.DebugCommandType("null", "Command.toString");
        return null;
    }
  }
}
