<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Trabajador"%>
<%@page import="entidades.Trabajador.TareaAsignada"%>
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
	<% if(u.getTipo().equalsIgnoreCase("Trabajador")) {%>
		    <% Trabajador t=(Trabajador)request.getAttribute("empleado"); %>
		    <hr>
		    <div class="container mt-4">
			    <div class="row">
			    	<h2>Tareas asignadas</h2>
			    	<% for(Trabajador.TareaAsignada tt: t.getTareasAsignadas()) {%>
			    		<div class="col-sm-3 border border-4 rounded border-primary" style="margin:3px;">
				    		<p class="h2">Tarea: <%= tt.tarea.getDescripcion() %></p><br>
				    		<p class="h4">Fecha asignada:<%= tt.fecha_asignada %></p><br>
				    		<p class="h4">Horas asignadas:<%= tt.hs_asignadas %></p><br>
			    		</div>
			    	<% } %>
			    </div>
		    </div>
		    
		    <% } %>
</body>
<script type="text/javascript">
	
</script>
</html>