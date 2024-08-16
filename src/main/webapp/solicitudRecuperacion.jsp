<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitud de recuperacion</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/solicitudRecuperacion.css">
</head>
<body style="font-family: 'Montserrat', sans-serif">
<section class="h-100 gradient-form" style="background-color: #eee;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-10">
                <div class="card rounded-3 text-black">
                    <div class="row g-0">
                        <div class="col-lg-6">
                            <div class="card-body p-md-5 mx-md-4">
                                <div class="text-center">
                                    <img src="img/CursoInduccion.jpg" alt="logo">

                                    <h4 class="mt-1 mb-5 pb-1">Recupera tu contraseña</h4>
                                </div>
                                <form method="post" action="recuContra">
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="email" name="correo" id="correo" class="form-control"
                                               placeholder="Correo" required/>
                                    </div>
                                    <div class="text-center">
                                        <%
                                            HttpSession sesion = request.getSession();
                                            String mensaje = (String) sesion.getAttribute("mensaje");

                                            if (mensaje != null) { %>
                                        <p style="color: #3100ff;"><%=mensaje%>
                                        </p>
                                        <% } %>
                                        <div class="text-center pt-1 mb-5 pb-1">
                                            <button type="submit" data-mdb-button-init data-mdb-ripple-init
                                                    class="btn btn-outline-danger">Solicitar
                                            </button>
                                            <a href="index.jsp" class="btn btn-outline-danger">Salir</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                            <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                <div class="text-center">
                                    <h4 class="mb-3">Informacion</h4>
                                    <p class="small mb-0">Si tienes problemas mayores con tu cuenta, hazle saber a un
                                        administrador.</p>
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
