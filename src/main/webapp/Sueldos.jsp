<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sueldos</title>
</head>
<body>
<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="java.util.ArrayList"%>
	<%@page import="entidades.Trabajador"%>
	<%@page import="entidades.Tarea"%>
	<%@page import="entidades.Tipo_Tarea"%>
	
	<div class="container mt-3">

		<br>
		<h1 class="text-center">Oficiales</h1>
		<br>
		<% ArrayList<Trabajador> ofs=(ArrayList<Trabajador>)request.getAttribute("oficiales"); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_oficiales">
			<th>Cuil</th>
			<th>Tipo Doc.</th>
			<th>Nº Documento</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
			<th>Fecha de nacimiento</th>
			<th class="h3">Sueldo</th>
			<% for(Trabajador t: ofs){ %>
			<tr>
				<td><%= t.getCuil() %></td>
				<td><%= t.getTipo_doc() %></td>
				<td><%= t.getN_doc() %></td>
				<td><%= t.getNombre() %></td>
				<td><%= t.getApellido() %></td>
				<td><%= t.getEmail() %></td>
				<td><%= t.getFechaNac() %></td>
				<td class="h3">$<%= t.calcularSueldo() %></td>
	
			</tr>
			<% } %>

		</table>

		<br>
		<h1 class="text-center">Obreros</h1>
		<br>
		<% ArrayList<Trabajador> obs=(ArrayList<Trabajador>)request.getAttribute("obreros"); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_obreros">
			<th>Cuil</th>
			<th>Tipo Doc.</th>
			<th>Nº Documento</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
			<th>Fecha de nacimiento</th>
			<th class="h3">Sueldo</th>
			<% for(Trabajador t: obs){ %>
			<tr>
				<td><%= t.getCuil() %></td>
				<td><%= t.getTipo_doc() %></td>
				<td><%= t.getN_doc() %></td>
				<td><%= t.getNombre() %></td>
				<td><%= t.getApellido() %></td>
				<td><%= t.getEmail() %></td>
				<td><%= t.getFechaNac() %></td>
				<td class="h3">$<%= t.calcularSueldo() %></td>
			</tr>
			<% } %>

		</table>
	</div>
</body>
</html>