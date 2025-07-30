import java.util.Scanner;

public class Entradas {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int feet;
        int inches;
        String name;
        System.out.println("Enter feet, inches, and name:");
        feet = console.nextInt();
        inches = console.nextInt();
        console.nextLine(); // consume the newline character
        name = console.nextLine();
        System.out.println("Feet: " + feet);
        System.out.println("Inches: " + inches);
        System.out.println("Name: " + name);
        console.close();
    }
}