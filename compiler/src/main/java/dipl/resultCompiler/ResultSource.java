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
    int likely = 0; // 0 is invalid
    ArrayList<String> other = new ArrayList<>();
    for (int i = 0; i < applicable.size(); i++) {
      if (i == caseIndex) {
        likely = applicable.get(i).number;
      } else {
        other.add(String.valueOf(applicable.get(i).number));
      }
    }
    String possibly = ", possibly " + String.join(Constants.COMMA_SEPARATOR, other);
    return new Document("likely " + likely + (other.isEmpty() ? "" : possibly));
  }
}
