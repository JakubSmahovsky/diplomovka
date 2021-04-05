package simpleT.sourcesCompiler.goal;

import simpleT.dataStructures.outputTerm.OutputTerm;

/**
 * Goal stating that intruder needs to learn some term.
 */
public class AdversaryGoal extends Goal{
  public OutputTerm term;

  public AdversaryGoal(OutputTerm term) {
    this.term = term;
  }

  @Override
  public boolean match(Goal goal) {
    if (!(goal instanceof AdversaryGoal)){
      return false;
    }
    return this.term.match(((AdversaryGoal)goal).term);
  }

  @Override
  public String render() {
    return "adversary learns " + term.render();
  }
}
