# Project setup

### Generate ANTLR4 parsers

Get the "comple ANTLR binaries".

Generate the main parser:
`java -jar /path/to/binaries -no-listener -visitor -package simple_tamarin.stParser -o compiler/src/main/java/simple_tamarin/stParser -Xexact-output-dir compiler/grammars/Simple_tamarin.g4`

Generate the sources parser:
`java -jar /path/to/binaries -no-listener -visitor -package simple_tamarin.sourcesParser -o compiler/src/main/java/simple_tamarin/sourcesParser -Xexact-output-dir compiler/grammars/Sources.g4`

Generate the logging parser:
`java -jar /path/to/binaries -no-listener -visitor -package simple_tamarin.loggingParser -o compiler/src/main/java/simple_tamarin/loggingParser -Xexact-output-dir compiler/grammars/Logging.g4`