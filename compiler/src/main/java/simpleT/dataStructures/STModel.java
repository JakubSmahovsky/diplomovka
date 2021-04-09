package simpleT.dataStructures;

import java.util.ArrayList;
import java.util.Arrays;

import simpleT.Constants;
import simpleT.dataStructures.query.Query;
import simpleT.dataStructures.term.Variable;
import simpleT.sourcesCompiler.Source;
import simpleT.sourcesCompiler.SourceGroup;

/**
 * SimpleT Model
 */
public class STModel {
  private final ArrayList<Principal> principals;
  public final ArrayList<Query> queries;
  public Builtins builtins;
  public final Variable instanceID;
  public final Variable sessionID;

  public ArrayList<SourceGroup> sourceGroups;

  // lists of all objects of type
  private final ArrayList<Variable> variables;
  public final ArrayList<STBlock> blocks;
  private final ArrayList<Source> sources;

  public STModel(){
    this.principals = new ArrayList<>();
    this.queries = new ArrayList<>();
    this.builtins = new Builtins();

    this.sourceGroups = new ArrayList<>();

    this.variables = new ArrayList<>();
    this.blocks = new ArrayList<>();
    this.sources = new ArrayList<>();

    instanceID = new Variable(this, Constants.ST_INSTANCEID);
    sessionID = new Variable(this, Constants.ST_SESSIONID);
  }

  /**
   * @return principal with given parameters or null if it doesn't exist
   */
  public Principal findPrincipal(String name){
    for (Principal principal : principals) {
      if (principal.nameEquals(name)) {
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
    Variable principalID = new Variable(this, name);
    Principal principal = new Principal(this, principalID, name);
    principals.add(principal);
    return principal;
  }

  /**
   * @return public variable with given name among all the principal's knowledge
   */
  public Variable findPublic(String name){
    Variable result;
    for (Principal principal : principals) {
      result = principal.knowsPublic(name);
      if (result != null) {
        return result;
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
    int sourceNo = 1;
    for (SourceGroup group : sourceGroups) {
      for (Source source : group.sources) {
        source.number = sourceNo++;
      }
    } 
  }

  public int registerVariable(Variable variable) {
    int index = variables.size();
    variables.add(variable);
    return index;
  }

  public int registerBlock(STBlock block) {
    int index = blocks.size();
    blocks.add(block);
    return index;
  }

  public void registerSource(Source source) {
    sources.add(source);
  }

  public Variable getVariable(int id) {
    if (variables.size() > id) {
      return variables.get(id);
    }
    return null;
  }
}
