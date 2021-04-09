package simpleT.resultCompiler.ResultClause;

import simpleT.dataStructures.document.Document;

public class ResultContradictionClause extends ResultClause {
  @Override
  public Document render() {
    return new Document("contradiction");
  }
}
