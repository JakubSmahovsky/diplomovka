package simple_tamarin.loggingCompiler;

import simple_tamarin.sourcesCompiler.Goal;

public class LoggingGoal {
  public int number;
  public Goal goal;

  public LoggingGoal(int number, Goal goal) {
    this.number = number;
    this.goal = goal;
  }

  @Override public String toString() {
    return "Solved (" + number + ") " + goal;
  }
}
