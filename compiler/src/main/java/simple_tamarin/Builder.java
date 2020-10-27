package simple_tamarin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import simple_tamarin.Constants.*;
import simple_tamarin.dataStructures.*;
import simple_tamarin.dataStructures.term.*;

public class Builder extends BuilderFormatting{
  public StringBuilder output;
  public StModel model;

  public Builder(StModel model){
    this.output = new StringBuilder();
    this.model = model;
    
    initProtocol();
    initRule();
    blocks();
    queries();
    output.append(endProtocol());
  }

  public String output(){
    return output.toString();
  }

  private void initProtocol() {
    output.append(theoryHeader(Constants.DEFAULTTHEORYNAME));

    ArrayList<String> builtins = new ArrayList<>();
    if (model.builtins.symmetric_encryption = true) {
      builtins.add(Constants.BUILTIN_SYMMETRIC_ENCRYPTION);
    }
    output.append(builtins(builtins));
  }

  /**
   * Create a rule that initialises the state of all principals.
   * Initialises long term variables.
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
    output.append(ruleAliases(Constants.INIT, facts));
    for (Variable variable : toGenerate) {
      facts.add(freshFact(variable));
    }
    output.append(rulePremise(facts));

    facts = new ArrayList<>();
    for (Principal principal : model.principals) {
      facts.add(initStateFact(principal));
    }
    output.append(ruleResult(facts));

    for (Principal principal : model.principals) {
      for (Variable variable : principal.initState) {
        if (variable.sort == VariableSort.FRESH) {
          variable.sort = VariableSort.NOSORT;
        }
      }
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
        for (Variable variable : block.aliases) {
          facts.add(alias(variable));
        }
        output.append(ruleAliases(blockName(principal, blockNo), facts));
        facts = new ArrayList<>();
        if (blockNo == 0) {
          facts.add(initStateFact(principal));
        } else {
          facts.add(stateFact(principal, blockNo, currState));
        }
        for (Command command : block.premise) {
          switch (command.type) {
            case IN:
              facts.add(inFact(command.variable));
              if (!currState.contains(command.variable)) {
                currState.add(command.variable);
              }
              break;
            case FRESH:
              facts.add(freshFact(command.variable));
              if (!currState.contains(command.variable)) {
                currState.add(command.variable);
              }
              break;
            case SDEC:
              if (!currState.contains(command.variable)) {
                currState.add(command.variable);
              }
              break;
            default: System.out.println("Debug: Unexpected command type in premises.");
          }
        }
        output.append(rulePremise(facts));
        blockNo+=1; // after premises we're working with the "next message" block

        facts = new ArrayList<>();
        String resultStateFact = stateFact(principal, blockNo, currState);
        facts.add(resultStateFact);
        output.append(ruleAction(facts));

        facts = new ArrayList<>();
        for (Command command : block.result) {
          switch (command.type) {
            case OUT: 
              facts.add(outFact(command.variable));
              break;
            default: System.out.println("Debug: Unexpected command type in results.");
          }
        }
        facts.add(resultStateFact);
        output.append(ruleResult(facts));
        
        // remove fresh sort from generated variables
        for (Variable variable : currState) {
          if (variable.sort == VariableSort.FRESH) {
            variable.sort = VariableSort.NOSORT;
          }
        }
        block.finalState = new ArrayList<>(currState);
      }
    }
  }

  private void queries() {
    if (model.queries.executable) {
      executable();
    }
  }

  private void executable() {
    output.append(lemmaEx(Constants.EXECUTABLE));
    HashSet<Variable> variables = new HashSet<>();
    for (Principal principal : model.principals) {
      for (Variable variable : principal.blocks.get(principal.blocks.size()-1).finalState) {
        variables.add(variable);
      }
    }
    ArrayList<Variable> temporals = new ArrayList<>();
    for (int principalNo = 0; principalNo < model.principals.size(); principalNo ++) {
      Variable t = Variable.nextTemporal();
      variables.add(t);
      temporals.add(t);
    }
    output.append(ExVariables(variables));

    ArrayList<String> facts = new ArrayList<>();
    Iterator<Variable> tempIt = temporals.iterator();
    for (Principal principal : model.principals) {
      int lastBlockNo = principal.blocks.size();
      ArrayList<Variable> finalState = principal.blocks.get(lastBlockNo-1).finalState;
      facts.add(lemmaStateFact(principal, lastBlockNo, finalState, tempIt.next()));
    }
    output.append(conjunction(facts));
    output.append(lemmaEnd());
  }
}
