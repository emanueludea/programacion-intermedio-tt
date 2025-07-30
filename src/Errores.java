import java.util.InputMismatchException;
import java.util.Scanner;

public class Errores {
    int suma() throws Exception {
        Scanner scan = new Scanner(System.in);
        int a, b;
        System.out.print("Ingrese un numero: ");
        a = scan.nextInt();
        scan.nextLine();
        System.out.println();
        System.out.print("Ingrese otro numero: ");
        b = scan.nextInt();
        scan.close();
        return a + b;
    }

    public static void main(String[] arg) {
        try {
            Errores e = new Errores();
            int resultado = e.suma();
            System.out.println("El resultado es: " + resultado);
        } catch (InputMismatchException e) {
            System.out.println("Eso no es un numero");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("hubo un error");
        }
    }
}
