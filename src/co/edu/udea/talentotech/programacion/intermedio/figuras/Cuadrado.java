package co.edu.udea.talentotech.programacion.intermedio.figuras;

public class Cuadrado extends Rectangulo {

    public Cuadrado(double lado) {
        this.alto = lado;
        this.lado = lado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(lado);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Cuadrado [lado=" + lado + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cuadrado other = (Cuadrado) obj;
        if (Double.doubleToLongBits(lado) != Double.doubleToLongBits(other.lado))
            return false;
        return true;
    }

    @Override
    public double getArea() {
        return this.lado * this.lado;
    }

    @Override
    public double getPerimetro() {
        return 4 * this.lado;
    }

}
