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

    // Actualizar la información de un grupo
    public boolean update(Grupo g) {
        String query = "UPDATE grupos SET correo = ? WHERE id_grupo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, g.getCorreo());
            ps.setInt(2, g.getId_grupo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
    public boolean save(Grupo grupo) {
        String query = "INSERT INTO grupos (letra, correo, cantidad) VALUES (?, ?, 25)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, grupo.getLetra());
            ps.setString(2, grupo.getCorreo());
            return ps.executeUpdate() == 1;
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
