package simple_tamarin.sourcesCompiler;

import simple_tamarin.Constants;

public class Source {
  public String name;
  public Graph graph;

  public Source(String name, Graph graph) {
    this.name = name;
    this.graph = graph;
  }

  @Override public String toString(){
    return Constants.INDENT + "Source " + name + "\r\n" + graph;
  }
}
