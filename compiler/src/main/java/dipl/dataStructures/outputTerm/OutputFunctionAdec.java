package dipl.dataStructures.outputTerm;

import java.util.Arrays;

import dipl.Constants;
import dipl.OutputFormatting;

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
    return OutputFormatting.term(Constants.IN_ADEC, Arrays.asList(key, encryptedValue));
  }

  @Override
  public boolean reversedArguments() {
    return true;
  }
}