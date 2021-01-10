package simple_tamarin.groupedFunctions;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StBlock;
import simple_tamarin.dataStructures.StModel;

public abstract class BlockNames{
  /**
  * Render the label of this block.
  * The label goes something like: principalName_principalBlockId_modelBlockId
  * Must be consistent with read().
  */
  public static String render(StBlock block) {
    return block.principal.name + Constants.NAMES_SEPARATOR
      + block.rangeBegin + Constants.NAMES_SEPARATOR + block.indexInModel;
  }

  /**
   * Identify a block based on label of a block rule.
   * Caller needs to ensure that label in not an init rule label
   * (using function defined in this file).
   * Must be consistent with render().
   */
  public static StBlock read(StModel model, String label) {
    String id = label.split("_")[2];
    return model.blocks.get(Integer.parseInt(id));
  }

  /**
   * Name for init block. We have to make sure, that other blocks cannot create this name.
   * Currently fine, because other block names consist of 3 words joined by underscore
   * and init is simply called init.
   * Must be consistent with isInitLable();
   */
  public static String renderInit() {
    return Constants.INIT;
  }

  /**
   * Must be consistent with renderInit()
   */
  public static boolean isInitLabel(String label) {
    return label.equals(Constants.INIT);
  }

}
