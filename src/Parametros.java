import co.edu.udea.talentotech.programacion.intermedio.figuras.Punto;

public class Parametros {
    private void metodo1(Punto a, float b){
        System.out.println("Metodo 1: " + a + ", " + b);
        a.setX(a.getX() * 20);
        b = b + 10.5f;
        System.out.println("Metodo 1 despues: " + a + ", " + b);
    }
    public static void main(String[] args ){
        Parametros p = new Parametros();
        Punto a = new Punto(10, 20);
        float b = 5.5f;
        System.out.println("Antes del metodo: " + a + ", " + b);
        p.metodo1(a.clone(), b);
        System.out.println("Despues del metodo: " + a + ", " + b);
    }
}
