<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Vista de Presupuesto</title>
</head>
<body>
	<jsp:include page="Shared.jsp"></jsp:include>
	<%@page import="entidades.Obra"%>
	<%@ page import="java.util.Date" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="java.text.SimpleDateFormat"%>
	<%@page import="logica.Tipo_TareaLogic" %>
	<%@page import="entidades.Tipo_Tarea" %>
	<%@page import="logica.MaterialLogic" %>
	<%@page import="entidades.Material" %>
	<%@page import="logica.MaquinariaLogic" %>
	<%@page import="entidades.Maquinaria" %>
	<%@page import="logica.PresupuestoLogic" %>
	<%@page import="entidades.Material_a_usar" %>
	<%@page import="entidades.Tarea"%>
	<%@page import="entidades.Presupuesto" %>
	<%@page import="logica.ObraLogic" %>
	<%@page import="entidades.Usuario" %>
	
	<% Presupuesto p = (Presupuesto)request.getAttribute("presupuesto"); %>
	<% Obra o = (Obra)request.getAttribute("obra"); %>
	<% Usuario u= (Usuario)session.getAttribute("usuario"); %>

	<hr>
	<div class="container mt-3 text-center">
		<h1><%=o.getDireccion() %></h1>
		<h2><%=o.getDescripcion() %></h2>
	</div>
	<br>
	<hr>
	<div class="container mt-3">
		
		<br>
		
		<% if(p.getId_presupuesto()!=0){ %>
		<h1>Presupuesto nº<%= p.getId_presupuesto() %></h1>
		<br>
		<h1>Total presupuestado: $<%=p.getMonto() %></h1>
		<br>
		<h1>Estado del presupuesto: <span class="badge bg-secondary"><%= p.getEstado() %></span></h1>
		<br>
		<h3 align="center">Tareas a realizar</h3>
		<%ArrayList<Tarea> ts = p.getTareas(); %>
		<div id="demo" class="carousel slide" data-bs-ride="carousel">
			<!-- The slideshow/carousel -->
  			<div class="carousel-inner">
			<% int i=0; %>
			<% for(Tarea t: ts){ %>
				<% if(i==0){ %>
				<div class="carousel-item active">
				<% } else{ %>
				<div class="carousel-item">
				<% } %>
					<h2>Tarea</h2>
					<br>
					<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);">
						<tr>
							<th style="display:none;">Id tarea</th>
							<th>Tipo de Tarea</th>
							<th>Descripción</th>
							<th>Cantidad de m2</th>
							<th>Fecha Inicio</th>
							<th>Fecha Fin</th>
							<th>Precio</th>
						</tr>
						<tr>
							<td style="display: none;"><%= t.getIdTarea() %></td>
							<td><%= t.getTipo_tarea().getDescripcion() %></td>
							<td><%= t.getDescripcion() %></td>
							<td><%= t.getCant_m2() %></td>
							<td><%= t.getFechaDesde() %></td>
							<td><%= t.getFechaHasta() %></td>
							<td><%= t.getMontoParcial() %></td>
						</tr>
					</table>
					<br>
					<br>
					<h2>Materiales asignados</h2>
					<br>
					<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);">
						<tr>
							<th>Material</th>
							<th>Precio</th>
							<th>Cantidad solicitada</th>
						</tr>
					<% for(Material m:t.getMateriales()){ %>
						<tr>
							<td><%= m.getDescripcion() %></td>
							<td><%= m.getPrecio() %></td>
							<td><%= m.getCantidad() %></td>
						</tr>
					<% } %>
					</table>
					<br>
					<br>
					<h2>Maquinas asignadas</h2>
					<br>
					<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);">
						<tr>
							<th>Maquina</th>
							<th>Precio x hora</th>
							<th>Horas a usar</th>
						</tr>
						<% for(Maquinaria mq:t.getMaquinas()){ %>
						<tr>
							<td><%= mq.getDescripcion() %></td>
							<td><%= mq.getPrecioHora() %></td>
							<td><%= mq.getCantHoras() %></td>
						</tr>
						<% } %>
					</table>
				</div>
				<!-- fin item carrusel -->
			<% i+=1; %>
			<% } %>
		
			</div>
			
			<div class="carousel-indicators">
			<% for(int j=0; j<=i;j++){ %>
				<% if(j==0){ %>
					<button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
				<% }else{ %>
					<button type="button" data-bs-target="#demo" data-bs-slide-to=<%=j %>></button>
				<% } %>
			<% } %>
			</div>
			<!-- Left and right controls/icons -->
		  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </button>
		</div>
		<br>
		<hr>
		<% if(u.getTipo().equalsIgnoreCase("Cliente") && !(p.getFecha_aceptacion()!=null) && !(p.getFecha_caencelacion()!=null) ) { %>
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4"></div>
			<div class="col-4">
				<form action="PresupuestoCliente" method="post">
					<input type="text" id="accion" name="accion" value="aprobar" style="display: none;">
					<input type="number" id="idpresupuesto" name="idpresupuesto" value=<%= p.getId_presupuesto() %> style="display: none;">
					<button type="submit" class="btn btn-primary"><h3>Aprobar</h3></button>
				</form>
				<form action="PresupuestoCliente" method="post">
					<input type="text" id="accion" name="accion" value="rechazar" style="display: none;">
					<input type="number" id="idpresupuesto" name="idpresupuesto" value=<%= p.getId_presupuesto() %> style="display: none;">
					<button type="submit" class="btn btn-danger"><h3>Rechazar</h3></button>
				</form>
			</div>
		</div>
		<% } %>
		
	<% } else{ %>
		<%
		   Date dNow = new Date();
		   SimpleDateFormat ft = 
		   new SimpleDateFormat ("dd/MM/yyyy");
		   String currentDate = ft.format(dNow);
		%>
		<label> Fecha de Creación: </label> <label><%=currentDate%></label>
		<br>
		
		<h2 style="align:center">Tareas</h2>
		<br>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<% ArrayList<Tipo_Tarea> tps= (ArrayList<Tipo_Tarea>)request.getAttribute("tipos"); %>
				<label for="tipos">Agregar tarea:</label>
				<select id="tipos" class="form-select">
					<% for(Tipo_Tarea tt: tps){ %>
						<option value=<%= tt.getId_tipo_tarea() %>><%= tt.getDescripcion() %></option>
					<% } %>
				</select>
				<button type="button" class="btn btn-primary" onClick="agregar_tarea()">Agregar tarea</button>
				<br><br>
				
				<% ArrayList<Material> ms= (ArrayList<Material>)request.getAttribute("materiales"); %>
				<label for="materiales">Agregar material:</label>
				<select id="materiales" class="form-select">
					<% for(Material m: ms){ %>
						<option value=<%= m.getId_material() %>><%= m.getDescripcion() %></option>
					<% } %>
				</select>
				<button type="button" class="btn btn-primary" onClick="agregar_material()">Agregar material</button>
				<br><br>
				
				<% ArrayList<Maquinaria> mqs= (ArrayList<Maquinaria>)request.getAttribute("maquinas"); %>
				<label for="maquinas">Agregar maquina:</label>
				<select id="maquinas" class="form-select">
					<% for(Maquinaria m: mqs){ %>
						<option value=<%= m.getIdMaquina() %>><%= m.getDescripcion() %></option>
					<% } %>
				</select>
				<button type="button" class="btn btn-primary" onClick="agregar_maquina()">Agregar maquina</button>
				
			</div>
			<div class="col-3"></div>
		</div>
		
		<form class="was-validated" method="post" action="VerPresupuesto">
		<label for="idObra">Obra numero:</label>
		<input id="idObra" name="idObra" readonly="true" class="form-control" value=<%= p.getId_obra() %>>
		<input id="accion" name="accion" style="display:none;" class="form-control" value=<%= "registrar_presupusto" %>>
		<input id="nl_tareas" name="nl_tareas" style="display:none;" class="form-control" value=<%= 0 %>>
		<input id="nl_materiales" name="nl_materiales" style="display:none;" class="form-control" value=<%= 0 %>>
		<input id="nl_maquinas" name="nl_maquinas" style="display:none;" class="form-control" value=<%= 0 %>>
		
		<br>
		<br>
		<table id="nuevas_tareas" class="table table-hover" style="background-image: linear-gradient(to bottom right, orange, white);display:none;">
			<th>Nro. de tarea</th>
			<th>Id tipo tarea</th>
			<th>Tipo de tarea</th>
			<th>Descripcion</th>
			<th>Cantidad de m2</th>
			<th>Precio xm2</th>
			<th>Fecha Inicio</th>
			<th>Fecha Fin</th>
			<th>Subtotal</th>
			<th></th>
		</table>
		
		<table id="nuevos_materiales" class="table table-hover" style="background-image: linear-gradient(to bottom right, orange, white);display:none;">
			<th>Id material</th>
			<th>Descripcion</th>
			<th>Cantidad</th>
			<th>Precio c/u</th>
			<th>Tarea</th>
			<th>Subtotal</th>
			<th></th>
		</table>
		
		<table id="nuevas_maquinas" class="table table-hover" style="background-image: linear-gradient(to bottom right, orange, white);display:none;">
			<th>Id maquina</th>
			<th>Descripcion</th>
			<th>Horas uso</th>
			<th>Precio x hora</th>
			<th>Tarea</th>
			<th>Subtotal</th>
			<th></th>
		</table>
		
		<h2 id="costo_parcial">Costo Parcial: $0</h2>
		<br><br>
		
		<h1>Ingrese precio final: $</h1><input type="number" min=1 id="total" name="total" class="form-control" required>
		<button class="btn btn-primary" type="sumbit">Registrar presupuesto</button>
		</form>
	</div>
	
	
	<script type="text/javascript">
	//array de precios para recuperar precios de tipo tarea
	var precios_tipos=[];
	var precios_maquina=[];
	var precios_material=[];
	var n_tareas=0;
	<% for(Tipo_Tarea t:tps){ %>
		precios_tipos[<%= t.getId_tipo_tarea() %>]=<%= t.getPrecio() %>;
	<% } %>
	<% for(Maquinaria m:mqs){ %>
	precios_maquina[<%= m.getIdMaquina() %>]=<%= m.getPrecioHora() %>;
	<% } %>
	<% for(Material m:ms){ %>
	precios_material[<%= m.getId_material() %>]=<%= m.getPrecio() %>;
	<% } %>
	
	function agregar_tarea(){
		n_tareas+=1;
		var id_tt=document.getElementById("tipos").value;
		//nuevas_tareas
		
		//div de la nueva tarea
		const div= document.createElement("tr");
		div.setAttribute('id','tarea_'+n_tareas);
		div.setAttribute('class','container');
		
		//nro de tarea
		const td0=document.createElement("td");
		const ntar= document.createElement("input");
		ntar.value=n_tareas;
		ntar.setAttribute('class','form-control');
		ntar.setAttribute('readonly','true');
		ntar.setAttribute('id','n_tar_'+n_tareas);
		ntar.setAttribute('name','n_tar_'+n_tareas);
		td0.appendChild(ntar);
		div.appendChild(td0);
		if(n_maquina>=1){
			for(i=1;i<=n_maquina;i++){
				//sel.setAttribute('id','n_tar_maq_'+n_maquina);
				var select=document.getElementById('n_tar_maq_'+i);
				const opt=document.createElement('option');
				const opttx=document.createTextNode(n_tareas);
				opt.appendChild(opttx);
				opt.value=n_tareas;
				select.appendChild(opt);
			}
		}
		if(n_material>=1){
			for(i=1;i<=n_material;i++){
				//sel.setAttribute('id','n_tar_mat_'+n_material);
				var select=document.getElementById('n_tar_mat_'+i);
				const opt=document.createElement('option');
				const opttx=document.createTextNode(n_tareas);
				opt.appendChild(opttx);
				opt.value=n_tareas;
				select.appendChild(opt);
			}
		}
		
		//id tipo de la tarea
		const td1=document.createElement("td");
		const hid= document.createElement("input");
		hid.value=id_tt;
		hid.setAttribute('readonly','true');
		hid.setAttribute('class','form-control');
		hid.setAttribute('id','id_'+n_tareas);
		hid.setAttribute('name','id_'+n_tareas);
		//hid.setAttribute('type','number');
		//hid.setAttribute("readonly" , "readonly" , false);
		td1.appendChild(hid);
		div.appendChild(td1);
		
		//descripcion de tipo de tarea
		const td2=document.createElement("td");
		const idesctipo= document.createElement("p");
		const sel=document.getElementById("tipos");
		const txt1=sel.options[sel.selectedIndex].text;
		const tdesctipo = document.createTextNode(txt1);
		idesctipo.appendChild(tdesctipo);
		td2.appendChild(idesctipo);
		div.appendChild(td2);
		
		//input de descripcion de la tarea
		const td3=document.createElement("td");
		const inpdesc=document.createElement("input");
		inpdesc.setAttribute('id','desc_tarea_'+n_tareas);
		inpdesc.setAttribute('name','desc_tarea_'+n_tareas);
		inpdesc.setAttribute('required','true');
		inpdesc.setAttribute('class','form-control');
		inpdesc.setAttribute('placeholder','Descripcion de la tarea');
		td3.appendChild(inpdesc);
		div.appendChild(td3);
		
		//cant m2
		const td4=document.createElement("td");const inpm2=document.createElement("input");
		inpm2.setAttribute('id','m2_'+n_tareas);
		inpm2.setAttribute('name','m2_'+n_tareas);
		inpm2.setAttribute('type','number');
		inpm2.setAttribute('min',1);
		inpm2.setAttribute('required','true');
		inpm2.setAttribute('onchange','subtotal_linea('+n_tareas+','+precios_tipos[id_tt]+')');
		inpm2.setAttribute('class','form-control');
		inpm2.setAttribute('placeholder','Cantidad de m2');
		td4.appendChild(inpm2);
		div.appendChild(td4);
		
		//precio por m2
		const td5=document.createElement("td");
		const prec=document.createElement("p");
		const txtm2 = document.createTextNode(precios_tipos[id_tt]);
		prec.appendChild(txtm2);
		prec.setAttribute('id','precio_'+n_tareas);
		prec.setAttribute('name','precio_'+n_tareas);
		td5.appendChild(prec);
		div.appendChild(td5);
		
		//fecha desde
		const tdfd= document.createElement("td");
		const inpfd=document.createElement("input");
		inpfd.setAttribute('id','fd_'+n_tareas);
		inpfd.setAttribute('name','fd_'+n_tareas);
		inpfd.setAttribute('type','date');
		let today = new Date();
		let dd = today.getDate();
		let mm = today.getMonth() + 1; //January is 0!
		let yyyy = today.getFullYear();
		if (dd < 10) {
		   dd = '0' + dd;
		}
		if (mm < 10) {
		   mm = '0' + mm;
		}  
		today = yyyy + '-' + mm + '-' + dd;
		inpfd.setAttribute('min', today);
		inpfd.value=today;
		inpfd.setAttribute('onchange','min_fecha_hasta('+n_tareas+')');
		tdfd.appendChild(inpfd);
		div.appendChild(tdfd);
		
		//fecha hasta
		const tdfh= document.createElement("td");
		const inpfh=document.createElement("input");
		inpfh.setAttribute('id','fh_'+n_tareas);
		inpfh.setAttribute('name','fh_'+n_tareas);
		inpfh.setAttribute('type','date');
		inpfh.setAttribute('min', today);
		inpfh.value=today;
		tdfh.appendChild(inpfh);
		div.appendChild(tdfh);
		
		
		
		//linea de subtotal
		const td6=document.createElement("td");
		const subtotal=document.createElement("p");
		subtotal.setAttribute('id','subtot_'+n_tareas);
		subtotal.setAttribute('name','subtot_'+n_tareas);
		const txsub=document.createTextNode("0");
		subtotal.appendChild(txsub);
		td6.appendChild(subtotal);
		div.appendChild(td6);
		
		//boton para eliminar linea
		const td7=document.createElement("td");
		const btn=document.createElement("button");
		btn.setAttribute("class","btn btn-danger btn-small");
		btn.setAttribute("id","rmv_tar_"+n_tareas);
		btn.setAttribute("onclick","remover_tarea("+n_tareas+")");
		btn.type='button';
		btn.innerText='X';
		td7.appendChild(btn);
		div.appendChild(td7);
		
		//agrega el div
        const element = document.getElementById("nuevas_tareas");
        element.appendChild(div);
        element.style.display="block";
        
        
      
	}
	function min_fecha_hasta(idx){
		let fd = document.getElementById('fd_'+idx).value;
		let fh = document.getElementById('fh_'+idx);
		fh.setAttribute('min',fd);
	}
	
	function subtotal_linea(idx,precio){
		var _1 = document.getElementById('m2_'+idx).value;
		var idtts = parseInt(document.getElementById('id_'+idx).text);
		
        var _2 = precio;
        var _11=parseFloat(_1);
        var _22=parseFloat(_2);
        var total= _11 * _22 ;
        var _total = document.getElementById('subtot_'+idx);
        console.log(_1,_2,total);
        if( isNaN(total)){
            _total.innerHTML=0;
        }else{
            _total.innerHTML=total;
        }
        calcular_costo_parcial();
	}
	
	/*------------------------------------------------
	-------------------------------------------------
	ahora maquina*/
	
	var n_maquina = 0;

	function agregar_maquina(){
			const select=document.getElementById("maquinas");
			//if(!select.options[select.selectedIndex].disabled){       //**if para que no se puede elegir mas de una vez la maquina
				n_maquina+=1;
				var id_mq=select.value;
				//select.options[select.selectedIndex].disabled=true;   //**
				//nuevas_maquinas
				
				//div de la nueva maquina
				const div= document.createElement("tr");
				div.setAttribute('id','maquina_'+n_maquina);
				div.setAttribute('class','container');
				
				//id maquina
				const td1=document.createElement("td");
				const hid= document.createElement("input");
				hid.value=id_mq;
				hid.setAttribute('readonly','true');
				hid.setAttribute('class','form-control');
				hid.setAttribute('id','idmq_'+n_maquina);
				hid.setAttribute('name','idmq_'+n_maquina);
				td1.appendChild(hid);
				div.appendChild(td1);
				
				//descripcion de maquina
				const td2=document.createElement("td");
				const idescmaquina= document.createElement("p");
				const sel=document.getElementById("maquinas");
				const txt1=sel.options[sel.selectedIndex].text;
				const tdescmaquina = document.createTextNode(txt1);
				idescmaquina.appendChild(tdescmaquina);
				td2.appendChild(idescmaquina);
				div.appendChild(td2);
				
				//cant horas
				const td4=document.createElement("td");
				const inphs=document.createElement("input");
				inphs.setAttribute('id','cant_hs_'+n_maquina);
				inphs.setAttribute('name','cant_hs_'+n_maquina);
				inphs.setAttribute('type','number');
				inphs.setAttribute('onchange','subtotal_mq_linea('+n_maquina+','+precios_maquina[id_mq]+')');
				inphs.setAttribute('min',1);
				inphs.setAttribute('required','true');
				inphs.setAttribute('class','form-control');
				inphs.setAttribute('placeholder','Cantidad de horas a usar');
				td4.appendChild(inphs);
				div.appendChild(td4);
				
				//precio por hs
				const td5=document.createElement("td");
				const prec=document.createElement("p");
				const txths = document.createTextNode(precios_maquina[id_mq]);
				prec.appendChild(txths);
				prec.setAttribute('id','precio_mq_'+n_maquina);
				prec.setAttribute('name','precio_mq_'+n_maquina);
				td5.appendChild(prec);
				div.appendChild(td5);
				
				//n de tarea a la que corresponde la maquina
				const td0=document.createElement("td");
				const selct=document.createElement("select");
				selct.setAttribute('id','n_tar_maq_'+n_maquina);
				selct.setAttribute('name','n_tar_maq_'+n_maquina);
				selct.setAttribute('min',1);
				selct.setAttribute('class','form-select');
				const opt0=document.createElement("option");
				const opt0tx=document.createTextNode("Seleccione una tarea");
				opt0.appendChild(opt0tx);
				opt0.value=0;
				opt0.disabled=true;
				selct.required=true;
				selct.appendChild(opt0);
				for(i=1;i<=n_tareas;i++){
					var opt=document.createElement("option");
					var opttx=document.createTextNode(i);
					opt.appendChild(opttx);
					opt.value=i;
					selct.appendChild(opt);
				}
				td0.appendChild(selct);
				div.appendChild(td0);
				
				//linea de subtotal
				const td6=document.createElement("td");
				const subtotal=document.createElement("p");
				subtotal.setAttribute('id','subtot_mq_'+n_maquina);
				subtotal.setAttribute('name','subtot_mq_'+n_maquina);
				const txsub=document.createTextNode("0");
				subtotal.appendChild(txsub);
				td6.appendChild(subtotal);
				div.appendChild(td6);
				
				//boton para eliminar linea
				const td7=document.createElement("td");
				const btn=document.createElement("button");
				btn.setAttribute('id','rmv_maq_'+n_maquina);
				btn.setAttribute('onclick','remover_maquina('+n_maquina+')');
				btn.setAttribute("class","btn btn-danger btn-small");
				btn.type='button';
				btn.innerText='X';
				td7.appendChild(btn);
				div.appendChild(td7);
				
				//agrega el div
		        const element = document.getElementById("nuevas_maquinas");
		        element.appendChild(div);
		        element.style.display="block";
			//}         						//**
	      
		}

	function subtotal_mq_linea(idx,precio){
			var _1 = document.getElementById('cant_hs_'+idx).value;	
	        var _2 = precio;
	        var _11=parseFloat(_1);
	        var _22=parseFloat(_2);
	        var total= _11 * _22 ;
	        var _total = document.getElementById('subtot_mq_'+idx);
	        console.log(_1,_2,total);
	        if( isNaN(total)){
	            _total.innerHTML=0;
	        }else{
	            _total.innerHTML=total;
	        }
	        calcular_costo_parcial();
		}
	
	/*------------------------------------------------------
	-------------------------------------------------------
	ahora los materiales*/
	var n_material = 0;

	function agregar_material(){
		const select=document.getElementById("materiales");
		//if(!select.options[select.selectedIndex].disabled){				//**if para que no se pueda seleccionar mas de una vez el material
			n_material+=1;
			var id_mt=select.value;
			//select.options[select.selectedIndex].disabled=true;		//**
			//nuevos materiales
			
			//div del nuevo material
			const div= document.createElement("tr");
			div.setAttribute('id','material_'+n_material);
			div.setAttribute('class','container');
			
			//id material
			const td1=document.createElement("td");
			const hid= document.createElement("input");
			hid.value=id_mt;
			hid.setAttribute('readonly','true');
			hid.setAttribute('class','form-control');
			hid.setAttribute('id','idmt_'+n_material);
			hid.setAttribute('name','idmt_'+n_material);
			td1.appendChild(hid);
			div.appendChild(td1);
			
			//descripcion de material
			const td2=document.createElement("td");
			const idescmaterial= document.createElement("p");
			const sel=document.getElementById("materiales");
			const txt1=sel.options[sel.selectedIndex].text;
			const tdescmaterial = document.createTextNode(txt1);
			idescmaterial.appendChild(tdescmaterial);
			td2.appendChild(idescmaterial);
			div.appendChild(td2);
			
			//cant 
			const td4=document.createElement("td");
			const inpc=document.createElement("input");
			inpc.setAttribute('id','cant_unidad_'+n_material);
			inpc.setAttribute('name','cant_unidad_'+n_material);
			inpc.setAttribute('type','number');
			inpc.setAttribute('onchange','subtotal_mt_linea('+n_material+','+precios_material[id_mt]+')');
			inpc.setAttribute('min',1);
			inpc.setAttribute('required','true');
			inpc.setAttribute('class','form-control');
			inpc.setAttribute('placeholder','Cantidad');
			td4.appendChild(inpc);
			div.appendChild(td4);
			
			//precio
			const td5=document.createElement("td");
			const prec=document.createElement("p");
			const txtpre = document.createTextNode(precios_material[id_mt]);
			prec.appendChild(txtpre);
			prec.setAttribute('id','precio_mt_'+n_material);
			prec.setAttribute('name','precio_mt_'+n_material);
			td5.appendChild(prec);
			div.appendChild(td5);
			
			//n de tarea a la que corresponde el material
			const td0=document.createElement("td");
			const selct=document.createElement("select");
			selct.setAttribute('id','n_tar_mat_'+n_material);
			selct.setAttribute('name','n_tar_mat_'+n_material);
			selct.setAttribute('class','form-select');
			const opt0=document.createElement("option");
			const opt0tx=document.createTextNode("Seleccione una tarea");
			opt0.appendChild(opt0tx);
			opt0.value=0;
			opt0.disabled=true;
			selct.required=true;
			selct.appendChild(opt0);
			for(i=1;i<=n_tareas;i++){
				var opt=document.createElement("option");
				var opttx=document.createTextNode(i);
				opt.appendChild(opttx);
				opt.value=i;
				selct.appendChild(opt);
			}
			td0.appendChild(selct);
			div.appendChild(td0);
			
			//linea de subtotal
			const td6=document.createElement("td");
			const subtotal=document.createElement("p");
			subtotal.setAttribute('id','subtot_mt_'+n_material);
			subtotal.setAttribute('name','subtot_mt_'+n_material);
			const txsub=document.createTextNode("0");
			subtotal.appendChild(txsub);
			td6.appendChild(subtotal);
			div.appendChild(td6);
			
			//boton para eliminar linea
			const td7=document.createElement("td");
			const btn=document.createElement("button");
			btn.setAttribute('id','rmv_mat_'+n_material);
			btn.setAttribute("class","btn btn-danger btn-small");
			btn.type='button';
			btn.innerText='X';
			btn.setAttribute('onclick','remover_material('+n_material+')');
			td7.appendChild(btn);
			div.appendChild(td7);
			
			//agrega el div
	        const element = document.getElementById("nuevos_materiales");
	        element.appendChild(div);
	        element.style.display="block";
		//}																	//**
	      
	}
		




	function subtotal_mt_linea(idx,precio){
			var _1 = document.getElementById('cant_unidad_'+idx).value;
				
				
		        var _2 = precio;
		        var _11=parseFloat(_1);
		        var _22=parseFloat(_2);
		        var total= _11 * _22 ;
		        var _total = document.getElementById('subtot_mt_'+idx);
		        console.log(_1,_2,total);
		        if( isNaN(total)){
		            _total.innerHTML=0;
		        }else{
		            _total.innerHTML=total;
		        }
		        calcular_costo_parcial();
		}
	
	/*costo parcial*/
	
	function calcular_costo_parcial() {
		var total_parcial = 0;
		
		for (i = 1; i <= n_material; i++){
			var linea = parseFloat(document.getElementById("subtot_mt_" + i).innerHTML);
			if (linea != NaN) {
				total_parcial += linea;
			}
		}
	
		for (i = 1; i <= n_maquina; i++){
			var linea = parseFloat(document.getElementById("subtot_mq_" + i).innerHTML);
			if (linea != NaN) {
				total_parcial += linea;
			}
		}
	
		for (i = 1; i <= n_tareas; i++){
			var linea = parseFloat(document.getElementById("subtot_" + i).innerHTML);
			console.log(total_parcial,linea);
			if (linea != NaN) {
				total_parcial += linea;
			}
			//como la funcion se dispara siempre se usa para igualar las cant de lineas con el
			//input que indica cuantas lineas deberan leerse
			document.getElementById("nl_maquinas").value=n_maquina;
			document.getElementById("nl_materiales").value=n_material;
			document.getElementById("nl_tareas").value=n_tareas;
		}
	
		const h2 = document.getElementById("costo_parcial");
		h2.innerHTML = "Costo Parcial: $" + total_parcial;
	}
	
	function remover_maquina(num_linea){
		let tabla=document.getElementById('nuevas_maquinas');
		let linea=document.getElementById('maquina_'+num_linea);
		tabla.removeChild(linea);
		for(i=num_linea+1;i<=n_maquina;i++){
			let linea=document.getElementById('maquina_'+i);
			linea.setAttribute('id','maquina_'+(i-1));
			
			let idl=document.getElementById('idmq_'+i);
			idl.setAttribute('id','idmq_'+(i-1));
			idl.setAttribute('name','idmq_'+(i-1));
			
			let cunl=document.getElementById('cant_hs_'+i);
			cunl.setAttribute('id','cant_hs_'+(i-1));
			cunl.setAttribute('name','cant_hs_'+(i-1));
			
			let pl=document.getElementById('precio_mq_'+i);
			pl.setAttribute('id','precio_mq_'+(i-1));
			pl.setAttribute('name','precio_mq_'+(i-1));
			
			cunl.setAttribute('onchange','subtotal_mq_linea('+(i-1)+','+parseFloat(pl.innerText)+')');
			
			let ntm=document.getElementById('n_tar_maq_'+i);
			ntm.setAttribute('id','n_tar_maq_'+(i-1));
			ntm.setAttribute('name','n_tar_maq_'+(i-1));
			
			let sub=document.getElementById('subtot_mq_'+i);
			sub.setAttribute('id','subtot_mq_'+(i-1));
			sub.setAttribute('name','subtot_mq_'+(i-1));
			
			let btn=document.getElementById('rmv_maq_'+i);
			btn.setAttribute('id','rmv_maq_'+(i-1));
			btn.setAttribute('onclick','remover_maquina('+(i-1)+')');
		}
		n_maquina-=1;
		if(n_maquina==0){
			tabla.style.display="none";
		}
		calcular_costo_parcial();
	}
	
	
	
	function remover_material(num_linea){
		let tabla=document.getElementById('nuevos_materiales');
		let linea=document.getElementById('material_'+num_linea);
		tabla.removeChild(linea);
		for(i=num_linea+1;i<=n_material;i++){
			let linea=document.getElementById('material_'+i);
			linea.setAttribute('id','material_'+(i-1));
			
			let idl=document.getElementById('idmt_'+i);
			idl.setAttribute('id','idmt_'+(i-1));
			idl.setAttribute('name','idmt_'+(i-1));
			
			let cunl=document.getElementById('cant_unidad_'+i);
			cunl.setAttribute('id','cant_unidad_'+(i-1));
			cunl.setAttribute('name','cant_unidad_'+(i-1));
			
			let pl=document.getElementById('precio_mt_'+i);
			pl.setAttribute('id','precio_mt_'+(i-1));
			pl.setAttribute('name','precio_mt_'+(i-1));
			
			cunl.setAttribute('onchange','subtotal_mt_linea('+(i-1)+','+parseFloat(pl.innerText)+')');
			
			let ntm=document.getElementById('n_tar_mat_'+i);
			ntm.setAttribute('id','n_tar_mat_'+(i-1));
			ntm.setAttribute('name','n_tar_mat_'+(i-1));
			
			let sub=document.getElementById('subtot_mt_'+i);
			sub.setAttribute('id','subtot_mt_'+(i-1));
			sub.setAttribute('name','subtot_mt_'+(i-1));
			
			let btn=document.getElementById('rmv_mat_'+i);
			btn.setAttribute('id','rmv_mat_'+(i-1));
			btn.setAttribute('onclick','remover_material('+(i-1)+')');
		}
		n_material-=1;
		if(n_material==0){
			tabla.style.display="none";
		}
		calcular_costo_parcial();
	}
	
	function remover_tarea(num_tarea){
		let tabla=document.getElementById('nuevas_tareas');
		let linea=document.getElementById('tarea_'+num_tarea);
		tabla.removeChild(linea);
		//remover la ultima opcion del select y seleccionar opcion anterior 
		//en caso la seleccionada es mayor a la eliminada
		for(i=1;i<=n_material;i++){
			let slc=document.getElementById('n_tar_mat_'+i);
			var condicion=(slc.selectedIndex>=num_tarea);
			var condicion2=(slc.selectedIndex==num_tarea);
			var idx_ele=slc.selectedIndex;
			slc.selectedIndex=slc.options.length-1;
			slc.remove(slc.selectedIndex);
			if(condicion){
				slc.selectedIndex=idx_ele-1;
			} 
			else {
				slc.selectedIndex=idx_ele;
			}
			if(condicion2){
				slc.selectedIndex=0;
			}
		}
		for(i=1;i<=n_maquina;i++){
			let slc=document.getElementById('n_tar_maq_'+i);
			var condicion=(slc.selectedIndex>=num_tarea);
			var condicion2=(slc.selectedIndex==num_tarea);
			var idx_ele=slc.selectedIndex;
			slc.selectedIndex=slc.options.length-1;
			slc.remove(slc.selectedIndex);
			if(condicion){
				slc.selectedIndex=idx_ele-1;
			} 
			else {
				slc.selectedIndex=idx_ele;
			}
			if(condicion2){
				slc.selectedIndex=0;
			}
		}
		//
		for(i=num_tarea+1;i<=n_tareas;i++){
			let linea=document.getElementById('tarea_'+i);
			linea.setAttribute('id','tarea_'+(i-1));
			
			let ntar=document.getElementById('n_tar_'+i);
			ntar.setAttribute('id','n_tar_'+(i-1));
			ntar.setAttribute('name','n_tar_'+(i-1));
			ntar.innerText=(i-1);
			ntar.value=(i-1);
			
			
			let id=document.getElementById('id_'+i);
			id.setAttribute('id','id_'+(i-1));
			id.setAttribute('name','id_'+(i-1));
			
			let desc=document.getElementById('desc_tarea_'+i);
			desc.setAttribute('id','desc_tarea_'+(i-1));
			desc.setAttribute('name','desc_tarea_'+(i-1));
			
			let m2=document.getElementById('m2_'+i);
			m2.setAttribute('id','m2_'+(i-1));
			m2.setAttribute('name','m2_'+(i-1));
			
			
			let pre=document.getElementById('precio_'+i);
			pre.setAttribute('id','precio_'+(i-1));
			pre.setAttribute('name','precio_'+(i-1));
			
			let fd = document.getElementById('fd_'+i);
			fd.setAttribute('id','fd_'+(i-1));
			fd.setAttribute('name','fd_'+(i-1));
			fd.setAttribute('onchange','min_fecha_hasta('+(i-1)+')');
			
			let fh = document.getElementById('fh_'+i);
			fh.setAttribute('id','fh_'+(i-1));
			fh.setAttribute('name','fh_'+(i-1));
			
			m2.setAttribute('onchange','subtotal_linea('+(i-1)+','+parseFloat(pre.innerText)+')');
			
			let sub=document.getElementById('subtot_'+i);
			sub.setAttribute('id','subtot_'+(i-1));
			sub.setAttribute('name','subtot_'+(i-1));
			
			let btn=document.getElementById('rmv_tar_'+i);
			btn.setAttribute('id','rmv_tar_'+(i-1));
			btn.setAttribute('onclick','remover_tarea('+(i-1)+')');
		}
		n_tareas-=1;
		if(n_tareas==0){
			tabla.style.display='none';
		}
		calcular_costo_parcial();
	}
		

	
	
	</script>
	
	<% } %>
</body>
</html>