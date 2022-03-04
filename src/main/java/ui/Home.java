package ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Trabajador;
import entidades.Usuario;
import logica.ClienteLogic;
import logica.TareaLogic;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns ={"/Home","/"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se = request.getSession();
		Usuario u = (Usuario)se.getAttribute("usuario");
		switch(u.getTipo()) {
		case "Cliente":{
			//el try esta recuperando el cliente y las obras del cliente.
			try {
				Cliente c = ClienteLogic.getOne(u.getCuil());
				se.setAttribute("cliente", c);
			}
			catch(Exception e) {
				request.setAttribute("error", e.getMessage());
				return;
			}
			break;
		}
		case "Trabajador":{
			try {
				Trabajador t=TareaLogic.getEmpleadoTareasActivas(u);
				request.setAttribute("empleado", t);
			}
			catch(Exception e) {
				request.setAttribute("error", "No fue posible recuperar tus tareas asignadas");
				return;
			}
			break;
		}
		}
		request.getRequestDispatcher("./Home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
