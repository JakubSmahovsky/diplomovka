package simpleT.sourcesCompiler.graph;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.node.Node;

public class Description {
  // documentation of path from goal to rule
  public final Document shortDoc;
  // documentation of entire tree including unknown sources (not going past rule)
  public final Document longDoc;
  // first rule encountered going up from goal (should hopefully only be one and not cause collisions)
  public final Node rule;
  // a tag we will use to describe source, conatins name of rule or most important function
  public String sourceDescription;

  public Description(Document shortDoc, Document longDoc, Node rule, String sourceDescription) {
    this.shortDoc = shortDoc;
    this.longDoc = longDoc;
    this.rule = rule;
    this.sourceDescription = sourceDescription;
  }
}
