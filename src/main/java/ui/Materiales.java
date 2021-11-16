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
		try {
			ArrayList<Proveedor> provs= ProvedorLogic.getAll();
			HashMap<Integer,Proveedor> provMap=new HashMap<Integer,Proveedor>();
			for(Proveedor p:provs) {
				provMap.put(p.getIdProveedor(), p);
			}
			request.setAttribute("provedores", provMap);
			ArrayList<Material> mats=MaterialLogic.getAll();
			request.setAttribute("materiales", mats);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("./Materiales.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
