package simple_tamarin;

import java.io.*;
import org.antlr.v4.runtime.CommonTokenStream;

import simple_tamarin.errors.STException;
import simple_tamarin.parser.*;

public class Simple_tamarin {
  public static void main(String[] args) throws IOException {
    String fileName = args[args.length-1]; // TODO: temporary solution
    FileInputStream inStream = new FileInputStream(fileName);

    Simple_tamarinLexer lexer = new Simple_tamarinLexer(org.antlr.v4.runtime.CharStreams.fromStream(inStream));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    Simple_tamarinParser parser = new Simple_tamarinParser(tokens);
    
    File out = new File("out.spthy"); // TODO: take name from arguments
    FileWriter writer = new FileWriter(out);
    boolean quitOnWarning = false; // TODO
    boolean showInfo = true; // TODO
    VisitorImp visitor = new VisitorImp(writer, quitOnWarning, showInfo);
    
    try {
      visitor.visitModel(parser.model());
    } catch (STException e) {
      System.out.println("Terminating due to unrecoverable errors!");
    }
  }
}
