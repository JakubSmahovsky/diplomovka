package simple_tamarin.sourcesCompiler.graph;

import java.util.ArrayList;

import simple_tamarin.Constants;
import simple_tamarin.errors.Errors;
import simple_tamarin.sourcesCompiler.graph.node.*;

public class Graph {
  public ArrayList<Node> nodes;
  public ArrayList<Edge> edges;
  public Node goal; // leaf of DAG 

  public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
    this.nodes = nodes;
    this.edges = edges;

    // form into DAG
    for (Edge edge : edges) {
      edge.from.children.add(edge.to);
    }
    // find leaf(s)
    for (Node node : nodes) {
      if (node.children.isEmpty()) {
        if (goal != null) {
          Errors.debug("More that one leaf in graph, had " + goal + ", found " + node);
        }
        goal = node;
      }
    }
  }

  @Override public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(Constants.INDENT + Constants.INDENT + "GOAL: " + goal + "\r\n");
    for (Edge edge : edges) {
      result.append(Constants.INDENT + Constants.INDENT + edge.from + "->" + edge.to + "\r\n");
    }
    return result.toString();
  }
}
