package simple_tamarin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import simple_tamarin.Constants.VariableSort;
import simple_tamarin.dataStructures.*;

public class Builder {
  public StringBuilder output;
  public StModel model;

  public Builder(StModel model){
    this.output = new StringBuilder();
    this.model = model;
    this.build();
  }

  public String output(){
    return output.toString();
  }

  private void build() {
    initProtocol();
    initRule();
    blocks();
    endProtocol();
  }

  /**
   * Create a rule that initialises the state of all principals.
   * Initialises static known variables.
   */
  private void initRule() {
    HashSet<Variable> toGenerate = new HashSet<>();
    
    for (Principal principal : model.principals) {
      for (Variable variable : principal.initState) {
        if (variable.cratedBy == null && variable.sort == VariableSort.FRESH) {
          toGenerate.add(variable);
        }
      }
    }

    ArrayList<String> facts = new ArrayList<>();
    for (Variable variable : toGenerate) {
      facts.add(fact("Fr", Arrays.asList(variable)));
    }
    rulePremise("init", facts);

    facts = new ArrayList<>();
    for (Principal principal : model.principals) {
      facts.add(fact(principal.name + "_0", principal.initState));
    }
    ruleResult(facts);

    for (Principal principal : model.principals) {
      for (Variable variable : principal.initState) {
        if (variable.sort == VariableSort.FRESH) {
          variable.sort = VariableSort.NOSORT;
        }
      };
    }
  }

  /**
   * Create all the regular principal blocks.
   */
  private void blocks() {
    for (Principal principal : model.principals) {
      HashSet<Variable> currState = new HashSet<>(principal.initState);
      int blocki = 0;
      for (StBlock block : principal.blocks) {
        ArrayList<String> facts = new ArrayList<>();
        facts.add(fact(principal.name + "_" + blocki, currState));
        for (Command command : block.premise) {
          switch (command.type) {
            case IN: 
              facts.add(fact("In", Arrays.asList(command.variable)));
              currState.add(command.variable);
              break;
            case FRESH:
              facts.add(fact("Fr", Arrays.asList(command.variable)));
              currState.add(command.variable);
              break;
            default: System.out.println("Debug: Unexpected command type in premises.");
          }
        }
        rulePremise(principal.name + "_" + (blocki+1), facts);

        String resultStateFact = fact(principal.name + "_" + (blocki+1), currState);
        ruleAction(Arrays.asList(resultStateFact));

        facts = new ArrayList<>();
        for (Command command : block.result) {
          switch (command.type) {
            case OUT: 
              facts.add(fact("Out", Arrays.asList(command.variable)));
              break;
            default: System.out.println("Debug: Unexpected command type in results.");
          }
        }
        facts.add(resultStateFact);
        ruleResult(facts);
        
        // remove fresh sort from generated variables
        for (Variable variable : currState) {
          if (variable.sort == VariableSort.FRESH) {
            variable.sort = VariableSort.NOSORT;
          }
        }

        blocki+=1;
      }
    }
  }

  private void rulePremise(String name, List<String> facts) {
    output.append("rule " + name + ": [\r\n");
    ruleBody(facts);
    output.append("]-");
  }
  private void ruleAction(List<String> facts){
    output.append("-[\r\n");
    ruleBody(facts);
    output.append("]");
  }
  private void ruleResult(List<String> facts){
    output.append("->[\r\n");
    ruleBody(facts);
    output.append("]\r\n");
    output.append("\r\n");
  }

  private void ruleBody(List<String> facts) {
    Iterator<String> it = facts.iterator();
    while (it.hasNext()) {
      String fact = it.next();
      output.append(fact + (it.hasNext() ? "," : "") + "\r\n");
    }
  }

  private void initProtocol() {
    output.append("theory temp\r\n"); // TODO theory name
    output.append("begin\r\n");
    output.append("\r\n");
  }

  private void endProtocol() {
    output.append("end\r\n");
  }

  private String fact(String name, Iterable<Variable> variables) {
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

    result.append(')');
    return result.toString();
  }

  private String sortString(VariableSort sort) {
    switch (sort) {
      case NOSORT: return "";
      case FRESH: return "~";
      case PUBLIC: return "$";
      case TEMPORAL: return "#";
      default: return "Invalid sort, impossible!";
    }
  }
}
