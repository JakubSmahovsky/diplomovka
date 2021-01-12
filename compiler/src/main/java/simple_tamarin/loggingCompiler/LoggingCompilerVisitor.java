package simple_tamarin.loggingCompiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StModel;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.loggingParser.LoggingParser.*;
import simple_tamarin.sourcesCompiler.Goal;
import simple_tamarin.sourcesCompiler.term.FunctionFirst;
import simple_tamarin.sourcesCompiler.term.FunctionSecond;
import simple_tamarin.sourcesCompiler.term.OutputVariable;

public class LoggingCompilerVisitor {
  private StModel model;
  private Queue<LoggingGoal> solved;
  private Queue<LoggingSource> by;

  public LoggingCompilerVisitor(StModel model) {
    this.model = model;
    this.solved = new LinkedList<>();
    this.by = new LinkedList<>();
  }

  public void visitMessage(MessageContext ctx) {
    // discard directly solved
    if (ctx.solved() != null && !ctx.solved().SOLVEDHOW().getText().equals(Constants.SOLVEDHOW_DIRECTLY)) {
      solved.add(visitSolved(ctx.solved()));
    } else if (ctx.by() != null) {
      by.add(visitBy(ctx.by()));
    } else {
      return;
    }

    if (!solved.isEmpty() && !by.isEmpty()) {
      LoggingGoal goal = solved.remove();
      LoggingSource source = by.remove();
      source.findSource(goal);
      System.out.println(goal);
      System.out.println(source);
    }
  }

  public LoggingGoal visitSolved(SolvedContext ctx) {
    int number = Integer.parseInt(ctx.NUMBER().getText());
    Goal goal = visitFact(ctx.goal().fact());
    return new LoggingGoal(model, number, goal);
  }

  public LoggingSource visitBy(ByContext ctx) {
    return new LoggingSource(ctx.IDENTIFIER().getText());
  }

  public Goal visitFact(FactContext ctx) {
    boolean persistent = ctx.PERSISTENT() != null;
    String factName = ctx.IDENTIFIER().getText();
    ArrayList<Term> terms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      terms.add(visitTerm(tctx));
    }
    
    return new Goal(persistent, factName, terms);
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

  public OutputVariable visitVariable(VariableContext ctx) {
    String name = ctx.IDENTIFIER().getText();
    String number = (ctx.NUMBER() != null) ? ("." + ctx.NUMBER().getText()) : "";
    return new OutputVariable(name, number);
  }
}
