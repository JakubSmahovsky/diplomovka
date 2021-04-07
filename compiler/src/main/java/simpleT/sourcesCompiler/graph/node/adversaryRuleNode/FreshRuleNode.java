package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;

public class FreshRuleNode extends AdversaryRuleNode {
  public FreshRuleNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }
  
  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    Document doc = new Document(new StringBuilder("Adversary generates " + term.render()));
      return new Description(doc, null, "generation");
  }
}
