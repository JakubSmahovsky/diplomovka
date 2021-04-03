package simpleT.sourcesCompiler.graph.node;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

public class CustomRuleNode extends Node{
  public CustomRuleNode(String nodeID, String nodeLabel) {
    super(nodeID, nodeLabel);
  }

  @Override
  public Description renderDescription() {
    String docLine = "Intruder may receive it from a cutom rule " + this.toString() + ".";
    return new Description(new Document(docLine), new Document(docLine), this, this.toString());
  }
}
