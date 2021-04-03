package simpleT.dataStructures.outputTerm;

import java.util.Arrays;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionAdec extends OutputTerm{
  private final OutputTerm key;
  private final OutputTerm encryptedValue;

  public OutputFunctionAdec(OutputTerm key, OutputTerm value) {
    this.key = key;
    this.encryptedValue = value;
  }

  @Override
  public boolean match(OutputTerm term) {
    if (!(term instanceof OutputFunctionAdec)) {
      return false;
    }
    OutputFunctionAdec sdec = (OutputFunctionAdec)term;
    return (key.match(sdec.key) && encryptedValue.match(sdec.encryptedValue)); 
  }

  @Override
  public String render(){
    return OutputFormatting.term(Constants.ST_ADEC, Arrays.asList(encryptedValue, key));
  }
}