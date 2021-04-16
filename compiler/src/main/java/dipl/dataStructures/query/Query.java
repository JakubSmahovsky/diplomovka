package dipl.dataStructures.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import dipl.BuilderFormatting;
import dipl.Constants;
import dipl.dataStructures.Principal;
import dipl.dataStructures.Block;
import dipl.dataStructures.Model;
import dipl.dataStructures.document.Document;
import dipl.dataStructures.term.*;
import dipl.resultCompiler.ResultClause.ResultClause;

public abstract class Query {
  private static int nextQueryID = 0;
  protected final Model model;
  protected final int queryID;
  // result
  public boolean success;
  public ResultClause trace;

  public Query(Model model) {
    this.model = model;
    this.queryID = nextQueryID++;
    this.success = false;
  }

  public abstract String renderLabel();

  public abstract Document render();

  public static Document lemma(String name, boolean existsTrace) {
    String quantifier = (existsTrace ? Constants.QUANTIFIER_TRACE_EXISTS : Constants.QUANTIFIER_TRACE_FORALL);
    return 
      new Document(Constants.CLAUSE_LEMMA + name + Constants.COLON)
      .append(quantifier + Constants.LEMMA_OPEN);
  }

  public static String lemmaQuatification(Collection<Variable> variables, boolean exQuantifier){
    ArrayList<String> identifiers = new ArrayList<>();
    for (Variable variable : variables) {
      identifiers.add(variable.render());
    }
    return (exQuantifier ? Constants.QUANTIFIER_EXISTS : Constants.QUANTIFIER_FORALL) + 
      String.join(Constants.QUANTIFICATION_SEPARATOR, identifiers) + Constants.QUANTIFICATION_CLOSE;
  }

  public static String lemmaBlockStateFact(Block block, Variable temporal) {
    return lemmaFact(block.render(), block.completeState(), temporal);
  }

  public static String lemmaFact(String name, List<? extends Term> terms, Variable temporal) {
    return BuilderFormatting.fact(name, terms, null) + atTemporal(temporal);
  }

  public static String lemmaFact(String name, Term term, Variable temporal) {
    return lemmaFact(name, Arrays.asList(term), temporal);
  }

  public static String negation(String fact) {
    return Constants.LEMMA_NEGAION + fact;
  }

  public static Document conjunction(List<String> facts) {
    Document conjunction = new Document();
    for (int i = 0; i < facts.size()-1; i++) {
      conjunction.append(facts.get(i) + Constants.LEMMA_CONJUNCTION);
    }
    conjunction.append(facts.get(facts.size()-1));
    return conjunction;
  }

  public static Document disjunction(List<String> facts) {
    Document conjunction = new Document();
    for (int i = 0; i < facts.size()-1; i++) {
      conjunction.append(facts.get(i) + Constants.LEMMA_DISJUNCTION);
    }
    conjunction.append(facts.get(facts.size()-1));
    return conjunction;
  }

  public static String dishonest(Principal principal, Variable temporal) {
    String dishonest = lemmaFact(Constants.FACT_DISHONEST, principal.principalID, temporal);
    return lemmaQuatification(Arrays.asList(temporal), true) + dishonest;
  }

  public static String bracket(String statement) {
    return Constants.OPEN_BR + statement + Constants.CLOSE_BR;
  }

  public static String beforeAfter(Variable temporalBefore, Variable temporalAfter) {
    return temporalBefore.render() + Constants.LEMMA_BEFORE + temporalAfter.render();
  }

  public static String lemmaEquals(Variable v1, Variable v2) {
    return v1.render() + Constants.LEMMA_EQUALS + v2.render();
  }

  public static String atTemporal(Variable temporal) {
    return Constants.LEMMA_ATTEMPORAL + temporal.render();
  }
}
