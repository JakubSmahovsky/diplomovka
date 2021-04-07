package simpleT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import simpleT.dataStructures.*;
import simpleT.dataStructures.command.*;
import simpleT.dataStructures.query.Authentication;
import simpleT.dataStructures.query.Confidentiality;
import simpleT.dataStructures.query.ForwardSecrecy;
import simpleT.dataStructures.query.InjAuthentication;
import simpleT.dataStructures.term.*;

/**
 * Class used for construction of Tamarin code from STModel. It consists of 2 files,
 * this one and BuilderFormatting. This class works  with logical parts 
 * of Tamarin code whereas BuilderFormatting handles all the speciffic
 * formatting of those parts.
 * BuilderFormatting is extended, not imported, to avoid extra classname before methods.
 */
public class Builder extends BuilderFormatting{
  public StringBuilder output;
  public STModel model;

  public Builder(STModel model){
    this.output = new StringBuilder();
    this.model = model;
    
    initProtocol();
    initRules();
    blocks();
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
      STBlock previousBlock = null;
      for (STBlock curBlock : principal.getBlocks()) {
        block(previousBlock, curBlock);
        previousBlock = curBlock;
      }
    }
  }

  private void block(STBlock previousBlock, STBlock block){
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
    if (model.queries.executable) {
      executable();
      output.append(Constants.LINE_BREAK);
    }
    for (Confidentiality query : model.queries.confidentiality) {
      confidentiality(query);
      output.append(Constants.LINE_BREAK);
    }

    for (ForwardSecrecy query : model.queries.forwardSecrecy) {
      forwardSecrecy(query);
      output.append(Constants.LINE_BREAK);
    }

    for (Authentication query : model.queries.authentication) {
      authentication(query);
      output.append(Constants.LINE_BREAK);
    }

    for (InjAuthentication query : model.queries.injAuthentication) {
      injAuthentication(query);
      output.append(Constants.LINE_BREAK);
    }
  }

  /**
   * Create lemma saying that all principals are able to reach
   * their final state. 
   */
  private void executable() {
    output.append(lemma(Constants.EXECUTABLE, true));
    ArrayList<Variable> allVariables = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      ArrayList<Term> finalState = principal.getLastBlock().completeState();
      for (Term term : finalState) {
        for (Variable variable : term.freeVariables()) {
          if (!Term.containsByObjectEquality(allVariables, variable)) {
              allVariables.add(variable);
          }
        }
      }
    }
    ArrayList<Variable> temporals = new ArrayList<>();
    for (int principalNo = 0; principalNo < model.getPrincipals().size(); principalNo ++) {
      Variable t = Variable.nextTemporal();
      allVariables.add(t);
      temporals.add(t);
    }
    output.append(lemmaQuatification(allVariables, true));
    output.append(Constants.LINE_BREAK);

    ArrayList<String> facts = new ArrayList<>();
    Iterator<Variable> tempIt = temporals.iterator();
    for (Principal principal : model.getPrincipals()) {
      facts.add(lemmaBlockStateFact(principal.getLastBlock(), tempIt.next()));
      Variable temporal = Variable.nextTemporal();
      facts.add(negation(dishonest(principal, temporal)));
    }

    output.append(conjunction(facts) + Constants.LINE_BREAK);
    output.append(Constants.LEMMA_CLOSE);
  }

  /**
   * Create a confidentiality lemma saying that if
   *   all principal are such and such,
   *   variable from query exists -> pricipal reached the first state that contains it,
   *   attacker knows the variable from query
   * then
   *   at least one of the principals was dishones
   */
  private void confidentiality(Confidentiality query) {
    // gather principal IDs
    ArrayList<Variable> principalIDs = new ArrayList<>();
    principalIDs.add(model.instanceID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }
    Variable principalsTemporal = Variable.nextTemporal();

    // find first block with varible from query
    STBlock originalBlock = null;
    for (STBlock block : query.principal.getBlocks()) {
      if (block.completeState().contains(query.variable)) {
        originalBlock = block;
        break;
      }
    }
    Variable stateTemporal = Variable.nextTemporal();

    Variable intruderTemporal = Variable.nextTemporal();

    ArrayList<Variable> allVariables = new ArrayList<>(principalIDs);
    for (Term term : originalBlock.completeState()) {
      for (Variable variable : term.freeVariables()) {
        if (!Term.containsByObjectEquality(allVariables, variable)) {
          allVariables.add(variable);
        }
      }
    }
    allVariables.add(principalsTemporal);
    allVariables.add(stateTemporal);
    allVariables.add(intruderTemporal);

    output.append(lemma(query.renderName(), false));
    output.append(lemmaQuatification(allVariables, false));
    output.append(Constants.LINE_BREAK);

    ArrayList<String> presumptionClauses = new ArrayList<>();
    presumptionClauses.add(lemmaFact(Constants.FACT_PRINCIPALS, principalIDs, principalsTemporal));
    presumptionClauses.add(lemmaBlockStateFact(originalBlock, stateTemporal));
    presumptionClauses.add(lemmaFact(Constants.INTRUDER_KNOWS_LEMMA, query.variable, intruderTemporal));
    ArrayList<String> dishonestClauses = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      Variable temporal = Variable.nextTemporal();
      dishonestClauses.add(bracket(dishonest(principal, temporal)));
    }
    
    output.append(implication(conjunction(presumptionClauses), disjunction(dishonestClauses)));
    output.append(Constants.LINE_BREAK);
    output.append(Constants.LEMMA_CLOSE);
  }


  /**
   * Create a forward secrecy lemma saying that if
   *   all principal are such and such,
   *   variable from query exists -> pricipal reached the first state that contains it,
   *   attacker knows the variable from query
   * then
   *   at least one of the principals was dishones BEFORE the variable existed
   */
  private void forwardSecrecy(ForwardSecrecy query) {
    // gather principal IDs
    ArrayList<Variable> principalIDs = new ArrayList<>();
    principalIDs.add(model.instanceID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }
    Variable principalsTemporal = Variable.nextTemporal();

    // find first block with varible from query
    STBlock finalBlock = query.principal.getLastBlock();
    Variable stateTemporal = Variable.nextTemporal();

    Variable intruderTemporal = Variable.nextTemporal();

    ArrayList<Variable> allVariables = new ArrayList<>(principalIDs);
    for (Term term : finalBlock.completeState()) {
      for (Variable variable : term.freeVariables()) {
        if (!Term.containsByObjectEquality(allVariables, variable)) {
          allVariables.add(variable);
        }
      }
    }
    allVariables.add(principalsTemporal);
    allVariables.add(stateTemporal);
    allVariables.add(intruderTemporal);

    output.append(lemma(query.renderName(), false));
    output.append(lemmaQuatification(allVariables, false));
    output.append(Constants.LINE_BREAK);

    ArrayList<String> presumptionClauses = new ArrayList<>();
    presumptionClauses.add(lemmaFact(Constants.FACT_PRINCIPALS, principalIDs, principalsTemporal));
    presumptionClauses.add(lemmaBlockStateFact(finalBlock, stateTemporal));
    presumptionClauses.add(lemmaFact(Constants.INTRUDER_KNOWS_LEMMA, query.variable, intruderTemporal));
    ArrayList<String> dishonestClauses = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      Variable dishonestTemporal = Variable.nextTemporal();
      String dishonest = dishonest(principal, dishonestTemporal);
      String before = beforeAfter(dishonestTemporal, stateTemporal);
      dishonestClauses.add(bracket(conjunction(Arrays.asList(dishonest, before))));
    }
    
    output.append(implication(conjunction(presumptionClauses), disjunction(dishonestClauses)));
    output.append(Constants.LINE_BREAK);
    output.append(Constants.LEMMA_CLOSE);
  }

  /** Create an authentication query saying that if
   *    all principals are such and such
   *    recipient received the variable (got to state)
   *  then
   *    sender sent the variable or (there is a special fact Sent(principal, variable))
   *    the sender is dishonest or 
   *    the recipient is dishonest
   */
  private void authentication(Authentication query) {
    // find the recipient block
		STBlock recipientBlock = null;
		for (STBlock block : query.recipient.getBlocks()) {
			for (CommandIn in : block.inputs) {
				if (in.receivedVariable(query.received)) {
					recipientBlock = block;
					break;
				}
			}
			if (recipientBlock != null) {
				break;
			}
		}

    // gather all Variables before implication
    ArrayList<Variable> principalIDs = new ArrayList<>();
    
    principalIDs.add(model.instanceID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }
    
    ArrayList<Variable> presumptionVariables = new ArrayList<>(principalIDs);
    for (Term term : recipientBlock.completeState()) {
      for (Variable variable : term.freeVariables()) {
        if (!Term.containsByObjectEquality(presumptionVariables, variable)) {
          presumptionVariables.add(variable);
        }
      }
    }
    
    Variable principalsTemporal = Variable.nextTemporal();
    presumptionVariables.add(principalsTemporal);
    Variable recipientTemporal = Variable.nextTemporal();
    presumptionVariables.add(recipientTemporal);

    // gather sender variables
    ArrayList<Variable> senderVariables = new ArrayList<>();
    senderVariables.add(query.sender.principalID);
    if (query.sent != query.received) {
      senderVariables.add(query.sent);
    }
    Variable senderTemporal = Variable.nextTemporal();
    senderVariables.add(senderTemporal);    

    // build the lemma
    output.append(lemma(query.renderName(), false));
    output.append(lemmaQuatification(presumptionVariables, false));
    output.append(Constants.LINE_BREAK);

    String principalsFact = lemmaFact(Constants.FACT_PRINCIPALS, principalIDs, principalsTemporal);
    String recipientFact = lemmaBlockStateFact(recipientBlock, recipientTemporal);
    String presumption = conjunction(Arrays.asList(principalsFact, recipientFact));
    
    String senderFact = lemmaFact(Constants.FACT_AUTHENTICATION_SENT, Arrays.asList(query.sender.principalID, query.sent), senderTemporal);
    String equalsClause = lemmaEquals(query.sent, query.received);
    String senderClause = lemmaQuatification(senderVariables, true) + Constants.LINE_BREAK + senderFact;
    senderClause = bracket(conjunction(Arrays.asList(senderClause, equalsClause)));

    String dishonestSender = bracket(dishonest(query.sender, Variable.nextTemporal()));
    String dishonestRecipient = bracket(dishonest(query.recipient, Variable.nextTemporal()));
    String result = disjunction(Arrays.asList(senderClause, dishonestSender, dishonestRecipient));
    output.append(implication(presumption, result));
    output.append(Constants.LINE_BREAK);
    output.append(Constants.LEMMA_CLOSE);
  }

  /** Create an injective authentication query saying that if
   *    all principals are such and such
   *    recipient received the variable (got to state after)
   *  then
   *    sender sent the variable or (got to state before)
   *    the sender is dishonest or
   *    the recipient is dishonest
   */
  private void injAuthentication(InjAuthentication query) {
    // find the sender and recipient blocks
    STBlock senderBlock = null;
		for (STBlock block : query.sender.getBlocks()) {
			for (CommandOut out : block.outputs) {
				if (out.sentVariable(query.sent)) {
					senderBlock = block;
					break;
				}
			}
			if (senderBlock != null) {
				break;
			}
		}
		STBlock recipientBlock = null;
		for (STBlock block : query.recipient.getBlocks()) {
			for (CommandIn in : block.inputs) {
				if (in.receivedVariable(query.received)) {
					recipientBlock = block;
					break;
				}
			}
			if (recipientBlock != null) {
				break;
			}
		}

    // gather all Variables before implication
    ArrayList<Variable> principalIDs = new ArrayList<>();
    
    principalIDs.add(model.instanceID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }
    
    ArrayList<Variable> presumptionVariables = new ArrayList<>(principalIDs);
    for (Term term : recipientBlock.completeState()) {
      for (Variable variable : term.freeVariables()) {
        if (!Term.containsByObjectEquality(presumptionVariables, variable)) {
          presumptionVariables.add(variable);
        }
      }
    }
    
    Variable principalsTemporal = Variable.nextTemporal();
    presumptionVariables.add(principalsTemporal);
    Variable recipientTemporal = Variable.nextTemporal();
    presumptionVariables.add(recipientTemporal);

    // garther all Variables variables in the sender clause (that do not yet occur in the presumption)
    ArrayList<Variable> senderVariables = new ArrayList<>();
    
    for (Term term : senderBlock.completeState()) {
      for (Variable variable : term.freeVariables()) {
        if (!Term.containsByObjectEquality(senderVariables, variable) && 
            !Term.containsByObjectEquality(presumptionVariables, variable)) {
          senderVariables.add(variable);
        }
      }
    }

    Variable senderTemporal = Variable.nextTemporal();
    senderVariables.add(senderTemporal);

    // build the lemma
    output.append(lemma(query.renderName(), false));
    output.append(lemmaQuatification(presumptionVariables, false));
    output.append(Constants.LINE_BREAK);

    String principalsFact = lemmaFact(Constants.FACT_PRINCIPALS, principalIDs, principalsTemporal);
    String recipientFact = lemmaBlockStateFact(recipientBlock, recipientTemporal);
    String presumption = conjunction(Arrays.asList(principalsFact, recipientFact));
    
    String senderFact = lemmaBlockStateFact(senderBlock, senderTemporal);
    String equalsClause = lemmaEquals(query.sent, query.received);
    String senderClause = lemmaQuatification(senderVariables, true) + Constants.LINE_BREAK + senderFact;
    senderClause = bracket(conjunction(Arrays.asList(senderClause, equalsClause)));

    String dishonestSender = bracket(dishonest(query.sender, Variable.nextTemporal()));
    String dishonestRecipient = bracket(dishonest(query.recipient, Variable.nextTemporal()));
    String result = disjunction(Arrays.asList(senderClause, dishonestSender, dishonestRecipient));
    output.append(implication(presumption, result));
    output.append(Constants.LINE_BREAK);
    output.append(Constants.LEMMA_CLOSE);
  }
}
