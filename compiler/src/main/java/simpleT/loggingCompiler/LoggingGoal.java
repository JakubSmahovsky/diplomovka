package simpleT.loggingCompiler;

import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.goal.*;
import simpleT.sourcesCompiler.SourceGroup;

public class LoggingGoal {
  public final STModel model;
  public int number;
  public Goal goal;
  public boolean shouldBeHidden;
  public SourceGroup group;

  public LoggingGoal(STModel model ,int number, Goal goal) {
    this.model = model;
    this.number = number;
    this.goal = goal;
    this.shouldBeHidden = goal.shouldBeHidden();
    this.group = null;
  }

  public void findSource(LoggingSource source) {
    for (SourceGroup group : model.sourceGroups) {
      if (group.goal.match(goal)) {
        this.group = group;
        source.findSource(this);
        if (source.source != null){
          break;
        }
      }
    }
  }

  public Document render() {
    return new Document("Solved (" + number + ") " + goal.render());
  }
}
