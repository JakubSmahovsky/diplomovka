package dipl.sourcesCompiler.graph.node.adversaryRuleNode;

import dipl.dataStructures.outputTerm.OutputTerm;
import dipl.sourcesCompiler.graph.node.Node;

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
