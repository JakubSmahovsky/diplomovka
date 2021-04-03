package simpleT.dataStructures.query;

import simpleT.Constants;
import simpleT.dataStructures.Principal;
import simpleT.dataStructures.term.Variable;

public class InjAuthentication {
  private static int queries = 0;
  private final int queryID;
  public final Principal sender;
  public final Principal recipient;
  public final Variable sent;
  public final Variable received;

  public InjAuthentication(Principal sender, Principal recipient, Variable sent, Variable received) {
    this.queryID = nextInjAuthenticationQuery();
    this.sender = sender;
    this.recipient = recipient;
    this.sent = sent;
    this.received = received;
  }

  private static int nextInjAuthenticationQuery() {
    return queries++;
  }

  public String renderName() {
    return Constants.INJ_AUTHENTICATION + queryID;
  }
}
