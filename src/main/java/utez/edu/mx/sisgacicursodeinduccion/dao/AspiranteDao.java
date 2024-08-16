package utez.edu.mx.sisgacicursodeinduccion.dao;

import utez.edu.mx.sisgacicursodeinduccion.model.Aspirante;
import utez.edu.mx.sisgacicursodeinduccion.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AspiranteDao {

    //Programar una función R (lectura) para obtener un Aspirante
    //con el fin de hacer el inicio de sesión

    public Aspirante getOneA(int id_aspirante) {
        Aspirante a = new Aspirante();
        String query = "SELECT * FROM aspirantes WHERE id_aspirante = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_aspirante);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a.setId_aspirante(rs.getInt("id_aspirante"));
                    a.setNombre(rs.getString("nombre"));
                    a.setApellidos(rs.getString("apellidos"));
                    a.setCorreo(rs.getString("correo"));
                    a.setTelefono(rs.getLong("telefono"));
                    a.setCurp(rs.getString("curp"));
                    a.setCalificacion(rs.getInt("calificacion"));
                }
                con.close();
                ps.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    //Seria la R del CRUD
    public ArrayList<Aspirante> getAllA() {
        ArrayList<Aspirante> lista = new ArrayList<>();
        String query = "SELECT * FROM aspirantes";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aspirante a = new Aspirante();
                a.setId_aspirante(rs.getInt("id_aspirante"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidos(rs.getString("apellidos"));
                a.setCorreo(rs.getString("correo"));
                a.setTelefono(rs.getLong("telefono"));
                a.setCurp(rs.getString("curp"));
                a.setCalificacion(rs.getInt("calificacion"));
                lista.add(a);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    //insertar aspirante
    public boolean insertA(Aspirante a) {
        String query = "INSERT INTO aspirantes (nombre, apellidos, correo, telefono, curp, calificacion) VALUES (?, ?, ?, ?, ?, 0)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getCorreo());
            ps.setLong(4, a.getTelefono());
            ps.setString(5, a.getCurp());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateA(Aspirante a) {
        String query = "UPDATE aspirantes SET nombre = ?, apellidos = ?, correo = ?, telefono = ?, curp = ? WHERE id_aspirante = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getCorreo());
            ps.setLong(4, a.getTelefono());
            ps.setString(5, a.getCurp());
            ps.setInt(6, a.getId_aspirante());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAs(Aspirante a) {
        String query = "UPDATE aspirantes SET calificacion = ? WHERE id_aspirante = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, a.getCalificacion());
            ps.setInt(2, a.getId_aspirante());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar si un correo existe en la tabla 'aspirantes'
    public boolean existsByCorreo(String correo) {
        String query = "SELECT COUNT(*) FROM aspirantes WHERE correo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, correo);
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

    // Verificar si un teléfono ya existe en la tabla 'aspirantes'
    public boolean existsByTelefono(long telefono) {
        String query = "SELECT COUNT(*) FROM aspirantes WHERE telefono = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, telefono);
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

    // Verificar si un CURP ya existe en la tabla 'aspirantes'
    public boolean existsByCurp(String curp) {
        String query = "SELECT COUNT(*) FROM aspirantes WHERE curp = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, curp);
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

    // Obtener un aspirante por teléfono
    public Aspirante getOneByTelefono(long telefono) {
        Aspirante a = null;
        String query = "SELECT * FROM aspirantes WHERE telefono = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, telefono);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new Aspirante();
                    a.setId_aspirante(rs.getInt("id_aspirante"));
                    a.setNombre(rs.getString("nombre"));
                    a.setApellidos(rs.getString("apellidos"));
                    a.setCorreo(rs.getString("correo"));
                    a.setTelefono(rs.getLong("telefono"));
                    a.setCurp(rs.getString("curp"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    // Obtener un Aspirante por CURP
    public Aspirante getOneByCurp(String curp) {
        Aspirante a = null;
        String query = "SELECT * FROM aspirantes WHERE curp = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, curp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new Aspirante();
                    a.setId_aspirante(rs.getInt("id_aspirante"));
                    a.setNombre(rs.getString("nombre"));
                    a.setApellidos(rs.getString("apellidos"));
                    a.setCorreo(rs.getString("correo"));
                    a.setTelefono(rs.getLong("telefono"));
                    a.setCurp(rs.getString("curp"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

}
