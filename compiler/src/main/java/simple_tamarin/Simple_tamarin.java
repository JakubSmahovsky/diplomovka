package simple_tamarin;

import java.io.*;
import org.antlr.v4.runtime.CommonTokenStream;

import simple_tamarin.errors.STException;
import simple_tamarin.stParser.*;

public class Simple_tamarin {
  public static void main(String[] args) throws IOException {
    String fileName = args[args.length-1]; // TODO: temporary solution
    FileInputStream inStream = new FileInputStream(fileName);

    Simple_tamarinLexer lexer = new Simple_tamarinLexer(org.antlr.v4.runtime.CharStreams.fromStream(inStream));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    Simple_tamarinParser parser = new Simple_tamarinParser(tokens);
    
    File out = new File("theory.spthy"); // TODO: take name from arguments
    FileWriter writer = new FileWriter(out);
    boolean quitOnWarning = false; // TODO
    boolean showInfo = true; // TODO
    CompilerVisitor visitor = new CompilerVisitor(writer, quitOnWarning, showInfo);
    
    try {
      visitor.visitModel(parser.model());
    } catch (STException e) {
      e.print();
    }

    try {
      String homedir = System.getProperty("user.home");
      String cmd = homedir + "/.local/bin/tamarin-prover theory.spthy";

      Process p = Runtime.getRuntime().exec(cmd);
      // input stream contains standard Tamarin output
      BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
      // error output contains logging from Tamarin computation
      // BufferedReader errorStream = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    
      for (String line = inputStream.readLine(); line != null; line = inputStream.readLine()) {
        System.out.println(line);
      }
    } catch (Exception e) {
      System.out.println(e);
    }


  }
}
