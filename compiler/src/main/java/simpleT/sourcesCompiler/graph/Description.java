package simpleT.sourcesCompiler.graph;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.node.Node;

public class Description {
  // documentation of path from goal to rule
  public final Document shortDoc;
  // documentation of entire tree including unknown sources !! use doc.clone() if it's the same as shortDoc !!
  public final Document longDoc;
  // first rule encountered going up from goal
  public final Node rule;
  // tag we use to describe source: rule name or the most important function
  public String sourceDescription;

  public Description(Document shortDoc, Document longDoc, Node rule, String sourceDescription) {
    this.shortDoc = shortDoc;
    this.longDoc = longDoc;
    this.rule = rule;
    this.sourceDescription = sourceDescription;
  }
}
