<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Materiales</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="entidades.Material"%>
	<%@page import="entidades.Proveedor"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.HashMap"%>

	<div class="container mt-3">
		<br>
		<h1 class="text-center">Materiales</h1>
		<br>
		<div>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#myModal" onClick="regMode()">Registrar
				Material</button>
		</div>
		<br>
		<% ArrayList<Proveedor> provs=(ArrayList<Proveedor>)request.getAttribute("provedores"); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_materiales">
			<th>Nombre del producto</th>
			<th>Provedor</th>
			<th>Precio</th>
			<th></th>
			<th></th>
			<% if(provs!=null) { %>
			<% for(Proveedor p: provs){ %>
			<% for(Material m: p.getMateriales()){ %>
			<tr>
				<td style="display: none;"><%= m.getId_material() %></td>
				<td><%= m.getDescripcion() %></td>
				<td value=<%= p.getIdProveedor() %>><%= p.getRazonSocial() %></td>
				<td><%= m.getPrecio() %></td>
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
					<h4 class="modal-title" id="modalHead">Registrar Material</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>


				<form method="post" action="Materiales">
					<!-- Modal body -->
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="idmaterial"
							placeholder="idmaterial" name="idmaterial" readonly> <label
							for="idprov">ID Material</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="descripcionmaterial"
							placeholder="descripcionmaterial" name="descripcionmaterial"
							required> <label for="razonsocial">Descripcion</label>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="number" class="form-control" id="preciomaterial"
							placeholder="preciomaterial" name="preciomaterial" required>
						<label for="direccion">Precio</label>
					</div>
					<label for="idprovedor">Provedor</label> <select id="idprovedor"
						name="idprovedor">
						<% for(Proveedor p:provs){ %>
						<option value=<%=p.getIdProveedor() %>><%=p.getRazonSocial() %></option>
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
var table = document.getElementById('tab_materiales'), rIndex;
for (i = 0; i < table.rows.length; i++) {
    table.rows[i].onclick = function () {
        rIndex = this.rowsIndex;
        document.getElementById('idmaterial').value = this.cells[0].innerHTML;
        document.getElementById('descripcionmaterial').value = this.cells[1].innerHTML;
        document.getElementById('preciomaterial').value = this.cells[3].innerHTML;
        for (x = 0; x < document.getElementById('idprovedor').options.length; x++) {
                if (this.cells[2].innerHTML == document.getElementById('idprovedor').options[x].innerHTML) {
                    document.getElementById('idprovedor').selectedIndex = x;
                    break;
                }
            }

    };
}

function regMode(){
	document.getElementById('modalHead').innerHTML="Registrar Material";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Registrar";
	document.getElementById('accion').selectedIndex=0;
	
    document.getElementById('descripcionmaterial').removeAttribute("readonly"  , false);
    document.getElementById('preciomaterial').removeAttribute("readonly"  , false);
    //document.getElementById('idprovedor').removeAttribute("readonly"  , false);
    for (x = 0; x < document.getElementById('idprovedor').options.length; x++){
    	document.getElementById('idprovedor').options[x].disabled=false;
    }
    
	document.getElementById('idmaterial').value = null;
    document.getElementById('descripcionmaterial').value = null;
    document.getElementById('preciomaterial').value = null;
    document.getElementById('idprovedor').selectedIndex=0;
}
function editMode(){
	document.getElementById('modalHead').innerHTML="Editar datos del material";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Guardar";
	
	document.getElementById('descripcionmaterial').removeAttribute("readonly"  , false);
    document.getElementById('preciomaterial').removeAttribute("readonly"  , false);
    //document.getElementById('idprovedor').removeAttribute("readonly"  , false);
    for (x = 0; x < document.getElementById('idprovedor').options.length; x++){
    	document.getElementById('idprovedor').options[x].disabled=false;
    }
	
	document.getElementById('accion').selectedIndex=1;
}
function deleteMode(){
	document.getElementById('modalHead').innerHTML="Eliminar Material";
	document.getElementById('btn').className="btn btn-danger";
	document.getElementById('btn').innerHTML="Eliminar";
	
	document.getElementById('descripcionmaterial').setAttribute("readonly" , "readonly" , false);
    document.getElementById('preciomaterial').setAttribute("readonly" , "readonly" , false);
    //document.getElementById('idprovedor').setAttribute("readonly" , "readonly" , false);
    var selected=document.getElementById('idprovedor').selectedIndex;
    for (x = 0; x < document.getElementById('idprovedor').options.length; x++){
    	if(x!=selected){
    		document.getElementById('idprovedor').options[x].setAttribute("disabled","disabled",false);
    	}
    }
	
	document.getElementById('accion').selectedIndex=2;
}

</script>

</body>
</html>