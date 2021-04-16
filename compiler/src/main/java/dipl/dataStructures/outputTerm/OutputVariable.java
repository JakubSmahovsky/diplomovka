package dipl.dataStructures.outputTerm;

import dipl.Constants;
import dipl.dataStructures.Model;
import dipl.dataStructures.term.Variable;

public class OutputVariable extends OutputTerm {
  private final String name;
  private final String number;
  private final Variable original;
  
  public OutputVariable(Model model, String name, String number) {
    this.name = name;
    this.number = number;

    if (name.matches(Constants.PREFIX_VARIABLEID + "[0-9]+")) {
      int id = Integer.parseInt(name.substring(Constants.PREFIX_VARIABLEID.length()));
      original = model.getVariable(id);
    } else {
      original = null;
    }
  }

  @Override
  public boolean isVariable() {
    return true;
  }

  @Override
  public boolean match(OutputTerm term) {
    return true;
  }

  @Override
  public String render() {
    return (original == null ? name : original.renderOutput()) + number;
  }
}
