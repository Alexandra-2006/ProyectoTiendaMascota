<%@page import="DAO.MascotaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Modelo.Mascota"%>
<%@page import="java.util.List"%>
<html>
<head>
    <title>Listado de Mascotas</title>
</head>
<body>

<h1>Listado de Mascotas</h1>

<a href="index.jsp">← Volver</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Especie</th>
        <th>Género</th>
        <th>Raza</th>
        <th>ID Cedula</th>
    </tr>

	<%
      MascotaDAO list = new MascotaDAO();
        List<Mascota> lista = (List<Mascota>) request.getAttribute("mascota");
        if (lista != null) {
        for (Mascota m : lista) {
    %>
		<tr>
			<td><%= m.getIDMascota() %></td>
			<td><%= m.getNombre() %></td>
			<td><%= m.getEspecie() %></td>
			<td><%= m.getGénero() %></td>
			<td><%= m.getRaza() %></td>
			<td><%= m.getIdcedula() %></td>
		</tr>
		<%
            }
        } else {
    %>
		<tr>
			<td colspan="6">No hay mascotas para mostrar</td>
		</tr>
		<%
        }
    %>
	</table>

</body>
</html>
