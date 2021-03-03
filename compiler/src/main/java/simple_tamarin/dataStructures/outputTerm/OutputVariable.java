package simple_tamarin.dataStructures.outputTerm;

public class OutputVariable extends OutputTerm {
  private final String name;
  private final String number;
  
  public OutputVariable(String name, String number) {
    this.name = name;
    this.number = number;
  }

  @Override
  public boolean unify(OutputTerm term) {
    return true;
  }

  @Override
  public String render() {
    return name + number;
  }
}
