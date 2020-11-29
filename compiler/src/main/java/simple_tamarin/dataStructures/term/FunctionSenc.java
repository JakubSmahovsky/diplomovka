package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StBlock;

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
  
  @Override public List<Variable> extractKnowledge() {
    return null;
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.SENC, Arrays.asList(value, key), null);
  }

  @Override public String render(StBlock block) {
    return render();
  }

  @Override public String render(List<Term> substitutions){
    return (new FunctionSenc(key, substitutions.get(0))).render();
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
}
