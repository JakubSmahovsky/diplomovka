package simpleT.dataStructures.outputTerm;

import simpleT.Constants;

public class OutputConstant extends OutputTerm{
  private final String word;

  public OutputConstant(String word) {
    this.word = word;
  }

  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof OutputConstant) && word.equals(((OutputConstant)term).word);
  }

  @Override
  public String render() {
    return Constants.CONSTANT_OPEN + word + Constants.CONSTANT_CLOSE;
  }
  
}
