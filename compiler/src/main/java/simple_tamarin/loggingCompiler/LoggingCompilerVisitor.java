package simple_tamarin.loggingCompiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STModel;
import simple_tamarin.dataStructures.outputTerm.*;
import simple_tamarin.loggingParser.LoggingParser.*;
import simple_tamarin.sourcesCompiler.Goal;

public class LoggingCompilerVisitor {
  private STModel model;
  private Queue<LoggingGoal> solved;
  private Queue<LoggingSource> by;

  public LoggingCompilerVisitor(STModel model) {
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
      if (goal.number != source.goalNr) {
        System.out.println("Discarded goal " + goal.goal + " and source " + source.name + "!");
        return;
      }
      goal.findSource(source);
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
    return new LoggingSource(Integer.parseInt(ctx.NUMBER().getText()) ,ctx.IDENTIFIER().getText());
  }

  public Goal visitFact(FactContext ctx) {
    boolean persistent = ctx.PERSISTENT() != null;
    String factName = ctx.IDENTIFIER().getText();
    ArrayList<OutputTerm> terms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      terms.add(visitTerm(tctx));
    }
    
    return new Goal(persistent, factName, terms);
  }

  public OutputTerm visitTerm(TermContext ctx) {
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
  public OutputTerm visitFunction(FunctionContext ctx) {
    String functionName = ctx.IDENTIFIER().getText();
    switch (functionName) {
      case Constants.SENC: {
        OutputTerm value = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSenc(key, value);
      }
      case Constants.SDEC: {
        OutputTerm value = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSdec(key, value);
      }
      case Constants.HASH: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new OutputFunctionHash(subterm);
      }
      case Constants.FIRST: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionFirst(subterm);
      }
      case Constants.SECOND: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionSecond(subterm);
      }
    }
    return new OutputVariable(model, "Function ph", "47"); // TODO debug
  }

  public OutputTuple visitTuple(TupleContext ctx) {
    ArrayList<OutputTerm> subterms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      subterms.add(visitTerm(tctx));
    }
    return new OutputTuple(subterms);
  }

  public OutputVariable visitVariable(VariableContext ctx) {
    String name = ctx.IDENTIFIER().getText();
    String number = (ctx.NUMBER() != null) ? ("." + ctx.NUMBER().getText()) : "";
    return new OutputVariable(model, name, number);
  }
}
