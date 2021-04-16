package dipl.sourcesCompiler.graph.node;

import dipl.dataStructures.document.Document;
import dipl.dataStructures.outputTerm.OutputTerm;
import dipl.sourcesCompiler.graph.Description;

public class UnsolvedNode extends Node {
  private OutputTerm term;
  public UnsolvedNode(String id, String label, OutputTerm term) {
    super(id, label);
    this.term = term;
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    StringBuilder myLine = new StringBuilder("Adversary gets " + term.render() + " from another source.");
    return new Description(new Document(new StringBuilder(myLine)), null, null);
  }

  @Override
  public String render(){
    return "Unsolved_learn(" + term.render() + ")";
  }
}