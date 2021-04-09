package simpleT.dataStructures.outputTerm;

public abstract class OutputTerm {
  // arguments are reversed compared to ST
  public boolean reversedArguments() {
    return false;
  }
  public boolean isVariable() {
    return false;
  }
  public abstract boolean match(OutputTerm term);
  public abstract String render();
}
