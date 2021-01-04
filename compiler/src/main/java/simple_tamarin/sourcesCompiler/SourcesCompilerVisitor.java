package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StModel;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.sourcesCompiler.term.*;
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
    Fact goal = visitFact(ctx.goal().fact());
    System.out.println("group " + goal);
    ArrayList<Source> sources = new ArrayList<>();
    for (SourceContext sctx : ctx.source()) {
      Source source = visitSource(sctx);
      sources.add(source);
      System.out.println("  source " + source);
    }

    model.sourceGroups.add(new SourceGroup(goal, sources));
  }

  public Source visitSource(SourceContext ctx) {
    String name = ctx.name.getText();
    return new Source(name);
  }

  public Fact visitFact(FactContext ctx) {
    boolean persistent = ctx.PERSISTENT() != null;
    String factName = ctx.IDENTIFIER().getText();
    ArrayList<Term> terms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      terms.add(visitTerm(tctx));
    }
    
    return new Fact(persistent, factName, terms);
  }

  public Term visitTerm(TermContext ctx) {
    if (ctx.function() != null) {
      return visitFunction(ctx.function());
    } else if (ctx.tuple() != null) {
      return visitTuple(ctx.tuple());
    } else if (ctx.variable() != null) {
      return visitVariable(ctx.variable());
    }
    return null; // TODO: debug
  }

  /**
   * IMPORTANT: order of key and value are reversed in Tamarin,
   * functions have format f(value, key)
   */
  public Term visitFunction(FunctionContext ctx) {
    String functionName = ctx.IDENTIFIER().getText();
    switch (functionName) {
      case Constants.SENC: {
        Term value = visitTerm(ctx.term(0));
        Term key = visitTerm(ctx.term(1));
        return new FunctionSenc(key, value);
      }
      case Constants.SDEC: {
        Term value = visitTerm(ctx.term(0));
        Term key = visitTerm(ctx.term(1));
        Term decoded = null;
        return new FunctionSdec(key, value, decoded);
      }
      case Constants.HASH: {
        Term subterm = visitTerm(ctx.term(0));
        return new FunctionHash(subterm);
      }
      case Constants.FIRST: {
        Term subterm = visitTerm(ctx.term(0));
        return new FunctionFirst(subterm);
      }
      case Constants.SECOND: {
        Term subterm = visitTerm(ctx.term(0));
        return new FunctionSecond(subterm);
      }
    }
    return new Variable("Function ph"); // TODO debug
  }

  public Term visitTuple(TupleContext ctx) {
    ArrayList<Term> subterms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      subterms.add(visitTerm(tctx));
    }
    return new Tuple(subterms);
  }

  public Variable visitVariable(VariableContext ctx) {
    String name = ctx.IDENTIFIER().getText();
    String number = (ctx.NUMBER() != null) ? ("." + ctx.NUMBER().getText()) : "";
    return new Variable(name + number);
  }
}
