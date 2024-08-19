package utez.edu.mx.sisgacicursodeinduccion.dao;

import utez.edu.mx.sisgacicursodeinduccion.model.Grupo;
import utez.edu.mx.sisgacicursodeinduccion.model.GrupoAspirante;
import utez.edu.mx.sisgacicursodeinduccion.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsignarDao {

    public boolean insertGrupo_A(GrupoAspirante o) {
        String query = "INSERT INTO grupo_aspirante (id_grupo, nombreA, apellidosA, correoA) " + "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Establece los valores para la inserción basados en el objeto 'g'
            ps.setInt(1, o.getId_grupo());
            ps.setString(2, o.getNombreA());
            ps.setString(3, o.getApellidosA());
            ps.setString(4, o.getCorreoA());

            // Ejecuta la inserción
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true si se insertó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false si hubo una excepción
        }
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