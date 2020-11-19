
grammar Simple_tamarin;

model: segment*;

segment: 
  principalBlock |
  messageBlock |
  queriesBlock;

principalBlock: principal=IDENTIFIER '[' command* ']';

command:
  knows |
  generates |
  assignment |
  check;

knows: 'knows' modifier=('public' | 'private') variable;
generates: 'generates' variable;
assignment: variable '=' term;
check: functionCall '?';

messageBlock: sender=IDENTIFIER '->' receiver=IDENTIFIER ':' variable (',' variable)*;

queriesBlock: 'queries' '[' query* ']';

term:
  variable |
  functionCall |
  tuple;

variable: IDENTIFIER;
functionCall: FUNCTION '(' (argument+=term)? (',' argument+=term)* ')';
tuple: '{' term (',' term)* '}';

query: executable;

executable: 'executable?';

FUNCTION:
  'ENC' |
  'DEC' |
  // checkables
  'ASSERT';

IDENTIFIER : [a-zA-Z0-9]+;
WHITESPACE : [ \t\r\n]+ -> skip;