package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Trabajador;
import logica.PresupuestoLogic;
import logica.TrabajadorLogic;

/**
 * Servlet implementation class AsignarEmpleados
 */
@WebServlet("/AsignarEmpleados")
public class AsignarEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignarEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idP=Integer.parseInt((String)request.getParameter("idPresupuesto"));
		try {
			entidades.Presupuesto p = PresupuestoLogic.getOne(idP, true);
			ArrayList<Trabajador> obreros = TrabajadorLogic.getObreros();
			ArrayList<Trabajador> oficiales = TrabajadorLogic.getOficiales();
			
		}
		catch(Exception e){
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
