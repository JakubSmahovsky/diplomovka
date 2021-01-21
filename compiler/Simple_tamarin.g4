
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

knows: 'knows' modifier=('public' | 'private') variable (',' variable)*;
generates: 'generates' variable (',' variable)*;
assignment: left=term '=' right=term;
check: functionCall '?';

messageBlock: sender=IDENTIFIER '->' receiver=IDENTIFIER ':' term (',' term)*;

queriesBlock: 'queries' '[' query* ']';

term:
  variable |
  functionCall |
  tuple;

variable: IDENTIFIER;
functionCall: FUNCTION '(' (argument+=term)? (',' argument+=term)* ')';
tuple: '{' term (',' term)* '}';

query: 
  executable |
  confidentiality;

confidentiality: 'confidentiality?' (principal=IDENTIFIER '\'s')? variable;
executable: 'executable?';

FUNCTION:
  'ENC' |
  'DEC' |
  'HASH' |
  // checkables
  'ASSERT';

IDENTIFIER : [a-zA-Z0-9]+;
WHITESPACE : [ \t\r\n]+ -> skip;