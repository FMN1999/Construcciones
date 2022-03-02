package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.TareaData;
import entidades.Presupuesto;
import entidades.Tarea;

public class TareaLogic {
	public static TareaData source=new TareaData();
	
	//no se captura ningun error, todos van a capa de presentacion
	
	/**
	 * @see _ Recupera todas las tareas del presupuesto p
	 *  y ademas recupera los materiales asignados(con sus precios correspondientes)
	 */
	public static ArrayList<Tarea> getTareas(Presupuesto p) throws Exception{
		ArrayList<Tarea> tareas=new ArrayList<Tarea>();
		try{
			tareas=source.getTareas(p);
		}
		catch(Exception e) {
			throw new Exception("No fue posible recuperar las tareas presupuestadas->"+e.getMessage());
		}
		try {
			tareas=MaterialLogic.materiales_tareas(tareas);
		}
		catch(Exception e) {
			throw new Exception("No fue posible recuperar los materiles presupuestados:"+e.getMessage());
		}
		tareas=MaquinariaLogic.maquinas_tareas(tareas);
		
		return tareas; 
	}
	
	public static Tarea getTarea(int id) throws SQLException {
		return source.getTarea(id);
	}
	
	public static void Registrar(int idPresupuesto,ArrayList<Tarea> tareas) throws Exception {
		for(Tarea t:tareas) {
			t.setIdTarea(source.Registrar(idPresupuesto, t));
			MaquinariaLogic.RegistrarUsoMaquinas(t.getIdTarea(), t.getMaquinas());
			MaterialLogic.RegistrarUsoMateriales(t.getIdTarea(), t.getMateriales());
		}
	}
	/*
	public static void Actualizar(Tarea t) throws Exception {
		source.Actualizar(t);
	}
	
	public static void Eliminar(int id) throws Exception {
		source.Eliminar(id);
	}*/
}