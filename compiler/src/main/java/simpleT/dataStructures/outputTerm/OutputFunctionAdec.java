package simpleT.dataStructures.outputTerm;

import java.util.Arrays;

import simpleT.Constants;
import simpleT.OutputFormatting;

public class OutputFunctionAdec extends OutputTerm{
  private final OutputTerm key;
  private final OutputTerm encodedValue;

  public OutputFunctionAdec(OutputTerm key, OutputTerm value) {
    this.key = key;
    this.encodedValue = value;
  }

  @Override
  public boolean unify(OutputTerm term) {
    if (!(term instanceof OutputFunctionAdec)) {
      return false;
    }
    OutputFunctionAdec sdec = (OutputFunctionAdec)term;
    return (key.unify(sdec.key) && encodedValue.unify(sdec.encodedValue)); 
  }

  @Override
  public String render(){
    return OutputFormatting.term(Constants.VPADEC, Arrays.asList(encodedValue, key));
  }
}