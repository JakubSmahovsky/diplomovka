package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

public class FreshRuleNode extends AdversaryRuleNode {
  public FreshRuleNode(String id, String label) {
    super(id, label);
  }
  
  @Override
  public Description renderDescription() {
    Document doc = new Document(new StringBuilder("Adversary generates it."));
      return new Description(doc, doc.clone(), null, "by generation");
  }
}
