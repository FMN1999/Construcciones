package ui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Material;
import entidades.Trabajador;
import logica.ClienteLogic;
import logica.MaterialLogic;
import logica.TrabajadorLogic;

@WebServlet("/Clientes")
public class Clientes extends HttpServlet{
	private static final long serialVersionUID = 1L;
    public Clientes() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clis = null;
		try {
			clis= ClienteLogic.getAll();
		}
		catch(Exception e) {
			request.setAttribute("error", "Error al solicitar datos de clientes: "+e.getMessage());
		}
		request.setAttribute("clientes", clis);
		request.getRequestDispatcher("./Clientes.jsp").forward(request, response);
	}
    
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
				Modificar(request,response);
				break;
			}
			case "Eliminar":{
				Eliminar(request,response);
				break;
			}
			}
		}
		catch(Exception e) {
			request.setAttribute("error", "Un error ha ocurrido mientras procesabamos su solicitud: "+e.getMessage());
		}
		doGet(request, response);
		}
    
    protected void registrar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nombre=(String)request.getParameter("nombre");
		String apellido=(String)request.getParameter("apellido");
		String email=(String)request.getParameter("email");
		String password=(String)request.getParameter("password");
		long cuil=Long.parseLong(request.getParameter("cuil"));
		String rzon_social=(String)request.getParameter("razon_social");
		String telefono=(String)request.getParameter("telefono");
		Cliente c =new Cliente(0, nombre, apellido, email, password, cuil, "Cliente", rzon_social, telefono, 0/*id_cliente no tiene uso en abm*/);
		ClienteLogic.Registrar(c);
	}
	
	protected void Modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id=Integer.parseInt(request.getParameter("idusuario"));
		String nombre=(String)request.getParameter("nombre");
		String apellido=(String)request.getParameter("apellido");
		String email=(String)request.getParameter("email");
		String password=(String)request.getParameter("password");
		long cuil=Long.parseLong(request.getParameter("cuil"));
		String rzon_social=(String)request.getParameter("razon_social");
		String telefono=(String)request.getParameter("telefono");
		
		Cliente c = new Cliente(id, nombre, apellido, email, password, cuil, "Cliente", rzon_social, telefono,0/*id_cliente no tiene uso en abm*/);
		ClienteLogic.ActualizarDatos(c);
	}
	
	protected void Eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id=Integer.parseInt(request.getParameter("idusuario"));
		long cuil=Long.parseLong(request.getParameter("cuil"));
		ClienteLogic.Eliminar(id, cuil);
	}
}
