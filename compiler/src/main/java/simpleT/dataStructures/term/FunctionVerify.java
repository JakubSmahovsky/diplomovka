package simpleT.dataStructures.term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simpleT.BuilderFormatting;
import simpleT.Constants;
import simpleT.dataStructures.STBlock;
import simpleT.errors.Errors;

/**
 * Verification of hiding signing, not actually a Term that can be used in
 * messages, should only occour in Eq restriction.
 */
public class FunctionVerify extends Term {
  private final Term key;
  private final Term message;
  private final Term signature;

  public FunctionVerify(Term key, Term message, Term signature) {
    this.key = key;
    this.message = message;
    this.signature = signature;
  }

  @Override public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.NON_NORMAL;
  }

  @Override public int normalFormCompareTo(Term term){
    // throw error, this is not a normal form
    Errors.DebugUnexpectedCall("normalFormCompareTo", "FunctionVerify");
    return 0;
  }

  @Override public Term getNormalForm() {
    return null;
  }

  @Override public boolean equals(Object obj) {
    // throw error, this does not have a valid normal form
    Errors.DebugUnexpectedCall("equals", "FunctionVerify");
    return false;
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.VERIFY, Arrays.asList(signature , message, key), null);
  }

  @Override public String render(STBlock block){
    return BuilderFormatting.fact(Constants.VERIFY, Arrays.asList(signature, message, key), block);
  }

  @Override public boolean isDeconstructionTerm() {
    return true;
  }

  @Override public List<Variable> freeVariables(){
    ArrayList<Variable> result = new ArrayList<>();
    result.addAll(key.freeVariables());
    result.addAll(message.freeVariables());
    result.addAll(signature.freeVariables());
    return result;
  }
}