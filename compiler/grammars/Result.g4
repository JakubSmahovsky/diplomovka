grammar Result;

clause:
  'solve' '(' goal ')' source clause ('next' source clause)* 'qed' |
  success |
  contradiction |
  exhausted;
source: 'case' IDENTIFIER;

goal:
  fact ATTIMEPOINT variable | 
  function; // special actions e.g. splitEqs(NUMBER)
fact: PERSISTENT? IDENTIFIER '(' term? (',' term)* ')';

term:
  '(' term ')' |
  term infixOp=('^' | '*') term |
  constant |
  variable |
  function |
  tuple |
  constantFunction = (NUMBER | 'true');

constant: '\'' word=(IDENTIFIER|NUMBER) '\'';
function: IDENTIFIER '(' term? (',' term)* ')';
tuple: '<' term? (',' term)* '>';
variable: ('$' | '~' | '#')? IDENTIFIER nameID?;
nameID: '.' NUMBER;

success: 'SOLVED // trace found';
contradiction: 'by' 'contradiction' CONTRADICTION_REASON;
exhausted: 'by' 'solve' '(' goal ')';

CONTRADICTION_REASON: '/*' [ a-zA-Z\-]+ '*/';
ATTIMEPOINT: ('▶' [₀-₉]+) | '@';
PERSISTENT: '!';
SOLVEDHOW: '(precomputed)' | '(directly)';
NUMBER: [0-9]+;
IDENTIFIER: ([a-zA-Z_] | NUMBER)+;
WHITESPACE: [ \t\r\n]+ -> skip;