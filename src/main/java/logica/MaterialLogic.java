package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.MaterialData;
import entidades.Material;

public class MaterialLogic {
	public static MaterialData source=new MaterialData();
	
	//no se captura ningun error, todos van a capa de presentacion
	
	public static void ActualizarDatos(Material m) throws SQLException, Exception {
		boolean modificaPre=false;
		Material mVerif=source.getOne(m.getId_material());
		if(mVerif.getPrecio()-m.getPrecio()!=0) {
			modificaPre=true;
		}
		source.ActualizarDatos(m, modificaPre);
	}
	
	public static ArrayList<Material> getAll() throws Exception{
		ArrayList<Material> mats=source.getAll();
		return mats;
	}
	
	public static void Registrar(Material m) throws Exception {
		source.Registrar(m);
	}
	
	public static void Eliminar(int id) throws Exception{
		source.Eliminar(id);
	}
}
