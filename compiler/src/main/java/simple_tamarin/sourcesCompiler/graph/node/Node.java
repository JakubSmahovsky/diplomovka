package simple_tamarin.sourcesCompiler.graph.node;

import java.util.ArrayList;

/**
 * Graph node superclass.
 * Instances of this node are still used for stub nodes
 * that Tamarin created when goal is a custon fact.
 */
public class Node {
  public String id;
  public String label;
  public ArrayList<Node> children; // outgoing edges 

  public Node(String id, String label) {
    this.id = id;
    this.label = label;
    this.children = new ArrayList<>();
  }

  @Override public String toString(){
    return label.isEmpty() ? "Stub" : label;
  }

  /**
   * Speciffically for compiling edges, take a Source node or 
   * target node in format "node:endpoint" and return only node
   */
  public static String ArrowEndpointToNode(String endpoint) {
    return endpoint.split(":")[0];
  }
}
