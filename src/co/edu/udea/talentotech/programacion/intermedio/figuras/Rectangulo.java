package co.edu.udea.talentotech.programacion.intermedio.figuras;

public class Rectangulo extends Figura {
    protected double lado;
    protected double alto;
    
    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    @Override
    public String toString() {
        return "Rectangulo [lado=" + lado + ", alto=" + alto + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(lado);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(alto);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rectangulo other = (Rectangulo) obj;
        if (Double.doubleToLongBits(lado) != Double.doubleToLongBits(other.lado))
            return false;
        if (Double.doubleToLongBits(alto) != Double.doubleToLongBits(other.alto))
            return false;
        return true;
    }

    @Override
    public double getArea() {
        return this.lado * this.alto;
    }

    @Override
    public double getPerimetro() {
        return this.lado * 2 + this.alto * 2;
    }
    
}
