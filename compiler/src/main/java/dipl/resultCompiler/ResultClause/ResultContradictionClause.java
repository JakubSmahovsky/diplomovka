package dipl.resultCompiler.ResultClause;

import dipl.dataStructures.document.Document;

public class ResultContradictionClause extends ResultClause {
  @Override
  public Document render() {
    return new Document("contradiction");
  }
}
