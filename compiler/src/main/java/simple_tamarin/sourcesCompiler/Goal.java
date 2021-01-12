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
