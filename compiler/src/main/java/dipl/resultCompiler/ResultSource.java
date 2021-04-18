package dipl.resultCompiler;

import java.util.ArrayList;

import dipl.Constants;
import dipl.dataStructures.document.Document;
import dipl.sourcesCompiler.Source;

public class ResultSource {
  public final ArrayList<Source> applicable;
  public final int caseIndex;

  public ResultSource(ArrayList<Source> applicable, int caseIndex) {
    this.applicable = applicable;
    this.caseIndex = caseIndex;
  }

  public Document render() {
    ArrayList<String> numbers = new ArrayList<>();
    for (Source source : applicable) {
      numbers.add(String.valueOf(source.number));
    }
    return new Document("applicable: " + String.join(Constants.COMMA_SEPARATOR, numbers));
  }
}
