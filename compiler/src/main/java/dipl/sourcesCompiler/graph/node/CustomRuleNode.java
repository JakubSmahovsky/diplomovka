package dipl.sourcesCompiler.graph.node;

import dipl.dataStructures.document.Document;
import dipl.sourcesCompiler.graph.Description;

public class CustomRuleNode extends Node{
  public CustomRuleNode(String nodeID, String nodeLabel) {
    super(nodeID, nodeLabel);
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    String docLine;
    if (protocolRuleParent) {
      docLine = "Initial assumptions precede it.";
    } else {
      docLine = "Adversary learns it as a public value from initial assumptions.";
    }
    return new Description(new Document(docLine), this, this.render());
  }

  @Override
  public String render(){
    return "initial assumptions";
  }
}
