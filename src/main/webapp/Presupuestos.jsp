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
	<%@page import="entidades.Usuario" %>
	<%@page import="logica.PresupuestoLogic" %>
	<%@page import ="entidades.Tarea" %>
	<%@page import ="logica.TareaLogic" %>
	<%@ page import = "entidades.Material" %>
	<%@page import ="logica.MaterialLogic" %>
	<%@ page import ="entidades.Maquinaria" %>
	<%@page import = "logica.MaquinariaLogic" %>
	
	<% Obra o = (Obra)request.getAttribute("obra"); %>
	<% Usuario u= (Usuario)session.getAttribute("usuario"); %>
	
	<div class="container mt-3">
		<br>
		<hr>
		<h1><%= o.getDireccion() %></h1>
		<h2><%= o.getDescripcion() %></h2>
		<br>
		<hr>
	</div>
	
	<div class="container mt-3">
		<br>
		<h1 class="text-center">Presupuestos</h1>
		<br>
		<div>
		<% if(!u.getTipo().equalsIgnoreCase("Cliente")) { %>
			<form action="VerPresupuesto"method="get">
					<input id="idPresupuesto" name="idPresupuesto" style="display:None;" value=<%= 0 %>>
					<input id="idObra" name="idObra" style="display:None;" value=<%= o.getIdObra() %>>
    				<button type="submit" class="btn btn-primary">Registrar Presupuesto</button>
    		</form>
    	<% }%>
		</div>
		<br>
		<% ArrayList<Presupuesto> psp= o.getPresupuestos(); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_materiales">
			<th>Fecha Emisión</th>
			<th>Monto</th>
			<th>Estado</th>
			<th>Fecha de aprobacion</th>
			<th>Fecha de cancelacion</th>
			<th></th>
			<% for(Presupuesto p: psp){ %>
			<tr>
				<td style="display: none;"><%= p.getId_presupuesto() %></td>
				<td><%= p.getFecha_emision() %></td>
				<td><%= p.getMonto() %></td>
				<td><%= p.getEstado() %></td>
				<td><% if(p.getFecha_aceptacion()!=null){ %><%= p.getFecha_aceptacion() %><% }else{ %>-<% } %></td>
				<td><% if(p.getFecha_caencelacion()!=null){ %><%= p.getFecha_caencelacion() %><% }else{ %>-<% } %></td>
				<td><form <% if(!u.getTipo().equalsIgnoreCase("Cliente")) { %>action="VerPresupuesto"<% }else{ %>action="PresupuestoCliente"<% } %> method="get">
							<input id="idPresupuesto" name="idPresupuesto" style="display:None;" value=<%= p.getId_presupuesto() %>>
							<input id="idObra" name="idObra" style="display:None;" value=<%= o.getIdObra() %>>
		    				<button type="submit" class="btn btn-primary">Ver Presupuesto</button>
		    		</form></td>
			</tr>
			<% } %>
		</table>
	</div>
		
	<div class="container mt-3 ">
		<% if(o.isFinalizado()) { %>
		<p class="h2 text-center">Balance</p>
		<div class= "row">
			<p class="h5 text-center"> Gastos en Materiales: <%= o.gastosMateriales() %></p>
		</div>
		<div class= "row">
			<p class="h5 text-center"> Gastos en Maquinarias: <%= o.gastosMaquinas() %></p>
		</div>
		<div class= "row">
			<p class="h5 text-center"> Gastos en Tareas: <%= o.gastosTareas() %></p>
		</div>
		<div class= "row">
			<p class="h5 text-center"> Gastos Totales: <%= o.totalPresupuestado() %></p>
		</div>
		<hr>
		<div class = "row">
			<p class="h5 text-center"> Presupuestado menos gastos: <%= o.getBalance() %></p>
		</div>
		<% } %>
	</div>
</body>
</html>