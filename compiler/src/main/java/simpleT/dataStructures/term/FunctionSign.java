package simpleT.dataStructures.term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.errors.Errors;
import simpleT.stParser.SimpleTParser.TermContext;

public class FunctionSign extends Term {
  private final Term key;
  private final Term message;
  private final FunctionSign normalForm;

  public FunctionSign(Term key, Term message) {
    this.key = key;
    this.message = message;
    this.normalForm = new FunctionSign(this);
  }

  /**
   * normal form constructor
   */
  private FunctionSign(FunctionSign original) {
    this.normalForm = this;
    this.key = original.key.getNormalForm();
    this.message = original.message.getNormalForm();
  }

  @Override
  public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.FunctionSign;
  }

  @Override
  public int normalFormCompareTo(Term term){
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

  @Override
  public Term getNormalForm() {
    return normalForm;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term term = ((Term)obj).getNormalForm();
    if (!(term instanceof FunctionSign)) {
      return false;
    }
    FunctionSign functionSign = (FunctionSign)term;
    return key.equals(functionSign.key) && message.equals(functionSign.message);
  }

  @Override
  public String render(){
    return BuilderFormatting.fact(Constants.T_SIGN, Arrays.asList(message, key), null);
  }

  @Override
  public String render(STBlock block){
    return BuilderFormatting.fact(Constants.T_SIGN, Arrays.asList(message, key), block);
  }

  @Override
  public boolean isDeconstructionTerm() {
    return false;
  }

  @Override
  public List<Variable> freeVariables(){
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.freeVariables());
    result.addAll(message.freeVariables());
    return result;
  }

  @Override
  public void verifySignature(Term pk, Term message, TermContext pkCtx, TermContext messageCtx, TermContext signatureCtx) {
    if (!pk.getNormalForm().verifyPk(key, pkCtx)) {
      Errors.ErrorSignVerifKeyNotMatch(pkCtx);
    }
    if (!this.message.equals(message)) {
      Errors.ErrorSignVerifMessageNotMatch(messageCtx);
    }
  }
}
