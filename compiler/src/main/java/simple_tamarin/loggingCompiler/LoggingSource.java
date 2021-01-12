package simple_tamarin.loggingCompiler;

import simple_tamarin.Constants;
import simple_tamarin.sourcesCompiler.Source;

public class LoggingSource {
  public String name;
  private Source source;

  public LoggingSource(String name) {
    this.name = name;
    this.source = null;
  }

  @Override public String toString() {
    return Constants.INDENT + "by " + name; 
  }
}
