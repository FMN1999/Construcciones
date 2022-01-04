package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Material;
import entidades.Proveedor;
import logica.MaterialLogic;
import logica.ProvedorLogic;

/**
 * Servlet implementation class Materiales
 */
@WebServlet("/Materiales")
public class Materiales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Materiales() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Proveedor> provs=null;
		try {
			provs= ProvedorLogic.getAll();
			provs=MaterialLogic.getAll(provs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "Un error ha ocurrido mientras procesabamos su solicitud: "+e.getMessage());
		}
		request.setAttribute("provedores", provs);
		request.getRequestDispatcher("./Materiales.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
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
		}
		catch(Exception e) {
			request.setAttribute("error", "Un error ha ocurrido mientras procesabamos su solicitud: "+e.getMessage());
		}
		doGet(request, response);
	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String descripcion=(String)request.getParameter("descripcionmaterial");
			int id_prov=Integer.parseInt(request.getParameter("idprovedor"));
			float precio=Float.parseFloat(request.getParameter("preciomaterial"));
			Material m=new Material(0,descripcion, precio);
			MaterialLogic.Registrar(m, id_prov);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible registrar el material>> "+e.getMessage());
		}
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("idmaterial"));
			String descripcion=(String)request.getParameter("descripcionmaterial");
			int id_prov=Integer.parseInt(request.getParameter("idprovedor"));
			float precio=Float.parseFloat(request.getParameter("preciomaterial"));
			Material m=new Material(id,descripcion, precio);
			MaterialLogic.ActualizarDatos(m, id_prov);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible actualizar los datos del material>> "+e.getMessage());
		}
	}

	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("idmaterial"));
			MaterialLogic.Eliminar(id);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible eliminar el material>> "+e.getMessage());
		}
	}

}
