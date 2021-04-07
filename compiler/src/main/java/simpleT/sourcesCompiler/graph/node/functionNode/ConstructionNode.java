package simpleT.sourcesCompiler.graph.node.functionNode;

import java.util.ArrayList;

import simpleT.dataStructures.document.Document;
import simpleT.dataStructures.outputTerm.OutputTerm;
import simpleT.sourcesCompiler.graph.Description;
import simpleT.sourcesCompiler.graph.node.Node;

public class ConstructionNode extends FunctionNode {
  public ConstructionNode(String id, String label, OutputTerm term) {
    super(id, label, term);
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    Document doc = new Document("Adversary constructs " + term.render() + " using " + render() + " on messages from:");
    Node rule = null;
    
    // maybe reverse order of parents
    ArrayList<Node> orderedParents;
    if (term.reversedArguments()) {
      orderedParents = new ArrayList<>();
      for (int i = this.parents.size()-1; i >= 0; i--) {
        orderedParents.add(this.parents.get(i));
      }
    } else {
      orderedParents = parents;
    }

    String sourceDescription = "construction";
    for (Node parent : orderedParents) {
      Description parentDesc = parent.renderDescription(false);
      if (parentDesc.rule != null) {
        rule = parentDesc.rule;
        sourceDescription = parentDesc.sourceDescription;
      }
      parentDesc.doc.indent();
      doc.append(parentDesc.doc);
    }
    return new Description(doc, rule, sourceDescription);
  }
  
}
