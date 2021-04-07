package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;

public class SendRuleNode extends AdversaryRuleNode {
  public SendRuleNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    Description description = new Description(new Document("Adversary sends " + term.render()), null, null);
    description.doc.append(parents.get(0).renderDescription(false).doc.indent());
    return description;
  }
}
