<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Materiales</title>
</head>
<body>
<jsp:include page="Shared.jsp"></jsp:include>
<%@page import="entidades.Material" %>
<%@page import="entidades.Proveedor" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>

<div class="container mt-3">
<br>
<h1 class="text-center">Materiales</h1>
<br>
<% HashMap<Integer,Proveedor> provs=(HashMap<Integer,Proveedor>)request.getAttribute("provedores"); %>
<table class="table table-dark table-hover" id="materiales">
	<th>Nombre del producto</th>
	<th>Provedor</th>
	<th>Precio</th>
	<% if(request.getAttribute("materiales")!=null && request.getAttribute("provedores")!=null) { %>
		<% for(Material m:(ArrayList<Material>)request.getAttribute("materiales")){ %>
	 	<tr>
	 		<td><%= m.getDescripcion() %></td>
	 		<td value=<%= m.getId_provedor() %>><%= provs.get(m.getId_provedor()).getRazonSocial() %></td>
	 		<td><%= m.getPrecio() %></td>
	 	</tr>
		<% } %>
	<% } %>
</table>


</div>

</body>
</html>