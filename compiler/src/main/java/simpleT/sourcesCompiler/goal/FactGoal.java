package simpleT.sourcesCompiler.goal;

import java.util.ArrayList;

import simpleT.OutputFormatting;
import simpleT.dataStructures.outputTerm.OutputTerm;

/**
 * Goal stating that some fact needs to be extablsihed.
 */
public class FactGoal extends Goal{
  public boolean persistent;
  public String symbol;
  public ArrayList<OutputTerm> terms;

  public FactGoal(boolean persistent, String symbol, ArrayList<OutputTerm> terms) {
    this.persistent = persistent;
    this.symbol = symbol;
    this.terms = terms;
  }

  @Override
  public boolean match(Goal goal) {
    if (!(goal instanceof FactGoal)) {
      return false;
    }
    FactGoal factGoal = (FactGoal)goal;
    if (persistent != factGoal.persistent ||
        !symbol.equals(factGoal.symbol) ||
        terms.size() != factGoal.terms.size()) {
      return false;
    }
    for (int i = 0; i < terms.size(); i++) {
      if (!terms.get(i).match(factGoal.terms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String render() {
    return "establish fact " + OutputFormatting.fact(persistent, symbol, terms);
  }
}
