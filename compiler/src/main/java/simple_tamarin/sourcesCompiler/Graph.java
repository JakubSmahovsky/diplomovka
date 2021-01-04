package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

import simple_tamarin.Constants;

public class Graph {
  public ArrayList<GraphNode> nodes;
  public ArrayList<GraphEdge> edges;

  public Graph(ArrayList<GraphNode> nodes, ArrayList<GraphEdge> edges) {
    this.nodes = nodes;
    this.edges = edges;
  }

  @Override public String toString() {
    StringBuilder result = new StringBuilder();
    for (GraphEdge edge : edges) {
      result.append(Constants.INDENT + Constants.INDENT + edge.from.label + "->" + edge.to.label + "\r\n");
    }
    return result.toString();
  }
}
