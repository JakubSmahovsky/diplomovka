package simpleT.sourcesCompiler.graph.node;

import simpleT.errors.Errors;
import simpleT.sourcesCompiler.graph.Description;

/**
 * A node for generating fresh values, which Tamarin
 * doesn't even display on the website.
 */
public class FreshNode extends Node{
  public FreshNode(String id) {
    super(id, "Fresh");
  }

  @Override
  public String render(){
    return this.label;
  }

  @Override
  public Description renderDescription() {
    // Fresh node is always above a Fresh Rule, no point rendering both
    Errors.DebugUnexpectedCall("renderDescription", "FreshNode");
    return null;
  }
}
