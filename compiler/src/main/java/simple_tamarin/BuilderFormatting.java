package simple_tamarin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.Variable;

public abstract class BuilderFormatting {
  
  public static String blockName(Principal principal, int blockNo) {
    return principal.name + "_" + blockNo;
  }
  
  public static String fact(String name, List<Variable> variables) {
    StringBuilder result = new StringBuilder();
    result.append(name + "(");

    Iterator<Variable> it = variables.iterator();
    if (it.hasNext()) {
      Variable variable = it.next();
      result.append(sortString(variable.sort) + variable.name);
    }
    while (it.hasNext()) {
      Variable variable = it.next();
      result.append("," + sortString(variable.sort) + variable.name);
    }

    result.append(")");
    return result.toString();
  }

  public static String fact(String name, Variable variable) {
    return fact(name, Arrays.asList(variable));
  }

  public static String queryFact(String name, List<Variable> variables, String time) {
    StringBuilder result = new StringBuilder();
    result.append(name + "(");

    Iterator<Variable> it = variables.iterator();
    if (it.hasNext()) {
      Variable variable = it.next();
      result.append(variable.name);
    }
    while (it.hasNext()) {
      Variable variable = it.next();
      result.append(","+ variable.name);
    }

    result.append(") @" + time);
    return result.toString();
  }

  public static String persistentFact(String name, List<Variable> variables) {
    return "!" + fact(name, variables);
  }

  public static String stateFact(Principal principal, int blockNo, List<Variable> state) {
    return fact(principal.name + "_" + blockNo, state);
  }

  public static String initStateFact(Principal principal) {
    return persistentFact(principal.name + "_init", principal.variables);
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

  public static String rulePremise(String name, List<String> facts) {
    return "rule " + name + ": [\r\n" + ruleBody(facts) + "]-";
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
      result.append(fact + (it.hasNext() ? "," : "") + "\r\n");
    }
    return result.toString();
  }

  public static String initProtocol() {
    return "theory temp\r\n" + "begin\r\n" + "\r\n";
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
    return fact(blockName(principal, blockNo), state) + "@ #" + temporal.name;
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

  private static String sortString(VariableSort sort) {
    switch (sort) {
      case NOSORT: return "";
      case FRESH: return "~";
      case PUBLIC: return "$";
      case TEMPORAL: return "#";
      default: return "Invalid sort, impossible!";
    }
  }  
}
