package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;

public class FunctionSenc extends Term {
  public Term key;
  public Term value;

  public FunctionSenc(Term key, Term value) {
    this.key = key;
    this.value = value;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).deconstructTerm();
    if (!(term instanceof FunctionSenc)) {
      return false;
    }
    return (key.equals(((FunctionSenc)term).key) && value.equals(((FunctionSenc)term).value));
  }

  @Override public Term deconstructTerm() {
    return this;
  }

  /**
   * TODO: Info message - don't define variables this way, it's silly
   */
  @Override public List<Variable> unify(Term right) {
    Term deconstructed = right.deconstructTerm();
    if (!(deconstructed instanceof FunctionSenc)) {
      return null;
    }
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.unify(((FunctionSenc)deconstructed).key));
    result.addAll(value.unify(((FunctionSenc)deconstructed).value));
    return result;
  }
  
  @Override public List<Variable> extractKnowledge() {
    return null;
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.SENC, Arrays.asList(value, key));
  }

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(Constants.SENC, Arrays.asList(value, key));
  }

  @Override public void removeFresh() {
    key.removeFresh();
    value.removeFresh();
  }

  @Override public boolean canBeLearnt() {
    return false;
  }
}
