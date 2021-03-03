package simple_tamarin.dataStructures.outputTerm;

import java.util.Arrays;

import simple_tamarin.Constants;
import simple_tamarin.OutputFormatting;

public class OutputFunctionSenc extends OutputTerm {
  private final OutputTerm key;
  private final OutputTerm value;

  public OutputFunctionSenc(OutputTerm key, OutputTerm value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public boolean unify(OutputTerm term) {
    return (term instanceof OutputFunctionSenc) &&
      key.unify(((OutputFunctionSenc)term).key) &&
      value.unify(((OutputFunctionSenc)term).value);
  }

  @Override
  public String render() {
    return OutputFormatting.term(Constants.VPSENC, Arrays.asList(key, value));
  }
}
