package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Material;
import entidades.Obra;
import entidades.Proveedor;
import logica.ClienteLogic;
import logica.MaterialLogic;
import logica.ObraLogic;
import logica.ProvedorLogic;

/**
 * Servlet implementation class Obra
 */
@WebServlet(urlPatterns ={"/Obras"})
public class Obras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Obras() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clientes=null;
		try {
			clientes=ClienteLogic.getAll();
			clientes=ObraLogic.getAll(clientes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.setAttribute("clientes", clientes);
		request.getRequestDispatcher("./Obras.jsp").forward(request, response);
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
			String direccion=(String)request.getParameter("direccion");
			int id_cli=Integer.parseInt(request.getParameter("idcliente"));
			Obra o=new Obra(0,direccion);
			ObraLogic.Registrar(o, id_cli);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible registrar la obra>> "+e.getMessage());
		}
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id_obra=Integer.parseInt(request.getParameter("idobra"));
			String direccion=(String)request.getParameter("direccion");
			int id_cli=Integer.parseInt(request.getParameter("idcliente"));
			Obra o=new Obra(id_obra, direccion);
			ObraLogic.Actualizar(o);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible actualizar los datos de la obra>> "+e.getMessage());
		}
	}

	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("idobra"));
			ObraLogic.Eliminar(id);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible eliminar la obra>> "+e.getMessage());
		}
	}
	

}
