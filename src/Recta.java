import co.edu.udea.talentotech.programacion.intermedio.figuras.Punto;

public class Recta {
    // Atributos
    private double pendiente;
    private double intercepto;

    //Constructores
    public  Recta(Punto p1, Punto p2){
        this.pendiente = p1.carlcularPendiente(p2);
        this.intercepto = p1.getY() - this.pendiente * p1.getX();
    }
    public Recta(double pendiente, double intercepto){
        this.pendiente = pendiente;
        this.intercepto = intercepto;
    }
    // Setters y Getters
    public double getPendiente() {
        return pendiente;
    }
    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }
    public double getIntercepto() {
        return intercepto;
    }
    public void setIntercepto(double intercepto) {
        this.intercepto = intercepto;
    }
    
}
