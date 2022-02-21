<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Tareas</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="entidades.Tarea" %>
	<%@page import="logica.TareaLogic" %>
	
	<div class="container mt-3">
			<br>
			<h1 class="text-center">Tareas</h1>
			
			<br>
			<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_materiales">
				<th>Tarea</th>
				<th>Descripción</th>
				<th>Cantidad de m2</th>
				<th>Obra</th>
				<th></th>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><form action="Tareas_Empleados" method="get">
		    				<button type="submit" class="btn btn-primary">Registrar Empleado</button>
		    			</form></td>
				</tr>
			</table>
		</div>

</body>
</html>