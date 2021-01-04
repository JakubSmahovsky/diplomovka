grammar Sources;

sources: group*;

group: 'Sources of' '"' goal '"' '(' NUMBER 'cases)' source*;

source:
  'Source' NUMBER 'of' NUMBER '/' 'named' '"' name=IDENTIFIER '"' 
    '"' goal '"'
  'last: none'
  'formulas:'
  'equations:'
    'subst:' subst*
    'conj:'
  'lemmas:' lemma*
  'allowed cases: refined'
  'solved formulas:' formula*
  'unsolved goals:'
    (goal '// nr:' NUMBER USEFUL)*
  'solved goals:'
    (goal '// nr:' NUMBER ('(' 'from rule' IDENTIFIER ')')? USEFUL)*
  'json graph:' jsonObj
  SEPARATOR;

goal: fact ATTIMEPOINT variable;
fact: PERSISTENT? IDENTIFIER '(' term? (',' term)* ')';

term:
  function |
  tuple |
  variable;

function: IDENTIFIER '(' term? (',' term)* ')';
tuple: '<' term? (',' term)* '>';
variable: ('$' | '~' | '#')? IDENTIFIER ('.' NUMBER)?;

subst: term SUBSTARROW '{' variable '}';
formula: term '=' term;
lemma: '∀' variable+ '.' lemmaStmt;
lemmaStmt:
  '(' lemmaStmt ')' |
  goal |
  variable '=' variable |
  variable '<' variable |
  variable '>' variable |
  lemmaStmt '⇒' lemmaStmt;

jsonObj: '{' jsonKeyValue? (',' jsonKeyValue)* '}';
jsonArray: '[' jsonValue? (',' jsonValue)* ']';
jsonKeyValue: jsonKey ':' jsonValue;
jsonKey: '"' jsonString '"';
jsonValue: '"' jsonString '"' | jsonObj | jsonArray | 'true' | 'false'; // todo? number, null

jsonChars: '(' | ')' | '<' | '>' | '{' | '}' | '#' | ':' | '!' | '$' | '.' | ',' | '~';
jsonString: (jsonChars | IDENTIFIER | NUMBER)*;


SEPARATOR: '------------------------------------------------------------------------------';
ATTIMEPOINT: '▶₀' | '@';
PERSISTENT: '!';
SUBSTARROW: '<~';
USEFUL: '" (useful2)"' | '" (currently deducible)"' | '" (probably constructible)"';
NUMBER: [0-9]+;
IDENTIFIER: [a-zA-Z0-9_]+;
WHITESPACE: [ \t\r\n]+ -> skip;
