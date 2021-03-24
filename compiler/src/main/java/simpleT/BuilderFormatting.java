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
    return "builtins: " + String.join(", ", builtins) + "\r\n\r\n";
  }

  public static String alias(String left, String right) {
    return left + " = " + right;
  }

  public static String fact(String name, List<? extends Term> terms, STBlock block) {
    ArrayList<String> renders = new ArrayList<>();
    for (Term term : terms) {
      renders.add(block == null ? term.render() : term.render(block));
    }
    return name + "(" + String.join(", ", renders) + ")";
  }

  public static String fact(String name, Term term, STBlock block) {
    return fact(name, Arrays.asList(term), block);
  }

  public static String persistentFact(String name, List<Variable> variables, STBlock block) {
    return "!" + fact(name, variables, block);
  }

  /**
   * @param sourceBlock is block whose state we are rendering
   * @param contextBlock is block whose body requires the state fact
   */
  public static String resultStateFact(STBlock sourceBlock, STBlock contextBlock) {
    return fact(sourceBlock.render(), sourceBlock.completeState(), contextBlock);
  }

  public static String initStateFact(Principal principal) {
    return persistentFact(principal.render() + "_init", principal.composeInitState(), null);
  }

  /**
   * @param block is null for init block
   */
  public static String ruleAliases(STBlock block, List<String> aliases) {
    String label = block == null ? Constants.INIT : block.render();
    StringBuilder result = new StringBuilder("rule " + label + ":\r\n");
    if (aliases.isEmpty()) {
      return result.toString();
    }
    result.append("let\r\n");
    result.append(String.join("\r\n", indent(aliases)) + "\r\n");
    result.append("in\r\n");
    return result.toString();
  }

  public static String rulePremise(List<String> facts) {
    return "[\r\n" + ruleBody(facts) + "]-";
  }
  
  public static String ruleAction(List<String> facts){
    return "-[\r\n" + ruleBody(facts) + "]";
  }

  public static String ruleResult(List<String> facts){
    return "->[\r\n" + ruleBody(facts) + "]\r\n" + "\r\n"; // empty line after end of each rule
  }

  public static String ruleBody(List<String> facts) {
    return String.join(",\r\n", indent(facts)) + "\r\n";
  }

  public static String theoryHeader(String name) {
    return "theory " + name + "\r\nbegin\r\n\r\n";
  }
  
  public static String endProtocol() {
    return "end\r\n";
  }

  public static String lemma(String name, boolean existsTrace) {
    return "lemma " + name + ":\r\n" + (existsTrace ? "exists-trace" : "all-traces") + " \"\r\n";
  }

  public static String lemmaVariables(Collection<Variable> variables, boolean exQuantifier){
    StringBuilder result = new StringBuilder(exQuantifier ? "Ex" : "All");
    for (Variable variable : variables) {
      result.append(" " + variable.render());
    }
    result.append(".");
    return result.toString();
  }

  public static String lemmaResultStateFact(STBlock block, Variable temporal) {
    return lemmaFact(block.render(), block.completeState(), temporal);
  }

  public static String lemmaFact(String name, List<? extends Term> terms, Variable temporal) {
    return fact(name, terms, null) + " " + atTemporal(temporal);
  }

  public static String lemmaFact(String name, Term term, Variable temporal) {
    return lemmaFact(name, Arrays.asList(term), temporal);
  }

  public static String negation(String fact) {
    return "not " + fact;
  }

  public static String conjunction(List<String> facts) {
    return String.join(" &\r\n", facts);
  }

  public static String disjunction(List<String> facts) {
    return String.join(" |\r\n", facts);
  }

  public static String implication(String from, String to) {
    return from + "\r\n" + "==>\r\n" + to;
  }

  public static String dishonest(Principal principal, Variable temporal) {
    String dishonest = lemmaFact(Constants.FACT_DISHONEST, principal.principalID, temporal);
    return lemmaVariables(Arrays.asList(temporal), true) + dishonest;
  }

  public static String bracket(String statement) {
    return "(" + statement + ")";
  }

  public static String beforeAfter(Variable temporalBefore, Variable temporalAfter) {
    return temporalBefore.render() + "<" + temporalAfter.render();
  }

  public static String lemmaEnd() {
    return "\"\r\n";
  }

  public static String atTemporal(Variable temporal) {
    return "@ " + temporal.render();
  }

  public static List<String> indent(List<String> strings) {
    ArrayList<String> result = new ArrayList<>();
    for (String string : strings) {
      result.add(Constants.INDENT + string);
    }
    return result;
  }

  public static String lineBreak() {
    return "\r\n";
  }
}
