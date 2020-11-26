package simple_tamarin.errors;

public class STException extends RuntimeException {
  private static final long serialVersionUID = 9186286201311471968L; 
  public String message;

  public STException(String message) {
    this.message = message;
  }

  public STException() {
    this.message = "Terminating due to unrecoverable errors!";
  }

  public void print() {
    System.out.println(message);
  }
}
