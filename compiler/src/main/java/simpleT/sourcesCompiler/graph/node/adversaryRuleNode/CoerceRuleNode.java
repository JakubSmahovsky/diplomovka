package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.sourcesCompiler.graph.Description;

public class CoerceRuleNode extends AdversaryRuleNode{
  public CoerceRuleNode(String id, String label) {
    super(id, label);
  }

  @Override
  public Description renderDescription() {
    return parents.get(0).renderDescription();
  }
}
