<%@ page import="utez.edu.mx.sisgacicursodeinduccion.dao.GrupoDao" %>
<%@ page import="utez.edu.mx.sisgacicursodeinduccion.model.Grupo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestión de Grupos</title>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <link href="CSS/boot.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/gestion_tabla.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/datatables.css">
</head>

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-brown">
        <a class="colorF" href="administrador.jsp">Administrador</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle colorW bg-brown" href="#" id="navbarDropdownRegister"
                       role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Registrar
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownRegister">
                        <a class="dropdown-item colorN" href="registrarUsuario.jsp">Usuarios</a>
                        <a class="dropdown-item colorN" href="registrarAspirante.jsp">Aspirantes</a>
                        <a class="dropdown-item colorN" href="registrarGrupos.jsp">Grupos</a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle colorW bg-brown" href="#" id="navbarDropdownManage" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Gestionar
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownManage">
                        <a class="dropdown-item colorN" href="gestionUsuario.jsp">Usuarios</a>
                        <a class="dropdown-item colorN" href="gestionAspirante.jsp">Aspirantes</a>
                        <a class="dropdown-item colorN" href="gestionGrupos.jsp">Grupos</a>
                    </div>
                </li>
            </ul>

            <a class="nav-link colorF" href="index.jsp">Cerrar Sesión</a>
        </div>
    </nav>
</header>
<body style="font-family: 'Montserrat', sans-serif">
<div class="tabla p-md-5 mx-md-4">
    <a href="administrador.jsp" class="btn btn-outline-danger salir">Salir</a>
    <a class="btn bg-color-blue" href="registrarGrupos.jsp" role="button">Registrar Grupo</a>

    <div class="table-container">
        <table id="example" class="table table-striped table-hover" style="width:100%">
            <thead style="background-color: #4A90E2; color: white;">
            <tr>
                <th>GRUPO</th>
                <th>LETRA</th>
                <th>NOMBRE DEL DOCENTE</th>
                <th>CORREO DEL DOCENTE</th>
                <th>CANTIDAD</th>
                <th>...</th>
            </tr>
            </thead>
            <tbody>
            <%
                GrupoDao dao = new GrupoDao();
                ArrayList<Grupo> lista = dao.getAllG();
                for (Grupo g : lista) {
            %>
            <tr>
                <td><%=g.getId_grupo()%>
                </td>
                <td><%=g.getLetra()%>
                </td>
                <td><%=g.getNombre()%>
                </td>
                <td><%=g.getCorreo()%>
                </td>
                <td><%=g.getCantidad()%>
                </td>
                <td>
                    <form action="register" method="get">
                        <input type="hidden" name="operacion" value="actualizar">
                        <input type="hidden" name="id_grupo" value="<%=g.getId_grupo()%>">
                        <button type="submit" class="btn btn-outline-primary">Asignar docente</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<%
    HttpSession sesion = request.getSession();
    sesion.removeAttribute("grupo");
    sesion.removeAttribute("operacion");
    sesion.removeAttribute("mensaje");
    sesion.removeAttribute("mensaje2");
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
