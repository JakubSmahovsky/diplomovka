package dipl.loggingCompiler;

import dipl.dataStructures.Model;
import dipl.dataStructures.document.Document;
import dipl.sourcesCompiler.SourceGroup;
import dipl.sourcesCompiler.goal.*;

public class LoggingGoal {
  public final Model model;
  public int number;
  public Goal goal;
  public boolean shouldBeHidden;
  public SourceGroup group;

  public LoggingGoal(Model model ,int number, Goal goal) {
    this.model = model;
    this.number = number;
    this.goal = goal;
    this.shouldBeHidden = goal.hideEverywhere();
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
    return new Document("Solved: " + goal.render());
  }
}
