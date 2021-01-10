package simple_tamarin.sourcesCompiler.graph.node;

/**
 * Node that Tamarin uses when the goal is a fact,
 * so the website displays a trapezium with (#i, 0)
 */
public class StubNode extends Node{
  public StubNode(String id) {
    super(id, "Stub");
  }
}
