package ui;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(urlPatterns = { "/AdminFilter", "/Proveedores", "/Materiales", "/Maquinarias", "/Empleados", "/Clientes", "/Tipo_Tareas", "/VerPresupuesto", "/Tareas", "/Tareas_Empleados" }, servletNames = {"Proveedores", "Materiales", "Maquinarias", "Empleados", "Clientes", "Tipo_Tareas", "VerPresupuesto", "Tareas", "Tareas_Empleados"})
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse r=((HttpServletResponse)response);
		HttpSession se= ((HttpServletRequest) request).getSession();
		
		Usuario user = (Usuario)se.getAttribute("usuario");
		
		if(!user.getTipo().equalsIgnoreCase("Administrador")) {
			r.sendRedirect("Home");
			request.setAttribute("error", "No tiene los permisos necesarios para ingresar a la p�gina! >:c.");
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
