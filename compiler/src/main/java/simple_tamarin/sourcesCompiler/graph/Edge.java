package simple_tamarin.sourcesCompiler.graph;

import simple_tamarin.sourcesCompiler.graph.node.*;

public class Edge {
  public Node from;
  public Node to;

public Edge(Node from, Node to) {
  this.from = from;
  this.to = to;
}
}