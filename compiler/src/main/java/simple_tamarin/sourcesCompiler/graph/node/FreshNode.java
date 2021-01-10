package simple_tamarin.sourcesCompiler.graph.node;

/**
 * A node for generating fresh values, which Tamarin
 * doesn't even display on the website.
 */
public class FreshNode extends Node{
  public FreshNode(String id) {
    super(id, "Fresh");
  }
}
