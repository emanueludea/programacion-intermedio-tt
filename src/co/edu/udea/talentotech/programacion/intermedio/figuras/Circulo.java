package co.edu.udea.talentotech.programacion.intermedio.figuras;

public class Circulo extends Figura {
    public static double PI = 3.1416;
    private static int contadorInstancias = 0;
    private double radio;

    public static int contarInstancia() {
        return Circulo.contadorInstancias;
    }

    public Circulo() {
        Circulo.contadorInstancias++;
    }

    public Circulo(double radio) {
        this.radio = radio;
        Circulo.contadorInstancias++;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(radio);
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
        Circulo other = (Circulo) obj;
        if (Double.doubleToLongBits(radio) != Double.doubleToLongBits(other.radio))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Circulo [radio=" + radio + "]";
    }

    @Override
    public double getArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double getPerimetro() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerimetro'");
    }

}
