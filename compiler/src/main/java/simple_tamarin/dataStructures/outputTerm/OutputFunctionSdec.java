package simple_tamarin.dataStructures.outputTerm;

import java.util.Arrays;

import simple_tamarin.Constants;
import simple_tamarin.OutputFormatting;

public class OutputFunctionSdec extends OutputTerm{
  private final OutputTerm key;
  private final OutputTerm encodedValue;

  public OutputFunctionSdec(OutputTerm key, OutputTerm value) {
    this.key = key;
    this.encodedValue = value;
  }

  @Override
  public boolean unify(OutputTerm term) {
    if (!(term instanceof OutputFunctionSdec)) {
      return false;
    }
    OutputFunctionSdec sdec = (OutputFunctionSdec)term;
    return (key.unify(sdec.key) && encodedValue.unify(sdec.encodedValue)); 
  }

  @Override
  public String render(){
    return OutputFormatting.term(Constants.VPSDEC, Arrays.asList(encodedValue, key));
  }
}