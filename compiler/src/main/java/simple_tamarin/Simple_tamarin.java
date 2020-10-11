package simple_tamarin;

import java.io.*;
import org.antlr.v4.runtime.CommonTokenStream;
import simple_tamarin.parser.*;

public class Simple_tamarin {
  public static void main(String[] args) throws IOException {
    String fileName = args[args.length-1]; // TODO: temporary solution
    FileInputStream in = new FileInputStream(fileName);
    Simple_tamarinLexer lexer = new Simple_tamarinLexer(org.antlr.v4.runtime.CharStreams.fromStream(in));

    CommonTokenStream tokens = new CommonTokenStream(lexer);
    Simple_tamarinParser parser = new Simple_tamarinParser(tokens);
    VisitorImp visitor = new VisitorImp();

    visitor.visitModel(parser.model());
  }
}
