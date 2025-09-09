<%@page import="com.mysql.cj.xdevapi.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Gestión de Mascota</title>
<style>
/* Reset básico */
* {
	box-sizing: border-box;
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: #f0f4f8;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: flex-start;
	min-height: 100vh;
	padding: 40px 20px;
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 30px;
}

form {
	background: #fff;
	padding: 30px 40px;
	border-radius: 8px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	max-width: 450px;
	width: 100%;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	font-weight: 600;
	margin-bottom: 6px;
	color: #444;
}

input[type="text"], input[type="number"] {
	width: 100%;
	padding: 10px 12px;
	border: 1.8px solid #ccc;
	border-radius: 5px;
	font-size: 15px;
	transition: border-color 0.3s ease;
}

input[type="text"]:focus, input[type="number"]:focus {
	border-color: #007bff;
	outline: none;
}

.button-group {
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
	justify-content: center;
}

button {
	flex: 1 1 120px;
	padding: 12px 20px;
	font-size: 16px;
	font-weight: 600;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	color: white;
}

button[type="submit"][value="insertar"] {
	background-color: #28a745;
}

button[type="submit"][value="insertar"]:hover {
	background-color: #218838;
}

button[type="submit"][value="actualizar"] {
	background-color: #ffc107;
	color: #212529;
}

button[type="submit"][value="actualizar"]:hover {
	background-color: #e0a800;
}

button[type="submit"][value="eliminar"] {
	background-color: #dc3545;
}

button[type="submit"][value="eliminar"]:hover {
	background-color: #c82333;
}

button[type="submit"][value="listar"] {
	background-color: #17a2b8;
}

button[type="submit"][value="listar"]:hover {
	background-color: #138496;
}

button[type="reset"] {
	background-color: #6c757d;
}

button[type="reset"]:hover {
	background-color: #5a6268;
}

/* Responsive */
@media ( max-width : 480px) {
	.button-group {
		flex-direction: column;
	}
	button {
		flex: 1 1 100%;
	}
}
</style>
</head>
<body>


	<form action="MascotaServlet" method="POST">
		<h1>Formulario de Mascota</h1>

		<div class="form-group">
			<label for="IDMascota">ID Mascota (solo para
				actualizar/eliminar):</label> <input type="number" name="IDMascota"
				id="IDMascota" />
		</div>

		<div class="form-group">
			<label for="nombre">Nombre:</label> <input type="text" name="nombre"
				id="nombre" required />
		</div>

		<div class="form-group">
			<label for="especie">Especie:</label> <input type="text"
				name="especie" id="especie" required />
		</div>

		<div class="form-group">
			<label for="genero">Género:</label> <input type="text" name="genero"
				id="genero" required />
		</div>

		<div class="form-group">
			<label for="raza">Raza:</label> <input type="text" name="raza"
				id="raza" required />
		</div>

		<div class="form-group">
			<label for="IDCedula">ID Propietario:</label> <input type="number"
				name="IDCedula" id="IDCedula" required />
		</div>

		<div class="button-group">
			<button type="submit" name="accion" value="insertar">Registrar</button>
			<button type="submit" name="accion" value="actualizar"
				onclick="return validarActualizar()">Actualizar</button>
			<button type="submit" name="accion" value="eliminar"
				onclick="return eliminarSoloConID()">Eliminar</button>
			<button type="submit" name="accion" value="listar"
				onclick="return quitarRequired()">Ver</button>
			<button type="reset">Limpiar</button>


			<nav style="text-align: center; margin-bottom: 20px;">
				<a href="EnviarCorreo.jsp">Enviar Correo</a>
			</nav>
		</div>
		
		<a href="ConsultarMascota.jsp" class="btn btn-info">Consultar
				mascota</a>
	</form>

	<form action="GenerarPdfServlet" method="get">
		<input type="submit" value="Generar PDF" />
	</form>

	<script>
	function validarActualizar() {
		const idInput = document.getElementById('IDMascota');
		if (!idInput.value.trim()) {
			alert("Debes ingresar el ID de la mascota que deseas actualizar.");
			idInput.focus();
			return false;
		}
		return true;
	}
</script>

	<script>
	function eliminarSoloConID() {
	  const idInput = document.getElementById('IDMascota');
	  if (!idInput.value) {
	    alert("Por favor ingresa el ID de la mascota a eliminar.");
	    return false;
	  }
	  // Quitar required de los otros campos temporalmente
	  document.querySelectorAll('[required]').forEach(input => input.removeAttribute('required'));
	  return true;
	}
</script>

	<script>
	function quitarRequired() {
	    document.querySelectorAll('input[required]').forEach(input => {
	        input.removeAttribute('required');
	    });
	    return true;  // permite que el formulario se envíe
	}
</script>

</body>

</html>
