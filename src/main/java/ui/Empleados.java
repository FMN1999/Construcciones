package ui;

import java.io.IOException;
import java.util.ArrayList;

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
		doGet(request, response);
	}

}
