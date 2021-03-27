package simpleT.dataStructures.query;

import simpleT.Constants;
import simpleT.dataStructures.Principal;
import simpleT.dataStructures.term.Variable;

public class Authentication {
  private static int queries = 0;
  private final int queryID;
  public final Principal sender;
  public final Principal receiver;
  public final Variable sent;
  public final Variable received;

  public Authentication(Principal sender, Principal receiver, Variable sent, Variable received) {
    this.queryID = nextAuthenticationQuery();
    this.sender = sender;
    this.receiver = receiver;
    this.sent = sent;
    this.received = received;
  }

  private static int nextAuthenticationQuery() {
    return queries++;
  }

  public String renderName() {
    return Constants.AUTHENTICATION + queryID;
  }
}
