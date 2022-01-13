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
}
