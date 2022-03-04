package ui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Tarea;
import entidades.Tipo_Tarea;
import entidades.Trabajador;
import logica.TareaLogic;
import logica.Tipo_TareaLogic;
import logica.TrabajadorLogic;

/**
 * Servlet implementation class Empleados
 */
@WebServlet("/Empleados")
public class Empleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Empleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Trabajador> ofs, obs;
		ArrayList<Tarea> tareas=new ArrayList<Tarea>();
		ofs=obs=new ArrayList<Trabajador>();
		Date now=new Date(System.currentTimeMillis());
		try {
			ofs=TrabajadorLogic.getOficiales();
			obs=TrabajadorLogic.getObreros();
			tareas=TareaLogic.getTareasActivas(now);
		}
		catch(Exception e) {
			request.setAttribute("error", "Error al solicitar datos de empleados: "+e.getMessage());
		}
		request.setAttribute("oficiales", ofs);
		request.setAttribute("obreros", obs);
		request.setAttribute("activas", tareas);
		request.getRequestDispatcher("./Empleados.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion2=null,accion;
		try {
			accion2=(String)request.getParameter("accion_2");
		}
		catch(Exception e) {}
		if(accion2!=null) {
			accion=accion2;
		}
		else {
			accion=(String)request.getParameter("accion");
		}
		try {
			switch(accion) {
				case "Registrar":{
					Registrar(request, response);
					break;
				}
				case "Editar":{
					Modificar(request, response);
					break;
				}
				case "Eliminar":{
					Eliminar(request, response);
					break;
				}
				case "Asignar":{
					AsignarEmpleado(request, response);
				}
				}
		}catch(Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		doGet(request, response);
	}
	
	/**
	 * @throws Exception 
	 * @see Registra oficial/obrero y su correspondiente usuario
	 */
	protected void Registrar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nombre=(String)request.getParameter("nombre");
		String apellido=(String)request.getParameter("apellido");
		String email=(String)request.getParameter("email");
		String password=(String)request.getParameter("password");
		long cuil=Long.parseLong(request.getParameter("cuil"));
		String tipo_doc=(String)request.getParameter("tipodoc");
		long ndoc=Long.parseLong(request.getParameter("ndoc"));
		//
		String date=request.getParameter("fnac");
		//
		Date fnac=null;
		try {
			fnac = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		}catch(Exception e) {
			throw new Exception("Error al procesar fecha");
		}
		String tipo_e=(String)request.getParameter("tipo_e");
		String disponible=request.getParameter("disponible");
		boolean disp=disponible.equalsIgnoreCase("Disponible");
		Trabajador t=new Trabajador(0, nombre, apellido, email, password, cuil, "Trabajador", tipo_doc, ndoc, fnac, disp, tipo_e, 0);
		TrabajadorLogic.Registrar(t);
	}
	
	protected void Modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id=Integer.parseInt(request.getParameter("idusu"));
		String nombre=(String)request.getParameter("nombre");
		String apellido=(String)request.getParameter("apellido");
		String email=(String)request.getParameter("email");
		String password=(String)request.getParameter("password");
		long cuil=Long.parseLong(request.getParameter("cuil"));
		String tipo_doc=(String)request.getParameter("tipodoc");
		long ndoc=Long.parseLong(request.getParameter("ndoc"));
		//
		String date=request.getParameter("fnac");
		//
		Date fnac=null;
		try {
			fnac = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		}catch(Exception e) {
			throw new Exception("Error al procesar fecha");
		}
		String tipo_e=(String)request.getParameter("tipo_e");
		String disponible=request.getParameter("disponible");
		boolean disp=disponible.equalsIgnoreCase("Disponible");
		Trabajador t=new Trabajador(id, nombre, apellido, email, password, cuil, "Trabajador", tipo_doc, ndoc, fnac, disp, tipo_e, 0);
		TrabajadorLogic.ActualizarDatos(t);
	}
	
	protected void Eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id=Integer.parseInt(request.getParameter("idusu"));
		long cuil=Long.parseLong(request.getParameter("cuil"));
		TrabajadorLogic.Eliminar(id, cuil);
	}

	
protected void AsignarEmpleado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		int id=Integer.parseInt(request.getParameter("idTarea"));
		long cuil=Long.parseLong(request.getParameter("cuilT"));
		String dia = (String)request.getParameter("f_asignacion");
		Date d=sdf.parse(dia);
		int cantHoras=Integer.parseInt(request.getParameter("cantHoras"));
		TareaLogic.AsignarTrabajador(id, cuil, cantHoras, d);
		request.setAttribute("msg", "Se han asignado las horas con exito!");
	}
}
