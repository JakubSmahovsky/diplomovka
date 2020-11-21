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

  @Override public boolean equals(Object term) {
    if (this == term) {
      return true;
    }
    if (term instanceof FunctionSenc) {
      if (key.equals(((FunctionSenc)term).key) && value.equals(((FunctionSenc)term).value)) {
        return true;
      }
    }
    return false;
  }

  @Override public Term deconstructTerm() {
    return this;
  }

  /**
   * TODO: Info message - don't define variables this way, it's silly
   */
  @Override public List<Variable> unify(Term right) {
    if (!(right instanceof FunctionSenc)) {
      return null;
    }
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.unify(((FunctionSenc)right).key));
    result.addAll(value.unify(((FunctionSenc)right).value));
    return result;
  }
  
  @Override public List<Term> extractKnowledge() {
    return Arrays.asList(this);
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
}
