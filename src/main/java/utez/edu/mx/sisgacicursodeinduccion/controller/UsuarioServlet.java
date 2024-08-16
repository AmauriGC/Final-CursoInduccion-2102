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

@WebServlet(name = "UsuarioServlet", value = "/login")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_usuario = Integer.parseInt(req.getParameter("id_usuario"));
        String operacion = req.getParameter("operacion");

        UsuarioDao dao = new UsuarioDao();
        Usuario u = dao.getOne(id_usuario);

        HttpSession sesion = req.getSession();
        sesion.setAttribute("usuario", u);
        sesion.setAttribute("operacion", operacion);

        resp.sendRedirect("registrarUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1) Obtener la información del formulario
        String correo = req.getParameter("correo");
        String contra = req.getParameter("contra");
        String ruta = "index.jsp";

        HttpSession sesion = req.getSession();

        // Validar que los campos no sean nulos o vacíos
        if (correo == null || correo.isEmpty() || contra == null || contra.isEmpty()) {
            sesion.setAttribute("mensaje", "Correo y contraseña no pueden estar vacíos.");
        } else {
            // Conectarse a la base de datos y buscar al usuario según las credenciales del formulario
            UsuarioDao dao = new UsuarioDao();
            Usuario u = dao.getOneByCorreo(correo);

            if (u == null) {
                // El usuario no existe en la base de datos
                sesion.setAttribute("mensaje", "El usuario no está registrado.");
            } else if (!dao.checkPassword(correo, contra)) {
                // Contraseña incorrecta
                sesion.setAttribute("mensaje", "La contraseña ingresada es incorrecta.");
            } else {
                // El usuario sí existe en la base de datos y las credenciales son correctas
                sesion.setAttribute("usuario", u);
                sesion.removeAttribute("mensaje");

                int id_rol = u.getId_rol();
                sesion.setAttribute("id_rol", id_rol);  // Establecer el id_rol en la sesión

                switch (id_rol) {
                    case 1: // es administrador
                        ruta = "administrador.jsp";
                        break;
                    case 2: // es docente
                        ruta = "docente.jsp";
                        break;
                    default:
                        sesion.setAttribute("mensaje", "Rol no reconocido");
                        ruta = "index.jsp";
                        break;
                }
            }
        }
        // Enviar al usuario a la página correspondiente
        resp.sendRedirect(ruta);

        // Limpieza de la sesión si es necesario
        //sesion.invalidate(); // Descomentar si quieres invalidar la sesión después de cada operación
    }
}