# Project setup

### Generate ANTLR4 parsers

Get the "comple ANTLR binaries".

Generate the main parser:
`java -jar /path/to/binaries -no-listener -visitor -package dipl.inputParser -o compiler/src/main/java/dipl/inputParser -Xexact-output-dir compiler/grammars/Input.g4`

Generate the sources parser:
`java -jar /path/to/binaries -no-listener -visitor -package dipl.sourcesParser -o compiler/src/main/java/dipl/sourcesParser -Xexact-output-dir compiler/grammars/Sources.g4`

Generate the logging parser:
`java -jar /path/to/binaries -no-listener -visitor -package dipl.loggingParser -o compiler/src/main/java/dipl/loggingParser -Xexact-output-dir compiler/grammars/Logging.g4`

Generate the result parser:
`java -jar /path/to/binaries -no-listener -visitor -package dipl.resultParser -o compiler/src/main/java/dipl/resultParser -Xexact-output-dir compiler/grammars/Result.g4`