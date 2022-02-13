<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Tarea</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="entidades.Tarea"%>
	<%@page import="entidades.Tipo_Tarea"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.HashMap"%>

	<div class="container mt-3">
		<br>
		<h1 class="text-center">Tareas</h1>
		<br>
		<div>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#myModal" onClick="regMode()">Registrar
				Tareas</button>
		</div>
		<br>
		<% ArrayList<Tipo_Tarea> tipos_tarea=(ArrayList<Tipo_Tarea>)request.getAttribute("tipos"); %>
		<table class="table table-dark table-hover" id="tab_tareas">
			<th>Descripcion</th>
			<th>Cant_m2</th>
			<th>Tipo</th>
			<th></th>
			<th></th>
			<% if(tipos_tarea!=null) { %>
			<% for(Tipo_Tarea tt: tipos_tarea){ %>
			<% for(Tarea tarea: tt.getTareas()){ %>
			<tr>
				<td style="display: none;"><%= tarea.getIdTarea() %></td>
				<td><%= tarea.getDescripcion() %></td>
				<td><%= tarea.getCant_m2() %></td>
				<td value=<%= tarea.getId_tipo_tarea() %>><%= tt.getDescripcion() %></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="editMode()">Editar</button>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="deleteMode()">Eliminar</button>
			</tr>
			<% } %>
			<% } %>
			<% } %>
		</table>
	</div>

	<div class="modal" id="myModal">
		<div class="modal-dialog modal-fullscreen-xxl-down">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="modalHead">Registrar Tareas</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>


				<form method="post" action="Tareas">
					<!-- Modal body -->
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="idtarea"
							placeholder="idtarea" name="idtarea" readonly> <label
							for="idprov">ID Tarea</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="descripciontarea"
							placeholder="descripciontarea" name="descripciontarea"
							required> <label for="razonsocial">Descripcion</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="number" class="form-control" id="cantm2tarea"
							placeholder="cantm2tarea" name="cantm2tarea" required>
						<label for="cantm2tarea">Cant M2</label>
					</div>
					<label for="tipotarea">Tipo de tarea</label> 
					<select id="tipotarea" name="tipotarea">
					<% for(Tipo_Tarea tt: tipos_tarea){ %>
						<option value=<%=tt.getId_tipo_tarea() %>><%= tt.getDescripcion() %></option>
						<% } %>	
					</select>

					<!--input type="text" name="accion" id="accion" value="registrar" style="display:none;"-->
					<select name="accion" id="accion" style="display: none;">
						<option>Registrar</option>
						<option>Editar</option>
						<option>Eliminar</option>
					</select>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="sumbit" class="btn btn-primary" id="btn">Registrar</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">Close</button>
					</div>
				</form>

			</div>
		</div>
	</div>

	<script type="text/javascript">
var table = document.getElementById('tab_tareas'), rIndex;
for (i = 0; i < table.rows.length; i++) {
    table.rows[i].onclick = function () {
        rIndex = this.rowsIndex;
        document.getElementById('idtarea').value = this.cells[0].innerHTML;
        document.getElementById('descripciontarea').value = this.cells[1].innerHTML;
        document.getElementById('cantm2tarea').value = this.cells[2].innerHTML;
        for (x = 0; x < document.getElementById('tipotarea').options.length; x++) {
            if (this.cells[3].innerHTML == document.getElementById('tipotarea').options[x].innerHTML) {
                document.getElementById('tipotarea').selectedIndex = x;
                break;
            }
        }
    };
}

function regMode(){
	document.getElementById('modalHead').innerHTML="Registrar Tarea";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Registrar";
	document.getElementById('accion').selectedIndex=0;
	
    document.getElementById('descripciontarea').removeAttribute("readonly"  , false);
    document.getElementById('cantm2tarea').removeAttribute("readonly"  , false);
    for (x = 0; x < document.getElementById('tipotarea').options.length; x++){
    	document.getElementById('tipotarea').options[x].disabled=false;
    }
    
	document.getElementById('idtarea').value = null;
    document.getElementById('descripciontarea').value = null;
    document.getElementById('cantm2tarea').value = null;
    document.getElementById('tipotarea').selectedIndex=0;
}
function editMode(){
	document.getElementById('modalHead').innerHTML="Editar datos de la Tarea";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Guardar";
	
	document.getElementById('descripciontarea').removeAttribute("readonly"  , false);
    document.getElementById('cantm2tarea').removeAttribute("readonly"  , false);
    for (x = 0; x < document.getElementById('tipotarea').options.length; x++){
    	document.getElementById('tipotarea').options[x].disabled=false;
    }
	
	document.getElementById('accion').selectedIndex=1;
}
function deleteMode(){
	document.getElementById('modalHead').innerHTML="Eliminar Tipo de Tarea";
	document.getElementById('btn').className="btn btn-danger";
	document.getElementById('btn').innerHTML="Eliminar";
	
	document.getElementById('descripciontarea').setAttribute("readonly" , "readonly" , false);
    document.getElementById('cantm2tarea').setAttribute("readonly" , "readonly" , false);
    var selected=document.getElementById('tipotarea').selectedIndex;
    for (x = 0; x < document.getElementById('tipotarea').options.length; x++){
    	if(x!=selected){
    		document.getElementById('tipotarea').options[x].setAttribute("disabled","disabled",false);
    	}
    }
	
	document.getElementById('accion').selectedIndex=2;
}

</script>

</body>
</html>