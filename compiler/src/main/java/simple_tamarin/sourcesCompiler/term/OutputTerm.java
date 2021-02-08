package simple_tamarin.sourcesCompiler.term;

import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.dataStructures.term.Term;

/**
 * We want to reuse some classes from the original compiler, 
 * so we have Terms extending Term from there. At the same time, 
 * the newly created Term types don't need to implement all
 * mandatory methods from Term, so we crate this superclass to
 * provide default implementations (throw exceptions).
 */
public abstract class OutputTerm extends Term {
  @Override public Term toCanonical() {
    return null; // TODO debug message
  }
  
  @Override public String render(StBlock block) {
    return null; // TODO debug message
  }

  @Override public String renderLemma() {
    return null; // TODO debug message
  }

  @Override public void removeFresh() {
    // TODO debug message
  }

  @Override public boolean isDeconstructionTerm() {
    return false; // TODO debug message
  }
}
