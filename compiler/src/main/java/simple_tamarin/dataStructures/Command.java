package simple_tamarin.dataStructures;

import simple_tamarin.Constants;

public class Command {
  public Constants.CommandType type;
  public Variable variable;
  public Command(Constants.CommandType type, Variable variable){
    this.type = type;
    this.variable = variable;
  };
}
