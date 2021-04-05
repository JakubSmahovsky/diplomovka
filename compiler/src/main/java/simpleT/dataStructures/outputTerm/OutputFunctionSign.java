package simpleT.dataStructures.outputTerm;

import java.util.Arrays;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionSign extends OutputTerm{
  private final OutputTerm key;
  private final OutputTerm message;

  public OutputFunctionSign(OutputTerm key, OutputTerm message) {
    this.key = key;
    this.message = message;
  }

  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputFunctionSign) &&
            key.match(((OutputFunctionSign)term).key) &&
            message.match(((OutputFunctionSign)term).message);
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.ST_SIGN, Arrays.asList(key, message));
  }

  @Override
  public boolean reversedArguments() {
    return true;
  }
}