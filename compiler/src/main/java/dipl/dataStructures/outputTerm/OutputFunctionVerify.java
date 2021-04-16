package dipl.dataStructures.outputTerm;

import java.util.Arrays;

import dipl.Constants;
import dipl.OutputFormatting;

public class OutputFunctionVerify extends OutputTerm{
  private final OutputTerm key;
  private final OutputTerm message;
  private final OutputTerm signature;

  public OutputFunctionVerify(OutputTerm key, OutputTerm message, OutputTerm signature) {
    this.key = key;
    this.message = message;
    this.signature = signature;
  }

  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputFunctionVerify) &&
            key.match(((OutputFunctionVerify)term).key) &&
            message.match(((OutputFunctionVerify)term).message) &&
            signature.match(((OutputFunctionVerify)term).signature);
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.IN_SIGNVERIF, Arrays.asList(key, message, signature));
  }

  @Override
  public boolean reversedArguments() {
    return true;
  }
}
