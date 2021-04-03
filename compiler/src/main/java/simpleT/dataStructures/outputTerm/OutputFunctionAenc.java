package simpleT.dataStructures.outputTerm;

import java.util.Arrays;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionAenc extends OutputTerm {
  private final OutputTerm key;
  private final OutputTerm value;

  public OutputFunctionAenc(OutputTerm key, OutputTerm value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public boolean match(OutputTerm term) {
    return (term instanceof OutputFunctionAenc) &&
      key.match(((OutputFunctionAenc)term).key) &&
      value.match(((OutputFunctionAenc)term).value);
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.ST_AENC, Arrays.asList(key, value));
  }
}