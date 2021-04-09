package simpleT.resultCompiler.ResultClause;

import simpleT.dataStructures.document.Document;

public class ResultSuccessClause extends ResultClause{
  @Override
  public Document render() {
    return new Document("success");
  }
}
