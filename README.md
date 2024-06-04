consiste no desenvolvimento de uma
solução para uma calculadora baseada em pilha para resolver expressões aritméticas simples com
parênteses, colchetes e chaves, e números inteiros. Apenas as seguintes operações serão consideradas:
soma(+), subtração(-), multiplicação(*), divisão(/) e potência(^).

Um exemplo de expressão é:
{ ( 5 + 12 ) + [ ( 10 - 8 ) + 2 ] }
O resultado do processamento desta expressão é 21.

Para isto, inicialmente deve ser implementada uma pilha usando estruturas encadeadas,
obrigatoriamente. Esta pilha deverá ter exatamente os seguintes métodos, conforme visto em aula:
push(e), pop(), top(), size(), isEmpty() e clear().

Depois de implementada, esta pilha deverá obrigatoriamente ser usada para processar uma expressão. A
prioridade das operações será feita somente através do uso de parênteses, colchetes e chaves.
Considere que os números de entrada são números inteiros, mas o resultado pode ser um número
real/double.

O conjunto de expressões que devem ser avaliadas está em um arquivo disponível no Moodle. A solução
a ser implementada deve ler este arquivo e apresentar o resultado de cada expressão, considerando que
algumas delas poderão ter erro de sintaxe (por exemplo, um parêntese ou colchete a mais ou a menos,
ter mais operadores do que operandos, etc.). Além de apresentar o resultado da expressão, deverá
também ser processado o tamanho máximo atingido pela pilha para resolver a expressão, e deverá ser
detalhado o tipo de erro de sintaxe, caso ocorra. Um exemplo do que pode ser apresentado para cada
expressão é apresentado abaixo:

Expressão: { ( 5 + 12 ) + [ ( 10 - 8 ) ^ 3 ] }
Resultado: 25.0
Tamanho máximo da pilha: 8

Expressão: { ( 5 + 12 ) + [ ( 10 - 8 ) ^ 3 } }
Erro de sintaxe: } no lugar de ]
