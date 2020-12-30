package simple_tamarin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Alias;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.errors.Errors;

/**
 * Part of Builder (see Builder) used to define methods working with strings.
 * Any constant strings should only used here. Builder itself works with logical
 * parts of Tamarin code whereas this class handles all the speciffic
 * formatting of those parts.
 * Other classes should also use methods of this class for output formatting where
 * needed in order to keep output uniform.
 */
public abstract class BuilderFormatting {
  public static String blockName(StBlock block) {
    return block.principal.name + "_" + block.rangeBegin;
  }

  public static String builtins(List<String> builtins) {
    return "builtins: " + String.join(", ", builtins) + "\r\n\r\n";
  }

  public static String alias(Alias alias) {
    return alias.left.render() + " = " + alias.right.render();
  }

  public static String fact(String name, List<? extends Term> terms, StBlock block) {
    ArrayList<String> renders = new ArrayList<>();
    for (Term term : terms) {
      renders.add(block == null ? term.render() : term.render(block));
    }
    return name + "(" + String.join(", ", renders) + ")";
  }

  public static String fact(String name, Term term, StBlock block) {
    return fact(name, Arrays.asList(term), block);
  }

  public static String persistentFact(String name, List<Variable> variables, StBlock block) {
    return "!" + fact(name, variables, block);
  }

  /**
   * @param sourceBlock is block whose state we are rendering
   * @param contextBlock is block whose body requires the state fact
   */
  public static String resultStateFact(StBlock sourceBlock, StBlock contextBlock) {
    return fact(blockName(sourceBlock), sourceBlock.completeState(), contextBlock);
  }

  public static String initStateFact(Principal principal, StBlock block) {
    return persistentFact(principal.name + "_init", principal.initState, block);
  }

  public static String ruleAliases(String name, List<String> aliases) {
    StringBuilder result = new StringBuilder("rule " + name + ":\r\n");
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

  public static String restrictionEq() {
    return Constants.RESTRICTION_EQUALITY;
  }

  public static String theoryHeader(String name) {
    return "theory " + name + "\r\nbegin\r\n\r\n";
  }
  
  public static String endProtocol() {
    return "end\r\n";
  }

  public static String lemmaEx(String name) {
    return "lemma " + name + ":\r\n" + 
      "exists-trace \"\r\n";
  }

  public static String ExVariables(Collection<Variable> variables){
    StringBuilder result = new StringBuilder("Ex");
    for (Variable variable : variables) {
      result.append(" " + variable.renderLemma());
    }
    result.append(".\r\n");
    return result.toString();
  }

  public static String lemmaResultStateFact(StBlock block, Variable temporal) {
    return lemmaFact(blockName(block), block.completeState()) + " " + atTemporal(temporal);
  }

  public static String lemmaFact(String name, List<? extends Term> terms) {
    ArrayList<String> renders = new ArrayList<>();
    for (Term term : terms) {
      renders.add(term.renderLemma());
    }
    return name + "(" + String.join(", ", renders) + ")";
  }

  public static String lemmaFact(String name, Term term) {
    return lemmaFact(name, Arrays.asList(term));
  }

  public static String conjunction(List<String> facts) {
    return String.join(" &\r\n", facts) + "\r\n";
  }

  public static String lemmaEnd() {
    return "\"\r\n";
  }

  public static String atTemporal(Variable temporal) {
    if (temporal.sort != VariableSort.TEMPORAL) {
      Errors.DebugNotTemporal("atTemporal");
    }
    return "@ " + temporal.render();
  }

  public static List<String> indent(List<String> strings) {
    ArrayList<String> result = new ArrayList<>();
    for (String string : strings) {
      result.add(Constants.INDENT + string);
    }
    return result;
  }
}
