package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.Tipo_TareaData;
import entidades.Maquinaria;
import entidades.Tipo_Tarea;

public class Tipo_TareaLogic {
	public static Tipo_TareaData source=new Tipo_TareaData();
	
	//no se captura ningun error, todos van a capa de presentacion
	
	public static void ActualizarDatos(Tipo_Tarea tt) throws SQLException, Exception {
		boolean modificaPre=false;
		Tipo_Tarea mVerif=source.getOne(tt.getId_tipo_tarea());
		if(mVerif.getPrecio()-tt.getPrecio()!=0) {
			modificaPre=true;
		}
		source.ActualizarDatos(tt, modificaPre);
	}


	public static void Registrar(Tipo_Tarea tt) throws Exception {
		source.Registrar(tt);
	}
	
	public static void Eliminar(int id) throws Exception{
		source.Eliminar(id);
	}
	
	
	public static ArrayList<Tipo_Tarea> getAll() throws Exception{
		ArrayList<Tipo_Tarea> tps;
		try {
			tps=source.getAll();
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		return tps;
	}
}