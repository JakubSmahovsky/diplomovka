package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.ArrayList;
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

  @Override public Term toCanonical() {
    return this;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).toCanonical();
    if (!(term instanceof FunctionSenc)) {
      return false;
    }
    return (key.equals(((FunctionSenc)term).key) && value.equals(((FunctionSenc)term).value));
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.SENC, Arrays.asList(value, key), null);
  }

  @Override public String render(Term substitution){
    return (new FunctionSenc(key, substitution).render());
  }

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(Constants.SENC, Arrays.asList(value, key));
  }

  @Override public void removeFresh() {
    key.removeFresh();
    value.removeFresh();
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public List<Variable> freeVariables() {
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.freeVariables());
    result.addAll(value.freeVariables());
    return result;
  }
}
