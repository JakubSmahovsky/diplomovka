package simpleT.sourcesCompiler.graph.node;

import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

public class BlockNode extends Node {
  public STBlock block; // null for custom rule (e.g. init, key reveals)

  public BlockNode(String id, String label, STModel model) {
    super(id, label);
    // assign principal block to this node if it has the correct format: FACT_NAME + principalID + NAMES_SEPARATOR + blockID
    if (label.matches(Constants.FACT_NAME + "[0-9]+" + Constants.NAMES_SEPARATOR + "[0-9]+")) {
      int blockID = Integer.parseInt(label.split(Constants.NAMES_SEPARATOR)[1]);
      this.block = model.blocks.get(blockID);
    }
  }

  @Override public Description renderDescription() {
    String printLabel = block == null ? label : (block.principal.renderOutput() + "'s block nr. " + block.rangeEnd);
    String docLine;
    if (block != null) {
      docLine = "Intruder may receive it in messages after " + printLabel + ".";
    } else {
      docLine = "Intruder may receive it from a compromited principal.";
      // TODO: identities in reveals
    }
    return new Description(new Document(docLine), new Document(docLine), this, printLabel);
  }

  @Override public String toString(){
    return "Block(" + super.toString() + ")";
  }
}
