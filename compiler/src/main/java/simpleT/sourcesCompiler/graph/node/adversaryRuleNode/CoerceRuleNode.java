package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;

public class CoerceRuleNode extends AdversaryRuleNode{
  public CoerceRuleNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }

  @Override
  public Description renderDescription() {
    return parents.get(0).renderDescription();
  }
}
