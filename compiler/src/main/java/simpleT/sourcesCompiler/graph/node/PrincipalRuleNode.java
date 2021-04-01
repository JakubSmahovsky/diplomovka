package simpleT.sourcesCompiler.graph.node;

import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

public class PrincipalRuleNode extends Node {
  public final STBlock block;

  public PrincipalRuleNode(String id, String label, STModel model) {
    super(id, label);
    int blockID = Integer.parseInt(label.split(Constants.NAME_SEPARATOR)[1]);
    this.block = model.blocks.get(blockID);
  }

  @Override public Description renderDescription() {
    String printLabel = block.principal.renderOutput() + "'s block nr. " + block.rangeEnd;
    String docLine = "Intruder may receive it in messages after " + printLabel + ".";
    return new Description(new Document(docLine), new Document(docLine), this, printLabel);
  }

  @Override public String toString(){
    return "Block(" + super.toString() + ")";
  }
}
