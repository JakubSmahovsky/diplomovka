
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
  assignment;

knows: 'knows' modifier=('public' | 'private') variable;
generates: 'generates' variable;
assignment: variable '=' term;

messageBlock: sender=IDENTIFIER '->' receiver=IDENTIFIER ':' variable (',' variable)*;

queriesBlock: 'queries' '[' query* ']';

term:
  variable |
  functionCall;

variable: IDENTIFIER;
functionCall: FUNCTION '(' (argument+=term)? (',' argument+=term)* ')';

query: executable;

executable: 'executable?';

FUNCTION:
  'ENC' |
  'DEC';
IDENTIFIER : [a-zA-Z0-9]+;
WHITESPACE : [ \t\r\n]+ -> skip;