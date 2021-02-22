package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.errors.Errors;
import simple_tamarin.stParser.Simple_tamarinParser.TermContext;

public class FunctionSign extends Term {
  private final Term key;
  private final Term message;
  private final FunctionSign canonical;

  public FunctionSign(Term key, Term message) {
    this.key = key;
    this.message = message;
    this.canonical = new FunctionSign(this);
  }

  /**
   * Canonical form constructor
   */
  private FunctionSign(FunctionSign original) {
    this.canonical = this;
    this.key = original.key.getCanonical();
    this.message = original.message.getCanonical();
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.FunctionSign;
  }

  @Override public int canonicalCompareTo(Term term){
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be FunctionSign, compare based on subterms
    FunctionSign functionSign = (FunctionSign)term;  
    result = key.compareTo(functionSign.key);
    if (result != 0) {
      return result;
    }
    return message.compareTo(functionSign.message);
  }

  @Override public Term getCanonical() {
    return canonical;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).getCanonical();
    if (!(term instanceof FunctionSign)) {
      return false;
    }
    FunctionSign functionSign = (FunctionSign)term;
    return key.equals(functionSign.key) && message.equals(functionSign.message);
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.SIGN, Arrays.asList(message, key), null);
  }

  @Override public String render(STBlock block){
    return BuilderFormatting.fact(Constants.SIGN, Arrays.asList(message, key), block);
  }

  @Override public String renderLemma(){
    return BuilderFormatting.lemmaFact(Constants.SIGN, Arrays.asList(message, key), null);
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }

  @Override public boolean unify(Term term) {
    if (!(term instanceof FunctionSign)) {
      return false;
    }
    FunctionSign sign = (FunctionSign)term;
    return (key.unify(sign.key) && message.unify(sign.message)); 
  }

  @Override public List<Variable> freeVariables(){
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.freeVariables());
    result.addAll(message.freeVariables());
    return result;
  }

  @Override public void verifySignature(Term pk, Term message, TermContext pkCtx, TermContext messageCtx, TermContext signatureCtx) {
    if (!pk.getCanonical().verifyPk(key, pkCtx)) {
      Errors.ErrorWrongPublicKeySigning(pkCtx);
    }
    if (!this.message.equals(message)) {
      Errors.ErrorWrongMessageSigning(messageCtx);
    }
  }
}
