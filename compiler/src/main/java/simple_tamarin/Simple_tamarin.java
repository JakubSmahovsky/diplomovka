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
  public static void main(String[] args) throws IOException { // TODO: catch IOException
    // TODO: parse arguments properly
    String inputFilePath = args[args.length-1];
    boolean quitOnWarning = false;
    boolean showInfo = true;
    String tamarinTheoryFilePath = Constants.DEFAULT_THEORY_PATH + Constants.MANDATORY_THEORY_EXTENSION;
    String tamarinExecutablePath = System.getProperty("user.home") + "/.local/bin/tamarin-prover";

    
    try {
      StModel model = compileInput(inputFilePath, tamarinTheoryFilePath, quitOnWarning, showInfo);
      compileSources(tamarinExecutablePath, tamarinTheoryFilePath, Constants.DEFAULT_SOURCES_OUTPUT_PATH, model);
      compileLogging(tamarinExecutablePath, tamarinTheoryFilePath, "secrecy0", model);
    } catch (STException e) {
      e.print();
    }
  }

  private static StModel compileInput(String inputFilePath, String tamarinTheoryFilePath,
        boolean quitOnWarning, boolean showInfo) throws IOException, STException{
    File out = new File(tamarinTheoryFilePath);
    FileWriter writer = new FileWriter(out);
    FileInputStream inputStream = new FileInputStream(inputFilePath);
    Simple_tamarinLexer lexer = new Simple_tamarinLexer(CharStreams.fromStream(inputStream));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    Simple_tamarinParser parser = new Simple_tamarinParser(tokens);
    CompilerVisitor visitor = new CompilerVisitor(writer, quitOnWarning, showInfo);
    return visitor.visitModel(parser.model());
  }

  private static void compileSources(String tamarinExecutablePath, String tamarinTheoryFilePath,
        String sourcesOutputFilePath, StModel model) throws STException, IOException {
    String command = tamarinExecutablePath + " " + tamarinTheoryFilePath;
    Process process = Runtime.getRuntime().exec(command);
    InputStream stdStream = process.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(stdStream));

    StringBuilder sources = new StringBuilder();

    // discard untill sources header
    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
      if (line.equals(Constants.OUTPUT_SEPARATOR)) {
        String maybeHeader = line + reader.readLine() + reader.readLine();
        if (maybeHeader.equals(Constants.SOURCES_HEADER)) {
          break;
        }
        throw new STException("Unexpected lines in Tamarin output: \n" + maybeHeader);
      }
    } 
    // read sources and discard the rest
    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
      if (line.equals(Constants.OUTPUT_SEPARATOR)) {
        break;
      }
      sources.append(line + "\n");
    }

    File outputFile = new File(sourcesOutputFilePath);
    FileWriter fileWriter = new FileWriter(outputFile);
    SourcesLexer sourcesLexer = new SourcesLexer(CharStreams.fromString(sources.toString()));
    CommonTokenStream sourcesTokens = new CommonTokenStream(sourcesLexer);
    SourcesParser sourcesParser = new SourcesParser(sourcesTokens);
    SourcesCompilerVisitor sourcesVisitor = new SourcesCompilerVisitor(model, fileWriter);
    sourcesVisitor.visitSources(sourcesParser.sources());
  }

  /**
   * Compile logging messages from Tamarin and return input stream containing result from Tamarin
   */
  private static InputStream compileLogging(String tamarinExecutablePath, String tamarinTheoryFilePath,
        String lemmaToProve, StModel model) throws STException, IOException {
    String command = tamarinExecutablePath + " --prove=" + lemmaToProve + " " + tamarinTheoryFilePath;
    Process process = Runtime.getRuntime().exec(command);
    // error output contains logging from Tamarin computation
    InputStream errStream = process.getErrorStream();
    BufferedReader errStreamReader = new BufferedReader(new InputStreamReader(errStream));
    // standard output contains result after computation ends
    InputStream stdStream = process.getInputStream();
    BufferedReader stdStreamReader = new BufferedReader(new InputStreamReader(stdStream));

    LoggingCompilerVisitor loggingVisitor = new LoggingCompilerVisitor(model);
    String message = "";
    int storedUncompiledLines = 0;

    while (true) {
      if (errStream.available() == 0) {
        if (stdStream.available() == 0 || !resultWasPrinted(stdStreamReader)) {
          Thread.yield();
          continue;
        } else {
          break;
        }
      }
      String line = errStreamReader.readLine();
      message = storedUncompiledLines == 0 ? line : message + line;
      LoggingLexer loggingLexer = new LoggingLexer(CharStreams.fromString(message));
      CommonTokenStream loggingTokes = new CommonTokenStream(loggingLexer);
      LoggingParser loggingParser = new LoggingParser(loggingTokes);
      loggingParser.removeErrorListeners();
      loggingParser.addErrorListener(LoggingErrorListener.INSTANCE);
      try {
        loggingVisitor.visitMessage(loggingParser.message());
      } catch (ParseCancellationException e) {
        storedUncompiledLines++;
        continue;
      }
      storedUncompiledLines = 0;
    }
    return stdStream;
  }

  private static boolean resultWasPrinted(BufferedReader stdStreamReader) throws IOException {
    String line = stdStreamReader.readLine();
    int length = ("theory ").length();
    return line.length() >= length && line.substring(0, ("theory ").length()).equals("theory ");
  }
}
