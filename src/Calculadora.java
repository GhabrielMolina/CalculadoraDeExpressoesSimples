public class Calculadora {
  /**
   * 
   * @param operador
   * @return a precedência do operador passado como parâmetro
   */
  private static int precedencia(char operador) {
    switch (operador) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
      default:
        return -1;
    }
  }

  /**
   * 
   * @param b
   * @param a
   * @param op
   * @return o resultado da operação matemática entre os dois números passados
   */
  private static double aplicarOperacoes(double b, double a, char op) {
    switch (op) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        if (b == 0)
          throw new UnsupportedOperationException("Não pode dividir por zero");
        return a / b;
      case '^':
        return Math.pow(a, b);
    }
    return 0;
  }

  /**
   * 
   * @param expressao
   * @return o resultado da expressão matemática (double) passada como parâmetro
   *         em forma de string
   */
  public static double calcularExpressao(String expressao) {
    Pilha valores = new Pilha();
    Pilha ops = new Pilha();
    int tamanhoMaxPilha = 0;

    // Percorre a expressão caractere por caractere.
    for (int i = 0; i < expressao.length(); i++) {
      char caractereAtual = expressao.charAt(i);

      if (Character.isDigit(caractereAtual)) {
        double num = 0;
        // Enquanto o caractere atual for um dígito, concatena o número. Exemplo: 123 =
        // 1*100 + 2*10 + 3*1 = 123.
        while (i < expressao.length() && Character.isDigit(expressao.charAt(i))) {
          // Transforma o caractere em número e concatena.
          num = num * 10 + (expressao.charAt(i) - '0');
          i++;
        }
        // Decrementa o índice para não pular nenhum caractere.
        i--;
        valores.push(num);
      } else if (caractereAtual == '(' || caractereAtual == '[' || caractereAtual == '{') {
        ops.push(caractereAtual);
      } else if (caractereAtual == ')' || caractereAtual == ']' || caractereAtual == '}') {
        // Enquanto a pilha de operadores não estiver vazia e o topo da pilha não for um
        // caractere de abertura de parênteses ou colchetes ou chaves.
        while (!ops.isEmpty() && !estaAbrindoParenteses((char) ops.top())) {
          // Empilha o resultado da operação entre os dois valores e o operador.
          valores.push(aplicarOperacoes(valores.pop(), valores.pop(), (char) ops.pop()));
        }
        // Verifica se a pilha de operadores não está vazia e se o caractere atual é um
        // caractere de fechamento correspondente ao caractere de abertura no topo da
        // pilha.
        if (!ops.isEmpty() && !parentesesCorrespondentes((char) ops.pop(), caractereAtual)) {
          throw new RuntimeException(
              "Erro de sintaxe: " + caractereAtual + " no lugar de "
                  + combinarParentesesDeAbertura(caractereAtual));
        }
      } else if (caractereAtual == '+' || caractereAtual == '-' || caractereAtual == '*' || caractereAtual == '/'
          || caractereAtual == '^') {
        // Enquanto a pilha de operadores não estiver vazia e a precedência do caractere
        // atual for menor ou igual à precedência do operador no topo da pilha de
        // operadores.
        while (!ops.isEmpty() && precedencia(caractereAtual) <= precedencia((char) ops.top())) {
          valores.push(aplicarOperacoes(valores.pop(), valores.pop(), (char) ops.pop()));
        }
        ops.push(caractereAtual);
      }
      tamanhoMaxPilha = Math.max(tamanhoMaxPilha, valores.size() + ops.size());
    }

    while (!ops.isEmpty()) {
      // Verifica se o topo da pilha de operadores não é um caractere de abertura de
      // parênteses ou colchetes ou chaves.
      if (estaAbrindoParenteses((char) ops.top())) {
        throw new RuntimeException("Erro de sintaxe: " + (char) ops.top() + " não fechado");
      }
      valores.push(aplicarOperacoes(valores.pop(), valores.pop(), (char) ops.pop()));
    }

    if (valores.size() > 1) {
      throw new RuntimeException("Erro de sintaxe: mais operandos do que operadores");
    }

    System.out.println("Tamanho máximo da pilha: " + tamanhoMaxPilha);

    return valores.pop();
  }

  /**
   * 
   * @param caractereAtual
   * @return true se o caractere atual for um caractere de abertura de parênteses
   *         ou colchetes ou chaves e false caso contrário.
   */
  private static boolean estaAbrindoParenteses(char caractereAtual) {
    return caractereAtual == '(' || caractereAtual == '[' || caractereAtual == '{';
  }

  /**
   * 
   * @param aberto
   * @param fechado
   * @return true se os caracteres passados como parâmetro forem correspondentes e
   *         false caso contrário.
   */
  private static boolean parentesesCorrespondentes(char aberto, char fechado) {
    return (aberto == '(' && fechado == ')') || (aberto == '[' && fechado == ']')
        || (aberto == '{' && fechado == '}');
  }

  /**
   * 
   * @param fechado
   * @return o caractere de abertura correspondente ao caractere de fechamento
   *         passado como parâmetro ou um espaço em branco caso contrário
   *         (default).
   */
  private static char combinarParentesesDeAbertura(char fechado) {
    switch (fechado) {
      case ')':
        return '(';
      case ']':
        return '[';
      case '}':
        return '{';
      default:
        return ' ';
    }
  }
}