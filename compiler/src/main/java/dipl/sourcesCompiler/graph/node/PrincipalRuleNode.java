package dipl.sourcesCompiler.graph.node;

import dipl.Constants;
import dipl.dataStructures.Block;
import dipl.dataStructures.Model;
import dipl.dataStructures.document.Document;
import dipl.sourcesCompiler.graph.Description;

public class PrincipalRuleNode extends Node {
  public final Block block;
  private final String printLabel;

  public PrincipalRuleNode(String id, String label, Model model) {
    super(id, label);
    int blockID = Integer.parseInt(label.split(Constants.NAME_SEPARATOR)[1]);
    this.block = model.blocks.get(blockID);
    this.printLabel = block.principal.renderOutput() + "'s block nr. " + block.rangeEnd;
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    // if this is before a rule, then it passes state, otherwise it sends a message
    String docLine = protocolRuleParent ? (printLabel + " precedes it") : ("which he receives after " + printLabel);
    Description description = new Description(new Document(docLine), this, printLabel);
    for (Node parent : parents) {
      Description parentDescription = parent.renderDescription(true);
      description.doc.append(parentDescription.doc.indent());
    }
    return description;
  }

  @Override
  public String render(){
    return "Block(" + this.label + ")";
  }
}
