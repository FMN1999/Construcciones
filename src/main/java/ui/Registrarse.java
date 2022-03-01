package ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import logica.ClienteLogic;

/**
 * Servlet implementation class Registrarse
 */
@WebServlet("/Registrarse")
public class Registrarse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrarse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String nombre=(String)request.getParameter("nombre");
			String apellido=(String)request.getParameter("apellido");
			String email=(String)request.getParameter("email");
			String password=(String)request.getParameter("password");
			long cuil=Long.parseLong(request.getParameter("cuil"));
			String rzon_social=(String)request.getParameter("razon_social");
			String telefono=(String)request.getParameter("telefono");
			Cliente c =new Cliente(0, nombre, apellido, email, password, cuil, "Cliente", rzon_social, telefono, 0/*id_cliente no tiene uso en abm*/);			
			ClienteLogic.Registrar(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "Un error ha ocurrido mientras procesabamos su solicitud: "+e.getMessage());
		}
		response.sendRedirect("ServLogin");
		//return;
		}
}
