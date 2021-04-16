package dipl.dataStructures.outputTerm;

import java.util.Arrays;

import dipl.Constants;
import dipl.OutputFormatting;

public class OutputFunctionSenc extends OutputTerm {
  private final OutputTerm key;
  private final OutputTerm value;

  public OutputFunctionSenc(OutputTerm key, OutputTerm value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputFunctionSenc) &&
      key.match(((OutputFunctionSenc)term).key) &&
      value.match(((OutputFunctionSenc)term).value);
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.IN_SENC, Arrays.asList(key, value));
  }

  @Override
  public boolean reversedArguments() {
    return true;
  }
}
