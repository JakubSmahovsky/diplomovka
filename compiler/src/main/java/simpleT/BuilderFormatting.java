package simpleT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import simpleT.dataStructures.Principal;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.term.*;

/**
 * Part of Builder (see Builder) used to define methods working with strings.
 * Any constant strings should only used here. Builder itself works with logical
 * parts of Tamarin code whereas this class handles all the speciffic
 * formatting of those parts.
 * Other classes should also use methods of this class for output formatting where
 * needed in order to keep output uniform.
 */
public abstract class BuilderFormatting {
  public static String builtins(List<String> builtins) {
    if (builtins.isEmpty()) {
      return "";
    }
    return Constants.CLAUSE_BUILTINS + String.join(Constants.COMMA_SEPARATOR, builtins) + Constants.LINE_BREAK + Constants.LINE_BREAK;
  }

  public static String alias(String left, String right) {
    return left + Constants.ALIAS_OPERATOR + right;
  }

  public static String fact(String name, List<? extends Term> terms, STBlock block) {
    ArrayList<String> renders = new ArrayList<>();
    for (Term term : terms) {
      renders.add(block == null ? term.render() : term.render(block));
    }
    return name + Constants.OPEN_BR + String.join(Constants.COMMA_SEPARATOR, renders) + Constants.CLOSE_BR;
  }

  public static String fact(String name, Term term, STBlock block) {
    return fact(name, Arrays.asList(term), block);
  }

  public static String persistentFact(String name, List<? extends Term> terms, STBlock block) {
    return Constants.PERSISTENT + fact(name, terms, block);
  }

  public static String persistentFact(String name, Term term, STBlock block) {
    return Constants.PERSISTENT + fact(name, Arrays.asList(term), block);
  }

  /**
   * @param sourceBlock is block whose state we are rendering
   * @param contextBlock is block whose body requires the state fact
   */
  public static String blockStateFact(STBlock sourceBlock, STBlock contextBlock) {
    return fact(sourceBlock.render(), sourceBlock.completeState(), contextBlock);
  }

  public static String instanceStateFact(Principal principal) {
    String factName = principal.render() + Constants.NAME_SEPARATOR + Constants.RULE_INSTANCE;
    return persistentFact(factName , principal.composeInstanceState(), null);
  }

  public static String sessionStateFact(Principal principal) {
    String factName = principal.render() + Constants.NAME_SEPARATOR + Constants.RULE_SESSION;
    return fact(factName, principal.composeSessionState(), null);
  }

  public static String LongTermPrivateListFact(Principal principal) {
    String factName = principal.render() + Constants.NAME_SEPARATOR + Constants.FACT_LONG_TERM_PRIVATE;
    ArrayList<Variable> LTP = new ArrayList<>();
    LTP.add(principal.principalID);
    LTP.addAll(principal.getLongTermPrivate());
    return persistentFact(factName, LTP, null);
  }

  public static String ruleAliases(STBlock block, List<String> aliases, String label) {
    if (label == null){
      label = block.render();
    }
    StringBuilder result = new StringBuilder(Constants.CLAUSE_RULE + label + Constants.COLON + Constants.LINE_BREAK);
    if (aliases.isEmpty()) {
      return result.toString();
    }
    result.append(Constants.ALIASES_OPEN);
    result.append(String.join(Constants.LINE_BREAK, indent(aliases)) + Constants.LINE_BREAK);
    result.append(Constants.ALIASES_CLOSE);
    return result.toString();
  }

  public static String rulePremise(List<String> facts) {
    return Constants.PREMISES_OPEN + ruleBody(facts) + Constants.PREMISES_CLOSE;
  }
  
  public static String ruleAction(List<String> facts){
    return Constants.ACTIONS_OPEN + ruleBody(facts) + Constants.ACTIONS_CLOSE;
  }

  public static String ruleConclusions(List<String> facts){
    return Constants.CONCLUSIONS_OPEN + ruleBody(facts) + Constants.CONCLUSIONS_CLOSE;
  }

  public static String ruleBody(List<String> facts) {
    return String.join(Constants.FACT_SEPARATOR, indent(facts)) + Constants.LINE_BREAK;
  }

  public static String revealRule(Principal principal) {
    String ruleName = principal.render() + Constants.NAME_SEPARATOR + Constants.RULE_LONG_TERM_REVEAL;
    String factName = principal.render() + Constants.NAME_SEPARATOR + Constants.FACT_LONG_TERM_PRIVATE;

    ArrayList<Variable> ltp = new ArrayList<>();
    ltp.add(principal.principalID);
    ltp.addAll(principal.getLongTermPrivate());
    String LTPListFact = persistentFact(factName, ltp, null);

    ArrayList<String> outputs = new ArrayList<>();
    for (Variable variable : principal.getLongTermPrivate()) {
      outputs.add(fact(Constants.COMMAND_OUT, variable, null));
    }
    return
      ruleAliases(null, new ArrayList<>(), ruleName) +
      rulePremise(Arrays.asList(LTPListFact)) +
      ruleAction(Arrays.asList(fact(Constants.FACT_DISHONEST, principal.principalID, null))) +
      ruleConclusions(outputs);
  }

  public static String lemma(String name, boolean existsTrace) {
    return Constants.CLAUSE_LEMMA + name + Constants.COLON + Constants.LINE_BREAK
    + (existsTrace ? Constants.QUANTIFIER_TRACE_EXISTS : Constants.QUANTIFIER_TRACE_FORALL) + Constants.LEMMA_OPEN;
  }

  public static String lemmaQuatification(Collection<Variable> variables, boolean exQuantifier){
    ArrayList<String> identifiers = new ArrayList<>();
    for (Variable variable : variables) {
      identifiers.add(variable.render());
    }
    return (exQuantifier ? Constants.QUANTIFIER_EXISTS : Constants.QUANTIFIER_FORALL) + 
      String.join(Constants.QUANTIFICATION_SEPARATOR, identifiers) + Constants.QUANTIFICATION_CLOSE;
  }

  public static String lemmaBlockStateFact(STBlock block, Variable temporal) {
    return lemmaFact(block.render(), block.completeState(), temporal);
  }

  public static String lemmaFact(String name, List<? extends Term> terms, Variable temporal) {
    return fact(name, terms, null) + atTemporal(temporal);
  }

  public static String lemmaFact(String name, Term term, Variable temporal) {
    return lemmaFact(name, Arrays.asList(term), temporal);
  }

  public static String negation(String fact) {
    return Constants.LEMMA_NEGAION + fact;
  }

  public static String conjunction(List<String> facts) {
    return String.join(Constants.LEMMA_CONJUNCTION, facts);
  }

  public static String disjunction(List<String> facts) {
    return String.join(Constants.LEMMA_DISJUNCTION, facts);
  }

  public static String implication(String from, String to) {
    return from + Constants.LEMMA_IMPLICATION + to;
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

  public static List<String> indent(List<String> strings) {
    ArrayList<String> result = new ArrayList<>();
    for (String string : strings) {
      result.add(Constants.INDENTATION + string);
    }
    return result;
  }
}
