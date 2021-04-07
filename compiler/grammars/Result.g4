grammar Result;

clause:
  solve source clause next* 'qed' |
  success |
  contradiction;
solve: 'solve' '(' goal ')';
source: 'case' IDENTIFIER;
next: 'next' source clause;

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

constant: '\'' word=IDENTIFIER '\'';
function: IDENTIFIER '(' term? (',' term)* ')';
tuple: '<' term? (',' term)* '>';
variable: ('$' | '~' | '#')? IDENTIFIER nameID?;
nameID: '.' NUMBER;

success: 'SOLVED // trace found';
contradiction: 'by contradiction' CONTRADICTION_REASON;

CONTRADICTION_REASON: '/*' [ a-zA-Z\-]+ '*/';
ATTIMEPOINT: ('▶' [₀-₉]+) | '@';
PERSISTENT: '!';
SOLVEDHOW: '(precomputed)' | '(directly)';
NUMBER: [0-9]+;
IDENTIFIER: ([a-zA-Z_] | NUMBER)+;
WHITESPACE: [ \t\r\n]+ -> skip;