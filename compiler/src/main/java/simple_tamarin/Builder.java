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
    restrictions();
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

    for (Variable variable : toGenerate) {
      variable.removeFresh();
    }
  }

  /**
   * Create all the regular principal blocks.
   */
  private void blocks() {
    for (Principal principal : model.principals) {
      StBlock previousBlock = null;
      for (int i = 0; i < principal.blocks.size(); i++) {
        block(previousBlock, principal.blocks.get(i));
        previousBlock = principal.blocks.get(i);
      }
    }
  }

  private void block(StBlock previousBlock,StBlock block){
    ArrayList<String> facts = new ArrayList<>();
    ArrayList<Term> generated = new ArrayList<>();
    for (Alias alias : block.aliases) {
      facts.add(alias(alias));
    }
    output.append(ruleAliases(blockName(block), facts));
    facts = new ArrayList<>();
    if (previousBlock == null) {
      facts.add(initStateFact(block.principal));
    } else {
      facts.add(resultStateFact(previousBlock));
    }
    for (Command command : block.premise) {
      switch (command.type) {
        case IN:
          facts.add(inFact(command.term));
          break;
        case FRESH:
          facts.add(freshFact(command.term));
          generated.add(command.term);
          break;
        default:
        System.out.println("Debug: Unexpected command type in premises.");
      }
    }
    output.append(rulePremise(facts));

    facts = new ArrayList<>();
    String resultStateFact = resultStateFact(block);
    facts.add(resultStateFact);
    for (ActionFact fact : block.actions) {
      facts.add(BuilderFormatting.fact(fact.name, fact.terms));
    }
    output.append(ruleAction(facts));

    facts = new ArrayList<>();
    for (Command command : block.result) {
      if (command.type == CommandType.OUT) {
        facts.add(outFact(command.term));
      } else {
        System.out.println("Debug: Unexpected command type in results.");
      }
    }
    facts.add(resultStateFact);
    output.append(ruleResult(facts));
    
    // remove fresh sort from generated variables
    for (Term term : generated) {
      term.removeFresh();
    }
  }

  private void restrictions() {
    if (model.builtins.restriction_eq) {
      output.append(restrictionEq());
    }
  }

  private void queries() {
    if (model.queries.executable) {
      executable();
    }
  }

  private void executable() {
    output.append(lemmaEx(Constants.EXECUTABLE));
    ArrayList<Variable> variables = new ArrayList<>();
    for (Principal principal : model.principals) {
      ArrayList<Term> finalState = principal.blocks.get(principal.blocks.size()-1).completeState();
      for (Term term : finalState) {
        for (Variable variable : term.extractKnowledge()) {
          if (!variables.contains(variable)) {
              variables.add(variable);
          }
        }
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
      StBlock lastBlock = principal.blocks.get(principal.blocks.size()-1);
      facts.add(lemmaResultStateFact(lastBlock, tempIt.next()));
    }
    output.append(conjunction(facts));
    output.append(lemmaEnd());
  }
}
