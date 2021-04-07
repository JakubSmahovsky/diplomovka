package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;

/**
 * Adversary rule that "declares terms equal", often wrong. e.g.
 * get a nonce, but suppose that it is a key.
 */
public class CoerceRuleNode extends AdversaryRuleNode{
  public CoerceRuleNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    Document doc = new Document("Adversary coerces the protocol to use " + term.render() + " instead of:");
    Description parentDescription = parents.get(0).renderDescription(false);
    Description description = new Description(doc.append(parentDescription.doc.indent()), parentDescription.rule, parentDescription.sourceDescription);
    return description;
  }
}
