package simple_tamarin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Alias;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.dataStructures.term.*;

public abstract class BuilderFormatting {
  public static String blockName(StBlock block) {
    return block.principal.name + "_" + block.rangeBegin;
  }

  public static String builtins(List<String> builtins) {
    if (builtins.isEmpty()) {
      return "";
    }

    StringBuilder result = new StringBuilder("builtins: ");
    Iterator<String> it = builtins.iterator();
    while (it.hasNext()) {
      result.append(it.next());
      if (it.hasNext()) {
        result.append(", ");
      }
    }
    result.append("\r\n");
    result.append("\r\n");
    return result.toString();
  }

  public static String alias(Alias alias) {
    return alias.left.render() + " = " + alias.right.render();
  }

  public static String fact(String name, List<? extends Term> terms, StBlock block) {
    StringBuilder result = new StringBuilder();
    result.append(name + "(");

    Iterator<? extends Term> it = terms.iterator();
    while (it.hasNext()) {
      if (block == null) {
        result.append(it.next().render());
      } else {
        result.append(it.next().render(block));
      }
      if (it.hasNext()) {
        result.append(", ");
      }
    }

    result.append(")");
    return result.toString();
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

  public static String freshFact(Term term, StBlock block) {
    return fact("Fr", term, block);
  }

  public static String inFact(Term term, StBlock block) {
    return fact("In", term, block);
  }

  public static String outFact(Term term, StBlock block) {
    return fact("Out", term, block);
  }

  public static String ruleAliases(String name, List<String> aliases) {
    StringBuilder result = new StringBuilder("rule " + name + ":\r\n");
    if (aliases.isEmpty()) {
      return result.toString();
    }

    result.append("let\r\n");
    for (String alias : aliases) {
      result.append(Constants.INDENT + alias + "\r\n");
    }
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
    StringBuilder result = new StringBuilder("");
    Iterator<String> it = facts.iterator();
    while (it.hasNext()) {
      String fact = it.next();
      result.append(Constants.INDENT + fact + (it.hasNext() ? "," : "") + "\r\n");
    }
    return result.toString();
  }

  public static String restrictionEq() {
    return 
      "restriction Equality:\r\n" +
      Constants.INDENT + "\"All x y #i. Eq(x,y) @i ==> x = y\"\r\n" +
      "\r\n"; // empty line after every restriction
  }

  public static String theoryHeader(String name) {
    return "theory " + name + "\r\n" + "begin\r\n" + "\r\n";
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
      String sortString = variable.sort == VariableSort.TEMPORAL ? "#" : "";
      result.append(" " + sortString + variable.name);
    }
    result.append(".\r\n");
    return result.toString();
  }

  public static String lemmaResultStateFact(StBlock block, Variable temporal) {
    if (temporal.sort != VariableSort.TEMPORAL) {
      System.out.println("Debug: Argument temporal is not of sort TEMPORAL in lemmaResultStateFact!");
    }
    return lemmaFact(blockName(block), block.completeState()) + " @ " + temporal.render();
  }

  public static String lemmaFact(String name, List<? extends Term> terms) {
    StringBuilder result = new StringBuilder();
    result.append(name + "(");

    Iterator<? extends Term> it = terms.iterator();
    while (it.hasNext()) {
      result.append(it.next().renderLemma());
      if (it.hasNext()) {
        result.append(", ");
      }
    }

    result.append(")");
    return result.toString();
  }

  public static String conjunction(List<String> facts) {
    Iterator<String> it = facts.iterator();
    StringBuilder result = new StringBuilder();
    while (it.hasNext()) {
      result.append(it.next());
      if (it.hasNext()) {
        result.append(" &");
      }
      result.append("\r\n");
    }
    return result.toString();
  }

  public static String lemmaEnd() {
    return "\"\r\n";
  }
}
