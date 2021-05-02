# [Project](https://github.com/JakubSmahovsky/diplomovka) setup

## Requirements
The project only runs on linux because of Tamarin-Prover. We have done our testing on Ubuntu 20.04 LTS but we see no reson why it shouldn't work on other linux distributions.

RAM helps. Our setup has 16GB of memory and som tests exhaust almost all of it (Yahalom confidentiality). Some properties should run fine on 4GB but that should be minimum. Whenever we say that a protocol "does not terminate", it actually runs out of memory and gets killed.

Officially, you need java 11 but some older java versions should work too.

## Recommended installation
You need to install maude, graphviz and haskell stack in order to build and use Tamarin. We HIGHLY recommend getting them using homebrew (it takes a while):
```
brew install tamarin-prover/tap/maude graphviz haskell-stack
```

Add user's private bin to PATH. This is where stack places binaries including Tamarin-Prover. Our tool also looks for the altered Tamarin-Prover binary here. For example, add the following line to your ~/.profile and restart:
```
PATH="$HOME/.local/bin:$PATH"
```

You will have to clone our fork of [tamarin-prover](https://github.com/JakubSmahovsky/tamarin-prover). Enter the cloned repository and run:
```
make default
```
This will take a while, because it downloads and installs GHC and builds the entire Tamarin-Prover project.

Run the application (from the top directory) using the following command (replace `<case_study_to_verify>` with the path to a case study or your own protocol).
```
java -cp antlr.jar:dipl.jar dipl.Dipl <case_study_to_verify>
```
We also recommend redirrecting standard output to a file in order to separate it from the logging, e.g:
```
java -cp antlr.jar:dipl.jar dipl.Dipl <case_study_to_verify>  > out.txt
```
The list of sources will be generated in `sources.txt`.
We do not delete the tamarin input after completion, it remains in `theory.spthy`.

## Building from source
Everything in the recommended installation still applies, but you can build the jar yourself. Since you are reading this, you have to build it from source unless you contact me to get the packaged version.


### Generate ANTLR4 parsers
The parsers aren't tracked by the repository, because they are generated using the ANTLR4 tool.
Get the "comple ANTLR binaries verison 4.9.2" (higher versions should be fine).

Generate the input parser:
```
java -jar /path/to/binaries -no-listener -visitor -package dipl.inputParser -o compiler/src/main/java/dipl/inputParser -Xexact-output-dir compiler/grammars/Input.g4
```


Generate the sources parser:
```
java -jar /path/to/binaries -no-listener -visitor -package dipl.sourcesParser -o compiler/src/main/java/dipl/sourcesParser -Xexact-output-dir compiler/grammars/Sources.g4
```

Generate the logging parser:
```
java -jar /path/to/binaries -no-listener -visitor -package dipl.loggingParser -o compiler/src/main/java/dipl/loggingParser -Xexact-output-dir compiler/grammars/Logging.g4
```

Generate the result parser:
```
java -jar /path/to/binaries -no-listener -visitor -package dipl.resultParser -o compiler/src/main/java/dipl/resultParser -Xexact-output-dir compiler/grammars/Result.g4
```

### Build the jar
Get maven and run
`mvn package`
within the compiler folder.
The dipl.jar will be placed inside the target folder.
