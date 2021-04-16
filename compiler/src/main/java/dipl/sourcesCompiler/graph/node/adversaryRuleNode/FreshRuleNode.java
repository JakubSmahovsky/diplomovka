package dipl.sourcesCompiler.graph.node.adversaryRuleNode;

import dipl.dataStructures.document.Document;
import dipl.dataStructures.outputTerm.OutputTerm;
import dipl.sourcesCompiler.graph.Description;

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
