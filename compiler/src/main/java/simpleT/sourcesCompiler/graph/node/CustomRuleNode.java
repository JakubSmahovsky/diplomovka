package simpleT.sourcesCompiler.graph.node;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

public class CustomRuleNode extends Node{
  public CustomRuleNode(String nodeID, String nodeLabel) {
    super(nodeID, nodeLabel);
  }

  @Override
  public Description renderDescription() {
    String docLine = "Intruder may receive it from a custom rule " + this.render() + ".";
    return new Description(new Document(docLine), new Document(docLine), this, this.render());
  }

  @Override
  public String render(){
    return this.label;
  }
}
