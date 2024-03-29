package dipl.dataStructures.term;

import dipl.Constants;
import dipl.dataStructures.Block;

public class ValueTrue extends Term {
  private static ValueTrue instance = new ValueTrue();
  private ValueTrue() {}

  public static ValueTrue instance() {
    return instance;
  }

  @Override
  public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.ValueTrue;
  }

  @Override
  public int normalFormCompareTo(Term term) {
    return this.getTypeOrder().compareTo(term.getTypeOrder());
  }

  @Override
  public Term getNormalForm() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    return this == obj;
  }

  @Override
  public String render() {
    return Constants.IN_TRUE;
  }

  @Override
  public String render(Block block) {
    return render();
  }

  @Override
  public boolean isDeconstructionTerm() {
    return false;
  }
}
