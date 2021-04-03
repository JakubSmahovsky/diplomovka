package simpleT;

import java.io.*;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import simpleT.dataStructures.STModel;
import simpleT.errors.STException;
import simpleT.loggingCompiler.*;
import simpleT.loggingParser.*;
import simpleT.sourcesCompiler.SourcesCompilerVisitor;
import simpleT.sourcesParser.*;
import simpleT.stParser.*;

import org.antlr.v4.runtime.CharStreams;

public class SimpleT {
  public static void main(String[] args) throws IOException {
    // TODO: parse arguments properly
    String inputFilePath = args[args.length-1];
    String tamarinTheoryFilePath = Constants.DEFAULT_THEORY_PATH + Constants.THEORY_FILE_EXTENSION;
    String tamarinExecutablePath = System.getProperty("user.home") + "/.local/bin/tamarin-prover";

    
    try {
      STModel model = compileInput(inputFilePath, tamarinTheoryFilePath);
      compileSources(tamarinExecutablePath, tamarinTheoryFilePath, Constants.DEFAULT_SOURCES_PATH, model);
      BufferedReader resultStdReader = compileLogging(tamarinExecutablePath, tamarinTheoryFilePath, "secrecy0", model);
      compileResult(resultStdReader);
    } catch (STException e) {
      e.print();
    }
  }

  private static STModel compileInput(String inputFilePath, String tamarinTheoryFilePath) throws IOException, STException{
    File out = new File(tamarinTheoryFilePath);
    FileWriter writer = new FileWriter(out);
    FileInputStream inputStream = new FileInputStream(inputFilePath);
    SimpleTLexer lexer = new SimpleTLexer(CharStreams.fromStream(inputStream));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    SimpleTParser parser = new SimpleTParser(tokens);
    CompilerVisitor visitor = new CompilerVisitor(writer);
    return visitor.visitProtocol(parser.protocol());
  }

  private static void compileSources(String tamarinExecutablePath, String tamarinTheoryFilePath,
        String sourcesOutputFilePath, STModel model) throws STException, IOException {
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
      sources.append(line);
      sources.append("\n");
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
  private static BufferedReader compileLogging(String tamarinExecutablePath, String tamarinTheoryFilePath,
        String lemmaToProve, STModel model) throws STException, IOException {
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
    return stdStreamReader;
  }

  private static void compileResult(BufferedReader stdReader) throws IOException, STException{
    StringBuilder resultTrace = new StringBuilder();

    // discard untill the proved lemma
    for (String line = stdReader.readLine(); line != null; line = stdReader.readLine()) {
      if (line.equals("simplify")) {
        break;
      }
    }
    // read trace untill an empty line
    for (String line = stdReader.readLine(); line != null; line = stdReader.readLine()) {
      if (line.equals("")) {
        break;
      }
      resultTrace.append(line);
      resultTrace.append("\n");
    }

    System.out.println();
    System.out.println("TRACE:");
    System.out.println(resultTrace.toString());
  }

  private static boolean resultWasPrinted(BufferedReader stdStreamReader) throws IOException {
    String line = stdStreamReader.readLine();
    int length = ("theory ").length();
    return line.length() >= length && line.substring(0, ("theory ").length()).equals("theory ");
  }
}
