package simple_tamarin.dataStructures;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.term.Term;

public class Command {
  public Constants.CommandType type;
  public Term term;
  
  public Command(Constants.CommandType type, Term term){
    this.type = type;
    this.term = term;
  }
}
