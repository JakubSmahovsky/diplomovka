package simple_tamarin.dataStructures;

import java.util.ArrayList;
import java.util.Arrays;

import simple_tamarin.Constants;
import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.query.Queries;
import simple_tamarin.dataStructures.term.Variable;
import simple_tamarin.sourcesCompiler.Source;
import simple_tamarin.sourcesCompiler.SourceGroup;

/**
 * Simple_tamarin Model
 */
public class STModel {
  private ArrayList<Principal> principals;
  public ArrayList<Variable> pubVariables; // long term public variables
  public Queries queries;
  public Builtins builtins;
  public final Variable runID = new Variable(Constants.VARIABLE_RUNID);

  public ArrayList<SourceGroup> sourceGroups;

  // lists of all objects of type
  public ArrayList<STBlock> blocks;
  public ArrayList<Source> sources;

  public STModel(){
    this.principals = new ArrayList<>();
    this.pubVariables = new ArrayList<>();
    this.queries = new Queries();
    this.builtins = new Builtins();

    this.sourceGroups = new ArrayList<>();

    this.blocks = new ArrayList<>();
    this.sources = new ArrayList<>();
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
    Variable principalID = new Variable(name, VariableSort.PUBLIC);
    pubVariables.add(principalID);
    Principal principal = new Principal(this, principalID, name);
    principals.add(principal);
    return principal;
  }

  /**
   * Finds a long term public variable with given parameters
   * @return variable with given parameters or null if it doesn't exist
   */
  public Variable findVariable(String name){
    for (Variable variable : pubVariables) {
      if (variable.equalsByName(name)) {
        return variable;
      }
    }
    return null;
  }

  /**
   * Getter for private list of principals to indicate that changing
   * should be done via methods of this class instead of directly.
   */
  public ArrayList<Principal> getPrincipals() {
    return principals;
  }

  public void sortSourceGroups() {
    SourceGroup array[] = sourceGroups.toArray(new SourceGroup[0]);
    Arrays.sort(array);
    sourceGroups = new ArrayList<>(Arrays.asList(array));
  }

  public int registerBlock(STBlock block) {
    int index = blocks.size();
    blocks.add(block);
    return index;
  }

  public int registerSource(Source source) {
    int index = sources.size();
    sources.add(source);
    return index;
  }
}
