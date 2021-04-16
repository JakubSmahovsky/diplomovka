package dipl.sourcesCompiler.graph;

import dipl.dataStructures.document.Document;
import dipl.sourcesCompiler.graph.node.Node;

public class Description {
  // documentation of entire tree including unknown sources !! use doc.clone() if it's the same as shortDoc !!
  public final Document doc;
  // first rule encountered going up from goal
  public final Node rule;
  // tag we use to describe source: rule name or the most important function
  public String sourceDescription;

  public Description(Document doc, Node rule, String sourceDescription) {
    this.doc = doc;
    this.rule = rule;
    this.sourceDescription = sourceDescription;
  }
}
