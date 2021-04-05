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
  public Description renderDescription() {
    Document shortDoc = new Document("Adversary constructs " + term.render() + " using " + render() + " on messages from:");
    Document longDoc = shortDoc.clone();
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

    for (Node parent : orderedParents) {
      Description parentDesc = parent.renderDescription();
      if (parentDesc.rule != null) {
        rule = parentDesc.rule;
        shortDoc.doc.addAll(parentDesc.shortDoc.doc);
      }
      parentDesc.longDoc.indent();
      longDoc.doc.addAll(parentDesc.longDoc.doc);
    }
    return new Description(shortDoc, longDoc, rule, "construction");
  }
  
}
