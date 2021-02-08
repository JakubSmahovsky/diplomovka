package simple_tamarin.dataStructures.term;

import java.util.ArrayList;
import java.util.List;

import simple_tamarin.dataStructures.Principal;
import simple_tamarin.dataStructures.StBlock;

public class Constant extends Term {
  public String word;

  public Constant(String word) {
    super();
    this.word = word;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term canonical = ((Term)obj).toCanonical();
    if (canonical instanceof Constant && ((Constant)canonical).word.equals(this.word)) {
      return true;
    }
    return false;
  }

  @Override public Term toCanonical() {
    return this;
  }

  @Override public List<Variable> extractKnowledge() {
    return new ArrayList<>();
  };

  @Override public String render() {
    return "'" + word + "'";
  }

  @Override public String render(StBlock block) {
    return render();
  }

  @Override public String renderLemma() {
    return render();
  }

  @Override public void removeFresh(){}

  @Override public boolean isDeconstructionTerm(){
    return false;
  }

  @Override public boolean unify(Term term) {
    return this.equals(term);
  }

  public List<Variable> freeVariables(){
    return new ArrayList<>(); 
  }

  @Override public boolean assign(Term right, StBlock block, Principal principal) {
    return this.equals(right);
  }
}
