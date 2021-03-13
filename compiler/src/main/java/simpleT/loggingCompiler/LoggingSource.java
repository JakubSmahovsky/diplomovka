package simpleT.loggingCompiler;

import simpleT.Constants;
import simpleT.sourcesCompiler.Source;

public class LoggingSource {
  public int goalNr;
  public String name;
  public Source source;

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
  }

  @Override public String toString() {
    return Constants.INDENT + "by (" + source.indexInModel + ") " + name;
  }
}
