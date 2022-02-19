<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Obras</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="entidades.Obra"%>
	<%@page import="entidades.Cliente"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.HashMap"%>
	
	<div class="container mt-3">
		<br>
		<h1 class="text-center">Obras</h1>
		<br>
		<div>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#myModal" onClick="regMode()">Registrar
				Obra</button>
		</div>
		<br>
		<% ArrayList<Cliente> clientes=(ArrayList<Cliente>)request.getAttribute("clientes"); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_obras">
			<th>ID Obra</th>
			<th>Direccion</th>
			<th>Descripción</th>
			<th>Cliente</th>
			<th></th>
			<th></th>
			<th></th>
			
			<% if(clientes!=null) { %>
			<% for(Cliente c: clientes){ %>
			<% for(Obra o: c.getObras()){ %>
			<tr>
				
				<td><%= o.getIdObra() %></td>
				<td><%= o.getDireccion() %></td>
				<td><%= o.getDescripcion() %></td>
				<td value=<%= c.getIdCliente() %>><%= c.getRazonSocial() %></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="editMode()">Editar</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="deleteMode()">Eliminar</button></td>
				<td><form action="Presupuesto" method="get">
		    				<input id="idObra" name="idObra" style="display:None;" value=<%= o.getIdObra() %>>
		    				<button type="submit" class="btn btn-primary">Detalles</button>
		    		</form>
				</td>
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
					<h4 class="modal-title" id="modalHead">Registrar Obra</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>


				<form method="post" action="Obras">
					<!-- Modal body -->
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="idobra"
							placeholder="ID" name="idobra" readonly> <label
							for="idObra">ID Obra</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="direccion"
							placeholder="Direccion" name="direccion" required> <label
							for="direccion">Direccion</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="descripcion"
							placeholder="Descripcion" name="descripcion" required> <label
							for="descripcion">Descripción</label>
					</div>
					<label for="idcliente">Cliente</label> 
					<select id="idcliente" name="idcliente">
					<% for(Cliente c:clientes){ %>
						<option value=<%=c.getIdCliente()%>><%=c.getRazonSocial()%></option>
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
						<button type="submit" class="btn btn-primary" id="btn">Registrar</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">Close</button>
					</div>
				</form>

			</div>
		</div>
	</div>

	<script type="text/javascript">
var table = document.getElementById('tab_obras'), rIndex;
for (i = 0; i < table.rows.length; i++) {
    table.rows[i].onclick = function () {
        rIndex = this.rowsIndex;
        document.getElementById('idobra').value = this.cells[0].innerHTML;
        document.getElementById('direccion').value = this.cells[1].innerHTML;
        document.getElementById('descripcion').value = this.cells[2].innerHTML;
        for (x = 0; x < document.getElementById('idcliente').options.length; x++) {
            if (this.cells[3].innerHTML == document.getElementById('idcliente').options[x].innerHTML) {
                document.getElementById('idcliente').selectedIndex = x;
                break;
            }
        }
    };
}

function regMode(){
	document.getElementById('modalHead').innerHTML="Registrar Obra";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Registrar";
	document.getElementById('accion').selectedIndex=0;
	
    document.getElementById('direccion').removeAttribute("readonly"  , false);
    document.getElementById('descripcion').removeAttribute("readonly"  , false);
    for (x = 0; x < document.getElementById('idcliente').options.length; x++){
    	document.getElementById('idcliente').options[x].disabled=false;
    }
    
	document.getElementById('idobra').value = null;
    document.getElementById('direccion').value = null;
    document.getElementById('descripcion').value = null;
    document.getElementById('idcliente').selectedIndex=0;
}
function editMode(){
	document.getElementById('modalHead').innerHTML="Editar datos de Obra";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Guardar";
	
	
    document.getElementById('direccion').removeAttribute("readonly"  , false);
    document.getElementById('descripcion').removeAttribute("readonly"  , false);
    var selected=document.getElementById('idcliente').selectedIndex;
    for (x = 0; x < document.getElementById('idcliente').options.length; x++){
    	if(x!=selected){
    		document.getElementById('idcliente').options[x].setAttribute("disabled","disabled",false);
    	}
    }
	
	document.getElementById('accion').selectedIndex=1;
}
function deleteMode(){
	document.getElementById('modalHead').innerHTML="Eliminar Obra";
	document.getElementById('btn').className="btn btn-danger";
	document.getElementById('btn').innerHTML="Eliminar";
	
	document.getElementById('idobra').setAttribute("readonly" , "readonly" , false);
    document.getElementById('direccion').setAttribute("readonly" , "readonly" , false);
    document.getElementById('descripcion').setAttribute("readonly" , "readonly" , false);
    var selected=document.getElementById('idcliente').selectedIndex;
    for (x = 0; x < document.getElementById('idcliente').options.length; x++){
    	if(x!=selected){
    		document.getElementById('idcliente').options[x].setAttribute("disabled","disabled",false);
    	}
    }
	
	document.getElementById('accion').selectedIndex=2;
}

</script>

</body>
</html>