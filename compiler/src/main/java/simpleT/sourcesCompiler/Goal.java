package simpleT.sourcesCompiler;

import java.util.ArrayList;

import simpleT.Constants;
import simpleT.OutputFormatting;
import simpleT.dataStructures.outputTerm.OutputTerm;

public class Goal {
  public boolean persistent;
  public String name; 
  public ArrayList<OutputTerm> terms;
  public boolean intruderGoal;

  public Goal(boolean persistent, String name, ArrayList<OutputTerm> terms) {
    this.persistent = persistent;
    this.name = name;
    this.terms = terms;
    intruderGoal = isIntruderGoal();
  }

  /**
   * finds out if this is a goal for intruder knowledge, i.e. !KU() fact
   */
  private boolean isIntruderGoal() {
    if (persistent && name.equals(Constants.INTRUDER_KNOWS_OUTPUT)) {
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
      fact = (persistent ? "!" : "") + OutputFormatting.term(name, terms);
    }
    return description + fact;
  }
}
