package simpleT.dataStructures;

  /**
   * A class hoding flags of builtins used by a protocol
   */
public class Builtins {
  // default
  public boolean symmetric_encryption = false;
  public boolean hashing = false;
  public boolean diffie_hellman = false;
  public boolean signing = false;
  // custom
  public boolean restriction_eq = false;
  public boolean prefab_private_reveal = false;
  // other flags
  public boolean principalsWereDeclared = false;
}
