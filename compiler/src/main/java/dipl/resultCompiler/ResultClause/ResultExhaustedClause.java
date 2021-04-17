package dipl.resultCompiler.ResultClause;

import dipl.dataStructures.document.Document;

public class ResultExhaustedClause extends ResultClause {
  @Override
  public Document render() {
    return new Document("exhausted options");
  }
}
