package simpleT.resultCompiler.ResultClause;

import java.util.ArrayList;

import simpleT.dataStructures.document.Document;
import simpleT.resultCompiler.ResultSource;
import simpleT.sourcesCompiler.goal.Goal;

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
    Document clause = new Document(goal.render());
    for (int i = 0; i < sources.size(); i++) {
      clause.append(sources.get(i).render())
        .append(children.get(i).render().indent());
    }
    return clause;
  }
}
