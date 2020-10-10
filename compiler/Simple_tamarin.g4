
grammar Simple_tamarin;

model: IDENTIFIER ;

IDENTIFIER : [a-zA-Z0-9]+ ;
WHITESPACE : [ \t\r\n]+ -> skip ;