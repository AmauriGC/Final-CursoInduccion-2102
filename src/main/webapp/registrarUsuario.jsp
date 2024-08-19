<%@ page import="utez.edu.mx.sisgacicursodeinduccion.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrar Usuario</title>
    <link href="CSS/boot.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/registrar_tabla.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/registrar_usuario.css">
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <script>
        function validarFormulario() {
            var form = document.forms["registroForm"];
            var nombre = form["nombre"].value;
            var apellidos = form["apellidos"].value;
            var correo = form["correo"].value;
            var contra1 = form["contra1"] ? form["contra1"].value : "";
            var contra2 = form["contra2"] ? form["contra2"].value : "";
            var telefono = form["telefono"].value;
            var curp = form["curp"].value;
            var operacion = form["operacion"].value;

            if ((operacion === "registrar" || operacion === "actualizar") && (nombre === "" || apellidos === "" || correo === "" || telefono === "" || curp === "")) {
                alert("Todos los campos deben ser llenados");
                return false;
            }

            // Validar que solo contenga letras y acentos para nombre y apellidos
            var letrasRegex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/;
            if (!letrasRegex.test(nombre)) {
                alert("El nombre solo debe contener letras y acentos.");
                return false;
            }
            if (!letrasRegex.test(apellidos)) {
                alert("Los apellidos solo deben contener letras y acentos.");
                return false;
            }

            // Validar correo electrónico completo con dominio .com
            var correoRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+(\.com)$/;
            if (!correoRegex.test(correo)) {
                alert("El correo electrónico debe ser válido y terminar en .com");
                return false;
            }

            // Validar teléfono
            if (telefono !== "" && !/^\d{10}$/.test(telefono)) {
                alert("Teléfono inválido. Debe tener exactamente 10 dígitos.");
                return false;
            }

            // Validar CURP
            if (curp !== "" && !/^[A-Z0-9]{18}$/.test(curp)) {
                alert("CURP inválido. Debe tener exactamente 18 caracteres alfanuméricos en mayúsculas.");
                return false;
            }

            // Validaciones específicas para registro
            if (operacion === "registrar") {
                if (contra1 === "" || contra2 === "") {
                    alert("Todos los campos de contraseña deben ser llenados");
                    return false;
                }
                if (contra1 !== contra2) {
                    alert("Las contraseñas no coinciden");
                    return false;
                }
            }

            if (operacion === "actualizar") {
                // Solicitar confirmación antes de actualizar
                var confirmar = confirm("¿Está seguro de que desea actualizar los datos?");
                if (!confirmar) {
                    window.location.href = "gestionUsuario.jsp"; // Redirigir a gestionUsuario.jsp si cancela
                    return false; // Evitar el envío del formulario
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
                                    Usuario u = (Usuario) sesion.getAttribute("usuario");
                                    String operacion = (String) sesion.getAttribute("operacion");
                                    String mensaje = (String) sesion.getAttribute("mensaje");
                                    if (operacion == null) {
                                        operacion = "registrar";
                                    }
                                %>

                                <div class="text-center">
                                    <h4 class="mt-1 mb-5 pb-1">
                                        <%= operacion.equals("registrar") ? "Registrar" : operacion.equals("actualizar") ? "Actualizar" : "" %>
                                    </h4>
                                    <p><%= operacion.equals("registrar") ? "Docentes" : operacion.equals("actualizar") ? "Docentes" : "" %>
                                    </p>
                                </div>

                                <% if (mensaje != null) { %>
                                <div class="alert alert-danger" role="alert">
                                    <%= mensaje %>
                                </div>
                                <% } %>

                                <% switch (operacion) {
                                    case "registrar": %>

                                <form name="registroForm" method="post" action="sign_in"
                                      onsubmit="return validarFormulario()">

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="nombre" class="form-control"
                                               placeholder="Nombre"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="apellidos" class="form-control"
                                               placeholder="Apellidos"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="email" name="correo" class="form-control"
                                               placeholder="Correo"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="password" name="contra1" class="form-control"
                                               placeholder="Contraseña"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="password" name="contra2" class="form-control"
                                               placeholder="Confirmar Contraseña"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="tel" name="telefono" class="form-control"
                                               placeholder="Teléfono"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="curp" class="form-control"
                                               placeholder="CURP"/>
                                    </div>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <input type="hidden" name="operacion" value="registrar">
                                        <input type="submit" data-mdb-button-init data-mdb-ripple-init
                                               class="btn btn-outline-primary none" value="Registrar">
                                        <a href="administrador.jsp" class="btn btn-outline-danger none">Salir</a>
                                    </div>
                                </form>

                                <% break;
                                    case "actualizar": %>

                                <form name="registroForm" method="post" action="sign_in"
                                      onsubmit="return validarFormulario()">

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="nombre" class="form-control" value="<%=u.getNombre()%>"
                                               placeholder="Nombre"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="apellidos" class="form-control"
                                               value="<%=u.getApellidos()%>"
                                               placeholder="Apellidos"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="email" name="correo" class="form-control"
                                               value="<%=u.getCorreo()%>"
                                               placeholder="Correo"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="tel" name="telefono" class="form-control"
                                               value="<%=u.getTelefono()%>"
                                               placeholder="Teléfono"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" name="curp" class="form-control" value="<%=u.getCurp()%>"
                                               placeholder="CURP"/>
                                    </div>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <input type="hidden" name="operacion" value="actualizar">
                                        <input type="hidden" name="id_usuario" value="<%=u.getId_usuario()%>">
                                        <input type="submit" data-mdb-button-init data-mdb-ripple-init
                                               class="btn btn-outline-primary none" value="Actualizar">
                                        <a href="gestionUsuario.jsp" class="btn btn-outline-danger none">Salir</a>
                                    </div>
                                </form>
                                <% break;
                                } %>

                                <%
                                    sesion.removeAttribute("usuario");
                                    sesion.removeAttribute("mensaje");
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
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
