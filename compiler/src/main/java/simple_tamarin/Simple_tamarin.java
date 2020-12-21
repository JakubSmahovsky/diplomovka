package simple_tamarin;

import java.io.*;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import simple_tamarin.dataStructures.StModel;
import simple_tamarin.errors.STException;
import simple_tamarin.sourcesCompiler.SourcesCompilerVisitor;
import simple_tamarin.sourcesParser.SourcesLexer;
import simple_tamarin.sourcesParser.SourcesParser;
import simple_tamarin.stParser.*;

public class Simple_tamarin {
  public static void main(String[] args) throws IOException {
    String fileName = args[args.length-1]; // TODO: temporary solution
    FileInputStream inStream = new FileInputStream(fileName);

    Simple_tamarinLexer lexer = new Simple_tamarinLexer(CharStreams.fromStream(inStream));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    Simple_tamarinParser parser = new Simple_tamarinParser(tokens);
    
    File out = new File("theory.spthy"); // TODO: take name from arguments
    FileWriter writer = new FileWriter(out);
    boolean quitOnWarning = false; // TODO
    boolean showInfo = true; // TODO
    CompilerVisitor visitor = new CompilerVisitor(writer, quitOnWarning, showInfo);
    
    try {
    StModel model = visitor.visitModel(parser.model());

    String homedir = System.getProperty("user.home");
    String cmd = homedir + "/.local/bin/tamarin-prover theory.spthy";

    Process p = Runtime.getRuntime().exec(cmd);  //TODO: catch
    // input stream contains standard Tamarin output
    InputStream stdStream = p.getInputStream();
    // error output contains logging from Tamarin computation
    // InputStream errorStream = p.getErrorStream();

    String sources = extractSourcesFromStdOutput(stdStream);
    SourcesLexer sourcesLexer = new SourcesLexer(CharStreams.fromString(sources));

    CommonTokenStream sourcesTokens = new CommonTokenStream(sourcesLexer);
    SourcesParser sourcesParser = new SourcesParser(sourcesTokens);

    SourcesCompilerVisitor sourcesVisitor = new SourcesCompilerVisitor(model);
    sourcesVisitor.visitSources(sourcesParser.sources());
    } catch (STException e) {
      e.print();
    }
  }

  private static String extractSourcesFromStdOutput(InputStream stdStream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(stdStream));
    StringBuilder result = new StringBuilder();
    
    boolean sources = false;
    for (String line = reader.readLine(); line != null; line = reader.readLine()){
      // ignore untill sources heder
      if (!sources && line.equals(Constants.OUTPUTSEPARATOR)) {
        String maybeHeader = line + reader.readLine() + reader.readLine();
        if (maybeHeader.equals(Constants.SOURCESHEADER)) {
          sources = true;
        }
        else {
          System.out.println("Unexpected lines in output:");
          System.out.println(maybeHeader);
        }
      } else if (sources && line.equals(Constants.OUTPUTSEPARATOR)) {// ignore after sources
        break;
      } else if (sources) {
        result.append(line);
        result.append("\n");
      }
    }

    return result.toString();
  }
}

