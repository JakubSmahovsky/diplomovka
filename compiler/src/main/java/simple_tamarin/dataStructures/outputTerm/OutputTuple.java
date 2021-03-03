package simple_tamarin.dataStructures.outputTerm;

import java.util.ArrayList;

public class OutputTuple extends OutputTerm{
  private final ArrayList<OutputTerm> subterms;

  public OutputTuple(ArrayList<OutputTerm> subterms) {
    this.subterms = subterms;
  }

  @Override
  public boolean unify(OutputTerm term) {
    if (!(term instanceof OutputTuple)) {
      return false;
    }
    OutputTuple oTuple = (OutputTuple)term;
    if (subterms.size() != oTuple.subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).unify(oTuple.subterms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String render() {
    ArrayList<String> renders = new ArrayList<>();
    for (OutputTerm subterm : subterms) {
      renders.add(subterm.render());
    }
    return "{" + String.join(", ", renders) + "}";
  }

   
}
