package simpleT.loggingCompiler;

import simpleT.dataStructures.STModel;
import simpleT.errors.Errors;
import simpleT.sourcesCompiler.Goal;
import simpleT.sourcesCompiler.SourceGroup;

public class LoggingGoal {
  public final STModel model;
  public int number;
  public Goal goal;
  public SourceGroup group;

  public LoggingGoal(STModel model ,int number, Goal goal) {
    this.model = model;
    this.number = number;
    this.goal = goal;
    this.group = null;
  }

  public void findSource(LoggingSource source) {
    for (SourceGroup group : model.sourceGroups) {
      if (group.goal.unify(goal)) {
        this.group = group;
        source.findSource(this);
        if (source.source != null){
          break;
        }
      }
    }
    if (source.source == null) {
      Errors.debug("Could not find source for goal " + toString() + " and source " + source.name);
    }
  }

  @Override public String toString() {
    return "Solved (" + number + ") " + goal;
  }
}
