package simpleT.sourcesCompiler.graph.node.adversaryRuleNode;

import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.node.Node;

public abstract class AdversaryRuleNode extends Node {
  protected final OutputTerm term;
  public AdversaryRuleNode(String id, String label, OutputTerm term) {
    super(id, label);
    this.term = term;
  }

  @Override
  public String render(){
    return this.label;
  }
}
