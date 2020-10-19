
grammar Simple_tamarin;

model: segment*;

segment: 
  principalBlock |
  messageBlock |
  queriesBlock;

principalBlock: principal=IDENTIFIER '[' command* ']';

command: 
  knows |
  generates;

knows: 'knows' modifier=('public' | 'private') IDENTIFIER;
generates: 'generates' IDENTIFIER;

messageBlock: sender=IDENTIFIER '->' receiver=IDENTIFIER ':' message+=IDENTIFIER (',' message+=IDENTIFIER)*;

queriesBlock: 'queries' '[' query* ']';

query: 'executable?';

IDENTIFIER : [a-zA-Z0-9]+;
WHITESPACE : [ \t\r\n]+ -> skip;