package utez.edu.mx.sisgacicursodeinduccion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utez.edu.mx.sisgacicursodeinduccion.dao.UsuarioDao;
import utez.edu.mx.sisgacicursodeinduccion.model.Usuario;
import utez.edu.mx.sisgacicursodeinduccion.utils.GmailSender;

import java.io.IOException;

@WebServlet(name = "UpdateContraServlet", value = "/updateContra")
public class UpdateContraServlet extends HttpServlet {
    private final UsuarioDao usuarioDao = new UsuarioDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Obtener nueva contraseña
        String nuevaContra = request.getParameter("contra");

        // 2. Obtener código
        String codigo = request.getParameter("codigo");

        // 3. Actualizar contraseña y código en la BD
        Usuario usuario = usuarioDao.getCodigoRecuperacion(codigo);

        if (usuario != null) {
            usuario.setContra(nuevaContra);
            usuario.setCodigoRecuperacion(null); // Limpiar el código
            usuarioDao.actContraCodigo(usuario);

            // 4. Opcional: enviar correo de aviso al usuario
            try {
                new GmailSender().sendMail(usuario.getCorreo(),
                        "Contraseña actualizada",
                        "Su contraseña ha sido actualizada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 5. Redirigir a Index.jsp
            response.sendRedirect("index.jsp");
        } else {
            request.getSession().setAttribute("mensaje", "Código inválido.");
            response.sendRedirect("recuperacion.jsp");
        }
    }
}
