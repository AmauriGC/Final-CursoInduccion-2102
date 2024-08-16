package utez.edu.mx.sisgacicursodeinduccion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utez.edu.mx.sisgacicursodeinduccion.dao.UsuarioDao;
import utez.edu.mx.sisgacicursodeinduccion.model.Usuario;
import utez.edu.mx.sisgacicursodeinduccion.utils.GmailSender;
import utez.edu.mx.sisgacicursodeinduccion.utils.SimpleRandomStringGenerator;

import java.io.IOException;

@WebServlet(name = "ContraServlet", value = "/recuContra")
public class ContraServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDao dao = new UsuarioDao();

        // 1. Obtener email
        String correo = request.getParameter("correo");

        // 2. Revisar que existe en la BD
        Usuario u = dao.getByEmail(correo);

        if (u == null) {
            request.getSession().setAttribute("mensaje", "El correo no esta registrado o esta incompleto.");
            response.sendRedirect("index.jsp");
            return;
        }

        // 3. Generar código
        String codigo = SimpleRandomStringGenerator.generateRandomString(10);

        // 4. Insertar código en BD
        u.setCodigoRecuperacion(codigo);
        dao.actualizacionCodigoRecuperacion(u);

        // 5. Generar correo electrónico con enlace
        String enlace = "http://localhost:8080/CursoInduccion_Css_6_war_exploded/recuContra?codigo=" + codigo;
        String mensaje = "Para recuperar su contraseña haga click en el siguiente enlace,\n" +
                "\n<a href=\"" + enlace + "\">Recuperar contraseña </a>";
        try {
            new GmailSender().sendMail(correo, "Recuperación de Contraseña", mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("mensaje", "El correo ha sido enviado.");
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDao dao = new UsuarioDao();

        // 1. Obtener código
        String codigo = request.getParameter("codigo");

        // 2. Revisar que existe en la BD
        Usuario u = dao.getCodigoRecuperacion(codigo);
        if (u == null) {
            request.getSession().setAttribute("mensaje", "El codigo es invalido.");
            response.sendRedirect("solicitudRecuperacion.jsp");
            return;
        }

        // 3. Redirigir a recuperacion.jsp
        response.sendRedirect("recuperacion.jsp?codigo=" + codigo);
    }
}
