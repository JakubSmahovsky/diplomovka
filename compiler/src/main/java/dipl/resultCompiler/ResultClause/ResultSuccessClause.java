package dipl.resultCompiler.ResultClause;

import dipl.dataStructures.document.Document;

public class ResultSuccessClause extends ResultClause{
  @Override
  public Document render() {
    return new Document("success");
  }
}
