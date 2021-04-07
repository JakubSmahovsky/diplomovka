package simpleT.sourcesCompiler.graph;

import java.util.ArrayList;

import simpleT.dataStructures.document.Document;
import simpleT.errors.Errors;
import simpleT.sourcesCompiler.graph.node.*;

public class Graph {
  public ArrayList<Node> nodes;
  public ArrayList<Edge> edges;
  public String sourceName;

  public Node goal; // leaf of DAG 

  public Description description;

  public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges, String sourceName) {
    this.nodes = nodes;
    this.edges = edges;
    this.sourceName = sourceName;

    // form into DAG
    for (Edge edge : edges) {
      if (!edge.from.children.contains(edge.to)) {
        edge.from.children.add(edge.to);
        edge.to.parents.add(edge.from);
      }
    }
    findGoal();
    description = goal.renderDescription(false);
  }

  /**
   * Separated part of constructor
   * Find leaf of graph (should only have 1) and assign it to goal.
   */
  private void findGoal() {
    for (Node node : nodes) {
      if (node.children.isEmpty()) {
        if (goal != null) {
          Errors.debug("More that one leaf in graph, had " + goal + ", found " + node);
        }
        goal = node;
      }
    }
  }

  public Document render() {
    return description.doc.clone();
  }
}
