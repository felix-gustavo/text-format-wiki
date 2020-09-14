import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Terminal {

  private static String getSeparator(){
    return "\n===========================================\n";
  }

  private static String getTitleStyled(String title){
    return getSeparator() + "\t\t"+title+"\t\t" + getSeparator();
  }

  private static String removeParentheses(String text) {
    return text.replaceAll("\\(.*\\)", "");
  }

  private static String removeBrackets(String text) {
    return text.replaceAll("\\[.?\\]", "");
  }

  public static void run() {
    try{
      int option = -1;

      Scanner scanner = new Scanner(System.in);
      while (option != 0) {
        System.out.println(getTitleStyled("Programa"));
        System.out.println("1 - Remover parênteses e seu conteúdo");
        System.out.println("2 - Remover colchetes e seu conteúdo");
        System.out.println("0 - Sair");
        System.out.println("Escolha uma opção: ");

        if(scanner.hasNextInt()) option = scanner.nextInt();
        else scanner.next();

        Scanner sc = new Scanner(new File("src\\files\\input.txt"));
        sc.useDelimiter("###");
        String text = sc.next();
        sc.close();
        String textFormated = text;

        switch(option) {
          case 0:
            PrintWriter pw = new PrintWriter(new FileWriter(new File("src\\files\\output.txt")));
            pw.println(textFormated);
            pw.close();
            break;
          case 1:
            textFormated = removeParentheses(textFormated != null ? textFormated : text);
            System.out.println(textFormated);
            break;
          case 2:
            textFormated = removeBrackets(textFormated != null ? textFormated : text);
            System.out.println(textFormated);
            break;
          default:
            System.out.println("Opção inválida, tente novamente");
        }
      }
      scanner.close();
    } catch (FileNotFoundException fnfe) {
      System.out.println("Arquivo não encontrado");
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Erro ao abrir o arquivo");
      ioe.printStackTrace();
    }
  }
}