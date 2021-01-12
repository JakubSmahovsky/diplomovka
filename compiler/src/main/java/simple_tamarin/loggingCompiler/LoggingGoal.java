package simple_tamarin.loggingCompiler;

import simple_tamarin.dataStructures.StModel;
import simple_tamarin.sourcesCompiler.Goal;
import simple_tamarin.sourcesCompiler.SourceGroup;

public class LoggingGoal {
  public final StModel model;
  public int number;
  public Goal goal;
  public SourceGroup group;

  public LoggingGoal(StModel model ,int number, Goal goal) {
    this.model = model;
    this.number = number;
    this.goal = goal;
    this.group = findGroup();
  }

  private SourceGroup findGroup() {
    for (SourceGroup group : model.sourceGroups) {
      if (group.goal.unify(goal)) {
        return group;
      }
    }
    System.out.println("Could not find group for " + toString());
    return null;
  }

  @Override public String toString() {
    return "Solved (" + number + ") " + goal;
  }
}
