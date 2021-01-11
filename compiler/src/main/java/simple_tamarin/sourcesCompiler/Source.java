package simple_tamarin.sourcesCompiler;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StModel;
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

  @Override public String toString(){
    return Constants.INDENT + "Source (" + indexInModel + ") " + printLabel + "\r\n" + graph;
  }
}
