<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enviar Correo</title>
<style>
/* Estilos generales */
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: #f0f4f8;
	display: flex;
	justify-content: center;
	align-items: flex-start;
	min-height: 100vh;
	padding: 40px 20px;
}

/* Contenedor del formulario */
form {
	background: #fff;
	padding: 30px 40px;
	border-radius: 8px;
	box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
	max-width: 480px;
	width: 100%;
	animation: fadeIn 0.5s ease-in-out;
}

/* Título */
h1 {
	text-align: center;
	color: #333;
	margin-bottom: 30px;
	font-size: 24px;
}

/* Grupo de campos */
.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	font-weight: 600;
	margin-bottom: 6px;
	color: #444;
	font-size: 15px;
}

input[type="email"], input[type="text"], textarea {
	width: 100%;
	padding: 10px 12px;
	border: 2px solid #ccc;
	border-radius: 6px;
	font-size: 15px;
	transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input[type="email"]:focus, input[type="text"]:focus, textarea:focus {
	border-color: #007bff;
	box-shadow: 0 0 5px rgba(0, 123, 255, 0.2);
	outline: none;
}

/* Botón de envío */
button {
	width: 100%;
	padding: 12px 20px;
	font-size: 16px;
	font-weight: 600;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	background-color: #28a745;
	color: white;
	transition: background-color 0.3s ease, transform 0.2s ease;
}

button:hover {
	background-color: #218838;
	transform: scale(1.01);
}

/* Menú de navegación */
nav {
	text-align: center;
	margin-bottom: 20px;
	position: absolute;
	top: 20px;
	left: 0;
	width: 100%;
}

nav a {
	margin: 0 15px;
	text-decoration: none;
	color: #007bff;
	font-weight: 600;
	font-size: 16px;
}

nav a:hover {
	text-decoration: underline;
}

/* Animación */
@
keyframes fadeIn {from { opacity:0;
	transform: translateY(-10px);
}

to {
	opacity: 1;
	transform: translateY(0);
}
}
</style>
</head>
<body>

	<!-- Menú -->


	<!-- Formulario de correo -->
	<form action="EnviarCorreoServlet" method="post">
		<h1>Enviar Correo</h1>

		<div class="form-group">
			<label for="destinatario">Correo Destinatario:</label> <input
				type="email" name="destinatario" id="destinatario" required />
		</div>

		<div class="form-group">
			<label for="asunto">Asunto:</label> <input type="text" name="asunto"
				id="asunto" required />
		</div>

		<div class="form-group">
			<label for="mensaje">Mensaje:</label>
			<textarea name="mensaje" id="mensaje" rows="6" required></textarea>
			
		</div>

		<button type="submit">Enviar Correo</button>
		<nav>
				<a href="index.jsp">Formulario de mascota</a>
			</nav>
	</form>
</body>


</html>