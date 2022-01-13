<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="entidades.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<% Usuario u= (Usuario)session.getAttribute("usuario"); %>
	<h1>
		Hola
		<%=u.getNombre() %></h1>
	<br>
	<table class="table ">
	</table>
</body>
</html>