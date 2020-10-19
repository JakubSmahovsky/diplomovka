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
    queries();
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
      facts.add(persistentFact(principal.name + "_0", principal.initState));
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
      ArrayList<Variable> currState = new ArrayList<>(principal.initState);
      int blockNo = 0;
      for (StBlock block : principal.blocks) {
        ArrayList<String> facts = new ArrayList<>();
        if (blockNo == 0) {
          facts.add(persistentFact(principal.name + "_" + blockNo, currState));
        } else {
          facts.add(fact(principal.name + "_" + blockNo, currState));  
        }
        for (Command command : block.premise) {
          switch (command.type) {
            case IN: 
              facts.add(fact("In", Arrays.asList(command.variable)));
              if (!currState.contains(command.variable)) {
                currState.add(command.variable);
              }
              break;
            case FRESH:
              facts.add(fact("Fr", Arrays.asList(command.variable)));
              if (!currState.contains(command.variable)) {
                currState.add(command.variable);
              }
              break;
            default: System.out.println("Debug: Unexpected command type in premises.");
          }
        }
        rulePremise(principal.name + "_" + (blockNo+1), facts);

        String resultStateFact = fact(principal.name + "_" + (blockNo+1), currState);
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
        block.finalState = new ArrayList<>(currState);
        blockNo+=1;
      }
    }
  }

  private void queries() {
    if (model.queries.executable) {
      executable();
    }
  }

  private void executable() {
    output.append("lemma executable: \r\n");
    output.append("exists-trace \"\r\n");
    HashSet<Variable> variables = new HashSet<>();
    for (Principal principal : model.principals) {
      for (Variable variable : principal.blocks.get(principal.blocks.size()-1).finalState) {
        variables.add(variable);
      }
    }
    output.append("Ex");
    for (Variable variable : variables) {
      output.append(" " + variable.name);
    }
    for (int principalNo = 0; principalNo < model.principals.size(); principalNo ++) {
      output.append(" #time" + principalNo);
    }
    output.append(" .\r\n");

    int principalNo = 0;
    Iterator<Principal> it = model.principals.iterator();
    while (it.hasNext()) {
      Principal principal = it.next();
      int lastBlockIndex = principal.blocks.size()-1;
      String factName = principal.name + "_" + (lastBlockIndex+1);
      String timeName = "#time" + principalNo;
      ArrayList<Variable> finalState = principal.blocks.get(lastBlockIndex).finalState;
      String conjunction = it.hasNext() ? " &" : "";
      output.append(queryFact(factName, finalState, timeName) + conjunction + "\r\n");
      principalNo += 1;
    }
    output.append("\"\r\n");
    output.append("\r\n");
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

    result.append(")");
    return result.toString();
  }

  private String queryFact(String name, Iterable<Variable> variables, String time) {
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

  private String persistentFact(String name, Iterable<Variable> variables) {
    return "!" + fact(name, variables);
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
