package simple_tamarin.sourcesCompiler.graph.node;

import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.dataStructures.StModel;
import simple_tamarin.groupedFunctions.BlockNames;

public class BlockNode extends Node {
  public StBlock block; // null for init rule

  public BlockNode(String id, String label, StModel model) {
    super(id, label);
    // assign block to node unless it's an init rule
    if (!BlockNames.isInitLabel(label)) {
      this.block = BlockNames.read(model, label);
    }
  }

  @Override public String toString(){
    return "Block(" + super.toString() + ")";
  }
}
