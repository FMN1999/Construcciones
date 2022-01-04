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
	private static UsuariosData source=new UsuariosData();
	
	public static boolean IniciaSesion(String dir, String pw) throws SQLException {
		Usuario u=source.get(dir);
		if(u.getPassword().equals(pw)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public static Usuario get(String dir) throws SQLException {
		Usuario u=source.get(dir);
		return u;
	}
	
	/**
	 * @see Verifica que el e-mail y cuil no esten registrados,
	 *  y luego registra usuario
	 */
	public static void Registrar(Usuario u) throws Exception{
		//primero se debe verificar que no exista un usuario/trabajador con el mismo cuil
		Usuario u2=null;
		try {
			u2 = source.get(u.getCuil());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			u2=null;
		}
		if(u2!=null) {
			throw new Exception("¡El CUIL ingresado no esta disponible! El cuil "+u2.getCuil()+" pertenece al "+u2.getTipo()+" "+u2.getApellido()
			+" "+u2.getNombre());
		}
		
		//luego verificar existencia de usuario con mismo email
		try {
			u2=source.get(u.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			u2=null;
		}
		if(u2!=null) {
			throw new Exception("¡La direccion de correo electronico no esta disponible! Pertenece al "+u2.getTipo()+" "+u2.getApellido()
			+" "+u2.getNombre());
		}
		//luego se registra usuario- lo que a la vez devulve el id de usuario que se registro
		source.Registrar(u);
		
	}
	

	public static void ActualizarDatos(Usuario u) throws Exception{
		source.ActualizarDatos(u);
	}
	
	public static void Eliminar(int id) throws Exception{
		source.Eliminar(id);
	}
}
