package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.TareaData;
import entidades.Presupuesto;
import entidades.Tarea;

public class TareaLogic {
	public static TareaData source=new TareaData();
	
	//no se captura ningun error, todos van a capa de presentacion
	
	public static ArrayList<Tarea> getTareas(int idPresup) throws Exception{
		ArrayList<Tarea> tareas=source.getTareas(idPresup);
		return tareas; 
	}
	/*
	public static void Registrar(Tarea t) throws Exception {
		source.Registrar(t);
	}
	
	public static void Actualizar(Tarea t) throws Exception {
		source.Actualizar(t);
	}
	
	public static void Eliminar(int id) throws Exception {
		source.Eliminar(id);
	}*/
}