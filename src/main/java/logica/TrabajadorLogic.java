package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.TrabajadorData;
import datos.UsuariosData;
import entidades.Trabajador;
import entidades.Usuario;

public class TrabajadorLogic {

	private static TrabajadorData source=new TrabajadorData();
	private static UsuariosData usuariodata=new UsuariosData();
	
	public static ArrayList<Trabajador> getOficiales() throws Exception {
		ArrayList<Trabajador> trs=source.getOficiales();
		return trs;
	}
	
	public static ArrayList<Trabajador> getObreros() throws Exception {
		ArrayList<Trabajador> trs=source.getObreros();
		return trs;
	}
	
	public static void Registrar(Trabajador t) throws Exception {
		//
		//primero se debe verificar que no exista un usuario/trabajador con el mismo cuil
		Usuario u=null;
		try {
			u = usuariodata.get(t.getCuil());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			u=null;
		}
		if(u!=null) {
			throw new Exception("¡El CUIL ingresado no esta disponible! El cuil "+u.getCuil()+" pertenece al "+u.getTipo()+" "+u.getApellido()
			+" "+u.getNombre());
		}
		
		//luego verificar existencia de usuario con mismo email
		try {
			u=usuariodata.get(t.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			u=null;
		}
		if(u!=null) {
			throw new Exception("¡La direccion de correo electronico no esta disponible! Pertenece al "+u.getTipo()+" "+u.getApellido()
			+" "+u.getNombre());
		}
		//luego se registra usuario- lo que a la vez devulve el id de usuario que se registro
		usuariodata.Registrar((Usuario)t);
		
		//finalmente se registra trabajador
		source.Registrar(t);
	}
}
