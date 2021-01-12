grammar Logging;

message: solved | by;
solved: 'solved goal nr.' NUMBER SOLVEDHOW ':' goal;
by: 'by:' IDENTIFIER;

goal: fact ATTIMEPOINT variable;
fact: PERSISTENT? IDENTIFIER '(' term? (',' term)* ')';

term:
  function |
  tuple |
  variable;

function: IDENTIFIER '(' term? (',' term)* ')';
tuple: '<' term? (',' term)* '>';
variable: ('$' | '~' | '#')? IDENTIFIER ('.' NUMBER)?;

ATTIMEPOINT: '▶₀' | '@';
PERSISTENT: '!';
SOLVEDHOW: '(precomputed)' | '(directly)';
NUMBER: [0-9]+;
IDENTIFIER: ([a-zA-Z_] | NUMBER)+;
WHITESPACE: [ \t\r\n]+ -> skip;