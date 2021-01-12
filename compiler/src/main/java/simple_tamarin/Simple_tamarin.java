package simple_tamarin;

import java.io.*;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.CharStreams;

import simple_tamarin.dataStructures.StModel;
import simple_tamarin.errors.STException;
import simple_tamarin.loggingCompiler.*;
import simple_tamarin.loggingParser.*;
import simple_tamarin.sourcesCompiler.SourcesCompilerVisitor;
import simple_tamarin.sourcesParser.*;
import simple_tamarin.stParser.*;

public class Simple_tamarin {
  public static final String theoryFile = "theory.spthy";  // TODO: take name from arguments
  public static void main(String[] args) throws IOException {
    String fileName = args[args.length-1]; // TODO: temporary solution
    FileInputStream inStream = new FileInputStream(fileName);

    Simple_tamarinLexer lexer = new Simple_tamarinLexer(CharStreams.fromStream(inStream));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    Simple_tamarinParser parser = new Simple_tamarinParser(tokens);

    File out = new File(theoryFile);
    FileWriter writer = new FileWriter(out);
    boolean quitOnWarning = false; // TODO
    boolean showInfo = true; // TODO
    CompilerVisitor visitor = new CompilerVisitor(writer, quitOnWarning, showInfo);
    
    try {
      // COMPILE INPUT
      StModel model = visitor.visitModel(parser.model());

      String homedir = System.getProperty("user.home");
      String cmd = homedir + "/.local/bin/tamarin-prover " + theoryFile;

      // COMPILE SOURCES
      Process p = Runtime.getRuntime().exec(cmd);  //TODO: catch
      // input stream contains standard Tamarin output
      InputStream stdStream = p.getInputStream();

      String sources = extractSourcesFromStdOutput(stdStream);
      SourcesLexer sourcesLexer = new SourcesLexer(CharStreams.fromString(sources));

      CommonTokenStream sourcesTokens = new CommonTokenStream(sourcesLexer);
      SourcesParser sourcesParser = new SourcesParser(sourcesTokens);

      out = new File("sources.txt"); // TODO: take name from arguments
      writer = new FileWriter(out);

      SourcesCompilerVisitor sourcesVisitor = new SourcesCompilerVisitor(model, writer);
      sourcesVisitor.visitSources(sourcesParser.sources());

      // COMPILE LOGGING
      cmd = homedir + "/.local/bin/tamarin-prover --prove=executable " + theoryFile;
      p = Runtime.getRuntime().exec(cmd);  //TODO: catch
      // error output contains logging from Tamarin computation
      InputStream errStream = p.getErrorStream();
      LoggingCompilerVisitor loggingVisitor = new LoggingCompilerVisitor(model);
      BufferedReader reader = new BufferedReader(new InputStreamReader(errStream));
      String message = "";
      int linesInMessage = 0;

      for (String line = reader.readLine(); line != null; line = reader.readLine()){
        if (linesInMessage == 0) {
          message = line;
        } else {
          message += line;
        }
        LoggingLexer loggingLexer = new LoggingLexer(CharStreams.fromString(message));
        CommonTokenStream loggingTokes = new CommonTokenStream(loggingLexer);
        LoggingParser loggingParser = new LoggingParser(loggingTokes);
        loggingParser.removeErrorListeners();
        loggingParser.addErrorListener(LoggingErrorListener.INSTANCE);
        try {
          loggingVisitor.visitMessage(loggingParser.message());
        } catch (ParseCancellationException e) {
          linesInMessage++;
          continue;
        }
        linesInMessage = 0;
      }
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
      if (!sources && line.equals(Constants.OUTPUT_SEPARATOR)) {
        String maybeHeader = line + reader.readLine() + reader.readLine();
        if (maybeHeader.equals(Constants.SOURCES_HEADER)) {
          sources = true;
        }
        else {
          System.out.println("Unexpected lines in output:");
          System.out.println(maybeHeader);
        }
      } else if (sources && line.equals(Constants.OUTPUT_SEPARATOR)) {// ignore after sources
        break;
      } else if (sources) {
        result.append(line);
        result.append("\n");
      }
    }

    return result.toString();
  }
}
