package uaem.isc.dto;

/**
 * @author Ricardo Sanchez Paz
 */
public class Alumno {
    
 private long id;
 private String nombre;
 private String apellidos;
 private String semestre;

    public Alumno(long id, String nombre, String apellidos, String semestre) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.semestre = semestre;
    }

    public Alumno(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
