<%@ page import="utez.edu.mx.sisgacicursodeinduccion.model.Aspirante" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utez.edu.mx.sisgacicursodeinduccion.dao.AspiranteDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Verificar si hay una sesión activa y si el usuario está autenticado
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head>
    <title>Gestion de aspirante</title>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <link href="CSS/boot.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/gestion_tabla.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/datatables.css">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-brown">
        <a class="colorF" href="docente.jsp">Docente</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link dropdown-toggle colorW bg-brown" href="#" id="navbarDropdownManage" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Gestionar
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownRegister">
                        <a class="dropdown-item colorN" href="gestionAspiranteDocente.jsp">Aspirante</a>
                        <a class="dropdown-item colorN" href="gestionGruposDocente.jsp">Grupos</a>
                        <a class="dropdown-item colorN" href="gestionDatos.jsp">Descargar</a>
                    </div>
                </li>
            </ul>
            <a class="nav-link colorF" href="LogoutServlet">Cerrar Sesión</a>
        </div>
    </nav>
</header>
<div class="card-body p-md-5 mx-md-4 tabla">
    <a href="docente.jsp" class="btn btn-outline-danger">Salir</a>
    <div class="table-container">
        <table id="example" class="table table-striped table-hover" style="width:100%">
            <thead>
            <tr>
                <th>FOLIO</th>
                <th>NOMBRE</th>
                <th>APELLIDOS</th>
                <th>CORREO</th>
                <th>TELÉFONO</th>
                <th>CURP</th>
                <th>CALIFICACIÓN</th>
                <th>ASIGNAR CALIFICACIÓN</th>
            </tr>
            </thead>
            <tbody>
            <%
                AspiranteDao dao = new AspiranteDao();
                ArrayList<Aspirante> lista = dao.getAllA();
                for (Aspirante a : lista) {%>
            <tr>
                <td><%=a.getId_aspirante()%></td>
                <td><%=a.getNombre()%></td>
                <td><%=a.getApellidos()%></td>
                <td><%=a.getCorreo()%></td>
                <td><%=a.getTelefono()%></td>
                <td><%=a.getCurp()%></td>
                <td><%=a.getCalificacion()%></td>
                <td>
                    <form action="sign_in_a" method="get">
                        <input type="hidden" name="id_aspirante" value="<%=a.getId_aspirante()%>">
                        <input type="hidden" name="operacion" value="calificar">
                        <button type="submit" class="btn btn-outline-primary">Calificar</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<%
    sesion.removeAttribute("aspirante");
    sesion.removeAttribute("mensaje");
    sesion.removeAttribute("mensaje2");
    sesion.removeAttribute("operacion");
%>


<script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/JS/datatables.js"></script>
<script src="${pageContext.request.contextPath}/JS/dataTables.bootstrap5.js"></script>
<script src="${pageContext.request.contextPath}/JS/es-MX.json"></script>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        const table = document.getElementById('example');
        new DataTable(table, {
            language: {
                url: '${pageContext.request.contextPath}/JS/es-MX.json'
            }
        });
    });
</script>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>