package dipl.dataStructures.term;

import java.util.ArrayList;
import java.util.List;

import dipl.Constants;
import dipl.dataStructures.Fact;
import dipl.dataStructures.Model;
import dipl.dataStructures.Principal;
import dipl.inputParser.InputParser.TermContext;
import dipl.dataStructures.Block;

public class Constant extends Term {
  private static int constants = 0;
  private final int id;
  private final String word;

  public Constant(String word) {
    this.id = nextId();
    this.word = word;
  }

  private int nextId() {
    return constants++;
  }

  @Override
  public NormalFormTypeOrder getTypeOrder() {
    return NormalFormTypeOrder.Constant;
  }

  @Override
  public int normalFormCompareTo(Term term) {
    int result = this.getTypeOrder().compareTo(term.getTypeOrder());
    if (result != 0) {
      return result;
    }
    // both have to be Constants, compare based on id
    return Integer.compare(this.id, ((Constant)term).id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {
      return false;
    }
    Term normalForm = ((Term)obj).getNormalForm();
    if (normalForm instanceof Constant && ((Constant)normalForm).word.equals(this.word)) {
      return true;
    }
    return false;
  }

  @Override
  public Term getNormalForm() {
    return this;
  }

  @Override
  public List<Variable> extractKnowledge() {
    return new ArrayList<>();
  };

  @Override
  public String render() {
    return Constants.CONSTANT_OPEN + word + Constants.CONSTANT_CLOSE;
  }

  @Override
  public String render(Block block) {
    return render();
  }

  @Override
  public boolean isDeconstructionTerm(){
    return false;
  }

  public List<Variable> freeVariables(){
    return new ArrayList<>(); 
  }

  @Override
  public boolean assign(Term right, boolean rightIndirection, Block block) {
    if (!rightIndirection) {
      block.principal.model.builtins.restriction_eq = true;
      block.actions.add(Fact.equality(this, right));
    }
    return this.equals(right);
  }

  @Override
  public Term sentToReceived(Model model, Principal recipient, TermContext messageCtx) {
    return this;
  }
}
