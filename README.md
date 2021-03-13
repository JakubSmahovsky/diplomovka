# Project setup

### Generate ANTLR4 parsers

Get the "comple ANTLR binaries".

Generate the main parser:
`java -jar /path/to/binaries -no-listener -visitor -package simpleT.stParser -o compiler/src/main/java/simpleT/stParser -Xexact-output-dir compiler/grammars/SimpleT.g4`

Generate the sources parser:
`java -jar /path/to/binaries -no-listener -visitor -package simpleT.sourcesParser -o compiler/src/main/java/simpleT/sourcesParser -Xexact-output-dir compiler/grammars/Sources.g4`

Generate the logging parser:
`java -jar /path/to/binaries -no-listener -visitor -package simpleT.loggingParser -o compiler/src/main/java/simpleT/loggingParser -Xexact-output-dir compiler/grammars/Logging.g4`