package dipl;

import java.util.ArrayList;
import java.util.HashSet;

import dipl.dataStructures.*;
import dipl.dataStructures.command.*;
import dipl.dataStructures.query.Query;
import dipl.dataStructures.term.*;

/**
 * Class used for construction of Tamarin code from STModel. It consists of 2 files,
 * this one and BuilderFormatting. This class works  with logical parts 
 * of Tamarin code whereas BuilderFormatting handles all the speciffic
 * formatting of those parts.
 * BuilderFormatting is extended, not imported, to avoid extra classname before methods.
 */
public class Builder extends BuilderFormatting{
  public StringBuilder output;
  public Model model;

  public Builder(Model model){
    this.output = new StringBuilder();
    this.model = model;
    
    initProtocol();
    initRules();
    blocks();
    model.sessionID.removeFresh();
    
    reveals();
    restrictions();
    queries();
    output.append(Constants.THEORY_CLOSE);
  }

  public String output(){
    return output.toString();
  }

  /**
   * Render the protocol header and declarations.
   */
  private void initProtocol() {
    output.append(Constants.THEORY_OPEN);

    ArrayList<String> builtins = new ArrayList<>();
    if (model.builtins.symmetric_encryption) {
      builtins.add(Constants.BUILTIN_SYMMETRIC_ENCRYPTION);
    }
    if (model.builtins.asymmetric_cryptography) {
      builtins.add(Constants.BUILTIN_SIGNING);
      builtins.add(Constants.BUILTIN_ASYMMETRIC_ENCRYPTION);
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
   * Create rules for instance and session initialization
   */
  private void initRules() {
    // gather fresh variables
    HashSet<Variable> toGenerate = new HashSet<>();
    for (Principal principal : model.getPrincipals()) {
      for (Variable variable : principal.getLongTermPrivate()) {
        variable.addFresh();
        toGenerate.add(variable);
      }
    }
    model.instanceID.addFresh();
    toGenerate.add(model.instanceID);

    // gather variables that need to be constructed (public variables tagged as constructed)
    HashSet<Variable> toConstruct = new HashSet<>();
    for (Principal principal : model.getPrincipals()) {
      for (Variable variable : principal.getKnownPublic()) {
        if (variable.isConstructed()) { 
          toConstruct.add(variable);
        }
      }
    }

    // truely long-term public variables are "created/declared" in init too
    for (Principal principal : model.getPrincipals()) {
      for (Variable variable : principal.getKnownPublic()) {
        if (!variable.isConstructed()) { 
          variable.addPublic();
        }
      }
    }
    
    // gather principal IDs (to bind principals with instanceID)
    ArrayList<Variable> principalIDs = new ArrayList<>();
    principalIDs.add(model.instanceID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }

    // render aliases
    ArrayList<String> facts = new ArrayList<>();
    for (Variable variable : toConstruct) {
      facts.add(variable.renderAlias(null));
    }
    output.append(ruleAliases(null, facts, Constants.RULE_INSTANCE));

    // render fresh facts
    facts = new ArrayList<>();
    for (Variable variable : toGenerate) {
      facts.add(fact(Constants.COMMAND_FRESH, variable, null));
    }
    output.append(rulePremise(facts));

    // render action facts
    facts = new ArrayList<>();
    facts.add(fact(Constants.FACT_PRINCIPALS, principalIDs, null));
    output.append(ruleAction(facts));

    // render instance states of principals
    // render facts listing all long-term private variables of each pricipal (for use in reveals)
    facts = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      facts.add(instanceStateFact(principal));
      facts.add(LongTermPrivateListFact(principal));
    }
    // render output of distributed variables
    for (Variable variable : toConstruct) {
      facts.add((new CommandOut(variable, null)).render());
    }
    output.append(ruleConclusions(facts));

    // remove fresh and public sort from variables
    for (Variable variable : toGenerate) {
      variable.removeFresh();
    }
    model.instanceID.removeFresh();
    for (Principal principal : model.getPrincipals()) {
      for (Variable variable : principal.getKnownPublic()) {
        if (!variable.isConstructed()) {
          variable.removePublic();
        }
      }
    }

    // render session init rule
    model.sessionID.addFresh();
    facts = new ArrayList<>();
    output.append(ruleAliases(null, facts, Constants.RULE_SESSION));
    facts.add(fact(Constants.COMMAND_FRESH, model.sessionID, null));
    for (Principal principal : model.getPrincipals()) {
      facts.add(instanceStateFact(principal));
    }
    output.append(rulePremise(facts));
    facts = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      facts.add(sessionStateFact(principal));
    }
    output.append(ruleAction(facts));
    output.append(ruleConclusions(facts));
  }

  /**
   * Create all the regular principal blocks.
   */
  private void blocks() {
    for (Principal principal : model.getPrincipals()) {
      Block previousBlock = null;
      for (Block curBlock : principal.getBlocks()) {
        block(previousBlock, curBlock);
        previousBlock = curBlock;
      }
    }
  }

  private void block(Block previousBlock, Block block){
    // add sort tags to base variables the principal declared himself
    block.principal.addSorts();

    // premises
    ArrayList<String> premises = new ArrayList<>();
    if (previousBlock == null) {
      premises.add(sessionStateFact(block.principal));
    } else {
      premises.add(blockStateFact(previousBlock, block));
    }
    for (CommandIn in : block.inputs) {
      premises.add(in.render());
    }
    for (CommandFr fr : block.fresh) {
      premises.add(fr.render());
    }

    // actions
    ArrayList<String> actions = new ArrayList<>();
    String blockStateFact = blockStateFact(block, block);
    actions.add(blockStateFact);
    for (Fact fact : block.actions) {
      actions.add(fact.render(block));
    }

    // conclusions
    ArrayList<String> conclusions = new ArrayList<>();
    for (CommandOut out : block.outputs) {
      conclusions.add(out.render());
    }
    conclusions.add(blockStateFact);

    // aliases; we render them last, because they are affected by fresh rules
    ArrayList<String> aliases = new ArrayList<>();
    for (Variable alias : block.aliases) {
      aliases.add(alias.renderAlias(block));
    }

    output.append(ruleAliases(block, aliases, null));
    output.append(rulePremise(premises));
    output.append(ruleAction(actions));
    output.append(ruleConclusions(conclusions));
    
    block.principal.removeSorts();
  }

  private void reveals() {
    for (Principal principal : model.getPrincipals()) {
      output.append(revealRule(principal));
    }
  }

  private void restrictions() {
    if (model.builtins.restriction_eq) {
      output.append(Constants.RESTRICTION_EQUALITY);
    }
  }

  private void queries() {
    for (Query query : model.queries) {
      output.append(query.render().endl());
    }
  }
}
