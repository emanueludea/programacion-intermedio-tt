import co.edu.udea.talentotech.programacion.intermedio.figuras.Punto;

public class MainPuntos {
    public static void main(String[] args){
        // double maxDistancia = 0;
        // double minDistancia = Double.MAX_VALUE;
        Punto p1, p2, p3;
        p1 = new Punto(3,4);
        p3 = new Punto(3,4);
        p2 = new Punto();
        // p2.setX(3);
        // p2.setY(4);  
        // double x = 5;
        // double y = 6;
        double distancia = p1.calcularDistancia(p2);
        System.out.println("Distancia: " + distancia);
        System.out.println(p2);
        if(p1.equals(p3)){
            System.out.println("los puntos son iguales");
        }
        // System.out.println("Distancia con coordenadas: " + p1.calcularDistancia(x, y));
        // System.out.println(x);
    }
}
