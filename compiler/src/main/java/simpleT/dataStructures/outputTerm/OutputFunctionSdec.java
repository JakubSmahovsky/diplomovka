package simpleT.dataStructures.outputTerm;

import java.util.Arrays;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionSdec extends OutputTerm{
  private final OutputTerm key;
  private final OutputTerm encryptedValue;

  public OutputFunctionSdec(OutputTerm key, OutputTerm value) {
    this.key = key;
    this.encryptedValue = value;
  }

  @Override
  public boolean match(OutputTerm term) {
    if (!(term instanceof OutputFunctionSdec)) {
      return false;
    }
    OutputFunctionSdec sdec = (OutputFunctionSdec)term;
    return (key.match(sdec.key) && encryptedValue.match(sdec.encryptedValue)); 
  }

  @Override
  public String render(){
    return OutputFormatting.term(Constants.ST_SDEC, Arrays.asList(key, encryptedValue));
  }

  @Override
  public boolean reversedArguments() {
    return true;
  }
}