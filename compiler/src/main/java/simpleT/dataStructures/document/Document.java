package simpleT.dataStructures.document;

import java.util.LinkedList;

import simpleT.Constants;

/**
 * A class used to manipulte text somewhat efficiently.
 * All methods change the original document but also return it,
 * so that they can be chaned nicely, for example "doc.indent().endl()"
 */
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

  public Document append(Document doc2) {
    doc.addAll(doc2.doc);
    return this;
  }

  public Document indent() {
    for (int i = 0; i < doc.size(); i++) {
      doc.get(i).insert(0, Constants.INDENTATION);
    }
    return this;
  }

  public Document indent(int n) {
    for (int i = 0; i < n; i++) {
      indent();
    }
    return this;
  }

  public Document endl() {
    append(new Document(""));
    return this;
  }


  public Document endl(int n) {
    for (int i = 0; i < n; i++) {
      endl();
    }
    return this;
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
