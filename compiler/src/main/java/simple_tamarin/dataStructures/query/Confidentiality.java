package simple_tamarin.dataStructures.query;

import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.term.Variable;

public class Confidentiality {
  private static int queries = 0;
  public Principal principal;
  public Variable variable;

  public Confidentiality(Principal principal, Variable variable) {
    this.principal = principal;
    this.variable = variable;
  }

  public static int nextConfidentialityQuery() {
    int result = queries;
    queries++;
    return result;
  }
}
