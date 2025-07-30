import co.edu.udea.talentotech.programacion.intermedio.figuras.Cuadrado;
import co.edu.udea.talentotech.programacion.intermedio.figuras.Figura;

public class MainFiguras {

    private void imprimirArea(Figura f){
        System.out.println(f.getArea());
    }
    private void imprimirPerimetro(Figura f){
        System.out.println(f.getPerimetro());
    }

    public static void main(String[] args ){
        Cuadrado cuadrado = new Cuadrado(5);
        System.out.println(cuadrado.getArea() + " " + cuadrado.getPerimetro() );
        MainFiguras mf = new MainFiguras();
        mf.imprimirArea(cuadrado);
        mf.imprimirPerimetro(cuadrado);
    }    
}
