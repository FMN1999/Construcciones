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
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" onClick="regMode()">
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
			<th style="display:none;">Password</th>
			<th>Fecha de nacimiento</th>
			<th>Disponibilidad</th>
			<th style="display:none;">ID</th>
			<th></th>
			<th></th>
			<% for(Trabajador t: ofs){ %>
				<tr>
					<td><%= t.getCuil() %></td>
					<td><%= t.getTipo_doc() %></td>
					<td><%= t.getN_doc() %></td>
					<td><%= t.getNombre() %></td>
					<td><%= t.getApellido() %></td>
					<td><%= t.getEmail() %></td>
					<td style="display:none;"><%= t.getPassword() %></td>
					<td><%= t.getFechaNac() %></td>
					<% if(t.isDisponible()){ %>
						<td class="text-success"><%= "Disponible" %></td>
					<% } else{  %>
						<td class="text-danger"><%= "No Disponible" %></td>
					<% } %>
					<td style="display:none;"><%= t.getId() %></td>
					<td><button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal" onClick="editMode()">Editar</button>
					<td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#myModal" onClick="deleteMode()">Eliminar</button>
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
			<th style="display:none;">Password</th>
			<th>Fecha de nacimiento</th>
			<th>Disponibilidad</th>
			<th style="display:none;">ID</th>
			<th></th>
			<th></th>
			<% for(Trabajador t: obs){ %>
				<tr>
					<td><%= t.getCuil() %></td>
					<td><%= t.getTipo_doc() %></td>
					<td><%= t.getN_doc() %></td>
					<td><%= t.getNombre() %></td>
					<td><%= t.getApellido() %></td>
					<td><%= t.getEmail() %></td>
					<td style="display:none;"><%= t.getPassword() %></td>
					<td><%= t.getFechaNac() %></td>
					<% if(t.isDisponible()){ %>
						<td class="text-success"><%= "Disponible" %></td>
					<% } else{  %>
						<td class="text-danger"><%= "No Disponible" %></td>
					<% } %>
					<td style="display:none;"><%= t.getId() %></td>
					<td><button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal" onClick="editMode()">Editar</button>
					<td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#myModal" onClick="deleteMode()">Eliminar</button>
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

      
      <form method="post" action="Empleados" >
      	  <!-- Modal body -->
	      <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="idusu" placeholder="id" name="idusu" readonly>
			  <label for="idusu">ID Usuario</label>
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
		  <div class="form-floating mb-3 mt-3">
		  	<label for="disponible">Disponibilidad</label>
			  <select id="disponible" name="disponible">
			  	<option>Disponible</option>
			  	<option>No Disponible</option>
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

<script type="text/javascript">
	var table1=document.getElementById('tab_oficiales'), rIndex1;
	var table2=document.getElementById('tab_obreros'), rIndex2;
	
	for (i = 0; i < table1.rows.length; i++) {
	    table1.rows[i].onclick = function () {
	        rIndex1 = this.rowsIndex;
	        document.getElementById('idusu').value = this.cells[9].innerHTML;
	        document.getElementById('nombre').value = this.cells[3].innerHTML;
	        document.getElementById('apellido').value = this.cells[4].innerHTML;
	        document.getElementById('email').value = this.cells[5].innerHTML;
	        document.getElementById('password').value = this.cells[6].innerHTML;
	        document.getElementById('cuil').value = this.cells[0].innerHTML;
	        for (x = 0; x < document.getElementById('tipodoc').options.length; x++) {
                if (this.cells[1].innerHTML == document.getElementById('tipodoc').options[x].innerHTML) {
                    document.getElementById('tipodoc').selectedIndex = x;
                    break;
                }
            }
	        document.getElementById('ndoc').value = this.cells[2].innerHTML;
	        document.getElementById('fnac').value = this.cells[7].innerHTML;
	        document.getElementById('tipo_e').selectedIndex = 0;
	        for (x = 0; x < document.getElementById('disponible').options.length; x++) {
                if (this.cells[8].innerHTML == document.getElementById('disponible').options[x].innerHTML) {
                    document.getElementById('disponible').selectedIndex = x;
                    break;
                }
            }
	    };
	}
	
	for (i = 0; i < table2.rows.length; i++) {
		table2.rows[i].onclick = function () {
			rIndex2 = this.rowsIndex;
			document.getElementById('idusu').value = this.cells[9].innerHTML;
	        document.getElementById('nombre').value = this.cells[3].innerHTML;
	        document.getElementById('apellido').value = this.cells[4].innerHTML;
	        document.getElementById('email').value = this.cells[5].innerHTML;
	        document.getElementById('password').value = this.cells[6].innerHTML;
	        document.getElementById('cuil').value = this.cells[0].innerHTML;
	        for (x = 0; x < document.getElementById('tipodoc').options.length; x++) {
                if (this.cells[1].innerHTML == document.getElementById('tipodoc').options[x].innerHTML) {
                    document.getElementById('tipodoc').selectedIndex = x;
                    break;
                }
            }
	        document.getElementById('ndoc').value = this.cells[2].innerHTML;
	        document.getElementById('fnac').value = this.cells[7].innerHTML;
	        document.getElementById('tipo_e').selectedIndex = 1;
	        for (x = 0; x < document.getElementById('disponible').options.length; x++) {
                if (this.cells[8].innerHTML == document.getElementById('disponible').options[x].innerHTML) {
                    document.getElementById('disponible').selectedIndex = x;
                    break;
                }
            }
	 	};
	}
	 	
	 	function regMode(){
	 		document.getElementById('modalHead').innerHTML="Registrar Empleado";
	 		document.getElementById('btn').className="btn btn-success";
	 		document.getElementById('btn').innerHTML="Registrar";
	 		document.getElementById('accion').selectedIndex=0;
	 		
	 	    document.getElementById('nombre').removeAttribute("readonly"  , false);
	 	    document.getElementById('apellido').removeAttribute("readonly"  , false);
	 	    document.getElementById('email').removeAttribute("readonly"  , false);
	 	    document.getElementById('password').removeAttribute("readonly"  , false);
	 	    document.getElementById('cuil').removeAttribute("readonly"  , false);
	 	    document.getElementById('ndoc').removeAttribute("readonly"  , false);
	 	    document.getElementById('fnac').removeAttribute("readonly"  , false);
	 	    for (x = 0; x < document.getElementById('tipodoc').options.length; x++){
	 	    	document.getElementById('tipodoc').options[x].disabled=false;
	 	    };
	 	   for (x = 0; x < document.getElementById('disponible').options.length; x++){
	 	    	document.getElementById('disponible').options[x].disabled=false;
	 	    };
	 	   for (x = 0; x < document.getElementById('tipo_e').options.length; x++){
	 	    	document.getElementById('tipo_e').options[x].disabled=false;
	 	    };
	 	    
	 		document.getElementById('idusu').value = null;
	 	    document.getElementById('nombre').value = null;
	 	    document.getElementById('apellido').value = null;
	 	    document.getElementById('email').value = null;
	 	    document.getElementById('password').value = null;
	 	    document.getElementById('cuil').value = null;
	 	    document.getElementById('tipodoc').selectedIndex=0;
	 	    document.getElementById('ndoc').value = null;
	 	}
	 	
	 	function editMode(){
	 		document.getElementById('modalHead').innerHTML="Editar datos del empleado";
	 		document.getElementById('btn').className="btn btn-success";
	 		document.getElementById('btn').innerHTML="Guardar";
	 		document.getElementById('accion').selectedIndex=1;
	 		
	 		document.getElementById('nombre').removeAttribute("readonly"  , false);
	 	    document.getElementById('apellido').removeAttribute("readonly"  , false);
	 	    document.getElementById('email').removeAttribute("readonly"  , false);
	 	    document.getElementById('password').removeAttribute("readonly"  , false);
	 	    document.getElementById('cuil').removeAttribute("readonly"  , false);
	 	    document.getElementById('ndoc').removeAttribute("readonly"  , false);
	 	    document.getElementById('fnac').removeAttribute("readonly"  , false);
	 	    for (x = 0; x < document.getElementById('tipodoc').options.length; x++){
	 	    	document.getElementById('tipodoc').options[x].disabled=false;
	 	    };
	 	   for (x = 0; x < document.getElementById('disponible').options.length; x++){
	 	    	document.getElementById('disponible').options[x].disabled=false;
	 	    };
	 	   for (x = 0; x < document.getElementById('tipo_e').options.length; x++){
	 	    	document.getElementById('tipo_e').options[x].disabled=false;
	 	    };
	 		
	 		
	 	}
	 	
	 	function deleteMode(){
	 		document.getElementById('modalHead').innerHTML="Eliminar empleado";
	 		document.getElementById('btn').className="btn btn-danger";
	 		document.getElementById('btn').innerHTML="Eliminar";
	 		document.getElementById('accion').selectedIndex=2;
	 		
	 		document.getElementById('nombre').setAttribute("readonly" , "readonly" , false);
	 	    document.getElementById('apellido').setAttribute("readonly" , "readonly" , false);
	 	    document.getElementById('email').setAttribute("readonly" , "readonly" , false);
	 	    document.getElementById('password').setAttribute("readonly" , "readonly" , false);
	 	    document.getElementById('cuil').setAttribute("readonly" , "readonly" , false);
	 	    document.getElementById('ndoc').setAttribute("readonly" , "readonly" , false);
	 	    document.getElementById('fnac').setAttribute("readonly" , "readonly" , false);
	 	    for (x = 0; x < document.getElementById('tipodoc').options.length; x++){
	 	    	document.getElementById('tipodoc').options[x].setAttribute("disabled","disabled",false);
	 	    }
	 	    for (x = 0; x < document.getElementById('disponible').options.length; x++){
	 	    	document.getElementById('disponible').options[x].setAttribute("disabled","disabled",false);
	 	    }
	 	    for (x = 0; x < document.getElementById('tipo_e').options.length; x++){
	 	    	document.getElementById('tipo_e').options[x].setAttribute("disabled","disabled",false);
	 	    }
	 		
	 	}
</script>
	
</body>
</html>