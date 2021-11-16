package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ProvedorLogic;
import entidades.Proveedor;
/**
 * Servlet implementation class Proveedores
 */
@WebServlet("/Proveedores")
public class Proveedores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Proveedores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Proveedor> provs=new ArrayList<Proveedor>();
		try {
			provs=ProvedorLogic.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.setAttribute("provedores", provs);
		request.getRequestDispatcher("./Provedores.jsp").forward(request, response);
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
		String rs=(String)request.getParameter("razonsocial");
		String direccion=(String)request.getParameter("direccion");
		long telefono=Long.parseLong(request.getParameter("telefono"));
		Proveedor p=new Proveedor(0,rs,direccion,telefono);
		try {
			ProvedorLogic.Registrar(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("idprov"));
		String rs=(String)request.getParameter("razonsocial");
		String direccion=(String)request.getParameter("direccion");
		long telefono=Long.parseLong(request.getParameter("telefono"));
		Proveedor p=new Proveedor(id,rs,direccion,telefono);
		try {
			ProvedorLogic.ActualizarDatos(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("idprov"));
		try {
			ProvedorLogic.Eliminar(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

}
