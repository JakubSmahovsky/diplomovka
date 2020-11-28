package simple_tamarin.dataStructures;

public class Builtins {
  // default
  public boolean symmetric_encryption;
  public boolean hashing;
  // custom
  public boolean restriction_eq;

  public Builtins() {
    symmetric_encryption = false;
    hashing = false;

    restriction_eq = false;
  }
}
