package dipl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dipl.dataStructures.outputTerm.OutputTerm;

public class OutputFormatting {
  public static String term(String symbol, List<? extends OutputTerm> subterms) {
    ArrayList<String> renders = new ArrayList<>();
    for (OutputTerm term : subterms) {
      renders.add(term.render());
    }
    return symbol + Constants.OPEN_BR + String.join(Constants.COMMA_SEPARATOR, renders) + Constants.CLOSE_BR;
  }

  public static String term(String symbol, OutputTerm term) {
    return term(symbol, Arrays.asList(term));
  }

  public static String fact(boolean persistent, String symbol, List<? extends OutputTerm> terms) {
    if (persistent) {
      return Constants.PERSISTENT + term(symbol, terms);
    } else {
      return term(symbol, terms);
    }
  }

  public static String fact(boolean persistent, String symbol, OutputTerm term) {
    if (persistent) {
      return Constants.PERSISTENT + term(symbol, term);
    } else {
      return term(symbol, term);
    }
  }
}
