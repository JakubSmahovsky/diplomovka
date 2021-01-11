package simple_tamarin.sourcesCompiler.graph;

import simple_tamarin.dataStructures.document.Document;
import simple_tamarin.sourcesCompiler.graph.node.Node;

public class Description {
  public final Document shortDoc;
  public final Document longDoc;
  public final Node rule;

  public Description(Document shortDoc, Document longDoc, Node rule) {
    this.shortDoc = shortDoc;
    this.longDoc = longDoc;
    this.rule = rule;
  }
}
