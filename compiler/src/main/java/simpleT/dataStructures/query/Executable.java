package simpleT.dataStructures.query;

import java.util.ArrayList;

import simpleT.Constants;
import simpleT.dataStructures.Principal;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.term.Term;
import simpleT.dataStructures.term.Variable;

/**
 * A query verifying that all principal, while staying honest,
 * can reach the end of the protocol.
 */
public class Executable extends Query {
  public Executable(STModel model) {
    super(model);
  }

  @Override
  public String renderLabel() {
    return Constants.EXECUTABLE + Constants.NAME_SEPARATOR + queryID;
  }

  @Override
  public Document render() {
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

    ArrayList<String> facts = new ArrayList<>();
    for (Principal principal : model.getPrincipals()) {
      Variable stateTemporal = Variable.nextTemporal();
      allVariables.add(stateTemporal);
      facts.add(lemmaBlockStateFact(principal.getLastBlock(), stateTemporal));
      facts.add(negation(dishonest(principal, Variable.nextTemporal())));
    }
    
    return conjunction(facts)
      .indent()
      .prepend(lemmaQuatification(allVariables, true))
      .indent()
      .prepend(lemma(renderLabel(), true))
      .append(Constants.LEMMA_CLOSE).endl();
  }
}
