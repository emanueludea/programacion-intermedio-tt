package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
// import java.util.Set;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    // @NotNull
    @Column(name = "cedula", nullable = false)
    private Integer cedula;

    // @NotBlank
    // @Size(max = 30)
    @Column(name = "primer_nombre", nullable = false, length = 30)
    private String primerNombre;

    // @Size(max = 30)
    @Column(name = "segundo_nombre", length = 30)
    private String segundoNombre;

    // @NotBlank
    // @Size(max = 30)
    @Column(name = "primer_apellido", nullable = false, length = 30)
    private String primerApellido;

    // @Size(max = 30)
    @Column(name = "segundo_apellido", length = 30)
    private String segundoApellido;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "semestre")
    private Short semestre;

    // @ManyToMany(mappedBy = "alumnos", fetch = FetchType.LAZY)
    // private Set<Profesor> profesores;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(
    //     name = "alumno_materia",
    //     joinColumns = @JoinColumn(name = "cc_alumno"),
    //     inverseJoinColumns = @JoinColumn(name = "codigo_materia")
    // )
    // private Set<Materia> materias;

    // Constructors
    public Alumno() {}

    public Alumno(Integer cedula, String primerNombre, String primerApellido) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
    }

    // Getters and Setters
    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Short getSemestre() {
        return semestre;
    }

    public void setSemestre(Short semestre) {
        this.semestre = semestre;
    }

    // public Set<Profesor> getProfesores() {
    //     return profesores;
    // }

    // public void setProfesores(Set<Profesor> profesores) {
    //     this.profesores = profesores;
    // }

    // public Set<Materia> getMaterias() {
    //     return materias;
    // }

    // public void setMaterias(Set<Materia> materias) {
    //     this.materias = materias;
    // }

    @Override
    public String toString() {
        return "Alumno{" +
                "cedula=" + cedula +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", edad=" + edad +
                ", semestre=" + semestre +
                '}';
    }
}
