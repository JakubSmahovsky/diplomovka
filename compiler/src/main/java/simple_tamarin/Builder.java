package simple_tamarin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import simple_tamarin.Constants.*;
import simple_tamarin.dataStructures.*;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.errors.Errors;

/**
 * Class used for construction of Tamarin code from StModel. It consists of 2 files,
 * this one and BuilderFormatting. This class works  with logical parts 
 * of Tamarin code whereas BuilderFormatting handles all the speciffic
 * formatting of those parts.
 * BuilderFormatting is extended, not imported, to avoid extra classname before methods.
 */
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

  /**
   * Render the protocol header and declarations.
   */
  private void initProtocol() {
    output.append(theoryHeader(Constants.DEFAULTTHEORYNAME));

    ArrayList<String> builtins = new ArrayList<>();
    if (model.builtins.symmetric_encryption) {
      builtins.add(Constants.BUILTIN_SYMMETRIC_ENCRYPTION);
    }
    if (model.builtins.hashing) {
      builtins.add(Constants.BUILTIN_HASHING);
    }
    output.append(builtins(builtins));
  }

  /**
   * Create a rule that initialises the state of all principals.
   * Initialises long term variables.
   */
  private void initRule() {
    // gather fresh variables
    HashSet<Variable> toGenerate = new HashSet<>();
    for (Principal principal : model.principals) {
      for (Variable variable : principal.initState) {
        // createdBy is null when variable is static
        if (variable.cratedBy == null && variable.sort == VariableSort.FRESH) {
          toGenerate.add(variable);
        }
      }
    }

    // render fresh facts
    ArrayList<String> facts = new ArrayList<>();
    output.append(ruleAliases(Constants.INIT, new ArrayList<>()));
    for (Variable variable : toGenerate) {
      facts.add(fact(Constants.COMMANDFRESH, variable, null));
    }
    output.append(rulePremise(facts));

    // render init states of principals
    facts = new ArrayList<>();
    for (Principal principal : model.principals) {
      facts.add(initStateFact(principal, null));
    }
    output.append(ruleResult(facts));

    // remove fresh sort from variables
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
        StBlock curBlock = principal.blocks.get(i);
        block(previousBlock, curBlock);
        previousBlock = curBlock;
      }
    }
  }

  private void block(StBlock previousBlock, StBlock block){
    ArrayList<Term> generated = new ArrayList<>();
    
    // aliases
    ArrayList<String> facts = new ArrayList<>();
    for (Alias alias : block.aliases) {
      facts.add(alias(alias));
    }
    output.append(ruleAliases(blockName(block), facts));

    // premise
    facts = new ArrayList<>();
    if (previousBlock == null) {
      facts.add(initStateFact(block.principal, null));
    } else {
      facts.add(resultStateFact(previousBlock, block));
    }
    for (Command command : block.premise) {
      switch (command.type) {
        case IN:
          facts.add(fact(command.toString(), command.term, block));
          break;
        case FRESH:
          facts.add(fact(command.toString(), command.term, null));
          generated.add(command.term);
          break;
        default:
          Errors.DebugCommandType(command.toString(), "premises of block()");
      }
    }
    output.append(rulePremise(facts));

    // actions
    facts = new ArrayList<>();
    String resultStateFact = resultStateFact(block, block);
    facts.add(resultStateFact);
    for (Fact fact : block.actions) {
      facts.add(fact.render(block));
    }
    output.append(ruleAction(facts));

    // results
    facts = new ArrayList<>();
    for (Command command : block.result) {
      if (command.type == CommandType.OUT) {
        facts.add(fact(command.toString(), command.term, block));
      } else {
        Errors.DebugCommandType(command.toString(), "results of block()");
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

  /**
   * Create lemma saying that all principals are able to reach
   * their final state. 
   */
  private void executable() {
    output.append(lemmaEx(Constants.EXECUTABLE));
    ArrayList<Variable> variables = new ArrayList<>();
    for (Principal principal : model.principals) {
      ArrayList<Term> finalState = principal.blocks.get(principal.blocks.size()-1).completeState();
      for (Term term : finalState) {
        for (Variable variable : term.freeVariables()) {
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
      StBlock finalBlock = principal.blocks.get(principal.blocks.size()-1);
      facts.add(lemmaResultStateFact(finalBlock, tempIt.next()));
    }
    output.append(conjunction(facts));
    output.append(lemmaEnd());
  }
}
