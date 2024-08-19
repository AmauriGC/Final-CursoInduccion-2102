package utez.edu.mx.sisgacicursodeinduccion.dao;

import utez.edu.mx.sisgacicursodeinduccion.model.Usuario;
import utez.edu.mx.sisgacicursodeinduccion.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Estas clases DAO permiten el uso de funciones CRUD
public class UsuarioDao {

    //Programar una función R (lectura) para obtener un usuario
    //con el fin de hacer el inicio de sesión

    public Usuario getOne(String correo, String contra) {
        Usuario u = new Usuario();
        String query = "select * from usuarios where correo = ? and contra = sha2(?,256)";
        try {
            //1) conectarnos a la BD
            Connection con = DatabaseConnectionManager.getConnection();
            //2) Configurar el query y ejecutarlo
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, correo);
            ps.setString(2, contra);
            ResultSet rs = ps.executeQuery();
            //3) Obtener la información
            if (rs.next()) {
                //Entonces llenamos la información del usuario
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setCorreo(rs.getString("correo"));
                u.setContra(rs.getString("contra"));
                u.setTelefono(rs.getLong("telefono"));
                u.setCurp(rs.getString("curp"));
                u.setId_rol(rs.getInt("id_rol"));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public Usuario getOneByCorreo(String correo) {
        Usuario u = null;
        String query = "select * from usuarios where correo = ?";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setCorreo(rs.getString("correo"));
                u.setContra(rs.getString("contra")); // Contraseña cifrada
                u.setTelefono(rs.getLong("telefono"));
                u.setCurp(rs.getString("curp"));
                u.setId_rol(rs.getInt("id_rol"));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean checkPassword(String correo, String contra) {
        boolean isValid = false;
        String query = "select * from usuarios where correo = ? and contra = sha2(?,256)";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, correo);
            ps.setString(2, contra);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    //insertar usuarios
    public boolean insert(Usuario u) {
        boolean flag = false;
        String query = "insert into usuarios (nombre,apellidos,correo,contra,telefono,curp,id_rol) values(?,?,?,sha2(?,256),?,?,2) ";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContra());
            ps.setLong(5, u.getTelefono());
            ps.setString(6, u.getCurp());
            if (ps.executeUpdate() == 1) {
                flag = true;//Porque significa que si se inserto en la BD
            }
            con.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //Seria la R del CRUD
    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String query = "select * from usuarios";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                //Entonces llenamos la información del usuario
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setCorreo(rs.getString("correo"));
                u.setContra(rs.getString("contra"));
                u.setTelefono(rs.getLong("telefono"));
                u.setCurp(rs.getString("curp"));
                u.setId_rol(rs.getInt("id_rol"));
                lista.add(u);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Usuario getOne(int id_usuario) {
        Usuario u = new Usuario();
        String query = "select * from usuarios where id_usuario = ? ";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_usuario);
            // Ejecutamos la consulta
            if (ps.execute()) {
                // Obtenemos el resultado
                var rs = ps.getResultSet();
                if (rs.next()) {
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellidos(rs.getString("apellidos"));
                    u.setCorreo(rs.getString("correo"));
                    u.setContra(rs.getString("contra"));
                    u.setTelefono(rs.getLong("telefono"));
                    u.setCurp(rs.getString("curp"));
                    u.setId_rol(rs.getInt("id_rol"));
                }
                rs.close();
            }
            con.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean update(Usuario u) {
        boolean flag = false;
        String query = "update usuarios set nombre = ?, apellidos = ?, correo = ?, telefono = ?, curp = ? where id_usuario = ? ";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getCorreo());
            ps.setLong(4, u.getTelefono());
            ps.setString(5, u.getCurp());
            ps.setInt(6, u.getId_usuario());
            // Ejecutamos la actualización
            if (ps.executeUpdate() > 0) {
                flag = true; // La actualización fue exitosa
            }
            con.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Usuario getByEmail(String correo) {
        Usuario u = null;
        String query = "select * from usuarios where correo = ?";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setCodigoRecuperacion(rs.getString("codigo"));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean actualizacionCodigoRecuperacion(Usuario u) {
        boolean flag = false;
        String query = "update usuarios set codigo = ? where id_usuario = ?";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getCodigoRecuperacion());
            ps.setInt(2, u.getId_usuario());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
            con.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Usuario getCodigoRecuperacion(String codigo) {
        Usuario u = null;
        String query = "select * from usuarios where codigo = ?";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setCodigoRecuperacion(rs.getString("codigo"));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean actContraCodigo(Usuario u) {
        boolean flag = false;
        String query = "update usuarios set contra = sha2(?,256), codigo = null where id_usuario = ?";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getContra());
            ps.setInt(2, u.getId_usuario());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
            con.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // Verificar si un correo existe en la tabla 'usuarios'
    public boolean existsByCorreo(String correo) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Verificar si un teléfono ya existe en la tabla 'usuarios'
    public boolean existsByTelefono(long telefono) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE telefono = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, telefono);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Verificar si un CURP ya existe en la tabla 'usuarios'
    public boolean existsByCurp(String curp) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE curp = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, curp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtener un usuario por teléfono
    public Usuario getOneByTelefono(long telefono) {
        Usuario u = null;
        String query = "SELECT * FROM usuarios WHERE telefono = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, telefono);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellidos(rs.getString("apellidos"));
                    u.setCorreo(rs.getString("correo"));
                    u.setTelefono(rs.getLong("telefono"));
                    u.setCurp(rs.getString("curp"));
                    u.setContra(rs.getString("contra"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    // Obtener un usuario por CURP
    public Usuario getOneByCurp(String curp) {
        Usuario u = null;
        String query = "SELECT * FROM usuarios WHERE curp = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, curp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellidos(rs.getString("apellidos"));
                    u.setCorreo(rs.getString("correo"));
                    u.setTelefono(rs.getLong("telefono"));
                    u.setCurp(rs.getString("curp"));
                    u.setContra(rs.getString("contra"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public Usuario getByCorreo(String correo) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    // Otros campos según tu modelo
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

}