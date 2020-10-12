package simple_tamarin.dataStructures;

public class Command {
  public static int in = 1;
  public static int out = 2;  
  public static int fr = 3;
  
  public int type;
  public Variable variable;
  public Command(int type, Variable variable){
    this.type = type;
    this.variable = variable;
  };
}
