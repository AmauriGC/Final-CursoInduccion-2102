<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/index.css">
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <script>
        function validarFormulario() {
            var correo = document.forms["loginForm"]["correo"].value;
            var contra = document.forms["loginForm"]["contra"].value;

            if (correo === "") {
                alert("Por favor, ingrese su correo.");
                return false;
            }

            if (contra === "") {
                alert("Por favor, ingrese su contraseña.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body style="font-family: 'Montserrat', sans-serif">
<section class="h-100 gradient-form box" style="background-color: #eee;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-10">
                <div class="card rounded-3 text-black">
                    <div class="row g-0">
                        <div class="col-lg-6">
                            <div class="card-body p-md-5 mx-md-4">
                                <div class="text-center">
                                    <img src="img/CursoInduccion.jpg" alt="logo"><br>
                                    <h4 class="mt-1 mb-5 pb-1">CURSOS DE INDUCCIÓN</h4>
                                    <p>Iniciar Sesión</p>
                                </div>

                                <form id="loginForm" method="post" action="login"
                                      onsubmit="return validarFormulario();">
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="email" name="correo" id="correo" class="form-control"
                                               placeholder="Correo"/>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="password" name="contra" id="contraseña" class="form-control"
                                               placeholder="Contraseña"/>
                                    </div>

                                    <!-- Mostrar Mensajes de Error -->
                                    <% if (session.getAttribute("mensaje") != null) { %>
                                    <div class="alert alert-danger">
                                        <%= session.getAttribute("mensaje") %>
                                    </div>
                                    <%
                                        session.removeAttribute("mensaje");
                                    %>
                                    <% } %>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <button type="submit" data-mdb-button-init data-mdb-ripple-init
                                                class="btn btn-outline-danger" value="ingresar">Ingresar
                                        </button>
                                        <br><br>
                                        <div class="text-center">
                                            <a class="text-muted" href="solicitudRecuperacion.jsp">
                                                ¿Olvidaste la contraseña?</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                            <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                <div class="text-center">
                                    <h4 class="mb-3">Ingresa con tu correo y contraseña proporcionados
                                        para acceder al contenido.</h4>
                                    <p class="small mb-0">Estamos aquí para apoyarte en cada paso del camino.
                                        ¡Comencemos esta emocionante aventura educativa juntos!.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
