<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Maquinarias</title>
</head>
<body>

<jsp:include page="Shared.jsp"></jsp:include>
<%@page import="entidades.Maquinaria" %>
<%@page import="java.util.ArrayList" %>
<div class="container mt-3">
<br>
	<h1 class="text-center">Listado de maquinarias</h1>
	<br>
	<div>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" onClick="regMode()">
	    Registrar Maquinaria
	    </button>
    </div>
	<br>
	<table class="table table-dark table-hover" id="maquinarias">
		<th>Descripcion</th>
		<th>Precio por Hora</th>
		<th></th>
		<th></th>
		
		<% for(Maquinaria maq: (ArrayList<Maquinaria>)request.getAttribute("maquinarias")){ %>
		<tr>
			<td style="display:none;"><%=maq.getIdMaquina() %></td>
			<td><%=maq.getDescripcion() %></td>
			<td><%=maq.getPrecioHora() %></td>
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
        <h4 class="modal-title" id="modalHead">Registrar Maquinaria</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      
      <form method="post" action="Maquinarias" >
      	  <!-- Modal body -->
	      <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="idmaq" placeholder="id" name="idmaq" readonly>
			  <label for="idmaq">ID MAQUINARIA</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="descripcion" placeholder="Descripcion" name="descripcion" required>
			  <label for="descripcion">Descripcion</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="precioHora" placeholder="Precio por Hora" name="precioHora" required>
			  <label for="precioHora">Precio por hora</label>
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
var table = document.getElementById('maquinarias'), rIndex;
for (i = 0; i < table.rows.length; i++) {
    table.rows[i].onclick = function () {
        rIndex = this.rowsIndex;
        document.getElementById('idmaq').value = this.cells[0].innerHTML;
        document.getElementById('descripcion').value = this.cells[1].innerHTML;
        document.getElementById('precioHora').value = this.cells[2].innerHTML;

    };
}

function regMode(){
	document.getElementById('modalHead').innerHTML="Registrar Maquinaria";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Registrar";
	document.getElementById('accion').selectedIndex=0;
	
    document.getElementById('descripcion').removeAttribute("readonly"  , false);
    document.getElementById('precioHora').removeAttribute("readonly"  , false);
    
	document.getElementById('idmaq').value = null;
    document.getElementById('descripcion').value = null;
    document.getElementById('precioHora').value = null;
}
function editMode(){
	document.getElementById('modalHead').innerHTML="Editar datos de Maquinaria";
	document.getElementById('btn').className="btn btn-success";
	document.getElementById('btn').innerHTML="Guardar";
	
	document.getElementById('descripcion').removeAttribute("readonly"  , false);
    document.getElementById('precioHora').removeAttribute("readonly"  , false);
	
	document.getElementById('accion').selectedIndex=1;
}
function deleteMode(){
	document.getElementById('modalHead').innerHTML="Eliminar Maquinaria";
	document.getElementById('btn').className="btn btn-danger";
	document.getElementById('btn').innerHTML="Eliminar";
	
	document.getElementById('descripcion').setAttribute("readonly" , "readonly" , false);
    document.getElementById('precioHora').setAttribute("readonly" , "readonly" , false);
	
	document.getElementById('accion').selectedIndex=2;
}

</script>


</body>
</html>