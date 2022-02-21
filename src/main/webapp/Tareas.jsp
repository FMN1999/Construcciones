<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tareas</title>
</head>
<body>
	<%@page import="entidades.Tarea" %>
	<%@page import="logica.TareaLogic" %>
	
	<div class="container mt-3">
			<br>
			<h1 class="text-center">Tareas</h1>
			
			<br>
			<% ArrayList<Tarea> ts = TareaLogic.getTareas(idPresup); %>
			<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_materiales">
				<th>Nombre del producto</th>
				<th>Provedor</th>
				<th>Precio</th>
				<th></th>
				<th></th>
				<% if(provs!=null) { %>
				<% for(Proveedor p: provs){ %>
				<% for(Material m: p.getMateriales()){ %>
				<tr>
					<td style="display: none;"><%= m.getId_material() %></td>
					<td><%= m.getDescripcion() %></td>
					<td value=<%= p.getIdProveedor() %>><%= p.getRazonSocial() %></td>
					<td><%= m.getPrecio() %></td>
					<td><button type="button" class="btn btn-success"
							data-bs-toggle="modal" data-bs-target="#myModal"
							onClick="editMode()">Editar</button>
					<td><button type="button" class="btn btn-danger"
							data-bs-toggle="modal" data-bs-target="#myModal"
							onClick="deleteMode()">Eliminar</button>
				</tr>
				<% } %>
				<% } %>
				<% } %>
			</table>
		</div>

</body>
</html>