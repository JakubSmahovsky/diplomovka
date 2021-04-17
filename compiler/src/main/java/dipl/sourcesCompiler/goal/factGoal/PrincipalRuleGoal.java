package dipl.sourcesCompiler.goal.factGoal;

import java.util.ArrayList;

import dipl.Constants;
import dipl.dataStructures.Block;
import dipl.dataStructures.outputTerm.OutputTerm;

public class PrincipalRuleGoal extends FactGoal{
  private final Block block;

  public PrincipalRuleGoal(Block block, boolean persistent, String symbol, ArrayList<OutputTerm> terms) {
    super(persistent, symbol, terms);
    this.block = block;
  }

  @Override
  public String render() {
    String principal = terms.get(2).render();
    ArrayList<String> known = new ArrayList<>();
    for (OutputTerm term : terms.subList(3, terms.size())) {
      known.add(term.render());
    } 

    return principal + " commits to block no. " + block.rangeEnd 
      + " knowing (" + String.join(Constants.COMMA_SEPARATOR, known) + ")"; 
  }

  @Override
  public boolean hideSourceGroup() {
    return true;
  }
}
