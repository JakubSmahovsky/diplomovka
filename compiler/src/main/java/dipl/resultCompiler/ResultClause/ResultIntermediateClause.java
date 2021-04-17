package dipl.resultCompiler.ResultClause;

import java.util.ArrayList;

import dipl.dataStructures.document.Document;
import dipl.resultCompiler.ResultSource;
import dipl.sourcesCompiler.goal.Goal;

public class ResultIntermediateClause extends ResultClause{
  public final Goal goal;
  public final ArrayList<ResultSource> sources;
  public final ArrayList<ResultClause> children;

  public ResultIntermediateClause(Goal goal, ArrayList<ResultSource> sources, ArrayList<ResultClause> children) {
    this.goal = goal;
    this.sources = sources;
    this.children = children;
  }

  @Override
  public Document render() {
    // if the clause should be hidden then hide goal, hide every source and don't indent childern
    Document clause = new Document();
    if (!goal.hideEverywhere()) {
      clause.append(goal.render());
    }
    for (int i = 0; i < sources.size(); i++) {
      Document childDoc = children.get(i).render();
      if (!goal.hideEverywhere()) {
        clause.append(sources.get(i).render());
        childDoc.indent();
      }
      clause.append(childDoc);
    }
    return clause;
  }
}
