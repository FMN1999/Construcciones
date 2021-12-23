<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Empleados</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="java.util.ArrayList" %>
	<%@page import="entidades.Trabajador" %>
	<div>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" ><!--onClick="regMode()"-->
	    Registrar empleado
	    </button>
    </div>
	<div class="container mt-3">
		<br>
		<h1 class="text-center">Oficiales</h1>
		<br>
		<% ArrayList<Trabajador> ofs=(ArrayList<Trabajador>)request.getAttribute("oficiales"); %>
		<table class="table table-dark table-hover" id="tab_oficiales">
			<th>Cuil</th>
			<th>Tipo Doc.</th>
			<th>Nº Documento</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
			<th>Fecha de nacimiento</th>
			<th>Disponibilidad</th>
			<% for(Trabajador t: ofs){ %>
				<tr>
					<td><%= t.getCuil() %></td>
					<td><%= t.getTipo_doc() %></td>
					<td><%= t.getN_doc() %></td>
					<td><%= t.getNombre() %></td>
					<td><%= t.getApellido() %></td>
					<td><%= t.getEmail() %></td>
					<td><%= t.getFechaNac() %></td>
					<% if(t.isDisponible()){ %>
						<td class="text-success"><%= "Disponible" %></td>
					<% } else{  %>
						<td class="text-danger"><%= "No Disponible" %></td>
					<% } %>
				</tr>
			<% } %>
			
		</table>
		
		<br>
		<h1 class="text-center">Obreros</h1>
		<br>
		<% ArrayList<Trabajador> obs=(ArrayList<Trabajador>)request.getAttribute("obreros"); %>
		<table class="table table-dark table-hover" id="tab_obreros">
			<th>Cuil</th>
			<th>Tipo Doc.</th>
			<th>Nº Documento</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
			<th>Fecha de nacimiento</th>
			<th>Disponibilidad</th>
			<% for(Trabajador t: obs){ %>
				<tr>
					<td><%= t.getCuil() %></td>
					<td><%= t.getTipo_doc() %></td>
					<td><%= t.getN_doc() %></td>
					<td><%= t.getNombre() %></td>
					<td><%= t.getApellido() %></td>
					<td><%= t.getEmail() %></td>
					<td><%= t.getFechaNac() %></td>
					<% if(t.isDisponible()){ %>
						<td class="text-success"><%= "Disponible" %></td>
					<% } else{  %>
						<td class="text-danger"><%= "No Disponible" %></td>
					<% } %>
				</tr>
			<% } %>
			
		</table>
	</div>
	
	
<div class="modal" id="myModal">
  <div class="modal-dialog modal-fullscreen-xxl-down">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="modalHead">Registrar Empleado</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      
      <form method="post" action="Proveedores" >
      	  <!-- Modal body -->
	      <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="idusu" placeholder="id" name="idusu" readonly>
			  <label for="idprov">ID Usuario</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="nombre" placeholder="Nombre" name="nombre" required>
			  <label for="nombre">Nombre</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="apellido" placeholder="Apellido" name="apellido" required>
			  <label for="apellido">Apellido</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="email" class="form-control" id="email" placeholder="E-mail" name="email" required>
			  <label for="idprov">Correo Electronico</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="password" class="form-control" id="password" placeholder="Contraseña" name="password" required>
			  <label for="password">Contraseña</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="number" class="form-control" id="cuil" placeholder="Cuil" name="cuil" required>
			  <label for="cuil">Cuil</label>
		  </div>
		  <div class="form-floating container mt-5">
		  <div class="row">
			  <div class="col-sm-5">
			  	<label for="tipodoc">Tipo de documento</label>
			  	<select name="tipodoc" id="tipodoc">
			  		<option>DNI</option>
			  		<option>ID</option>
			  		<option>CI</option>
			  		<option>LC</option>
			  		<option>LE</option>
			  	</select>
			  </div>
			  <div class="col-sm-5">
			  	<input type="number" class="form-control" id="ndoc" name="ndoc" placeholder="Numero de documento" required>
			  	<label for="ndoc">Numero de documento</label>
			  </div>
		  	</div>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="date" class="form-control" id="fnac" placeholder="Fecha de nacimiento" name="fnac" required>
			  <label for="fnac">Fecha de nacimiento</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <label for="tipo_e">Jerarquia</label>
			  <select id="tipo_e" name="tipo_e">
			  	<option>Oficial</option>
			  	<option>Obrero</option>
			  </select>
		  </div>
			<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
			<select name="accion" id="accion" style="display:none;">
				<option>Registrar</option>
				<option>Editar</option>
				<option>Eliminar</option>
			</select>
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<button type="sumbit" class="btn btn-primary" id="btn">Registrar</button>
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
	      </div>
      </form>

    </div>
  </div>
</div>
	
</body>
</html>