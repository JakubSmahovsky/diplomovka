package simple_tamarin.groupedFunctions;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STBlock;
import simple_tamarin.dataStructures.STModel;

public abstract class BlockNames{
  /**
  * Render the label of this block.
  * The label goes something like: principalName_principalBlockId_modelBlockId
  * Must be consistent with read().
  */
  public static String render(STBlock block) {
    return block.principal.name + Constants.NAMES_SEPARATOR
      + block.rangeEnd + Constants.NAMES_SEPARATOR + block.indexInModel;
  }

  /**
   * Identify a block based on label of a block rule.
   * Caller needs to ensure that label is not an init rule or prefab rule label
   * (using function defined in this file).
   * Must be consistent with render().
   */
  public static STBlock read(STModel model, String label) {
    String id = label.split("_")[2];
    return model.blocks.get(Integer.parseInt(id));
  }

  /**
   * Name for init block. We have to make sure, that other blocks cannot create this name.
   * Currently fine, because other block names consist of 3 words joined by underscore
   * and init is simply called init.
   * Must be consistent with isCustomLabel();
   */
  public static String renderInit() {
    return Constants.INIT;
  }

  /**
   * Name for private reveal block. We have to make sure, that other blocks cannot create this name.
   * Currently fine, because other block names consist of 3 words joined by underscore
   * and private reveal is simply called privateReveal.
   * Must be consistent with isCustomLabel();
   */
  public static String renderPrivateReveal() {
    return Constants.PREFAB_PRIVATE_REVEAL_NAME;
  }

  /**
   * Must be consistent with renderInit() and renderPrivateReveal()
   */
  public static boolean isCustomLabel(String label) {
    return label.equals(Constants.INIT) || label.equals(Constants.PREFAB_PRIVATE_REVEAL_NAME);
  }
}
