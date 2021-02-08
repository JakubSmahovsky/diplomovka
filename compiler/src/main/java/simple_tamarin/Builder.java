package simple_tamarin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import simple_tamarin.Constants.*;
import simple_tamarin.dataStructures.*;
import simple_tamarin.dataStructures.query.Confidentiality;
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
    prefabs();
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
    output.append(theoryHeader(Constants.DEFAULT_THEORY_NAME));

    ArrayList<String> builtins = new ArrayList<>();
    if (model.builtins.symmetric_encryption) {
      builtins.add(Constants.BUILTIN_SYMMETRIC_ENCRYPTION);
    }
    if (model.builtins.hashing) {
      builtins.add(Constants.BUILTIN_HASHING);
    }
    if (model.builtins.diffie_hellman) {
      builtins.add(Constants.BUILTIN_DH);
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
    for (Principal principal : model.getPrincipals()) {
      for (Variable variable : principal.initState) {
        // private static variables are generated in init block 
        if (variable.sort != VariableSort.PUBLIC && variable.cratedBy == null) {
          variable.addFresh();
          toGenerate.add(variable);
        }
      }
    }
    model.runID.addFresh();
    toGenerate.add(model.runID);

    // gather principal IDs (to bind principals with runID)
    ArrayList<Variable> principalIDs = new ArrayList<>();
    principalIDs.add(model.runID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }

    // render fresh facts
    ArrayList<String> facts = new ArrayList<>();
    output.append(ruleAliases(null, new ArrayList<>()));
    for (Variable variable : toGenerate) {
      facts.add(fact(Constants.COMMAND_FRESH, variable, null));
    }
    output.append(rulePremise(facts));

    // render action facts
    facts = new ArrayList<>();
    facts.add(fact(Constants.FACT_PRINCIPALS, principalIDs, null));
    output.append(ruleAction(facts));

    // render init states of principals and initResults
    facts = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      facts.add(initStateFact(principal, null));
      for (Fact fact : principal.initResults) {
        facts.add(fact.render(null));
      }
    }
    output.append(ruleResult(facts));

    // remove fresh sort from variables
    for (Variable variable : toGenerate) {
      variable.removeFresh();
    }
    model.runID.removeFresh();
  }

  /**
   * Create all the regular principal blocks.
   */
  private void blocks() {
    for (Principal principal : model.getPrincipals()) {
      StBlock previousBlock = null;
      for (StBlock curBlock : principal.getBlocks()) {
        block(previousBlock, curBlock);
        previousBlock = curBlock;
      }
    }
  }

  private void block(StBlock previousBlock, StBlock block){
    ArrayList<Term> generated = new ArrayList<>();

    // premises
    ArrayList<String> premises = new ArrayList<>();
    if (previousBlock == null) {
      premises.add(initStateFact(block.principal, null));
    } else {
      premises.add(resultStateFact(previousBlock, block));
    }
    for (Command command : block.premise) {
      switch (command.type) {
        case IN:
          premises.add(fact(command.toString(), command.term, block));
          break;
        case FRESH:
          command.term.addFresh();
          generated.add(command.term);
          premises.add(fact(command.toString(), command.term, null));
          break;
        default:
          Errors.DebugCommandType(command.toString(), "premises of block()");
      }
    }

    // actions
    ArrayList<String> actions = new ArrayList<>();
    String resultStateFact = resultStateFact(block, block);
    actions.add(resultStateFact);
    for (Fact fact : block.actions) {
      actions.add(fact.render(block));
    }

    // results
    ArrayList<String> results = new ArrayList<>();
    for (Command command : block.result) {
      if (command.type == CommandType.OUT) {
        results.add(fact(command.toString(), command.term, block));
      } else {
        Errors.DebugCommandType(command.toString(), "results of block()");
      }
    }
    results.add(resultStateFact);

    // aliases; they need to be rendered last, cause they are affected by other parts
    ArrayList<String> aliases = new ArrayList<>();
    for (Alias alias : block.aliases) {
      aliases.add(alias(alias));
    }

    output.append(ruleAliases(block, aliases));
    output.append(rulePremise(premises));
    output.append(ruleAction(actions));
    output.append(ruleResult(results));
    
    // remove fresh sort from generated variables
    for (Term term : generated) {
      term.removeFresh();
    }
  }

  private void prefabs() {
    if (model.builtins.prefab_private_reveal) {
      output.append(Constants.PREFAB_PRIVATE_REVEAL);
    }
  }

  private void restrictions() {
    if (model.builtins.restriction_eq) {
      output.append(Constants.RESTRICTION_EQUALITY);
    }
  }

  private void queries() {
    if (model.queries.executable) {
      executable();
      output.append(lineBreak());
    }
    for (Confidentiality query : model.queries.confidentiality) {
      confidentiality(query);
      output.append(lineBreak());
    }
  }

  /**
   * Create lemma saying that all principals are able to reach
   * their final state. 
   */
  private void executable() {
    output.append(lemma(Constants.EXECUTABLE, true));
    ArrayList<Variable> variables = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      ArrayList<Term> finalState = principal.getLastBlock().completeState();
      for (Term term : finalState) {
        for (Variable variable : term.freeVariables()) {
          if (!variables.contains(variable)) {
              variables.add(variable);
          }
        }
      }
    }
    ArrayList<Variable> temporals = new ArrayList<>();
    for (int principalNo = 0; principalNo < model.getPrincipals().size(); principalNo ++) {
      Variable t = Variable.nextTemporal();
      variables.add(t);
      temporals.add(t);
    }
    output.append(lemmaVariables(variables, true));
    output.append(lineBreak());

    ArrayList<String> facts = new ArrayList<>();
    Iterator<Variable> tempIt = temporals.iterator();
    for (Principal principal : model.getPrincipals()) {
      facts.add(lemmaResultStateFact(principal.getLastBlock(), tempIt.next()));
      facts.add(honest(principal));
    }

    output.append(conjunction(facts) + lineBreak());
    output.append(lemmaEnd());
  }

  /**
   * Create a confidentiality lemma saying that if
   * all principals from init are honest
   * principal from query can reach the first state mentioning the variable from query
   * then
   * intruder doesn't know the variable from query.
   */
  private void confidentiality(Confidentiality query) {
    output.append(lemma(Constants.CONFIDENTIALITY + Confidentiality.nextConfidentialityQuery(), false));
    // gather principal IDs
    ArrayList<Variable> principalIDs = new ArrayList<>();
    principalIDs.add(model.runID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }
    Variable principalsTemporal = Variable.nextTemporal();

    // find first block with varible from query
    StBlock originalBlock = null;
    for (StBlock block : query.principal.getBlocks()) {
      if (block.completeState().contains(query.variable)) {
        originalBlock = block;
        break;
      }
    }
    Variable stateTemporal = Variable.nextTemporal();

    ArrayList<Variable> allVariables = new ArrayList<>(principalIDs);
    for (Term term : originalBlock.completeState()) {
      for (Variable variable : term.freeVariables()) {
        if (!allVariables.contains(variable)) {
            allVariables.add(variable);
        }
      }
    }
    allVariables.add(principalsTemporal);
    allVariables.add(stateTemporal);

    output.append(lemmaVariables(allVariables, false));
    output.append(lineBreak());

    ArrayList<String> facts = new ArrayList<>();
    facts.add(lemmaFact(Constants.FACT_PRINCIPALS, principalIDs, principalsTemporal));
    for (Principal principal : model.getPrincipals()) {
      facts.add(honest(principal));
    }
    facts.add(lemmaResultStateFact(originalBlock, stateTemporal));
    
    output.append(implication(conjunction(facts), intruderDoesntKnow(query.variable)));
    output.append(lineBreak());
    output.append(lemmaEnd());
  }

  private String intruderDoesntKnow(Variable variable) {
    Variable temporal = Variable.nextTemporal();
    String knows = lemmaFact(Constants.INTRUDER_KNOWS_LEMMA, variable, temporal);
    knows = lemmaVariables(Arrays.asList(temporal), true) + knows;
    return negation(knows);
  }

  private String honest(Principal principal) {
    Variable temporal = Variable.nextTemporal();
    String dishonest = lemmaFact(Constants.FACT_DISHONEST, principal.principalID, temporal);
    dishonest = lemmaVariables(Arrays.asList(temporal), true) + dishonest;
    return negation(dishonest);
  }
}
