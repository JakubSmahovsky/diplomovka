package simple_tamarin.sourcesCompiler;

public class Source {
  public String name;

  public Source(String name) {
    this.name = name;
  }

  @Override public String toString(){
    return name;
  }
}
