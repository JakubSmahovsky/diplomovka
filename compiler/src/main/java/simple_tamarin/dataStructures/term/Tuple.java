package simple_tamarin.dataStructures.term;

import java.util.ArrayList;

public class Tuple implements Term{
  public ArrayList<Term> subterms;

  public Tuple(ArrayList<Term> subterms) {
    this.subterms = subterms;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Tuple)) {
      return false;
    }

    if (subterms.size() != ((Tuple)obj).subterms.size()) {
      return false;
    }

    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).equals(((Tuple)obj).subterms.get(i))) {
        return false;
      }
    }

    return true;
  }

  @Override public Term deconstructTerm() {
    return this;
  }

  
}
