grammar gram;

programa
    : sequencia_de_comandos EOF
    ;

sequencia_de_comandos
    : comando+
    ;

comando
    : exercicio
    | condicional
    | repeticao
    ;

exercicio
    : 'exercicio' nome_exercicio '{' series '}'
    ;

nome_exercicio
    : identificador
    ;

series
    : serie+
    ;

serie
    : 'serie' numero '{' repeticoes '}'
    ;

repeticoes
    : 'repeticao' numero
    ;

condicional
    : 'se' condicao 'entao' sequencia_de_comandos ('senao' sequencia_de_comandos)?
    ;

condicao
    : comparacao
    ;

comparacao
    : expressao operador_relacional expressao
    ;

expressao
    : numero
    | identificador
    ;

repeticao
    : 'enquanto' condicao '{' sequencia_de_comandos '}'
    ;

identificador
    : LETRA (LETRA | DIGITO)*
    ;

numero
    : DIGITO+
    ;

operador_relacional
    : '>' | '<' | '>=' | '<=' | '==' | '!='
    ;

LETRA
    : ('a'..'z' | 'A'..'Z')+
    ('a'..'z'|'A'..'Z' | '0'..'9')*
;

DIGITO
    : ('0'..'9')
    ;

COMENTARIO
	:	'%' ~('\n'|'\r')* '\r'? '\n' {skip();}
	;

WS 	:	( ' ' |'\t' | '\r' | '\n'){skip();}
	;