package dipl.sourcesCompiler.goal;

public abstract class Goal {
  /**
   * Find out if this goal has the same structure as the provided goal 
   * and is at least as general as the provided goal.
   */
  public abstract boolean match(Goal goal);

  public abstract String render();

  public boolean shouldBeHidden() {
    return false;
  }
}
