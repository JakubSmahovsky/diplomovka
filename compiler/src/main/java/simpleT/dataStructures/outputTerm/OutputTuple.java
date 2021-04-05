package simpleT.dataStructures.outputTerm;

import java.util.ArrayList;

import simpleT.Constants;

public class OutputTuple extends OutputTerm{
  private final ArrayList<OutputTerm> subterms;

  public OutputTuple(ArrayList<OutputTerm> subterms) {
    this.subterms = subterms;
  }

  public OutputTuple(OutputTerm fst, OutputTerm snd) {
    this.subterms = new ArrayList<>();
    if (fst instanceof OutputTuple) {
      subterms.addAll(((OutputTuple)fst).subterms);
    } else {
      subterms.add(fst);
    }
    if (snd instanceof OutputTuple) {
      subterms.addAll(((OutputTuple)snd).subterms);
    } else {
      subterms.add(snd);
    }
  }

  @Override
  public boolean match(OutputTerm term) {
    if (!(term instanceof OutputTuple)) {
      return false;
    }
    OutputTuple oTuple = (OutputTuple)term;
    if (subterms.size() != oTuple.subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).match(oTuple.subterms.get(i))) {
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
    return Constants.ST_TUPLE_OPEN + String.join(Constants.COMMA_SEPARATOR, renders) + Constants.ST_TUPLE_CLOSE;
  }
}
