package simple_tamarin.dataStructures;

import java.util.ArrayList;

/**
 * Simple_tamarin Model
 */
public class StModel {
  public ArrayList<Principal> principals;
  public ArrayList<Variable> variables;

  public StModel(){
    this.principals = new ArrayList<>();
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
   * @return variable with name==name or void if it doesn't exist
   */
  public Variable findVariable(String name){
    for (Variable variable : variables) {
      if (variable.name.equals(name)) {
        return variable;
      }
    }
    return null;
  };

  /**
   * does NOT check for name collisions
   * @return newly created Variable with name==name
   */
  public Variable addVariable(String name, boolean pub) {
    Variable variable = new Variable(name, pub);
    variables.add(variable);
    return variable;
  }
}
