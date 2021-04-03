package simpleT.sourcesCompiler;

import java.util.ArrayList;

import simpleT.dataStructures.document.Document;

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
      doc.append((source.render().indent()));
    }
    return doc.endl().endl();
  }

  @Override
  public int compareTo(SourceGroup group) {
    if (goal.match(group.goal)) {
      return 1;
    }
    return -1;
  }
}
