<%@page import="entidades.Obra"%>
<%@page import="entidades.Presupuesto" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Presupuestos</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import ="java.util.ArrayList"%>
	<%@page import="entidades.Presupuesto" %>
	<%@page import="logica.PresupuestoLogic" %>
	<% Obra o = (Obra)request.getAttribute("obra"); %>

	<h1><%= o.getDireccion() %></h1>
	<hr>
	<div class="container mt-3">
		<br>
		<h1 class="text-center">Presupuestos</h1>
		<br>
		<div>
			<form action="VerPresupuesto" method="get">
					<input id="idPresupuesto" name="idPresupuesto" style="display:None;" value=<%= 0 %>>
					<input id="idObra" name="idObra" style="display:None;" value=<%= o.getIdObra() %>>
    				<button type="submit" class="btn btn-primary">Registrar Presupuesto</button>
    		</form>
		</div>
		<br>
		<% ArrayList<Presupuesto> psp= (ArrayList<Presupuesto>)request.getAttribute("presupuestos"); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_materiales">
			<th>Fecha Emisión</th>
			<th>Monto</th>
			<th>Estado</th>
			<th></th>
			<th></th>
			<th></th>
			<% for(Presupuesto p: psp){ %>
			<tr>
				<td style="display: none;"><%= p.getId_presupuesto() %></td>
				<td><%= p.getFecha_emision() %></td>
				<td><%= p.getMonto() %></td>
				<td></td>
				<td><form action="VerPresupuesto" method="get">
							<input id="idPresupuesto" name="idPresupuesto" style="display:None;" value=<%= p.getId_presupuesto() %>>
							<input id="idObra" name="idObra" style="display:None;" value=<%= o.getIdObra() %>>
		    				<button type="submit" class="btn btn-primary">Ver Presupuesto</button>
		    		</form></td>
				<td><button type="submit" class="btn btn-success">Confirmar</button>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="deleteMode()">Eliminar</button>
			</tr>
			<% } %>
		</table>
	</div>
		
</body>
</html>