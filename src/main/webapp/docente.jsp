<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Docente</title>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <link href="CSS/boot.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/docente.css">
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
                        <a class="dropdown-item colorN" href="gestionDatos.jsp">Descargas</a>
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
                <h2>Gestionar</h2>
            </div>

            <div class="col-lg-4">
                <div class="pastel-blue contenedor">
                    <img class="" src="img/gestionarAspirante.png" alt="aspirante image" width="80" height="80">
                    <h2>Gestionar Aspirantes</h2>
                    <p>En este apartado podran ver los detalles de los aspirantes registrados.</p>
                    <p><a class="btn bg-color-blue" href="gestionAspiranteDocente.jsp" role="button">Entrar &raquo;</a>
                    </p>
                </div>
            </div><!-- /.col-lg-4 -->

            <div class="col-lg-4">
                <div class="pastel-pink contenedor">
                    <img class="" src="img/gestionarGrupo.png" alt="grupos image" width="80" height="80">
                    <h2>Gestionar Grupos</h2>
                    <p>En este apartado podran ver los grupos que existen en total.</p>
                    <p><a class="btn bg-color-blue" href="gestionGruposDocente.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div><!-- /.col-lg-4 -->

            <div class="col-lg-4">
                <div class="pastel-purple contenedor">
                    <img class="" src="img/calificarGrupo.png" alt="grupos image" width="80" height="80">
                    <h2>Descargar datos</h2>
                    <p>En este apartado podran descargar los datos en formato pdf.</p>
                    <p><a class="btn bg-color-blue" href="gestionDatos.jsp" role="button">Entrar &raquo;</a></p>
                </div>
            </div><!-- /.col-lg-4 -->

        </div><!-- /.row -->
    </div>
</main>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
