package simple_tamarin.dataStructures;

import java.util.ArrayList;

public class ProtocolModel {
  public ArrayList<Principal> principals;

  public ProtocolModel(){
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
   * does NOT check for name collision, for that use addIfPrincipal
   * @return newly created principal with name==name
   */
  public Principal addPrincipal(String name) {
    Principal principal = new Principal(name);
    principals.add(principal);
    return principal;
  }

  /**
   * Returns pointer to principal with name==name or adds a new one if it doesn't exist.
   * @return principal with name==name (existing or new)
   */
  public Principal addIfPrincipal(String name) {
    Principal principal = findPrincipal(name);
    if (principal == null) {
      principal = addPrincipal(name);
    }
    return principal;
  }
}
