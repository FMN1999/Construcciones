<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Provedores</title>
</head>
<body>
<jsp:include page="Shared.jsp"></jsp:include>
<%@page import="entidades.Proveedor" %>
<%@page import="java.util.ArrayList" %>
<div class="container mt-3">
<br>
	<h1 class="text-center">Listado de provedores</h1>
	<br>
	<div>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" onClick="regMode()">
	    Registrar Provedor
	    </button>
    </div>
	<br>
	<table class="table table-dark table-hover" id="provedores">
		<th>Razon social</th>
		<th>Direccion</th>
		<th>Telefono</th>
		<th></th>
		<th></th>
		
		<% for(Proveedor prov: (ArrayList<Proveedor>)request.getAttribute("provedores")){ %>
		<tr>
			<td style="display:none;"><%=prov.getIdProveedor() %></td>
			<td><%=prov.getRazonSocial() %></td>
			<td><%=prov.getDireccion() %></td>
			<td><%=prov.getTelefono() %></td>
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
        <h4 class="modal-title" id="modalHead">Registrar Proveedor</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      
      <form method="post" action="Proveedores" >
      	  <!-- Modal body -->
	      <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="idprov" placeholder="id" name="idprov" readonly>
			  <label for="idprov">ID PROVEDOR</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="razonsocial" placeholder="Razon social" name="razonsocial" required>
			  <label for="razonsocial">Razon Social</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="direccion" placeholder="Direccion" name="direccion" required>
			  <label for="direccion">Direccion</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="number" class="form-control" id="telefono" placeholder="Telefono" name="telefono" required>
			  <label for="idprov">Telefono</label>
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
var table = document.getElementById('provedores'), rIndex;
for (i = 0; i < table.rows.length; i++) {
    table.rows[i].onclick = function () {
        rIndex = this.rowsIndex;
        document.getElementById('idprov').value = this.cells[0].innerHTML;
        document.getElementById('razonsocial').value = this.cells[1].innerHTML;
        document.getElementById('direccion').value = this.cells[2].innerHTML;
        document.getElementById('telefono').value = this.cells[3].innerHTML;

    };
}

function regMode(){
	document.getElementById('modalHead').innerHTML="Registrar Provedor";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Registrar";
	document.getElementById('accion').selectedIndex=0;
	
    document.getElementById('razonsocial').removeAttribute("readonly"  , false);
    document.getElementById('direccion').removeAttribute("readonly"  , false);
    document.getElementById('telefono').removeAttribute("readonly"  , false);
    
	document.getElementById('idprov').value = null;
    document.getElementById('razonsocial').value = null;
    document.getElementById('direccion').value = null;
    document.getElementById('telefono').value = null;
}
function editMode(){
	document.getElementById('modalHead').innerHTML="Editar datos de Provedor";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Guardar";
	
	document.getElementById('razonsocial').removeAttribute("readonly"  , false);
    document.getElementById('direccion').removeAttribute("readonly"  , false);
    document.getElementById('telefono').removeAttribute("readonly"  , false);
	
	document.getElementById('accion').selectedIndex=1;
}
function deleteMode(){
	document.getElementById('modalHead').innerHTML="Eliminar Provedor";
	document.getElementById('btn').className="btn btn-danger";
	document.getElementById('btn').innerHTML="Eliminar";
	
	document.getElementById('razonsocial').setAttribute("readonly" , "readonly" , false);
    document.getElementById('direccion').setAttribute("readonly" , "readonly" , false);
    document.getElementById('telefono').setAttribute("readonly" , "readonly" , false);
	
	document.getElementById('accion').selectedIndex=2;
}

</script>

</body>
</html>