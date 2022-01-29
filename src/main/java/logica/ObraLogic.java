package logica;

import java.util.ArrayList;

import datos.ObraData;
import entidades.Cliente;
import entidades.Material;
import entidades.Obra;
import entidades.Proveedor;

public class ObraLogic {
	public static ObraData source=new ObraData();
	
//	public static void Registrar(Proveedor p) throws Exception {
//		
//		try {
//			source.Registrar(p);
//		}
//		catch(Exception e){
//			//a presentacion solo deben llegar exceptciones de la clase Exception
//			String msg=e.getMessage();
//			throw new Exception(msg);
//		}
//		
//	}
//	
//	public static void ActualizarDatos(Proveedor p) throws Exception {
//		
//		try {
//			source.ActualizarDatos(p);
//		}
//		catch(Exception e) {
//			String msg=e.getMessage();
//			throw new Exception(msg);
//		}
//	}
//	
//	public static void Eliminar(int id) throws Exception {
//		try {
//			source.Eliminar(id);
//		}
//		catch(Exception e) {
//			String msg=e.getMessage();
//			throw new Exception(msg);
//		}
//	}
	
	public static Obra getOne(int id) throws Exception {
		Obra o;
		try {
			o=source.getOne(id);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
		return o;
	}
	
	
	public static ArrayList<Cliente> getAll(ArrayList<Cliente> clientes) throws Exception{
		for(Cliente cli:clientes) {
			cli.setObras(source.getObras(cli.getIdCliente()));
		}
		
		return clientes;
	}
	
	public static void Registrar(Obra o) throws Exception {
		source.Registrar(o);
	}
	
	public static void Actualizar(Obra o) throws Exception {
		source.Actualizar(o);
	}
	
	public static void Eliminar(int id) throws Exception {
		source.Eliminar(id);
	}
	
	
}
