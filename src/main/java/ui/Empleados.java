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

import entidades.Trabajador;
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
		ofs=obs=new ArrayList<Trabajador>();
		try {
			ofs=TrabajadorLogic.getOficiales();
			obs=TrabajadorLogic.getObreros();
		}
		catch(Exception e) {
			request.setAttribute("error", "Error al solicitar datos de empleados: "+e.getMessage());
		}
		request.setAttribute("oficiales", ofs);
		request.setAttribute("obreros", obs);
		request.getRequestDispatcher("./Empleados.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion=(String)request.getParameter("accion");
		try {
		switch(accion) {
			case "Registrar":{
				Registrar(request, response);
				break;
			}
			case "Modificar":{
				Modificar(request, response);
				break;
			}
			case "Eliminar":{
				Eliminar(request, response);
				break;
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
		Date fnac=null;
		try {
			fnac = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fnac"));
		}catch(Exception e) {
			throw new Exception("Error al procesar fecha");
		}
		String tipo_e=(String)request.getParameter("tipo_e");
		Trabajador t=new Trabajador(0, nombre, apellido, email, password, cuil, "Trabajador", tipo_doc, ndoc, fnac, true, tipo_e, 0);
		TrabajadorLogic.Registrar(t);
	}
	
	protected void Modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	protected void Eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
