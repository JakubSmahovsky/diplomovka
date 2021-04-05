package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.sourcesCompiler.graph.node.Node;

public abstract class AdversaryRuleNode extends Node {
  public AdversaryRuleNode(String id, String label) {
    super(id, label);
  }

  @Override
  public String render(){
    return this.label;
  }
}
