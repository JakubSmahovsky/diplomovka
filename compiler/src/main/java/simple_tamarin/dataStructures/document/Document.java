package simple_tamarin.dataStructures.document;

import java.util.LinkedList;

import simple_tamarin.Constants;

public class Document {
  public LinkedList<StringBuilder> doc;
  
  public Document() {
    this.doc = new LinkedList<>();
  }

  public Document(StringBuilder s) {
    this.doc = new LinkedList<>();
    doc.add(s);
  }

  public void indent() {
    for (int i = 0; i < doc.size(); i++) {
      doc.get(i).insert(0, Constants.INDENT);
    }
  }

  public void indent(int n) {
    for (int i = 0; i < n; i++) {
      indent();
    }
  }

  public Document clone() {
    Document clone = new Document();
    for (StringBuilder s : doc) {
      clone.doc.add(new StringBuilder(s));
    }
    return clone;
  }

  @Override public String toString() {
    return String.join("\r\n", doc);
  }
}
