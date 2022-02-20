<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Vista de Presupuesto</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="entidades.Obra"%>
	<%@ page import="java.util.*" %>
	<%@ page import="java.text.SimpleDateFormat"%>
	<%@page import="logica.Tipo_TareaLogic" %>
	<%@page import="entidades.Tipo_Tarea" %>
	<%@page import="logica.MaterialLogic" %>
	<%@page import="entidades.Material" %>
	<%@page import="logica.MaquinariaLogic" %>
	<%@page import="entidades.Maquinaria" %>
	
	<% Obra o = (Obra)request.getAttribute("obra"); %>

	<h1><%= o.getDireccion() %></h1>
	<hr>
	
	<div class="container mt-3">
		<%
		   Date dNow = new Date();
		   SimpleDateFormat ft = 
		   new SimpleDateFormat ("MM/dd/yyyy");
		   String currentDate = ft.format(dNow);
		%>
		<label> Fecha de Creación: </label> <label><%=currentDate%></label>
		<br>
		
		<h2 style="align:center">Tareas</h2>
		<br>
		<% ArrayList<Tipo_Tarea> tps= Tipo_TareaLogic.getAll(); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_tipos_tarea">
			<th>Descripcion tipo tarea</th>
			<th>Precio</th>
			<th></th>
			<th></th>
			<% if(tps!=null) { %>
			<% for(Tipo_Tarea tt: tps){ %>
			<tr>
				<td style="display: none;"><%= tt.getId_tipo_tarea() %></td>
				<td><%= tt.getDescripcion() %></td>
				<td><%= tt.getPrecio() %></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="editMode()">Agregar</button></td>		
			</tr>
			<% } %>
			<% } %>
		</table>
		<br>
		
		<h3 align="center">Tareas a realizar</h3>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_tareas">
			<th>Tipo de Tarea</th>
			<th>Descripción</th>
			<th>Cantidad de m2</th>
			<th>Precio</th>
			<th></th>
			<th></th>
			<th></th>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal1"
						onClick="editMode()">Agregar Material</button></td>		
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal2"
						onClick="editMode()">Agregar Maquinaria</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="editMode()">Eliminar</button></td>
			</tr>
		</table>
		<br>
		
		<h3 align="center">Materiales</h3>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_materiales">
			<th>Tarea</th>
			<th>Material</th>
			<th>Cantidad</th>
			<th>Precio total</th>
			<th></th>
			<th></th>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal1"
						onClick="editMode()">Editar</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal1"
						onClick="editMode()">Eliminar</button></td>
			</tr>
		</table>
		
		<h3 align="center">Maquinarias</h3>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_maquinarias">
			<th>Tarea</th>
			<th>Maquinaria</th>
			<th>Horas de Uso</th>
			<th>Precio total</th>
			<th></th>
			<th></th>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal2"
						onClick="editMode()">Editar</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal2"
						onClick="editMode()">Eliminar</button></td>
			</tr>
		</table>
		
		<div class="modal" id="myModal">
			<div class="modal-dialog modal-fullscreen-xxl-down">
				<div class="modal-content">
	
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="modalHead">Registrar Tarea</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
	
	
					<form method="post" action="VerPresupuesto">
						<!-- Modal body -->
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="idtipo_tarea"
								placeholder="ID" name="idtipo_tarea" readonly> <label
								for="idObra">ID Tipo de Tarea</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="descripcion"
								placeholder="Descripcion" name="descripcion" required> <label
								for="descripcion">Descripción</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<input type="number" class="form-control" id="cantm2"
								placeholder="CantidadM2" name="cantm2" required> <label
								for="descripcion">Cantidad M2</label>
						</div>
						
						<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
						
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="btn">Aceptar</button>
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal">Cerrar</button>
						</div>
					</form>
	
				</div>
			</div>
		</div>
		
		<div class="modal" id="myModal1">
			<div class="modal-dialog modal-fullscreen-xxl-down">
				<div class="modal-content">
	
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="modalHead">Registrar Materiales</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
	
	
					<form method="post" action="VerPresupuesto">
						<!-- Modal body -->
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="idtipo_tarea"
								placeholder="ID" name="idtipo_tarea" readonly> <label
								for="idObra">ID Tipo de Tarea</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<select id="idmaterial" name="idmaterial">
								<% ArrayList<Material> mats = MaterialLogic.getAll(); %>
								<% for(Material m:mats){ %>
								<option value=<%= m.getId_material() %>><%= m.getDescripcion() %></option>
								<% } %>	
							</select>
						</div>
						<div class="form-floating mb-3 mt-3">
							<input type="number" class="form-control" id="cantm2"
								placeholder="CantidadM2" name="cantm2" required> <label
								for="descripcion">Cantidad</label>
						</div>
						
						<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
						
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="btn">Aceptar</button>
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal">Cerrar</button>
						</div>
					</form>	
				</div>
			</div>
		</div>
		
		<div class="modal" id="myModal2">
			<div class="modal-dialog modal-fullscreen-xxl-down">
				<div class="modal-content">
	
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="modalHead">Registrar Maquinaria</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
	
	
					<form method="post" action="VerPresupuesto">
						<!-- Modal body -->
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="idtipo_tarea"
								placeholder="ID" name="idtipo_tarea" readonly> <label
								for="idObra">ID Tipo de Tarea</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<select id="idmaterial" name="idmaterial">
								<% ArrayList<Maquinaria> maqs = MaquinariaLogic.getAll(); %>
								<% for(Maquinaria m:maqs){ %>
								<option value=<%= m.getIdMaquina() %>><%= m.getDescripcion() %></option>
								<% } %>	
							</select>
						</div>
						<div class="form-floating mb-3 mt-3">
						</div>
						<div class="form-floating mb-3 mt-3">
							<input type="number" class="form-control" id="cantm2"
								placeholder="CantidadM2" name="cantm2" required> <label
								for="descripcion">Horas de uso</label>
						</div>
						
						<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
						
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="btn">Aceptar</button>
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal">Cerrar</button>
						</div>
					</form>	
				</div>
			</div>
		</div>
		
	</div>
	
	
</body>
</html>