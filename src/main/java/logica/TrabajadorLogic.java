package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.TrabajadorData;
import datos.UsuariosData;
import entidades.Trabajador;
import entidades.Usuario;

public class TrabajadorLogic {

	private static TrabajadorData source=new TrabajadorData();
	
	public static ArrayList<Trabajador> getOficiales() throws Exception {
		ArrayList<Trabajador> trs=source.getOficiales();
		return trs;
	}
	
	public static ArrayList<Trabajador> getObreros() throws Exception {
		ArrayList<Trabajador> trs=source.getObreros();
		return trs;
	}
	
	public static void Registrar(Trabajador t) throws Exception {
		UsuarioLogic.Registrar(t);
		source.Registrar(t);
	}

	public static void ActualizarDatos(Trabajador t) throws Exception{
		UsuarioLogic.ActualizarDatos(t);
		source.ActualizarDatos(t);
	}
	
	public static void Eliminar(int id, long cuil) throws Exception{
		source.Eliminar(cuil);
		UsuarioLogic.Eliminar(id);
	}
	
	public static Trabajador toTrabajador(Usuario u)throws Exception{
		ArrayList<Trabajador> trabs=getOficiales();
		trabs.addAll(getObreros());
		for(Trabajador t: trabs) {
			if(t.getCuil()==u.getCuil()) {
				return t;
			}
		}
		return null;
	}
}
