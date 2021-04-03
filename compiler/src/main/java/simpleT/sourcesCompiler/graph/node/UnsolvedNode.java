package simpleT.sourcesCompiler.graph.node;

import simpleT.dataStructures.document.Document;
import simpleT.sourcesCompiler.graph.Description;

public class UnsolvedNode extends Node {
  public String term;
  public UnsolvedNode(String id, String label) {
    super(id, label);
    this.term = labelToTermString(label);
  }

  /**
   * Unsolved nodes follow the same formatting, it's term surrouned by
   * one space on each side and enclosed in !KU() function, for example
   * !KU( term ), so we just cut off the constant parts;
   */
  public static String labelToTermString(String label) {
    return label.substring(4, label.length()-1);
  }

  @Override
  public Description renderDescription() {
    StringBuilder myLine = new StringBuilder("Intruder gets it from unknown source (source endpoint).");
    return new Description(new Document(myLine), new Document(new StringBuilder(myLine)), null, null);
  }

  @Override
  public String render(){
    return "Unsolved(" + term + ")";
  }
}