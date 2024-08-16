package utez.edu.mx.sisgacicursodeinduccion.dao;

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
                    g.setLetra(rs.getString("letra"));
                    g.setId_aspirante(rs.getInt("id_aspirante"));
                    g.setNombreA(rs.getString("nombreA"));
                    g.setApellidosA(rs.getString("apellidosA"));
                    g.setCorreoA(rs.getString("correoA"));
                    g.setId_usuario(rs.getInt("id_usuario"));
                    g.setNombreU(rs.getString("nombreU"));
                    g.setApellidosU(rs.getString("apellidosU"));
                    g.setCorreoU(rs.getString("correoU"));
                    g.setId_materia(rs.getInt("id_materia"));
                    g.setNombreM(rs.getString("nombreM"));
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
        String query = "SELECT * FROM grupo_aspirante WHERE id_grupo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_grupo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GrupoAspirante g = new GrupoAspirante();
                    g.setId_grupo(rs.getInt("id_grupo"));
                    g.setLetra(rs.getString("letra"));
                    g.setId_aspirante(rs.getInt("id_aspirante"));
                    g.setNombreA(rs.getString("nombreA"));
                    g.setApellidosA(rs.getString("apellidosA"));
                    g.setCorreoA(rs.getString("correoA"));
                    g.setId_usuario(rs.getInt("id_usuario"));
                    g.setNombreU(rs.getString("nombreU"));
                    g.setApellidosU(rs.getString("apellidosU"));
                    g.setCorreoU(rs.getString("correoU"));
                    g.setId_materia(rs.getInt("id_materia"));
                    g.setNombreM(rs.getString("nombreM"));

                    lista.add(g);
                }
                con.close();
                ps.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
