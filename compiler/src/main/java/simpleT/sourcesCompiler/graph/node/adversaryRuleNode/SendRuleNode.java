package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.sourcesCompiler.graph.Description;

public class SendRuleNode extends AdversaryRuleNode {
  public SendRuleNode(String id, String label) {
    super(id, label);
  }

  @Override
  public Description renderDescription() {
    return parents.get(0).renderDescription();
  }

}
