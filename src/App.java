import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

  public static void main(String[] args) {
    BufferedReader reader;
    Path path1 = Paths.get("expressoes3.txt");
    try {
      reader = Files.newBufferedReader(path1, Charset.defaultCharset());
      String line = null;
      while ((line = reader.readLine()) != null) {
        System.out.println("Expressão: " + line);
        try {
          double resultado = Calculadora.calcularExpressao(line);
          System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
          System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("\n");
      }
      reader.close();
    } catch (IOException e) {
      System.err.format("Erro na leitura do arquivo: ", e);
    }
  }
}
