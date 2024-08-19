<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Acceso Denegado</title>
    <link rel="stylesheet" href="CSS/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/index.css">
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
</head>
<body style="font-family: 'Montserrat', sans-serif">
<section class="h-100 gradient-form box" style="background-color: #eee;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-10">
                <div class="card rounded-3 text-black">

                    <div class="card-body p-md-5 mx-md-4">

                        <div class="text-center">
                            <h4 class="mt-1 mb-5 pb-1">Acceso Denegado</h4>
                            <p>No tienes permiso para acceder a esta página</p>
                            <p>Si crees que esto es un error, por favor contacta al administrador del sistema.</p>
                            <a href="index.jsp" class="btn btn-outline-primary">Inicio</a>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
