package simpleT.sourcesCompiler.graph.node.functionNode;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;
import simpleT.sourcesCompiler.graph.node.Node;

public class DeconstructionNode extends FunctionNode {
  public DeconstructionNode(String id, String label) {
    super(id, label);
  }

  @Override
  public Description renderDescription() {
    Document shortDoc = new Document("Adversary deconstructs it using " + render() + " on messages from:");
    Document longDoc = shortDoc.clone();
    Node rule = null;
    for (Node parent : parents) {
      Description parentDesc = parent.renderDescription();
      if (parentDesc.rule != null) {
        rule = parentDesc.rule;
        shortDoc.doc.addAll(parentDesc.shortDoc.doc);
      }
      parentDesc.longDoc.indent();
      longDoc.doc.addAll(parentDesc.longDoc.doc);
    }
    return new Description(shortDoc, longDoc, rule, "deconstruction");
  }
}
