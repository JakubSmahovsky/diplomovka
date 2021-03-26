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
  public boolean unify(OutputTerm term) {
    if (!(term instanceof OutputFunctionSdec)) {
      return false;
    }
    OutputFunctionSdec sdec = (OutputFunctionSdec)term;
    return (key.unify(sdec.key) && encryptedValue.unify(sdec.encryptedValue)); 
  }

  @Override
  public String render(){
    return OutputFormatting.term(Constants.VPSDEC, Arrays.asList(encryptedValue, key));
  }
}