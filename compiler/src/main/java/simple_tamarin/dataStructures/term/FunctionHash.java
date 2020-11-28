package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;

public class FunctionHash extends Term {
  public ArrayList<Term> subterms;

  public FunctionHash(ArrayList<Term> subterms) {
    this.subterms = subterms;
  }

  @Override
  public Term deconstructTerm() {
    return this;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).deconstructTerm();
    if (!(term instanceof FunctionHash) || subterms.size() != ((FunctionHash)term).subterms.size()) {
      return false;
    }
    for (int i = 0; i < subterms.size(); i++) {
      if (!subterms.get(i).equals(((FunctionHash)term).subterms.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<Variable> unify(Term right) {
    if (!(right instanceof FunctionHash) || subterms.size() != ((FunctionHash)right).subterms.size()) {
      return null;
    }
    
    ArrayList<Variable> result = new ArrayList<>();
    for (int i = 0; i < subterms.size(); i++) {
      List<Variable> subresult = subterms.get(i).unify(((Tuple)right).subterms.get(i));
      if (subresult == null) {
        return null;
      }
      result.addAll(subresult);
    }
    return result;
  }

  @Override
  public List<Variable> extractKnowledge() {
    return new ArrayList<>();
  }


  @Override
  public String render() {
    List<Term> tuple = subterms;
    // Tamarin knows only an unary hash, so we have to pack values in a Tuple
    if (subterms.size() > 1) {
      tuple = Arrays.asList(new Tuple(subterms));
    }
    return BuilderFormatting.fact(Constants.HASH, tuple);
  }

  @Override
  public String renderLemma() {
    List<Term> tuple = subterms;
    // Tamarin knows only an unary hash, so we have to pack values in a Tuple
    if (subterms.size() > 1) {
      tuple = Arrays.asList(new Tuple(subterms));
    }
    return BuilderFormatting.lemmaFact(Constants.HASH, tuple);
  }

  @Override
  public void removeFresh() {
    for (Term term : subterms) {
      term.removeFresh();
    }
  }

  @Override
  public boolean canBeLearnt() {
    return false;
  }
}
