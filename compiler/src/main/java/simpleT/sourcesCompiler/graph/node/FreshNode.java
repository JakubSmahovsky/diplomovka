package simpleT.sourcesCompiler.graph.node;

import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;

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
  public Description renderDescription() {
    Document doc = new Document(new StringBuilder("Adversary generates " + term.render()));
      return new Description(doc, null, "generation");
  }
}
