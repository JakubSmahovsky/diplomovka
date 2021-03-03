package simple_tamarin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple_tamarin.dataStructures.outputTerm.OutputTerm;

public class OutputFormatting {
  public static String term(String symbol, List<? extends OutputTerm> subterms) {
    ArrayList<String> renders = new ArrayList<>();
    for (OutputTerm term : subterms) {
      renders.add(term.render());
    }
    return symbol + "(" + String.join(", ", renders) + ")";
  }

  public static String term(String symbol, OutputTerm term) {
    return term(symbol, Arrays.asList(term));
  }
}
