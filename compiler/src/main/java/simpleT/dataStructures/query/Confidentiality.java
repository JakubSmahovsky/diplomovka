package simpleT.dataStructures.query;

import simpleT.Constants;
import simpleT.dataStructures.Principal;
import simpleT.dataStructures.term.Variable;

public class Confidentiality {
  private static int queries = 0;
  private final int queryID;
  public final Principal principal;
  public final Variable variable;

  public Confidentiality(Principal principal, Variable variable) {
    this.queryID = nextConfidentialityQuery();
    this.principal = principal;
    this.variable = variable;
  }

  private static int nextConfidentialityQuery() {
    return queries++;
  }

  public String renderName() {
    return Constants.CONFIDENTIALITY + queryID;
  }
}