package simple_tamarin.sourcesCompiler;

import simple_tamarin.dataStructures.StModel;
import simple_tamarin.dataStructures.document.Document;
import simple_tamarin.sourcesCompiler.graph.*;

public class Source {
  public final StModel model;
  public final int indexInModel;

  public String name;
  public Graph graph;
  public String printLabel;

  public Source(StModel model, String name, Graph graph) {
    this.model = model;
    this.indexInModel = model.registerSource(this);

    this.name = name;
    this.graph = graph;
    this.printLabel = graph.description.sourceDescription == null ? name : graph.description.sourceDescription;
  }

  public Document render(){
    Document doc = new Document("Source (" + indexInModel + ") " + printLabel);
    Document graphDoc = graph.render();
    graphDoc.indent();
    doc.append(graphDoc);
    return doc;
  }
}
