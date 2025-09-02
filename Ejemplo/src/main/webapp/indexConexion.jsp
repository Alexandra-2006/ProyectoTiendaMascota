<%@page import="Controlador.Conexion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<%
Conexion test = new Conexion();

if (!test.getConnection().isClosed()) {
     out.print ("Conectado a MySQL...");
	
}

else{	
	out.print (" no conectado a MySQL...");
}

%>
</body>
</html>