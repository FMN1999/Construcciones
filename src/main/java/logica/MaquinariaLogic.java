package logica;

import java.util.ArrayList;

import datos.MaquinariaData;
import entidades.Maquinaria;

public class MaquinariaLogic {
	
	private static MaquinariaData source=new MaquinariaData();
		
	public static void Registrar(Maquinaria m) throws Exception {
		
		try {
			source.Registrar(m);
		}
		catch(Exception e){
			//a presentacion solo deben llegar exceptciones de la clase Exception
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
	}
	
	public static void ActualizarDatos(Maquinaria m) throws Exception {
		
		try {
			source.ActualizarDatos(m);
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
	
	public static Maquinaria getOne(int id) throws Exception {
		Maquinaria m;
		try {
			m=source.getOne(id);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
		return m;
	}
	
	public static ArrayList<Maquinaria> getAll() throws Exception{
		ArrayList<Maquinaria> maqs;
		try {
			maqs=source.getAll();
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		return maqs;
	}

}
