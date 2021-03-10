package simple_tamarin.dataStructures.outputTerm;

import simple_tamarin.Constants;

public class OutputMultiplication extends OutputTerm{
  private final OutputTerm left, right;

  public OutputMultiplication(OutputTerm left, OutputTerm right) {
    this.left = left;
    this.right = right;
  }
  
  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof OutputMultiplication) && 
           (left.unify(((OutputMultiplication)term).left)) &&
           (right.unify(((OutputMultiplication)term).right));
  }
  @Override
  public String render() {
    return left.render() + Constants.MUL + right.render();
  }
}
