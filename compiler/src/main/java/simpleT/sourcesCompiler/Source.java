package simpleT.sourcesCompiler;

import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.*;

public class Source {
  public final STModel model;
  public int number; // assigned after sources are sorted

  public String name;
  public Graph graph;
  public String printLabel;

  public Source(STModel model, String name, Graph graph) {
    this.model = model;

    this.name = name;
    this.graph = graph;
    this.printLabel = graph.description.sourceDescription == null ? name : graph.description.sourceDescription;
  }

  public Document render(){
    Document doc = new Document("Source (" + number + ") " + printLabel);
    return doc.append(graph.render()).endl();
  }

  public String renderLabel() {
    return printLabel;
  }
}
