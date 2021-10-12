/**
 * 
 */
package logica;
import entidades.Usuario;

import java.sql.SQLException;

import datos.UsuariosData;

/**
 * @author Usuario
 *
 */
public class UsuarioLogic {
	private UsuariosData source;
	
	public UsuarioLogic() {
		this.source=new UsuariosData();
	}
	
	public boolean IniciaSesion(String dir, String pw) throws SQLException {
		Usuario u=this.source.get(dir);
		if(u.getPassword().equals(pw)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public Usuario get(String dir) throws SQLException {
		Usuario u=this.source.get(dir);
		return u;
	}
	

}
