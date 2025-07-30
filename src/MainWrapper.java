import java.lang.reflect.Array;
import java.util.ArrayList;

import co.edu.udea.talentotech.programacion.intermedio.figuras.Punto;

public class MainWrapper {
    public static void main(String[] args) {
        Integer i = 10;
        int j = 20;
        int  k = Integer.parseInt("432");
        System.out.println("i: " + i + ", j: " + j + ", k: " + k);
        ArrayList<Punto> lista = new ArrayList<>();
        lista.add(new Punto(1, 2));
        lista.add(new Punto(1, 8));
        lista.add(new Punto(1, 4));
        lista.remove(2);
        System.out.println("Lista: " + lista);
    }
}
