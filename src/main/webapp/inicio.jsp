<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.84.0">
<title>Inicio</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/sign-in/">



<!-- Bootstrap core CSS -->
<link href="assets/dist/css/bootstrap.min.css" rel="stylesheet">

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


<!-- Custom styles for this template -->
<link href="assets/locals/inicio.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
		<form method="post" action="ServLogin">
			<img class="mb-4" src="assets/brand/ctrsite.png" alt="" width="72"
				height="57">
			<h1 class="h3 mb-3 fw-normal">Inicie Sesion</h1>

			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput"
					name="correo" placeholder="name@example.com"> <label
					for="floatingInput">Correo electronico</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					name="contra" placeholder="Password"> <label
					for="floatingPassword">Contraseña</label>
			</div>

			<% String error=(String)request.getAttribute("error"); %>
			<% if(error!=null){ %>
			<div class="alert alert-danger">
				<strong>ERROR</strong>
				<p><%=error %></p>
			</div>
			<% } %>
			<button class="w-100 btn btn-lg btn-primary" type="submit" style="margin: 5px" onClick="nuevoMode()">Iniciar sesion</button>
			<button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin: 5px" onClick="registrarMode()">Registrarse</button>
			<p class="mt-5 mb-3 text-muted">&copy; 2021</p>
			
			<select name="accion" id="accion" style="display: none;">
						<option>Ingresar</option>
						<option>Registrar</option>
			</select>
		</form>
		
	</main>
	<script type ="text/javascript">
		function nuevoMode(){
			document.getElementById('accion').selectedIndex=0;
		}
		
		function registrarMode(){
			document.getElementById('accion').selectedIndex=1;
		}
	</script>
</body>
</html>