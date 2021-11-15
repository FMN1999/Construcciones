package logica;

import java.util.ArrayList;

import datos.ProveedorData;
import entidades.Proveedor;

public class ProvedorLogic {
	private static ProveedorData source=new ProveedorData();
	
	public static void Registrar(Proveedor p) throws Exception {
		
		try {
			source.Registrar(p);
		}
		catch(Exception e){
			//a presentacion solo deben llegar exceptciones de la clase Exception
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
	}
	
	public static void ActualizarDatos(Proveedor p) throws Exception {
		
		try {
			source.ActualizarDatos(p);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
	}
	
	public static void Eliminar(int id) throws Exception {
		try {
			source.Eliminar(id);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
	}
	
	public static Proveedor getOne(int id) throws Exception {
		Proveedor p;
		try {
			p=source.getOne(id);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
		return p;
	}
	
	public static ArrayList<Proveedor> getAll() throws Exception{
		ArrayList<Proveedor> provs;
		try {
			provs=source.getAll();
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		return provs;
	}
	
}
