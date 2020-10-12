package simple_tamarin.dataStructures;

import java.util.ArrayList;

/**
 * Simple_tamarin Block
 */
public class StBlock {
  public Principal principal;
  public ArrayList<Command> commands;
  public StBlock(Principal principal){
    this.principal = principal;
    this.commands = new ArrayList<>();
  }
}
