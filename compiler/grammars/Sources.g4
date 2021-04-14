grammar Sources;

sources: group*;

group: 'Sources of' '"' goal '"' '(' NUMBER 'cases)' source*;

source:
  'Source' NUMBER 'of' NUMBER '/' 'named' '"' name=IDENTIFIER '"' PARTIAL_DECONSTRUCTIONS? 
    '"' goal '"'
  'last: none'
  'formulas:'
  'equations:'
    'subst:' subst*
    'conj:' (lemma | (NUMBER '.' lemma?)*)
  'lemmas:' (lemma | (NUMBER '.' lemma?)*)
  'allowed cases: refined'
  'solved formulas:' formula*
  'unsolved goals:'
    (goal '// nr:' NUMBER USEFUL)*
  'solved goals:'
    (goal '// nr:' NUMBER ('(' 'from rule' IDENTIFIER ')')? USEFUL)*
  'json graph:' jsonObj
  SEPARATOR;

goal:
  fact ATTIMEPOINT variable |
  function | // special actions e.g. splitEqs(NUMBER)
  '(' variable ',' NUMBER ')' '~~>' '(' variable ',' NUMBER ')';
fact: (persistent='!')? IDENTIFIER '(' term? (',' term)* ')';


term:
  '(' term ')' |
  term infixOp=('^' | '*') term |
  function |
  constant |
  variable |
  tuple |
  constantFunction = (NUMBER | 'true');

constant: '\'' word=(IDENTIFIER|NUMBER) '\'';
function: IDENTIFIER '(' term? (',' term)* ')';
tuple: '<' term (',' term)* '>';
variable: ('$' | '~' | '#')? IDENTIFIER nameID?;
nameID: '.' NUMBER;

subst: term '<' '~' '{' variable (',' variable)* '}';
formula: term '=' term;
lemma: ('∀' | '∃') variable+ '.' lemmaStmt;
lemmaStmt:
  '(' lemmaStmt ')' |
  goal |
  term ('=' | '<' | '>') term |
  lemmaStmt ('⇒' | '∧') lemmaStmt;

jsonObj: '{' jsonKeyValue? (',' jsonKeyValue)* '}';
jsonArray: '[' jsonValue? (',' jsonValue)* ']';
jsonKeyValue: jsonKey ':' jsonValue;
jsonKey: '"' jsonString '"';
jsonValue: jsonObj | jsonArray | 'true' | 'false' |  '"' jsonString '"'; // todo? number, null


jsonChars: '(' | ')' | '<' | '>' | '{' | '}' | '#' | ':' | '!' | '$' | '.' | ',' | '~' | '\'' | '*' | '^';
jsonString: term | (jsonChars | IDENTIFIER | NUMBER)*;


SEPARATOR: '------------------------------------------------------------------------------';
ATTIMEPOINT:  ('▶' [₀-₉]+) | '@';
PARTIAL_DECONSTRUCTIONS: '(partial deconstructions)';
USEFUL: '" (useful2)"' | '" (currently deducible)"' | '" (probably constructible)"';
NUMBER: [0-9]+;
IDENTIFIER: [a-zA-Z0-9_]+;
WHITESPACE: [ \t\r\n]+ -> skip;
