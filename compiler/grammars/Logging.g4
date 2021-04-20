grammar Logging;

message: solved | by;
solved: 'solved goal nr.' NUMBER SOLVEDHOW ':' goal;
by: NUMBER 'by:' IDENTIFIER;

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

ATTIMEPOINT: ('▶' [₀-₉]+) | '@';
PERSISTENT: '!';
SOLVEDHOW: '(precomputed)' | '(directly)';
NUMBER: [0-9]+;
IDENTIFIER: ([a-zA-Z_] | NUMBER)+;

UNPARSED_TOKEN: '"' | '⊥';
WHITESPACE: [ \t\r\n]+ -> skip;