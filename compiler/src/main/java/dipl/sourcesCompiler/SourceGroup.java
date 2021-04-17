package dipl.sourcesCompiler;

import java.util.ArrayList;

import dipl.Constants;
import dipl.dataStructures.document.Document;
import dipl.sourcesCompiler.goal.Goal;

public class SourceGroup implements Comparable<SourceGroup>{
  public Goal goal;
  public ArrayList<Source> sources;

  public SourceGroup(Goal goal, ArrayList<Source> sources) {
    this.goal = goal;
    this.sources = sources;
  }

  public Document render(){
    if (goal.shouldBeHidden()) {
      return new Document();
    }

    Document doc = new Document()
      .append(Constants.OUTPUT_SEPARATOR)
      .append("GROUP: " + goal.render())
      .append(Constants.OUTPUT_SEPARATOR);
    
    for (Source source : sources) {
      doc.append("solve: " + goal.render());
      doc.append((source.render()));
    }
    return doc.endl(2);
  }

  @Override
  public int compareTo(SourceGroup group) {
    if (goal.match(group.goal)) {
      return 1;
    }
    return -1;
  }
}
