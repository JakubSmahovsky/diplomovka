package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

public class SourceGroup {
  public Fact goal;
  public ArrayList<Source> sources;

  public SourceGroup(Fact goal, ArrayList<Source> sources) {
    this.goal = goal;
    this.sources = sources;
  }
}
