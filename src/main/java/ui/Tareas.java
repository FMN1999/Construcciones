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
import entidades.Tarea;
import entidades.Tipo_Tarea;
import entidades.Usuario;
import logica.ClienteLogic;
import logica.ObraLogic;
import logica.TareaLogic;
import logica.Tipo_TareaLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Tipo_Tarea
 */
@WebServlet("/Tareas")
public class Tareas extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Tareas() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*ArrayList<Tipo_Tarea> tipos=null;
		try {
			tipos=Tipo_TareaLogic.getAll();
			tipos=TareaLogic.getAll(tipos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.setAttribute("tipos", tipos);
		request.getRequestDispatcher("./Tareas.jsp").forward(request, response); */
		int idObra;
		idObra = Integer.parseInt(request.getParameter("idObra"));
		HttpSession se = request.getSession();
		Cliente c = (Cliente)se.getAttribute("cliente");
		Obra o = c.getObra(idObra);
		
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
			String descripcion=(String)request.getParameter("descripciontarea");
			float cantm2=Float.parseFloat(request.getParameter("cantm2tarea"));
			int idTipo=Integer.parseInt(request.getParameter("tipotarea"));
			Tarea tarea=new Tarea(0,descripcion, cantm2, 0, idTipo);
			TareaLogic.Registrar(tarea);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible registrar la tarea>> "+e.getMessage());
		}
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("idtarea"));
			String descripcion=(String)request.getParameter("descripciontarea");
			float cantm2=Float.parseFloat(request.getParameter("cantm2tarea"));
			int idTipo=Integer.parseInt(request.getParameter("tipotarea"));
			Tarea tarea=new Tarea(id, descripcion, cantm2, 0, idTipo);
			TareaLogic.Actualizar(tarea);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible actualizar los datos de la tarea>> "+e.getMessage());
		}
	}

	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("idtarea"));
			TareaLogic.Eliminar(id);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible eliminar la tarea>> "+e.getMessage());
		}
	}

}
