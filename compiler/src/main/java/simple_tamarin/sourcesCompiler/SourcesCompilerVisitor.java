package simple_tamarin.sourcesCompiler;

import simple_tamarin.dataStructures.StModel;
import simple_tamarin.sourcesParser.SourcesParser.*;

public class SourcesCompilerVisitor {
  private StModel model;
  
  public SourcesCompilerVisitor(StModel model) {
    this.model = model;
  }

  public void visitSources(SourcesContext ctx) {
    System.out.println("sources");
    for (GroupContext gctx : ctx.group()) {
      visitGroup(gctx);
    }
  }

  public void visitGroup(GroupContext ctx) {
    System.out.println("group");
    for (SourceContext sctx : ctx.source()) {
      visitSource(sctx);
    }
  }

  public void visitSource(SourceContext ctx) {
    System.out.println("source");
  }
}
