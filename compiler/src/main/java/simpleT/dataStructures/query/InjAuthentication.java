package simpleT.dataStructures.query;

import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.term.Variable;

public class InjAuthentication {
  private static int queries = 0;
  private final int queryID;
  public final STBlock senderBlock;
  public final STBlock receiverBlock;
  public final Variable sent;
  public final Variable received;

  public InjAuthentication(STBlock senderBlock, STBlock receiverBlock, Variable sent, Variable received) {
    this.queryID = nextInjAuthenticationQuery();
    this.senderBlock = senderBlock;
    this.receiverBlock = receiverBlock;
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
