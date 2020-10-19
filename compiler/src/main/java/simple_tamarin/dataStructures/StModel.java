package simple_tamarin.dataStructures;

import java.util.ArrayList;

/**
 * Simple_tamarin Model
 */
public class StModel {
  public ArrayList<Principal> principals;
  public ArrayList<Variable> pubVariables; // long term public variables

  public StModel(){
    this.principals = new ArrayList<>();
    this.pubVariables = new ArrayList<>();
  }

  /**
   * @return principal with name==name or void if it doesn't exist
   */
  public Principal findPrincipal(String name){
    for (Principal principal : principals) {
      if (principal.name.equals(name)) {
        return principal;
      }
    }
    return null;
  };

  /**
   * does NOT check for name collisions
   * @return newly created principal with name==name
   */
  public Principal addPrincipal(String name) {
    Principal principal = new Principal(name);
    principals.add(principal);
    return principal;
  }

  /**
   * @return variable with given parameters or void if it doesn't exist
   */
  public Variable findVariable(String name){
    for (Variable variable : pubVariables) {
      if (variable.name.equals(name)) {
        return variable;
      }
    }
    return null;
  };

  /**
   * @return variable with given parameters or void if it doesn't exist
   */
  public Variable findVariable(String name, Principal createdBy){
    for (Variable variable : pubVariables) {
      if (variable.name.equals(name) && variable.cratedBy == createdBy) {
        return variable;
      }
    }
    return null;
  };
}
