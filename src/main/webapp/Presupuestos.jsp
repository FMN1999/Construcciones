<%@page import="entidades.Obra"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Presupuesto</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	
	
	<% Obra o = (Obra)request.getAttribute("obra"); %>

	<h1><%= o.getDireccion() %></h1>
	
	

</body>
</html>