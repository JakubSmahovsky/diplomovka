package simpleT.sourcesCompiler.graph.node.functionNode;

import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;
import simpleT.sourcesCompiler.graph.node.Node;

public class DeconstructionNode extends FunctionNode {
  public DeconstructionNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }

  @Override
  public Description renderDescription() {
    Document shortDoc = new Document("Adversary deconstructs " + term.render() + " using " + render() + " on messages from:");
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
