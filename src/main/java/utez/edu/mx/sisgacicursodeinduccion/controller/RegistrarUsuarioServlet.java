package utez.edu.mx.sisgacicursodeinduccion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utez.edu.mx.sisgacicursodeinduccion.dao.UsuarioDao;
import utez.edu.mx.sisgacicursodeinduccion.model.Usuario;

import java.io.IOException;

@WebServlet(name = "RegistrarUsuarioServlet", value = "/sign_in")
public class RegistrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_usuario;
        String operacion = req.getParameter("operacion");

        try {
            id_usuario = Integer.parseInt(req.getParameter("id_usuario"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("error.jsp");
            return;
        }

        UsuarioDao dao = new UsuarioDao();
        Usuario u = dao.getOne(id_usuario);

        if (u == null) {
            resp.sendRedirect("error.jsp"); // Redirige a una página de error si el aspirante no se encuentra
            return;
        }

        HttpSession sesion = req.getSession();
        sesion.setAttribute("usuario", u);
        sesion.setAttribute("operacion", operacion);

        resp.sendRedirect("registrarUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();
        String operacion = req.getParameter("operacion");


        try {
            switch (operacion) {
                case "registrar": {
                    String nombre = req.getParameter("nombre");
                    String apellidos = req.getParameter("apellidos");
                    String correo = req.getParameter("correo");
                    String contra1 = req.getParameter("contra1");
                    String contra2 = req.getParameter("contra2");
                    String telefonoStr = req.getParameter("telefono");
                    String curp = req.getParameter("curp");

                    // Validar campos requeridos
                    if (nombre == null || nombre.isEmpty() ||
                            apellidos == null || apellidos.isEmpty() ||
                            correo == null || correo.isEmpty() ||
                            contra1 == null || contra1.isEmpty() ||
                            contra2 == null || contra2.isEmpty() ||
                            telefonoStr == null || telefonoStr.isEmpty() ||
                            curp == null || curp.isEmpty()) {
                        sesion.setAttribute("mensaje", "Todos los campos deben ser llenados");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    // Validar contraseñas
                    if (!contra1.equals(contra2)) {
                        sesion.setAttribute("mensaje", "Las contraseñas no coinciden");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    // Validar formato de teléfono
                    long telefono;
                    try {
                        telefono = Long.parseLong(telefonoStr);
                    } catch (NumberFormatException e) {
                        sesion.setAttribute("mensaje", "Teléfono inválido. Debe ser un número.");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    UsuarioDao dao = new UsuarioDao();

                    // Verificar si el correo, teléfono o CURP ya existen
                    if (dao.existsByCorreo(correo)) {
                        sesion.setAttribute("mensaje", "El correo ya está registrado");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    if (dao.existsByTelefono(telefono)) {
                        sesion.setAttribute("mensaje", "El teléfono ya está registrado");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    if (dao.existsByCurp(curp)) {
                        sesion.setAttribute("mensaje", "El CURP ya está registrado");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    Usuario u = new Usuario();
                    u.setNombre(nombre);
                    u.setApellidos(apellidos);
                    u.setCorreo(correo);
                    u.setContra(contra1);
                    u.setTelefono(telefono);
                    u.setCurp(curp);

                    boolean insertado = dao.insert(u);

                    if (insertado) {
                        sesion.setAttribute("mensaje", "Registro exitoso");
                        resp.sendRedirect("gestionUsuario.jsp");
                    } else {
                        sesion.setAttribute("mensaje", "El usuario no se registró correctamente");
                        resp.sendRedirect("registrarUsuario.jsp");
                    }
                    break;
                }
                case "actualizar": {
                    String nombre = req.getParameter("nombre");
                    String apellidos = req.getParameter("apellidos");
                    String correo = req.getParameter("correo");
                    String telefonoStr = req.getParameter("telefono");
                    String curp = req.getParameter("curp");
                    String id_usuarioStr = req.getParameter("id_usuario");

                    // Validar campos requeridos
                    if (nombre == null || nombre.isEmpty() ||
                            apellidos == null || apellidos.isEmpty() ||
                            correo == null || correo.isEmpty() ||
                            telefonoStr == null || telefonoStr.isEmpty() ||
                            curp == null || curp.isEmpty() ||
                            id_usuarioStr == null || id_usuarioStr.isEmpty()) {
                        sesion.setAttribute("mensaje", "Todos los campos deben ser llenados");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    // Validar formato de teléfono
                    long telefono;
                    try {
                        telefono = Long.parseLong(telefonoStr);
                    } catch (NumberFormatException e) {
                        sesion.setAttribute("mensaje", "Teléfono inválido. Debe ser un número.");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    int id_usuario;
                    try {
                        id_usuario = Integer.parseInt(id_usuarioStr);
                    } catch (NumberFormatException e) {
                        sesion.setAttribute("mensaje", "ID de usuario inválido.");
                        resp.sendRedirect("registrarUsuario.jsp");
                        return;
                    }

                    Usuario u = new Usuario();
                    u.setNombre(nombre);
                    u.setApellidos(apellidos);
                    u.setCorreo(correo);
                    u.setTelefono(telefono);
                    u.setCurp(curp);
                    u.setId_usuario(id_usuario);

                    UsuarioDao dao = new UsuarioDao();

                    // Actualizar en la base de datos
                    boolean actualizado = dao.update(u);

                    if (actualizado) {
                        sesion.setAttribute("mensaje", "Usuario actualizado correctamente");
                        resp.sendRedirect("gestionUsuario.jsp");
                    } else {
                        sesion.setAttribute("mensaje", "No se pudo actualizar el usuario");
                        resp.sendRedirect("registrarUsuario.jsp");
                    }
                    break;
                }
                default:
                    resp.sendRedirect("error.jsp");
                    break;
            }
        } catch (Exception e) {
            sesion.setAttribute("mensaje", "Ocurrió un error durante el proceso.");
            resp.sendRedirect("registrarUsuario.jsp");
        }
    }
}
