package utez.edu.mx.sisgacicursodeinduccion.model;

import java.io.Serializable;

public class GrupoAspirante implements Serializable {
    //Debe de representar exactamente la estructura de la
    //tabla de la base de datos

    private int id_grupo;
    private String letra;

    private int id_aspirante;
    private String nombreA;
    private String apellidosA;
    private String correoA;

    private int id_usuario;
    private String nombreU;
    private String apellidosU;
    private String correoU;

    private int id_materia;
    private String nombreM;

    public GrupoAspirante() {
    }

    public GrupoAspirante(int id_grupo, String letra, int id_aspirante, String nombreA, String apellidosA, String correoA, int id_usuario, String nombreU, String apellidosU, String correoU, int id_materia, String nombreM) {
    this.id_grupo = id_grupo;
    this.letra = letra;
    this.id_aspirante = id_aspirante;
    this.nombreA = nombreA;
    this.apellidosA = apellidosA;
    this.correoA = correoA;
    this.id_usuario = id_usuario;
    this.nombreU = nombreU;
    this.apellidosU = apellidosU;
    this.correoU = correoU;
    this.id_materia = id_materia;
    this.nombreM = nombreM;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getId_aspirante() {
        return id_aspirante;
    }

    public void setId_aspirante(int id_aspirante) {
        this.id_aspirante = id_aspirante;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public String getApellidosU() {
        return apellidosU;
    }

    public void setApellidosU(String apellidosU) {
        this.apellidosU = apellidosU;
    }

    public String getCorreoU() {
        return correoU;
    }

    public void setCorreoU(String correoU) {
        this.correoU = correoU;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }
}