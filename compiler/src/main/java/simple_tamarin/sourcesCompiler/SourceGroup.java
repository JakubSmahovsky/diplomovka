package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

import simple_tamarin.dataStructures.document.Document;

public class SourceGroup implements Comparable<SourceGroup>{
  public Goal goal;
  public ArrayList<Source> sources;

  public SourceGroup(Goal goal, ArrayList<Source> sources) {
    this.goal = goal;
    this.sources = sources;
  }


  public Document render(){
    Document doc = new Document("Group: " + goal);
    for (Source source : sources) {
      Document sourceDoc = source.render();
      sourceDoc.indent();
      doc.append(sourceDoc);
    }
    return doc;
  }

  @Override
  public int compareTo(SourceGroup group) {
    if (goal.unify(group.goal)) {
      return 1;
    }
    return -1;
  }
}
