package simple_tamarin.sourcesCompiler.term;

import java.util.Arrays;

import simple_tamarin.BuilderFormatting;
import simple_tamarin.Constants;
import simple_tamarin.dataStructures.term.Term;

public class OutputFSdec extends OutputTerm{
  public Term key;
  public Term encodedValue;

  public OutputFSdec(Term key, Term value) {
    this.key = key;
    this.encodedValue = value;
  }

  @Override public boolean unify(Term term) {
    if (!(term instanceof OutputFSdec)) {
      return false;
    }
    OutputFSdec sdec = (OutputFSdec)term;
    return (key.unify(sdec.key) && encodedValue.unify(sdec.encodedValue)); 
  }

  @Override public String render(){
    return BuilderFormatting.fact(Constants.VPSDEC, Arrays.asList(encodedValue, key), null);
  }
}