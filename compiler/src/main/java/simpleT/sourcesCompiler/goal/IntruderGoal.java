package simpleT.sourcesCompiler.goal;

import simpleT.dataStructures.outputTerm.OutputTerm;

/**
 * Goal stating that intruder needs to learn some term.
 */
public class IntruderGoal extends Goal{
  public OutputTerm term;

  public IntruderGoal(OutputTerm term) {
    this.term = term;
  }

  @Override
  public boolean match(Goal goal) {
    if (!(goal instanceof IntruderGoal)){
      return false;
    }
    return this.term.match(((IntruderGoal)goal).term);
  }

  @Override
  public String render() {
    return "intruder learns " + term.render();
  }
}
