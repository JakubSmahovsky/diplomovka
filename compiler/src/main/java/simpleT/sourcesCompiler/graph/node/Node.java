package simpleT.sourcesCompiler.graph.node;

import java.util.ArrayList;

import simpleT.sourcesCompiler.graph.Description;

/**
 * Graph node superclass.
 * Instances of this node are still used for stub nodes
 * that Tamarin created when goal is a custon fact.
 */
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
   */
  public abstract Description renderDescription();

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
