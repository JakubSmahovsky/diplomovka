package dipl.sourcesCompiler.graph.node;

import dipl.dataStructures.document.Document;
import dipl.dataStructures.outputTerm.OutputTerm;
import dipl.sourcesCompiler.graph.Description;

/**
 * A node for generating fresh values, which Tamarin doesn't even display on the website.
 * Seemingly allways above FreshRuleNode, sometimes also standalone.
 */
public class FreshNode extends Node{
  private final OutputTerm term;
  public FreshNode(String id, OutputTerm term) {
    super(id, "Fresh");
    this.term = term;
  }

  @Override
  public String render(){
    return this.label;
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    String docLine = (protocolRuleParent ? "Protocol" : "Adversary") + " generates " + term.render();
    Document doc = new Document(new StringBuilder(docLine));
      return new Description(doc, null, "generation");
  }
}
