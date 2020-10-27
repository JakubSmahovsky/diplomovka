package simple_tamarin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.term.*;

public abstract class BuilderFormatting {
  
  public static String blockName(Principal principal, int blockNo) {
    return principal.name + "_" + blockNo;
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

  public static String alias(Variable variable) {
    if (variable.subterm == null) {
      System.out.println("Debug: Aliased variable " + variable.name + " has no subterm");
    }
    return variable + " = " + variable.subterm;
  }

  public static String fact(String name, List<Variable> variables) {
    StringBuilder result = new StringBuilder();
    result.append(name + "(");

    Iterator<Variable> it = variables.iterator();
    while (it.hasNext()) {
      result.append(it.next());
      if (it.hasNext()) {
        result.append(", ");
      }
    }

    result.append(")");
    return result.toString();
  }

  public static String fact(String name, Variable variable) {
    return fact(name, Arrays.asList(variable));
  }

  public static String persistentFact(String name, List<Variable> variables) {
    return "!" + fact(name, variables);
  }

  public static String stateFact(Principal principal, int blockNo, List<Variable> state) {
    return fact(principal.name + "_" + blockNo, state);
  }

  public static String initStateFact(Principal principal) {
    return persistentFact(principal.name + "_init", principal.initState);
  }

  public static String freshFact(Variable variable) {
    return fact("Fr", variable);
  }

  public static String inFact(Variable variable) {
    return fact("In", variable);
  }

  public static String outFact(Variable variable) {
    return fact("Out", variable);
  }

  public static String ruleAliases(String name, List<String> aliases) {
    return "rule " + name + ":\r\n" + (aliases.isEmpty()? "" : ("let\r\n" + ruleBody(aliases) + "in\r\n"));
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

  public static String lemmaStateFact(Principal principal, int blockNo, List<Variable> state, Variable temporal) {
    if (temporal.sort != VariableSort.TEMPORAL) {
      System.out.println("Debug: Argument temporal is not of sort TEMPORAL in lemmaStateFact!");
    }
    
    StringBuilder result = new StringBuilder();
    result.append(blockName(principal, blockNo) + "(");

    Iterator<Variable> it = state.iterator();
    while (it.hasNext()) {
      result.append(it.next().name);
      if (it.hasNext()) {
        result.append(", ");
      }
    }

    result.append(")" + " @ #" + temporal.name);
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
