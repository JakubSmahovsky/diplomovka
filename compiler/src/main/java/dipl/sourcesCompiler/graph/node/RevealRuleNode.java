package dipl.sourcesCompiler.graph.node;

import dipl.Constants;
import dipl.dataStructures.Principal;
import dipl.dataStructures.Model;
import dipl.dataStructures.document.Document;
import dipl.sourcesCompiler.graph.Description;

public class RevealRuleNode extends Node{
  public Principal principal;

  public RevealRuleNode(String nodeID, String nodeLabel, Model model) {
    super(nodeID, nodeLabel);
    int principalID = Integer.parseInt(label.split(Constants.NAME_SEPARATOR)[0].substring(Constants.FACT_PREFIX_PRINCIPALID.length()));
    for (Principal principal : model.getPrincipals()) {
      if (model.getVariable(principalID) == principal.principalID) {
        this.principal = principal;
        return;
      }
    }
  }

  @Override
  public Description renderDescription(boolean protocolRuleParent) {
    String printLabel = "long-term reveal on " + principal.renderOutput();
    String docLine = "Intruder may receive it by performing long-term reveal on a principal in the role of " + principal.renderOutput() + ".";
    return new Description(new Document(docLine), this, printLabel);
  }
  
  @Override
  public String render(){
    return "Reveal(" + this.label + ")";
  }
}
