package simpleT.sourcesCompiler.graph.node;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

/**
 * Node that Tamarin uses when the goal is a fact, so the website displays a
 * trapezium with (#i, 0)
 */
public class StubNode extends Node{
  public StubNode(String id) {
    super(id, "Stub");
  }

  @Override
  public Description renderDescription() {
    StringBuilder myLine = new StringBuilder("TODO (unimportant, we will likely be hiding fact sources.)");
    return new Description(new Document(myLine), new Document(new StringBuilder(myLine)), null, null);
  }

  @Override
  public String render(){
    return "Stub";
  }
}
