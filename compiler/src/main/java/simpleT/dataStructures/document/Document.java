package simpleT.dataStructures.document;

import java.util.LinkedList;

import simpleT.Constants;

public class Document {
  public LinkedList<StringBuilder> doc;
  
  public Document() {
    this.doc = new LinkedList<>();
  }

  public Document(StringBuilder s) {
    this.doc = new LinkedList<>();
    doc.add(s);
  }

  public Document(String s) {
    this.doc = new LinkedList<>();
    doc.add(new StringBuilder(s));
  }

  public void append(Document doc2) {
    doc.addAll(doc2.doc);
  }

  public void indent() {
    for (int i = 0; i < doc.size(); i++) {
      doc.get(i).insert(0, Constants.INDENTATION);
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

  @Override
  public String toString() {
    return String.join(Constants.LINE_BREAK, doc);
  }
}
