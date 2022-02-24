<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Asignar Trabajadores</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>

	<h1>Dirección de la Obra</h1>
	<h2>Descripción de Tarea</h2>
	<hr>
	<br>
	
	<h3 align="center">Trabajadores</h3>
	
	<button type="button" class="btn btn-success"
	data-bs-toggle="modal" data-bs-target="#myModal"
	onClick="deleteMode()">Agregar Trabajador</button>
	<br>
	<div class="container mt-3">
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_materiales">
			<th>Empleado</th>
			<th>Tipo</th>
			<th>Desde</th>
			<th>Hasta</th>
			<th></th>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="deleteMode()">Eliminar</button>
			</tr>
		</table>
	</div>
	
	<div class="modal" id="myModal">
		<div class="modal-dialog modal-fullscreen-xxl-down">
			<div class="modal-content">
	
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="modalHead">Empleados</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
	
	
				<form method="post" action="Tareas_Materiales">
					<!-- Modal body -->
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="idtarea"
							placeholder="idtarea" name="idtarea" readonly> <label
							for="idprov">ID Tarea</label>
					</div>
					
					<label for="idempleado">ID Empleado</label> <select id="idempleado"
						name="idempleado">
						<option></option>
					</select>
					<div class="form-floating mb-3 mt-3">
						<input type="date" class="form-control" id="descripcionmaterial"
							placeholder="descripcionmaterial" name="descripcionmaterial"
							required> <label for="razonsocial">Fecha Desde</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="date" class="form-control" id="preciomaterial"
							placeholder="preciomaterial" name="preciomaterial" required>
						<label for="direccion">Fecha Hasta</label>
					</div>
	
					<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
					
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="btn">Registrar</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>