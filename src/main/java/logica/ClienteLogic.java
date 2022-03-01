package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.ClientesData;
import datos.UsuariosData;
import entidades.Cliente;
import entidades.Usuario;

public class ClienteLogic {

	private static ClientesData source=new ClientesData();
	public static ArrayList<Cliente> getAll() throws Exception {
		ArrayList<Cliente> trs=source.getAll();
		return trs;
	}
	public static Cliente getOne(long cuil) throws Exception {
		ArrayList<Cliente> clis=source.getAll();
		for(Cliente c: clis) {
			if(c.getCuil()==cuil) {
				return ObraLogic.setObras(c);
			}
		}
		throw new Exception("No se encontro el cliente");
	}
	
	public static void Registrar(Cliente c) throws Exception {
		UsuarioLogic.Registrar(c);
		source.Registrar(c);
	}

	public static void ActualizarDatos(Cliente t) throws Exception{
		UsuarioLogic.ActualizarDatos(t);
		source.ActualizarDatos(t);
	}
	
	public static void Eliminar(int id, long cuil) throws Exception{
		source.Eliminar(cuil);
		UsuarioLogic.Eliminar(id);
	}
	
	public static Cliente ObtenerDuenioObra(int idObra) throws Exception {
		Cliente c=source.ObtenerDuenioObra(idObra);
		return c;
	}
}
