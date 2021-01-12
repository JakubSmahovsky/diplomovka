package simple_tamarin.loggingCompiler;

import simple_tamarin.sourcesCompiler.Fact;

public class LoggingGoal {
  public int number;
  public Fact goal;

  public LoggingGoal(int number, Fact goal) {
    this.number = number;
    this.goal = goal;
  }

  @Override public String toString() {
    return "Solved (" + number + ") " + goal;
  }
}
