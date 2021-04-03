
grammar SimpleT;

protocol: declaration* specificationSegment+ queriesBlock?;

declaration:
  decPrincipals |
  decUnaryEquals |
  decHideInfo |
  decQuitOnWarning;

decPrincipals: 'principals' ':' principal+=IDENTIFIER (',' principal+=IDENTIFIER)*;
decUnaryEquals: 'unary-equals' ':' value=('implicit'|'explicit'|'default');
decHideInfo: 'hide-info' ':' value=('true'|'false');
decQuitOnWarning: 'quit-on-warning' ':' value=('true'|'false');

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

message: sender=IDENTIFIER '->' recipient=IDENTIFIER ':' term (',' term)*;

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
  confidentiality |
  forwardSecrecy |
  authentication |
  injAuthentication;

executable: 'executable?';
confidentiality: 'confidentiality?' principal=IDENTIFIER '\'s' variable;
forwardSecrecy: 'forward-secrecy?' principal=IDENTIFIER '\'s' variable;
authentication: 'authentication?' sender=IDENTIFIER '->' recipient=IDENTIFIER ':' variable;
injAuthentication: 'injective-authentication?' sender=IDENTIFIER '->' recipient=IDENTIFIER ':' variable;

FUNCTION:
  'PK' |
  'SIGN' |
  'ENC' |
  'DEC' |
  'AENC' |
  'ADEC' |
  'HASH';

CHECKED:
  'SIGNVERIF' |
  'EQUALS';

POWER_OP: '^';

IDENTIFIER: [a-zA-Z0-9]+;
LINE_COMMENT: ('//' (~'\n')*) -> skip;
WHITESPACE: [ \t\r\n]+ -> skip;
