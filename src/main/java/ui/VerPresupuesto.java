package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Maquinaria;
import entidades.Material;
import entidades.Obra;
import logica.MaquinariaLogic;
import logica.MaterialLogic;
import logica.ObraLogic;
import entidades.Presupuesto;
import entidades.Tarea;
import entidades.Tipo_Tarea;
import logica.PresupuestoLogic;
import logica.Tipo_TareaLogic;

/**
 * Servlet implementation class VerPresupuesto
 */
@WebServlet("/VerPresupuesto")
public class VerPresupuesto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPresupuesto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("idPresupuesto"));
		int idObra=Integer.parseInt(request.getParameter("idObra"));
		Presupuesto p= new Presupuesto();
		p.setId_obra(idObra);
		try {
			if(id!=0) {
				p = PresupuestoLogic.getOne(id,true);
			}
			ArrayList<Tipo_Tarea> tt=Tipo_TareaLogic.getAll();
			request.setAttribute("tipos", tt);
			
			ArrayList<Maquinaria> maq=MaquinariaLogic.getAll();
			request.setAttribute("maquinas", maq);
			
			ArrayList<Material> mats=MaterialLogic.getAll();
			request.setAttribute("materiales", mats);
			
			request.setAttribute("presupuesto", p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "Error al cargar los datos del presupuesto: "+e.getMessage());
		}
		request.getRequestDispatcher("./Presupuesto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion=(String)request.getParameter("accion");
		switch(accion) {
		case "registrar_presupusto":{
			RegistrarPresupuesto(request,response);
			break;
		}
		}
	}
	
	/**
	 * @see RegistrarPresupuesto
	 */
	protected void RegistrarPresupuesto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int i=0;
		int idObra=Integer.parseInt((String)request.getParameter("idObra"));
		float monto=Float.parseFloat((String)request.getParameter("total"));
		Date hoy=new Date(System.currentTimeMillis());
		int cant_tareas=Integer.parseInt((String)request.getParameter("nl_tareas"));
		int cant_materiales=Integer.parseInt((String)request.getParameter("nl_materiales"));
		int cant_maquinas=Integer.parseInt((String)request.getParameter("nl_maquinas"));
		
		Presupuesto p=new Presupuesto(0,hoy,monto,idObra,null,null);
		p.setTareas(new ArrayList<Tarea>());
		
		int n_tarea,idtipotarea;
		String descTarea;
		float cantm2Tarea;
		Tarea t;
		
		for(i=1;i<=cant_tareas;i++) {
			n_tarea=Integer.parseInt((String)request.getParameter("n_tar_"+i));
			idtipotarea=Integer.parseInt((String)request.getParameter("id_"+i));
			descTarea=(String)request.getParameter("desc_tarea_"+i);
			cantm2Tarea=Float.parseFloat((String)request.getParameter("m2_"+i));
			t=new Tarea(n_tarea, descTarea, cantm2Tarea, new Tipo_Tarea(idtipotarea,"",0)); //el precio y descripcion del tipo_tarea es despreciable
			t.setMaquinas(new ArrayList<Maquinaria>());
			t.setMaquinas(new ArrayList<Maquinaria>());
			p.getTareas().add(t);
		}
		
		int idmt, cant_mt;
		Material mt;
		boolean bandera=false;
		
		for(i=1;i<=cant_materiales;i++) {
			bandera=false;
			idmt=Integer.parseInt((String)request.getParameter("idmt_"+i));
			cant_mt=Integer.parseInt((String)request.getParameter("cant_unidad_"+i));
			n_tarea=Integer.parseInt((String)request.getParameter("n_tar_mat_"+i));
			mt=new Material(idmt,"",0); //descripcion y precio despreciables
			mt.setCantidad(cant_mt);
			for(Tarea tar:p.getTareas()) {
				if(tar.getIdTarea()==n_tarea) {
					//verifica si ya se ha agregado un material con el mismo id
					for(Material mat:tar.getMateriales()) {
						if(mat.getId_material()==idmt) { //se encotro material con el mismo id
							mat.setCantidad(mat.getCantidad()+cant_mt); //aumenta
							bandera=true;
							break;
						}
					}
					//si no encontro un material con el mismo id lo agrega
					if(!bandera) { //no encontro un material con el mismo id
						tar.getMateriales().add(mt);
						break;
					}
				}
			}
		}
		
		int idmq, cantHS;
		Maquinaria mq;
		
		for(i=1;i<=cant_maquinas;i++) {
			bandera=false;
			idmq=Integer.parseInt((String)request.getParameter("idmq_"+i));
			cantHS=Integer.parseInt((String)request.getParameter("cant_hs_"+i));
			n_tarea=Integer.parseInt((String)request.getParameter("n_tar_maq_"+i));
			mq=new Maquinaria(idmq, "", 0);
			mq.setCantHoras(cantHS);
			for(Tarea tar:p.getTareas()) {
				if(tar.getIdTarea()==n_tarea) {
					//verifica si ya se ha agregado una maquina con el mismo id
					for(Maquinaria maq:tar.getMaquinas()) {
						if(maq.getIdMaquina()==idmq) { //se encotro maquina con el mismo id
							maq.setCantHoras(maq.getCantHoras()+cantHS); //aumenta
							bandera=true;
							break;
						}
					}
					//si no encontro una maquina con el mismo id la agrega
					if(!bandera) {
						tar.getMaquinas().add(mq);
						break;
					}
				}
			}
		}
		
		try {
			PresupuestoLogic.RegistrarPresupuesto(idObra, p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
