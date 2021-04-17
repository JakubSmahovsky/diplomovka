package dipl.sourcesCompiler.goal.factGoal;

import java.util.ArrayList;

import dipl.dataStructures.outputTerm.OutputTerm;

public class HiddenFactGoal extends FactGoal {
  public HiddenFactGoal(boolean persistent, String symbol, ArrayList<OutputTerm> terms) {
    super(persistent, symbol, terms);
  }

  @Override
  public String render() {
    return "HIDDEN GOAL";
  }

  @Override
  public boolean hideEverywhere() {
    return true;
  }
}
