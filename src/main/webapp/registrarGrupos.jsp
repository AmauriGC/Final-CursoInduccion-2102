<%@ page import="utez.edu.mx.sisgacicursodeinduccion.model.Grupo" %>
<%@ page import="utez.edu.mx.sisgacicursodeinduccion.model.Usuario" %>
<%@ page import="utez.edu.mx.sisgacicursodeinduccion.dao.UsuarioDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrar Grupo</title>
    <link href="CSS/boot.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/registrar_tabla.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/registrar_usuario.css">
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <script>
        function validarFormulario() {
            var form = document.forms["grupoForm"];
            var letra = form["letra"] ? form["letra"].value.toUpperCase() : '';

            // Convertir letra a mayúscula
            form["letra"].value = letra;

            // Validar letra (solo en registro)
            if (form["operacion"].value === "registrar") {
                // Validar que no esté vacío y sea solo una letra
                if (letra === "" || !/^[A-Z]$/.test(letra)) {
                    alert("La letra debe ser una única letra en mayúscula.");
                    return false;
                }
            }

            return true;
        }
    </script>
</head>
<body style="font-family: 'Montserrat', sans-serif">
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

<section class="h-75 gradient-form box" style="background-color: #eee;">
    <div class="container py-5 h-75">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-10">
                <div class="card rounded-3 text-black">
                    <div class="row g-0">
                        <div class="col-lg-6">
                            <div class="card-body p-md-5 mx-md-4">

                                <%
                                    HttpSession sesion = request.getSession();
                                    Grupo g = (Grupo) sesion.getAttribute("grupo");
                                    String operacion = (String) sesion.getAttribute("operacion");
                                    if (operacion == null) {
                                        operacion = "registrar";
                                    }
                                %>

                                <div class="text-center">
                                    <h4 class="mt-1 mb-5 pb-1">
                                        <%= operacion.equals("registrar") ? "Registrar" : "Actualizar" %> Grupo
                                    </h4>
                                </div>

                                <% if (operacion.equals("registrar")) { %>

                                <form name="grupoForm" method="post" action="register"
                                      onsubmit="return validarFormulario()">

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="letra" class="form-control" placeholder="Letra"/>
                                    </div>

                                    <!-- Mostrar Mensajes de Error -->
                                    <% if (sesion.getAttribute("mensaje") != null) { %>
                                    <div class="alert alert-danger">
                                        <%= sesion.getAttribute("mensaje") %>
                                    </div>
                                    <%
                                        sesion.removeAttribute("mensaje");
                                    %>
                                    <% } %>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <input type="hidden" name="operacion" value="registrar">
                                        <input type="submit" data-mdb-button-init data-mdb-ripple-init
                                               class="btn btn-outline-primary none" value="Registrar">
                                        <a href="administrador.jsp" class="btn btn-outline-danger none">Salir</a>
                                    </div>
                                </form>

                                <% } else if (operacion.equals("actualizar")) { %>

                                <form name="grupoForm" method="post" action="register"
                                      onsubmit="return validarFormulario()">

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="letra" class="form-control"
                                               value="<%=g != null ? g.getLetra() : "" %>"
                                               placeholder="Letra del Grupo" disabled/>
                                    </div>

                                    <%
                                        // Obtén la lista de usuarios
                                        UsuarioDao usuarioDao = new UsuarioDao();
                                        ArrayList<Usuario> lista = usuarioDao.getAll();
                                    %>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label for="correo">Correo</label>
                                        <select name="correo" id="correo" class="form-control">
                                            <option value="">Selecciona un correo</option>
                                            <%
                                                for (Usuario u : lista) {
                                                    String correo = u.getCorreo();
                                                    boolean isSelected = (g != null && g.getCorreo() != null && g.getCorreo().equals(correo));
                                            %>
                                            <option value="<%= correo %>" <%= isSelected ? "selected" : "" %>><%= correo %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>

                                    <!-- Mostrar Mensajes de Error -->
                                    <% if (sesion.getAttribute("mensaje") != null) { %>
                                    <div class="alert alert-danger">
                                        <%= sesion.getAttribute("mensaje") %>
                                    </div>
                                    <%
                                        sesion.removeAttribute("mensaje");
                                    %>
                                    <% } %>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <input type="hidden" name="operacion" value="actualizar">
                                        <input type="hidden" name="id_grupo"
                                               value="<%=g != null ? g.getId_grupo() : "" %>">
                                        <input type="submit" data-mdb-button-init data-mdb-ripple-init
                                               class="btn btn-outline-primary none" value="Actualizar">
                                        <a href="gestionGrupos.jsp" class="btn btn-outline-danger none">Salir</a>
                                    </div>
                                </form>

                                <% } %>

                                <!-- Limpiar atributos al final, solo después de mostrar el mensaje -->
                                <%
                                    // Limpiar atributos después de haber procesado el mensaje
                                    sesion.removeAttribute("grupo");
                                    sesion.removeAttribute("operacion");
                                %>
                            </div>
                        </div>
                        <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                            <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                <div class="text-center">
                                    <h4 class="mb-3">Ingresa los datos correspondientes al formulario.</h4>
                                    <p class="small mb-0">¡Comencemos esta emocionante aventura educativa juntos!.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- jQuery, Popper.js, Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
