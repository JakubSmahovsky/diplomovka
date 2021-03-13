package simpleT.sourcesCompiler.graph.node;

import simpleT.dataStructures.STBlock;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.document.Document;
import simpleT.groupedFunctions.BlockNames;
import simpleT.sourcesCompiler.graph.Description;

public class BlockNode extends Node {
  public STBlock block; // null for custom rule (e.g. init, key reveals)

  public BlockNode(String id, String label, STModel model) {
    super(id, label);
    // assign block to node unless it's a custom rule (e.g. init, key reveals)
    if (!BlockNames.isCustomLabel(label)) {
      this.block = BlockNames.read(model, label);
    }
  }

  @Override public Description renderDescription() {
    return new Description(new Document(), new Document(), this, label);
  }

  @Override public String toString(){
    return "Block(" + super.toString() + ")";
  }
}
