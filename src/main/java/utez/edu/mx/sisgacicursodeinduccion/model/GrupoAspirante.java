package utez.edu.mx.sisgacicursodeinduccion.model;

import java.io.Serializable;

public class GrupoAspirante implements Serializable {
    //Debe de representar exactamente la estructura de la
    //tabla de la base de datos

    private int id_grupo;

    private String nombreA;
    private String apellidosA;
    private String correoA;


    public GrupoAspirante() {
    }

    public GrupoAspirante(int id_grupo, String nombreA, String apellidosA, String correoA) {
        this.id_grupo = id_grupo;
        this.nombreA = nombreA;
        this.apellidosA = apellidosA;
        this.correoA = correoA;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNombreA() {
        return nombreA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public String getApellidosA() {
        return apellidosA;
    }

    public void setApellidosA(String apellidosA) {
        this.apellidosA = apellidosA;
    }

    public String getCorreoA() {
        return correoA;
    }

    public void setCorreoA(String correoA) {
        this.correoA = correoA;
    }
}