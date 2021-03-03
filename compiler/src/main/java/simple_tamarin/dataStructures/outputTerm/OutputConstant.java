package simple_tamarin.dataStructures.outputTerm;

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
    return "\'" + word + "\'";
  }
  
}
