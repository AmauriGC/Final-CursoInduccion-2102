<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrador</title>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <link href="CSS/boot.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/administrador.css">
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

            <a class="nav-link colorF" href="index.jsp">Cerrar Sesion</a>
        </div>
    </nav>
</header>

<br><br><br>

<main role="main">

    <div class="container marketing">

        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Registrar</h2>
            </div>

            <div class="col-lg-4">
                <div class="pastel-blue contenedor">
                    <img class="" src="img/registrarUsuario.png" alt="usuario image" width="90" height="90">
                    <h2>Usuarios</h2>
                    <p>En este apartado se daran de alta a los usuarios que podran acceder a la pagina del sistema.</p>
                    <p><a class="btn bg-color-blue" href="registrarUsuario.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="pastel-pink contenedor">
                    <img class="" src="img/registrarAspirante.png" alt="aspirante image" width="90" height="90">
                    <h2>Aspirantes</h2>
                    <p>En este apartado se daran de alta a los aspirantes que inician el curso de induccion.</p>
                    <p><a class="btn bg-color-blue" href="registrarAspirante.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div>

            <!--
            <div class="col-lg-4 ">
                <div class="pastel-purple contenedor">
                    <img class="" src="img/registrarGrupo.png" alt="grupos image" width="90" height="90">
                    <h2>Grupos</h2>
                    <p>En este apartado se daran de alta a los grupos que estan conformados por los aspirantes.</p>
                    <p><a class="btn bg-color-blue" href="registrarGrupos.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div>
            -->

            <div class="col-lg-12 text-center">
                <h2>Gestionar</h2>
            </div>

            <div class="col-lg-4 ">
                <div class="pastel-yellow contenedor">
                    <img class="" src="img/gestionarUsuario.png" alt="usuario image" width="90" height="90">
                    <h2>Usuarios</h2>
                    <p>En este apartado podran ver los detalles de los usuarios dados de alta en el sistema.</p>
                    <p><a class="btn bg-color-blue" href="gestionUsuario.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div>

            <div class="col-lg-4 ">
                <div class="pastel-green contenedor">
                    <img class="" src="img/gestionarAspirante.png" alt="aspirante image" width="90"
                         height="90">
                    <h2>Aspirantes</h2>
                    <p>En este apartado podran ver los detalles de los aspirantes dados de alta en el sistema.</p>
                    <p><a class="btn bg-color-blue" href="gestionAspirante.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div>

            <div class="col-lg-4 ">
                <div class="pastel-orange contenedor">
                    <img class="" src="img/gestionarGrupo.png" alt="grupos image" width="90" height="90">
                    <h2>Grupos</h2>
                    <p>En este apartado podran ver a todos los grupos que existen en el curso.</p>
                    <p><a class="btn bg-color-blue" href="gestionGrupos.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div>

        </div>
    </div>
</main>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
