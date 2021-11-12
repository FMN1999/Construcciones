<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="entidades.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
</head>
<body>
	<%@include file="Shared.jsp" %>
	<% Usuario u= (Usuario)session.getAttribute("Usuario"); %>
	<h1>Hola <%=u.getNombre() %></h1>

</body>
</html>