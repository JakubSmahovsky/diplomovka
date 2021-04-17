package dipl.sourcesCompiler.graph.node;

import java.util.ArrayList;

import dipl.sourcesCompiler.graph.Description;

public abstract class Node {
  public String id;
  public String label;
  public ArrayList<Node> children; // outgoing edges 
  public ArrayList<Node> parents; // incomming edges 

  public Node(String id, String label) {
    this.id = id;
    this.label = label;
    this.children = new ArrayList<>();
    this.parents = new ArrayList<>();
  }

  public abstract String render();

  /**
   * Search depth-first throught parents and collect rendered printout of nodes' meaning.
   * @param protocolRuleParent indicates that this is a direct parent of a protocol rule. 
   * This means some actions above are executed by the protocol (fresh, other rules, ...)
   */
  public abstract Description renderDescription(boolean protocolRuleParent);

  /**
   * Speciffically for compiling edges, take a Source node or 
   * target node in format "node:endpoint" and return only node
   */
  public static String ArrowEndpointToNode(String endpoint) {
    return endpoint.split(":")[0];
  }

  @Override public String toString(){
    return "";
  }
}
