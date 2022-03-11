package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Usuario;
import logica.ClienteLogic;
import logica.UsuarioLogic;

/**
 * Servlet implementation class ServLogin
 */
@WebServlet(description = "Administra inicio de sesion", urlPatterns = { "/ServLogin" })
public class ServLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se= request.getSession();
		if(se.getAttribute("usuario")!=null) {
			response.sendRedirect("Home");
			return;
		}
		request.getRequestDispatcher("./inicio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion=(String)request.getParameter("accion");
		switch(accion) {
		case "Ingresar":{
			String correo=request.getParameter("correo");
			String clave=request.getParameter("contra");
			HttpSession se= request.getSession();
			try {
				if(UsuarioLogic.IniciaSesion(correo, clave)) {
					Usuario u=UsuarioLogic.get(correo);
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
						break;
					}
					}
					se.setAttribute("usuario", u);
					se.setMaxInactiveInterval(30*60);
					response.sendRedirect("Home");
					return;
				}
				else {
					request.setAttribute("error", "Usuario y/o contrasena Incorrectos");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("./inicio.jsp").forward(request, response);
		}
		
		case "Registrar":{
			request.getRequestDispatcher("./Registrarse.jsp").forward(request, response);
		}
		}
		
	}

}
