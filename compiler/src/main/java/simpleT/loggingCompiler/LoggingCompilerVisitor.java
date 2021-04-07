package simpleT.loggingCompiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import simpleT.Constants;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.*;
import simpleT.errors.Errors;
import simpleT.loggingParser.LoggingParser.*;
import simpleT.sourcesCompiler.goal.*;

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
    if (ctx.solved() != null && !ctx.solved().SOLVEDHOW().getText().equals(Constants.LOGGING_SOLVEDHOW_DIRECTLY)) {
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
        System.err.println("Discarded goal " + goal.goal.render() + " and source " + source.name + "!");
        return;
      }
      if (goal.shouldBeHidden) {
        return;
      }
      goal.findSource(source);
      System.err.println(goal.render());
      Document sourceDoc = source.render();
      sourceDoc.indent();
      System.err.println(sourceDoc);
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
    String symbol = ctx.IDENTIFIER().getText();
    ArrayList<OutputTerm> terms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      terms.add(visitTerm(tctx));
    }
    
    if (persistent && symbol.equals(Constants.INTRUDER_KNOWS_OUTPUT)) {
      return new AdversaryGoal(terms.get(0)); // intruder goal fact contains exactly 1 term
    } else {
      return new FactGoal(persistent, symbol, terms);
    }
  }

  public OutputTerm visitTerm(TermContext ctx) {
    // bracketed term
    if (ctx.term().size() == 1) {
      return visitTerm(ctx.term(0));
    }
    if (ctx.constant() != null) {
      return visitConstant(ctx.constant());
    }
    if (ctx.variable() != null) {
      return visitVariable(ctx.variable());
    }
    if (ctx.function() != null) {
      return visitFunction(ctx.function());
    }
    if (ctx.tuple() != null) {
      return visitTuple(ctx.tuple());
    }
    if (ctx.infixOp != null) {
      if (ctx.infixOp.getText().equals(Constants.T_EXP)) {
        return new OutputExponentiation(visitTerm(ctx.term(0)), visitTerm(ctx.term(1)));
      }
      if (ctx.infixOp.getText().equals(Constants.T_MUL)) {
        return new OutputMultiplication(visitTerm(ctx.term(0)), visitTerm(ctx.term(1)));
      }
    }

    Errors.DebugUnexpectedTokenType(ctx.getText(), "logging compiler visitTerm");;
    return null;
  }

  public OutputConstant visitConstant(ConstantContext ctx) {
    return new OutputConstant(ctx.word.getText());
  }

  /**
   * IMPORTANT: order of key and value are reversed in Tamarin,
   * functions have format f(value, key)
   */
  public OutputTerm visitFunction(FunctionContext ctx) {
    String functionName = ctx.IDENTIFIER().getText();
    switch (functionName) {
      case Constants.T_FIRST: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionFirst(subterm);
      }
      case Constants.T_SECOND: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionSecond(subterm);
      }
      case Constants.T_PK: {
        OutputTerm sk = visitTerm(ctx.term(0));
        return new OutputFunctionPk(sk);
      }
      case Constants.T_SIGN: {
        OutputTerm message = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSign(key, message);
      }
      case Constants.T_VERIFY: {
        OutputTerm signature = visitTerm(ctx.term(0));
        OutputTerm message = visitTerm(ctx.term(1));
        OutputTerm key = visitTerm(ctx.term(2));
        return new OutputFunctionVerify(key, message, signature);
      }
      case Constants.T_SENC: {
        OutputTerm value = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSenc(key, value);
      }
      case Constants.T_SDEC: {
        OutputTerm encryptedValue = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSdec(key, encryptedValue);
      }
      case Constants.T_AENC: {
        OutputTerm value = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionAenc(key, value);
      }
      case Constants.T_ADEC: {
        OutputTerm encryptedValue = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionAdec(key, encryptedValue);
      }
      case Constants.T_HASH: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new OutputFunctionHash(subterm);
      }
      case Constants.T_EXP_WORD: {
        OutputTerm base = visitTerm(ctx.term(0));
        OutputTerm exponent = visitTerm(ctx.term(1));
        return new OutputExponentiation(base, exponent);
      }
      case Constants.T_MUL_WORD: {
        OutputTerm left = visitTerm(ctx.term(0));
        OutputTerm right = visitTerm(ctx.term(1));
        return new OutputMultiplication(left, right);
      }
      case Constants.T_INVERSE: {
        OutputTerm base = visitTerm(ctx.term(0));
        return new OutputFunctionInverse(base);
      }
      case Constants.T_TUPLE_WORD: {
        OutputTerm fst = visitTerm(ctx.term(0));
        OutputTerm snd = visitTerm(ctx.term(1));
        return new OutputTuple(fst, snd);
      }
    }
    Errors.DebugUnexpectedTokenType(ctx.getText(), "logging compiler visitFunction");
    return null;
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
    String number = ctx.nameID() == null ? "" : ctx.nameID().getText();
    return new OutputVariable(model, name, number);
  }
}
