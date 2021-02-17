package simple_tamarin.sourcesCompiler.term;

import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.dataStructures.term.Term;

/**
 * We want to reuse some classes from the original compiler, 
 * so we have Terms extending Term from there. At the same time, 
 * the newly created Term types don't need to implement all
 * mandatory methods from Term, so we crate this superclass to
 * provide default implementations (throw exceptions).
 */
public abstract class OutputTerm extends Term {
  @Override public CanonicalTypeOrder getTypeOrder() {
    return null; // TODO rework output Terms
  }

  @Override public int canonicalCompareTo(Term term) {
    return 0; // TODO rework output Terms
  }

  @Override public Term getCanonical() {
    return null; // TODO debug message
  }
  
  @Override public String render(STBlock block) {
    return null; // TODO debug message
  }

  @Override public String renderLemma() {
    return null; // TODO debug message
  }

  @Override public boolean isDeconstructionTerm() {
    return false; // TODO debug message
  }
}
