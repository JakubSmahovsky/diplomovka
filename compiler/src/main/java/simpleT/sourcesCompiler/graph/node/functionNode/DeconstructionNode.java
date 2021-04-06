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
    Document doc = new Document("Adversary deconstructs " + term.render() + " using " + render() + " on messages from:");
    Node rule = null;
    for (Node parent : parents) {
      Description parentDesc = parent.renderDescription();
      if (parentDesc.rule != null) {
        rule = parentDesc.rule;
      }
      parentDesc.doc.indent();
      doc.append(parentDesc.doc);
    }
    return new Description(doc, rule, "deconstruction");
  }
}
