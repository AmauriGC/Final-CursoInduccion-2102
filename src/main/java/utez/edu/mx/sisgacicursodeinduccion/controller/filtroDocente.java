
package utez.edu.mx.sisgacicursodeinduccion.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/docente.jsp",
        "/gestionAspiranteDocente.jsp",
        "/gestionGruposAspiranteDocente.jsp",
        "/gestionGruposDocente.jsp",
})

public class filtroDocente implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isDocente = false;

        if (session != null) {
            System.out.println("Sesión obtenida. id_rol = " + session.getAttribute("id_rol"));
        } else {
            System.out.println("Sesión no obtenida.");
        }

        int idRol = (session != null && session.getAttribute("id_rol") != null) ? (int) session.getAttribute("id_rol") : -1;
        if (idRol == 2) {
            isDocente = true;
            System.out.println("Usuario es docente.");
        } else {
            System.out.println("Usuario no es docente. Redirigiendo a acceso denegado.");
        }

        if (isDocente) {
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpResponse.setHeader("Pragma", "no-cache");
            httpResponse.setDateHeader("Expires", 0);
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("accesoDenegado.jsp");
        }
    }
}


