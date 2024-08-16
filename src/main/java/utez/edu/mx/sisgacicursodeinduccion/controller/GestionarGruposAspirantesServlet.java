package utez.edu.mx.sisgacicursodeinduccion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utez.edu.mx.sisgacicursodeinduccion.dao.GrupoAspiranteDao;
import utez.edu.mx.sisgacicursodeinduccion.model.GrupoAspirante;

import java.io.IOException;

@WebServlet(name = "GestionarGruposAspirantesServlet", value = "/buscarGrupo")
public class GestionarGruposAspirantesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_grupo = Integer.parseInt(req.getParameter("id_grupo"));
        GrupoAspiranteDao dao = new GrupoAspiranteDao();
        GrupoAspirante g = dao.getOneGA(id_grupo);
        HttpSession sesion = req.getSession();
        sesion.setAttribute("grupo", g);
        resp.sendRedirect("registrarGrupos.jsp");
    }
}