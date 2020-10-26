package simple_tamarin.dataStructures;

import java.util.ArrayList;

import simple_tamarin.dataStructures.term.Variable;

/**
 * Simple_tamarin Model
 */
public class StModel {
  public ArrayList<Principal> principals;
  public ArrayList<Variable> pubVariables; // long term public variables
  public Queries queries;
  public Builtins builtins;

  public StModel(){
    this.principals = new ArrayList<>();
    this.pubVariables = new ArrayList<>();
    this.queries = new Queries();
    this.builtins = new Builtins();
  }

  /**
   * @return principal with given parameters or null if it doesn't exist
   */
  public Principal findPrincipal(String name){
    for (Principal principal : principals) {
      if (principal.name.equals(name)) {
        return principal;
      }
    }
    return null;
  }

  /**
   * does NOT check for name collisions
   * @return newly created principal with given parameters
   */
  public Principal addPrincipal(String name) {
    Principal principal = new Principal(name);
    principals.add(principal);
    return principal;
  }

  /**
   * Finds a long term public variable with given parameters
   * @return variable with given parameters or null if it doesn't exist
   */
  public Variable findVariable(String name){
    for (Variable variable : pubVariables) {
      if (variable.name.equals(name)) {
        return variable;
      }
    }
    return null;
  }
}
