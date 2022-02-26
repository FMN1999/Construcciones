package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.MaterialData;
import entidades.Material;
import entidades.Proveedor;
import entidades.Tarea;

public class MaterialLogic {
	public static MaterialData source=new MaterialData();
	
	//no se captura ningun error, todos van a capa de presentacion
	
	public static void ActualizarDatos(Material m, int id_prov) throws SQLException, Exception {
		boolean modificaPre=false;
		Material mVerif=source.getOne(m.getId_material());
		if(mVerif.getPrecio()-m.getPrecio()!=0) {
			modificaPre=true;
		}
		source.ActualizarDatos(m, modificaPre, id_prov);
	}
	
	
	/**
	 * @see _ Devuelve la misma lista de tareas pero con los materiales que le fueron asignados
	 * al momento de confeccionar el presupuesto
	 */
	public static ArrayList<Tarea> materiales_tareas(ArrayList<Tarea> tareas) throws Exception{
		for(Tarea t:tareas) {
			t.setMateriales(source.getMaterialesTarea(t.getIdTarea()));
		}
		return tareas;
	}
	
	/**
	 * @see _ Recupera los materiales asignados a la tarea
	 * Devuelve la tarea
	 */
	public static Tarea getMaterialesTarea(Tarea t) throws Exception{
		t.setMateriales(source.getMaterialesTarea(t.getIdTarea()));
		return t;
	}
	
	public static ArrayList<Proveedor> getAll(ArrayList<Proveedor> provs) throws Exception{
		for(Proveedor prov:provs) {
			prov.setMateriales(source.getMateriales(prov.getIdProveedor()));
		}
		
		return provs;
	}
	
	public static ArrayList<Material> getAll() throws Exception{
		return source.getAll();
	}
	
	public static void Registrar(Material m, int id_prov) throws Exception {
		source.Registrar(m, id_prov);
	}
	
	public static void Eliminar(int id) throws Exception{
		source.Eliminar(id);
	}
	
	public static void RegistrarUsoMateriales(Tarea t) {}
}
