
grammar Simple_tamarin;

model: declaration* specificationSegment+ queriesBlock?;

declaration: decPrincipals;

decPrincipals: KEYWORD_PRINCIPALS ':' principal+=IDENTIFIER (',' principal+=IDENTIFIER)*;

specificationSegment:
  principalBlock | 
  message;

principalBlock: principal=IDENTIFIER '[' command* ']';

command:
  distributed |
  knows |
  generates |
  assignment |
  check;

distributed: 'distributed' variable '=' term;
knows: 'knows' modifier=('public' | 'private') variable (',' variable)*;
generates: 'generates' variable (',' variable)*;
assignment: left=term '=' right=term;
check: checkedCall '?';

message: sender=IDENTIFIER '->' receiver=IDENTIFIER ':' term (',' term)*;

queriesBlock: 'queries' '[' query* ']';

term:
  '(' term ')' | 
  term POWER_OP term |
  constant |
  variable |
  functionCall |
  tuple;

constant: '\'' word=IDENTIFIER '\'';
variable: IDENTIFIER;
functionCall: FUNCTION '(' (argument+=term)? (',' argument+=term)* ')';
tuple: '{' term (',' term)* '}';

checkedCall: CHECKED '(' (argument+=term)? (',' argument+=term)* ')';

query: 
  executable |
  confidentiality;

confidentiality: 'confidentiality?' (principal=IDENTIFIER '\'s')? variable;
executable: 'executable?';

FUNCTION:
  'PK' |
  'SIGN' |
  'ENC' |
  'DEC' |
  'HASH';

CHECKED:
  'SIGNVERIF' |
  'ASSERT';

POWER_OP: '^';

KEYWORD_PRINCIPALS: 'principals';

IDENTIFIER : [a-zA-Z0-9]+;
WHITESPACE : [ \t\r\n]+ -> skip;
