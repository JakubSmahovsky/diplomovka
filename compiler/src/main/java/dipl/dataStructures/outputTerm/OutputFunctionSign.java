package dipl.dataStructures.outputTerm;

import java.util.Arrays;

import dipl.Constants;
import dipl.OutputFormatting;

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
    return OutputFormatting.term(Constants.IN_SIGN, Arrays.asList(key, message));
  }

  @Override
  public boolean reversedArguments() {
    return true;
  }
}