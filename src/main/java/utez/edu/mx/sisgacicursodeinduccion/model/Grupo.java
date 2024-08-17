package utez.edu.mx.sisgacicursodeinduccion.model;

import java.io.Serializable;

public class Grupo implements Serializable {

    private int id_grupo;
    private String letra;
    private String nombre;
    private String correo;
    private int cantidad;

    public Grupo() {
    }

    public Grupo(int id_grupo, String letra, String nombre, String correo, int cantidad) {
        this.id_grupo = id_grupo;
        this.letra = letra;
        this.nombre = nombre;
        this.correo = correo;
        this.cantidad = cantidad;
    }

    public int getId_grupo() { return id_grupo; }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) { this.correo = correo; }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}