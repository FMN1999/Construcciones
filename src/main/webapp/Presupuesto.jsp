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
	<%@page import="logica.PresupuestoLogic" %>
	<%@page import="entidades.Material_a_usar" %>
	<%@page import="entidades.Tarea"%>
	<%@page import="entidades.Presupuesto" %>
	
	<% Obra o = (Obra)request.getAttribute("obra"); %>
	<% Presupuesto p = (Presupuesto)request.getAttribute("presupuesto"); %>

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
			<th>Descripción tipo tarea</th>
			<th>Precio</th>
			<th></th>
			<th></th>
			<% if(tps!=null) { %>
			<% for(Tipo_Tarea tt: tps){ %>
			<tr>
				<td style="display: none;"><%= tt.getId_tipo_tarea() %></td>
				<td><%= tt.getDescripcion() %></td>
				<td><%= tt.getPrecio() %></td>
				<td>
					<button type="submit" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="agregaTipoTarea()">Agregar</button></td>		
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
						onClick="agregaMaterial()">Agregar Material</button></td>		
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal2"
						onClick="agregaMaquinaria()">Agregar Maquinaria</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="eliminaTarea()">Eliminar</button></td>
			</tr>
		</table>
		<br>
		<% ArrayList<Material> alt= PresupuestoLogic.getMateriales(p); %>
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
						onClick="editaMaterial()">Editar</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal1"
						onClick="eliminaMaterial()">Eliminar</button></td>
			</tr>
		</table>
		
		<% ArrayList<Maquinaria> mqs = PresupuestoLogic.getMaquinarias(p); %>
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
						onClick="editaMaquinaria()">Editar</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal2"
						onClick="eliminaMaquinaria()">Eliminar</button></td>
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
							<input type="text" class="form-control" id="idtipo_tareat"
								placeholder="ID" name="idtipo_tareat" readonly> <label
								for="idObra">ID Tipo de Tarea</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="descripciont"
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
							<input type="text" class="form-control" id="idtipo_tareamt"
								placeholder="ID" name="idtipo_tareamt" readonly> <label
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
							<input type="number" class="form-control" id="cantm2mt"
								placeholder="CantidadM2" name="cantm2mt" required> <label
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
							<input type="text" class="form-control" id="idtipo_tareamq"
								placeholder="ID" name="idtipo_tareamq" readonly> <label
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
		
		<select name="accion" id="accion" style="display: none;">
						<option>AgregaTipoTarea</option>
						<option>AgregaMaterial</option>
						<option>AgregaMaquinaria</option>
						<option>EliminaTarea</option>
						<option>EditaMaterial</option>
						<option>EliminaMaterial</option>
						<option>EditaMaquinaria</option>
						<option>EliminaMaqunaria</option>
		</select>
		
		<br>
		<h2>Cálculo de presupuesto</h2>
		<div class="container">
			<div class="row">
				<div class="col">
					<label>Gastos: </label>		
				</div>
				<div class="col">
					<label>Detalle: </label>
				</div>
				<div class="col">
					<label>Monto:</label>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col">
					<label>Cantidad de Oficiales: </label>		
				</div>
				<div class="col">
					<input type="number" id="cantofi" name="cantofi"></input>
				</div>
				<div class="col">
					<label id="montooficiales"></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label>Cantidad de Albañiles: </label>		
				</div>
				<div class="col">
					<input type="number" id="cantalb" name="cantalb"></input>
				</div>
				<div class="col">
					<label id="montoalb"></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label>Materiales: </label>		
				</div>
				<div class="col">
					<label></label>
				</div>
				<div class="col">
					<label id="montomateriales"></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label>Maquinaria: </label>		
				</div>
				<div class="col">
					<label></label>
				</div>
				<div class="col">
					<label id="montomaquinarias"></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label>Tareas: </label>		
				</div>
				<div class="col">
					<label></label>
				</div>
				<div class="col">
					<label id="montotareas"></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label>Ganancias: </label>		
				</div>
				<div class="col">
					<input type="number" id="ganancias" name="ganancias"></input>
				</div>
				<div class="col">
					<label id="montotareas"></label>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col">
					<label></label>		
				</div>
				<div class="col">
					<label>TOTAL</label>
				</div>
				<div class="col">
					<label id="montototal"></label>
				</div>
			</div>
		</div>
		<button type="button" class="btn btn-success">Calcular</button>
	</div>
	
	<script type="text/javascript">
	var table = document.getElementById('tab_tipos_tarea'), rIndex;
	for (i = 0; i < table.rows.length; i++) {
	    table.rows[i].onclick = function () {
	        rIndex = this.rowsIndex;
	        document.getElementById('idtipo_tareat').value = this.cells[0].innerHTML;
	    };
	}

	function agregaTipoTarea(){
		document.getElementById('modalHead').innerHTML="Registrar Tipo de Tarea";
		document.getElementById('accion').selectedIndex=0;
		
	    document.getElementById('descripciont').removeAttribute("readonly"  , false);
	    document.getElementById('cantm2').removeAttribute("readonly"  , false);
	    
		document.getElementById('idtipo_tareat').value = null;
	    document.getElementById('descripciont').value = null;
	    document.getElementById('cantm2').value = null;
	}
	
	
	</script>
	

</body>
</html>