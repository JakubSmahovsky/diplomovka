package simpleT.dataStructures.outputTerm;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionPk extends OutputTerm{
  private final OutputTerm sk;

  public OutputFunctionPk(OutputTerm sk) {
    this.sk = sk;
  }

  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputFunctionPk) && sk.match(((OutputFunctionPk)term).sk);
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.ST_PK, sk);
  }
}