package utez.edu.mx.sisgacicursodeinduccion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utez.edu.mx.sisgacicursodeinduccion.dao.GrupoDao;
import utez.edu.mx.sisgacicursodeinduccion.dao.UsuarioDao;
import utez.edu.mx.sisgacicursodeinduccion.model.Grupo;

import java.io.IOException;

@WebServlet(name = "RegistrarGruposServlet", value = "/register")
public class RegistrarGruposServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idGrupoStr = req.getParameter("id_grupo");
        String operacion = req.getParameter("operacion");

        if (idGrupoStr != null && !idGrupoStr.trim().isEmpty()) {
            try {
                int id_grupo = Integer.parseInt(idGrupoStr);
                GrupoDao dao = new GrupoDao();
                Grupo g = dao.getOneG(id_grupo);
                HttpSession sesion = req.getSession();
                sesion.setAttribute("grupo", g);
                sesion.setAttribute("operacion", operacion);
            } catch (NumberFormatException e) {
                HttpSession sesion = req.getSession();
                sesion.setAttribute("mensaje", "ID del grupo inválido.");
                resp.sendRedirect("gestionGrupos.jsp");
                return;
            }
        } else {
            HttpSession sesion = req.getSession();
            sesion.setAttribute("mensaje", "ID del grupo no proporcionado.");
            resp.sendRedirect("gestionGrupos.jsp");
            return;
        }
        resp.sendRedirect("registrarGrupos.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        String idGrupo = request.getParameter("id_grupo");
        String letra = request.getParameter("letra");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");


        GrupoDao grupoDao = new GrupoDao();
        UsuarioDao usuarioDao = new UsuarioDao();
        HttpSession sesion = request.getSession();

        // Validar letra
        if ("registrar".equals(operacion)) {
            if (letra == null || letra.trim().isEmpty() || !letra.matches("^[A-Z0-9]+$")) {
                sesion.setAttribute("mensaje", "La letra debe ser alfanumérica y en mayúscula.");
                sesion.setAttribute("operacion", "registrar");
                response.sendRedirect("registrarGrupos.jsp");
                return;
            }

            // Verificar si la letra ya está registrada
            if (grupoDao.existsByLetra(letra)) {
                sesion.setAttribute("mensaje", "La letra ya está registrada.");
                sesion.setAttribute("operacion", "registrar");
                response.sendRedirect("registrarGrupos.jsp");
                return;
            }
        }

        // Procesar el registro o actualización
        if ("registrar".equals(operacion)) {
            Grupo grupo = new Grupo();
            grupo.setLetra(letra);

            if (grupoDao.insertG(grupo)) {
                response.sendRedirect("gestionGrupos.jsp");
            } else {
                sesion.setAttribute("mensaje", "Error al registrar el grupo.");
                sesion.setAttribute("operacion", "registrar");
                response.sendRedirect("registrarGrupos.jsp");
            }
        } else if ("actualizar".equals(operacion)) {
            if (idGrupo != null && !idGrupo.trim().isEmpty()) {
                try {
                    Grupo grupo = grupoDao.getById(Integer.parseInt(idGrupo));
                    grupo.setNombre(nombre);
                    grupo.setCorreo(correo);

                    if (grupoDao.updateG(grupo)) {
                        response.sendRedirect("gestionGrupos.jsp");
                    } else {
                        sesion.setAttribute("mensaje", "Error al actualizar el grupo.");
                        sesion.setAttribute("operacion", "actualizar");
                        response.sendRedirect("registrarGrupos.jsp");
                    }
                } catch (NumberFormatException e) {
                    sesion.setAttribute("mensaje", "ID del grupo inválido.");
                    sesion.setAttribute("operacion", "actualizar");
                    response.sendRedirect("registrarGrupos.jsp");
                }
            } else {
                sesion.setAttribute("mensaje", "ID del grupo no proporcionado.");
                sesion.setAttribute("operacion", "actualizar");
                response.sendRedirect("registrarGrupos.jsp");
            }
        }
    }
}
