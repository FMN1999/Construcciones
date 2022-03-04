package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Obra;
import entidades.Usuario;
import logica.ClienteLogic;
import logica.ObraLogic;

/**
 * Servlet implementation class ObrasCliente
 */
@WebServlet("/ObrasCliente")
public class ObrasCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrasCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		HttpSession sesion = request.getSession();
		Usuario user = (Usuario)sesion.getAttribute("usuario");
		try {
			Cliente c = ClienteLogic.getOne(user.getCuil());
			clientes.add(c);
			clientes=ObraLogic.getAll(clientes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.setAttribute("clientes", clientes);
		request.getRequestDispatcher("./ObrasCliente.jsp").forward(request, response);
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
			HttpSession sesion = request.getSession();
			Usuario cli = (Usuario)sesion.getAttribute("usuario");			
			Cliente c = ClienteLogic.getOne(cli.getCuil());
			int id_cli = c.getIdCliente();
			String desc = (String)request.getParameter("descripcion");
			Obra o=new Obra(0,direccion, desc, false);
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
			String desc = (String)request.getParameter("descripcion");
			Obra o=new Obra(id_obra, direccion, desc, false);
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
