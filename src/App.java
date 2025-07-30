public class App {
    public static void main(String[] args) throws Exception {
        int numeros2[] = new int[5];
        int numeros[] = {3,5,6,7,8,9};
        // int matriz[][][] = new int[2][3][4];
        int matriz[][][]= {{{1,2,3},{4,5,6,}},{{7,8,9},{10,11,12}}};
        for(int i = 0; i< matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                for( int k= 0; k <  matriz[0][0].length; k++){
                    System.out.print(matriz[i][j][k]);
                }
                System.out.println();
            }
        }

        // for(int i = 0; i < numeros.length; i++) {
        //     // numeros[i] = i + 1;
        //     System.out.println(numeros[i]);
        // }
        // String txt = "abcd";
        // String txt2 = "abcd";
        // if(txt.equals(txt2)){
        //     System.out.println("Son iguales");
        // }
        // if(txt == txt2){
        //     System.out.println("son iguales");
        // }
        // System.out.println("son: " + txt.length());
        // System.out.println(txt.toUpperCase());
        // System.out.println(txt.indexOf("ab"));
        // System.out.println(txt + " -> "+ txt2); // concatenacion
        // System.out.println(txt2.concat(txt)); // concatenacion
        // int num1 = 3;
        // int num2 = 6;
        // System.out.println(num1);
        // num1 += num2;// num1 = num1 + num2
        // num2 = num2 * 5; // num2 *= 5;
        // System.out.println(num1);
        // System.out.println(num2);
        // int i = 10;
        // for(int j = 10; j > 5; j++){
        // System.out.println(j);
        // }
        // System.out.println(i--);
        // System.out.println(i);
        // System.out.println(--i);
        // System.out.println(i);
        // System.out.println(i);
        // System.out.println(i++);
        // System.out.println(i);
        // System.out.println(++i);
    }
}
