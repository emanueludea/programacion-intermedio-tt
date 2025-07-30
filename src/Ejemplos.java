public class Ejemplos {
    public static void main(String[] args){
        String nombres[] = {"Carlos", "Maria", "Pedro"};
        for (String nombre : nombres) {
            if(nombre.equals("Maria")) {
                // System.out.println("llegamos al final");
                continue;
            }
            System.out.println(nombre);
        }
        // System.out.println("1. \"Opcion\"1\n \t 1.\\ opcion2");
        // String palabra = "10";
        // System.out.println(palabra + 20);
        // double num1 = 2f;
        // int num;
        // System.out.println(num1/0);
        // num = num1 >= 10 ? 5 : 20; // operador ternario
        /*if(num1>=10){
            num = 5;
        }else{
            num = 20;
        }*/
        // System.out.println(num);
    }
}
