package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Obra;
import logica.ObraLogic;
import logica.PresupuestoLogic;

/**
 * Servlet implementation class Presupuesto
 */
@WebServlet("/Presupuesto")
public class Presupuesto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Presupuesto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("idObra"));
		try {
			Obra o = ObraLogic.getOne(id);
			request.setAttribute("obra", o);
			
			ArrayList<entidades.Presupuesto> psp= PresupuestoLogic.getPresuspuestos(o, false);
			request.setAttribute("presupuestos", psp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "Error al cargar los datos de la obra: "+e.getMessage());
		}
		request.getRequestDispatcher("./Presupuestos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
