package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;

public class ReceiveRuleNode extends AdversaryRuleNode {

  public ReceiveRuleNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }

  @Override
  public Description renderDescription() {
    Document doc = new Document("Adversary receives " + term.render() + " in a protocol message");
    Description parentDesc = parents.get(0).renderDescription();
    return new Description(doc.append(parentDesc.doc.indent()), parentDesc.rule, parentDesc.sourceDescription);
  }
}
