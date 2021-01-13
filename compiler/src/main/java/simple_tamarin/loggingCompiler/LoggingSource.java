package simple_tamarin.loggingCompiler;

import simple_tamarin.Constants;
import simple_tamarin.sourcesCompiler.Source;

public class LoggingSource {
  public int goalNr;
  public String name;
  private Source source;

  public LoggingSource(int goalNr, String name) {
    this.goalNr = goalNr;
    this.name = name;
    this.source = null;
  }

  public void findSource(LoggingGoal goal) {
    for (Source source : goal.group.sources) {
      if (name.equals(source.name)) {
        this.source = source;
        return;
      } 
    }
    System.out.println("Could not find source for " + name + " in group " + goal.group.goal);
  }

  @Override public String toString() {
    return Constants.INDENT + "by (" + source.indexInModel + ") " + name;
  }
}
