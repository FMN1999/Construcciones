package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import datos.TareaData;
import entidades.Cliente;
import entidades.Presupuesto;
import entidades.Tarea;
import entidades.Trabajador;
import entidades.Usuario;

public class TareaLogic {
	public static TareaData source=new TareaData();
	
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
	
	/**@see _ Solo tareas que aun no terminaron
	 * */
	public static ArrayList<Tarea> getTareasActivas(Date d) throws Exception{
		ArrayList<Tarea> tareas=new ArrayList<Tarea>();
		try{
			tareas=source.getTareasActivas(d);
		}
		catch(Exception e) {
			throw new Exception("No fue posible recuperar las tareas->"+e.getMessage());
		}
		return tareas;
	}
	
	public static void AsignarTrabajador(int idTarea,long cuil, int horas, Date dia) throws Exception{
		//primero debe verificarse que el empleado no acumule mas de 8 horas trabajando ese dia
		int hs_trabajadas=source.HorasTrabajadas(dia, cuil);
		//si no acumula mas de 8 horas se registra la asignacion
		if((hs_trabajadas+horas)<=8) {
			source.AsignarTrabajador(idTarea, cuil, horas, dia);
		}
		else {
			//si tiene mas de 8 horas se lanza excepcion
			throw new Exception("No se asigno la tarea al trabajador-->Ya acumulara mas de 8hs trabajadas el dia "
					+dia+ ".\n Actualmente posee "+hs_trabajadas+"hs");
		}
	}
	
	public static void Registrar(int idPresupuesto,ArrayList<Tarea> tareas) throws Exception {
		for(Tarea t:tareas) {
			t.setIdTarea(source.Registrar(idPresupuesto, t));
			MaquinariaLogic.RegistrarUsoMaquinas(t.getIdTarea(), t.getMaquinas());
			MaterialLogic.RegistrarUsoMateriales(t.getIdTarea(), t.getMateriales());
		}
	}
	/**
	 * @see Añade al empleado las tareas del mes actual que le fueron asignadas
	 * */
	public static Trabajador getEmpleadoWithTareas(Usuario u)throws Exception{
		Trabajador t=TrabajadorLogic.toTrabajador(u);
		t.setTareasAsignadas(new ArrayList<Trabajador.TareaAsignada>());
		t=source.getTareasMesEmpleado(t);
		return t;
	}
	
	/**
	 * @see Añade al empleado las tareas asignadas posteriores al dia actual
	 * */
	public static Trabajador getEmpleadoTareasActivas(Usuario u)throws Exception{
		Trabajador t=TrabajadorLogic.toTrabajador(u);
		t.setTareasAsignadas(new ArrayList<Trabajador.TareaAsignada>());
		t=source.getTareasActivasEmpleado(t);
		return t;
	}
	
}