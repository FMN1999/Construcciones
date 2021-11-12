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
	<h1>Listado de provedores</h1>
	
	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
    Registrar Provedor
    </button>
	
	<table class="table table-dark table-hover">
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
			<td><button type="button" class="btn btn-success">Editar</button>
			<td><button type="button" class="btn btn-danger">Eliminar</button>
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

      
      <form method="post" action="Proveedores">
      	  <!-- Modal body -->
	      <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="idprov" placeholder="id" name="idprov" readonly>
			  <label for="idprov">ID PROVEDOR</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="razonsocial" placeholder="Razon social" name="razonsocial">
			  <label for="razonsocial">Razon Social</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="direccion" placeholder="Direccion" name="direccion">
			  <label for="direccion">Direccion</label>
		  </div>
		  <div class="form-floating mb-3 mt-3">
			  <input type="text" class="form-control" id="telefono" placeholder="Telefono" name="telefono">
			  <label for="idprov">Telefono</label>
		  </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">Registrar</button>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
	      </div>
      </form>

    </div>
  </div>
</div>



</body>
</html>