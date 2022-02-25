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
	<%@ page import="java.util.*" %>
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
	
	<% Presupuesto p = (Presupuesto)request.getAttribute("presupuesto"); %>

	<hr>
	
	<div class="container mt-3">
		<%
		   Date dNow = new Date();
		   SimpleDateFormat ft = 
		   new SimpleDateFormat ("MM/dd/yyyy");
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
				<br>
				
				<% ArrayList<Material> ms= (ArrayList<Material>)request.getAttribute("materiales"); %>
				<label for="materiales">Agregar material:</label>
				<select id="materiales" class="form-select">
					<% for(Material m: ms){ %>
						<option value=<%= m.getId_material() %>><%= m.getDescripcion() %></option>
					<% } %>
				</select>
				<button type="button" class="btn btn-primary" onClick="agregar_material()">Agregar material</button>
				<br>
				
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
		<br>
		
		<% if(p.getId_presupuesto()!=0){ %>
		<% Obra o = ObraLogic.getOne(p.getId_obra()); %>
		
		<h3 align="center">Tareas a realizar</h3>
		<%ArrayList<Tarea> ts = PresupuestoLogic.getTareas(p); %>
		<table class="table" style="background-image: linear-gradient(to bottom right, orange, white);" id="tab_tareas">
			<th>Tipo de Tarea</th>
			<th>Descripción</th>
			<th>Cantidad de m2</th>
			<th>Precio</th>
			<th></th>
			<th></th>
			<th></th>
			
			<% for(Tarea t: ts){ %>
			<tr>
				<td style="display: none;"><%= t.getIdTarea() %></td>
				<td><%= t.getTipo_tarea().getDescripcion() %></td>
				<td><%= t.getDescripcion() %></td>
				<td><%= t.getCant_m2() %></td>
				<td><%= t.getMontoParcial() %></td>
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal1"
						onClick="agregaMaterial()">Agregar Material</button></td>		
				<td><button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#myModal2"
						onClick="agregaMaquinaria()">Agregar Maquinaria</button></td>
				<td><button type="button" class="btn btn-danger"
						data-bs-toggle="modal" data-bs-target="#myModal"
						onClick="eliminaTarea()">Eliminar</button></td>
			</tr>
			<% } %>
		</table>

		
		<br>
		
	<% } else{ %>
		<form class="was-validated">
		<table id="nuevas_tareas" class="table" style="background-image: linear-gradient(to bottom right, orange, white);display:none;">
			<th>Id tipo tarea</th>
			<th>Tipo de tarea</th>
			<th>Descripcion</th>
			<th>Cantidad de m2</th>
			<th>Precio xm2</th>
			<th>Subtotal</th>
		</table>
		
		<table id="nuevos_materiales" class="table" style="background-image: linear-gradient(to bottom right, orange, white);display:none;">
			<th>Id material</th>
			<th>Descripcion</th>
			<th>Cantidad</th>
			<th>Precio c/u</th>
			<th>Subtotal</th>
		</table>
		
		<table id="nuevas_maquinas" class="table" style="background-image: linear-gradient(to bottom right, orange, white);display:none;">
			<th>Id maquina</th>
			<th>Descripcion</th>
			<th>Horas uso</th>
			<th>Precio x hora</th>
			<th>Subtotal</th>
		</table>
		
		<h2 id="costo_parcial">Costo Parcial: $0</h2>
		<br><br>
		
		<h1>Ingrese precio final: $</h1><input type="number" min=1 id="total" class="form-control" required>
		<button class="btn btn-primary" type="sumbit">Registrar presupuesto</button>
		</form>
	<% } %>
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
		
		//id tipo de la tarea
		const td1=document.createElement("td");
		const hid= document.createElement("p");
		const pid=document.createTextNode(id_tt);
		hid.appendChild(pid);
		hid.setAttribute('id','id_'+n_tareas);
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
		const label=document.createElement("label");
		label.setAttribute('for','desc_tarea_'+n_tareas);
		label.innerHTML="Descripcion";
		const inpdesc=document.createElement("input");
		inpdesc.setAttribute('id','desc_tarea_'+n_tareas);
		inpdesc.setAttribute('required','true');
		td3.appendChild(label);
		td3.appendChild(inpdesc);
		div.appendChild(td3);
		
		//cant m2
		const td4=document.createElement("td");
		const label2=document.createElement("label");
		label2.setAttribute('for','desc_tarea_'+n_tareas);
		label2.innerHTML="m2:";
		const inpm2=document.createElement("input");
		inpm2.setAttribute('id','m2_'+n_tareas);
		inpm2.setAttribute('type','number');
		inpm2.setAttribute('min',0.01);
		inpm2.setAttribute('required','true');
		inpm2.setAttribute('onchange','subtotal_linea('+n_tareas+','+precios_tipos[id_tt]+')');
		td4.appendChild(label2);
		td4.appendChild(inpm2);
		div.appendChild(td4);
		
		//precio por m2
		const td5=document.createElement("td");
		const prec=document.createElement("p");
		const txtm2 = document.createTextNode(precios_tipos[id_tt]);
		prec.appendChild(txtm2);
		prec.setAttribute('id','precio_'+n_tareas);
		td5.appendChild(prec);
		div.appendChild(td5);
		
		//linea de subtotal
		const td6=document.createElement("td");
		const subtotal=document.createElement("p");
		subtotal.setAttribute('id','subtot_'+n_tareas);
		const txsub=document.createTextNode("0");
		subtotal.appendChild(txsub);
		td6.appendChild(subtotal);
		div.appendChild(td6);
		
		//agrega el div
        const element = document.getElementById("nuevas_tareas");
        element.appendChild(div);
        element.style.display="block";
        
        
      
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
			n_maquina+=1;
			var id_mq=document.getElementById("maquinas").value;
			//nuevas_maquinas
			
			//div de la nueva maquina
			const div= document.createElement("tr");
			div.setAttribute('id','maquina_'+n_maquina);
			div.setAttribute('class','container');
			
			//id maquina
			const td1=document.createElement("td");
			const hid= document.createElement("p");
			const pid=document.createTextNode(id_mq);
			hid.appendChild(pid);
			hid.setAttribute('id','idmq_'+n_maquina);
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
			const label2=document.createElement("label");
			label2.setAttribute('for','cant_hs_'+n_maquina);
			label2.innerHTML="cantidad horas:";
			const inphs=document.createElement("input");
			inphs.setAttribute('id','cant_hs_'+n_maquina);
			inphs.setAttribute('type','number');
			inphs.setAttribute('onchange','subtotal_mq_linea('+n_maquina+','+precios_maquina[id_mq]+')');
			inphs.setAttribute('min',1);
			inphs.setAttribute('required','true');
			td4.appendChild(label2);
			td4.appendChild(inphs);
			div.appendChild(td4);
			
			//precio por hs
			const td5=document.createElement("td");
			const prec=document.createElement("p");
			const txths = document.createTextNode(precios_maquina[id_mq]);
			prec.appendChild(txths);
			prec.setAttribute('id','precio_mq_'+n_maquina);
			td5.appendChild(prec);
			div.appendChild(td5);
			
			//linea de subtotal
			const td6=document.createElement("td");
			const subtotal=document.createElement("p");
			subtotal.setAttribute('id','subtot_mq_'+n_maquina);
			const txsub=document.createTextNode("0");
			subtotal.appendChild(txsub);
			td6.appendChild(subtotal);
			div.appendChild(td6);
			
			//agrega el div
	        const element = document.getElementById("nuevas_maquinas");
	        element.appendChild(div);
	        element.style.display="block";
	        
	      
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
			n_material+=1;
			var id_mt=document.getElementById("materiales").value;
			//nuevos materiales
			
			//div del nuevo material
			const div= document.createElement("tr");
			div.setAttribute('id','material_'+n_material);
			div.setAttribute('class','container');
			
			//id material
			const td1=document.createElement("td");
			const hid= document.createElement("p");
			const pid=document.createTextNode(id_mt);
			hid.appendChild(pid);
			hid.setAttribute('id','idmt_'+n_material);
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
			const label2=document.createElement("label");
			label2.setAttribute('for','cant_unidad_'+n_material);
			label2.innerHTML="cantidad:";
			const inpc=document.createElement("input");
			inpc.setAttribute('id','cant_unidad_'+n_material);
			inpc.setAttribute('type','number');
			inpc.setAttribute('onchange','subtotal_mt_linea('+n_material+','+precios_material[id_mt]+')');
			inpc.setAttribute('min',1);
			inpc.setAttribute('required','true');
			td4.appendChild(label2);
			td4.appendChild(inpc);
			div.appendChild(td4);
			
			//precio
			const td5=document.createElement("td");
			const prec=document.createElement("p");
			const txtpre = document.createTextNode(precios_material[id_mt]);
			prec.appendChild(txtpre);
			prec.setAttribute('id','precio_mt_'+n_material);
			td5.appendChild(prec);
			div.appendChild(td5);
			
			//linea de subtotal
			const td6=document.createElement("td");
			const subtotal=document.createElement("p");
			subtotal.setAttribute('id','subtot_mt_'+n_material);
			const txsub=document.createTextNode("0");
			subtotal.appendChild(txsub);
			td6.appendChild(subtotal);
			div.appendChild(td6);
			
			//agrega el div
	        const element = document.getElementById("nuevos_materiales");
	        element.appendChild(div);
	        element.style.display="block";
	        
	      
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
			//console.log(total_parcial,linea);
		}
	
		const h2 = document.getElementById("costo_parcial");
		h2.innerHTML = "Costo Parcial: $" + total_parcial;
	}
		

	
	
	</script>
	

</body>
</html>