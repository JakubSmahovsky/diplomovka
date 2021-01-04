package simple_tamarin.sourcesCompiler.term;

public class OutputVariable extends OutputTerm {
  public String name;
  public String number;
  
  public OutputVariable(String name, String number) {
    this.name = name;
    this.number = number;
  }

  @Override
  public String render() {
    return null;
  }  
}
