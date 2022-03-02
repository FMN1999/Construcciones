package ui;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Obra;
import logica.ObraLogic;
import logica.PresupuestoLogic;

/**
 * Servlet implementation class PresupuestoCliente
 */
@WebServlet("/PresupuestoCliente")
public class PresupuestoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PresupuestoCliente() {
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
		try {
			entidades.Presupuesto p = PresupuestoLogic.getOne(id,true);
			request.setAttribute("presupuesto", p);
			Obra o=ObraLogic.getOne(idObra);
			request.setAttribute("obra", o);
		}
		catch(Exception e){
			request.setAttribute("error", "Error al cargar los datos del presupuesto: "+e.getMessage());
		}
		request.getRequestDispatcher("./Presupuesto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion=(String)request.getParameter("accion");
		int id = Integer.parseInt(request.getParameter("idpresupuesto"));
		java.util.Date hoy=new java.util.Date(System.currentTimeMillis());
		try {
			entidades.Presupuesto p = PresupuestoLogic.getOne(id,false);
			switch(accion) {
				case "aprobar":{
					p.setFecha_aceptacion(hoy);
					break;
				}
				case "rechazar":{
					p.setFecha_caencelacion(hoy);
					break;
				}
			}
			PresupuestoLogic.RegistrarEstadoPresupuesto(p);
		}
		catch(Exception e){
			request.setAttribute("error", "Error al cargar los datos del presupuesto: "+e.getMessage());
		}
		response.sendRedirect("Home");
	}

}
