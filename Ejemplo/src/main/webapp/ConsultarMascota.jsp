<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <title>Consultar Mascota</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #121212;
            color: #e0e0e0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding-top: 60px;
        }

        .container {
            max-width: 600px;
        }

        .card {
            background-color: #1e1e1e;
            border: none;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.5);
        }

        .card-header {
            background-color: #0d6efd;
            color: white;
            padding: 20px 30px;
            border-bottom: none;
        }

        .card-body {
            padding: 30px;
        }

        .form-label {
            font-weight: 500;
            color: #e0e0e0;
        }

        .form-control {
            background-color: #2c2c2c;
            border: 1px solid #444;
            color: #fff;
        }

        .form-control:focus {
            background-color: #2c2c2c;
            color: #fff;
            border-color: #0d6efd;
            box-shadow: none;
        }

        .btn-primary {
            background-color: #0d6efd;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0b5ed7;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .list-group-item {
            background-color: #2c2c2c;
            border: none;
            border-bottom: 1px solid #444;
            color: #e0e0e0;
        }

        .alert-warning {
            background-color: #ffc107;
            color: #212529;
            border: none;
        }

        .btn-group {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        h4, h5 {
            font-weight: 600;
        }

        @media (max-width: 576px) {
            .btn-group {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-header text-center">
            <h4 class="mb-0">Consultar Mascota</h4>
        </div>
        <div class="card-body">

            <!-- Mensaje si no se encuentra -->
            <c:if test="${not empty mensaje}">
                <div class="alert alert-warning text-center">${mensaje}</div>
            </c:if>

            <!-- Formulario -->
            <form action="ConsultarMascota" method="post" class="mb-4">
                <div class="mb-3">
                    <label for="idMascota" class="form-label">ID de la Mascota:</label>
                    <input type="text" class="form-control" id="idMascota" name="IDMascota" required>
                </div>
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                    <a href="index.jsp" class="btn btn-secondary">Volver</a>
                </div>
            </form>

            <!-- Resultados -->
            <c:if test="${not empty nombre}">
                <h5 class="mb-3">Detalles de la Mascota</h5>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Nombre:</strong> ${nombre}</li>
                    <li class="list-group-item"><strong>Especie:</strong> ${especie}</li>
                    <li class="list-group-item"><strong>Género:</strong> ${genero}</li>
                    <li class="list-group-item"><strong>Raza:</strong> ${raza}</li>
                    <li class="list-group-item"><strong>ID del Propietario:</strong> ${idcedula}</li>
                </ul>
            </c:if>

        </div>
    </div>
</div>

</body>
</html>