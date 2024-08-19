package utez.edu.mx.sisgacicursodeinduccion.dao;

import utez.edu.mx.sisgacicursodeinduccion.model.Grupo;
import utez.edu.mx.sisgacicursodeinduccion.model.GrupoAspirante;
import utez.edu.mx.sisgacicursodeinduccion.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GrupoAspiranteDao {

    // Obtener un GrupoAspirante por id_grupo
    public GrupoAspirante getOneGA(int id_grupo) {
        GrupoAspirante g = null;
        String query = "SELECT * FROM grupo_aspirante WHERE id_grupo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_grupo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    g = new GrupoAspirante();
                    g.setId_grupo(rs.getInt("id_grupo"));
                    g.setNombreA(rs.getString("nombreA"));
                    g.setApellidosA(rs.getString("apellidosA"));
                    g.setCorreoA(rs.getString("correoA"));
                }
                con.close();
                ps.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }

    // Obtener todos los GrupoAspirante para un id_grupo específico
    public ArrayList<GrupoAspirante> getAspirantesPorGrupo(int id_grupo) {
        ArrayList<GrupoAspirante> lista = new ArrayList<>();
        String query = "SELECT GA.id_grupo, GA.nombreA, GA.apellidosA, GA.correoA, G.letra FROM GRUPO_ASPIRANTE GA JOIN GRUPOS G ON GA.id_grupo = G.id_grupo WHERE G.id_grupo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_grupo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GrupoAspirante g = new GrupoAspirante();
                    g.setId_grupo(rs.getInt("id_grupo"));
                    g.setNombreA(rs.getString("nombreA"));
                    g.setApellidosA(rs.getString("apellidosA"));
                    g.setCorreoA(rs.getString("correoA"));
                    lista.add(g);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Grupo> getAll() {
        ArrayList<Grupo> lista = new ArrayList<>();
        String query = "select * from grupos";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grupo l = new Grupo();
                //Entonces llenamos la información del usuario
                l.setId_grupo(rs.getInt("id_grupo"));
                l.setLetra(rs.getString("letra"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setCantidad(rs.getInt("cantidad"));
                lista.add(l);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}