package simpleT.dataStructures.outputTerm;

public abstract class OutputTerm {
  public abstract boolean match(OutputTerm term);
  public abstract String render();
}
