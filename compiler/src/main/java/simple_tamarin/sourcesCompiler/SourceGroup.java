package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.document.Document;
import simple_tamarin.errors.Errors;

public class SourceGroup {
  public Fact goal;
  public ArrayList<Source> sources;
  // flag: true if goal is a !KU() fact, false if goal is a user fact
  public boolean hasIntruderGoal;

  public SourceGroup(Fact goal, ArrayList<Source> sources) {
    this.goal = goal;
    this.sources = sources;
    this.hasIntruderGoal = isIntruderGoal(goal);
  }

  /**
   * finds out if the provided fact is a goal for intruder knowledge, i.e. !KU() fact
   */
  public static boolean isIntruderGoal(Fact goal) {
    if (goal.persistent && goal.name.equals(Constants.INTRUDER_KNOWLEDGE_FACT)) {
      if (goal.terms.size() != 1) {
        Errors.debug("Intruder goal " + goal + " contains " + goal.terms.size() + " terms!");
      }
      return true;
    }
    return false;
  }

  public Document render(){
    String description = hasIntruderGoal ? "intruder needs to know term " : "computatuion needs to establish fact ";
    String termOrFact = hasIntruderGoal ? goal.terms.get(0).render() : goal.toString();
    Document doc = new Document("Group: " + description + termOrFact);
    for (Source source : sources) {
      Document sourceDoc = source.render();
      sourceDoc.indent();
      doc.append(sourceDoc);
    }
    return doc;
  }
}
