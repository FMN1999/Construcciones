<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<title>Registrar Usuario</title>
</head>

<body>
	<form method="post" action="Registrarse">
		<!-- Modal body -->
		<div class="form-floating mb-3">
			<input type="text" class="form-control" id="idusuario"
				placeholder="id" name="idusuario" readonly> <label
				for="idusuario">ID Usuario</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="text" class="form-control" id="nombre"
				placeholder="Nombre" name="nombre"> <label
				for="nombre">Nombre</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="text" class="form-control" id="apellido"
				placeholder="Apellido" name="apellido"> <label
				for="apellido">Apellido</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="email" class="form-control" id="email"
				placeholder="E-mail" name="email"> <label
				for="email">Correo Electrónico</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="password" class="form-control" id="password"
				placeholder="Contraseña" name="password"> <label
				for="password">Contraseña</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="number" class="form-control" id="cuil"
				placeholder="Cuil" name="cuil"> <label
				for="cuil">Cuil</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="text" class="form-control" id="razon_social"
				placeholder="Razon Social" name="razon_social">
			<label for="razon_social">Razón Social</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="text" class="form-control" id="telefono"
				placeholder="Telefono" name="telefono"> <label
				for="telefono">Teléfono</label>
		</div>

		<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
		
		<!-- Modal footer -->
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary">Aceptar</button>
			<a href="ServLogin">
				<button type="button" class="btn btn-danger"> Cancelar </button>
			</a>
			
		</div>
	</form>
	
</body>
</html>