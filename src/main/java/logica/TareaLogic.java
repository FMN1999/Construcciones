package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.TareaData;
import datos.Tipo_TareaData;
import entidades.Cliente;
import entidades.Maquinaria;
import entidades.Obra;
import entidades.Tarea;
import entidades.Tipo_Tarea;

public class TareaLogic {
	public static TareaData source=new TareaData();
	
	//no se captura ningun error, todos van a capa de presentacion
	
	public static ArrayList<Tipo_Tarea> getAll(ArrayList<Tipo_Tarea> tipos) throws Exception{
		for(Tipo_Tarea tipo:tipos) {
			tipo.setTareas(source.getTareas(tipo.getId_tipo_tarea()));
		}
		return tipos;
	}
	public static void Registrar(Tarea t) throws Exception {
		source.Registrar(t);
	}
	
	public static void Actualizar(Tarea t) throws Exception {
		source.Actualizar(t);
	}
	
	public static void Eliminar(int id) throws Exception {
		source.Eliminar(id);
	}
}