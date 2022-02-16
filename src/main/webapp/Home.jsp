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
		    			<form action="Tareas" method="get">
		    				<input id="idObra" name="idObra" style="display:None;" value=<%= o.getIdObra() %>>
		    				<button type="submit"><%= o.getDireccion() %></button>
		    			</form>
			    		<p>Obra numero: <%= o.getIdObra() %></p>
			    		<p>Tareas presupuestadas: </p>
			    		<p>Tareas no presupuestadas: </p>
		    	</div>
		    	
		    <% } %>
		  </div>
		</div>
	<% } %>
</body>
<script type="text/javascript">
	
</script>
</html>