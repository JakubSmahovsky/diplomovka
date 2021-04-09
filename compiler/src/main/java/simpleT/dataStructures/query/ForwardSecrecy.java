package simpleT.dataStructures.query;

import java.util.ArrayList;

import simpleT.Constants;
import simpleT.dataStructures.Principal;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.term.Term;
import simpleT.dataStructures.term.Variable;

/**
 * Query saying that if
 *   principal identities are these ...,
 *   secret variable exists (pricipal reached the first state that contains it),
 *   adversary knows the variable
 * then
 *   at least one of the principals was dishones BEFORE the variable existed
 */
public class ForwardSecrecy extends Query {
  public final Principal principal;
  public final Variable variable;

  public ForwardSecrecy(Principal principal, Variable variable, STModel model) {
    super(model);
    this.principal = principal;
    this.variable = variable;
  }

  @Override
  public String renderLabel() {
    return Constants.FORWARD_SECRECY + Constants.NAME_SEPARATOR + queryID;
  }

  @Override
  public Document render() {
    ArrayList<Variable> allVariables = new ArrayList<>();
    ArrayList<String> presumptionClauses = new ArrayList<>();
    // principals fact
    ArrayList<Variable> principalsFactVariables = new ArrayList<>();
    principalsFactVariables.add(model.instanceID);
    for (Principal principal : model.getPrincipals()) {
      principalsFactVariables.add(principal.principalID);
    }
    Variable principalsTemporal = Variable.nextTemporal();
    presumptionClauses.add(lemmaFact(Constants.FACT_PRINCIPALS, principalsFactVariables, principalsTemporal));
    allVariables.addAll(principalsFactVariables);
    allVariables.add(principalsTemporal);
    // state
    Variable stateTemporal = Variable.nextTemporal();
    for (STBlock block : principal.getBlocks()) {
      if (block.completeState().contains(variable)) {
        for (Term term : block.completeState()) {
          for (Variable variable : term.freeVariables()) {
            if (!Term.containsByObjectEquality(allVariables, variable)) {
              allVariables.add(variable);
            }
          }
        }
        presumptionClauses.add(lemmaBlockStateFact(block, stateTemporal));
        allVariables.add(stateTemporal);
        break;
      }
    }
    // adversary
    Variable adversaryTemporal = Variable.nextTemporal();
    presumptionClauses.add(lemmaFact(Constants.INTRUDER_KNOWS_LEMMA, variable, adversaryTemporal));
    allVariables.add(adversaryTemporal);
    // dishonest
    ArrayList<String> dishonestClauses = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      Variable dishonestTemporal = Variable.nextTemporal();
      String dishonest = dishonest(principal, dishonestTemporal);
      String before = beforeAfter(dishonestTemporal, stateTemporal);
      dishonestClauses.add(bracket(dishonest + Constants.LEMMA_CONJUNCTION + before));
    }
    // construct
    return conjunction(presumptionClauses).indent()
      .append(Constants.LEMMA_IMPLICATION)
      .append(disjunction(dishonestClauses).indent())
      .indent()
      .prepend(lemmaQuatification(allVariables, false))
      .indent()
      .prepend(lemma(renderLabel(), false))
      .append(Constants.LEMMA_CLOSE).endl();
  }
}
