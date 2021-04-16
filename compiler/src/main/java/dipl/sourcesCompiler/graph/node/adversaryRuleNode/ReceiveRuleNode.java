package dipl.sourcesCompiler.graph.node.adversaryRuleNode;

import dipl.dataStructures.document.Document;
import dipl.dataStructures.outputTerm.OutputTerm;
import dipl.sourcesCompiler.graph.Description;

public class ReceiveRuleNode extends AdversaryRuleNode {

  public ReceiveRuleNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    Document doc = new Document("Adversary receives " + term.render() + " in a protocol message");
    Description parentDesc = parents.get(0).renderDescription(false);
    return new Description(doc.append(parentDesc.doc.indent()), parentDesc.rule, parentDesc.sourceDescription);
  }
}
