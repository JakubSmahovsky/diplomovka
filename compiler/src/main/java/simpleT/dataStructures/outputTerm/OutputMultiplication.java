package simpleT.dataStructures.outputTerm;

import simpleT.Constants;

public class OutputMultiplication extends OutputTerm{
  private final OutputTerm left, right;

  public OutputMultiplication(OutputTerm left, OutputTerm right) {
    this.left = left;
    this.right = right;
  }
  
  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputMultiplication) && 
           (left.match(((OutputMultiplication)term).left)) &&
           (right.match(((OutputMultiplication)term).right));
  }
  @Override
  public String render() {
    return left.render() + Constants.T_MUL + right.render();
  }
}
