package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.term.Term;
import simple_tamarin.errors.Errors;

public class Goal {
  public boolean persistent;
  public String name; 
  public ArrayList<Term> terms;
  public boolean intruderGoal;

  public Goal(boolean persistent, String name, ArrayList<Term> terms) {
    this.persistent = persistent;
    this.name = name;
    this.terms = terms;
    intruderGoal = isIntruderGoal();
  }

  /**
   * finds out if this is a goal for intruder knowledge, i.e. !KU() fact
   */
  private boolean isIntruderGoal() {
    if (persistent && name.equals(Constants.INTRUDER_KNOWLEDGE_FACT)) {
      if (terms.size() != 1) {
        Errors.debug("Intruder goal " + BuilderFormatting.fact(name, terms, null) 
            + " contains " + terms.size() + " terms!");
      }
      return true;
    }
    return false;
  }

  /**
   * Find out if this goal has the same structure as the provided goal 
   * and is at least as general as the provided goal.
   */
  public boolean unify(Goal goal) {
    if (intruderGoal != goal.intruderGoal || persistent != goal.persistent ||
          !name.equals(goal.name) || terms.size() != goal.terms.size()) {
      return false;
    }
    for (int i = 0; i < terms.size(); i++) {
      if (!terms.get(i).unify(goal.terms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override public String toString() {
    String description = intruderGoal ? "intruder learns " : "establish fact ";
    String fact;
    if (intruderGoal) {
      fact = terms.get(0).render();
    } else {
      fact = (persistent ? "!" : "") + BuilderFormatting.fact(name, terms, null);
    }
    return description + fact;
  }
}
