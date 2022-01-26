<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Clientes</title>
</head>

<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="java.util.ArrayList"%>
	<%@page import="entidades.Cliente"%>
	<div>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#myModal" onClick="regMode()">Registrar
			cliente</button>
	</div>
	<div class="container mt-3">
		<br>
		<h1 class="text-center">Nuevo Cliente</h1>
		<br>
		<% ArrayList<Cliente> cli=(ArrayList<Cliente>)request.getAttribute("clientes"); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_clientes">
			<th>Cuil</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
			<th>Razon Social</th>
			<th>Teléfono</th>
			<th style="display: none;">Password</th>
			<th style="display: none;">ID</th>
			<th></th>
			<th></th>
			<% for(Cliente c: cli){ %>
			<tr>
				<td><%= c.getCuil() %></td>
				<td><%= c.getNombre() %></td>
				<td><%= c.getApellido() %></td>
				<td><%= c.getEmail() %></td>
				<td style="display: none;"><%= c.getPassword() %></td>
				<td><%= c.getRazonSocial() %></td>
				<td><%= c.getTelefono() %></td>
				<td style="display: none;"><%= c.getId() %></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="editMode()">Editar</button>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="deleteMode()">Eliminar</button>
			</tr>
			<% } %>

		</table>
	</div>

	<div class="modal" id="myModal">
		<div class="modal-dialog modal-fullscreen-xxl-down">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="modalHead">Registrar Cliente</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>


				<form method="post" action="Clientes">
					<!-- Modal body -->
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="idusuario"
							placeholder="id" name="idusuario" readonly> <label
							for="idusuario">ID Usuario</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="nombre"
							placeholder="Nombre" name="nombre" required> <label
							for="nombre">Nombre</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="apellido"
							placeholder="Apellido" name="apellido" required> <label
							for="apellido">Apellido</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="email" class="form-control" id="email"
							placeholder="E-mail" name="email" required> <label
							for="email">Correo Electrónico</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="password" class="form-control" id="password"
							placeholder="Contraseña" name="password" required> <label
							for="password">Contraseña</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="number" class="form-control" id="cuil"
							placeholder="Cuil" name="cuil" required> <label
							for="cuil">Cuil</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="razon_social"
							placeholder="Razon Social" name="razon_social" required>
						<label for="razon_social">Razon Social</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="telefono"
							placeholder="Telefono" name="telefono" required> <label
							for="telefono">Teléfono</label>
					</div>

					<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
					<select name="accion" id="accion" style="display: none;">
						<option>Registrar</option>
						<option>Editar</option>
						<option>Eliminar</option>
					</select>
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

	<script type="text/javascript">
			var table1=document.getElementById('tab_clientes'), rIndex1;
			
			for (i = 0; i < table1.rows.length; i++) {
			    table1.rows[i].onclick = function () {
			        rIndex1 = this.rowsIndex;
			        document.getElementById('idusuario').value = this.cells[7].innerHTML;
			        document.getElementById('nombre').value = this.cells[1].innerHTML;
			        document.getElementById('apellido').value = this.cells[2].innerHTML;
			        document.getElementById('email').value = this.cells[3].innerHTML;
			        document.getElementById('password').value = this.cells[4].innerHTML;
			        document.getElementById('cuil').value = this.cells[0].innerHTML;
			        document.getElementById('razon_social').value = this.cells[5].innerHTML;
			        document.getElementById('telefono').value = this.cells[6].innerHTML;
			    };
			}
			 	
		 	function regMode(){
		 		document.getElementById('modalHead').innerHTML="Registrar Cliente";
		 		document.getElementById('btn').className="btn btn-success";
		 		document.getElementById('btn').innerHTML="Registrar";
		 		document.getElementById('accion').selectedIndex=0;
		 		
		 	    document.getElementById('nombre').removeAttribute("readonly"  , false);
		 	    document.getElementById('apellido').removeAttribute("readonly"  , false);
		 	    document.getElementById('email').removeAttribute("readonly"  , false);
		 	    document.getElementById('password').removeAttribute("readonly"  , false);
		 	    document.getElementById('cuil').removeAttribute("readonly"  , false);
		 	    document.getElementById('razon_social').removeAttribute("readonly"  , false);
		 	    document.getElementById('telefono').removeAttribute("readonly"  , false);
		 	    
		 	    
		 		document.getElementById('idusu').value = null;
		 	    document.getElementById('nombre').value = null;
		 	    document.getElementById('apellido').value = null;
		 	    document.getElementById('email').value = null;
		 	    document.getElementById('password').value = null;
		 	    document.getElementById('cuil').value = null;
		 	    document.getElementById('razon_social').value = null;
		 	    document.getElementById('telefono').value = null;
		 	}
		 	
		 	function editMode(){
		 		document.getElementById('modalHead').innerHTML="Editar datos del cliente";
		 		document.getElementById('btn').className="btn btn-success";
		 		document.getElementById('btn').innerHTML="Guardar";
		 		document.getElementById('accion').selectedIndex=1;
		 		
		 		document.getElementById('nombre').removeAttribute("readonly"  , false);
		 	    document.getElementById('apellido').removeAttribute("readonly"  , false);
		 	    document.getElementById('email').removeAttribute("readonly"  , false);
		 	    document.getElementById('password').removeAttribute("readonly"  , false);
		 	    document.getElementById('cuil').removeAttribute("readonly"  , false);
		 	    document.getElementById('razon_social').removeAttribute("readonly"  , false);
		 	    document.getElementById('telefono').removeAttribute("readonly"  , false);	 		
		 	}
		 	
		 	function deleteMode(){
		 		document.getElementById('modalHead').innerHTML="Eliminar cliente";
		 		document.getElementById('btn').className="btn btn-danger";
		 		document.getElementById('btn').innerHTML="Eliminar";
		 		document.getElementById('accion').selectedIndex=2;
		 		
		 		document.getElementById('nombre').setAttribute("readonly" , "readonly" , false);
		 	    document.getElementById('apellido').setAttribute("readonly" , "readonly" , false);
		 	    document.getElementById('email').setAttribute("readonly" , "readonly" , false);
		 	    document.getElementById('password').setAttribute("readonly" , "readonly" , false);
		 	    document.getElementById('cuil').setAttribute("readonly" , "readonly" , false);
		 	    document.getElementById('razon_social').setAttribute("readonly" , "readonly" , false);
		 	    document.getElementById('telefono').setAttribute("readonly" , "readonly" , false);	 	    
		 	}
		</script>

</body>
</html>