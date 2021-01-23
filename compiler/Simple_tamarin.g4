
grammar Simple_tamarin;

model: segment*;

segment: 
  declaration |
  principalBlock |
  messageBlock |
  queriesBlock;

declaration: decPrincipals;

decPrincipals: KEYWORD_PRINCIPALS ':' principal+=IDENTIFIER (',' principal+=IDENTIFIER)*;

principalBlock: principal=IDENTIFIER '[' command* ']';

command:
  knows |
  generates |
  assignment |
  check;

knows: 'knows' modifier=('public' | 'private') variable (',' variable)*;
generates: 'generates' variable (',' variable)*;
assignment: left=term '=' right=term;
check: checkedCall '?';

messageBlock: sender=IDENTIFIER '->' receiver=IDENTIFIER ':' term (',' term)*;

queriesBlock: 'queries' '[' query* ']';

term:
  variable |
  functionCall |
  tuple;

variable: IDENTIFIER;
functionCall: FUNCTION '(' (argument+=term)? (',' argument+=term)* ')';
checkedCall: CHECKED '(' (argument+=term)? (',' argument+=term)* ')';
tuple: '{' term (',' term)* '}';

query: 
  executable |
  confidentiality;

confidentiality: 'confidentiality?' (principal=IDENTIFIER '\'s')? variable;
executable: 'executable?';

FUNCTION:
  'ENC' |
  'DEC' |
  'HASH';

CHECKED:
  'ASSERT';

KEYWORD_PRINCIPALS: 'principals';

IDENTIFIER : [a-zA-Z0-9]+;
WHITESPACE : [ \t\r\n]+ -> skip;