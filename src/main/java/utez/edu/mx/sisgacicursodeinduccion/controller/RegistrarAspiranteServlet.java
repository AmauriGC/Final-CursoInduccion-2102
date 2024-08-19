package utez.edu.mx.sisgacicursodeinduccion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utez.edu.mx.sisgacicursodeinduccion.dao.AsignarDao;
import utez.edu.mx.sisgacicursodeinduccion.dao.AspiranteDao;
import utez.edu.mx.sisgacicursodeinduccion.model.Aspirante;
import utez.edu.mx.sisgacicursodeinduccion.model.GrupoAspirante;

import java.io.IOException;

@WebServlet(name = "RegistrarAspiranteServlet", value = "/sign_in_a")
public class RegistrarAspiranteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_aspirante;
        String operacion = req.getParameter("operacion");

        try {
            id_aspirante = Integer.parseInt(req.getParameter("id_aspirante"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("error.jsp"); // Redirige a una página de error si el ID es inválido
            return;
        }

        AspiranteDao dao = new AspiranteDao();
        Aspirante a = dao.getOneA(id_aspirante);

        if (a == null) {
            resp.sendRedirect("error.jsp"); // Redirige a una página de error si el aspirante no se encuentra
            return;
        }

        HttpSession sesion = req.getSession();
        sesion.setAttribute("aspirante", a);
        sesion.setAttribute("operacion", operacion);

        resp.sendRedirect("registrarAspirante.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();
        String operacion = req.getParameter("operacion");

        switch (operacion) {
            case "registrar": {
                String nombre = req.getParameter("nombre");
                String apellidos = req.getParameter("apellidos");
                String correo = req.getParameter("correo");
                String telefonoStr = req.getParameter("telefono");
                String curp = req.getParameter("curp");

                String nombreA = req.getParameter("nombre");
                String apellidosA = req.getParameter("apellidos");
                String correoA = req.getParameter("correo");
                int id_grupo = Integer.parseInt(req.getParameter("id_grupo"));
                String ruta = "gestionAspirante.jsp";

                // Validación: Verificar que el grupo seleccionado es válido
                if (id_grupo == 0) {
                    sesion.setAttribute("mensaje2", "Debes seleccionar un grupo válido.");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                if (nombre == null || nombre.isEmpty() ||
                        apellidos == null || apellidos.isEmpty() ||
                        correo == null || correo.isEmpty() ||
                        telefonoStr == null || telefonoStr.isEmpty() ||
                        curp == null || curp.isEmpty()) {
                    sesion.setAttribute("mensaje2", "Todos los campos deben ser llenados");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                long telefono;
                try {
                    telefono = Long.parseLong(telefonoStr);
                } catch (NumberFormatException e) {
                    sesion.setAttribute("mensaje2", "El teléfono debe ser un número válido");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                AspiranteDao dao = new AspiranteDao();

                if (dao.existsByCorreo(correo)) {
                    sesion.setAttribute("mensaje2", "El correo electrónico ya está registrado.");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                if (dao.existsByTelefono(telefono)) {
                    sesion.setAttribute("mensaje2", "El teléfono ya está registrado");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                if (dao.existsByCurp(curp)) {
                    sesion.setAttribute("mensaje2", "El CURP ya está registrado.");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                GrupoAspirante o = new GrupoAspirante();
                o.setId_grupo(id_grupo);
                o.setNombreA(nombreA);
                o.setApellidosA(apellidosA);
                o.setCorreoA(correoA);

                Aspirante a = new Aspirante();
                a.setNombre(nombre);
                a.setApellidos(apellidos);
                a.setCorreo(correo);
                a.setTelefono(telefono);
                a.setCurp(curp);

                AsignarDao da = new AsignarDao();
                boolean insertadoo = da.insertGrupo_A(o);

                boolean insertado = dao.insertA(a);

                if (insertado) {
                    sesion.setAttribute("mensaje2", "Registro exitoso");
                    resp.sendRedirect(ruta);
                } else {
                    sesion.setAttribute("mensaje3", "El aspirante no se registró correctamente");
                    resp.sendRedirect("registrarAspirante.jsp");
                }
                break;
            }
            case "actualizar": {
                String nombre = req.getParameter("nombre");
                String apellidos = req.getParameter("apellidos");
                String correo = req.getParameter("correo");
                String telefonoStr = req.getParameter("telefono");
                String curp = req.getParameter("curp");
                String id_aspiranteStr = req.getParameter("id_aspirante");
                String ruta = "gestionAspirante.jsp";

                if (nombre == null || nombre.isEmpty() ||
                        apellidos == null || apellidos.isEmpty() ||
                        correo == null || correo.isEmpty() ||
                        telefonoStr == null || telefonoStr.isEmpty() ||
                        curp == null || curp.isEmpty() ||
                        id_aspiranteStr == null || id_aspiranteStr.isEmpty()) {
                    sesion.setAttribute("mensaje2", "Todos los campos deben ser llenados");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                long telefono;
                int id_aspirante;
                try {
                    telefono = Long.parseLong(telefonoStr);
                    id_aspirante = Integer.parseInt(id_aspiranteStr);
                } catch (NumberFormatException e) {
                    sesion.setAttribute("mensaje2", "El teléfono y el ID deben ser números válidos");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                Aspirante a = new Aspirante();
                a.setId_aspirante(id_aspirante);
                a.setNombre(nombre);
                a.setApellidos(apellidos);
                a.setCorreo(correo);
                a.setTelefono(telefono);
                a.setCurp(curp);

                AspiranteDao dao = new AspiranteDao();
                boolean actualizado = dao.updateA(a);

                if (actualizado) {
                    sesion.setAttribute("mensaje", "Actualización exitosa");
                    resp.sendRedirect(ruta);
                } else {
                    sesion.setAttribute("mensaje", "El aspirante no se actualizó correctamente");
                    resp.sendRedirect("registrarAspirante.jsp");
                }
                break;
            }
            case "calificar": {
                String id_aspiranteStr = req.getParameter("id_aspirante");
                String calificacionStr = req.getParameter("calificacion");
                String ruta = "gestionAspiranteDocente.jsp";

                if (id_aspiranteStr == null || id_aspiranteStr.isEmpty() ||
                        calificacionStr == null || calificacionStr.isEmpty()) {
                    sesion.setAttribute("mensaje2", "Todos los campos deben ser llenados");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                int calificacion;
                int id_aspirante;
                try {
                    calificacion = Integer.parseInt(calificacionStr);
                    id_aspirante = Integer.parseInt(id_aspiranteStr);
                } catch (NumberFormatException e) {
                    sesion.setAttribute("mensaje2", "La calificación y el ID deben ser números válidos");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }


                if (calificacion < 0 || calificacion > 10) {
                    sesion.setAttribute("mensaje2", "La calificación debe estar entre 0 y 10");
                    resp.sendRedirect("registrarAspirante.jsp");
                    return;
                }

                Aspirante a = new Aspirante();
                a.setId_aspirante(id_aspirante);
                a.setCalificacion(calificacion);

                AspiranteDao dao = new AspiranteDao();
                boolean calificado = dao.updateAs(a);

                if (calificado) {
                    sesion.setAttribute("mensaje2", "Calificación exitosa");
                    resp.sendRedirect(ruta);
                } else {
                    sesion.setAttribute("mensaje3", "La calificación no se registró correctamente");
                    resp.sendRedirect("registrarAspirante.jsp");
                }
                break;
            }
        }
    }
}