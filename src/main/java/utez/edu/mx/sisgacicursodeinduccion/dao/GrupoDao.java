package utez.edu.mx.sisgacicursodeinduccion.dao;

import utez.edu.mx.sisgacicursodeinduccion.model.Grupo;
import utez.edu.mx.sisgacicursodeinduccion.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GrupoDao {

    public Grupo getOneG(int id_grupo) {
        Grupo g = new Grupo();
        String query = "select * from grupos where id_grupo = ?";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_grupo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                g.setId_grupo(rs.getInt("id_grupo"));
                g.setLetra(rs.getString("letra"));
                g.setNombre(rs.getString("nombre"));
                g.setCorreo(rs.getString("correo")); // Nuevo campo
                g.setCantidad(rs.getInt("cantidad"));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }

    // Obtener todos los grupos
    public ArrayList<Grupo> getAllG() {
        ArrayList<Grupo> lista = new ArrayList<>();
        String query = "SELECT * FROM grupos";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Grupo g = new Grupo();
                g.setId_grupo(rs.getInt("id_grupo"));
                g.setLetra(rs.getString("letra"));
                g.setNombre(rs.getString("nombre"));
                g.setCorreo(rs.getString("correo"));
                g.setCantidad(rs.getInt("cantidad"));
                lista.add(g);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Guardar un nuevo grupo en la base de datos
    public boolean insertG(Grupo grupo) {
        String query = "INSERT INTO grupos (letra, cantidad) VALUES (?, 0)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, grupo.getLetra());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar la información de un grupo
    public boolean updateG(Grupo g) {
        String query = "update grupos set nombre = ?, correo = ? where id_grupo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getCorreo());
            ps.setInt(3, g.getId_grupo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtener un grupo por su ID (este método ya lo tienes)
    public Grupo getById(int idGrupo) {
        Grupo g = null;
        String query = "SELECT * FROM grupos WHERE id_grupo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idGrupo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    g = new Grupo();
                    g.setId_grupo(rs.getInt("id_grupo"));
                    g.setLetra(rs.getString("letra"));
                    g.setNombre(rs.getString("nombre"));
                    g.setCorreo(rs.getString("correo"));
                    g.setCantidad(rs.getInt("cantidad"));
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

    public boolean existsByLetra(String letra) {
        String query = "SELECT COUNT(*) FROM grupos WHERE letra = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, letra);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                con.close();
                ps.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
