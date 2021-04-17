package dipl.resultCompiler;

import java.util.ArrayList;

import dipl.Constants;
import dipl.dataStructures.Block;
import dipl.dataStructures.Model;
import dipl.dataStructures.outputTerm.*;
import dipl.dataStructures.query.Query;
import dipl.errors.Errors;
import dipl.resultCompiler.ResultClause.*;
import dipl.resultParser.ResultParser.*;
import dipl.sourcesCompiler.Source;
import dipl.sourcesCompiler.SourceGroup;
import dipl.sourcesCompiler.goal.*;
import dipl.sourcesCompiler.goal.factGoal.*;

public class ResultCompilerVisitor {
  private Model model;
  private Query query;

  public ResultCompilerVisitor(Model model, Query query) {
    this.model = model;
    this.query = query;
  }

  public void compile(ClauseContext ctx) {
    ResultClause trace = visitClause(ctx);
    query.trace = trace;
    System.out.println(query.success ? "DISPROVED" : "PROVED");
    System.out.println(trace.render());
  }

  public ResultClause visitClause(ClauseContext ctx) {
    if (ctx.success() != null) {
      query.success = true;
      return new ResultSuccessClause();
    }
    if (ctx.contradiction() != null) {
      return new ResultContradictionClause();
    }
    if (ctx.exhausted() != null) {
      return new ResultExhaustedClause();
    }
    if (ctx.goal().function() != null) {
      // skip splitEqs() todo? we can't get anything from it
      return visitClause(ctx.clause(0));
    }
    Goal goal = visitFact(ctx.goal().fact());
    ArrayList<SourceGroup> applicableGroups = new ArrayList<>();
    for (SourceGroup group : model.sourceGroups) {
      if (group.goal.match(goal)) {
        applicableGroups.add(group);
      }
    }
    ArrayList<ResultSource> sources = new ArrayList<>();
    for (SourceContext sctx : ctx.source()) {
      sources.add(visitSource(sctx, applicableGroups));
    }
    ArrayList<ResultClause> children = new ArrayList<>();
    for (ClauseContext cctx : ctx.clause()) {
      children.add(visitClause(cctx));
    }
    return new ResultIntermediateClause(goal, sources, children);
  }

  public ResultSource visitSource(SourceContext ctx, ArrayList<SourceGroup> groups) {
    String name = ctx.IDENTIFIER().getText();
    int caseIndex = 0;
    // if the identifier ends with "case_num", then we want to separate and parse it
    if (name.matches("[a-zA-Z0-9_]+case_[0-9]+")) {
      String[] parts = name.split("_");
      String caseIndexString = parts[parts.length-1];
      caseIndex = Integer.parseInt(caseIndexString)-1; // printed number = index + 1
      int suffixLength = "_case_".length() + caseIndexString.length();
      name = name.substring(0, name.length()-suffixLength);
    }
    ArrayList<Source> applicableSources = new ArrayList<>();
    for (SourceGroup group : groups) {
      for (Source source : group.sources) {
        if (name.equals(source.name)) {
          applicableSources.add(source);
        }
      }
    }
    return new ResultSource(applicableSources, caseIndex);
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
    } else if (!persistent && symbol.matches(Constants.FACT_PREFIX_PRINCIPALID + "[0-9]+" + Constants.NAME_SEPARATOR + "[0-9]+")) {
      Block block = model.blocks.get(Integer.parseInt(symbol.split(Constants.NAME_SEPARATOR)[1]));
      return new PrincipalRuleGoal(block, persistent, symbol, terms);
    } else {
      return new HiddenFactGoal(persistent, symbol, terms);
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
