<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<title>Registrar Usuario</title>

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
</head>

<body>
	<form method="post" action="Registrarse" class="was-validated">
		<!-- Modal body -->
		<div class="container mt-3">
		<div class="text-center">
		<img class="mb-4" src="assets/brand/ctrsite.png" alt="" width="72"
				height="57"></div>
		<div class="form-floating mb-3">
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
			<label for="razon_social">Razón Social</label>
		</div>
		<div class="form-floating mb-3 mt-3">
			<input type="text" class="form-control" id="telefono"
				placeholder="Telefono" name="telefono" required> <label
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
		</div>
	</form>
	
</body>
</html>