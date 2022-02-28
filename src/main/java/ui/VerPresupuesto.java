package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Maquinaria;
import entidades.Material;
import entidades.Obra;
import logica.MaquinariaLogic;
import logica.MaterialLogic;
import logica.ObraLogic;
import entidades.Presupuesto;
import entidades.Tipo_Tarea;
import logica.PresupuestoLogic;
import logica.Tipo_TareaLogic;

/**
 * Servlet implementation class VerPresupuesto
 */
@WebServlet("/VerPresupuesto")
public class VerPresupuesto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPresupuesto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("idPresupuesto"));
		int idObra=Integer.parseInt(request.getParameter("idObra"));
		Presupuesto p= new Presupuesto();
		p.setId_obra(idObra);
		try {
			if(id!=0) {
				p = PresupuestoLogic.getOne(id,true);
			}
			ArrayList<Tipo_Tarea> tt=Tipo_TareaLogic.getAll();
			request.setAttribute("tipos", tt);
			
			ArrayList<Maquinaria> maq=MaquinariaLogic.getAll();
			request.setAttribute("maquinas", maq);
			
			ArrayList<Material> mats=MaterialLogic.getAll();
			request.setAttribute("materiales", mats);
			
			request.setAttribute("presupuesto", p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "Error al cargar los datos del presupuesto: "+e.getMessage());
		}
		request.getRequestDispatcher("./Presupuesto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
