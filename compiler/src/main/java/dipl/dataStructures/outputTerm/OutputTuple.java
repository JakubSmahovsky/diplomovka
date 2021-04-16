package dipl.dataStructures.outputTerm;

import java.util.ArrayList;

import dipl.Constants;

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

  /**
   * Tuple matching is a bit complicated, eg. {x, y} matches {x, y, z}
   * because x matches x and y matches {y, z}.
   * In general this matches the other if there is such a sequence of subtuples
   * of the other that each subterm of this matches a subtuple of the other.
   * In reality it is not as bad, only a variable can match a tuple (or anything else).
   * Matching can be verified by this greedy algorithm:
   *  find the first non-variable in this
   *    passedThis is the number of variables you passed
   *    if you reach the end
   *      if you passed some variables but not more than is left in other then succeed (variables match anything)
   *      if you passed nothing and there is nothing left in othern also succeed (finished)
   *  jump passedThis subterms forward in other
   *  find the first subterm in other that the non-variable matches
   *    if you reached the end then fail (nothing mathes the non-variable)
   *    passedOther is the number of variables you passed
   *    if you pass something and passedThis == 0, then fail (nothing matches passed in other)
   *  if you reached the end of other, then succeed
   *  repeat
   */
  @Override
  public boolean match(OutputTerm term) {
    if (!(term instanceof OutputTuple)) {
      return false;
    }
    OutputTuple other = (OutputTuple)term;
    if (subterms.size() > other.subterms.size()) {
      return false;
    }
    int i = 0;
    int j = 0;
    while (subterms.size()-i <= other.subterms.size()-j) {
      int passedThis = 0;
      OutputTerm nonVariable = null;
      for (; i < subterms.size(); i++) {
        if (subterms.get(i).isVariable()) {
          passedThis++;
        }
        else {
          nonVariable = subterms.get(i);
          break;
        }
      }
      if (nonVariable == null && (
            (passedThis > 0 && passedThis <= (other.subterms.size()-j)) ||
            (passedThis == 0 && (other.subterms.size()-j) == 0)
          )) {
        return true;
      }

      j += passedThis;
      boolean matched = false;
      for (; j < other.subterms.size(); j++) {
        if (nonVariable.match(other.subterms.get(j))) {
          matched = true;
          break;
        } else if (passedThis == 0) {
          return false;
        }
      }
      if (!matched) {
        return false;
      }
    }
    return false;
  }

  @Override
  public String render() {
    ArrayList<String> renders = new ArrayList<>();
    for (OutputTerm subterm : subterms) {
      renders.add(subterm.render());
    }
    return Constants.IN_TUPLE_OPEN + String.join(Constants.COMMA_SEPARATOR, renders) + Constants.IN_TUPLE_CLOSE;
  }
}
