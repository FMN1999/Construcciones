<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Tipo_Tarea</title>
</head>
<body>
<jsp:include page="Shared.jsp"></jsp:include>
<%@page import="entidades.Tipo_Tarea" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>

<div class="container mt-3">
	<br>
	<h1 class="text-center">Tipos De Tarea</h1>
	<br>
	<div>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" onClick="regMode()">
	    Registrar Tipo de Tarea
	    </button>
	</div>
	<br>
	<% ArrayList<Tipo_Tarea> tps=(ArrayList<Tipo_Tarea>)request.getAttribute("tipos_tarea"); %>
	<table class="table table-dark table-hover" id="tab_tipos_tarea">
		<th>Descripcion tipo tarea</th>
		<th>Precio</th>
		<th></th>
		<th></th>
		<% if(tps!=null) { %>
			<% for(Tipo_Tarea tt: tps){ %>
			 	<tr>
			 		<td style="display:none;"><%= tt.getId_tipo_tarea() %></td>
			 		<td><%= tt.getDescripcion() %></td>
			 		<td><%= tt.getPrecio() %></td>
			 		<td><button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal" onClick="editMode()">Editar</button>
					<td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#myModal" onClick="deleteMode()">Eliminar</button>
			 	</tr>
		 		<% } %>
			<% } %>
	</table>
</div>

<div class="modal" id="myModal">
  <div class="modal-dialog modal-fullscreen-xxl-down">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="modalHead">Registrar Tipo de Tarea</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      
      <form method="post" action="Tipo_Tareas" >
      	  <!-- Modal body -->
	      <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="idtipotarea" placeholder="idtipotarea" name="idtipotarea" readonly>
			  <label for="idprov">ID TipoTarea</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="descripciontipotarea" placeholder="descripciontipotarea" name="descripciontipotarea" required>
			  <label for="razonsocial">Descripcion</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="number" class="form-control" id="preciotipotarea" placeholder="preciotipotarea" name="preciotipotarea" required>
			  <label for="direccion">Precio</label>
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
var table = document.getElementById('tab_tipos_tarea'), rIndex;
for (i = 0; i < table.rows.length; i++) {
    table.rows[i].onclick = function () {
        rIndex = this.rowsIndex;
        document.getElementById('idtipotarea').value = this.cells[0].innerHTML;
        document.getElementById('descripciontipotarea').value = this.cells[1].innerHTML;
        document.getElementById('preciotipotarea').value = this.cells[3].innerHTML;

    };
}

function regMode(){
	document.getElementById('modalHead').innerHTML="Registrar Tipo de Tarea";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Registrar";
	document.getElementById('accion').selectedIndex=0;
	
    document.getElementById('descripciontipotarea').removeAttribute("readonly"  , false);
    document.getElementById('preciotipotarea').removeAttribute("readonly"  , false);
    
	document.getElementById('idtipotarea').value = null;
    document.getElementById('descripciontipotarea').value = null;
    document.getElementById('preciotipotarea').value = null;
}
function editMode(){
	document.getElementById('modalHead').innerHTML="Editar datos del Tipo de Tarea";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Guardar";
	
	document.getElementById('descripciontipotarea').removeAttribute("readonly"  , false);
    document.getElementById('preciotipotarea').removeAttribute("readonly"  , false);
	
	document.getElementById('accion').selectedIndex=1;
}
function deleteMode(){
	document.getElementById('modalHead').innerHTML="Eliminar Tipo de Tarea";
	document.getElementById('btn').className="btn btn-danger";
	document.getElementById('btn').innerHTML="Eliminar";
	
	document.getElementById('descripciontipotarea').setAttribute("readonly" , "readonly" , false);
    document.getElementById('preciotipotarea').setAttribute("readonly" , "readonly" , false);
	
	document.getElementById('accion').selectedIndex=2;
}

</script>

</body>
</html>