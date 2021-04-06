package simpleT.sourcesCompiler.graph.node;

import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

public class PrincipalRuleNode extends Node {
  public final STBlock block;
  private final String printLabel;

  public PrincipalRuleNode(String id, String label, STModel model) {
    super(id, label);
    int blockID = Integer.parseInt(label.split(Constants.NAME_SEPARATOR)[1]);
    this.block = model.blocks.get(blockID);
    this.printLabel = block.principal.renderOutput() + "'s block nr. " + block.rangeEnd;
  }

  @Override
  public Description renderDescription() {
    String docLine = "which he receives after " + printLabel + ". He has to send messages (possibly none):";
    return new Description(new Document(docLine), this, printLabel);
  }

  @Override
  public String render(){
    return "Block(" + this.label + ")";
  }
}
