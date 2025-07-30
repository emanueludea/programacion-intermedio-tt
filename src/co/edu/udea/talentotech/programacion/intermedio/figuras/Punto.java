package co.edu.udea.talentotech.programacion.intermedio.figuras;

public class Punto {
    // Atributos
    private double x;
    private double y;

    // Constructores
    public Punto() {
        this.x = 0;
        this.y = 0;
    }

    public Punto(double t) {
        this.x = t;
        this.y = 0;
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Setter y Getter
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Metodos
    public double calcularDistancia(Punto p2) {
        // raiz((x2-x1)^2+(y2-y1)²)
        double valor = Math.pow(this.x - p2.x, 2) + Math.pow(this.y - p2.y, 2);
        double distancia = Math.sqrt(valor);
        p2.setX(43);
        return distancia;
    }

    public double calcularDistancia(double x, double y) {
        // raiz((x2-x1)^2+(y2-y1)²)
        double valor = Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2);
        x = 1000;
        return Math.sqrt(valor);
    }

    public double carlcularPendiente(Punto p2) {
        // (y2-y1)/(x2-x1)
        return (p2.y - this.y) / (p2.x - this.x);
    }

    @Override
    public String toString() {
        return "[x: " + this.x + ", y: " + this.y + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    // @Override
    // public boolean equals(Object p2){
    //     if(this.x == p2.x && this.y == p2.y){
    //         return true;
    //     }
    //     return false;
    // }
    @Override
    public Punto clone() {
        Punto punto = new Punto();
        punto.setX(this.x);
        punto.setY(this.y);
        return punto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punto other = (Punto) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }
    
}
