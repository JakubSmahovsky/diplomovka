package simple_tamarin.dataStructures.term;

import simple_tamarin.dataStructures.STBlock;

public class ValueTrue extends Term {
  private static ValueTrue instance = new ValueTrue();
  private ValueTrue() {}

  public static ValueTrue instance() {
    return instance;
  }

  @Override public CanonicalTypeOrder getTypeOrder() {
    return CanonicalTypeOrder.ValueTrue;
  }

  @Override public int canonicalCompareTo(Term term) {
    return this.getTypeOrder().compareTo(term.getTypeOrder());
  }

  @Override public Term getCanonical() {
    return this;
  }

  @Override public boolean equals(Object obj) {
    return this == obj;
  }

  @Override public String render() {
    return "true";
  }

  @Override public String render(STBlock block) {
    return render();
  }

  @Override public String renderLemma() {
    return render();
  }

  @Override public boolean isDeconstructionTerm() {
    return false;
  }
}
