package utez.edu.mx.sisgacicursodeinduccion.model;

import java.io.Serializable;

public class Aspirante implements Serializable {
    //Debe de representar exactamente la estructura de la
    //tabla de la base de datos

    private int id_aspirante;
    private String nombre;
    private String apellidos;
    private String correo;
    private long telefono;
    private String curp;
    private int calificacion;

    public Aspirante() {
    }

    public Aspirante(int id_aspirante, String nombre, String apellidos, String correo, long telefono, String curp, int calificacion) {
        this.id_aspirante = id_aspirante;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.curp = curp;
        this.calificacion = calificacion;
    }

    public int getId_aspirante() {
        return id_aspirante;
    }

    public void setId_aspirante(int id_aspirante) {
        this.id_aspirante = id_aspirante;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getCalificacion() { return calificacion; }

    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }
}
