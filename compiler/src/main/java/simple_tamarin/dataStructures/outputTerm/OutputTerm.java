package simple_tamarin.dataStructures.outputTerm;

public abstract class OutputTerm {
  public abstract boolean unify(OutputTerm term);
  public abstract String render();
}
