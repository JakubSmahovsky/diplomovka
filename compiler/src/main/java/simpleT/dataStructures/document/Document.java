package simpleT.dataStructures.document;

import java.util.LinkedList;

import simpleT.Constants;

/**
 * A class used to manipulte text somewhat efficiently (code-wise).
 * All methods change the original document but also return it,
 * so that they can be chaned nicely, for example "doc.indent().endl()".
 * After using any of the methods, the arguments should be discarded,
 * because we don't clone objects and they may change later.
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

  public Document append(String s) {
    doc.add(new StringBuilder(s));
    return this;
  }

  public Document prepend(Document doc2) {
    for (int i = doc2.doc.size()-1; i >= 0; i--) {
      doc.addFirst(doc2.doc.get(i));
    }
    return this;
  }

  public Document prepend(String s) {
    doc.addFirst(new StringBuilder(s));
    return this;
  }

  public Document appendToLastLine(String s) {
    doc.getLast().append(s);
    return this;
  }

  public Document prependToFistLine(String s) {
    doc.getFirst().insert(0, s);
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

  public Document bracket() {
    return prependToFistLine(Constants.OPEN_BR).appendToLastLine(Constants.CLOSE_BR);    
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
