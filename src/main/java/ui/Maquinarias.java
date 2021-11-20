package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Maquinaria;
import logica.MaquinariaLogic;

/**
 * Servlet implementation class Maquinarias
 */
@WebServlet("/Maquinarias")
public class Maquinarias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Maquinarias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Maquinaria> maqs=new ArrayList<Maquinaria>();
		try {
			maqs=MaquinariaLogic.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.setAttribute("maquinarias", maqs);
		request.getRequestDispatcher("./Maquinarias.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accion=(String)request.getParameter("accion");
		switch(accion) {
		case "Registrar":{
			registrar(request,response);
			break;
		}
		case "Editar":{
			actualizar(request,response);
			break;
		}
		case "Eliminar":{
			eliminar(request,response);
			break;
		}
		}
		doGet(request,response);
	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String descripcion=(String)request.getParameter("descripcion");
		float precioHora=Float.parseFloat(request.getParameter("precioHora"));
		Maquinaria m = new Maquinaria(0,descripcion,precioHora);
		try {
			MaquinariaLogic.Registrar(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("idmaq"));
		String descripcion=(String)request.getParameter("descripcion");
		float precioHora=Float.parseFloat(request.getParameter("precioHora"));
		Maquinaria m=new Maquinaria(id,descripcion,precioHora);
		try {
			MaquinariaLogic.ActualizarDatos(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("idmaq"));
		try {
			MaquinariaLogic.Eliminar(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

}
