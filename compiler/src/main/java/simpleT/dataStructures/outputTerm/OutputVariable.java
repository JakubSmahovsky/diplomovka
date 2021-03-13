package simpleT.dataStructures.outputTerm;

import simpleT.Constants;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.term.Variable;

public class OutputVariable extends OutputTerm {
  private final String name;
  private final String number;
  private final Variable original;
  
  public OutputVariable(STModel model, String name, String number) {
    this.name = name;
    this.number = number;

    if (name.matches(Constants.VARIABLE_NAME + "[0-9]+")) {
      int id = Integer.parseInt(name.substring(Constants.VARIABLE_NAME.length()));
      original = model.getVariable(id);
    } else {
      original = null;
    }
  }

  @Override
  public boolean unify(OutputTerm term) {
    return true;
  }

  @Override
  public String render() {
    return (original == null ? name : original.renderOutput()) + number;
  }
}
