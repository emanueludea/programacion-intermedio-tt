import co.edu.udea.talentotech.programacion.intermedio.figuras.Punto;
import co.edu.udea.talentotech.programacion.intermedio.interfaces.Interface1;
import co.edu.udea.talentotech.programacion.intermedio.interfaces.Interface2;

public class MainInterface1 implements Interface1, Interface2 {
    
    public static void main(String[] args) {
        MainInterface1 main = new MainInterface1();
        Punto punto = new Punto(3, 4);
        main.metodo1(punto, 5.0f);
        main.metodo2(punto, 10.0f);
        main.metodo3(punto, 15.0f);
        main.metodo4(punto, 5.0f);
        main.metodo5(punto, 10.0f);
        main.metodo6(punto, 15.0f);
    }

    @Override
    public void metodo1(Punto a, float b) {
        System.out.println("metodo1 implementaddo");
    }

    @Override
    public void metodo2(Punto a, float b) {
        System.out.println("metodo2 implementaddo");
    }

    @Override
    public void metodo3(Punto a, float b) {
        System.out.println("metodo3 implementaddo");
    }

    @Override
    public void metodo4(Punto a, float b) {
        System.out.println("metodo4 implementaddo");
    }

    @Override
    public void metodo5(Punto a, float b) {
        System.out.println("metodo5 implementaddo");
    }

    @Override
    public void metodo6(Punto a, float b) {
        System.out.println("metodo6 implementaddo");
    }
}
