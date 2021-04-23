package dipl.dataStructures.query;

import java.util.ArrayList;

import dipl.Constants;
import dipl.dataStructures.Fact;
import dipl.dataStructures.Principal;
import dipl.dataStructures.Block;
import dipl.dataStructures.Model;
import dipl.dataStructures.command.CommandIn;
import dipl.dataStructures.document.Document;
import dipl.dataStructures.term.Term;
import dipl.dataStructures.term.Variable;

/** An authentication query saying that if
 *    principal identities are these ...,
 *    recipient received the variable (got to state)
 *  then
 *    sender sent the variable (there is a special fact Sent(principal, variable)) or
 *    one of the principals is dishonest
 */
public class Authentication extends Query{
  public final Principal sender;
  public final Principal recipient;
  public final Variable sent;
  public final Variable received;
  public final Fact fact;

  public Authentication(Principal sender, Principal recipient, Variable sent, Variable received, Fact fact, Model model) {
    super(model);
    this.sender = sender;
    this.recipient = recipient;
    this.sent = sent;
    this.received = received;
    this.fact = fact;
  }

  @Override
  public String renderLabel() {
    return Constants.AUTHENTICATION + Constants.NAME_SEPARATOR + queryID;
  }

  @Override
  public Document render() {
    ArrayList<Variable> allVariables = new ArrayList<>();
    // principals fact
    ArrayList<Variable> principalIDs = new ArrayList<>();
    principalIDs.add(model.instanceID);
    for (Principal principal : model.getPrincipals()) {
      principalIDs.add(principal.principalID);
    }
    Variable principalsTemporal = Variable.nextTemporal();
    String principals = lemmaFact(Constants.FACT_PRINCIPALS, principalIDs, principalsTemporal);
    allVariables.addAll(principalIDs);
    allVariables.add(principalsTemporal);
    // recipient state
    Variable recipientTemporal = Variable.nextTemporal();
    Block recipientBlock = null;
    for (Block block : recipient.getBlocks()) {
			for (CommandIn in : block.inputs) {
				if (in.receivedVariable(received)) {
					recipientBlock = block;
				}
			}
      if (recipientBlock != null) {
        break;
      }
		}
    for (Term term : recipientBlock.completeState()) {
      for (Variable variable : term.freeVariables()) {
        if (!Term.containsByObjectEquality(allVariables, variable)) {
          allVariables.add(variable);
        }
      }
    }
    String recipientState = lemmaBlockStateFact(recipientBlock, recipientTemporal);
    allVariables.add(recipientTemporal);
    // sender clause
    ArrayList<Variable> senderVariables = new ArrayList<>();
    if (sent != received) {
      senderVariables.add(sent);
    }
    Variable senderTemporal = Variable.nextTemporal();
    senderVariables.add(senderTemporal);
    Document senderClause = 
      new Document(fact.render() + atTemporal(senderTemporal) + Constants.LEMMA_CONJUNCTION)
      .append(lemmaEquals(sent, received))
      .indent()
      .prepend(lemmaQuatification(senderVariables, true))
      .bracket();
    // build the lemma
    Document presumption = 
      new Document(principals + Constants.LEMMA_CONJUNCTION)
      .append(recipientState);
    Document conclusion = new Document();
    for (Principal principal : model.getPrincipals()) {
      conclusion.append(bracket(dishonest(principal, Variable.nextTemporal())) + Constants.LEMMA_DISJUNCTION);
    }
    conclusion.append(senderClause);
    return presumption.indent()
      .append(Constants.LEMMA_IMPLICATION)
      .append(conclusion.indent())
      .indent()
      .prepend(lemmaQuatification(allVariables, false))
      .indent()
      .prepend(lemma(renderLabel(), false))
      .append(Constants.LEMMA_CLOSE).endl();
  }

  @Override
  public Document renderOutput() {
    return new Document("property: authentication? " + sender.renderOutput() + " -> " + recipient.renderOutput() + ": " + sent.renderOutput())
      .append(success ? "DISPROVED" : "PROVED")
      .append(trace.render())
      .endl();
  }
}
