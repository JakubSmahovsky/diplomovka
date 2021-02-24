package simple_tamarin.dataStructures.query;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.term.Variable;

public class ForwardSecrecy {
  private static int queries = 0;
  private final int queryID;
  public final Principal principal;
  public final Variable variable;

  public ForwardSecrecy(Principal principal, Variable variable) {
    this.queryID = nextConfidentialityQuery();
    this.principal = principal;
    this.variable = variable;
  }

  private static int nextConfidentialityQuery() {
    return queries++;
  }

  public String renderName() {
    return Constants.FORWARD_SECRECY + queryID;
  }
}
