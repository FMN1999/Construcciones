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
	<h1 style="text-align:center">
		Hola
		<%=u.getNombre() %></h1>
	<br>
	<% if(u.getTipo().equalsIgnoreCase("Cliente")) { %>
		<%@page import="entidades.Obra"%>
		<%@page import="entidades.Cliente"%>
		<% Cliente c= (Cliente)session.getAttribute("cliente"); %>
		<div class="container-fluid p-5 bg-primary text-white text-center">
		  <h1> Tus Obras </h1>
		</div>
		<div class="container mt-4">
		  <div class="row">
		    <% for(Obra o: c.getObras()){ %>
		    	<div class="col-sm-3 border border-4 rounded border-primary" style="margin:3px;">
		    			<form action="Presupuesto" method="get">
		    				<input id="idObra" name="idObra" style="display:None;" value=<%= o.getIdObra() %>>
		    				<button type="submit" class="btn btn-light">
		    					<h2><%= o.getDireccion() %>
		    					<% if(o.presupuestosPendientes()>=1){ %>
		    					<span class="badge bg-secondary">New</span>
		    				<% } %>
		    					</h2>
		    				</button>
		    				<p class="card-text"><%= o.getDescripcion() %></p>
		    			</form>
			    		<p class="card-text">Obra numero: <%= o.getIdObra() %></p>
			    		<p class="card-text">Presupuestos pendientes: <%= o.presupuestosPendientes() %></p>
		    	</div>
		    	
		    <% } %>
		  </div>
		</div>
	<% } %>
</body>
<script type="text/javascript">
	
</script>
</html>