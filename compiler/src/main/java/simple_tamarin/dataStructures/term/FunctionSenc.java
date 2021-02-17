package simple_tamarin.dataStructures.term;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.errors.Errors;
import simple_tamarin.stParser.Simple_tamarinParser.TermContext;

public class FunctionSenc extends Term {
  private final Term key;
  private final Term value;

  public FunctionSenc(Term key, Term value) {
    this.key = key;
    this.value = value;
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.FunctionSenc;
  }

  @Override public int canonicalCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionSenc, compare based on subterms
    FunctionSenc functionSenc = (FunctionSenc)term;  
    result = key.compareTo(functionSenc.key);
    if (result != 0) {
      return result;
    }
    return value.compareTo(functionSenc.value);
  }

  @Override public Term toCanonical() {
    return new FunctionSenc(key.toCanonical(), value.toCanonical());
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

  @Override public String render(STBlock block){
    return BuilderFormatting.fact(Constants.SENC, Arrays.asList(value, key), block);
  }

  @Override public String render(Term substitution){
    return (new FunctionSenc(key, substitution).render());
  }

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(Constants.SENC, Arrays.asList(value, key));
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public boolean unify(Term term) {
    if (!(term instanceof FunctionSenc)) {
      return false;
    }
    FunctionSenc senc = (FunctionSenc)term;
    return (key.unify(senc.key) && value.unify(senc.value)); 
  }

  @Override public List<Variable> freeVariables() {
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.freeVariables());
    result.addAll(value.freeVariables());
    return result;
  }

  @Override public Term decode(Term key, TermContext keyCtx, TermContext valueCtx) {
    if (!this.key.equals(key)) {
      Errors.ErrorWrongKey(keyCtx);
    }
    return value;
  }
}
