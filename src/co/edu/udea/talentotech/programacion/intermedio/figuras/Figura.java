package co.edu.udea.talentotech.programacion.intermedio.figuras;

public abstract class Figura {
    private double area;
    private double perimetro;
    private String color ;

    public abstract double getArea() ;
    public abstract double getPerimetro();
    public String getColor() {
        return color;
    }
    

    /**
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    
}
