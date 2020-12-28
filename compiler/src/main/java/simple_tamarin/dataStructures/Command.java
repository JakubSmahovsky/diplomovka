package simple_tamarin.dataStructures;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.term.Term;
import simple_tamarin.errors.Errors;

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
        return Constants.COMMANDIN;
      case OUT:
        return Constants.COMMANDOUT;
      case FRESH:
        return Constants.COMMANDFRESH;
      default:
        Errors.DebugCommandType("null", "Command.toString");
        return null;
    }
  }
}
